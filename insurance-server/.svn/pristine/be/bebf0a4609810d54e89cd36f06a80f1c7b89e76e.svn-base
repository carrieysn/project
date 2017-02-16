package com.cifpay.insurance.service;

import java.util.List;

import com.cifpay.insurance.model.InsWarningRule;
import com.cifpay.starframework.service.CommonService;

public interface InsWarningRuleService extends CommonService<InsWarningRule> {
	public InsWarningRule get(int id);

	public List<InsWarningRule> getList();

	public int getCount();
	
	/**
	 * 获取一条预警规则。只有一条记录？
	 * 
	 * @return
	 */
	public InsWarningRule getOne();
	
	/**
	 * 修改预警规则
	 * @param insUserId 操作用户ID
	 * @param greenMax
	 * @param yellowMax
	 * @return
	 */
	public boolean changeWarningRule(Long insUserId, Double greenMax, Double yellowMax);
}
