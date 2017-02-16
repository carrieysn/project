package com.cifpay.insurance.model;

import java.io.Serializable;
import java.util.Date;

public class InsInsuredAmountInfo implements Serializable {

	private static final long serialVersionUID = 19700101000000000L;
	/** id **/
	private Long id;
	/** 保单ID **/
	private Long policyId;
	/** 保额（分） **/
	private Long insuredAmount;
	/** 冻结金额（分） **/
	private Long frozenAmount;
	/** 余额（分） **/
	private Long balance;
	/** 操作时间 **/
	private Date optTime;
	/** 版本号 **/
	private Integer version;

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

	public Long getInsuredAmount() {
		return insuredAmount;
	}

	public void setInsuredAmount(Long insuredAmount) {
		this.insuredAmount = insuredAmount;
	}

	public Long getFrozenAmount() {
		return frozenAmount;
	}

	public void setFrozenAmount(Long frozenAmount) {
		this.frozenAmount = frozenAmount;
	}

	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}

	public Date getOptTime() {
		return optTime;
	}

	public void setOptTime(Date optTime) {
		this.optTime = optTime;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
