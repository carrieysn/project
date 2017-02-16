package com.cifpay.insurance.dao.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cifpay.insurance.dao.SysCodeRuleDao;
import com.cifpay.insurance.model.SysCodeRule;
import com.cifpay.starframework.dao.impl.CommonDaoImpl;

@Repository("sysCodeRuleDao")
public class SysCodeRuleDaoImpl extends CommonDaoImpl<SysCodeRule> implements
		SysCodeRuleDao {
	@Override
	public SysCodeRule get(long id) {
		return this.getSqlSession().selectOne(getStatementPrefix() + ".get", id);
	}

	@Override
	public List<SysCodeRule> getList() {
		List<SysCodeRule> resultList = this.getSqlSession().selectList(getStatementPrefix() + ".getList");
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
	public SysCodeRule getSysCodeRule(String codeName) {
		return getSysCodeRule(null,codeName);
	}
	
	@Override
	public SysCodeRule getDefaultSysCodeRule(String codeName) {
		return this.getSqlSession().selectOne(getStatementPrefix() + ".getDefaultSysCodeRule", codeName);
	}
	
	@Override
	public SysCodeRule getSysCodeRule(String cu, String codeName) {
		HashMap<String,Object> params = new HashMap<String, Object>();
		params.put("cu", cu);
		params.put("codeName", codeName);
		return this.getSqlSession().selectOne(getStatementPrefix() + ".getSysCodeRule", params);
	}
}
