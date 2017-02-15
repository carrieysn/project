package com.cifpay.lc.domain.bank;

import java.io.Serializable;

public class PaymentSuccessInputBean implements Serializable {

	private static final long serialVersionUID = -3181389583717820356L;

	private Long lcOpenId; // 开证ID

	private String remark; // 备注

	public Long getLcOpenId() {
		return lcOpenId;
	}

	public void setLcOpenId(Long lcOpenId) {
		this.lcOpenId = lcOpenId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
