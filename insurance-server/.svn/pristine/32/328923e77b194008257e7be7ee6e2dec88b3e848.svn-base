package com.cifpay.insurance.dao.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cifpay.insurance.dao.InsGearingRuleDao;
import com.cifpay.insurance.model.InsGearingRule;
import com.cifpay.starframework.dao.impl.CommonDaoImpl;

@Repository("insGearingRuleDao")
public class InsGearingRuleDaoImpl extends CommonDaoImpl<InsGearingRule> implements InsGearingRuleDao {
@Override
public List<InsGearingRule> getList() {List<InsGearingRule> resultList = this.getSqlSession().selectList(getStatementPrefix() + ".getList");
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
public InsGearingRule getDefault() {
	return this.getSqlSession().selectOne(getStatementPrefix() + ".getDefault");
}
}
