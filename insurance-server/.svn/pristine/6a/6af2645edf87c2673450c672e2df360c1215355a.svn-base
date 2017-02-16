package com.cifpay.insurance.dao.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cifpay.insurance.dao.SysCodeRuleDtDao;
import com.cifpay.insurance.model.SysCodeRuleDt;
import com.cifpay.starframework.dao.impl.CommonDaoImpl;

@Repository("sysCodeRuleDtDao")
public class SysCodeRuleDtDaoImpl extends CommonDaoImpl<SysCodeRuleDt>
		implements SysCodeRuleDtDao {
	@Override
	public SysCodeRuleDt get(long id) {
		return this.getSqlSession().selectOne(getStatementPrefix() + ".get", id);
	}

	@Override
	public List<SysCodeRuleDt> getList() {
		List<SysCodeRuleDt> resultList = this.getSqlSession().selectList(getStatementPrefix() + ".getList");
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
	public int addBatch(List<SysCodeRuleDt> sysCodeRuleDtList) {
		return this.getSqlSession().insert(getStatementPrefix() + ".addBatch", sysCodeRuleDtList);
	}
}
