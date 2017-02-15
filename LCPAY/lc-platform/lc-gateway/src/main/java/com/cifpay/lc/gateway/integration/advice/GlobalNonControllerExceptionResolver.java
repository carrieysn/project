package com.cifpay.lc.gateway.integration.advice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller之外的异常（例如404）。
 * 
 * 
 *
 */
public class GlobalNonControllerExceptionResolver implements HandlerExceptionResolver {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final boolean isLoggerDebugEnabled = logger.isDebugEnabled();

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		// TODO Auto-generated method stub
		return null;
	}
}
