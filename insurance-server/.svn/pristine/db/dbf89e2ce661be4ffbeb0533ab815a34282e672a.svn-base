/**
 * File: MsgTypeEnum.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年12月2日 下午4:29:57
 */
package com.cifpay.insurance.push;

/**
 * 消息类型
 * 
 * @author 张均锋
 *
 */
public enum MsgTypeEnum {
	/** 增加保费 **/
	ADDPREMINUM(1, "增加保费"),
	/** 生成保险证 **/
	ADD_CERT(2, "生成保险证"),
	/** 保险证失效 **/
	EXPIRED_CERT(3, "保险证失效"),
	/** 申请退货,只处理待退款状态的 **/
	RETURN_CERT(4, "申请退货"),
	/** 商户退款成功 **/
	REFUND_SUCCESS(5, "商户退款成功"),
	/** 保险公司赔付成功 **/
	INS_COMPENSATE_SUCCESS(6, "保险公司赔付成功"),
	/** 拒绝退款 **/
	REFUSE_REFUND(7, "拒绝退款");

	public final int val;
	public final String desc;

	MsgTypeEnum(int val, String desc) {
		this.val = val;
		this.desc = desc;
	}
}