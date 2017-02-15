package com.cifpay.lc.std.util;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cifpay.lc.core.autoconfigure.sms.SmsProperties;

public class SmsSendUtil {
	private static final Logger logger = LoggerFactory.getLogger(SmsSendUtil.class);
	
	public static boolean sendSms(String phone, String msg, SmsProperties smsProperties){
		if(logger.isDebugEnabled()){
			logger.debug("send SMS start phone:" + phone +" msg: "+msg +" sendflag:"+smsProperties.getSendflag()+" username:"+smsProperties.getUsername());
		}
		Map<String, String> map = new HashMap<String,String>();
		map.put("account", smsProperties.getUsername());
		map.put("pswd", smsProperties.getPassword());
		map.put("mobile", phone);
		map.put("msg", msg);
		map.put("needstatus", String.valueOf(true));
		int returncode = -1;
		if("true".equals(smsProperties.getSendflag())){
			try {
				String rs = HTTPTool.postMap(smsProperties.getUrl(), map);
				if(logger.isDebugEnabled()){
					logger.debug("send SMS return :{}",rs);
				}
				String[] repstrlist = rs.split("\n");
				if (repstrlist.length > 0) {
					repstrlist = repstrlist[0].split(",");
					if (repstrlist.length > 1) {
						returncode = Integer.parseInt(repstrlist[1]);
						if(returncode==0){
							return true;
						}
					}
				}
			} catch (Exception e) {
				logger.error("发送短信失败,err:{}",e);
			}
		}else{
			logger.info("send SMS return code :" + returncode);
			return true;
		}
				
		return false;
	}
	
}
