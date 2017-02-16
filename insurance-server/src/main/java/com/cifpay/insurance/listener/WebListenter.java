package com.cifpay.insurance.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.cifpay.insurance.config.WebAppInit;
import com.cifpay.starframework.redis.util.RedisConnectionPool;
import com.cifpay.starframework.util.SpringUtil;

public class WebListenter implements ServletContextListener {
	private static final Logger LOG = LogManager.getLogger(WebListenter.class);

	public void contextInitialized(ServletContextEvent event) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("WebListenter contextInitialized(ServletContextEvent event) start");
		}
		ServletContext servletContext = event.getServletContext();
		SpringUtil.setApplicationContext(WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext));

		//MessageSourceUtil.setMessageSource((MessageSource) SpringUtil.getBean("messageSource"));

		//CacheSimpleManger.init();
		
		RedisConnectionPool redisConnPool = RedisConnectionPool.getInstance();
		redisConnPool.getSessionConnection();
		
		new WebAppInit().loadCache();
		
		if (LOG.isDebugEnabled()) {
			LOG.debug("WebListenter contextInitialized(ServletContextEvent event) end");
		}
	}

	public void contextDestroyed(ServletContextEvent event) {
	}
}
