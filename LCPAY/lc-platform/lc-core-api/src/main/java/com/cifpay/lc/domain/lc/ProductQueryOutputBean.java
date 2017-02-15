package com.cifpay.lc.domain.lc;

import java.io.Serializable;
import java.util.Date;

public class ProductQueryOutputBean implements Serializable {

	private static final long serialVersionUID = -5852183935656894770L;

	private Long productId;

    private String productCode;

    private String productName;

    private String productDescription;

    private String lcType;

    private String lcCurrency;

    private String payerType;

    private String recvType;

    private String thirdPartyType;

    private String thirdPartyCode;

    private Boolean allowMultiple;

    private Boolean allowPartialPay;

    private Boolean isAutoRecv;

    private Boolean isAutoSend;

    private Boolean isAutoConfirm;

    private Boolean isAutoPay;

    private Boolean isAutoInvalid;

    private Date expiredTime;

    private Boolean isValid;

    private Date createTime;

    private Date updateTime;

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
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getLcType() {
		return lcType;
	}

	public void setLcType(String lcType) {
		this.lcType = lcType;
	}

	public String getLcCurrency() {
		return lcCurrency;
	}

	public void setLcCurrency(String lcCurrency) {
		this.lcCurrency = lcCurrency;
	}

	public String getPayerType() {
		return payerType;
	}

	public void setPayerType(String payerType) {
		this.payerType = payerType;
	}

	public String getRecvType() {
		return recvType;
	}

	public void setRecvType(String recvType) {
		this.recvType = recvType;
	}

	public String getThirdPartyType() {
		return thirdPartyType;
	}

	public void setThirdPartyType(String thirdPartyType) {
		this.thirdPartyType = thirdPartyType;
	}

	public String getThirdPartyCode() {
		return thirdPartyCode;
	}

	public void setThirdPartyCode(String thirdPartyCode) {
		this.thirdPartyCode = thirdPartyCode;
	}

	public Boolean getAllowMultiple() {
		return allowMultiple;
	}

	public void setAllowMultiple(Boolean allowMultiple) {
		this.allowMultiple = allowMultiple;
	}

	public Boolean getAllowPartialPay() {
		return allowPartialPay;
	}

	public void setAllowPartialPay(Boolean allowPartialPay) {
		this.allowPartialPay = allowPartialPay;
	}

	public Boolean getIsAutoRecv() {
		return isAutoRecv;
	}

	public void setIsAutoRecv(Boolean isAutoRecv) {
		this.isAutoRecv = isAutoRecv;
	}

	public Boolean getIsAutoSend() {
		return isAutoSend;
	}

	public void setIsAutoSend(Boolean isAutoSend) {
		this.isAutoSend = isAutoSend;
	}

	public Boolean getIsAutoConfirm() {
		return isAutoConfirm;
	}

	public void setIsAutoConfirm(Boolean isAutoConfirm) {
		this.isAutoConfirm = isAutoConfirm;
	}

	public Boolean getIsAutoPay() {
		return isAutoPay;
	}

	public void setIsAutoPay(Boolean isAutoPay) {
		this.isAutoPay = isAutoPay;
	}

	public Boolean getIsAutoInvalid() {
		return isAutoInvalid;
	}

	public void setIsAutoInvalid(Boolean isAutoInvalid) {
		this.isAutoInvalid = isAutoInvalid;
	}

	public Date getExpiredTime() {
		return expiredTime;
	}

	public void setExpiredTime(Date expiredTime) {
		this.expiredTime = expiredTime;
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
}
