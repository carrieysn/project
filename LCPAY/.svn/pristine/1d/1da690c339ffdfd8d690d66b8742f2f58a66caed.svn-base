package com.cifpay.lc.bankadapter.integration;

import com.cifpay.lc.util.AppNodeInfoHelper;
import com.cifpay.lc.util.logging.AbstractLogbackInitializer;

/**
 * Logback初始化配置
 * 
 * 
 *
 */
public class LCPartnerbankAdapterLogbackInitializer extends AbstractLogbackInitializer {

	@Override
	protected String getEnvSpecifiedExternalConfigPath() {
		return "/app/cifpaylc/" + AppNodeInfoHelper.getAppNameOverriddenByJVM("partnerbank-adapter")
				+ "/config/logback-config.xml";
	}

	@Override
	protected String getDefaultClasspathConfigPath() {
		return "com/cifpay/lc/bankadapter/config/logback-config.xml";
	}

}
