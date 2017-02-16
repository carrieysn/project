package com.cifpay.insurance.dao.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cifpay.insurance.dao.InsInsuredAmountInfoDao;
import com.cifpay.insurance.model.InsInsuredAmountInfo;
import com.cifpay.starframework.dao.impl.CommonDaoImpl;

@Repository("insInsuredAmountInfoDao")
public class InsInsuredAmountInfoDaoImpl extends CommonDaoImpl<InsInsuredAmountInfo> implements InsInsuredAmountInfoDao {
	@Override
	public InsInsuredAmountInfo get(long id) {
		return this.getSqlSession().selectOne(getStatementPrefix() + ".get", id);
	}

	@Override
	public List<InsInsuredAmountInfo> getList() {
		List<InsInsuredAmountInfo> resultList = this.getSqlSession().selectList(getStatementPrefix() + ".getList");
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
	public InsInsuredAmountInfo getInsInsuredAmountInfoByPolicyId(Long policyId) {
		return this.getSqlSession().selectOne(getStatementPrefix() + ".getInsInsuredAmountInfoByPolicyId", policyId);
	}
}
