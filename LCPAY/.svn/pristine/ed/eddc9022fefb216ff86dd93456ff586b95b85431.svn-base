package com.cifpay.lc.core.message.server.listener.lc;

import com.cifpay.lc.api.message.MessageService;
import com.cifpay.lc.api.message.lc.AutoAppointmentLcMessageService;
import com.cifpay.lc.constant.enums.MessageEnum;
import com.cifpay.lc.core.message.server.listener.BaseMessageListener;
import com.cifpay.lc.domain.message.LcAppointmentParamBean;
import com.cifpay.lc.domain.message.MessageInputBean;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppointmentLcMessageServiceListener extends BaseMessageListener<LcAppointmentParamBean> {

    @Autowired
    private AutoAppointmentLcMessageService autoAppointmentLcMessageService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = MessageEnum.QUE_APPOINTMENTLC, durable = "true", autoDelete = "false"),
            exchange = @Exchange(value = MessageEnum.EXC_LC, durable = "true", autoDelete = "false"),
            key = MessageEnum.QUE_APPOINTMENTLC))
    public void onOpenLcMessage(MessageInputBean messageInputBean) {
        if (logger.isDebugEnabled()) {
            logger.debug("======== 处理履约消息开始 ==============");
            logger.debug("messageInputBean:{}",messageInputBean);
        }

        handleMessage(messageInputBean);

        if (logger.isDebugEnabled()) {
            logger.debug("======== 处理履约消息结束 ==============");
        }
    }


    @Override
    public MessageService<LcAppointmentParamBean> getMessageService() {
        return autoAppointmentLcMessageService;
    }
}
