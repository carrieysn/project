package com.cifpay.insurance.param.user;

import java.io.Serializable;

import com.cifpay.insurance.param.PageInfo;
/**
 * 用户管理传入查询参数对象
 * 
 * @author 叶胜南
 *
 */
public class GetUserAccountInfo extends PageInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** 用户账号 (备用)**/
	private String userAccount;

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	
	
	

}
