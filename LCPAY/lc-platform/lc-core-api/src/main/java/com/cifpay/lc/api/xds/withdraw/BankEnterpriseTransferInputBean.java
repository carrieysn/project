package com.cifpay.lc.api.xds.withdraw;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 银企互联转账服务接口输入参数。
 * 
 * 
 *
 */
public class BankEnterpriseTransferInputBean implements Serializable {
	private static final long serialVersionUID = -5447926543737726938L;
	private String requestId;
	private Date requestTime;
	private String merId;
	private String sign;

	private String orderId;
	private String orderDesc;
	private BigDecimal amount;
	private String currency;
	private String payerBankCode;
	private String payerBankAcctNo;
	private String payerMobile;
	private String userCode;
	private String payeeBankCode;
	private String payeeBankAcctNo;
	private String payeeName;
	private String payeeMobile;
	private String payeeAcctType;
	private String payeeBankName;
	private String payeeCityName;
	private String noticeUrl;

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public Date getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}

	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderDesc() {
		return orderDesc;
	}

	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getPayerBankCode() {
		return payerBankCode;
	}

	public void setPayerBankCode(String payerBankCode) {
		this.payerBankCode = payerBankCode;
	}

	public String getPayerBankAcctNo() {
		return payerBankAcctNo;
	}

	public void setPayerBankAcctNo(String payerBankAcctNo) {
		this.payerBankAcctNo = payerBankAcctNo;
	}

	public String getPayerMobile() {
		return payerMobile;
	}

	public void setPayerMobile(String payerMobile) {
		this.payerMobile = payerMobile;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getPayeeBankCode() {
		return payeeBankCode;
	}

	public void setPayeeBankCode(String payeeBankCode) {
		this.payeeBankCode = payeeBankCode;
	}

	public String getPayeeBankAcctNo() {
		return payeeBankAcctNo;
	}

	public void setPayeeBankAcctNo(String payeeBankAcctNo) {
		this.payeeBankAcctNo = payeeBankAcctNo;
	}

	public String getPayeeName() {
		return payeeName;
	}

	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}

	public String getPayeeMobile() {
		return payeeMobile;
	}

	public void setPayeeMobile(String payeeMobile) {
		this.payeeMobile = payeeMobile;
	}

	public String getPayeeAcctType() {
		return payeeAcctType;
	}

	public void setPayeeAcctType(String payeeAcctType) {
		this.payeeAcctType = payeeAcctType;
	}

	public String getPayeeBankName() {
		return payeeBankName;
	}

	public void setPayeeBankName(String payeeBankName) {
		this.payeeBankName = payeeBankName;
	}

	public String getPayeeCityName() {
		return payeeCityName;
	}

	public void setPayeeCityName(String payeeCityName) {
		this.payeeCityName = payeeCityName;
	}

	public String getNoticeUrl() {
		return noticeUrl;
	}

	public void setNoticeUrl(String noticeUrl) {
		this.noticeUrl = noticeUrl;
	}

}
