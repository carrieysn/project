package com.cifpay.lc.gateway.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 请求记录
 */
public class AccessInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(AccessInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            String url = request.getRequestURL().toString();
            String uri = request.getRequestURI();
            String method = request.getMethod();
            String queryString = request.getQueryString();
            System.out.println(request.getParameterMap());
            logger.debug(String.format("请求参数, url: %s, method: %s, queryString: %s", url, method, queryString));
        } catch (Throwable ex) {
            logger.error("拦截器错误:", ex);
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
