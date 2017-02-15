package com.cifpay.lc.core.message.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cifpay.lc.core.message.biz.MessageHandler;
import com.cifpay.lc.core.message.biz.MessagePublisher;

@Service("smsMessageHandler")
public class SmsMessageHandler extends MessageHandler {

	@Autowired
	SmsMessagePublisher smsMessagePublisher;

	@Override
	public MessagePublisher getMessagePublisher() {
		return smsMessagePublisher;
	}
}
