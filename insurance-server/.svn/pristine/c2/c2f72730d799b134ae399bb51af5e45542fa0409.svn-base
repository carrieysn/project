package com.cifpay.insurance.service;

import java.util.List;

import com.cifpay.insurance.base.Page;
import com.cifpay.insurance.model.InsPolicyOrder;
import com.cifpay.insurance.param.policy.GetPolicyOrderListInfo;
import com.cifpay.starframework.service.CommonService;

public interface InsPolicyOrderService extends CommonService<InsPolicyOrder> {
	public InsPolicyOrder get(long id);

	public List<InsPolicyOrder> getList();

	public int getCount();

	/**
	 * 创建投保单
	 * 
	 * @param insPolicyOrder
	 */
	public void createInsPolicyOrder(InsPolicyOrder insPolicyOrder);
	
	/**
	 * 创建保费充值订单
	 * 
	 * @param insPolicyOrder
	 */
	public void createChargeFeePolicyOrder(InsPolicyOrder insPolicyOrder);
	
	/**
	 * 获取订单列表。
	 * 
	 * @param getChargeFeePolicyOrderListInfo
	 * @param page
	 * @return
	 */
	public List<InsPolicyOrder> getInsPolicyOrderList(GetPolicyOrderListInfo getChargeFeePolicyOrderListInfo, Page<InsPolicyOrder> page);
	
	/**
	 * 订单交易状态通知
	 * 
	 * @param insPolicyOrder
	 */
	public void noticePolicyOrder(InsPolicyOrder insPolicyOrder);
	
	/**
	 * 据订单号查找订单
	 * 
	 * @param billNo
	 * @return
	 */
	public InsPolicyOrder getInsPolicyOrderByBillNo(String billNo);
}
