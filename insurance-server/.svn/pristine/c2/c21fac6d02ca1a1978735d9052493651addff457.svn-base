/**
 * File: PolicyOrderStatusEnum.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年12月10日 上午9:19:51
 */
package com.cifpay.insurance;

/**
 * 订单状态
 * 
 * @author 张均锋
 *
 */
public enum PolicyOrderStatusEnum {

	/** 未支付 **/
	UNPAY(0, "未支付"),
	/** 支付完成 **/
	PAY_FINISH(1, "支付完成");

	public final int val;
	public final String desc;

	PolicyOrderStatusEnum(int val, String desc) {
		this.val = val;
		this.desc = desc;
	}

	/**
	 * 据指定值，转换成枚举
	 * 
	 * @param val
	 * @return
	 */
	public static PolicyOrderStatusEnum toEnum(int val) {
		for (PolicyOrderStatusEnum i : values()) {
			if (i.val == val)
				return i;
		}
		return null;
	}
}
