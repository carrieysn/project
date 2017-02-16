/**
 * File: InsurancePolicyInfo.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年11月25日 下午2:20:20
 */
package com.cifpay.insurance.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 保单信息
 * 
 * @author 张均锋
 *
 */
public class InsurancePolicyInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 保单流水号 **/
	private Long policyId;
	/** 保单号 **/
	private String policyNo;
	/** 险种名称 **/
	private String product;
	/** 被保人 **/
	private String insuredName;
	/** 投保人信息 **/
	private PolicyHolderInfo policyHolderInfo;
	/** 支付方式（1-银信证） **/
	private Integer payMode;
	/** 当前保费（分） **/
	private Long premium;
	/** 有效开始日期 **/
	private Date validFrom;
	/** 有效截止日期 **/
	private Date validTo;
	/** 当前保额（分） **/
	private Long insuredAmount;

	/** 保险人信息 **/
	private InsurerInfo insurerInfo;

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

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public PolicyHolderInfo getPolicyHolderInfo() {
		return policyHolderInfo;
	}

	public void setPolicyHolderInfo(PolicyHolderInfo policyHolderInfo) {
		this.policyHolderInfo = policyHolderInfo;
	}

	public Integer getPayMode() {
		return payMode;
	}

	public void setPayMode(Integer payMode) {
		this.payMode = payMode;
	}

	public Long getPremium() {
		return premium;
	}

	public void setPremium(Long premium) {
		this.premium = premium;
	}

	public Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public Date getValidTo() {
		return validTo;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

	public Long getInsuredAmount() {
		return insuredAmount;
	}

	public void setInsuredAmount(Long insuredAmount) {
		this.insuredAmount = insuredAmount;
	}

	public InsurerInfo getInsurerInfo() {
		return insurerInfo;
	}

	public void setInsurerInfo(InsurerInfo insurerInfo) {
		this.insurerInfo = insurerInfo;
	}

	public String getInsuredName() {
		return insuredName;
	}

	public void setInsuredName(String insuredName) {
		this.insuredName = insuredName;
	}
	

}
