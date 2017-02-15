package com.cifpay.lc.gateway.common.exception;

/**
 * 普通Controller抛出的异常
 */
public abstract class AbstractGatewayViewException extends AbstractGatewayException {
    private static final long serialVersionUID = 2846635982345708910L;

    public AbstractGatewayViewException(int returnCode, String message) {
        super(returnCode, message);
    }

    public AbstractGatewayViewException(int returnCode, String message, Throwable cause) {
        super(returnCode, message, cause);
    }

    public AbstractGatewayViewException(int returnCode, Throwable cause) {
        super(returnCode, cause);
    }

    @Override
    public String getMessage() {
        String msg = super.getMessage();
        return "[" + getFormatedReturnCode() + "]" + msg;
    }
}
