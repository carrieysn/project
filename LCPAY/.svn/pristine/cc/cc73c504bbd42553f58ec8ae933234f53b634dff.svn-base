package com.cifpay.lc.std.integration;

import com.cifpay.lc.util.AppNodeInfoHelper;
import com.cifpay.lc.util.logging.AbstractLogbackInitializer;

/**
 * Logback初始化配置
 * 
 * 
 *
 */
public class StandardBusinessLogbackInitializer extends AbstractLogbackInitializer {

	@Override
	protected String getEnvSpecifiedExternalConfigPath() {
		return "/app/cifpaylc/" + AppNodeInfoHelper.getAppNameOverriddenByJVM("standard-business")
				+ "/config/logback-config.xml";
	}

	@Override
	protected String getDefaultClasspathConfigPath() {
		return "com/cifpay/lc/std/business/config/logback-config.xml";
	}

}
