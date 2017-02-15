package com.cifpay.lc.domain.batch;

import java.io.Serializable;
import java.util.List;

public class BatchApplyOutputBean implements Serializable {

	private static final long serialVersionUID = -4466279473863628163L;

	private List<BatchApplyOutputDto> applyOutputList;

	public List<BatchApplyOutputDto> getApplyOutputList() {
		return applyOutputList;
	}

	public void setApplyOutputList(List<BatchApplyOutputDto> applyOutputList) {
		this.applyOutputList = applyOutputList;
	}
}
