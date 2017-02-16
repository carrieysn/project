package com.cifpay.insurance.dao;

import java.util.List;

import com.cifpay.insurance.model.InsPolicyHolder;
import com.cifpay.starframework.dao.CommonDao;

public interface InsPolicyHolderDao extends CommonDao<InsPolicyHolder> {
	public InsPolicyHolder get(long ID);

	public List<InsPolicyHolder> getList();

	public int getCount();
	
	public InsPolicyHolder getPolicyHolderByCertNo(String certNo);
	
	/**
	 * 据商户号获取投保人信息
	 * 
	 * @param vendorId
	 * @return
	 */
	public InsPolicyHolder getPolicyHolderByVendorId(String vendorId);
}
