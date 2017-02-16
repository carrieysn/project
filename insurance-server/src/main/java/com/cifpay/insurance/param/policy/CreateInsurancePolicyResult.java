/**
 * File: CreatePolicy.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年11月25日 下午1:42:44
 */
package com.cifpay.insurance.param.policy;

import java.io.Serializable;

/**
 * 创建保单返回结果
 * 
 * @author 张均锋
 *
 */
public class CreateInsurancePolicyResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 保单流水号 **/
	private String policyId;
	/** 保单号 **/
	private String policyNo;
	/** 充值订单流水号 **/
	private String chargeBillNo;
	/** 保费 **/
	private Long premium;

	public String getPolicyId() {
		return policyId;
	}

	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public String getChargeBillNo() {
		return chargeBillNo;
	}

	public void setChargeBillNo(String chargeBillNo) {
		this.chargeBillNo = chargeBillNo;
	}

	public Long getPremium() {
		return premium;
	}

	public void setPremium(Long premium) {
		this.premium = premium;
	}

}
