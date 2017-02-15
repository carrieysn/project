package com.cifpay.lc.domain.bank;

import java.io.Serializable;

public class PaymentSuccessOutputBean implements Serializable {

	private static final long serialVersionUID = 743502754693837756L;

	private Long lcId; 			// 银信证ID
	private String lcStatus;
	private String lcStatusDesc;
	private String bankSerialNo; 	// 银行交易流水

	private String orderId;
	private String mid;
	private String returnUrl; 	// 返回地址

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	public Long getLcId() {
		return lcId;
	}

	public void setLcId(Long lcId) {
		this.lcId = lcId;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getLcStatus() {
		return lcStatus;
	}

	public void setLcStatus(String lcStatus) {
		this.lcStatus = lcStatus;
	}

	public String getLcStatusDesc() {
		return lcStatusDesc;
	}

	public void setLcStatusDesc(String lcStatusDesc) {
		this.lcStatusDesc = lcStatusDesc;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getBankSerialNo() {
		return bankSerialNo;
	}

	public void setBankSerialNo(String bankSerialNo) {
		this.bankSerialNo = bankSerialNo;
	}

}
