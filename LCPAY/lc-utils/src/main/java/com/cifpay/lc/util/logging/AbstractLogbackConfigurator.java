package com.cifpay.lc.util.logging;

import java.io.File;
import java.net.URL;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.classic.spi.Configurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.spi.ContextAwareBase;
import ch.qos.logback.core.util.Loader;
import ch.qos.logback.core.util.StatusPrinter;

/**
 * Logback配置文件加载处理
 * 
 * 
 */
public abstract class AbstractLogbackConfigurator extends ContextAwareBase implements Configurator {

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
	public void configure(LoggerContext loggerContext) {
		JoranConfigurator configurator = new JoranConfigurator();
		configurator.setContext(loggerContext);
		loggerContext.reset();

		ClassLoader myClassLoader = Loader.getClassLoaderOfObject(this);

		try {

			System.out.println("~~~AbstractLogbackConfigurator.configure()...");
			boolean inited = false;
			String externalCfgPath = getEnvSpecifiedExternalConfigPath();
			String defaultClasspathCfgPath = getDefaultClasspathConfigPath();

			if (null != externalCfgPath && externalCfgPath.trim().length() > 0) {
				System.out.println("~~~Checking whether the external logback config file eixsts...");
				File externalCfgFile = new File(externalCfgPath);
				if (externalCfgFile.exists() && externalCfgFile.canRead()) {
					System.out.println("~~~Found the external logback config file: " + externalCfgPath);
					configurator.doConfigure(externalCfgFile);
					StatusPrinter.printInCaseOfErrorsOrWarnings(loggerContext);
					inited = true;
				} else {
					System.out.println("~~~Cannot find or read the external logback config file: " + externalCfgPath);
				}
			}

			if (!inited && null != defaultClasspathCfgPath && defaultClasspathCfgPath.trim().length() > 0) {
				System.out.println("~~~Use the embeded logback config file in classpath: " + defaultClasspathCfgPath);
				URL url = Loader.getResource(defaultClasspathCfgPath, myClassLoader);
				configurator.doConfigure(url);
				StatusPrinter.printInCaseOfErrorsOrWarnings(loggerContext);
				inited = true;
			}

			if (inited) {
				LoggerFactory.getLogger(getClass())
						.info("***** The customized Logback config is inititalized successfully. ******");
			} else {
				System.out.println("*** [Warning] ****No customized logback config file was found or loaded.");
			}
		} catch (JoranException e) {
			System.err.println("!!! Customized Logback config was failed to initialized, err: " + e.getMessage());
			e.printStackTrace(System.err);
		}
	}

}
