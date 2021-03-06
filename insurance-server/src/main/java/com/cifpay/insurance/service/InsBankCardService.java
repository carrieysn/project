package com.cifpay.insurance.service;

import java.util.List;

import com.cifpay.insurance.model.InsBankCard;
import com.cifpay.starframework.service.CommonService;

public interface InsBankCardService extends CommonService<InsBankCard> {
	public InsBankCard get(long id);

	public List<InsBankCard> getList();

	public int getCount();
	/**
	 * 获取有效银行卡信息。
	 * 
	 * @return
	 */
    public List<InsBankCard> getBankCardList();
}
