package com.cifpay.insurance.dao;

import java.util.List;

import com.cifpay.insurance.model.InsUnopenRefundBill;
import com.cifpay.starframework.dao.CommonDao;

public interface InsUnopenRefundBillDao extends CommonDao<InsUnopenRefundBill> {
	public InsUnopenRefundBill get(long id);

	public List<InsUnopenRefundBill> getList();

	public int getCount();
	
	/**
	 * 获取未过期的未开证退款单
	 * 须考虑海量数据
	 * @return
	 */
	public List<InsUnopenRefundBill> getUnexpiredInsUnopenRefundBills();
	
	/**
	 * 获取过期的未开证退款单
	 * 须考虑海量数据
	 * @return
	 */
	public List<InsUnopenRefundBill> getExpiredInsUnopenRefundBills();
}
