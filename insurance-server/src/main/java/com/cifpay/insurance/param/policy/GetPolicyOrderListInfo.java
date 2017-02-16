/**
 * File: InsuranceFeeChargeList.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年11月25日 下午2:50:38
 */
package com.cifpay.insurance.param.policy;

import java.io.Serializable;

import com.cifpay.insurance.param.PageInfo;

/**
 * 投保/保费充值记录查询
 * 
 * @author 张均锋
 *
 */
public class GetPolicyOrderListInfo extends PageInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 保单流水号 **/
	private Long policyId;
	/** 状态：（0-未支付；1-已支付） **/
	private Integer status;
	/** 起始日期 **/
	private String beginDate;
	/** 截止日期 **/
	private String endDate;

	public Long getPolicyId() {
		return policyId;
	}

	public void setPolicyId(Long policyId) {
		this.policyId = policyId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

}
