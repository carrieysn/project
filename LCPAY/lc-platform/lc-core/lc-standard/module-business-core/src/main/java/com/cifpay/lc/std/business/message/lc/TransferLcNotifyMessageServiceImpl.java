package com.cifpay.lc.std.business.message.lc;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.cifpay.lc.constant.enums.PayMethod;
import com.cifpay.lc.domain.message.union.CreditParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cifpay.lc.api.BusinessOutput;
import com.cifpay.lc.api.message.lc.TransferLcNotifyMessageService;
import com.cifpay.lc.constant.BizConstants;
import com.cifpay.lc.constant.ReturnCode;
import com.cifpay.lc.constant.enums.LcPayType;
import com.cifpay.lc.constant.enums.LcStatusType;
import com.cifpay.lc.constant.enums.LcTranStatus;
import com.cifpay.lc.constant.merchant.MerchantTranStatus;
import com.cifpay.lc.core.common.CoreBusinessContext;
import com.cifpay.lc.core.common.CoreBusinessInterceptorConfig;
import com.cifpay.lc.core.db.dao.LcDao;
import com.cifpay.lc.core.db.dao.LcLogDao;
import com.cifpay.lc.core.db.dao.LcPayDao;
import com.cifpay.lc.core.db.pojo.Lc;
import com.cifpay.lc.core.db.pojo.LcLog;
import com.cifpay.lc.core.db.pojo.LcPay;
import com.cifpay.lc.core.exception.CoreBusinessException;
import com.cifpay.lc.core.uid.LcLogIdWorker;
import com.cifpay.lc.domain.message.LcUnFreezeParamBean;
import com.cifpay.lc.domain.message.MessageOutputBean;
import com.cifpay.lc.exception.MessageBusinessException;
import com.cifpay.lc.std.component.NotifyService;
import com.cifpay.lc.std.domain.component.notify.TransferAsyncNotifyBean;
import com.cifpay.lc.std.interceptor.BusinessLoggingInterceptor;
import com.cifpay.lc.std.interceptor.MessageLockInterceptor;

@Service("transferLcNotifyMessageService")
@CoreBusinessInterceptorConfig({BusinessLoggingInterceptor.class, MessageLockInterceptor.class})
public class TransferLcNotifyMessageServiceImpl extends LcMessageServiceBase<LcUnFreezeParamBean> implements TransferLcNotifyMessageService {

    @Autowired
    private LcDao lcDao;
    @Autowired
    private LcPayDao lcPayDao;
    @Autowired
    private LcLogDao lcLogDao;

    @Autowired
    private LcLogIdWorker lcLogIdWorker;

    @Autowired
    private NotifyService notifyService;

    @Override
    protected BusinessOutput<MessageOutputBean> handleMessage(LcUnFreezeParamBean messageParams, CoreBusinessContext context) throws CoreBusinessException {
        Long lcId = messageParams.getLcId();
        Long lcPayId = messageParams.getLcPayId();

        Lc lc = lcDao.selectByPrimaryKey(lcId);
        if (lc == null) {
            throw new MessageBusinessException(ReturnCode.CORE_STD_PRE_LC_NOT_EXISTS, "预开证记录不存在");
        }
        LcPay lcPay = lcPayDao.selectByPrimaryKey(lcPayId);
        if (lcPay == null) {
            throw new MessageBusinessException(ReturnCode.CORE_STD_LC_TRANSFER_NOT_EXISTS, "执行解付记录不存在");
        }

        // 如果已经交易成功，不可更新状态，兼容两次成功回调的情况
        if (lcPay.getLcPayStatus() != null && LcTranStatus.SUCCESS.getTranStatusStr().compareTo(lcPay.getLcPayStatus()) == 0) {
            logger.warn("执行解付记录已经成功处理过，忽略请求: {}", messageParams);
            // 消息处理成功
            return BusinessOutput.success(new MessageOutputBean(true));
        }

        // 修改执行解付记录状态
        updateLcPay(lcPayId, messageParams);

        // 成功返回
        if (LcTranStatus.SUCCESS.compareTo(messageParams.getLcTranStatus()) == 0) {
            logger.info("执行解付回调，(lcId={},LcTranStatus={})", messageParams.getLcId(), messageParams.getLcTranStatus());

            // 更新银信证余额、状态
            int updateRow = lcDao.decreaseFreezingAmount(lc.getLcId(), lcPay.getTotalAmount());
            if (updateRow <= 0) {
                logger.error("更新银信证余额失败：{}", lc.getLcId());
            }

            Lc updateLc = new Lc();
            updateLc.setLcId(lc.getLcId());
            updateLc.setUpdateTime(new Date());
            if (lc.getPayType().compareTo(LcPayType.SINGLE_PAY.getCode()) == 0) {
                updateLc.setLcStatus(LcStatusType.SUCCESS.getStatusCode());
            }
            boolean updateLcSuccess = lcDao.updateByPrimaryKeySelective(updateLc);

            // 记录日志
            LcLog lcLog = new LcLog();
            lcLog.setLogId(lcLogIdWorker.nextId());
            lcLog.setLcId(lcId);
            lcLog.setStepLogId(lcPayId);
            lcLog.setTradeCode(BizConstants.LcTranCode.TRANSFER.getTranCodeStr());
            lcLog.setFromStatus(lc.getLcStatus());
            lcLog.setToStatus(LcStatusType.TRANSFERED.getStatusCode());
            lcLog.setRemark("执行解付转账");
            lcLogDao.insert(lcLog);

            if (updateLcSuccess) {
                BusinessOutput notifyOutput = notify(lc, messageParams, updateLc.getLcStatus(), lcPay);
            }
        }

        // 失败时，亦通知
        if (LcTranStatus.FAIL.compareTo(messageParams.getLcTranStatus()) == 0) {
            logger.info("执行解付回调，(lcId={},LcTranStatus={})", messageParams.getLcId(), messageParams.getLcTranStatus());
            BusinessOutput notifyOutput = notify(lc, messageParams, "", lcPay);
        }

        // 默认总是认为消息处理成功
        return BusinessOutput.success(new MessageOutputBean(true));
    }

