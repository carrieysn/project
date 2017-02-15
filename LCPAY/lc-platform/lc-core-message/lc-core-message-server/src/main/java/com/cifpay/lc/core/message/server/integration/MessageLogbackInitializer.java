package com.cifpay.lc.core.message.server.integration;

import com.cifpay.lc.util.AppNodeInfoHelper;
import com.cifpay.lc.util.logging.AbstractLogbackInitializer;

/**
 * Created by sweet on 16-12-30.
 */
public class MessageLogbackInitializer extends AbstractLogbackInitializer {

    @Override
    protected String getEnvSpecifiedExternalConfigPath() {
        return "/app/cifpaylc/" + AppNodeInfoHelper.getAppNameOverriddenByJVM("message") + "/config/logback-config.xml";
    }

    @Override
    protected String getDefaultClasspathConfigPath() {
        return "com/cifpay/lc/core/message/config/logback-config.xml";
    }

}
