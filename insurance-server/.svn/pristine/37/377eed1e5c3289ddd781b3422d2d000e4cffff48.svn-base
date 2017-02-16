/**
 * File: InsuranceCertStatusEnum.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年12月2日 上午11:39:57
 */
package com.cifpay.insurance;

/**
 * 保险证状态枚举
 * 
 * @author 张均锋
 *
 */
public enum InsuranceCertStatusEnum {
	/** 未生效(新增）**/
	NOT_EFFECTIVED(0, "未生效"),
	/** 已签收(生效) **/
	//SIGNED(1, "已签收"),
	/** 生效(正常) **/
	EFFECTIVE(2, "生效(正常)"),
	/** 待开证**/
	//TO_OPEN(5, "待开证"),
	/** 待退款 **/
	TO_REFUND(10, "待退款"),
	/** 退款中**/
	/*IN_REFUND(11, "退款中"),*/
	/** 已退款 **/
	FINISH_REFUND(12, "已退款"),
	/** 待协商退款 **/
	/*CONSUIT_REFUND(13, "待协商退款"),*/
	/** 拒绝退款； **/
	REFUSE_REFUND(14, "拒绝退款"),
	/** 已失效 **/
	EXPIRED(100, "已失效");

	public final int val;
	public final String desc;

	InsuranceCertStatusEnum(int val, String desc) {
		this.val = val;
		this.desc = desc;
	}
	
	/**
	 * 据指定值，转换成枚举
	 * 
	 * @param val
	 * @return
	 */
	public static InsuranceCertStatusEnum toEnum(int val) {
		for (InsuranceCertStatusEnum i: values()) {
			if (i.val == val) return i;
		}
		return null;
	}
}
