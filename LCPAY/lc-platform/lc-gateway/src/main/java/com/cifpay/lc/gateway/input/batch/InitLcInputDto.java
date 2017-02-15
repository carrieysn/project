package com.cifpay.lc.gateway.input.batch;

public class InitLcInputDto {

	// private String merId; // 商户号
	private String productCode; // 银信证产品代码
	private String orderId; // 商户订单号

	private String currency; // 银信证币种
	private String amount; // 银信证开证金额
	private String payType; // 解付方式：1:单次解付 2:多次解付

	private String recvValidSecond; // 收证失效时间（单位秒）
	private String sendValidSecond; // 履约失效时间（单位秒）
	private String confirmValidSecond; // 申请解付失效时间（单位秒）
	private String payValidSecond; // 执行解付到期时间（单位秒）

	// private String payerBankCode; // 付款方银行代码
	// private String payerBankAccountNo; // 付款人账号
	// private AccountPropertyType payerAccountType; // 付款人类型：1:个人 2:企业

	private String recvBankCode; // 收款方银行代码
	private String recvBankAccountNo; // 收款人账号
	private String recvAccountType; // 收款人类型：1:个人 2:企业
	private String recvMobile; // 收款人手机号

	private String merOrderUrl;// 商户订单详情地址
//	private String returnUrl; // 返回地址
//	private String noticeUrl; // 通知地址

	private String remark; // 备注

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getRecvValidSecond() {
		return recvValidSecond;
	}

	public void setRecvValidSecond(String recvValidSecond) {
		this.recvValidSecond = recvValidSecond;
	}

	public String getSendValidSecond() {
		return sendValidSecond;
	}

	public void setSendValidSecond(String sendValidSecond) {
		this.sendValidSecond = sendValidSecond;
	}

	public String getConfirmValidSecond() {
		return confirmValidSecond;
	}

	public void setConfirmValidSecond(String confirmValidSecond) {
		this.confirmValidSecond = confirmValidSecond;
	}

	public String getPayValidSecond() {
		return payValidSecond;
	}

	public void setPayValidSecond(String payValidSecond) {
		this.payValidSecond = payValidSecond;
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

	public String getRecvAccountType() {
		return recvAccountType;
	}

	public void setRecvAccountType(String recvAccountType) {
		this.recvAccountType = recvAccountType;
	}

	public String getMerOrderUrl() {
		return merOrderUrl;
	}

	public void setMerOrderUrl(String mrchOrderUrl) {
		this.merOrderUrl = mrchOrderUrl;
	}

//	public String getReturnUrl() {
//		return returnUrl;
//	}
//
//	public void setReturnUrl(String returnUrl) {
//		this.returnUrl = returnUrl;
//	}
//
//	public String getNoticeUrl() {
//		return noticeUrl;
//	}
//
//	public void setNoticeUrl(String noticeUrl) {
//		this.noticeUrl = noticeUrl;
//	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRecvMobile() {
		return recvMobile;
	}

	public void setRecvMobile(String recvMobile) {
		this.recvMobile = recvMobile;
	}
}
