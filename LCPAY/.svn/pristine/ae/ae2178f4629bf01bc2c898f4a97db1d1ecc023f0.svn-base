package com.cifpay.lc.core.exception;

import com.cifpay.lc.exception.AbstractCoreException;

public class DistributeLockExcpetion extends AbstractCoreException {

    private static final long serialVersionUID = -7708882126654130602L;

    private static final String FORMATED_RETURN_CODE_PREFIX = "LOCK";

    public DistributeLockExcpetion(int returnCode, String message, Throwable cause) {
        super(returnCode, message, cause);
    }

    public DistributeLockExcpetion(int returnCode, String message) {
        super(returnCode, message);
    }

    public DistributeLockExcpetion(int returnCode, Throwable cause) {
        super(returnCode, cause);
    }

    @Override
    protected String getFormatedReturnCodePrefix() {
        return FORMATED_RETURN_CODE_PREFIX;
    }

}
