package com.cifpay.lc.domain.quickpay;

import java.io.Serializable;

/**
 * 
 *
 */
public class QuickPaySmsTokenApplyOutputBean implements Serializable {
	private static final long serialVersionUID = -7572708923811987180L;
	private String orderNo;
	private String status;
	private String smsCode;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSmsCode() {
		return smsCode;
	}

	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}

}
