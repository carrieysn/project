package com.cifpay.lc.gateway.common.exception;

/**
 * 银信证业务gateway异常
 */
public class GatewayLcException extends AbstractGatewayApiException {
    private static final long serialVersionUID = 7776956493362944319L;
    public static final String FORMATED_RETURN_CODE_PREFIX = "GL";

    public GatewayLcException(int returnCode, String message) {
        super(returnCode, message);
    }

    public GatewayLcException(int returnCode, String message, Throwable cause) {
        super(returnCode, message, cause);
    }

    public GatewayLcException(int returnCode, Throwable cause) {
        super(returnCode, cause);
    }

    @Override
    protected String getFormatedReturnCodePrefix() {
        return FORMATED_RETURN_CODE_PREFIX;
    }

}
