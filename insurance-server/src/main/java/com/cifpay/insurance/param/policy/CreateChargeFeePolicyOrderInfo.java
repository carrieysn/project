/**
 * File: ChargePolicyFeeInfo.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年11月25日 下午2:40:09
 */
package com.cifpay.insurance.param.policy;

import java.io.Serializable;

/**
 * 创建保单充值信息
 * 
 * @author 张均锋
 *
 */
public class CreateChargeFeePolicyOrderInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 保单流水号 **/
	private Long policyId;
	/** 充值金额（分） **/
	private Long amount;

	public Long getPolicyId() {
		return policyId;
	}

	public void setPolicyId(Long policyId) {
		this.policyId = policyId;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

}
