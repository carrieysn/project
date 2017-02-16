package com.cifpay.insurance.service;

import java.util.List;

import com.cifpay.insurance.model.InsVendorReturnAddress;
import com.cifpay.starframework.service.CommonService;

public interface InsVendorReturnAddressService extends CommonService<InsVendorReturnAddress> {
	public InsVendorReturnAddress get(long id);

	public List<InsVendorReturnAddress> getList();

	public int getCount();
	
	/**
	 * 保存商户退货地址
	 * 
	 * @param entity
	 */
	public void saveInsVendorReturnAddress(InsVendorReturnAddress entity);

	/**
	 * 据商户ID获取商户退货地址列表信息
	 * 
	 * @param vendorId
	 * @return
	 */
	public List<InsVendorReturnAddress> getInsVendorReturnAddressList(String vendorId);
	
	/**
	 * 设置默认退货地址
	 * 
	 * @param vendorId
	 * @param id
	 */
	public void setDefaultInsVendorReturnAddress(String vendorId, Long id);
	
	/**
	 * 解绑退货地址。
	 * 
	 * @param vendorId
	 * @param id
	 */
	public void deleteInsVendorReturnAddress(String vendorId, Long id);
	
	/**
	 * 获取商户默认退货地址
	 * 
	 * @param vendorId
	 * @return
	 */
	public InsVendorReturnAddress getDefaultInsVendorReturnAddress(String vendorId);
}
