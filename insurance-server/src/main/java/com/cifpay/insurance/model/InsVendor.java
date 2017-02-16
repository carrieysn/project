package com.cifpay.insurance.model;

import java.io.Serializable;
import java.util.Date;

public class InsVendor implements Serializable {

	private static final long serialVersionUID = 19700101000000000L;
	/** id **/
	private Long id;
	/** 商户登录账号 **/
	private String loginAccount;
	/** 商户登录密码 **/
	private String loginPassword;
	/** 是否启用(1是；0否) **/
	private Integer isEnable;
	/** 注册日期 **/
	private Date regDate;
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

	public String getLoginAccount() {
		return loginAccount;
	}

	public void setLoginAccount(String loginAccount) {
		this.loginAccount = loginAccount;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public Integer getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Integer isEnable) {
		this.isEnable = isEnable;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
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
