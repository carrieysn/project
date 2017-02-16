package com.cifpay.insurance.controller;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cifpay.insurance.service.TestService;
import com.cifpay.starframework.adapter.HttpCall;
import com.cifpay.starframework.cache.ServiceResultCodeCache;
import com.cifpay.starframework.model.ServiceResult;
import com.cifpay.starframework.util.MathUtil;
import com.cifpay.starframework.util.SessionUtil;

@Controller
public class TestController {
	
	private static final Logger LOG = LogManager.getLogger(TestController.class);
	private static ServiceResultCodeCache resultCode = ServiceResultCodeCache.getInstance();
	@Autowired
	private TestService testService;
	
	@RequestMapping(value = "/test/getTest", method = RequestMethod.POST,produces={"text/json;charset=UTF-8"})
	public @ResponseBody 
	String getTest(ServletRequest request) {
		HttpServletRequest req = (HttpServletRequest) request;
		long id = MathUtil.toLong(req.getParameter("id"));
		if (LOG.isDebugEnabled()) {
			LOG.debug("getTest(ServletRequest request)");
			LOG.debug("id=" + id);
		}

		HttpCall httpCall = SessionUtil.getHttpCallSession(req);
		ServiceResult<String> result = testService.get(httpCall);
		return result.getObj();
	}
}
