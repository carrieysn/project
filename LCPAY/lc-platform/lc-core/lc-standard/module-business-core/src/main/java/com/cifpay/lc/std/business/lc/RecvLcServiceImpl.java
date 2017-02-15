package com.cifpay.lc.std.business.lc;

import com.cifpay.lc.api.BusinessOutput;
import com.cifpay.lc.api.gateway.lc.RecvLcService;
import com.cifpay.lc.constant.BizConstants;
import com.cifpay.lc.constant.BizConstants.LcTranCode;
import com.cifpay.lc.constant.ReturnCode;
import com.cifpay.lc.constant.enums.LcStatusType;
import com.cifpay.lc.constant.enums.LcTranStatus;
import com.cifpay.lc.core.cache.pojo.LcBankCache;
import com.cifpay.lc.core.cache.pojo.LcProductCache;
import com.cifpay.lc.core.cache.service.LcBankCacheServant;
import com.cifpay.lc.core.common.*;
import com.cifpay.lc.core.db.dao.LcDao;
import com.cifpay.lc.core.db.dao.LcLogDao;
import com.cifpay.lc.core.db.dao.LcRecvDao;
import com.cifpay.lc.core.db.pojo.Lc;
import com.cifpay.lc.core.db.pojo.LcLog;
import com.cifpay.lc.core.db.pojo.LcRecv;
import com.cifpay.lc.core.exception.CoreBusinessException;
import com.cifpay.lc.core.exception.CoreValidationRejectException;
import com.cifpay.lc.core.uid.LcLogIdWorker;
import com.cifpay.lc.core.uid.LcRecvIdWorker;
import com.cifpay.lc.domain.lc.RecvLcInputBean;
import com.cifpay.lc.domain.lc.RecvLcOutputBean;
import com.cifpay.lc.std.interceptor.BusinessLockInterceptor;
import com.cifpay.lc.std.interceptor.BusinessLoggingInterceptor;
import com.cifpay.lc.std.interceptor.LcAutoFlowProcessingInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

@Service("recvLcService")
@CoreBusinessInterceptorConfig({BusinessLoggingInterceptor.class, BusinessLockInterceptor.class, LcAutoFlowProcessingInterceptor.class})
@CoreBusinessTranCode(LcTranCode.RECV)
public class RecvLcServiceImpl extends AbstractLcProductServiceImplBase<RecvLcInputBean, RecvLcOutputBean> implements RecvLcService {

    @Autowired
    private LcDao lcDao;
    @Autowired
    private LcRecvDao lcRecvDao;
    @Autowired
    private LcLogDao lcLogDao;
    @Autowired
    private LcBankCacheServant lcBankCacheServant;

    @Autowired
    private LcRecvIdWorker lcRecvIdWorker;
    @Autowired
    private LcLogIdWorker lcLogIdWorker;

    @Autowired
    private DbTransactionHelper dbTransactionHelper;

    @Override
    public void validateInputParameters(RecvLcInputBean inputBean) throws CoreValidationRejectException {

        if (inputBean.getLcId() <= 0) {
            throw new CoreValidationRejectException(103300, "银信证ID不允许为空");
        }
        if (!StringUtils.hasText(inputBean.getMerId())) {
            throw new CoreValidationRejectException(103300, "商户号不允许为空");
        }
        if (!StringUtils.hasText(inputBean.getRecvBankCode())) {
            throw new CoreValidationRejectException(103300, "收证银行代码不允许为空");
        }
        if (!StringUtils.hasText(inputBean.getRecvBankAccountNo())) {
            throw new CoreValidationRejectException(103300, "收证银行账号不允许为空");
        }
        if (null == inputBean.getRecvAccountType()) {
            throw new CoreValidationRejectException(103300, "收证账号类型不允许为空");
        }
    }

    @Override
    protected void validateLc(RecvLcInputBean inputBean, Lc lc) throws CoreValidationRejectException {
        if (lc == null) {
            throw new CoreValidationRejectException(ReturnCode.CORE_STD_LC_NOT_EXISTS, "银信证记录不存在");
        }
        // 检查银信证状态
        String lcStatus = lc.getLcStatus();
        if (lcStatus == null) {
            throw new CoreValidationRejectException(ReturnCode.CORE_STD_LC_STATUS_CHECK_UNPASSED, "银信证状态不正确");
        }
        if (LcStatusType.RETREAT.getStatusCode().compareTo(lc.getLcStatus()) == 0) {
            throw new CoreValidationRejectException(ReturnCode.CORE_STD_LC_INVALID, "银信证已失效");
        }
        if (LcStatusType.PRE_INVALID.getStatusCode().compareTo(lcStatus) == 0) {
            throw new CoreValidationRejectException(ReturnCode.CORE_STD_LC_INVALID, "银信证已失效");
        }
        if (LcStatusType.OPENED.getStatusCode().compareTo(lcStatus) != 0) {
            throw new CoreValidationRejectException(ReturnCode.CORE_STD_LC_STATUS_CHECK_UNPASSED, "收证状态校验不通过");
        }
        // 检查收证有效期
        Date recvValidate = lc.getRecvValidTime();
        Date now = new Date();
        if (recvValidate.getTime() < now.getTime()) { // 如果收证有效期小于当前时间，则过期
            throw new CoreValidationRejectException(ReturnCode.CORE_STD_LC_INVALID, "收银信证已失效");
        }

    }

