package com.cifpay.lc.gateway.integration;

import com.cifpay.lc.util.AppNodeInfoHelper;
import com.cifpay.lc.util.logging.AbstractLogbackInitializer;

/**
 * Logback初始化配置
 * 
 * 
 *
 */
public class GatewayLogbackInitializer extends AbstractLogbackInitializer {

	@Override
	protected String getEnvSpecifiedExternalConfigPath() {
		return "/app/cifpaylc/" + AppNodeInfoHelper.getAppNameOverriddenByJVM("gateway") + "/config/logback-config.xml";
	}

	@Override
	protected String getDefaultClasspathConfigPath() {
		return "com/cifpay/lc/gateway/config/logback-config.xml";
	}

}
