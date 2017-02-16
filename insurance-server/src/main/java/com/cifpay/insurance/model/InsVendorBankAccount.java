package com.cifpay.insurance.model;

import java.io.Serializable;
import java.util.Date;

public class InsVendorBankAccount implements Serializable {

	private static final long serialVersionUID = 19700101000000000L;
	private Long id;
	/** 商户ID **/
	private String vendorId;
	/** 开户行 代码 **/
	private String bankCode;
	/** 开户行名称 **/
	private String bankName;
	/** 开户名 **/
	private String accountName;
	/** 银行账号 **/
	private String bankAccount;
	/** 预留手机号 **/
	private String reserveMobilePhone;
	/** 默认开证账户（0-否；1-是） **/
	private Integer isDefault;
	/** 是否有效（0-否；1-是） **/
	private Integer isValid;
	/** 创建时间 **/
	private Date createdTime;
	/** 修改时间 **/
	private Date modifiedTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getReserveMobilePhone() {
		return reserveMobilePhone;
	}

	public void setReserveMobilePhone(String reserveMobilePhone) {
		this.reserveMobilePhone = reserveMobilePhone;
	}

	public Integer getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
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

}
