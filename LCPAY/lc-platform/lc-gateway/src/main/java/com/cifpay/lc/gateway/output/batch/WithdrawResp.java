package com.cifpay.lc.gateway.output.batch;

public class WithdrawResp {

	private String lcId;			// 银信证ID
	private String lcStatus; 		// 银信证状态
	private String lcStatusDesc; 	// 银信证状态描述

	private String lcAppointmentId; // 履约ID
	private String lcConfirmId; 	// 申请解付ID
	private String lcPayAmount; 	// 申请解付金额

	public String getLcAppointmentId() {
		return lcAppointmentId;
	}

	public void setLcAppointmentId(String lcAppointmentId) {
		this.lcAppointmentId = lcAppointmentId;
	}

	public String getLcConfirmId() {
		return lcConfirmId;
	}

	public void setLcConfirmId(String lcConfirmId) {
		this.lcConfirmId = lcConfirmId;
	}

	public String getLcPayAmount() {
		return lcPayAmount;
	}

	public void setLcPayAmount(String lcPayAmount) {
		this.lcPayAmount = lcPayAmount;
	}

	public String getLcId() {
		return lcId;
	}

	public void setLcId(String lcId) {
		this.lcId = lcId;
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

}
