package com.cifpay.insurance.dao.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cifpay.insurance.dao.InsVendorReturnAddressDao;
import com.cifpay.insurance.model.InsVendorReturnAddress;
import com.cifpay.starframework.dao.impl.CommonDaoImpl;

@Repository("insVendorReturnAddressDao")
public class InsVendorReturnAddressDaoImpl extends CommonDaoImpl<InsVendorReturnAddress> implements InsVendorReturnAddressDao {
	@Override
	public InsVendorReturnAddress get(long id) {
		return this.getSqlSession().selectOne(getStatementPrefix() + ".get", id);
	}

	@Override
	public List<InsVendorReturnAddress> getList() {
		List<InsVendorReturnAddress> resultList = this.getSqlSession().selectList(getStatementPrefix() + ".getList");
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
	public List<InsVendorReturnAddress> getInsVendorReturnAddressListByVendorId(String vendorId) {
		List<InsVendorReturnAddress> resultList = this.getSqlSession().selectList(getStatementPrefix() + ".getInsVendorReturnAddressListByVendorId", vendorId);
		if (resultList == null) {
			resultList = Collections.emptyList();
		}
		return resultList;
	}

	@Override
	public int getVendorReturnAddressCount(String vendorId) {
		Integer result = (Integer) this.getSqlSession().selectOne(getStatementPrefix() + ".getVendorReturnAddressCount", vendorId);
		return result != null ? result.intValue() : 0;
	}

	@Override
	public int updateOtherNotDefault(Long excludeId) {
		return this.getSqlSession().update(getStatementPrefix() + ".updateOtherNotDefault", excludeId);
	}

	@Override
	public int updateInsVendorReturnAddressDefault(long id) {
		return this.getSqlSession().update(getStatementPrefix() + ".updateInsVendorReturnAddressDefault", id);
	}

	@Override
	public InsVendorReturnAddress getValidInsVendorReturnAddress(long id) {
		return this.getSqlSession().selectOne(getStatementPrefix() + ".getValidInsVendorReturnAddress", id);
	}
	
	@Override
	public InsVendorReturnAddress getDefaultInsVendorReturnAddress(String vendorId) {
		return this.getSqlSession().selectOne(getStatementPrefix() + ".getDefaultInsVendorReturnAddress", vendorId);
	}
}
