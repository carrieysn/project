/**
 * File: RefundCertInfo.java
 *
 * Copyright：Copyright (c) 2016
 * Company：深圳市银信网银科技有限公司
 * Created on：2016年1月5日 上午10:28:56
 */
package com.cifpay.insurance.param.msg;

/**
 * 所有待退款信息
 * 
 * @author 张均锋
 *
 */
public class RefundCertInfo {

	/** 所有待退款数量 **/
	private long allToRefundCertCount;
	/** 所有待退款金额 **/
	private long allToRefundAmount;

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
