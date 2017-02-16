package com.cifpay.insurance.bean;

import java.io.Serializable;
/**
 * 保单充值输入对象
 * 
 * @author 叶胜南
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
