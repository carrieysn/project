package com.cifpay.insurance.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.cifpay.insurance.config.WebConstant;
import com.cifpay.insurance.service.TestService;
import com.cifpay.starframework.adapter.HttpCall;
import com.cifpay.starframework.cache.ServiceResultCodeCache;
import com.cifpay.starframework.model.ServiceResult;

@Service("testService")
public class TestServiceImpl implements TestService {
	private static final Logger LOG = LogManager.getLogger(TestServiceImpl.class);
	private static ServiceResultCodeCache resultCode = ServiceResultCodeCache.getInstance();

	@Override
	public ServiceResult<String> get(HttpCall httpCall) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		// 调用远程服务器
		StringBuilder requestUrl = new StringBuilder();
		Map<String, String> reqParam = new HashMap<String, String>();
		requestUrl.append(WebConstant.INSURANCE_SERVER_DOMAIN);
		requestUrl.append("/test/getTest");
		reqParam.put("id","1");
		
		String method = WebConstant.REQUEST_POST;
		if (LOG.isDebugEnabled()) {
			LOG.debug("requestUrl: " + requestUrl.toString());
			LOG.debug("method: " + method);
		}
		LOG.info(requestUrl.toString());
		
		Object resResult = httpCall.httpCall(requestUrl.toString(), method, reqParam);
		if (resResult instanceof String) {
			String resultJson = (String) resResult;
			serviceResult.setObj(resultJson);
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug("getProductVolume end");
		}
		return serviceResult;
	}
}
