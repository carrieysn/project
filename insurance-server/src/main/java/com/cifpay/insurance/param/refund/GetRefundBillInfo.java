package com.cifpay.insurance.param.refund;

import java.io.Serializable;

import com.cifpay.insurance.param.PageInfo;

public class GetRefundBillInfo extends PageInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 保险证号 **/
	private String insuranceCertNo;
	/** 退款状态（-1-待开证；0-待退款；1-退款中；2-退款成功；9-退款失败；10-拒绝退款；20-开证失败） */
	private String billStatus;
	/** 解付开始日期 **/
	private String solutionPayDateFrom;
	/** 解付结束日期 **/
	private String solutionPayDateTo;

	public String getInsuranceCertNo() {
		return insuranceCertNo;
	}

	public void setInsuranceCertNo(String insuranceCertNo) {
		this.insuranceCertNo = insuranceCertNo;
	}

	public String getBillStatus() {
		return billStatus;
	}

	public void setBillStatus(String billStatus) {
		this.billStatus = billStatus;
	}

	public String getSolutionPayDateFrom() {
		return solutionPayDateFrom;
	}

	public void setSolutionPayDateFrom(String solutionPayDateFrom) {
		this.solutionPayDateFrom = solutionPayDateFrom;
	}

	public String getSolutionPayDateTo() {
		return solutionPayDateTo;
	}

	public void setSolutionPayDateTo(String solutionPayDateTo) {
		this.solutionPayDateTo = solutionPayDateTo;
	}

}
