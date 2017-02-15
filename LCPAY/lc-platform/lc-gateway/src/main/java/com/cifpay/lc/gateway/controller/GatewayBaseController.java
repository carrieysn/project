package com.cifpay.lc.gateway.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class GatewayBaseController {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    protected final boolean isLoggerDebugEnabled = logger.isDebugEnabled();

}
