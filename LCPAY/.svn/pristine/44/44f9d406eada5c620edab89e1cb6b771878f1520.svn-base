package com.cifpay.lc.std.business.lc;

import com.cifpay.lc.api.BusinessOutput;
import com.cifpay.lc.api.gateway.lc.OpenLcService;
import com.cifpay.lc.constant.ReturnCode;
import com.cifpay.lc.constant.enums.LcStatusType;
import com.cifpay.lc.constant.enums.LcTranStatus;
import com.cifpay.lc.constant.enums.MessageEnum;
import com.cifpay.lc.constant.enums.PayMethod;
import com.cifpay.lc.core.cache.pojo.LcProductCache;
import com.cifpay.lc.core.common.CoreBusinessContext;
import com.cifpay.lc.core.common.CoreBusinessInterceptorConfig;
import com.cifpay.lc.core.db.dao.LcOpenDao;
import com.cifpay.lc.core.db.dao.PreLcDao;
import com.cifpay.lc.core.db.pojo.Lc;
import com.cifpay.lc.core.db.pojo.LcOpen;
import com.cifpay.lc.core.db.pojo.PreLc;
import com.cifpay.lc.core.exception.CoreBusinessException;
import com.cifpay.lc.core.exception.CoreValidationRejectException;
import com.cifpay.lc.core.message.biz.impl.LcMessageHandler;
import com.cifpay.lc.core.uid.LcOpenIdWorker;
import com.cifpay.lc.domain.lc.OpenLcInputBean;
import com.cifpay.lc.domain.lc.OpenLcOutputBean;
import com.cifpay.lc.domain.message.LcFreezeParamBean;
import com.cifpay.lc.std.domain.paychannel.FreezeInputBean;
import com.cifpay.lc.std.domain.paychannel.FreezeOutputBean;
import com.cifpay.lc.std.interceptor.BusinessLockInterceptor;
import com.cifpay.lc.std.interceptor.BusinessLoggingInterceptor;
import com.cifpay.lc.std.paychannel.FreezeInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cifpay.lc.domain.lc.OpenLcUnionDepositInputBean;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 开证
 *
 * @author sweet
 */
@Service("openLcService")
@CoreBusinessInterceptorConfig({BusinessLoggingInterceptor.class, BusinessLockInterceptor.class/*,LcAutoFlowProcessingInterceptor.class*/})
public class OpenLcServiceImpl extends AbstractLcProductServiceImplBase<OpenLcInputBean, OpenLcOutputBean> implements OpenLcService {

    private Map<PayMethod, FreezeInterface<? super OpenLcInputBean, ? super OpenLcOutputBean>> openMappings;

    @SuppressWarnings("unchecked")
    @Autowired
    public void setOpenMappings(List<FreezeInterface<? extends OpenLcInputBean, ? super OpenLcOutputBean>> arrays) {

        this.openMappings = new HashMap<PayMethod, FreezeInterface<? super OpenLcInputBean, ? super OpenLcOutputBean>>();

        for (FreezeInterface<? extends OpenLcInputBean, ? super OpenLcOutputBean> st : arrays) {

            this.openMappings.put(st.getPayMethod(), (FreezeInterface<? super OpenLcInputBean, ? super OpenLcOutputBean>) st);
        }
    }

    @Autowired
    private PreLcDao preLcDao;

    @Autowired
    private LcOpenDao lcOpenDao;

    @Autowired
    private LcOpenIdWorker lcOpenIdWorker;

    @Autowired
    private LcMessageHandler lcMessageHandler;

    @Override
    public void validateInputParameters(OpenLcInputBean inputBean) throws CoreValidationRejectException {
    }

    @Override
    protected void validateLc(OpenLcInputBean inputBean, Lc lc) throws CoreValidationRejectException {

        if (lc != null && lc.getLcStatus() != null
                && LcStatusType.RETREAT.getStatusCode().compareTo(lc.getLcStatus()) == 0) {
            throw new CoreValidationRejectException(ReturnCode.CORE_STD_LC_INVALID, "银信证已失效");
        }

    }

    @Override
    protected void validateLcProductRule(OpenLcInputBean inputBean, LcProductCache lcProductCache, CoreBusinessContext context) throws CoreValidationRejectException {
        PreLc preLc = preLcDao.selectByLcId(inputBean.getLcId());
        if (preLc == null) {
            throw new CoreValidationRejectException(ReturnCode.CORE_STD_PRE_LC_NOT_EXISTS, "银信证预开证记录不存在");
        }
        context.setAttribute("PRE_LC", preLc);
    }

