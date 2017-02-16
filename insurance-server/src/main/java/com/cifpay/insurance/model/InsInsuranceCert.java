package com.cifpay.insurance.model;

import java.io.Serializable;
import java.util.Date;

public class InsInsuranceCert implements Serializable {

	private static final long serialVersionUID = 19700101000000000L;
	private Long id;
	/** 保单ID **/
	private Long policyId;
	/** 保单号 **/
	private String policyNo;
	/** 商户号 **/
	private String vendorId;
	/** 保险证号 **/
	private String insuranceCertNo;
	/** 生效时间 **/
	private Date effectiveTime;
	/** 有效期限（天） **/
	private Integer effectivePeriod;
	/** 过期时间 **/
	private Date expiredTime;
	/** 状态（0-未生效；2-生效（正常）；10-待退款；12-已退款；14-拒绝退款；100-已失效） **/
	private Integer status;
	/** 是否签收（1-是；0-否） **/
	private Integer isSign;
	/** 签收时间 **/
	private Date signTime;
	/** 商品总价（分） **/
	private Long totalPrice;
	/** 后台通知URL **/
	private String noticeUrl;
	/** 退货时间 **/
	private Date returnDate;
	/** 是否保险公司退款（1-是；0-否） **/
	private Integer isIcRefund;
	/** 是否开证（1-是；0-否） **/
	private Integer isLcOpen;
	/** 版本号 **/
	private Integer version;
	/** 创建时间 **/
	private Date createdTime;
	/** 修改时间 **/
	private Date modifiedTime;
	/** 商品销售订单信息 **/
	private InsSalesOrderItems insSalesOrderItems;
	/** 保单信息 **/
	private InsPolicy insPolicy;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPolicyId() {
		return policyId;
	}

	public void setPolicyId(Long policyId) {
		this.policyId = policyId;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public String getInsuranceCertNo() {
		return insuranceCertNo;
	}

	public void setInsuranceCertNo(String insuranceCertNo) {
		this.insuranceCertNo = insuranceCertNo;
	}

	public Date getEffectiveTime() {
		return effectiveTime;
	}

	public void setEffectiveTime(Date effectiveTime) {
		this.effectiveTime = effectiveTime;
	}

	public Integer getEffectivePeriod() {
		return effectivePeriod;
	}

	public void setEffectivePeriod(Integer effectivePeriod) {
		this.effectivePeriod = effectivePeriod;
	}

	public Date getExpiredTime() {
		return expiredTime;
	}

	public void setExpiredTime(Date expiredTime) {
		this.expiredTime = expiredTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsSign() {
		return isSign;
	}

	public void setIsSign(Integer isSign) {
		this.isSign = isSign;
	}

	public Date getSignTime() {
		return signTime;
	}

	public void setSignTime(Date signTime) {
		this.signTime = signTime;
	}

	public Long getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Long totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getNoticeUrl() {
		return noticeUrl;
	}

	public void setNoticeUrl(String noticeUrl) {
		this.noticeUrl = noticeUrl;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public Integer getIsIcRefund() {
		return isIcRefund;
	}

	public void setIsIcRefund(Integer isIcRefund) {
		this.isIcRefund = isIcRefund;
	}

	public Integer getIsLcOpen() {
		return isLcOpen;
	}

	public void setIsLcOpen(Integer isLcOpen) {
		this.isLcOpen = isLcOpen;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public InsSalesOrderItems getInsSalesOrderItems() {
		return insSalesOrderItems;
	}

	public void setInsSalesOrderItems(InsSalesOrderItems insSalesOrderItems) {
		this.insSalesOrderItems = insSalesOrderItems;
	}

	public InsPolicy getInsPolicy() {
		return insPolicy;
	}

	public void setInsPolicy(InsPolicy insPolicy) {
		this.insPolicy = insPolicy;
	}

}
