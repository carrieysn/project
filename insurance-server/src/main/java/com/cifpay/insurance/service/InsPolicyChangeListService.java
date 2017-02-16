package com.cifpay.insurance.service;

import java.util.List;

import com.cifpay.insurance.base.Page;
import com.cifpay.insurance.model.InsPolicyChangeList;
import com.cifpay.insurance.param.policy.GetPolicyChangeListInfo;
import com.cifpay.starframework.service.CommonService;

public interface InsPolicyChangeListService extends CommonService<InsPolicyChangeList> {
	public InsPolicyChangeList get(long id);

	public List<InsPolicyChangeList> getList();

	public int getCount();

	/**
	 * 获取保单变动列表信息
	 * 
	 * @param vendorId
	 * @param getPolicyChangeListInfo
	 * @param page
	 * @return
	 */
	public List<InsPolicyChangeList> getPolicyChangeList(String vendorId, GetPolicyChangeListInfo getPolicyChangeListInfo, Page<InsPolicyChangeList> page);
}
