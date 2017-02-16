/**
 * File: GetVendorBankAccountListResult.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年12月5日 下午2:45:54
 */
package com.cifpay.insurance.param.vendor;

import java.io.Serializable;

/**
 * 获取商户银行卡信息列表结果
 * 
 * @author 张均锋
 *
 */
public class GetVendorBankAccountListResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//private Long id;
	/** 开户行 **/
	private String bankName;
	/** 开户名 **/
	private String accountName;
	/** 银行账号 **/
	private String bankAccount;
	/** 预留手机号 **/
	private String reserveMobilePhone;
	/** 默认开证账户（0-否；1-是） **/
	private Integer isDefault;

	/*public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}*/

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

}
