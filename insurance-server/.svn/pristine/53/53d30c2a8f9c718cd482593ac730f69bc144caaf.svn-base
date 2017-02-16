package com.cifpay.insurance.dao;

import java.util.List;

import com.cifpay.insurance.model.InsVendorReturnAddress;
import com.cifpay.starframework.dao.CommonDao;

public interface InsVendorReturnAddressDao extends CommonDao<InsVendorReturnAddress> {
	public InsVendorReturnAddress get(long id);

	public List<InsVendorReturnAddress> getList();

	public int getCount();
	
	/**
	 * 据商户号获取退货地址列表信息
	 * 
	 * @param vendorId
	 * @return
	 */
	public List<InsVendorReturnAddress> getInsVendorReturnAddressListByVendorId(String vendorId);
	
	/**
	 * 获取指定商户的退货地址总数。
	 * 
	 * @param vendorId
	 * @return
	 */
	public int getVendorReturnAddressCount(String vendorId);
	
	/**
	 * 更新除指定id外，其它退货地址都为非默认。
	 * 
	 * @param excludeId
	 * @return
	 */
	public int updateOtherNotDefault(Long excludeId);
	
	/**
	 * 更新指定id的退货地址记录为默认。
	 * 
	 * @param id
	 * @return
	 */
	public int updateInsVendorReturnAddressDefault(long id);
	
	/**
	 * 获取有效退货地址
	 * 
	 * @param id
	 * @return
	 */
	public InsVendorReturnAddress getValidInsVendorReturnAddress(long id);
	
	/**
	 * 获取商户默认退货地址
	 * 
	 * @param vendorId
	 * @return
	 */
	public InsVendorReturnAddress getDefaultInsVendorReturnAddress(String vendorId);
}
