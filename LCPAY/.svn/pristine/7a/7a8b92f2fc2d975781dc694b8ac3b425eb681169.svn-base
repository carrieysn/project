package com.cifpay.lc.gateway.common.exception;

/**
 * 认证失败
 */
public class GatewayAuthenticationFailure extends AbstractGatewayApiException {
    private static final long serialVersionUID = -6589667044423104596L;
    public static final String FORMATED_RETURN_CODE_PREFIX = "AF";

    public GatewayAuthenticationFailure(int returnCode, String message) {
        super(returnCode, message);
    }

    public GatewayAuthenticationFailure(int returnCode, String message, Throwable cause) {
        super(returnCode, message, cause);
    }

    @Override
    protected String getFormatedReturnCodePrefix() {
        return FORMATED_RETURN_CODE_PREFIX;
    }
}
