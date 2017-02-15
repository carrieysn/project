package com.cifpay.lc.gateway.common.exception;

/**
 * API接口抛出的异常
 */
public abstract class AbstractGatewayApiException extends AbstractGatewayException {
    private static final long serialVersionUID = -6629234737489757804L;

    public AbstractGatewayApiException(int returnCode, String message) {
        super(returnCode, message);
    }

    public AbstractGatewayApiException(int returnCode, String message, Throwable cause) {
        super(returnCode, message, cause);
    }

    public AbstractGatewayApiException(int returnCode, Throwable cause) {
        super(returnCode, cause);
    }
}