    @Override
    public BusinessOutput<OpenLcOutputBean> processBusiness(OpenLcInputBean inputBean, CoreBusinessContext context) throws CoreBusinessException {
        logger.info("进入开证业务处理...");

        FreezeInterface<? super OpenLcInputBean, ? super OpenLcOutputBean> openLcHandler = openMappings.get(inputBean.getPayMethod());

        if (openLcHandler == null) {
            throw new CoreBusinessException(ReturnCode.CORE_STD_UNSUPPORT_OPEN_TYPE, "不支持的支付方式");
        }

        Long lcId = inputBean.getLcId();

        // 查询预开证记录
        PreLc preLc = (PreLc) context.getAttribute("PRE_LC");

        // === 银信证[未付款]（没有开证记录或开证均失败），执行冻结资金操作 ===
        // 插入开证记录
        LcOpen lcOpen = insertLcOpen(preLc, inputBean);
        if (lcOpen == null) {
            logger.error("插入开证记录表不成功");
            throw new CoreBusinessException(ReturnCode.CORE_STD_LC_OPEN_ERROR, "开证失败");
        }

        logger.info("开证业务处理...调用银行接口");
        // 冻结账户金额
        FreezeInputBean freezeInputBean = new FreezeInputBean();
        freezeInputBean.setPreLc(preLc);
        freezeInputBean.setLcOpen(lcOpen);
        FreezeOutputBean freezeOutputBean = openLcHandler.freeze(inputBean, freezeInputBean);

        logger.info("开证业务处理...放入消息队列");
        // 把冻结账户金额任务放入消息队列
        LcFreezeParamBean lcFreezeParamBean = new LcFreezeParamBean();
        lcFreezeParamBean.setLcId(preLc.getLcId());
        lcFreezeParamBean.setLcOpenId(lcOpen.getLcOpenId());
        lcFreezeParamBean.setPayMethod(inputBean.getPayMethod());
        lcFreezeParamBean.setLcTranStatus(freezeOutputBean.getLcTranStatus());
        lcFreezeParamBean.setSerialNo(freezeOutputBean.getSerialNo());
        lcFreezeParamBean.setFreezeTime(new Date());
        lcFreezeParamBean.setData(freezeOutputBean.getData());
        lcFreezeParamBean.setFreezeDesc("等待银联回调结果");
        lcFreezeParamBean.setLcOpenResponse(freezeOutputBean.getReturnMsg() + "," + freezeOutputBean.getLcTranStatus());
        lcMessageHandler.sendMessage(MessageEnum.MsgType.LC, MessageEnum.Scene.OPENLC_NOTIFY, lcFreezeParamBean);

        // 返回开证处理结果，告诉商户支付已经成功受理
        if (freezeOutputBean.isSuccess()) {
            logger.info("开证业务处理...SUCCESS");

            OpenLcOutputBean output = new OpenLcOutputBean();
            output.setLcId(lcId);
            output.setLcStatus(LcStatusType.ONPAYING.getStatusCode());
            output.setLcStatusDesc(LcStatusType.getDesc(LcStatusType.ONPAYING.getStatusCode()));

            output.setOrderId(preLc.getOrderId());
            output.setLcAmount(preLc.getLcAmount());
            output.setReturnUrl(preLc.getLcStateReturnUrl());
            output.setPayMethod(openLcHandler.getPayMethod());
            output.setSerialNo(freezeOutputBean.getSerialNo());
            return BusinessOutput.success(output);
        }

        logger.info("开证业务处理...FAIL");
        return BusinessOutput.fail(freezeOutputBean.getReturnCode(), "开证失败： " + freezeOutputBean.getReturnMsg());
    }

    private LcOpen insertLcOpen(PreLc preLc, OpenLcInputBean inputBean) {
        Date now = new Date();

        LcOpen lcOpen = new LcOpen();
        lcOpen.setLcOpenId(lcOpenIdWorker.nextId());

        if (inputBean.getClass() == OpenLcUnionDepositInputBean.class) {
            OpenLcUnionDepositInputBean openLcUnionDepositInputBean = (OpenLcUnionDepositInputBean) inputBean;
            lcOpen.setLcOpenId(openLcUnionDepositInputBean.getLcOpenId());
        }
        if (lcOpen.getLcOpenId() == null) {
            throw new CoreBusinessException(ReturnCode.CORE_LC_PARAMETER_INVALID, "开证Id不可以为空");
        }

        lcOpen.setCreateTime(now);
        lcOpen.setUpdateTime(now);

        lcOpen.setLcId(preLc.getLcId());
        lcOpen.setMid(preLc.getMid());

        lcOpen.setLcCurrency(preLc.getLcCurrency());
        lcOpen.setLcAmount(preLc.getLcAmount());

        lcOpen.setPayerId(preLc.getPayerId());
        lcOpen.setPayerAccno(preLc.getPayerAccno());
        lcOpen.setPayerType(preLc.getPayerType());
        lcOpen.setPayerBankCode(preLc.getPayerBankCode());
        lcOpen.setPayerBankName(preLc.getPayerBankName());
        lcOpen.setPayerMobile(preLc.getPayerMobile());

        lcOpen.setRecvId(preLc.getRecvId());
        lcOpen.setRecvAccno(preLc.getRecvAccno());
        lcOpen.setRecvType(preLc.getRecvType());
        lcOpen.setRecvBankCode(preLc.getRecvBankCode());
        lcOpen.setRecvBankName(preLc.getRecvBankName());
        lcOpen.setRecvMobile(preLc.getRecvMobile());

        lcOpen.setValidTime(preLc.getRecvValidTime());

        lcOpen.setOrderId(preLc.getOrderId());
        lcOpen.setRemark(inputBean.getRemark());

        lcOpen.setTradeTime(now);
        lcOpen.setLcOpenStatus(LcTranStatus.INPROCESS.getTranStatusStr());
        lcOpen.setLcOpenChannel(inputBean.getPayMethod().getCode());

        boolean insertSuccess = lcOpenDao.insert(lcOpen);
        if (insertSuccess) {
            return lcOpen;
        }

        return null;
    }
}
