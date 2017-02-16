/**
 * File: PolicyOrderPayInfo.java
 *
 * Copyright：Copyright (c) 2016
 * Company：深圳市银信网银科技有限公司
 * Created on：2016年1月5日 下午9:02:09
 */
package com.cifpay.insurance.param.policy;

import java.io.Serializable;

/**
 * 商户订单支付信息
 * 
 * @author 张均锋
 *
 */
public class PolicyOrderPayInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 充值订单号 **/
	private String orderNo;
	/** 银行编码 **/
	private String bankCode;
	/** 付款人手机号 **/
	private String mobilePhone;
	/** 支付金额 **/
	private long premium;
	/** 商户返回地址 **/
	private String returnUrl;
	/** 商户跳转地址 **/
	private String mrchOrderUrl;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public long getPremium() {
		return premium;
	}

	public void setPremium(long premium) {
		this.premium = premium;
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	public String getMrchOrderUrl() {
		return mrchOrderUrl;
	}

	public void setMrchOrderUrl(String mrchOrderUrl) {
		this.mrchOrderUrl = mrchOrderUrl;
	}

}
