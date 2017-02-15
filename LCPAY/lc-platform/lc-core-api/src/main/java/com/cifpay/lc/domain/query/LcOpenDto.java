package com.cifpay.lc.domain.query;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by sweet on 16-11-7.
 */
public class LcOpenDto implements Serializable {
    private static final long serialVersionUID = -3511355927621737511L;

    private Long lcOpenId;

    private Long lcId;

    private Long lcBatchId;

    private String mid;

    private String orderId;

    private String lcCurrency;

    private BigDecimal lcAmount;

    private String payerId;

    private String payerAccno;

    private String payerType;

    private String payerBankCode;

    private String payerBankName;

    private String payerMobile;

    private String recvId;

    private String recvAccno;

    private String recvType;

    private String recvBankCode;

    private String recvBankName;

    private String recvMobile;

    private Date validTime;

    private String unionTxntime;

    private String unionSerialNo;

    private String remark;

    private Date tradeTime;

    private String lcOpenChannel;

    private String lcOpenStatus;

    private String lcOpenResponse;

    private Date createTime;

    private Date updateTime;

    public Long getLcOpenId() {
        return lcOpenId;
    }

    public void setLcOpenId(Long lcOpenId) {
        this.lcOpenId = lcOpenId;
    }

    public Long getLcId() {
        return lcId;
    }

    public void setLcId(Long lcId) {
        this.lcId = lcId;
    }

    public Long getLcBatchId() {
        return lcBatchId;
    }

    public void setLcBatchId(Long lcBatchId) {
        this.lcBatchId = lcBatchId;
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

    public String getLcCurrency() {
        return lcCurrency;
    }

    public void setLcCurrency(String lcCurrency) {
        this.lcCurrency = lcCurrency == null ? null : lcCurrency.trim();
    }

    public BigDecimal getLcAmount() {
        return lcAmount;
    }

    public void setLcAmount(BigDecimal lcAmount) {
        this.lcAmount = lcAmount;
    }

    public String getPayerId() {
        return payerId;
    }

    public void setPayerId(String payerId) {
        this.payerId = payerId == null ? null : payerId.trim();
    }

    public String getPayerAccno() {
        return payerAccno;
    }

    public void setPayerAccno(String payerAccno) {
        this.payerAccno = payerAccno == null ? null : payerAccno.trim();
    }

    public String getPayerType() {
        return payerType;
    }

    public void setPayerType(String payerType) {
        this.payerType = payerType == null ? null : payerType.trim();
    }

    public String getPayerBankCode() {
        return payerBankCode;
    }

    public void setPayerBankCode(String payerBankCode) {
        this.payerBankCode = payerBankCode == null ? null : payerBankCode.trim();
    }

    public String getPayerBankName() {
        return payerBankName;
    }

    public void setPayerBankName(String payerBankName) {
        this.payerBankName = payerBankName == null ? null : payerBankName.trim();
    }

    public String getPayerMobile() {
        return payerMobile;
    }

    public void setPayerMobile(String payerMobile) {
        this.payerMobile = payerMobile == null ? null : payerMobile.trim();
    }

    public String getRecvId() {
        return recvId;
    }

    public void setRecvId(String recvId) {
        this.recvId = recvId == null ? null : recvId.trim();
    }

    public String getRecvAccno() {
        return recvAccno;
    }

    public void setRecvAccno(String recvAccno) {
        this.recvAccno = recvAccno == null ? null : recvAccno.trim();
    }

    public String getRecvType() {
        return recvType;
    }

    public void setRecvType(String recvType) {
        this.recvType = recvType == null ? null : recvType.trim();
    }

    public String getRecvBankCode() {
        return recvBankCode;
    }

    public void setRecvBankCode(String recvBankCode) {
        this.recvBankCode = recvBankCode == null ? null : recvBankCode.trim();
    }

    public String getRecvBankName() {
        return recvBankName;
    }

    public void setRecvBankName(String recvBankName) {
        this.recvBankName = recvBankName == null ? null : recvBankName.trim();
    }

    public String getRecvMobile() {
        return recvMobile;
    }

    public void setRecvMobile(String recvMobile) {
        this.recvMobile = recvMobile == null ? null : recvMobile.trim();
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

    public Date getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(Date tradeTime) {
        this.tradeTime = tradeTime;
    }

    public String getLcOpenChannel() {
        return lcOpenChannel;
    }

    public void setLcOpenChannel(String lcOpenChannel) {
        this.lcOpenChannel = lcOpenChannel == null ? null : lcOpenChannel.trim();
    }

    public String getLcOpenStatus() {
        return lcOpenStatus;
    }

    public void setLcOpenStatus(String lcOpenStatus) {
        this.lcOpenStatus = lcOpenStatus == null ? null : lcOpenStatus.trim();
    }

    public String getLcOpenResponse() {
        return lcOpenResponse;
    }

    public void setLcOpenResponse(String lcOpenResponse) {
        this.lcOpenResponse = lcOpenResponse == null ? null : lcOpenResponse.trim();
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
