package com.cifpay.lc.api.xds.onlinepayment;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by yx on 2016/5/3.
 */
public class AlipayQueryOutputBean implements Serializable {
    private static final long serialVersionUID = 6135096416748874530L;

    private Long lcId;
    private String mid;
    private String orderId;
    private BigDecimal amount;
    private String userCode;
    private String lcReturnUrl;
    private String lcNotifyUrl;
    private String requestId;

    public AlipayQueryOutputBean() {
    }

    public Long getLcId() {
        return lcId;
    }

    public void setLcId(Long lcId) {
        this.lcId = lcId;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getLcReturnUrl() {
        return lcReturnUrl;
    }

    public void setLcReturnUrl(String lcReturnUrl) {
        this.lcReturnUrl = lcReturnUrl;
    }

    public String getLcNotifyUrl() {
        return lcNotifyUrl;
    }

    public void setLcNotifyUrl(String lcNotifyUrl) {
        this.lcNotifyUrl = lcNotifyUrl;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
