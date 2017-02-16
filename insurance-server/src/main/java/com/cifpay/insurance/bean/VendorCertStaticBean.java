/**
 * File: VendorCertStaticBean.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年12月5日 上午10:28:10
 */
package com.cifpay.insurance.bean;

import java.io.Serializable;

/**
 * 商户保险证统计bean
 * 
 * @author 张均锋
 *
 */
public class VendorCertStaticBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 统计时间类别 （1-今天、2-昨天、3-本月、4-上月、5-本年、6-上年）**/
	private int type;

	/**生成类别（1-新增保险证；2-退货保险证） **/
	private int ctype;

	/** 保险证数量 **/
	private int certCount;
    /** 保险证金额 **/
	private Long amount;

	/** 商户号 **/
	private String vendorId;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getCtype() {
		return ctype;
	}

	public void setCtype(int ctype) {
		this.ctype = ctype;
	}

	public int getCertCount() {
		return certCount;
	}

	public void setCertCount(int certCount) {
		this.certCount = certCount;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

}
