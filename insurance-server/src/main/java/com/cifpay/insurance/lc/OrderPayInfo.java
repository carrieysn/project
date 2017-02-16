/**
 * File: OrderPayInfo.java
 *
 * Copyright：Copyright (c) 2016
 * Company：深圳市银信网银科技有限公司
 * Created on：2016年1月22日 下午2:39:12
 */
package com.cifpay.insurance.lc;

/**
 * 跟单付款信息
 * 
 * @author 张均锋
 *
 */
public class OrderPayInfo {

	private String openBankCode;
	private String orderId;
	private Long amount;
	private String payerMobile;
	private String returnUrl;
	private String noticeUrl;
	private String mrchOrderUrl;
	private Long orderAmount;

	public String getOpenBankCode() {
		return openBankCode;
	}

	public void setOpenBankCode(String openBankCode) {
		this.openBankCode = openBankCode;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getPayerMobile() {
		return payerMobile;
	}

	public void setPayerMobile(String payerMobile) {
		this.payerMobile = payerMobile;
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	public String getNoticeUrl() {
		return noticeUrl;
	}

	public void setNoticeUrl(String noticeUrl) {
		this.noticeUrl = noticeUrl;
	}

	public String getMrchOrderUrl() {
		return mrchOrderUrl;
	}

	public void setMrchOrderUrl(String mrchOrderUrl) {
		this.mrchOrderUrl = mrchOrderUrl;
	}

	public Long getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(Long orderAmount) {
		this.orderAmount = orderAmount;
	}

}
