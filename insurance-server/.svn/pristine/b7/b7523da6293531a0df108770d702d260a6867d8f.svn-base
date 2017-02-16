package com.cifpay.insurance.dao.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cifpay.insurance.dao.InsReturnTraceDao;
import com.cifpay.insurance.model.InsReturnTrace;
import com.cifpay.starframework.dao.impl.CommonDaoImpl;

@Repository("insReturnTraceDao")
public class InsReturnTraceDaoImpl extends CommonDaoImpl<InsReturnTrace>
		implements InsReturnTraceDao {
	@Override
	public InsReturnTrace get(long ID) {
		return this.getSqlSession()
				.selectOne(getStatementPrefix() + ".get", ID);
	}

	@Override
	public List<InsReturnTrace> getList() {
		List<InsReturnTrace> resultList = this.getSqlSession().selectList(
				getStatementPrefix() + ".getList");
		if (resultList == null) {
			resultList = Collections.emptyList();
		}
		return resultList;
	}

	@Override
	public int getCount() {
		Integer result = (Integer) this.getSqlSession().selectOne(
				getStatementPrefix() + ".getCount");
		return result != null ? result.intValue() : 0;
	}

	@Override
	public List<InsReturnTrace> getListByCertNo(String certNo) {
		
		List<InsReturnTrace> resultList = this.getSqlSession().selectList(getStatementPrefix() + ".getListByCertNo",certNo);
		if (resultList == null) {
			resultList = Collections.emptyList();
		}
		return resultList;
	}
}
