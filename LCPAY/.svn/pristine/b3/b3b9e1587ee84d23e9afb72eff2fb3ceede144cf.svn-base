package com.cifpay.lc.core.message.server.receiver;

import com.cifpay.lc.core.db.pojo.Message;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("redisKeyExpiredReceiver")
public class RedisKeyExpiredReceiver extends AbstractReceiver implements Receiver {
	
	public final static  String MSG_TYPE_LE = "lc";


	@Override
	public Message beforeHandle(String msg) {
		String[] arry = msg.split("BID_");
		//LcMessage message = new LcMessage();
		//message.setLcId(arry[1]);
		//message.setMsgType(MSG_TYPE_LE);
		//return message;
		return null;
	}

	@Override
	public boolean process(Message message) {
		//lcMessageSend.send(message);
		return true;
	}

	
}
