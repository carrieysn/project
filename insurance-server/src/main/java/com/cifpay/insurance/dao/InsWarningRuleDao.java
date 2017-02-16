package com.cifpay.insurance.dao;

import java.util.List;

import com.cifpay.insurance.model.InsWarningRule;
import com.cifpay.starframework.dao.CommonDao;

public interface InsWarningRuleDao extends CommonDao<InsWarningRule> {
	public List<InsWarningRule> getList();

	public int getCount();
	
	/**
	 * 获取一条预警规则。只有一条记录？
	 * 
	 * @return
	 */
	public InsWarningRule getOne();
}
