package com.cifpay.lc.std.domain.kernel;

import java.io.Serializable;

import com.cifpay.lc.constant.enums.LcTranStatus;

public class RefundKernalOutputBean<T extends Serializable> implements Serializable {
    
    private static final long serialVersionUID = -3137451902065460257L;
    private boolean success; // 业务受理成功
    private String message; // 业务受理信息
    private LcTranStatus lcTranStatus; // 交易处理状态
    private String serialNo; // 交易流水号
    private T data; // 开证返回参数

    public LcTranStatus getLcTranStatus() {
        return lcTranStatus;
    }

    public void setLcTranStatus(LcTranStatus lcTranStatus) {
        this.lcTranStatus = lcTranStatus;
    }

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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("RefundKernalOutputBean [success=");
        builder.append(success);
        builder.append(", message=");
        builder.append(message);
        builder.append(", lcTranStatus=");
        builder.append(lcTranStatus);
        builder.append(", serialNo=");
        builder.append(serialNo);
        builder.append(", data=");
        builder.append(data);
        builder.append("]");
        return builder.toString();
    }
}
