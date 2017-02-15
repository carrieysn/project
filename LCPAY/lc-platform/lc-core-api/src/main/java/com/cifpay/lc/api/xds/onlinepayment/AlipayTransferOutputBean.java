package com.cifpay.lc.api.xds.onlinepayment;

import java.io.Serializable;

/**
 * Created by yx on 2016/4/29.
 */
public class AlipayTransferOutputBean implements Serializable {
    private static final long serialVersionUID = 6875260621475396694L;
    private String requestId;
    private String orderId;
    private String mid;
    private Long lcId;
    private String lcNo;

    public AlipayTransferOutputBean() {
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public Long getLcId() {
        return lcId;
    }

    public void setLcId(Long lcId) {
        this.lcId = lcId;
    }

    public String getLcNo() {
        return lcNo;
    }

    public void setLcNo(String lcNo) {
        this.lcNo = lcNo;
    }
}
