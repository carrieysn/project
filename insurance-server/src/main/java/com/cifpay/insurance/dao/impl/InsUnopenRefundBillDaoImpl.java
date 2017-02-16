package com.cifpay.insurance.dao.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cifpay.insurance.dao.InsUnopenRefundBillDao;
import com.cifpay.insurance.model.InsUnopenRefundBill;
import com.cifpay.starframework.dao.impl.CommonDaoImpl;

@Repository("insUnopenRefundBillDao")
public class InsUnopenRefundBillDaoImpl extends CommonDaoImpl<InsUnopenRefundBill> implements InsUnopenRefundBillDao {
	@Override
	public InsUnopenRefundBill get(long id) {
		return this.getSqlSession().selectOne(getStatementPrefix() + ".get", id);
	}

	@Override
	public List<InsUnopenRefundBill> getList() {
		List<InsUnopenRefundBill> resultList = this.getSqlSession().selectList(getStatementPrefix() + ".getList");
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
	public List<InsUnopenRefundBill> getUnexpiredInsUnopenRefundBills() {
		return this.getSqlSession().selectList(getStatementPrefix() + ".getUnexpiredInsUnopenRefundBills");
	}

	@Override
	public List<InsUnopenRefundBill> getExpiredInsUnopenRefundBills() {
		return this.getSqlSession().selectList(getStatementPrefix() + ".getExpiredInsUnopenRefundBills");
	}
}
