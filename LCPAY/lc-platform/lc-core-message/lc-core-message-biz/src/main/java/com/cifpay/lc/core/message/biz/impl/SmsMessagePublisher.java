package com.cifpay.lc.core.message.biz.impl;

import org.springframework.stereotype.Service;

import com.cifpay.lc.constant.enums.MessageEnum;
import com.cifpay.lc.core.message.biz.MessagePublisher;

@Service("smsMessagePublisher")
public class SmsMessagePublisher extends MessagePublisher {

	@Override
	public String getExchange(int msgType) {
		if(MessageEnum.MsgType.SMS.val == msgType){
			return MessageEnum.EXC_SMS;
		}
		return null;	
	}

	@Override
	public String getRoutingKey(String scene) {
		return scene + "Queue";
	}
}
