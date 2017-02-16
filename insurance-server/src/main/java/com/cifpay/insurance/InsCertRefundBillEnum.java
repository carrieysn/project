/**
 * File: InsCertRefundBillEnum.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年12月16日 下午5:15:29
 */
package com.cifpay.insurance;

/**
 * 退款申请状态。
 * 
 * @author 张均锋
 *
 */
public enum InsCertRefundBillEnum {
	/** 待开证**/
	TO_OPEN(-1, "待开证"),
	/** 待退款**/
	TO_REFUND(0, "待退款"),
	/** 退款中 **/
	IN_REFUND(1, "退款中"),
	/** 退款成功 **/
	REFUND_SUCCESS(2, "退款成功"),
	/** 退款失败 **/
	REFUND_FAIL(9, "退款失败"),
	/** 拒绝退款 **/
	REFUSE_REFUND(10, "拒绝退款"),
	/** 开证失败 **/
	OPEN_FAIL(20, "开证失败");

	public final int val;
	public final String desc;

	InsCertRefundBillEnum(int val, String desc) {
		this.val = val;
		this.desc = desc;
	}
	
	/**
	 * 据指定值，转换成枚举
	 * 
	 * @param val
	 * @return
	 */
	public static InsCertRefundBillEnum toEnum(int val) {
		for (InsCertRefundBillEnum i: values()) {
			if (i.val == val) return i;
		}
		return null;
	}
}
