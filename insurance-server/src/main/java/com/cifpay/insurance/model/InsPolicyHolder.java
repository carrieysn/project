package com.cifpay.insurance.model;

import java.io.Serializable;
import java.util.Date;

public class InsPolicyHolder implements Serializable {

private static final long serialVersionUID = 19700101000000000L;
private Long id;
/** 用户ID **/
private String vendorId;
/** 投保人 **/
private String holderName;
/** 投保人类型（1：机构代码证） **/
private Integer holderType;
/** 证件类型（1：个人；2：企业） **/
private Integer idType;
/** 证件号码 **/
private String idNo;
/** 联系人 **/
private String contacts;
/** 联系电话 **/
private String phone;
/** 邮箱 **/
private String email;
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
public String getVendorId() {
	return vendorId;
}
public void setVendorId(String vendorId) {
	this.vendorId = vendorId;
}
public String getHolderName() {
	return holderName;
}
public void setHolderName(String holderName) {
	this.holderName = holderName;
}
public Integer getHolderType() {
	return holderType;
}
public void setHolderType(Integer holderType) {
	this.holderType = holderType;
}
public Integer getIdType() {
	return idType;
}
public void setIdType(Integer idType) {
	this.idType = idType;
}
public String getIdNo() {
	return idNo;
}
public void setIdNo(String idNo) {
	this.idNo = idNo;
}
public String getContacts() {
	return contacts;
}
public void setContacts(String contacts) {
	this.contacts = contacts;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
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
