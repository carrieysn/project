package com.cifpay.insurance.model;

import java.io.Serializable;
import java.util.Date;

public class InsGearingAdjustHis implements Serializable {

	private static final long serialVersionUID = 19700101000000000L;
	/** id **/
	private Long id;
	/** 保单Id **/
	private Long policyId;
	/** 调整时间 **/
	private Date adjusttime;
	/** 信用分 **/
	private Integer creditScore;
	/** 杠杆比例 **/
	private Integer gearing;
	/** 保费 **/
	private Long premium;
	/** 保额 **/
	private Long insuredAmount;
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

	public Long getPolicyId() {
		return policyId;
	}

	public void setPolicyId(Long policyId) {
		this.policyId = policyId;
	}

	public Date getAdjusttime() {
		return adjusttime;
	}

	public void setAdjusttime(Date adjusttime) {
		this.adjusttime = adjusttime;
	}

	public Integer getCreditScore() {
		return creditScore;
	}

	public void setCreditScore(Integer creditScore) {
		this.creditScore = creditScore;
	}

	public Integer getGearing() {
		return gearing;
	}

	public void setGearing(Integer gearing) {
		this.gearing = gearing;
	}

	public Long getPremium() {
		return premium;
	}

	public void setPremium(Long premium) {
		this.premium = premium;
	}

	public Long getInsuredAmount() {
		return insuredAmount;
	}

	public void setInsuredAmount(Long insuredAmount) {
		this.insuredAmount = insuredAmount;
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
