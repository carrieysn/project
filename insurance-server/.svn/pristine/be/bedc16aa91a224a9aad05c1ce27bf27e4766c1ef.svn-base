package com.cifpay.insurance.dao.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.cifpay.insurance.base.Page;
import com.cifpay.insurance.dao.InsPolicyOrderDao;
import com.cifpay.insurance.model.InsPolicyOrder;
import com.cifpay.insurance.param.policy.GetPolicyOrderListInfo;
import com.cifpay.starframework.dao.impl.CommonDaoImpl;

@Repository("insPolicyOrderDao")
public class InsPolicyOrderDaoImpl extends CommonDaoImpl<InsPolicyOrder> implements InsPolicyOrderDao {
	@Override
	public InsPolicyOrder get(long id) {
		return this.getSqlSession().selectOne(getStatementPrefix() + ".get", id);
	}

	@Override
	public List<InsPolicyOrder> getList() {
		List<InsPolicyOrder> resultList = this.getSqlSession().selectList(getStatementPrefix() + ".getList");
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
	public List<InsPolicyOrder> getInsPolicyOrderList(GetPolicyOrderListInfo getChargeFeePolicyOrderListInfo, Page<InsPolicyOrder> page) {
		List<InsPolicyOrder> list = this.getSqlSession().selectList(getStatementPrefix()+".getInsPolicyOrderList", getChargeFeePolicyOrderListInfo, new RowBounds((page.getPageNo()-1)*page.getPageSize(),page.getPageSize()));
		page.setResult(list);
		if (list.size() > 0) {
			Integer result = (Integer) this.getSqlSession().selectOne(getStatementPrefix() + ".getInsPolicyOrderListCount", getChargeFeePolicyOrderListInfo);
			if (result != null) {
				page.setRecordCount(result);
			}
		}
		return list;
	}
	
	@Override
	public InsPolicyOrder getInsPolicyOrder(String billNo, String lcId) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("billNo", billNo);
		params.put("lcId", lcId);
		return this.getSqlSession().selectOne(getStatementPrefix() + ".getInsPolicyOrder", params);
	}
	
	@Override
	public InsPolicyOrder getInsPolicyOrderByBillNo(String billNo) {
		return getInsPolicyOrder(billNo, null);
	}
}
