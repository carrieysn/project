/**
 * File: GetPolicyOrderResult.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年12月12日 上午9:52:46
 */
package com.cifpay.insurance.param.policy;

import java.io.Serializable;

/**
 * 获取投保/充值订单结果信息
 * 
 * @author 张均锋
 *
 */
public class GetPolicyOrderResult implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 订单号 **/
	private String billNo;
	/** 订单类型(0-投保；1-充值) **/
	private Integer type;
	/** 订单状态（0-未支付；1-已支付） **/
	private Integer status;
	/** 金额（分） **/
	private Long amount;
	/** 保单ID **/
	private Long policyId;

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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

	public Long getPolicyId() {
		return policyId;
	}

	public void setPolicyId(Long policyId) {
		this.policyId = policyId;
	}

}
