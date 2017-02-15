package com.cifpay.lc.core.db.pojo;

public class LcType {
    private String lcType;

    private String lcTypeDesc;

    private Integer maxDaysToReceive;

    private Integer maxDaysToSend;

    private Integer maxDaysToConfirmPay;

    private Integer maxDaysToPay;

    private Integer isValid;

    public String getLcType() {
        return lcType;
    }

    public void setLcType(String lcType) {
        this.lcType = lcType == null ? null : lcType.trim();
    }

    public String getLcTypeDesc() {
        return lcTypeDesc;
    }

    public void setLcTypeDesc(String lcTypeDesc) {
        this.lcTypeDesc = lcTypeDesc == null ? null : lcTypeDesc.trim();
    }

    public Integer getMaxDaysToReceive() {
        return maxDaysToReceive;
    }

    public void setMaxDaysToReceive(Integer maxDaysToReceive) {
        this.maxDaysToReceive = maxDaysToReceive;
    }

    public Integer getMaxDaysToSend() {
        return maxDaysToSend;
    }

    public void setMaxDaysToSend(Integer maxDaysToSend) {
        this.maxDaysToSend = maxDaysToSend;
    }

    public Integer getMaxDaysToConfirmPay() {
        return maxDaysToConfirmPay;
    }

    public void setMaxDaysToConfirmPay(Integer maxDaysToConfirmPay) {
        this.maxDaysToConfirmPay = maxDaysToConfirmPay;
    }

    public Integer getMaxDaysToPay() {
        return maxDaysToPay;
    }

    public void setMaxDaysToPay(Integer maxDaysToPay) {
        this.maxDaysToPay = maxDaysToPay;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }
}