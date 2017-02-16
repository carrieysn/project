package com.cifpay.insurance.listener;
	import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import com.cifpay.insurance.model.InsUser;

	public class LoginFilter implements Filter {

		public FilterConfig config;
	    
	    public void destroy() {
	        this.config = null;
	    }
	    
	    public static boolean isContains(String container, String[] regx) {
	        boolean result = false;

	        for (int i = 0; i < regx.length; i++) {
	            if (container.indexOf(regx[i]) != -1) {
	                return true;
	            }
	        }
	        return result;
	    }

	    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	        HttpServletRequest hrequest = (HttpServletRequest)request;
	        HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper((HttpServletResponse) response);
	        
	        
	        String logonStrings = config.getInitParameter("logonStrings");        // 登录登陆页面
	        String redirectPath = hrequest.getContextPath() + config.getInitParameter("redirectPath");// 没有登陆转向页面
	        String disabletestfilter = config.getInitParameter("disabletestfilter");// 过滤器是否有效
	        
	        if (disabletestfilter.toUpperCase().equals("Y")) {    // 过滤无效
	            chain.doFilter(request, response);
	            return;
	        }
	        String[] logonList = logonStrings.split(";");
	        
	        if (this.isContains(hrequest.getRequestURI(), logonList)) {// 对登录页面不进行过滤
	            chain.doFilter(request, response);
	            return;
	        }
	        
	        InsUser user = ( InsUser ) hrequest.getSession().getAttribute("user");//判断用户是否登录
	        if (user == null) {
	            wrapper.sendRedirect(redirectPath);
	            return;
	        }else {
	            chain.doFilter(request, response);
	            return;
	        }
	    }

	    public void init(FilterConfig filterConfig) throws ServletException {
	        config = filterConfig;
	    }

}
