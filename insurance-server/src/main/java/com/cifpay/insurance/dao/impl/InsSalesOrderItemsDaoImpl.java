package com.cifpay.insurance.dao.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cifpay.insurance.dao.InsSalesOrderItemsDao;
import com.cifpay.insurance.model.InsSalesOrderItems;
import com.cifpay.starframework.dao.impl.CommonDaoImpl;

@Repository("insSalesOrderItemsDao")
public class InsSalesOrderItemsDaoImpl extends CommonDaoImpl<InsSalesOrderItems> implements InsSalesOrderItemsDao {
	@Override
	public InsSalesOrderItems get(long id) {
		return this.getSqlSession().selectOne(getStatementPrefix() + ".get", id);
	}

	@Override
	public List<InsSalesOrderItems> getList() {
		List<InsSalesOrderItems> resultList = this.getSqlSession().selectList(getStatementPrefix() + ".getList");
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
	public int addBatch(List<InsSalesOrderItems> insSalesOrderItemsList) {
		 return getSqlSession().insert((new StringBuilder()).append(getStatementPrefix()).append(".addBatch").toString(), insSalesOrderItemsList);
	}
	@Override
	public InsSalesOrderItems getInsSalesOrderItemsByCertNo(String insuranceCertNo) {
		return this.getSqlSession().selectOne(getStatementPrefix() + ".getInsSalesOrderItemsByCertNo", insuranceCertNo);
	}
}
