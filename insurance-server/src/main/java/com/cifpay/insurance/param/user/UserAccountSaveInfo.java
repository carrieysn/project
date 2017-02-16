package com.cifpay.insurance.param.user;

import java.io.Serializable;
import java.util.Date;
/**
 * 新增用户
 * 
 * @author 叶胜南
 *
 */
public class UserAccountSaveInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
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
	/** 是否超级管理员（1是；0否） **/
	private Integer isAdmin;
	/** 创建人 **/
	private String createdUser;
	/** 创建时间 **/
	private Date createdTime;
	/** 修改人 **/
	private String modifiedUser;
	/** 修改时间 **/
	private Date modifiedTime;
	
	

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
	public Integer getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}
	public String getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public String getModifiedUser() {
		return modifiedUser;
	}
	public void setModifiedUser(String modifiedUser) {
		this.modifiedUser = modifiedUser;
	}
	public Date getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	} 
	
	

}
