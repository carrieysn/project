package com.cifpay.lc.domain.security;

import java.io.Serializable;

/**
 * Gateway接口统一响应返回值
 */
public abstract class AbstractApiResponse implements Serializable {
    private static final long serialVersionUID = -5766522727268669910L;

    private String returnCode = "";
    private String returnMsg = "Unknown Error";

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }
}