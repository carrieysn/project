/**
 * File: ReturnCertInfo.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年12月1日 上午9:38:23
 */
package com.cifpay.insurance.param.msg;

/**
 * 退货消息　
 * 
 * @author 张均锋
 *
 */
public class ReturnCertInfo {
	/**今日退货数量 **/
	private long todayReturnCertCount;
    /** 今日退货金额 **/
	private long todayAmount;
    /** 所有待退款数量 **/
	private long allToRefundCertCount;
	/** 所有待退款金额 **/
	private long allToRefundAmount;

	public long getTodayReturnCertCount() {
		return todayReturnCertCount;
	}

	public void setTodayReturnCertCount(long todayReturnCertCount) {
		this.todayReturnCertCount = todayReturnCertCount;
	}

	public long getTodayAmount() {
		return todayAmount;
	}

	public void setTodayAmount(long todayAmount) {
		this.todayAmount = todayAmount;
	}

	public long getAllToRefundCertCount() {
		return allToRefundCertCount;
	}

	public void setAllToRefundCertCount(long allToRefundCertCount) {
		this.allToRefundCertCount = allToRefundCertCount;
	}

	public long getAllToRefundAmount() {
		return allToRefundAmount;
	}

	public void setAllToRefundAmount(long allToRefundAmount) {
		this.allToRefundAmount = allToRefundAmount;
	}

}
