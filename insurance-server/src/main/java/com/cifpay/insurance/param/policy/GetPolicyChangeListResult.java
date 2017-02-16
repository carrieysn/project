/**
 * File: GetPolicyChangeListResult.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年12月7日 下午3:30:38
 */
package com.cifpay.insurance.param.policy;

import java.io.Serializable;
import java.util.Date;

/**
 * 获取保单变动流水列表结果
 * 
 * @author 张均锋
 *
 */
public class GetPolicyChangeListResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** id **/
	// private Long id;
	/** 引用凭证号 **/
	private String refVoucherNo;
	/** 类型（1-生成；2-退款；3-失效；4-充值） **/
	private Integer type;
	/** 变动金额（分） **/
	private Long changeAmount;
	/** 变动时间 **/
	private Date changeTime;
	/** 变动后保费（分） **/
	private Long curPremium;
	/** 变动后保额（分） **/
	private Long curInsuredAmount;

	public String getRefVoucherNo() {
		return refVoucherNo;
	}

	public void setRefVoucherNo(String refVoucherNo) {
		this.refVoucherNo = refVoucherNo;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getChangeAmount() {
		return changeAmount;
	}

	public void setChangeAmount(Long changeAmount) {
		this.changeAmount = changeAmount;
	}

	public Date getChangeTime() {
		return changeTime;
	}

	public void setChangeTime(Date changeTime) {
		this.changeTime = changeTime;
	}

	public Long getCurPremium() {
		return curPremium;
	}

	public void setCurPremium(Long curPremium) {
		this.curPremium = curPremium;
	}

	public Long getCurInsuredAmount() {
		return curInsuredAmount;
	}

	public void setCurInsuredAmount(Long curInsuredAmount) {
		this.curInsuredAmount = curInsuredAmount;
	}

}
