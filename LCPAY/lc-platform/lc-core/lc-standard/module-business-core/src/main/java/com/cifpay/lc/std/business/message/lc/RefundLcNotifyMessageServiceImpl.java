package com.cifpay.lc.std.business.message.lc;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.cifpay.lc.constant.ReturnCode;
import com.cifpay.lc.exception.MessageBusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cifpay.lc.api.BusinessOutput;
import com.cifpay.lc.api.message.lc.RefundLcNotifyMessageService;
import com.cifpay.lc.constant.BizConstants;
import com.cifpay.lc.constant.enums.LcStatusType;
import com.cifpay.lc.constant.enums.LcTranStatus;
import com.cifpay.lc.constant.merchant.MerchantTranStatus;
import com.cifpay.lc.core.common.CoreBusinessContext;
import com.cifpay.lc.core.common.CoreBusinessInterceptorConfig;
import com.cifpay.lc.core.db.dao.LcDao;
import com.cifpay.lc.core.db.dao.LcInvalidDao;
import com.cifpay.lc.core.db.dao.LcLogDao;
import com.cifpay.lc.core.db.dao.LcRefundDao;
import com.cifpay.lc.core.db.pojo.Lc;
import com.cifpay.lc.core.db.pojo.LcLog;
import com.cifpay.lc.core.db.pojo.LcRefund;
import com.cifpay.lc.core.exception.CoreBusinessException;
import com.cifpay.lc.core.uid.LcLogIdWorker;
import com.cifpay.lc.domain.message.LcRefundParamBean;
import com.cifpay.lc.domain.message.MessageOutputBean;
import com.cifpay.lc.std.component.NotifyService;
import com.cifpay.lc.std.domain.component.notify.RefundAsyncNotifyBean;
import com.cifpay.lc.std.interceptor.BusinessLoggingInterceptor;
import com.cifpay.lc.std.interceptor.MessageLockInterceptor;

/**
 * 失效
 */
@Service("refundLcNotifyMessageService")
@CoreBusinessInterceptorConfig({BusinessLoggingInterceptor.class, MessageLockInterceptor.class})
public class RefundLcNotifyMessageServiceImpl extends LcMessageServiceBase<LcRefundParamBean> implements RefundLcNotifyMessageService {

    @Autowired
    private LcDao lcDao;

    @Autowired
    private LcLogDao lcLogDao;

    @Autowired
    private LcRefundDao lcRefundDao;

    @Autowired
    private LcLogIdWorker lcLogIdWorker;

    @Autowired
    private NotifyService notifyService;

    @Override
    protected BusinessOutput<MessageOutputBean> handleMessage(LcRefundParamBean messageParams, CoreBusinessContext context) throws CoreBusinessException {

        LcRefund lcRefund = lcRefundDao.selectByPrimaryKey(messageParams.getLcRefundId());
        if (lcRefund == null) {
            throw new MessageBusinessException(ReturnCode.CORE_STD_LC_REFUND_NOT_EXISTS, "退款记录不存在");
        }

        // 如果已经交易成功，不可更新状态，兼容两次成功回调的情况
        if (lcRefund.getRefundStatus() != null && LcTranStatus.SUCCESS.getTranStatusStr().compareTo(lcRefund.getRefundStatus().toString()) == 0) {
            logger.warn("退款记录已经成功处理过，忽略请求: {}", messageParams);
            // 消息处理成功
            return BusinessOutput.success(new MessageOutputBean(true));
        }

        LcRefund updateLcRefund = new LcRefund();
        updateLcRefund.setLcRefundId(messageParams.getLcRefundId());

        int status = Integer.parseInt(messageParams.getLcTranStatus().getTranStatusStr());
        updateLcRefund.setRefundStatus(status);
        updateLcRefund.setUpdateTime(new Date());

        lcRefundDao.updateByPrimaryKeySelective(updateLcRefund);

        if (LcTranStatus.SUCCESS.equals(messageParams.getLcTranStatus())) {
            logger.info("退款回调，(lcId={},LcTranStatus={})", messageParams.getLcId(), messageParams.getLcTranStatus());

//            LcStatusType lcStatus = LcStatusType.REFUND;

//            Lc updateLc = new Lc();
//            updateLc.setLcId(messageParams.getLcId());
//            updateLc.setLcStatus(lcStatus.getStatusCode());
//            updateLc.setUpdateTime(new Date());
//            lcDao.updateByPrimaryKeySelective(updateLc);

            // 记录日志
            LcLog lcLog = new LcLog();
            lcLog.setLogId(lcLogIdWorker.nextId());
            lcLog.setLcId(messageParams.getLcId());
            lcLog.setStepLogId(messageParams.getLcRefundId());
//            lcLog.setTradeCode(BizConstants.LcTranCode.REFUND.getTranCodeStr());
//            lcLog.setFromStatus();
            lcLog.setToStatus(LcStatusType.REFUND.getStatusCode());
            lcLog.setRemark("执行解付转账");
            lcLogDao.insert(lcLog);

            Lc lc = lcDao.selectByPrimaryKey(messageParams.getLcId());

            // 通知商户
            BusinessOutput notifyOutput = notify(lc, messageParams);
        }

        // 失败时，亦通知
        if (LcTranStatus.FAIL.compareTo(messageParams.getLcTranStatus()) == 0) {
            logger.info("退款回调，(lcId={},LcTranStatus={})", messageParams.getLcId(), messageParams.getLcTranStatus());

            Lc lc = lcDao.selectByPrimaryKey(messageParams.getLcId());

            BusinessOutput notifyOutput = notify(lc, messageParams);
        }

        // 默认总是认为消息处理成功
        return BusinessOutput.success(new MessageOutputBean(true));
    }

    private BusinessOutput notify(Lc lc, LcRefundParamBean messageParams) {
        // 通知商户
        RefundAsyncNotifyBean notifyBean = new RefundAsyncNotifyBean();

        notifyBean.setLcId(String.valueOf(messageParams.getLcId()));
        notifyBean.setLcStatus(LcStatusType.REFUND.getStatusCode());

        notifyBean.setOrderId(lc.getOrderId());
        notifyBean.setLcAmount(BizConstants.decimalFormat.format(lc.getLcAmount()));
        notifyBean.setRefundAmount(BizConstants.decimalFormat.format(messageParams.getRefundAmount()));
        notifyBean.setRefundTime(new SimpleDateFormat(BizConstants.DateFormat_std).format(messageParams.getRefundTime()));

        notifyBean.setTranStatus(MerchantTranStatus.parse(messageParams.getLcTranStatus()).getMerchantTranStatus());
        notifyBean.setSerialNo(messageParams.getSerialNo());
        notifyBean.setMessage(messageParams.getRefundDesc());

        BusinessOutput notifyOutput = notifyService.notify(lc.getMid(), lc.getLcStateNotifyUrl(), notifyBean);

        return notifyOutput;
    }
}
