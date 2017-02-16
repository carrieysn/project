package com.cifpay.insurance.dao.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cifpay.insurance.dao.InsSalesOrderDao;
import com.cifpay.insurance.model.InsSalesOrder;
import com.cifpay.starframework.dao.impl.CommonDaoImpl;

@Repository("insSalesOrderDao")
public class InsSalesOrderDaoImpl extends CommonDaoImpl<InsSalesOrder> implements InsSalesOrderDao {
	@Override
	public InsSalesOrder get(long id) {
		return this.getSqlSession().selectOne(getStatementPrefix() + ".get", id);
	}

	@Override
	public List<InsSalesOrder> getList() {
		List<InsSalesOrder> resultList = this.getSqlSession().selectList(getStatementPrefix() + ".getList");
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
	public InsSalesOrder getFullInsSalesOrder(String vendorId, String orderNo) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("vendorId", vendorId);
		params.put("orderNo", orderNo);
		return this.getSqlSession().selectOne(getStatementPrefix() + ".getFullInsSalesOrder", params);
	}
	
	
}
