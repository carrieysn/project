package com.cifpay.lc.core.message.server.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cifpay.lc.core.db.pojo.Message;

public abstract class AbstractReceiver implements Receiver{
	
	Logger logger = LoggerFactory.getLogger(AbstractReceiver.class);

	@Override
	public void onMessage(String msg) {
		Message message = null;
		try{
			message = beforeHandle(msg);
			
			process(message);
			
			afterHandle(message);
		}catch(Exception e){
			logger.error("处理订阅到消息异常，原因：{}", e);
		}
	}
	
	public abstract Message beforeHandle(String msg);
	
	public abstract boolean process(Message message);
	
	public void afterHandle(Message message){}
}
