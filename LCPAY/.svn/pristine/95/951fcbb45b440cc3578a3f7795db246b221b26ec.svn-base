package com.cifpay.lc.thirdtradeadapter.api.output.alipay;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.cifpay.lc.util.StringParamPair;

/**
 * 
 *
 */
public class AlipayPaymentInitResult implements Serializable {
	private static final long serialVersionUID = -112166540031328462L;
	private String instructionNo;
	private Long totalAmount;
	private String adapterReturnedSeqNo;
	private List<StringParamPair> alipayAutoPostFormParams;

	public String getInstructionNo() {
		return instructionNo;
	}

	public void setInstructionNo(String instructionNo) {
		this.instructionNo = instructionNo;
	}

	public Long getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getAdapterReturnedSeqNo() {
		return adapterReturnedSeqNo;
	}

	public void setAdapterReturnedSeqNo(String adapterReturnedSeqNo) {
		this.adapterReturnedSeqNo = adapterReturnedSeqNo;
	}

	public List<StringParamPair> getAlipayAutoPostFormParams() {
		return alipayAutoPostFormParams;
	}

	public void setAlipayAutoPostFormParams(List<StringParamPair> alipayAutoPostFormParams) {
		this.alipayAutoPostFormParams = alipayAutoPostFormParams;
	}

	public void addAlipayAutoPostFormParams(StringParamPair param) {
		if (null == alipayAutoPostFormParams) {
			alipayAutoPostFormParams = new ArrayList<StringParamPair>();
		}
		alipayAutoPostFormParams.add(param);
	}

}
