package com.cifpay.lc.core.message.server.listener.sms;

import com.cifpay.lc.api.message.MessageService;
import com.cifpay.lc.api.message.sms.SmsSendService;
import com.cifpay.lc.constant.enums.MessageEnum;
import com.cifpay.lc.core.message.server.listener.BaseMessageListener;
import com.cifpay.lc.domain.message.MessageInputBean;
import com.cifpay.lc.domain.message.SmsParamBean;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SmsMessageListener extends BaseMessageListener<SmsParamBean> {

    @Autowired
    private SmsSendService smsSendService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = MessageEnum.QUE_SENDSMS, durable = "true", autoDelete = "false"),
            exchange = @Exchange(value = MessageEnum.EXC_SMS, durable = "true", autoDelete = "false"),
            key = MessageEnum.QUE_SENDSMS))
    public void onOpenLcMessage(MessageInputBean messageInputBean) {
        logger.info("messageInputBean:{}", messageInputBean);
        if (logger.isDebugEnabled()) {
            logger.debug("======== 处理短信消息开始 ==============");
            logger.debug("messageInputBean:{}", messageInputBean);
        }

        handleMessage(messageInputBean);

        if (logger.isDebugEnabled()) {
            logger.debug("======== 处理短信消息结束 ==============");
        }
    }


    @Override
    public MessageService<SmsParamBean> getMessageService() {
        return smsSendService;
    }
}
