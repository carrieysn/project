package com.cifpay.lc.core.message.server.listener.lc;

import com.cifpay.lc.api.message.MessageService;
import com.cifpay.lc.api.message.lc.RefundLcNotifyMessageService;
import com.cifpay.lc.constant.enums.MessageEnum;
import com.cifpay.lc.core.message.server.listener.BaseMessageListener;
import com.cifpay.lc.domain.message.LcRefundParamBean;
import com.cifpay.lc.domain.message.MessageInputBean;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 失效后，异步处理返回结果
 *
 * @author Administrator
 */
@Component
public class RefundLcNotifyMessageListener extends BaseMessageListener<LcRefundParamBean> {

    @Autowired
    private RefundLcNotifyMessageService refundLcNotifyMessageService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = MessageEnum.QUE_REFUND_NOTIFY, durable = "true", autoDelete = "false"),
            exchange = @Exchange(value = MessageEnum.EXC_LC, durable = "true", autoDelete = "false"),
            key = MessageEnum.QUE_REFUND_NOTIFY))
    public void onOpenLcMessage(MessageInputBean messageInputBean) {
        if (logger.isDebugEnabled()) {
            logger.debug("======== 处理失效异步通知消息开始 ==============");
            logger.debug("messageInputBean:{}",messageInputBean);
        }

        handleMessage(messageInputBean);

        if (logger.isDebugEnabled()) {
            logger.debug("======== 处理失效异步通知消息结束 ==============");
        }
    }

    @Override
    public MessageService<LcRefundParamBean> getMessageService() {
        return refundLcNotifyMessageService;
    }
}
