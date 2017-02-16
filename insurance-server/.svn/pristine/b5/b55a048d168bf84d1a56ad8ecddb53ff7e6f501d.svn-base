package com.cifpay.insurance.dao;

import java.util.List;

import com.cifpay.insurance.base.Page;
import com.cifpay.insurance.model.InsPolicyOrder;
import com.cifpay.insurance.param.policy.GetPolicyOrderListInfo;
import com.cifpay.starframework.dao.CommonDao;

public interface InsPolicyOrderDao extends CommonDao<InsPolicyOrder> {
	public InsPolicyOrder get(long id);

	public List<InsPolicyOrder> getList();

	public int getCount();

	/**
	 * 获取订单列表。
	 * 
	 * @param getChargeFeePolicyOrderListInfo
	 * @return
	 */
	public List<InsPolicyOrder> getInsPolicyOrderList(GetPolicyOrderListInfo getChargeFeePolicyOrderListInfo, Page<InsPolicyOrder> page);
	
	/**
	 * 据订单号银信证id查找订单
	 * 
	 * @param billNo
	 * @param lcId
	 * @return
	 */
	public InsPolicyOrder getInsPolicyOrder(String billNo, String lcId);
	
	/**
	 * 据订单号查找订单
	 * 
	 * @param billNo
	 * @return
	 */
	public InsPolicyOrder getInsPolicyOrderByBillNo(String billNo);
}
