package com.cifpay.lc.domain.quickpay;

import java.io.Serializable;

import com.cifpay.lc.constant.enums.LcStatusType;

/**
 * 
 *
 */
public class QuickPayTradeOutputBean implements Serializable {
	private static final long serialVersionUID = -930614036356890105L;
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
