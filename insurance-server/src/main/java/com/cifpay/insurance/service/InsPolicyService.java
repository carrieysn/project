package com.cifpay.insurance.service;

import java.util.List;

import com.cifpay.insurance.base.Page;
import com.cifpay.insurance.bean.PolicyAmountChangeBean;
import com.cifpay.insurance.model.InsPolicy;
import com.cifpay.insurance.model.InsPolicyOrder;
import com.cifpay.insurance.param.policy.CreateInsurancePolicyResult;
import com.cifpay.insurance.param.policy.GetPolicyListInfo;
import com.cifpay.starframework.model.ServiceResult;
import com.cifpay.starframework.service.CommonService;

public interface InsPolicyService extends CommonService<InsPolicy> {
	public InsPolicy get(long ID);

	public List<InsPolicy> getList();

	public int getCount();

	/**
	 * 
	 * @param insPolicy
	 * @return
	 * @deprecated replace by {@link #createInsPolicy(InsPolicy)}
	 */
	public ServiceResult<CreateInsurancePolicyResult> savePolicy(InsPolicy insPolicy);

	public InsPolicy getPolicyByVendorId(String vendorId);
	/**
	 * 根据保单号查询保单详细信息
	 * @param policyNo
	 * @return
	 */
	public InsPolicy getPolicyByPolicyNo(String policyNo);

	/**
	 * 创建保单
	 * 
	 * @param insPolicy
	 *            创建保单
	 */
	public void createInsPolicy(InsPolicy insPolicy);

	/**
	 * 据保险证冻结保单保额，存在并发的情况，须循环（默认10次）冻结，直至冻结成功。可能冻结失败！最后记录保单金额变动流水
	 * 
	 * @param policyAmountChangeBean
	 *            保险证
	 */
	public void freezeInsuredAmount(PolicyAmountChangeBean policyAmountChangeBean);

	/**
	 * 据保险证解冻保单保额，存在并发的情况，须循环（默认10次）解冻，直至解冻成功。可能解冻失败！最后记录保单金额变动流水
	 * 
	 * @param policyAmountChangeBean
	 */
	public void unfreezeInsuredAmount(PolicyAmountChangeBean policyAmountChangeBean);

	/**
	 * 缴费成功后，调整保单保费及保额，并记录保单金额变动流水
	 * 
	 * @param insPolicyOrder
	 *            缴费订单
	 */
	public void addPolicyPremium(InsPolicyOrder insPolicyOrder);
	
	/**
	 * 据退款保险证扣减保费，同时调整保额。
	 * 
	 * @param policyAmountChangeBean
	 */
	public void deductPolicyPremium(PolicyAmountChangeBean policyAmountChangeBean);

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
	 * 调整信用
	 * @param policyId
	 * @param creditScore
	 * @return
	 */
	public InsPolicy adjustGearing(Long insUserId, Long policyId,Integer gearingRuleId);
	
	/**
	 * 重新对所有商户保单的保额进行预警测算。
	 */
	public void reCalculateInsuredAmountState();
}
