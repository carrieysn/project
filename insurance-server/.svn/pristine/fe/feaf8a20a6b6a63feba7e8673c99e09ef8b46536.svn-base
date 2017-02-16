package com.cifpay.insurance.dao.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.cifpay.insurance.base.Page;
import com.cifpay.insurance.dao.InsCertRefundBillDao;
import com.cifpay.insurance.model.InsCertRefundBill;
import com.cifpay.insurance.param.refund.GetRefundBillInfo;
import com.cifpay.starframework.dao.impl.CommonDaoImpl;

@Repository("insCertRefundBillDao")
public class InsCertRefundBillDaoImpl extends CommonDaoImpl<InsCertRefundBill> implements InsCertRefundBillDao {
	@Override
	public InsCertRefundBill get(long id) {
		return this.getSqlSession().selectOne(getStatementPrefix() + ".get", id);
	}

	@Override
	public List<InsCertRefundBill> getList() {
		List<InsCertRefundBill> resultList = this.getSqlSession().selectList(getStatementPrefix() + ".getList");
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
	public int updateInRefundState(InsCertRefundBill entity) {
		return getSqlSession().update((new StringBuilder()).append(getStatementPrefix()).append(".updateInRefundState").toString(), entity);
	}

	@Override
	public int updateRefundSuccessState(InsCertRefundBill entity) {
		return getSqlSession().update((new StringBuilder()).append(getStatementPrefix()).append(".updateRefundSuccessState").toString(), entity);
	}

	@Override
	public int updateRefundFailState(InsCertRefundBill entity) {
		return getSqlSession().update((new StringBuilder()).append(getStatementPrefix()).append(".updateRefundFailState").toString(), entity);
	}
	
	@Override
	public InsCertRefundBill getInsCertRefundBillByOrderId(String lcId, String orderId) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("lcId", lcId);
		params.put("orderId", orderId);
		return this.getSqlSession().selectOne(getStatementPrefix() + ".getInsCertRefundBillByOrderId", params);
	}
	
	@Override
	public int updateOpenFailState(InsCertRefundBill entity) {
		return getSqlSession().update((new StringBuilder()).append(getStatementPrefix()).append(".updateOpenFailState").toString(), entity);
	}
	
	@Override
	public int updateOpenSuccessState(InsCertRefundBill entity) {
		return getSqlSession().update((new StringBuilder()).append(getStatementPrefix()).append(".updateOpenSuccessState").toString(), entity);
	}
	
	@Override
	public int updateRefuseRefundState(InsCertRefundBill entity) {
		return getSqlSession().update((new StringBuilder()).append(getStatementPrefix()).append(".updateRefuseRefundState").toString(), entity);
	}
	
	@Override
	public InsCertRefundBill getInsCertRefundBillByCertNo(String insuranceCertNo) {
		return this.getSqlSession().selectOne(getStatementPrefix() + ".getInsCertRefundBillByCertNo", insuranceCertNo);
	}

	@Override
	public List<InsCertRefundBill> getInsCertRefundBills(GetRefundBillInfo bean, Page<InsCertRefundBill> page) {
		List<InsCertRefundBill> list = null;
		if(page != null){
			list = this.getSqlSession().selectList(getStatementPrefix()+".selectPageRefundBills", bean, new RowBounds((page.getPageNo()-1)*page.getPageSize(),page.getPageSize()));
			page.setResult(list);
			if (list.size() > 0) {
				Integer result = (Integer) this.getSqlSession().selectOne(getStatementPrefix() + ".selectRefundBillCount", bean);
				if (result != null) {
					page.setRecordCount(result);
				}
			}
		}else{
			list = this.getSqlSession().selectList(getStatementPrefix()+".selectPageRefundBills", bean);
		}
		return list;
	}
}
