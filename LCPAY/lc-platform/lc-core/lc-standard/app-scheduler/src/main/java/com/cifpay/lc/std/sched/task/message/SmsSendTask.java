package com.cifpay.lc.std.sched.task.message;

import com.cifpay.lc.util.logging.LoggerEnum;
import com.cifpay.lc.constant.enums.MessageEnum;
import com.cifpay.lc.core.interceptor.annotation.EnableGenerateRequestId;
import com.cifpay.lc.core.message.biz.MessagePublisher;
import com.cifpay.lc.core.message.biz.impl.SmsMessagePublisher;
import com.cifpay.lc.std.sched.task.BaseMessageTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 短信发送
 *
 * @author Administrator
 */
@Component
@EnableGenerateRequestId(scene = LoggerEnum.Scene.SENDSMS)
public class SmsSendTask extends BaseMessageTask {

    @Autowired
    SmsMessagePublisher smsMessagePublisher;

    @Scheduled(cron = "0 */5 * * * *")
    public void handleAutoApplyLc() {
        String threadName = Thread.currentThread().getName();
        if (logger.isInfoEnabled()) {
            logger.info("============= 开始处理SMS相关消息 ，threadName:{}==================", threadName);
        }

        handleMessage(MessageEnum.MsgType.SMS, MessageEnum.Scene.SENDSMS);

        if (logger.isInfoEnabled()) {
            logger.info("============= 处理SMS相关消息结束 ，threadName:{}==================", threadName);
        }
    }


    @Override
    public MessagePublisher getMessagePublisher() {
        return smsMessagePublisher;
    }
}
