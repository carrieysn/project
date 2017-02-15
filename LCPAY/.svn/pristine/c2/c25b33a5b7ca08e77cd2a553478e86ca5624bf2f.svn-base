package com.cifpay.lc.std.sched.integration;

import com.cifpay.lc.util.AppNodeInfoHelper;
import com.cifpay.lc.util.logging.AbstractLogbackInitializer;

/**
 * Logback初始化配置
 * 
 * 
 *
 */
public class StandardLCSchedulerLogbackInitializer extends AbstractLogbackInitializer {

	@Override
	protected String getEnvSpecifiedExternalConfigPath() {
		return "/app/cifpaylc/" + AppNodeInfoHelper.getAppNameOverriddenByJVM("standard-scheduler")
				+ "/config/logback-config.xml";
	}

	@Override
	protected String getDefaultClasspathConfigPath() {
		return "com/cifpay/lc/std/sched/config/logback-config.xml";
	}

}
