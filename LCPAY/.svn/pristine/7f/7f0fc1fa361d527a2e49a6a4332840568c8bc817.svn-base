package com.cifpay.lc.gateway.output;

/**
 * ajax返回值的基类
 * Created by sweet on 16-9-22.
 */
public class AjaxResponse {
    private Boolean isSuccess;
    private String message;

    public static AjaxResponse success() {
        AjaxResponse response = new AjaxResponse();
        response.setSuccess(true);
        response.setMessage("操作成功");

        return response;
    }

    public static AjaxResponse fail(String message) {
        AjaxResponse response = new AjaxResponse();
        response.setSuccess(false);
        response.setMessage(message);

        return response;
    }

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
