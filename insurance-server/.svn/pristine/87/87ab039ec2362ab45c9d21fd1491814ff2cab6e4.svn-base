/**
 * File: InsuranceFeeChargeListResult.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年11月25日 下午2:55:44
 */
package com.cifpay.insurance.param.policy;

import java.io.Serializable;
import java.util.Date;

/**
 * 投保/充值记录查询结果信息
 * 
 * @author 张均锋
 *
 */
public class GetPolicyOrderListResult implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 充值订单号 **/
	private String billNo;
	/** 订单状态（0-未支付；1-已支付） **/
	private Integer status;
	/** 充值金额（分） **/
	private Long amount;
	/** 订单充值时间 **/
	private Date orderTime;
	/** 充值前保费（分） **/
	private Long beforePremium;

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

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public Long getBeforePremium() {
		return beforePremium;
	}

	public void setBeforePremium(Long beforePremium) {
		this.beforePremium = beforePremium;
	}

}
