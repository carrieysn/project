package com.cifpay.lc.domain.quickpay;

import java.io.Serializable;

/**
 * 
 *
 */
public class QuickPayTradeInputBean implements Serializable {
	private static final long serialVersionUID = 1994176703892048026L;
	private String merchantId;
	private String orderNo;
	private String smsCode;
	private String cBindBankPhone;
	private String confirmFlag;

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getSmsCode() {
		return smsCode;
	}

	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}

	public String getcBindBankPhone() {
		return cBindBankPhone;
	}

	public void setcBindBankPhone(String cBindBankPhone) {
		this.cBindBankPhone = cBindBankPhone;
	}

	public String getConfirmFlag() {
		return confirmFlag;
	}

	public void setConfirmFlag(String confirmFlag) {
		this.confirmFlag = confirmFlag;
	}

}
