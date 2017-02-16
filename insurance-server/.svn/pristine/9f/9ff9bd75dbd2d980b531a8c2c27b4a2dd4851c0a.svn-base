package com.cifpay.insurance.dao.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cifpay.insurance.dao.InsVendorDao;
import com.cifpay.insurance.model.InsVendor;
import com.cifpay.starframework.dao.impl.CommonDaoImpl;

@Repository("insVendorDao")
public class InsVendorDaoImpl extends CommonDaoImpl<InsVendor> implements InsVendorDao {
	@Override
	public InsVendor get(long id) {
		return this.getSqlSession().selectOne(getStatementPrefix() + ".get", id);
	}

	@Override
	public List<InsVendor> getList() {
		List<InsVendor> resultList = this.getSqlSession().selectList(getStatementPrefix() + ".getList");
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
	public InsVendor getInsVendorByLoginAccount(String loginAccount) {
		return this.getSqlSession().selectOne(getStatementPrefix() + ".getInsVendorByLoginAccount", loginAccount);
	}
}
