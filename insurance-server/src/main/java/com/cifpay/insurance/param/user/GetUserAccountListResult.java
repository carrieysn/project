package com.cifpay.insurance.param.user;

import java.io.Serializable;

public class GetUserAccountListResult implements Serializable {

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
	/** 是否启用（1是；0否） **/
	private Integer isEnable;
	/** 是否有效（1是；0否） **/
	private Integer isValid;
	/** 是否超级管理员（1是；0否） **/
	private Integer isAdmin;
	
	
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
	public Integer getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}

}
