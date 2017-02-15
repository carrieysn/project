package com.cifpay.lc.core.message.biz.comm;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MessageContext {

	private Map<String, Object> attributes = new ConcurrentHashMap<String, Object>();

	public void setAttribute(String key, Object attrValue) {
		if (null == attrValue) {
			attributes.remove(key);
		} else {
			attributes.put(key, attrValue);
		}
	}

	public Object getAttribute(String key) {
		return attributes.get(key);
	}
	
	
}
