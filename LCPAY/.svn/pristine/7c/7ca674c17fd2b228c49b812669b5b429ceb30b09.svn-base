package com.cifpay.lc.core.message.biz.impl;

import org.springframework.stereotype.Service;

import com.cifpay.lc.constant.enums.MessageEnum;
import com.cifpay.lc.core.message.biz.MessagePublisher;

@Service("lcMessagePublisher")
public class LcMessagePublisher extends MessagePublisher{

	@Override
	public String getExchange(int msgType) {
		if(MessageEnum.MsgType.LC.val == msgType){
			return MessageEnum.EXC_LC;
		}
		return null;	
	}

	@Override
	public String getRoutingKey(String scene) {
		return scene + "Queue";
	}
}
