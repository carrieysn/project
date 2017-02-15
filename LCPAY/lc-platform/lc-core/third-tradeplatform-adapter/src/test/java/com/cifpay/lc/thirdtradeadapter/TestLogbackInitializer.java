package com.cifpay.lc.thirdtradeadapter;

import com.cifpay.lc.util.logging.AbstractLogbackInitializer;

/**
 * Logback初始化配置
 * 
 * 
 *
 */
public class TestLogbackInitializer extends AbstractLogbackInitializer {

	@Override
	protected String getEnvSpecifiedExternalConfigPath() {
		return null;
	}

	@Override
	protected String getDefaultClasspathConfigPath() {
		return "com/cifpay/lc/thirdtradeadapter/config/logback-config.xml";
	}

}
