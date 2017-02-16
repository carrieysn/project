/**
 * File: InsurancePolicyEvent.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年12月2日 上午9:43:49
 */
package com.cifpay.insurance.push.event;

import java.util.EventObject;

import com.cifpay.insurance.model.InsPolicy;

/**
 * 保单金额变动事件。
 * 
 * @author 张均锋
 *
 */
public class InsurancePolicyEvent extends EventObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 当前保单 **/
	private InsPolicy curInsPolicy;
	/** 商户ID **/
	private String vendorId;
	/** 标识 **/
	private int tag;

	public InsurancePolicyEvent(Object source) {
		super(source);
	}

	public InsPolicy getCurInsPolicy() {
		return curInsPolicy;
	}

	public void setCurInsPolicy(InsPolicy curInsPolicy) {
		this.curInsPolicy = curInsPolicy;
	}

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public int getTag() {
		return tag;
	}

	public void setTag(int tag) {
		this.tag = tag;
	}

}
