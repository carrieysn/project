package com.cifpay.lc.gateway.input.lc;

public class RecvReq {

	private long lcId; 	// 银信证ID

	private String recvBankCode; 		// 收款方银行代码
	private String recvBankAccountNo; 	// 收款人账号
	private String recvAccountType; 	// 收款人类型：c:个人 b:企业

	private String remark; // 备注

	public long getLcId() {
		return lcId;
	}

	public void setLcId(long lcId) {
		this.lcId = lcId;
	}

	public String getRecvBankCode() {
		return recvBankCode;
	}

	public void setRecvBankCode(String recvBankCode) {
		this.recvBankCode = recvBankCode;
	}

	public String getRecvBankAccountNo() {
		return recvBankAccountNo;
	}

	public void setRecvBankAccountNo(String recvBankAccountNo) {
		this.recvBankAccountNo = recvBankAccountNo;
	}

	public String getRecvAccountType() {
		return recvAccountType;
	}

	public void setRecvAccountType(String recvAccountType) {
		this.recvAccountType = recvAccountType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
