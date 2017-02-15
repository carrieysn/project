package com.cifpay.lc.gateway.output.lc;

/**
 * 申请解付返回参数
 *
 */
public class ApplyResp extends LcBaseResp {

	private String lcConfirmId;
	private String amount;

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getLcConfirmId() {
		return lcConfirmId;
	}

	public void setLcConfirmId(String lcConfirmId) {
		this.lcConfirmId = lcConfirmId;
	}
}
