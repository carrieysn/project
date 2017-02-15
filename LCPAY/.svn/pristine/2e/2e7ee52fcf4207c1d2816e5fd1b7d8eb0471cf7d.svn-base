package com.cifpay.lc.std.business.message.lc;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.cifpay.lc.constant.ReturnCode;
import com.cifpay.lc.exception.MessageBusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cifpay.lc.api.BusinessOutput;
import com.cifpay.lc.api.message.lc.InvalidateLcNotifyMessageService;
import com.cifpay.lc.constant.BizConstants;
import com.cifpay.lc.constant.enums.LcStatusType;
import com.cifpay.lc.constant.enums.LcTranStatus;
import com.cifpay.lc.constant.merchant.MerchantTranStatus;
import com.cifpay.lc.core.common.CoreBusinessContext;
import com.cifpay.lc.core.common.CoreBusinessInterceptorConfig;
import com.cifpay.lc.core.db.dao.LcDao;
import com.cifpay.lc.core.db.dao.LcInvalidDao;
import com.cifpay.lc.core.db.dao.LcLogDao;
import com.cifpay.lc.core.db.pojo.Lc;
import com.cifpay.lc.core.db.pojo.LcInvalid;
import com.cifpay.lc.core.db.pojo.LcLog;
import com.cifpay.lc.core.exception.CoreBusinessException;
import com.cifpay.lc.core.uid.LcLogIdWorker;
import com.cifpay.lc.domain.message.LcExpiryParamBean;
import com.cifpay.lc.domain.message.MessageOutputBean;
import com.cifpay.lc.std.component.NotifyService;
import com.cifpay.lc.std.domain.component.notify.InvalidateAsyncNotifyBean;
import com.cifpay.lc.std.interceptor.BusinessLoggingInterceptor;
import com.cifpay.lc.std.interceptor.MessageLockInterceptor;

/**
 * 失效
 */
@Service("invalidateLcNotifyMessageService")
@CoreBusinessInterceptorConfig({BusinessLoggingInterceptor.class, MessageLockInterceptor.class})
public class InvalidateLcNotifyMessageServiceImpl extends LcMessageServiceBase<LcExpiryParamBean> implements InvalidateLcNotifyMessageService {

    @Autowired
    private LcDao lcDao;

    @Autowired
    private LcInvalidDao lcInvalidDao;

    @Autowired
    private LcLogDao lcLogDao;

    @Autowired
    private LcLogIdWorker lcLogIdWorker;

    @Autowired
    private NotifyService notifyService;

