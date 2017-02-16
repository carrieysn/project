/**
 * File: PolicyStatusEnum.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年12月8日 下午3:35:09
 */
package com.cifpay.insurance;

/**
 * 保单状态。
 * 
 * @author 张均锋
 *
 */
public enum PolicyStatusEnum {

	/** 未生效**/
	NOT_EFFECTIVED(0, "未生效"),
	/** 正常 **/
	NORMAL(10, "正常"),
	/** 预警 **/
	PRE_WARN(11, "预警"),
	/** 警报 **/
	WARNING(12, "警报");

	public final int val;
	public final String desc;

	PolicyStatusEnum(int val, String desc) {
		this.val = val;
		this.desc = desc;
	}
	
	/**
	 * 据指定值，转换成枚举
	 * 
	 * @param val
	 * @return
	 */
	public static PolicyStatusEnum toEnum(int val) {
		for (PolicyStatusEnum i: values()) {
			if (i.val == val) return i;
		}
		return null;
	}
}
