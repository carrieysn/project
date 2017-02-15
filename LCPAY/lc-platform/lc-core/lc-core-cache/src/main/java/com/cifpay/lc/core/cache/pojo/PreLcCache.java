package com.cifpay.lc.core.cache.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by sweet on 16-9-14.
 */
public class PreLcCache implements Serializable {
    private static final long serialVersionUID = -6433797096745434613L;

    private Long lcId;

    private String batchNo;

    private String mid;

    private String orderId;

    private String orderContent;

    private Long productId;

    private String productCode;

    private String lcNo;

    private String lcType;

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

    private Date openValidTime;

    private Date recvValidTime;

    private Date sendValidTime;

    private Date confirmValidTime;

    private Date payValidTime;

    private String lcStateReturnUrl;

    private String lcStateNotifyUrl;

    private String lcOrderDetailUrl;

    private String thirdPartyCode;

    private String payType;

    private Boolean isValid;

    private Date createTime;

    private Date updateTime;

    private String remark;

    public Long getLcId() {
        return lcId;
    }

    public void setLcId(Long lcId) {
        this.lcId = lcId;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo == null ? null : batchNo.trim();
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

    public String getOrderContent() {
        return orderContent;
    }

    public void setOrderContent(String orderContent) {
        this.orderContent = orderContent == null ? null : orderContent.trim();
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode == null ? null : productCode.trim();
    }

    public String getLcNo() {
        return lcNo;
    }

    public void setLcNo(String lcNo) {
        this.lcNo = lcNo == null ? null : lcNo.trim();
    }

    public String getLcType() {
        return lcType;
    }

    public void setLcType(String lcType) {
        this.lcType = lcType == null ? null : lcType.trim();
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

    public Date getOpenValidTime() {
        return openValidTime;
    }

    public void setOpenValidTime(Date openValidTime) {
        this.openValidTime = openValidTime;
    }

    public Date getRecvValidTime() {
        return recvValidTime;
    }

    public void setRecvValidTime(Date recvValidTime) {
        this.recvValidTime = recvValidTime;
    }

    public Date getSendValidTime() {
        return sendValidTime;
    }

    public void setSendValidTime(Date sendValidTime) {
        this.sendValidTime = sendValidTime;
    }

    public Date getConfirmValidTime() {
        return confirmValidTime;
    }

    public void setConfirmValidTime(Date confirmValidTime) {
        this.confirmValidTime = confirmValidTime;
    }

    public Date getPayValidTime() {
        return payValidTime;
    }

    public void setPayValidTime(Date payValidTime) {
        this.payValidTime = payValidTime;
    }

    public String getLcStateReturnUrl() {
        return lcStateReturnUrl;
    }

    public void setLcStateReturnUrl(String lcStateReturnUrl) {
        this.lcStateReturnUrl = lcStateReturnUrl == null ? null : lcStateReturnUrl.trim();
    }

    public String getLcStateNotifyUrl() {
        return lcStateNotifyUrl;
    }

    public void setLcStateNotifyUrl(String lcStateNotifyUrl) {
        this.lcStateNotifyUrl = lcStateNotifyUrl == null ? null : lcStateNotifyUrl.trim();
    }

    public String getLcOrderDetailUrl() {
        return lcOrderDetailUrl;
    }

    public void setLcOrderDetailUrl(String lcOrderDetailUrl) {
        this.lcOrderDetailUrl = lcOrderDetailUrl == null ? null : lcOrderDetailUrl.trim();
    }

    public String getThirdPartyCode() {
        return thirdPartyCode;
    }

    public void setThirdPartyCode(String thirdPartyCode) {
        this.thirdPartyCode = thirdPartyCode == null ? null : thirdPartyCode.trim();
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
    }

    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}
