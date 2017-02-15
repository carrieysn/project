package com.cifpay.lc.domain.lc;

import com.cifpay.lc.constant.enums.PayMethod;

import java.math.BigDecimal;

public class OpenLcOutputBean extends AbstractLcOutputBean {

    private static final long serialVersionUID = 7433159208758154930L;

    private String orderId;         // 商户订单号
    private BigDecimal lcAmount;    // 银信证开证金额（单位分）

    private Long lcOpenId;          // 开证ID
    private PayMethod payMethod;    // 付款方式
    private String serialNo;        // 交易流水号

    private String returnUrl;        // 返回地址

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getLcAmount() {
        return lcAmount;
    }

    public void setLcAmount(BigDecimal lcAmount) {
        this.lcAmount = lcAmount;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public Long getLcOpenId() {
        return lcOpenId;
    }

    public void setLcOpenId(Long lcOpenId) {
        this.lcOpenId = lcOpenId;
    }

    public PayMethod getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(PayMethod payMethod) {
        this.payMethod = payMethod;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    @Override
    public String toString() {
        return "OpenLcOutputBean{" +
                "orderId='" + orderId + '\'' +
                ", lcAmount=" + lcAmount +
                ", lcOpenId=" + lcOpenId +
                ", payMethod=" + payMethod +
                ", serialNo='" + serialNo + '\'' +
                ", returnUrl='" + returnUrl + '\'' +
                '}';
    }
}
