package com.cifpay.lc.thirdtradeadapter.integration;

import org.springframework.boot.context.embedded.ServletListenerRegistrationBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.dubbo.remoting.http.servlet.BootstrapListener;
import com.alibaba.dubbo.remoting.http.servlet.DispatcherServlet;

/**
 * Dubbo集成
 * 
 * 
 *
 */
@Configuration
public class DubboConfiguration {

	@Bean
	public ServletListenerRegistrationBean<BootstrapListener> dubboBootstrapListener() {
		ServletListenerRegistrationBean<BootstrapListener> registration = new ServletListenerRegistrationBean<BootstrapListener>(
				new BootstrapListener());
		return registration;
	}

	@Bean
	public ServletRegistrationBean dubboDispatcherServlet() {
		ServletRegistrationBean registration = new ServletRegistrationBean(new DispatcherServlet(), "/services/*");
		return registration;
	}

}
