/**
 * File: UserVendorAccountLoginResult.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年12月1日 上午11:02:56
 */
package com.cifpay.insurance.param.user;

import java.io.Serializable;

/**
 * 商户登录结果
 * 
 * @author 张均锋
 *
 */
public class UserVendorAccountLoginResult implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String vendorId;

	private String vendorName;

	private String userAccount;

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

}
