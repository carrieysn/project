/**
 * File: PolicyRealtimeInfo.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年12月1日 下午2:04:57
 */
package com.cifpay.insurance.param.msg;

import java.io.Serializable;
import java.util.Date;

/**
 * 保单实时信息
 * 
 * @author 张均锋
 *
 */
public class PolicyRealtimeInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 保费，单位:分，如2001表示20.01元 **/
	private long premium;
	/** 保额，单位:分，如2001表示20.01元 **/
	private long insuredAmount;
	/** 保单有效开始时间 **/
	private Date validFrom;
	/** 保单有效截止时间 **/
	private Date validTo;
	/** 占用保额（承保的在途保险证商品金额），单位:分，如2001表示20.01元 **/
	//private long usedInsuredAmount;
	/** 实时保额（保额-占用保额），单位:分，如2001表示20.01元 **/
	private long realInsuredAmount;
	/** 实时收益，单位:分，如2001表示20.01元 **/
	private long realIncome;
	/** 预警状态。5-正常，6-预警，7-警报 **/
	private int status;

	public long getPremium() {
		return premium;
	}

	public void setPremium(long premium) {
		this.premium = premium;
	}

	public long getInsuredAmount() {
		return insuredAmount;
	}

	public void setInsuredAmount(long insuredAmount) {
		this.insuredAmount = insuredAmount;
	}

	public Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public Date getValidTo() {
		return validTo;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

	/*public long getUsedInsuredAmount() {
		return usedInsuredAmount;
	}

	public void setUsedInsuredAmount(long usedInsuredAmount) {
		this.usedInsuredAmount = usedInsuredAmount;
	}*/

	public long getRealInsuredAmount() {
		return realInsuredAmount;
	}

	public void setRealInsuredAmount(long realInsuredAmount) {
		this.realInsuredAmount = realInsuredAmount;
	}

	public long getRealIncome() {
		return realIncome;
	}

	public void setRealIncome(long realIncome) {
		this.realIncome = realIncome;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
