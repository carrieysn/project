package com.cifpay.insurance.param.cert;

import java.io.Serializable;
import java.util.Date;

public class GetRefundBillListResult implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 保险证号 **/
	private String insuranceCertNo;
	/** 退款状态（0-待退款；1-退款中；2-退款成功；9-退款失败；10-拒绝退款） **/
	private Integer billStatus;
	/** 开证时间 **/
	private Date lcOpenTime;
	/** 解付时间 **/
	private Date lcPayedTime;
	/** 退款金额（分） **/
	private Long refundAmount;

	public String getInsuranceCertNo() {
		return insuranceCertNo;
	}

	public void setInsuranceCertNo(String insuranceCertNo) {
		this.insuranceCertNo = insuranceCertNo;
	}

	public Integer getBillStatus() {
		return billStatus;
	}

	public void setBillStatus(Integer billStatus) {
		this.billStatus = billStatus;
	}

	public Date getLcOpenTime() {
		return lcOpenTime;
	}

	public void setLcOpenTime(Date lcOpenTime) {
		this.lcOpenTime = lcOpenTime;
	}

	public Date getLcPayedTime() {
		return lcPayedTime;
	}

	public void setLcPayedTime(Date lcPayedTime) {
		this.lcPayedTime = lcPayedTime;
	}

	public Long getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(Long refundAmount) {
		this.refundAmount = refundAmount;
	}

}
