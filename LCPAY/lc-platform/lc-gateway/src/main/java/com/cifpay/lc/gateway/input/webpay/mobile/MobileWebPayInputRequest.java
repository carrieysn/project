package com.cifpay.lc.gateway.input.webpay.mobile;

/**
 * 
 *
 */
public class MobileWebPayInputRequest {
	private String merchantId;
	private String orderNo;
	private String tradeName;
	private String amount;
	private String currency;
	private String cBankAccountNo;
	private String cBankCode;
	private String mBankAccountNo;
	private String mBankAccountName;
	private String mBankCode;
	private String mobile;
	private String lastConfirmTime; // yyyy-MM-dd HH:mm:ss
	private String returnUrl;
	private String notifyUrl;

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

	public String getTradeName() {
		return tradeName;
	}

	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getcBankAccountNo() {
		return cBankAccountNo;
	}

	public void setcBankAccountNo(String cBankAccountNo) {
		this.cBankAccountNo = cBankAccountNo;
	}

	public String getcBankCode() {
		return cBankCode;
	}

	public void setcBankCode(String cBankCode) {
		this.cBankCode = cBankCode;
	}

	public String getmBankAccountNo() {
		return mBankAccountNo;
	}

	public void setmBankAccountNo(String mBankAccountNo) {
		this.mBankAccountNo = mBankAccountNo;
	}

	public String getmBankAccountName() {
		return mBankAccountName;
	}

	public void setmBankAccountName(String mBankAccountName) {
		this.mBankAccountName = mBankAccountName;
	}

	public String getmBankCode() {
		return mBankCode;
	}

	public void setmBankCode(String mBankCode) {
		this.mBankCode = mBankCode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getLastConfirmTime() {
		return lastConfirmTime;
	}

	public void setLastConfirmTime(String lastConfirmTime) {
		this.lastConfirmTime = lastConfirmTime;
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

}