    @Override
    protected BusinessOutput<MessageOutputBean> handleMessage(LcExpiryParamBean messageParams, CoreBusinessContext context) throws CoreBusinessException {

        Long lcId = messageParams.getLcId();
        Long lcInvalidId = messageParams.getLcInvalidId();
        Date now = new Date();

        LcInvalid lcInvalid = lcInvalidDao.selectByPrimaryKey(lcInvalidId);

        if (lcInvalid == null) {
            throw new MessageBusinessException(ReturnCode.CORE_STD_LC_INVALID_NOT_EXISTS, "失效记录不存在");
        }

        // 如果已经交易成功，不可更新状态，兼容两次成功回调的情况
        if (lcInvalid.getLcInvalidStatus() != null && LcTranStatus.SUCCESS.getTranStatusStr().compareTo(lcInvalid.getLcInvalidStatus()) == 0) {
            logger.warn("失效记录已经成功处理过，忽略请求: {}", messageParams);
            // 消息处理成功
            return BusinessOutput.success(new MessageOutputBean(true));
        }

        // 7.更新失效记录状态
        updateLcInvalid(messageParams);

        // 成功返回
        if (LcTranStatus.SUCCESS.compareTo(messageParams.getLcTranStatus()) == 0) {
            logger.info("失效回调，(lcId={},LcTranStatus={})", messageParams.getLcId(), messageParams.getLcTranStatus());

            // 8.更新银信证余额、状态
            int updateRow = lcDao.decreaseFreezingAmount(lcId, messageParams.getInvalidAmount());
            if (updateRow <= 0) {
                logger.error("更新银信证余额失败：{}", lcId);
            }
            // 重新查询银信证状态
            Lc lc = lcDao.selectByPrimaryKey(lcId);

            Lc updateLc = new Lc();
            updateLc.setLcId(lcId);
            updateLc.setUpdateTime(now);
            if (lc.getLcBalance().compareTo(BigDecimal.ZERO) <= 0 && lc.getLcFreezingAmount().compareTo(BigDecimal.ZERO) <= 0) {
                updateLc.setLcStatus(LcStatusType.RETREAT.getStatusCode());
            }
            lcDao.updateByPrimaryKeySelective(updateLc);

            // 9.记录日志
            LcLog lcLog = new LcLog();
            lcLog.setLogId(lcLogIdWorker.nextId());
            lcLog.setCreateTime(now);
            lcLog.setUpdateTime(now);
            lcLog.setLcId(lcId);
            lcLog.setStepLogId(lcInvalidId);
            lcLog.setTradeCode(BizConstants.LcTranCode.INVALIDATE.getTranCodeStr());
            lcLog.setFromStatus(lc.getLcStatus());
            lcLog.setToStatus(LcStatusType.RETREAT.getStatusCode());
            lcLog.setRemark("成功失效");
            lcLogDao.insert(lcLog);

            BusinessOutput notifyOutput = notify(lc, messageParams);
        }

        // 失败时，亦通知
        if (LcTranStatus.FAIL.compareTo(messageParams.getLcTranStatus()) == 0) {
            logger.info("失效回调，(lcId={},LcTranStatus={})", messageParams.getLcId(), messageParams.getLcTranStatus());

            Lc lc = lcDao.selectByPrimaryKey(lcId);

            BusinessOutput notifyOutput = notify(lc, messageParams);
        }

        // 默认总是认为消息处理成功
        return BusinessOutput.success(new MessageOutputBean(true));
    }

    private BusinessOutput notify(Lc lc, LcExpiryParamBean messageParams) {

        // 通知商户
        InvalidateAsyncNotifyBean notifyBean = new InvalidateAsyncNotifyBean();

        notifyBean.setLcId(String.valueOf(messageParams.getLcId()));
        notifyBean.setLcStatus(LcStatusType.REFUND.getStatusCode());

        notifyBean.setOrderId(lc.getOrderId());
        notifyBean.setLcAmount(BizConstants.decimalFormat.format(lc.getLcAmount()));
        notifyBean.setInvalidAmount(BizConstants.decimalFormat.format(messageParams.getInvalidAmount()));
        notifyBean.setInvalidTime(new SimpleDateFormat(BizConstants.DateFormat_std).format(messageParams.getExpiryTime()));

        notifyBean.setTranStatus(MerchantTranStatus.parse(messageParams.getLcTranStatus()).getMerchantTranStatus());
        notifyBean.setSerialNo(messageParams.getSerialNo());
        notifyBean.setMessage(messageParams.getExpiryDesc());

        BusinessOutput notifyOutput = notifyService.notify(lc.getMid(), lc.getLcStateNotifyUrl(), notifyBean);

        return notifyOutput;
    }

    private void updateLcInvalid(LcExpiryParamBean messageParams) {
        Date now = new Date();

        LcInvalid updateLcInvalid = new LcInvalid();
        updateLcInvalid.setLcInvalidId(messageParams.getLcInvalidId());
        updateLcInvalid.setUpdateTime(now);
        updateLcInvalid.setLcInvalidStatus(messageParams.getLcTranStatus().getTranStatusStr());
        updateLcInvalid.setLcInvalidResponse(messageParams.getLcExpiryResponse());

        lcInvalidDao.updateByPrimaryKeySelective(updateLcInvalid);

    }
}
