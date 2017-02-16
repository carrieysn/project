package com.cifpay.insurance.dao.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cifpay.insurance.dao.InsWarningRuleDao;
import com.cifpay.insurance.model.InsWarningRule;
import com.cifpay.starframework.dao.impl.CommonDaoImpl;

@Repository("insWarningRuleDao")
public class InsWarningRuleDaoImpl extends CommonDaoImpl<InsWarningRule> implements InsWarningRuleDao {
	@Override
	public List<InsWarningRule> getList() {
		List<InsWarningRule> resultList = this.getSqlSession().selectList(getStatementPrefix() + ".getList");
		if (resultList == null) {
			resultList = Collections.emptyList();
		}
		return resultList;
	}

	@Override
	public int getCount() {
		Integer result = (Integer) this.getSqlSession().selectOne(getStatementPrefix() + ".getCount");
		return result != null ? result.intValue() : 0;
	}

	@Override
	public InsWarningRule getOne() {
		return this.getSqlSession().selectOne(getStatementPrefix() + ".getOne");
	}
}
