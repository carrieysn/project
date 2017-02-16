/**
 * File: InsuranceCertReportInfo.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年11月25日 下午5:32:52
 */
package com.cifpay.insurance.param.cert;

import java.io.Serializable;

/**
 * 保险证统计查询
 * 
 * @author 张均锋
 *
 */
public class InsuranceCertReportInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 保单号 **/
	private String policyNo;

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

}
