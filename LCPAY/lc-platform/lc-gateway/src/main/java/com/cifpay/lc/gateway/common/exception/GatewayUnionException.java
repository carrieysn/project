package com.cifpay.lc.gateway.common.exception;

import com.cifpay.lc.constant.ReturnCode;

/**
 * Created by sweet on 16-12-2.
 */
public class GatewayUnionException extends AbstractGatewayViewException {
    private static final long serialVersionUID = 5248354201029826462L;
    public static final String FORMATED_RETURN_CODE_PREFIX = "UNION";

    public GatewayUnionException(String message) {
        super(ReturnCode.GW_UNION_ERROR, message);
    }

    public GatewayUnionException(int returnCode, String message) {
        super(returnCode, message);
    }

    public GatewayUnionException(int returnCode, String message, Throwable cause) {
        super(returnCode, message, cause);
    }

    public GatewayUnionException(int returnCode, Throwable cause) {
        super(returnCode, cause);
    }

    @Override
    protected String getFormatedReturnCodePrefix() {
        return FORMATED_RETURN_CODE_PREFIX;
    }
}
