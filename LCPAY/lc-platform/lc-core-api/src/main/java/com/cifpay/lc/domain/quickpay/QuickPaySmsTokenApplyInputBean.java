package com.cifpay.lc.domain.quickpay;

import java.io.Serializable;

import com.cifpay.lc.constant.enums.CretificationType;
import com.cifpay.lc.constant.enums.LcCurrency;

/**
 * 
 *
 */
public class QuickPaySmsTokenApplyInputBean implements Serializable {
	private static final long serialVersionUID = -2765518757564623374L;
	private String merchantId;
	private String orderNo;
	private String tradeName;
	private Long amount;
	private LcCurrency currency;
	private String cBankAccountNo;
	private String cBankCode;
	private String mBankAccountNo;
	private String mBankCode;
	private String cBindBankPhone;
	private String cName;
	private CretificationType cretificationType;
	private String cretification;

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

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public LcCurrency getCurrency() {
		return currency;
	}

	public void setCurrency(LcCurrency currency) {
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

	public String getmBankCode() {
		return mBankCode;
	}

	public void setmBankCode(String mBankCode) {
		this.mBankCode = mBankCode;
	}

	public String getcBindBankPhone() {
		return cBindBankPhone;
	}

	public void setcBindBankPhone(String cBindBankPhone) {
		this.cBindBankPhone = cBindBankPhone;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public CretificationType getCretificationType() {
		return cretificationType;
	}

	public void setCretificationType(CretificationType cretificationType) {
		this.cretificationType = cretificationType;
	}

	public String getCretification() {
		return cretification;
	}

	public void setCretification(String cretification) {
		this.cretification = cretification;
	}

}
