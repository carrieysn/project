package com.cifpay.insurance.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import com.alibaba.fastjson.JSONObject;
import com.cifpay.insurance.service.InsPolicyOrderService;
import com.cifpay.insurance.util.SpringContextUtil;
import com.cifpay.starframework.util.PathUtil;
import com.cifpay.starframework.util.SpringUtil;
import com.cifpay.starframework.util.StrUtil;

public class AuthFilter implements Filter {
	private static final Logger LOG = LogManager.getLogger(AuthFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		
		InsPolicyOrderService active = (InsPolicyOrderService) SpringContextUtil.getApplicationContext().getBean("insPolicyOrderService");
		
		System.out.println(">>>>>>>>>>>>>>> active <<<<<<<<<<<<<<<<<<"+active.getCount());
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		//User userSession = (User) SessionAndCookieUtil.getUser(req);
		//Manager managerSession = (Manager) SessionAndCookieUtil.getManager(req);
		if (LOG.isDebugEnabled()) {
			LOG.debug("req.getMethod()=" + req.getMethod());
			LOG.debug("req.getRequestURL()=" + req.getRequestURL());
			LOG.debug("req.getRequestURI()=" + req.getRequestURI());
			LOG.debug("req.getQueryString()=" + req.getQueryString());
			LOG.debug("req.getServletPath()=" + req.getServletPath());
			LOG.debug("req.getRemoteHost()=" + req.getRemoteHost());
			LOG.debug("req.getRemoteAddr()=" + req.getRemoteAddr());
		}

		if (LOG.isDebugEnabled()) {
			LOG.debug("***************************************************");
			List<String> requestParameterList = PathUtil.getRequestParameterList(req);
			int parameterListSize = (requestParameterList != null ? requestParameterList.size() : 0);
			LOG.debug("requestParameterList=" + parameterListSize);
			for (int i = 0; i < parameterListSize; i++) {
				LOG.debug(requestParameterList.get(i));
			}

			List<String> requestHeaderList = PathUtil.getRequestHeaderList(req);
			int requestHeaderListSize = (requestHeaderList != null ? requestHeaderList.size() : 0);
			LOG.debug("requestHeaderListSize=" + requestHeaderListSize);
			for (int i = 0; i < requestHeaderListSize; i++) {
				LOG.debug(requestHeaderList.get(i));
			}
			LOG.debug("***************************************************");
		}

		String servletPath = req.getServletPath();

		if (servletPath.startsWith("/user/")) {
			response.setContentType("application/json;charset=UTF-8");
			String methodName = req.getMethod().toUpperCase();
			String methodNameData = StrUtil.getParameter(req.getParameter("method"));
			if(!"".equals(methodNameData)) {
				methodName = methodNameData;
			}
			PrintWriter out = null;
			try {
				String accessToken = StrUtil.getParameter(request.getParameter("access_token"));
				if(LOG.isDebugEnabled()) {
					LOG.debug("request.getParameter(\"access_token\")=" + accessToken);
				}
				Locale locale = request.getLocale();
				JSONObject jsonObject = new JSONObject();
				String msg = "";
				ReloadableResourceBundleMessageSource messageSource = (ReloadableResourceBundleMessageSource)SpringUtil.getBean("messageSource");
				
				
				if(!methodName.equals("")) {
					out = response.getWriter();
					jsonObject.put("code", 222);
					jsonObject.put("msg", "");
					out.print(jsonObject.toJSONString());
					return;
				}
			} catch (Exception e) {
				LOG.error(e);
			} finally {
				if (out != null) {
					try {
						out.close();
					} catch (Exception e) {
						LOG.error(e.getMessage(), e);
					}
				}
			}
		} 
		if (LOG.isDebugEnabled()) {
			LOG.debug("chain.doFilter(request, response);");
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}
}
