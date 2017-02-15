package com.cifpay.lc.gateway.output.batch;

import java.util.List;

import com.cifpay.lc.domain.security.AbstractMerchantResponse;

public class BatchWithdrawResp extends AbstractMerchantResponse {

	private List<WithdrawResp> listWithdrawResp;

	public List<WithdrawResp> getListWithdrawResp() {
		return listWithdrawResp;
	}

	public void setListWithdrawResp(List<WithdrawResp> listWithdrawResp) {
		this.listWithdrawResp = listWithdrawResp;
	}

}
