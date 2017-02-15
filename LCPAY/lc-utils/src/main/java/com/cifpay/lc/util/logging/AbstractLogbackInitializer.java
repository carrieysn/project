package com.cifpay.lc.util.logging;

import org.slf4j.ILoggerFactory;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;

import ch.qos.logback.classic.LoggerContext;

/**
 * Logback初始化抽象类 ，其实现类适合通过META-INF/spring.factories的方式来引入到应用中。<br>
 * 
 * <pre>
 * spring.factories文件举例：
 * 
 * org.springframework.context.ApplicationContextInitializer=com.cifpay.lc.simplegw.SimpleGWLogbackInitializer
 * 
 * 其中，SimpleGWLogbackInitializer是AbstractLogbackInitializer的实现类。 
 * 
 * <pre>
 * 
 *
 */
public abstract class AbstractLogbackInitializer
		implements ApplicationContextInitializer<ConfigurableApplicationContext>, Ordered {

	/**
	 * 获取WAR应用之外的属于当前环境（例如SIT环境、UAT环境、生产环境）的logback配置文件的外部路径，例如C:/abc/hello.xml
	 * 
	 * @return
	 */
	protected abstract String getEnvSpecifiedExternalConfigPath();

	/**
	 * 获取WAR应用之外的classpath范围的logback配置文件的包路径，例如abc/hello.xml
	 * 
	 * @return
	 */
	protected abstract String getDefaultClasspathConfigPath();

	@Override
	public int getOrder() {
		return Integer.MIN_VALUE;
	}

	class InternalLogbackConfigurator extends AbstractLogbackConfigurator {

		String envSpecifiedExternalConfigPath;
		String defaultClasspathConfigPath;

		public InternalLogbackConfigurator(String envSpecifiedExternalConfigPath, String defaultClasspathConfigPath) {
			super();
			this.envSpecifiedExternalConfigPath = envSpecifiedExternalConfigPath;
			this.defaultClasspathConfigPath = defaultClasspathConfigPath;
		}

		@Override
		protected String getEnvSpecifiedExternalConfigPath() {
			return envSpecifiedExternalConfigPath;
		}

		@Override
		protected String getDefaultClasspathConfigPath() {
			return defaultClasspathConfigPath;
		}
	}

	@Override
	public final void initialize(ConfigurableApplicationContext applicationContext) {
		System.out.println("~~~LogbackInitializer.initialize()...");
		ILoggerFactory fac = LoggerFactory.getILoggerFactory();
		if (fac instanceof LoggerContext) {
			new InternalLogbackConfigurator(getEnvSpecifiedExternalConfigPath(), getDefaultClasspathConfigPath())
					.configure((LoggerContext) fac);
		}
	}

}
