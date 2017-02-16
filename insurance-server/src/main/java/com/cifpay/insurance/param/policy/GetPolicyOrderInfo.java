/**
 * File: GetPolicyOrderfo.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年12月12日 上午9:51:22
 */
package com.cifpay.insurance.param.policy;

import java.io.Serializable;

/**
 * 获取投保/充值订单信息
 * 
 * @author 张均锋
 *
 */
public class GetPolicyOrderInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String billNo;

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

}
