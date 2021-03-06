/**
 * File: LcOrderStateEnum.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年12月18日 下午5:59:39
 */
package com.cifpay.insurance;

/**
 * 
 * @author 张均锋
 *
 */
public enum LcOrderStateEnum {

	/** 已开证 **/
	CREDIT_OPENED("CREDIT_OPENED", "已开证"),
	/** 已收证 **/
	CREDIT_RECEIVED("CREDIT_RECEIVED", "已收证"),
	/** 已履约 **/
	CREDIT_SEND("CREDIT_SEND", "已履约"),
	/** 已申请解付 **/
	CREDIT_CONFIRMED_PAY("CREDIT_CONFIRMED_PAY", "已申请解付"),
	/** 已解付 **/
	CREDIT_PAYED("CREDIT_PAYED", "已解付"),
	/** 已退回 **/
	CREDIT_SEND_BACK("CREDIT_SEND_BACK", "已退回"),
	/** 已撤回 **/
	CREDIT_REVOKE("CREDIT_REVOKE", "已撤回"),
	/** 失效 **/
	CREDIT_INVALID("CREDIT_INVALID", "失效");

	public final String val;
	public final String desc;

	LcOrderStateEnum(String val, String desc) {
		this.val = val;
		this.desc = desc;
	}

	/**
	 * 据指定值，转换成枚举
	 * 
	 * @param val
	 * @return
	 */
	public static LcOrderStateEnum toEnum(String val) {
		if (val == null) return null;
		for (LcOrderStateEnum i : LcOrderStateEnum.values()) {
			if (i.val.equals(val))
				return i;
		}
		return null;
	}

}
