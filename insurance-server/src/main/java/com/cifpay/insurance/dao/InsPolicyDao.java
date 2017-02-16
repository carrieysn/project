package com.cifpay.insurance.dao;

import java.util.List;

import com.cifpay.insurance.base.Page;
import com.cifpay.insurance.model.InsPolicy;
import com.cifpay.insurance.param.policy.GetPolicyListInfo;
import com.cifpay.starframework.dao.CommonDao;

public interface InsPolicyDao extends CommonDao<InsPolicy> {
	public InsPolicy get(long ID);

	public List<InsPolicy> getList();

	public int getCount();

	public InsPolicy getPolicyByVendorId(String vendorId);
	public InsPolicy getPolicyByPolicyId(Long policyId);
	public InsPolicy getPolicyByPolicyNo(String policyNo);

	/**
	 * 更新变动保费
	 * 
	 * @param insPolicy
	 * @return
	 */
	public int updateChangePolicyPremium(InsPolicy insPolicy);

	/**
	 * 是否存在商户保单。vendorId属性必须，excludeId属性可选（不包含id值本身）。
	 * 
	 * @param vendorId
	 * @param excludeId
	 * @return true存在，false不存在
	 */
	public boolean isExistsVendorInsPolicy(String vendorId, Long excludeId);
	
	/**
	 * 获取保单及其关联投保人、保险公司信息、保额信息等
	 * @param vendorId
	 * @return
	 */
	public InsPolicy getFullInsPolicy(String vendorId);
	
	/**
	 * 获取保单列表。
	 * 
	 * @param bean
	 * @return
	 */
	public List<InsPolicy> getInsPolicyList(GetPolicyListInfo bean, Page<InsPolicy> page);
	
	/**
	 * 更新原保单状态为指定新状态。
	 * 
	 * @param oldInsPolicy
	 * @param newStatus
	 * @return
	 */
	public int updatePolicyStatus(InsPolicy oldInsPolicy, int newStatus);
	
	/**
	 *  查询所有保单及其实时保额信息,注海量数据处理？
	 *  
	 * @return
	 */
	public List<InsPolicy> getAllInsPolicyAndInsuredAmountInfo();
}
