/**
 * File: PolicyChangeListTypeEnum.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年12月8日 上午11:32:56
 */
package com.cifpay.insurance;

/**
 * 保单保额变动流水类型
 * 
 * @author 张均锋
 *
 */
public enum PolicyChangeListTypeEnum {
	
	/** 保险证生成 **/
	ADD_CERT(1, "生成"),
	/** 商户退款 **/
	VENDOR_REFUND_CERT(2, "商户退款成功"),
	/** 保险公司退款 **/
	IC_REFUND_CERT(3, "保险理赔"),
	/** 保险证失效 **/
	EXPIRED_CERT(4, "失效"),
	/** 保费充值 **/
	CHARGE_PREMIUM(5, "充值"),
	/** 杠杆调整**/
	ADJUST_GEARING(6, "杠杆调整"),
	/** 拒绝退款**/
	REFUSE_REFUND(9, "拒绝退款");

	public final int val;
	public final String desc;

	PolicyChangeListTypeEnum(int val, String desc) {
		this.val = val;
		this.desc = desc;
	}
	
	/**
	 * 据指定值，转换成枚举
	 * 
	 * @param val
	 * @return
	 */
	public static PolicyChangeListTypeEnum toEnum(int val) {
		for (PolicyChangeListTypeEnum i: values()) {
			if (i.val == val) return i;
		}
		return null;
	}
}
