package com.cifpay.insurance.service;

import java.util.List;

import com.cifpay.insurance.model.InsVendor;
import com.cifpay.starframework.service.CommonService;

public interface InsVendorService extends CommonService<InsVendor> {
	public InsVendor get(long id);

	public List<InsVendor> getList();

	public int getCount();
	
	/**
	 * 据登录账号获取商户信息
	 * 
	 * @param loginAccount
	 *            登录账号
	 * @return
	 */
	public InsVendor getInsVendorByLoginAccount(String loginAccount);
}
