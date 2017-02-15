package com.cifpay.lc.std.sched.integration;

import java.lang.reflect.Method;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

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
			System.out.println("Start to call com.alibaba.dubbo.config.ProtocolConfig.destroyAll()...");
			ProtocolConfig.destroyAll();
			System.out.println("End to call com.alibaba.dubbo.config.ProtocolConfig.destroyAll().");
		} catch (Exception e) {
			System.out.println(
					"Exception on calling com.alibaba.dubbo.config.ProtocolConfig.destroyAll(): " + e.getMessage());
		}

		shutdownMySQLCleanupThread();

		// Shutdown others...
	}

	private void shutdownMySQLCleanupThread() {
		try {
			String clzName = "com.mysql.jdbc.AbandonedConnectionCleanupThread";
			Class<?> clz = null;

			try {
				clz = ClassUtils.resolveClassName(clzName, null);
			} catch (Exception e) {
			}

			if (null != clz) {
				Method m = ReflectionUtils.findMethod(clz, "shutdown");
				if (null != m) {
					System.out.println("Start to shutdown com.mysql.jdbc.AbandonedConnectionCleanupThread ...");
					m.invoke(null);
					System.out.println("End to shutdown com.mysql.jdbc.AbandonedConnectionCleanupThread.");
				}
			}
		} catch (Exception e) {
		}
	}

}
