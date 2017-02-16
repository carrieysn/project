package com.cifpay.insurance.service;

import java.util.List;

import com.cifpay.insurance.model.InsInsuredAmountInfo;
import com.cifpay.starframework.service.CommonService;

public interface InsInsuredAmountInfoService extends CommonService<InsInsuredAmountInfo> {
	public InsInsuredAmountInfo get(long id);

	public List<InsInsuredAmountInfo> getList();

	public int getCount();
	/**
	 * 据保单id获取保额信息
	 * 
	 * @param policyId
	 * @return
	 */
	public InsInsuredAmountInfo getInsInsuredAmountInfoByPolicyId(Long policyId);
}
