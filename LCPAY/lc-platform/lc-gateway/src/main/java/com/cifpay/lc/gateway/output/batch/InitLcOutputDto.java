package com.cifpay.lc.gateway.output.batch;

public class InitLcOutputDto {

	private String lcId;// 银信证ID
	private String lcNo; // 银信证编号
	private String lcType; // 银信证类型

	private String lcAmount; // 银信证开证金额（单位分）
	private String currency;// 银信证币种

	// private String channel; // 支付渠道

	private String payerBankName;// 付款人银行名称
	private String payerBankCode;// 付款人银行代码
	private String payerBankAccountNo;// 付款人账号

	private String recvBankName; // 收款人银行名称
	private String recvBankCode;// 收款人银行代码
	private String recvBankAccountNo;// 收款人账号

	private String orderId;// 商户订单号
	private String mrchOrderUrl;// 商户订单详情地址

	private String recvValidTime;// 收证到期时间
	private String sendValidTime;// 履约到期时间
	private String confirmPayValidTime;// 申请收款到期时间
	private String payValidTime;// 银行划款时间

	public String getLcId() {
		return lcId;
	}

	public void setLcId(String lcId) {
		this.lcId = lcId;
	}

	public String getLcNo() {
		return lcNo;
	}

	public void setLcNo(String lcNo) {
		this.lcNo = lcNo;
	}

	public String getLcType() {
		return lcType;
	}

	public void setLcType(String lcType) {
		this.lcType = lcType;
	}

	public String getLcAmount() {
		return lcAmount;
	}

	public void setLcAmount(String lcAmount) {
		this.lcAmount = lcAmount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getPayerBankName() {
		return payerBankName;
	}

	public void setPayerBankName(String payerBankName) {
		this.payerBankName = payerBankName;
	}

	public String getPayerBankCode() {
		return payerBankCode;
	}

	public void setPayerBankCode(String payerBankCode) {
		this.payerBankCode = payerBankCode;
	}

	public String getPayerBankAccountNo() {
		return payerBankAccountNo;
	}

	public void setPayerBankAccountNo(String payerBankAccountNo) {
		this.payerBankAccountNo = payerBankAccountNo;
	}

	public String getRecvBankName() {
		return recvBankName;
	}

	public void setRecvBankName(String recvBankName) {
		this.recvBankName = recvBankName;
	}

	public String getRecvBankCode() {
		return recvBankCode;
	}

	public void setRecvBankCode(String recvBankCode) {
		this.recvBankCode = recvBankCode;
	}

	public String getRecvBankAccountNo() {
		return recvBankAccountNo;
	}

	public void setRecvBankAccountNo(String recvBankAccountNo) {
		this.recvBankAccountNo = recvBankAccountNo;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getMrchOrderUrl() {
		return mrchOrderUrl;
	}

	public void setMrchOrderUrl(String mrchOrderUrl) {
		this.mrchOrderUrl = mrchOrderUrl;
	}

	public String getRecvValidTime() {
		return recvValidTime;
	}

	public void setRecvValidTime(String recvValidTime) {
		this.recvValidTime = recvValidTime;
	}

	public String getSendValidTime() {
		return sendValidTime;
	}

	public void setSendValidTime(String sendValidTime) {
		this.sendValidTime = sendValidTime;
	}

	public String getConfirmPayValidTime() {
		return confirmPayValidTime;
	}

	public void setConfirmPayValidTime(String confirmPayValidTime) {
		this.confirmPayValidTime = confirmPayValidTime;
	}

	public String getPayValidTime() {
		return payValidTime;
	}

	public void setPayValidTime(String payValidTime) {
		this.payValidTime = payValidTime;
	}
}
