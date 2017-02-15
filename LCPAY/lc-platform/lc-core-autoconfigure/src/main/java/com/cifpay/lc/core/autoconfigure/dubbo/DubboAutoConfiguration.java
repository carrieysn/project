package com.cifpay.lc.core.autoconfigure.dubbo;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.dubbo.remoting.http.servlet.BootstrapListener;
import com.alibaba.dubbo.remoting.http.servlet.DispatcherServlet;

@Configuration
@ConditionalOnProperty(name="cifpay.dubbo.enable", havingValue="true")
public class DubboAutoConfiguration {

	@Bean
	public ServletListenerRegistrationBean<BootstrapListener> dubboBootstrapListener() {
		ServletListenerRegistrationBean<BootstrapListener> registration = new ServletListenerRegistrationBean<BootstrapListener>(
				new BootstrapListener());
		return registration;
	}

	/*@Bean
	public ServletRegistrationBean dubboDispatcherServlet() {
		ServletRegistrationBean registration = new ServletRegistrationBean(new DispatcherServlet(), "/services/*");
		registration.setLoadOnStartup(10);
		return registration;
	}*/

}
