package com.cifpay.insurance.model;

import java.io.Serializable;

public class InsPolicyOrderItem implements Serializable {

	private static final long serialVersionUID = 19700101000000000L;
	/** id **/
	private Long id;
	/** 商户订单ID **/
	private Long policyOrderId;
	/** 投保人 **/
	private String holderName;
	/** 投保人类型（1-企业；2-个人） **/
	private Integer holderType;
	/** 证件类型（1：机构代码；2：身份证） **/
	private Integer idType;
	/** 证件号码 **/
	private String idNo;
	/** 联系人 **/
	private String contacts;
	/** 联系电话 **/
	private String phone;
	/** 邮箱 **/
	private String email;
	/** 险种名称 **/
	private String product;
	/** 被保人 **/
	private String insuredName;
	/** 保险期限 **/
	private Integer insurancePeriod;
	/** 保险标的 **/
	private String insuredid;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPolicyOrderId() {
		return policyOrderId;
	}

	public void setPolicyOrderId(Long policyOrderId) {
		this.policyOrderId = policyOrderId;
	}

	public String getHolderName() {
		return holderName;
	}

	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}

	public Integer getHolderType() {
		return holderType;
	}

	public void setHolderType(Integer holderType) {
		this.holderType = holderType;
	}

	public Integer getIdType() {
		return idType;
	}

	public void setIdType(Integer idType) {
		this.idType = idType;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getInsuredName() {
		return insuredName;
	}

	public void setInsuredName(String insuredName) {
		this.insuredName = insuredName;
	}

	public Integer getInsurancePeriod() {
		return insurancePeriod;
	}

	public void setInsurancePeriod(Integer insurancePeriod) {
		this.insurancePeriod = insurancePeriod;
	}

	public String getInsuredid() {
		return insuredid;
	}

	public void setInsuredid(String insuredid) {
		this.insuredid = insuredid;
	}

}
