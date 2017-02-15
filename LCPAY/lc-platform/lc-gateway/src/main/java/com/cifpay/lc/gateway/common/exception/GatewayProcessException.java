package com.cifpay.lc.gateway.common.exception;

/**
 * Gateway层进行处理时出现的异常，即除了校验未通知异常GatewayValidationRejectException之外的通用异常。
 */
public class GatewayProcessException extends AbstractGatewayApiException {
    private static final long serialVersionUID = -8355156644398741553L;
    public static final String FORMATED_RETURN_CODE_PREFIX = "GP";

    public GatewayProcessException(int returnCode, String message) {
        super(returnCode, message);
    }

    @Override
    protected String getFormatedReturnCodePrefix() {
        return FORMATED_RETURN_CODE_PREFIX;
    }
}
