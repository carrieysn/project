package com.cifpay.lc.core.message.server.listener.lc;

import com.cifpay.lc.api.message.MessageService;
import com.cifpay.lc.api.message.lc.OpenLcNotifyMessageService;
import com.cifpay.lc.constant.enums.MessageEnum;
import com.cifpay.lc.core.message.server.listener.BaseMessageListener;
import com.cifpay.lc.domain.message.LcFreezeParamBean;
import com.cifpay.lc.domain.message.MessageInputBean;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 开证后，通知商家资金冻结情况
 *
 * @author Administrator
 */
@Component
public class OpenLcNotifyMessageListener extends BaseMessageListener<LcFreezeParamBean> {

    @Autowired
    private OpenLcNotifyMessageService openLcNotifyMessageService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = MessageEnum.QUE_OPENLC_NOTIFY, durable = "true", autoDelete = "false"),
            exchange = @Exchange(value = MessageEnum.EXC_LC, durable = "true", autoDelete = "false"),
            key = MessageEnum.QUE_OPENLC_NOTIFY))
    public void onOpenLcMessage(MessageInputBean messageInputBean) {
        if (logger.isDebugEnabled()) {
            logger.debug("======== 处理开证消息开始 ==============");
            logger.debug("messageInputBean:{}",messageInputBean);
        }

        handleMessage(messageInputBean);

        if (logger.isDebugEnabled()) {
            logger.debug("======== 处理开证消息结束 ==============");
        }
    }

    @Override
    public MessageService<LcFreezeParamBean> getMessageService() {
        return openLcNotifyMessageService;
    }
}
