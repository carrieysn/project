package com.cifpay.lc.std.sched;

import com.cifpay.lc.util.logging.AbstractLogbackInitializer;

/**
 * Logback初始化配置
 * 
 * 
 *
 */
public class TaskTestLogbackInitializer extends AbstractLogbackInitializer {

	@Override
	protected String getEnvSpecifiedExternalConfigPath() {
		return null;
	}

	@Override
	protected String getDefaultClasspathConfigPath() {
		return "com/cifpay/lc/std/sched/config/logback-config.xml";
	}

}
