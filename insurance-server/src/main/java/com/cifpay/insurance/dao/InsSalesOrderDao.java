package com.cifpay.insurance.dao;

import java.util.List;

import com.cifpay.insurance.model.InsSalesOrder;
import com.cifpay.starframework.dao.CommonDao;

public interface InsSalesOrderDao extends CommonDao<InsSalesOrder> {
	public InsSalesOrder get(long id);

	public List<InsSalesOrder> getList();

	public int getCount();
	
	/**
	 * 获取商户及其关联订单信息
	 * 
	 * @param vendorId
	 * @param orderNo
	 * @return
	 */
	public InsSalesOrder getFullInsSalesOrder(String vendorId, String orderNo);
}
