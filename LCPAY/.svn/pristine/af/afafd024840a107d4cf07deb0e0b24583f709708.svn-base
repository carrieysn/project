package com.cifpay.lc.std.domain.paychannel;

import com.cifpay.lc.constant.enums.LcTranStatus;

import java.io.Serializable;

/**
 * Created by sweet on 16-11-15.
 */
public class ExpiryOutputBean<T extends Serializable> {
    private boolean success;    // 业务受理成功
    private String message;     // 业务受理信息

    private LcTranStatus lcTranStatus;  // 交易处理状态
    private String serialNo;            // 交易流水号

    private T data;             //  开证返回参数

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LcTranStatus getLcTranStatus() {
        return lcTranStatus;
    }

    public void setLcTranStatus(LcTranStatus lcTranStatus) {
        this.lcTranStatus = lcTranStatus;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
