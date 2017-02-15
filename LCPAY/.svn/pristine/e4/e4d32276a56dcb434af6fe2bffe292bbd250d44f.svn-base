package com.cifpay.lc.std.business.lc;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cifpay.lc.api.BusinessOutput;
import com.cifpay.lc.api.gateway.lc.TransferService;
import com.cifpay.lc.constant.BizConstants.LcTranCode;
import com.cifpay.lc.constant.ReturnCode;
import com.cifpay.lc.constant.enums.LcStatusType;
import com.cifpay.lc.constant.enums.LcTranStatus;
import com.cifpay.lc.constant.enums.MessageEnum;
import com.cifpay.lc.constant.enums.PayMethod;
import com.cifpay.lc.constant.enums.ProcessStatus;
import com.cifpay.lc.core.common.CoreBusinessContext;
import com.cifpay.lc.core.common.CoreBusinessInterceptorConfig;
import com.cifpay.lc.core.common.CoreBusinessTranCode;
import com.cifpay.lc.core.db.dao.LcConfirmPayDao;
import com.cifpay.lc.core.db.dao.LcDao;
import com.cifpay.lc.core.db.dao.LcOpenDao;
import com.cifpay.lc.core.db.dao.LcPayDao;
import com.cifpay.lc.core.db.pojo.Lc;
import com.cifpay.lc.core.db.pojo.LcConfirmPay;
import com.cifpay.lc.core.db.pojo.LcOpen;
import com.cifpay.lc.core.db.pojo.LcPay;
import com.cifpay.lc.core.exception.CoreBusinessException;
import com.cifpay.lc.core.exception.CoreValidationRejectException;
import com.cifpay.lc.core.message.biz.MessageHandler;
import com.cifpay.lc.core.uid.LcPayIdWorker;
import com.cifpay.lc.domain.lc.TransferInputBean;
import com.cifpay.lc.domain.lc.TransferOutputBean;
import com.cifpay.lc.domain.message.LcUnFreezeParamBean;
import com.cifpay.lc.std.domain.paychannel.UnfreezeInputBean;
import com.cifpay.lc.std.domain.paychannel.UnfreezeOutputBean;
import com.cifpay.lc.std.interceptor.BusinessLockInterceptor;
import com.cifpay.lc.std.interceptor.BusinessLoggingInterceptor;
import com.cifpay.lc.std.interceptor.LcAutoFlowProcessingInterceptor;
import com.cifpay.lc.std.paychannel.TransferInterface;

/**
 * 转账（执行解付）
 *
 * @author sweet
 */
@Service("transferService")
@CoreBusinessInterceptorConfig({BusinessLoggingInterceptor.class, BusinessLockInterceptor.class, LcAutoFlowProcessingInterceptor.class})
@CoreBusinessTranCode(LcTranCode.TRANSFER)
public class TransferServiceImpl extends AbstractLcProductServiceImplBase<TransferInputBean, TransferOutputBean> implements TransferService {

    private Map<PayMethod, TransferInterface<? super TransferInputBean, ? super TransferOutputBean>> transferMappings;

    @SuppressWarnings("unchecked")
    @Autowired
    public void setTransferMappings(List<TransferInterface<? extends TransferInputBean, ? super TransferOutputBean>> arrays) {

        this.transferMappings = new HashMap<PayMethod, TransferInterface<? super TransferInputBean, ? super TransferOutputBean>>();

        for (TransferInterface<? extends TransferInputBean, ? super TransferOutputBean> st : arrays) {

            this.transferMappings.put(st.getPayMethod(), (TransferInterface<? super TransferInputBean, ? super TransferOutputBean>) st);
        }
    }

    @Autowired
    private LcDao lcDao;
    @Autowired
    private LcOpenDao lcOpenDao;
    @Autowired
    private LcConfirmPayDao lcConfirmPayDao;
    @Autowired
    private LcPayDao lcPayDao;

    @Autowired
    private LcPayIdWorker lcPayIdWorker;

    @Autowired
    MessageHandler lcMessageHandler;

    @Override
    protected void validateInputParameters(TransferInputBean inputBean) throws CoreValidationRejectException {
        // TODO Auto-generated method stub

    }

    @Override
    protected void validateLc(TransferInputBean inputBean, Lc lc) throws CoreValidationRejectException {
        if (lc == null) {
            throw new CoreValidationRejectException(ReturnCode.CORE_STD_LC_NOT_EXISTS, "银信证记录不存在");
        }

    }

