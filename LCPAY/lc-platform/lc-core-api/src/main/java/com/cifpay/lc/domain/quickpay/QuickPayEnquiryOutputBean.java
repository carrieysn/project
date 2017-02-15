package com.cifpay.lc.domain.quickpay;

import java.io.Serializable;

import com.cifpay.lc.constant.enums.LcStatusType;

/**
 * 
 *
 */
public class QuickPayEnquiryOutputBean implements Serializable {
	private static final long serialVersionUID = 6627663985835012205L;
	private String orderNo;
	private String lcId;
	private LcStatusType lcStatus;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getLcId() {
		return lcId;
	}

	public void setLcId(String lcId) {
		this.lcId = lcId;
	}

	public LcStatusType getLcStatus() {
		return lcStatus;
	}

	public void setLcStatus(LcStatusType lcStatus) {
		this.lcStatus = lcStatus;
	}

}
