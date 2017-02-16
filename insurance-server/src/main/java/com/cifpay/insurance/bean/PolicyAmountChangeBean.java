/**
 * File: PolicyAmountChangeBean.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年12月17日 下午1:37:55
 */
package com.cifpay.insurance.bean;

import java.io.Serializable;

import com.cifpay.insurance.PolicyChangeListTypeEnum;

/**
 * 保单金额变动对象
 * 
 * @author 张均锋
 *
 */
public class PolicyAmountChangeBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 商户号 **/
	private String vendorId;
	/** 引用ID **/
	private Long refId;
	/** 引用凭证号 **/
	private String refVoucherNo;
	/** 类型（1-生成；2-退款；3-失效；4-充值） **/
	private PolicyChangeListTypeEnum type;
	/** 变动金额（分） **/
	private Long changeAmount;

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public Long getRefId() {
		return refId;
	}

	public void setRefId(Long refId) {
		this.refId = refId;
	}

	public String getRefVoucherNo() {
		return refVoucherNo;
	}

	public void setRefVoucherNo(String refVoucherNo) {
		this.refVoucherNo = refVoucherNo;
	}

	public PolicyChangeListTypeEnum getType() {
		return type;
	}

	public void setType(PolicyChangeListTypeEnum type) {
		this.type = type;
	}

	public Long getChangeAmount() {
		return changeAmount;
	}

	public void setChangeAmount(Long changeAmount) {
		this.changeAmount = changeAmount;
	}

}
