/**
 * File: CreatePolicyOrderResult.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年12月8日 下午8:40:50
 */
package com.cifpay.insurance.param.policy;

import java.io.Serializable;

/**
 * 商户订单结果信息
 * 
 * @author 张均锋
 *
 */
public class CreatePolicyOrderResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 订单号 **/
	private String billNo;
	/** 保费 **/
	private Long premium;

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public Long getPremium() {
		return premium;
	}

	public void setPremium(Long premium) {
		this.premium = premium;
	}

}
