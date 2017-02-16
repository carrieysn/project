package com.cifpay.insurance.dao.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cifpay.insurance.dao.InsPolicyHolderDao;
import com.cifpay.insurance.model.InsPolicyHolder;
import com.cifpay.starframework.dao.impl.CommonDaoImpl;

@Repository("insPolicyHolderDao")
public class InsPolicyHolderDaoImpl extends CommonDaoImpl<InsPolicyHolder>
		implements InsPolicyHolderDao {
	@Override
	public InsPolicyHolder get(long ID) {
		return this.getSqlSession()
				.selectOne(getStatementPrefix() + ".get", ID);
	}

	@Override
	public List<InsPolicyHolder> getList() {
		List<InsPolicyHolder> resultList = this.getSqlSession().selectList(
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
	public InsPolicyHolder getPolicyHolderByCertNo(String certNo) {
	     return this.getSqlSession().selectOne(getStatementPrefix()+".selectPolicyHolderByCertNo", certNo);
	}

	@Override
	public InsPolicyHolder getPolicyHolderByVendorId(String vendorId) {
		return this.getSqlSession().selectOne(getStatementPrefix()+".getPolicyHolderByVendorId", vendorId);
	}
}
