package com.cifpay.lc.gateway.integration.advice;

import java.lang.reflect.Type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import com.cifpay.lc.domain.security.MerchantRequest;

/**
 * 自动将商户POST+JSON形式的REST请求内容映射到MerchantRequest对象，
 * 有别于RequestParamMerchantRequestResolver，这里只针对JSON请求的情况进行处理，即整个HTTP
 * BODY就是一个JSON对象这种情形。
 * 
 * 
 *
 */
public class MerchantRequestBodyAdvice extends RequestBodyAdviceAdapter implements RequestBodyAdvice {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private boolean isLoggerDebugEnabled = logger.isDebugEnabled();

	@Autowired
	private MerchantRequestBaseHandler merchantRequestBaseHandler;

	@Override
	public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
		return (AbstractJackson2HttpMessageConverter.class.isAssignableFrom(converterType) && MerchantRequest.class.isAssignableFrom(methodParameter.getParameterType()));
	}

	@Override
	public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
		@SuppressWarnings("unchecked")
		MerchantRequest<Object> merReq = (MerchantRequest<Object>) body;

		if (isLoggerDebugEnabled) {
			logger.debug("~~~Gateway收到商户请求（REST JSON），商户ID：{}, 签名：{}，数据：{}", String.valueOf(merReq.getMerId()), String.valueOf(merReq.getSign()), String.valueOf(merReq.getEncodedJsonData()));
		}

		// 输入参数基本校验、验签
		merchantRequestBaseHandler.validateMerRequest(merReq);

		// 基本校验、验签通过后，才将MerchantRequest对象暂存至request上下文中
		RequestContextHolder.getRequestAttributes().setAttribute(MerchantRequest.MER_REQUEST_ATTR_KEY, merReq, RequestAttributes.SCOPE_REQUEST);

		// 数据解码
		merchantRequestBaseHandler.decodeRequestData(merReq, parameter);

		return merReq;
	}

}
