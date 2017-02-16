package com.cifpay.insurance.param.user;

import java.io.Serializable;

public class UserAccountUpdateInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** 用户id **/
	private Long id;
	/** 用户账号 **/
	private String userAccount;
	/** 用户名称 **/
	private String userName;
	/** 密码 **/
	private String password;
	/** 是否启用（1是；0否） **/
	private Integer isEnable;
	/** 是否有效（1是；0否） **/
	private Integer isValid;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getIsEnable() {
		return isEnable;
	}
	public void setIsEnable(Integer isEnable) {
		this.isEnable = isEnable;
	}
	public Integer getIsValid() {
		return isValid;
	}
	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

}
