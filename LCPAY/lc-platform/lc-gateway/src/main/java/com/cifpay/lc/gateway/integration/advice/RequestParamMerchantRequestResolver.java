package com.cifpay.lc.gateway.integration.advice;

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

import com.cifpay.lc.domain.security.MerchantRequest;

/**
 * 和MerchantRequestBodyAdvice类似，自动将商户传统请求的HTTP Request
 * Parameter参数自动映射到MerchantRequest对象。有别于MerchantRequestBodyAdvice，
 * 这里只针对普通GET或POST方式(paramName=paramValue)提交参数的情形。
 * 
 * 
 *
 * @see com.cifpay.lc.gateway.integration.advice.MerchantRequestBodyAdvice
 */
public class RequestParamMerchantRequestResolver implements HandlerMethodArgumentResolver {
	private static final String REQ_PARAM_MER_ID = "merId";
	private static final String REQ_PARAM_SIGN = "sign";
	private static final String REQ_PARAM_ENCODED_JSON_DATA = "encodedJsonData";

	private Logger logger = LoggerFactory.getLogger(getClass());
	private boolean isLoggerDebugEnabled = logger.isDebugEnabled();

	@Autowired
	private MerchantRequestBaseHandler merchantRequestBaseHandler;

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		MerchantFormRequest requestParam = parameter.getParameterAnnotation(MerchantFormRequest.class);
		return (requestParam != null && MerchantRequest.class.isAssignableFrom(parameter.getParameterType()));
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

		MerchantRequest<Object> merReq = new MerchantRequest<Object>();
		merReq.setMerId(webRequest.getParameter(REQ_PARAM_MER_ID));
		merReq.setSign(webRequest.getParameter(REQ_PARAM_SIGN));
		merReq.setEncodedJsonData(webRequest.getParameter(REQ_PARAM_ENCODED_JSON_DATA));

		if (isLoggerDebugEnabled) {
			logger.debug("~~~Gateway收到商户请求（普通GET/POST），商户ID：{}, 签名：{}，数据：{}", String.valueOf(merReq.getMerId()), String.valueOf(merReq.getSign()), String.valueOf(merReq.getEncodedJsonData()));
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