    @Override
    protected void validateLcProductRule(RecvLcInputBean inputBean, LcProductCache lcProductCache, CoreBusinessContext context) throws CoreValidationRejectException {
        // 检查收证银行
        LcBankCache lcBank = lcBankCacheServant.selectByPrimaryKey(inputBean.getRecvBankCode());
        if (lcBank == null) {
            throw new CoreValidationRejectException(ReturnCode.CORE_STD_LC_BANK_NOT_EXISTS, "银信证银行代码不存在");
        }
        context.setAttribute("LC_BANK", lcBank);
    }

    @Override
    public BusinessOutput<RecvLcOutputBean> processBusiness(RecvLcInputBean inputBean, CoreBusinessContext context) throws CoreBusinessException {
        logger.info("---进入收证业务处理:" + inputBean.getLcId());

        Long lcId = inputBean.getLcId();
        String mid = inputBean.getMerId();
        Date now = new Date();
        LcBankCache lcBank = (LcBankCache) context.getAttribute("LC_BANK");
        Lc lc = (Lc) context.getAttribute("LC");

        DbTransaction transaction = dbTransactionHelper.beginTransaction();
        try {

            long lcRecvId = lcRecvIdWorker.nextId();

            // 修改开证记录为当前处理程序

            // 插入收证记录
            LcRecv lcRecv = new LcRecv();
            lcRecv.setLcRecvId(lcRecvId);
            lcRecv.setCreateTime(now);
            lcRecv.setLcId(lcId);
            lcRecv.setMid(mid);
            // lcRecv.setRecvId(recvId);
            lcRecv.setRecvAccno(inputBean.getRecvBankAccountNo());
            lcRecv.setRecvType(inputBean.getRecvAccountType().getCode());
            lcRecv.setRecvBankCode(inputBean.getRecvBankCode());
            lcRecv.setRecvBankName(lcBank.getBankName());
            lcRecv.setRecvMobile(inputBean.getRecvMobile());
            lcRecv.setRemark(inputBean.getRemark());
            lcRecvDao.insert(lcRecv);

            // 插入流水表记录
            LcLog lcLog = new LcLog();
            lcLog.setLogId(lcLogIdWorker.nextId());
            lcLog.setCreateTime(now);
            lcLog.setLcId(lcId);
            lcLog.setStepLogId(lcRecvId);
            lcLog.setTradeCode(BizConstants.LcTranCode.RECV.getTranCodeStr());
            lcLog.setFromStatus(lc.getLcStatus());
            lcLog.setToStatus(LcStatusType.RECIEVED.getStatusCode());
            lcLog.setLcResponse(LcTranStatus.SUCCESS.getTranStatusStr());
            lcLog.setRemark("收证");
            lcLogDao.insertSelective(lcLog);

            // 修改银信证状态为“已收证”
            Lc updateLc = new Lc();
            updateLc.setLcId(lcId);
            updateLc.setRecvId(lcRecv.getRecvId());
            updateLc.setLcStatus(LcStatusType.RECIEVED.getStatusCode());
            updateLc.setRecvAccno(lcRecv.getRecvAccno());
            updateLc.setRecvType(lcRecv.getRecvType());
            updateLc.setRecvBankCode(lcRecv.getRecvBankCode());
            updateLc.setRecvBankName(lcRecv.getRecvBankName());
            updateLc.setRecvMobile(lcRecv.getRecvMobile());

            lcDao.updateByPrimaryKeySelective(updateLc);

            dbTransactionHelper.commitTransaction(transaction);

            // 成功返回
            RecvLcOutputBean outputBean = new RecvLcOutputBean();
            outputBean.setLcId(lcId);
            outputBean.setLcStatus(updateLc.getLcStatus());
            outputBean.setLcStatusDesc(LcStatusType.getDesc(updateLc.getLcStatus()));

            return BusinessOutput.success(outputBean);

        } catch (Throwable e) {
            dbTransactionHelper.rollbackTransaction(transaction);
            CoreBusinessExceptionHelper.throwAsCoreBusinessException(e);
        }

        logger.info("---离开收证业务处理");

        return BusinessOutput.fail(ReturnCode.CORE_STD_LC_RECV_ERROR, "收证失败");
    }

}
