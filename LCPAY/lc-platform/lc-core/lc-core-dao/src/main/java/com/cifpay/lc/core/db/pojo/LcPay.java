package com.cifpay.lc.core.db.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class LcPay {
    private Long lcPayId;

    private Long lcConfirmId;

    private Long lcId;

    private String mid;

    private String orderId;

    private BigDecimal totalAmount;

    private Date tradeTime;

    private Date validTime;

    private String unionTxntime;

    private String unionSerialNo;

    private String remark;

    private String lcPayStatus;

    private String lcPayResponse;

    private Date createTime;

    private Date updateTime;

    public Long getLcPayId() {
        return lcPayId;
    }

    public void setLcPayId(Long lcPayId) {
        this.lcPayId = lcPayId;
    }

    public Long getLcConfirmId() {
        return lcConfirmId;
    }

    public void setLcConfirmId(Long lcConfirmId) {
        this.lcConfirmId = lcConfirmId;
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
        this.mid = mid == null ? null : mid.trim();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(Date tradeTime) {
        this.tradeTime = tradeTime;
    }

    public Date getValidTime() {
        return validTime;
    }

    public void setValidTime(Date validTime) {
        this.validTime = validTime;
    }

    public String getUnionTxntime() {
        return unionTxntime;
    }

    public void setUnionTxntime(String unionTxntime) {
        this.unionTxntime = unionTxntime == null ? null : unionTxntime.trim();
    }

    public String getUnionSerialNo() {
        return unionSerialNo;
    }

    public void setUnionSerialNo(String unionSerialNo) {
        this.unionSerialNo = unionSerialNo == null ? null : unionSerialNo.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getLcPayStatus() {
        return lcPayStatus;
    }

    public void setLcPayStatus(String lcPayStatus) {
        this.lcPayStatus = lcPayStatus == null ? null : lcPayStatus.trim();
    }

    public String getLcPayResponse() {
        return lcPayResponse;
    }

    public void setLcPayResponse(String lcPayResponse) {
        this.lcPayResponse = lcPayResponse == null ? null : lcPayResponse.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}