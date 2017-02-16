/**
 * File: NoticeFrontPolicyOrderResult.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年12月11日 下午2:02:56
 */
package com.cifpay.insurance.param.policy;

import java.io.Serializable;

/**
 * 投保充值订单前端通知处理结果
 * 
 * @author 张均锋
 *
 */
public class NoticeFrontPolicyOrderResult implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 商户id **/
	private String vendorId;
	/** 订单号 **/
	private String billNo;
	/** 订单状态 **/
	private Integer status;
	/** 交易金额 **/
	private Long amount;

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

}
