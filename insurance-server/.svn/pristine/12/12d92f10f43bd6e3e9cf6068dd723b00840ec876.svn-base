package com.cifpay.insurance.service;

import java.util.List;

import com.cifpay.insurance.model.InsPolicyHolder;
import com.cifpay.starframework.service.CommonService;

public interface InsPolicyHolderService extends CommonService<InsPolicyHolder> {
	public InsPolicyHolder get(long ID);

	public List<InsPolicyHolder> getList();

	public int getCount();
	
	public InsPolicyHolder getInsPolicyHolderByCertNo(String certNo);
	
	/**
	 * 据商户id获取投保人信息
	 * 
	 * @param vendorId
	 * @return
	 */
	public InsPolicyHolder getPolicyHolderByVendorId(String vendorId);
}
