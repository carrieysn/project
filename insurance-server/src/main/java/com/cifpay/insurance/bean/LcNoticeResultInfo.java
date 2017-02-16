/**
 * File: LcNoticeResultInfo.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年12月17日 上午9:43:37
 */
package com.cifpay.insurance.bean;

/**
 * 银信证通知结果信息
 * 
 * @author 张均锋
 *
 */
public class LcNoticeResultInfo {
 
	/** 请求Id **/
	private String requestId;
	/** 银信证流水号 **/
	private String lcId;
	/** 订单号 **/
	private String orderId;
	/** 银信证编号 **/
	private String lcNo;
	/** 银信证状态 **/
	private String lcState;
	/** 订单状态 **/
	private String orderState;
	/** 银行交易时间 **/
	private String tradeDate;

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getLcId() {
		return lcId;
	}

	public void setLcId(String lcId) {
		this.lcId = lcId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
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

	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	public String getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}

}
