package com.cifpay.lc.core.exception;

import com.cifpay.lc.exception.AbstractCoreException;

/**
 * Created by sweet on 16-11-2.
 */
public class SecurityException extends AbstractCoreException {
    public static final String FORMATED_RETURN_CODE_PREFIX = "SECURITY";

    public SecurityException(int returnCode, String message) {
        super(returnCode, message);
    }

    @Override
    protected String getFormatedReturnCodePrefix() {
        return FORMATED_RETURN_CODE_PREFIX;
    }
}
