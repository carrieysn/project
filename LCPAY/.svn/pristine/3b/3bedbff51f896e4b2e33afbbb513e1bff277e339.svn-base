package com.cifpay.lc.domain.batch;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class BatchOpenLcOutputBean implements Serializable {

	private static final long serialVersionUID = 5800538245224827962L;

	private Long batchOpenId; // 批次号
	private BigDecimal lcTotalAmount; // 银信证开证金额（单位分）
	private List<OpenLcOutputBean> lcList;

	// private String returnUrl;// 返回地址

	public List<OpenLcOutputBean> getLcList() {
		return lcList;
	}

	public void setLcList(List<OpenLcOutputBean> lcList) {
		this.lcList = lcList;
	}

	public Long getBatchOpenId() {
		return batchOpenId;
	}

	public void setBatchOpenId(Long batchOpenId) {
		this.batchOpenId = batchOpenId;
	}

	public BigDecimal getLcTotalAmount() {
		return lcTotalAmount;
	}

	public void setLcTotalAmount(BigDecimal lcTotalAmount) {
		this.lcTotalAmount = lcTotalAmount;
	}

}
