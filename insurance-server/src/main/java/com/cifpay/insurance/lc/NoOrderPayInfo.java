/**
 * File: NoOrderPayInfo.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年12月18日 下午4:16:23
 */
package com.cifpay.insurance.lc;

/**
 * 空单开证付款信息
 * 
 * @author 张均锋
 *
 */
public class NoOrderPayInfo {
	private Long amount;
	private String payerBankCode;
	private String payerBankAcctno;
	private String payerAcctName;
	private String payerCardType;
	private String payerMobile;
	private String recvBankCode;
	private String recvBankAcctno;
	private String recvAcctName;
	private String recvMobile;

	private String noticeUrl;

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getPayerBankCode() {
		return payerBankCode;
	}

	public void setPayerBankCode(String payerBankCode) {
		this.payerBankCode = payerBankCode;
	}

	public String getPayerBankAcctno() {
		return payerBankAcctno;
	}

	public void setPayerBankAcctno(String payerBankAcctno) {
		this.payerBankAcctno = payerBankAcctno;
	}

	public String getPayerAcctName() {
		return payerAcctName;
	}

	public void setPayerAcctName(String payerAcctName) {
		this.payerAcctName = payerAcctName;
	}

	public String getPayerCardType() {
		return payerCardType;
	}

	public void setPayerCardType(String payerCardType) {
		this.payerCardType = payerCardType;
	}

	public String getPayerMobile() {
		return payerMobile;
	}

	public void setPayerMobile(String payerMobile) {
		this.payerMobile = payerMobile;
	}

	public String getRecvBankCode() {
		return recvBankCode;
	}

	public void setRecvBankCode(String recvBankCode) {
		this.recvBankCode = recvBankCode;
	}

	public String getRecvBankAcctno() {
		return recvBankAcctno;
	}

	public void setRecvBankAcctno(String recvBankAcctno) {
		this.recvBankAcctno = recvBankAcctno;
	}

	public String getRecvAcctName() {
		return recvAcctName;
	}

	public void setRecvAcctName(String recvAcctName) {
		this.recvAcctName = recvAcctName;
	}

	public String getRecvMobile() {
		return recvMobile;
	}

	public void setRecvMobile(String recvMobile) {
		this.recvMobile = recvMobile;
	}

	public String getNoticeUrl() {
		return noticeUrl;
	}

	public void setNoticeUrl(String noticeUrl) {
		this.noticeUrl = noticeUrl;
	}

}
