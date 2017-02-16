/**
 * File: TodayAddedCertInfo.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年11月30日 下午8:45:57
 */
package com.cifpay.insurance.param.msg;

/**
 * 今日新增保险证信息
 * 
 * @author 张均锋
 *
 */
public class TodayAddedCertInfo {

	/** 今日新增保险证数（未生效） **/
	private long todayAddedCertCount;

	/** 今日新增保险证金额（未生效） **/
	private long todayAddedAmount;

	/** 今日所有保险证数 **/
	private long todayAllCertCount;

	/**今日所有保险证金额 **/
	private long todayAllAmount;

	public long getTodayAddedCertCount() {
		return todayAddedCertCount;
	}

	public void setTodayAddedCertCount(long todayAddedCertCount) {
		this.todayAddedCertCount = todayAddedCertCount;
	}

	public long getTodayAddedAmount() {
		return todayAddedAmount;
	}

	public void setTodayAddedAmount(long todayAddedAmount) {
		this.todayAddedAmount = todayAddedAmount;
	}

	public long getTodayAllCertCount() {
		return todayAllCertCount;
	}

	public void setTodayAllCertCount(long todayAllCertCount) {
		this.todayAllCertCount = todayAllCertCount;
	}

	public long getTodayAllAmount() {
		return todayAllAmount;
	}

	public void setTodayAllAmount(long todayAllAmount) {
		this.todayAllAmount = todayAllAmount;
	}

}
