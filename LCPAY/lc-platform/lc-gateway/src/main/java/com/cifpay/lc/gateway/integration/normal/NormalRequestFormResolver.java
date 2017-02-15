package com.cifpay.lc.gateway.integration.normal;

import java.net.URLDecoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.cifpay.lc.gateway.input.NormalRequest;

public class NormalRequestFormResolver implements HandlerMethodArgumentResolver {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private boolean isLoggerDebugEnabled = logger.isDebugEnabled();

	@Autowired
	private NormalMerchantHandler normalMerchantHandler;

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		NormalFormRequest requestParam = parameter.getParameterAnnotation(NormalFormRequest.class);
		return (requestParam != null && NormalRequest.class.isAssignableFrom(parameter.getParameterType()));
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

		NormalRequest<Object> request = new NormalRequest<Object>();
		request.setAppId(webRequest.getParameter("appId"));
		request.setService(webRequest.getParameter("service"));
		request.setCharset(webRequest.getParameter("charset"));
		request.setSignType(webRequest.getParameter("signType"));
		request.setSign(webRequest.getParameter("sign"));
		request.setVersion(webRequest.getParameter("version"));
		request.setTimesTamp(webRequest.getParameter("timesTamp"));
		request.setMobile(webRequest.getParameter("mobile"));
		request.setData(webRequest.getParameter("data"));

		if (isLoggerDebugEnabled) {
			logger.debug("~~~Gateway收到商户请求（普通GET/POST），商户ID：{}, 签名：{}，数据：", String.valueOf(request.getAppId()), String.valueOf(request.getSign()));
		}

		// 输入参数基本校验、验签
		normalMerchantHandler.validateMerRequest(request);

		// 基本校验、验签通过后，才将MerchantRequest对象暂存至request上下文中
		RequestContextHolder.getRequestAttributes().setAttribute(NormalRequest.MER_REQUEST_ATTR_KEY, request, RequestAttributes.SCOPE_REQUEST);

		String rawData = URLDecoder.decode(request.getData(), request.getCharset());
		
		// 数据解码
		Object decryptData = normalMerchantHandler.decodeRequestData(rawData, parameter);
		request.setDecryptData(decryptData);

		return request;
	}

}
