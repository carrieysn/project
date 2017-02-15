package com.cifpay.lc.gateway.output.batch;

import java.util.List;

import com.cifpay.lc.domain.security.AbstractMerchantResponse;

public class BatchOpenResp extends AbstractMerchantResponse {

	private String batchOpenId; // 批次号
	private String lcTotalAmount; // 银信证开证金额（单位分）

	private List<OpenLcOutputDto> lcList;

	public String getBatchOpenId() {
		return batchOpenId;
	}

	public void setBatchOpenId(String batchOpenId) {
		this.batchOpenId = batchOpenId;
	}

	public String getLcTotalAmount() {
		return lcTotalAmount;
	}

	public void setLcTotalAmount(String lcTotalAmount) {
		this.lcTotalAmount = lcTotalAmount;
	}

	public List<OpenLcOutputDto> getLcList() {
		return lcList;
	}

	public void setLcList(List<OpenLcOutputDto> lcList) {
		this.lcList = lcList;
	}
}
