package com.cifpay.insurance.dao.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cifpay.insurance.dao.InsReturnTypeDao;
import com.cifpay.insurance.model.InsReturnType;
import com.cifpay.starframework.dao.impl.CommonDaoImpl;

@Repository("insReturnTypeDao")
public class InsReturnTypeDaoImpl extends CommonDaoImpl<InsReturnType> implements InsReturnTypeDao {
	@Override
	public InsReturnType get(long id) {
		return this.getSqlSession().selectOne(getStatementPrefix() + ".get", id);
	}

	@Override
	public List<InsReturnType> getList() {
		List<InsReturnType> resultList = this.getSqlSession().selectList(getStatementPrefix() + ".getList");
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
	public InsReturnType getInsReturnTypeByCode(String code) {
		return this.getSqlSession().selectOne(getStatementPrefix() + ".getInsReturnTypeByCode", code);
	}
}
