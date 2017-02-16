package com.cifpay.insurance.dao;

import java.util.List;

import com.cifpay.insurance.model.InsReturnTrace;
import com.cifpay.starframework.dao.CommonDao;

public interface InsReturnTraceDao extends CommonDao<InsReturnTrace> {
	public InsReturnTrace get(long ID);

	public List<InsReturnTrace> getList();

	public int getCount();

	public List<InsReturnTrace> getListByCertNo(String certNo);
}
