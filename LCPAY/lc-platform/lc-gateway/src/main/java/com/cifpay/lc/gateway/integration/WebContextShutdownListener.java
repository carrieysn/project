package com.cifpay.lc.gateway.integration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.alibaba.dubbo.config.ProtocolConfig;

/**
 * 应用停止后，释放相关资源.
 * 
 * 
 *
 */
public class WebContextShutdownListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// Shutdown Dubbo
		try {
			System.err.println("Start to call com.alibaba.dubbo.config.ProtocolConfig.destroyAll()...");
			ProtocolConfig.destroyAll();
			System.err.println("End to call com.alibaba.dubbo.config.ProtocolConfig.destroyAll().");
		} catch (Exception e) {
			System.err.println(
					"Exception on calling com.alibaba.dubbo.config.ProtocolConfig.destroyAll(): " + e.getMessage());
		}

		// Shutdown others...
	}

}
