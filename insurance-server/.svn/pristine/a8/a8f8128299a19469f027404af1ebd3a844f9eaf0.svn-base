/**
 * File: InsuranceMsgPusher.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年12月2日 上午10:34:07
 */
package com.cifpay.insurance.push;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cifpay.insurance.param.msg.MsgResponseInfo;
import com.cifpay.insurance.util.JacksonUtil;
import com.cifpay.insurance.util.SpringContextUtil;
import com.cifpay.starframework.adapter.HttpCall;
import com.cifpay.starframework.cache.ConfigPropertiesCache;
import com.cifpay.starframework.cache.ServiceResultCodeCache;
import com.cifpay.starframework.util.DateUtil;

/**
 * 交还险消息推送者
 * 
 * @author 张均锋
 *
 */
public class InsuranceMsgPusher {
	private static final Logger logger = LoggerFactory.getLogger(InsuranceMsgPusher.class);
	private static ConfigPropertiesCache configProperties = ConfigPropertiesCache.getInstance();
	/**任务线程池 **/
	private static ThreadPoolTaskExecutor executor = (ThreadPoolTaskExecutor)SpringContextUtil.getBean("threadPoolTaskExecutor");
	protected static ServiceResultCodeCache resultCode = ServiceResultCodeCache.getInstance();
	/**
	 * 推送
	 * 
	 * @param rms
	 */
	public static void doPush(MsgResponseInfo rms) {
		PushMsgBean pmb = new PushMsgBean();
		pmb.sendDate = DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
		pmb.receiverId = new String[]{rms.getVendorId()};
		pmb.content = rms;
		final String data = JacksonUtil.toJson(pmb);
		final String serverUrl = configProperties.get("pushserver.url");
		executor.execute(new Runnable() {//异步推送。
			@Override
			public void run() {
				try {
					HttpCall httpCall = new HttpCall();
					Object result = httpCall.httpCall(serverUrl+"?data="+data, "POST");
					String ret = (String)result;
					if(ret != null && ret.length() > 0) {
						JSONObject json = JSON.parseObject(ret);
						String code = json.getString("code");
						if(!resultCode.get("common.framework.success").equals(code))
							logger.error(String.format("发送消息到推送服务器[%1$s]失败：%2$s", serverUrl, json.getString("msg")));
					}
				} catch (Exception e) {
					logger.error("调用推送服务接口出现异常！"+e.getMessage(), e);
				}
			}
		});
	}
	
	/** 推送bean　**/
	static class PushMsgBean {
		private String sendDate;
		private String[] receiverId;
		private Object content;
		public String getSendDate() {
			return sendDate;
		}
		public void setSendDate(String sendDate) {
			this.sendDate = sendDate;
		}
		public String[] getReceiverId() {
			return receiverId;
		}
		public void setReceiverId(String[] receiverId) {
			this.receiverId = receiverId;
		}
		public Object getContent() {
			return content;
		}
		public void setContent(Object content) {
			this.content = content;
		}
		
	}
	
	
}
