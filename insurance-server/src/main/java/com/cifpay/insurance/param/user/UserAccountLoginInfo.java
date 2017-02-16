/**
 * File: UserAccountLoginInfo.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年12月1日 上午11:03:25
 */
package com.cifpay.insurance.param.user;

import java.io.Serializable;

/**
 * 登录商户信息
 * 
 * @author 张均锋
 *
 */
public class UserAccountLoginInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userAccount;

	private String password;

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
