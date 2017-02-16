package com.cifpay.insurance.bean;

import java.io.Serializable;

public class RemoteLoginInfo implements Serializable{
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
