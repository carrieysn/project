package com.cifpay.insurance.dao;

import java.util.List;

import com.cifpay.insurance.model.InsReturnType;
import com.cifpay.starframework.dao.CommonDao;

public interface InsReturnTypeDao extends CommonDao<InsReturnType> {
	public InsReturnType get(long id);

	public List<InsReturnType> getList();

	public int getCount();
	
	/**
	 * 获取退货类型
	 * 
	 * @param code
	 * @return
	 */
	public InsReturnType getInsReturnTypeByCode(String code);
}
