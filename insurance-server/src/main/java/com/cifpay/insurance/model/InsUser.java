package com.cifpay.insurance.model;

import java.io.Serializable;
import java.util.Date;

public class InsUser implements Serializable {

	private static final long serialVersionUID = 19700101000000000L;
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
	/** 是否超级管理员（1是；0否） **/
	private Integer isAdmin;
	/** 是否有效（1是；0否） **/
	private Integer isValid;
	/** 备注 **/
	private String remark;
	/** 创建人 **/
	private String createdUser;
	/** 创建时间 **/
	private Date createdTime;
	/** 修改人 **/
	private String modifiedUser;
	/** 修改时间 **/
	private Date modifiedTime;

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

	public Integer getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
