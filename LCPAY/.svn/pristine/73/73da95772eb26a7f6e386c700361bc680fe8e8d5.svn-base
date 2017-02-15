package com.cifpay.lc.core.exception;

import com.cifpay.lc.exception.AbstractCoreException;

/**
 * 付款异常
 */
public class PaymentException extends AbstractCoreException {
    private static final long serialVersionUID = -7194333529156959624L;
    private static final String FORMATED_RETURN_CODE_PREFIX = "PE";

    public PaymentException(int returnCode, String message, Throwable cause) {
        super(returnCode, message, cause);
    }

    public PaymentException(int returnCode, String message) {
        super(returnCode, message);
    }

    public PaymentException(int returnCode, Throwable cause) {
        super(returnCode, cause);
    }


    @Override
    protected String getFormatedReturnCodePrefix() {
        return FORMATED_RETURN_CODE_PREFIX;
    }
}
