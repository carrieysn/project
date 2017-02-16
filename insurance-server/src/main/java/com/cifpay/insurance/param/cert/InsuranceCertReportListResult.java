/**
 * File: InsuranceCertReportListResult.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年11月25日 下午5:34:32
 */
package com.cifpay.insurance.param.cert;

import java.io.Serializable;

/**
 * 报表统计结果
 * 
 * @author 张均锋
 *
 */
public class InsuranceCertReportListResult implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 时间类别（今天、昨天、本月、上月、本年、上年） **/
	private int timeType;
	/** 保险证数（全部保险证，包含待开证、待退款） **/
	private int certCount;
	/** 承保金额，单位:分，如2001表示20.01元 **/
	private long insuredAmount;
	/** 退货证数 **/
	private int returnCertCount;
	/** 退货金额，单位:分，如2001表示20.01元 **/
	private long returnAmount;

	public int getTimeType() {
		return timeType;
	}

	public void setTimeType(int timeType) {
		this.timeType = timeType;
	}

	public int getCertCount() {
		return certCount;
	}

	public void setCertCount(int certCount) {
		this.certCount = certCount;
	}

	public long getInsuredAmount() {
		return insuredAmount;
	}

	public void setInsuredAmount(long insuredAmount) {
		this.insuredAmount = insuredAmount;
	}

	public int getReturnCertCount() {
		return returnCertCount;
	}

	public void setReturnCertCount(int returnCertCount) {
		this.returnCertCount = returnCertCount;
	}

	public long getReturnAmount() {
		return returnAmount;
	}

	public void setReturnAmount(long returnAmount) {
		this.returnAmount = returnAmount;
	}

}
