package mock.merchant.integration;

import com.cifpay.lc.util.AppNodeInfoHelper;
import com.cifpay.lc.util.logging.AbstractLogbackInitializer;

/**
 * Logback初始化配置
 * 
 * 
 *
 */
public class MockMerchantLogbackInitializer extends AbstractLogbackInitializer {

	@Override
	protected String getEnvSpecifiedExternalConfigPath() {
		return "/app/cifpaylc/" + AppNodeInfoHelper.getAppNameOverriddenByJVM("mock-api") + "/config/logback-config.xml";
	}

	@Override
	protected String getDefaultClasspathConfigPath() {
		return "mock/merchant/config/logback-config.xml";
	}

}
