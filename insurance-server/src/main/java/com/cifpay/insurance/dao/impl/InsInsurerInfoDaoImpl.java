package com.cifpay.insurance.dao.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cifpay.insurance.dao.InsInsurerInfoDao;
import com.cifpay.insurance.model.InsInsurerInfo;
import com.cifpay.starframework.dao.impl.CommonDaoImpl;

@Repository("insInsurerInfoDao")
public class InsInsurerInfoDaoImpl extends CommonDaoImpl<InsInsurerInfo> implements InsInsurerInfoDao {
	@Override
	public InsInsurerInfo get(long ID) {
		return this.getSqlSession().selectOne(getStatementPrefix() + ".get", ID);
	}

	@Override
	public List<InsInsurerInfo> getList() {
		List<InsInsurerInfo> resultList = this.getSqlSession().selectList(getStatementPrefix() + ".getList");
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
	public InsInsurerInfo getOne() {
		return this.getSqlSession().selectOne(getStatementPrefix() + ".getOne");
	}
}
