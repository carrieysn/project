package com.cifpay.lc.core.message.biz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.cifpay.lc.core.db.dao.MessageDao;
import com.cifpay.lc.domain.message.MessageInputBean;

public abstract class MessagePublisher {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	RabbitTemplate rabbitTemplate;

	@Autowired
	MessageDao messageDao;

	public boolean send(MessageInputBean messageInput) {
		try {
			String exchage = getExchange(messageInput.getMsgType());
			String roteKey = getRoutingKey(messageInput.getSence());
			if (!StringUtils.isEmpty(exchage) && !StringUtils.isEmpty(roteKey)) {
				logger.debug("exchage={}, roteKey={}, messageInput={}", exchage, roteKey, messageInput);
				rabbitTemplate.convertAndSend(exchage, roteKey, messageInput);
			} else if (!StringUtils.isEmpty(roteKey)) {
				logger.debug("roteKey={}, messageInput={}", roteKey, messageInput);
				rabbitTemplate.convertAndSend(roteKey, messageInput);
			} else {
				logger.debug("messageInput={}", messageInput);
				rabbitTemplate.convertAndSend(messageInput);
			}
			return true;
		} catch (Exception e) {
			logger.error("消息放入消息队列异常，input:{},err:{}", messageInput, e);
			return false;
		}
	}

	public abstract String getExchange(int msgType);

	public abstract String getRoutingKey(String scene);

}
