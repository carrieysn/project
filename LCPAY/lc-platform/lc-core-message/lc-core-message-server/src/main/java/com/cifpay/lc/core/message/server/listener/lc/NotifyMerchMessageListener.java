package com.cifpay.lc.core.message.server.listener.lc;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cifpay.lc.api.message.MessageService;
import com.cifpay.lc.api.message.lc.NotifyMerchMessageService;
import com.cifpay.lc.constant.enums.MessageEnum;
import com.cifpay.lc.core.message.server.listener.BaseMessageListener;
import com.cifpay.lc.domain.message.LcNotifyParamBean;
import com.cifpay.lc.domain.message.MessageInputBean;

/**
 * 开证成功异步通知商户
 * @author Administrator
 *
 */
@Component
public class NotifyMerchMessageListener extends BaseMessageListener<LcNotifyParamBean>{

	@Autowired
	private NotifyMerchMessageService notifyMerchMessageService;
	
	@RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = MessageEnum.QUE_NOTIFY_MERCH, durable = "true", autoDelete = "false"),
            exchange = @Exchange(value = MessageEnum.EXC_LC, durable = "true", autoDelete = "false"),
            key = MessageEnum.QUE_NOTIFY_MERCH))
    public void onOpenLcMessage(MessageInputBean messageInputBean) {
        if (logger.isDebugEnabled()) {
            logger.debug("======== 处理异步通知商户消息开始 ==============");
            logger.debug("messageInputBean:{}",messageInputBean);
        }

        handleMessage(messageInputBean);

        if (logger.isDebugEnabled()) {
            logger.debug("======== 处理异步通知商户消息结束 ==============");
        }
    }
	
	@Override
	public MessageService<LcNotifyParamBean> getMessageService() {
		return notifyMerchMessageService;
	}

}