    private BusinessOutput notify(Lc lc, LcUnFreezeParamBean messageParams, String currentLcStatus, LcPay lcPay) {
        // 通知商户
        TransferAsyncNotifyBean notifyBean = new TransferAsyncNotifyBean();

        notifyBean.setLcId(lc.getLcId().toString());
        notifyBean.setLcStatus(currentLcStatus);

        notifyBean.setOrderId(lc.getOrderId());
        notifyBean.setLcAmount(BizConstants.decimalFormat.format(lc.getLcAmount()));
        notifyBean.setTransferAmount(BizConstants.decimalFormat.format(lcPay.getTotalAmount()));
        notifyBean.setTransferTime(new SimpleDateFormat(BizConstants.DateFormat_std).format(messageParams.getTransferTime()));

        notifyBean.setTranStatus(MerchantTranStatus.parse(messageParams.getLcTranStatus()).getMerchantTranStatus());
        notifyBean.setSerialNo(messageParams.getSerialNo());
        notifyBean.setMessage(messageParams.getTransferDesc());

        BusinessOutput notifyOutput = notifyService.notify(lc.getMid(), lc.getLcStateNotifyUrl(), notifyBean);

        return notifyOutput;
    }

    private void updateLcPay(Long lcPayId, LcUnFreezeParamBean<JSONObject> lcUnFreezeParamBean) {
        Date now = new Date();

        LcPay updateLcPay = new LcPay();
        updateLcPay.setLcPayId(lcPayId);
        updateLcPay.setUpdateTime(now);
        updateLcPay.setUnionSerialNo(lcUnFreezeParamBean.getSerialNo());
        updateLcPay.setTradeTime(lcUnFreezeParamBean.getTransferTime());

        updateLcPay.setLcPayResponse(lcUnFreezeParamBean.getLcTransferResponse());
        updateLcPay.setTradeTime(lcUnFreezeParamBean.getTransferTime());
        updateLcPay.setLcPayStatus(lcUnFreezeParamBean.getLcTranStatus().getTranStatusStr());

        PayMethod payMethod = lcUnFreezeParamBean.getPayMethod();

        if (PayMethod.UNION_CREDIT.compareTo(payMethod) == 0
                || PayMethod.UNION_DEPOSIT.compareTo(payMethod) == 0) {

            CreditParam creditParam = JSONObject.toJavaObject(lcUnFreezeParamBean.getData(), CreditParam.class);
            if (creditParam != null) {
                updateLcPay.setUnionTxntime(creditParam.getTxnTime());
            }
        }

        updateLcPay.setLcPayResponse(lcUnFreezeParamBean.getLcTransferResponse());

        lcPayDao.updateByPrimaryKeySelective(updateLcPay);

    }
}
