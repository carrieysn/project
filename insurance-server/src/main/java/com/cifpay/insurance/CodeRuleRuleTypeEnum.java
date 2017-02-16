/**
 * File: CodeRuleRuleTypeEnum.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年12月10日 下午2:59:56
 */
package com.cifpay.insurance;

/**
 * 
 * @author 张均锋
 *
 */
public enum CodeRuleRuleTypeEnum {
	
	/** 变量 **/
	PROPERTY(0, "变量"),
	/** 时间 **/
	TIME(1, "时间"),
	/** 26字母组合 **/
	ALPHABET(2, "26字母组合"),
	/** 常量 **/
	CONSTANT(3, "常量"),
	/** 流水号 **/
	SERIAL_NO(4, "流水号");

	public final int val;
	public final String desc;

	CodeRuleRuleTypeEnum(int val, String desc) {
		this.val = val;
		this.desc = desc;
	}
	
	/**
	 * 据指定值，转换成枚举
	 * 
	 * @param val
	 * @return
	 */
	public static CodeRuleRuleTypeEnum toEnum(int val) {
		for (CodeRuleRuleTypeEnum i: values()) {
			if (i.val == val) return i;
		}
		return null;
	}

}
