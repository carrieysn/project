package com.cifpay.lc.constant.enums;

/**
 * 流程状态
 * 
 * @author sweet
 *
 */
public enum ProcessStatus {

	INPROCESS(0, "待处理"), 
	RECIEVE(20, "已由收证处理"), 
	APPOINTMENT(30, "已由履约处理"), 
	DEFER(32, "已由展期处理"),
	APPLY(40, "已由申请解付处理"), 
	SUSPEND(41, "已由刹车处理"), 
	TRANSFER(50, "已由执行解付处理"),
	INVALIDATE(90, "已由失效处理");

	private int statusCode;
	private String statusDesc;

	private ProcessStatus(int statusCode, String statusDesc) {
		this.statusCode = statusCode;
		this.statusDesc = statusDesc;
	}

	public int getCode() {
		return statusCode;
	}

	public String getDesc() {
		return statusDesc;
	}
}
