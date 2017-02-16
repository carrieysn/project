package com.cifpay.insurance.dao.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cifpay.insurance.dao.InsVendorBankAccountDao;
import com.cifpay.insurance.model.InsVendorBankAccount;
import com.cifpay.starframework.dao.impl.CommonDaoImpl;

@Repository("insVendorBankAccountDao")
public class InsVendorBankAccountDaoImpl extends CommonDaoImpl<InsVendorBankAccount> implements InsVendorBankAccountDao {
	@Override
	public InsVendorBankAccount get(long id) {
		return this.getSqlSession().selectOne(getStatementPrefix() + ".get", id);
	}

	@Override
	public List<InsVendorBankAccount> getList() {
		List<InsVendorBankAccount> resultList = this.getSqlSession().selectList(getStatementPrefix() + ".getList");
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
	public int getSameBankAccountCount(InsVendorBankAccount insVendorBankAccount) {
		Integer result = (Integer) this.getSqlSession().selectOne(getStatementPrefix() + ".getSameBankAccountCount", insVendorBankAccount);
		return result != null ? result.intValue() : 0;
	}

	@Override
	public InsVendorBankAccount getInsVendorBankAccount(String vendorId, String bankAccount) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("vendorId", vendorId);
		params.put("bankAccount", bankAccount);
		return this.getSqlSession().selectOne(getStatementPrefix() + ".getInsVendorBankAccount", params);
	}

	@Override
	public List<InsVendorBankAccount> getInsVendorBankAccountListByVendorId(String vendorId) {
		List<InsVendorBankAccount> resultList = this.getSqlSession().selectList(getStatementPrefix() + ".getInsVendorBankAccountListByVendorId", vendorId);
		if (resultList == null) {
			resultList = Collections.emptyList();
		}
		return resultList;
	}

	@Override
	public int getVendorBankAccountCount(String vendorId) {
		Integer result = (Integer) this.getSqlSession().selectOne(getStatementPrefix() + ".getVendorBankAccountCount", vendorId);
		return result != null ? result.intValue() : 0;
	}

	@Override
	public int updateOtherNotDefault(Long excludeId) {
		return this.getSqlSession().update(getStatementPrefix() + ".updateOtherNotDefault", excludeId);
	}

	@Override
	public int updateInsVendorBankAccountDefault(long id) {
		return this.getSqlSession().update(getStatementPrefix() + ".updateInsVendorBankAccountDefault", id);
	}
	
	@Override
	public InsVendorBankAccount getDefaultInsVendorBankAccount(String vendorId) {
		return this.getSqlSession().selectOne(getStatementPrefix() + ".getDefaultInsVendorBankAccount", vendorId);
	}
	
}