    @Override
    public BusinessOutput<TransferOutputBean> processBusiness(TransferInputBean inputBean, CoreBusinessContext context) {
        logger.info("进入执行解付业务处理..." + inputBean.getLcId());

        LcConfirmPay lcConfirmPay = lcConfirmPayDao.selectByPrimaryKey(inputBean.getApplyId());
        if (lcConfirmPay == null) {
            throw new CoreValidationRejectException(ReturnCode.CORE_STD_LC_APPLY_NOT_EXISTS, "申请解付记录不存在");
        }

        // 申请解付记录状态不为“处理成功”，抛出异常
        if (LcTranStatus.SUCCESS.getTranStatusStr().compareTo(lcConfirmPay.getConfirmStatus()) != 0) {
            throw new CoreValidationRejectException(ReturnCode.CORE_STD_LC_APPLY_IS_INPROCESS_EXISTS, "申请解付状态不为成功");
        }
        // 申请解付记录不为“待处理”，抛出异常
        if (ProcessStatus.INPROCESS.getCode() != lcConfirmPay.getProcessStatus().intValue()) {
            throw new CoreValidationRejectException(ReturnCode.CORE_STD_LC_APPLY_NOT_EXISTS, "申请解付已被处理");
        }

        Date now = new Date();
        Lc lc = (Lc) context.getAttribute("LC");
        BigDecimal amount = lcConfirmPay.getLcPayAmount();

        // 查找对应的开证记录
        LcOpen lcOpen = lcOpenDao.selectByLcIdSuccess(inputBean.getLcId());
        if (lcOpen == null) {
            throw new CoreValidationRejectException(ReturnCode.CORE_STD_LC_OPEN_NOT_EXISTS, "未找到开证记录");
        }
        PayMethod payChannel = PayMethod.parse(lcOpen.getLcOpenChannel());
        // 查找解付处理器
        TransferInterface<? super TransferInputBean, ? super TransferOutputBean> transferHandler = transferMappings.get(payChannel);
        if (transferHandler == null) {
            throw new CoreBusinessException(ReturnCode.CORE_STD_UNSUPPORT_TRANSFER_TYPE, "不支持的解付方式");
        }

        // 更新申请解付记录
        lcConfirmPayDao.updateProcessStatus(ProcessStatus.TRANSFER.getCode(), now, lcConfirmPay.getLcConfirmId());

        // 插入解付表
        LcPay lcPay = new LcPay();
        lcPay.setLcPayId(lcPayIdWorker.nextId());
        lcPay.setLcConfirmId(inputBean.getApplyId());
        lcPay.setLcId(inputBean.getLcId());
        lcPay.setMid(inputBean.getMerId());
        lcPay.setOrderId(lcConfirmPay.getOrderId());
        lcPay.setTotalAmount(amount);
        lcPay.setTradeTime(now);
        lcPay.setRemark(inputBean.getRemark());
        lcPay.setLcPayStatus(LcTranStatus.INPROCESS.getTranStatusStr());
        lcPayDao.insert(lcPay);

        logger.info("执行解付业务处理...调用银行接口");
        // 调用银行接口，划转资金
        UnfreezeInputBean unfreezeInputBean = new UnfreezeInputBean();
        unfreezeInputBean.setLc(lc);
        unfreezeInputBean.setLcOpen(lcOpen);
        unfreezeInputBean.setLcPay(lcPay);
        UnfreezeOutputBean unfreezeOutputBean = transferHandler.unfreeze(inputBean, unfreezeInputBean);

        logger.info("执行解付业务处理...放入消息队列");
        // 把解冻信息放入消息队列
        LcUnFreezeParamBean lcUnFreezeParamBean = new LcUnFreezeParamBean();
        lcUnFreezeParamBean.setPayMethod(payChannel);
        lcUnFreezeParamBean.setLcId(lc.getLcId());

        lcUnFreezeParamBean.setLcPayId(lcPay.getLcPayId());
        lcUnFreezeParamBean.setLcTranStatus(unfreezeOutputBean.getLcTranStatus());
        lcUnFreezeParamBean.setSerialNo(unfreezeOutputBean.getSerialNo());
        lcUnFreezeParamBean.setTransferTime(new Date());
        lcUnFreezeParamBean.setLcTransferResponse(unfreezeOutputBean.getMessage() + "," + unfreezeOutputBean.getLcTranStatus());
        lcUnFreezeParamBean.setData(unfreezeOutputBean.getData());
        lcMessageHandler.sendMessage(MessageEnum.MsgType.LC, MessageEnum.Scene.TRANSFERLC_NOTIFY, lcUnFreezeParamBean);

        if (unfreezeOutputBean.isSuccess()) {
            logger.info("执行解付业务处理...SUCCESS");

            Lc updateLc = new Lc();
            updateLc.setLcId(lc.getLcId());
            updateLc.setLcStatus(LcStatusType.TRANSFERED.getStatusCode());
            updateLc.setUpdateTime(now);
            lcDao.updateByPrimaryKeySelective(updateLc);

            //返回
            TransferOutputBean transferOutputBean = new TransferOutputBean();
            transferOutputBean.setLcId(lc.getLcId());
            transferOutputBean.setLcStatus(lc.getLcStatus());
            transferOutputBean.setLcStatusDesc(LcStatusType.getDesc(lc.getLcStatus()));

            transferOutputBean.setOrderId(lc.getOrderId());
            transferOutputBean.setTransferAmount(lcPay.getTotalAmount());
            transferOutputBean.setSerialNo(unfreezeOutputBean.getSerialNo());
            transferOutputBean.setPayMethod(payChannel);
            transferOutputBean.setLcPayId(lcPay.getLcPayId());
            return BusinessOutput.success(transferOutputBean);
        }

        logger.info("执行解付业务处理...FAIL");
        return BusinessOutput.fail(ReturnCode.CORE_STD_LC_TRANSFER_ERROR, "执行解付失败： " + unfreezeOutputBean.getMessage());
    }

}
