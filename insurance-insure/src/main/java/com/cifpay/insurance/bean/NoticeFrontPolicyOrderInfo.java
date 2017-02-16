package com.cifpay.insurance.bean;

import java.io.Serializable;
import java.util.Date;

public class NoticeFrontPolicyOrderInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 订单号 **/
	private String billNo;
	/** 银信证流水号 **/
	private String lcId;
	/** 银信证编号 **/
	private String lcNo;
	/** 银信证状态 **/
	private String lcState;
	/** 银行交易时间 **/
	private Date tradeDate;
	
	public NoticeFrontPolicyOrderInfo(String billNo,String lcId,String lcNo,String lcState,Date tradeDate){
		this.billNo = billNo;
		this.lcId = lcId;
		this.lcNo = lcNo;
		this.lcState = lcState;
		this.tradeDate = tradeDate;
		
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

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

	public String getLcState() {
		return lcState;
	}

	public void setLcState(String lcState) {
		this.lcState = lcState;
	}

	public Date getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}

}