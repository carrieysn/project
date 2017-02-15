package com.cifpay.lc.gateway.common.exception;

/**
 * 核心业务层对输入进行校验，若校验不通过，则抛出此异常。
 */
public class GatewayValidationRejectException extends AbstractGatewayApiException {
    private static final long serialVersionUID = -4272668632615358264L;
    public static final String FORMATED_RETURN_CODE_PREFIX = "GV";

    public GatewayValidationRejectException(int returnCode, String message) {
        super(returnCode, message);
    }

    public GatewayValidationRejectException(int returnCode, String message, Throwable cause) {
        super(returnCode, message, cause);
    }

    @Override
    protected String getFormatedReturnCodePrefix() {
        return FORMATED_RETURN_CODE_PREFIX;
    }
}
