/**
 * File: LcStateEnum.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年12月17日 上午10:12:43
 */
package com.cifpay.insurance;

/**
 * 银信证状态枚举
 * 
 * @author 张均锋
 *
 */
public enum LcStateEnum {

	/** 待开证 **/
	CREDIT_TO_OPEN("CREDIT_TO_OPEN", "待开证"),
	/** 已开证 **/
	CREDIT_OPENED("CREDIT_OPENED", "已开证"),
	/** 已收证 **/
	CREDIT_RECEIVED("CREDIT_RECEIVED", "已收证"),
	/** 部分解付 **/
	CREDIT_PART_PAYED("CREDIT_PART_PAYED", "部分解付"),
	/** 全额解付 **/
	CREDIT_PAYED("CREDIT_PAYED", "全额解付"),
	/** 已退回 **/
	CREDIT_SEND_BACK("CREDIT_SEND_BACK", "已退回"),
	/** 已撤回 **/
	CREDIT_REVOKE("CREDIT_REVOKE", "已撤回"),
	/** 部分失效 **/
	CREDIT_PART_INVALID("CREDIT_PART_INVALID", "部分失效"),
	/** 失效 **/
	CREDIT_INVALID("CREDIT_INVALID", "失效");

	public final String val;
	public final String desc;

	LcStateEnum(String val, String desc) {
		this.val = val;
		this.desc = desc;
	}

	/**
	 * 据指定值，转换成枚举
	 * 
	 * @param val
	 * @return
	 */
	public static LcStateEnum toEnum(String val) {
		if (val == null) return null;
		for (LcStateEnum i : values()) {
			if (i.val.equals(val))
				return i;
		}
		return null;
	}

}
