package com.cifpay.insurance.dao.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cifpay.insurance.dao.InsBankCardDao;
import com.cifpay.insurance.model.InsBankCard;
import com.cifpay.starframework.dao.impl.CommonDaoImpl;

@Repository("insBankCardDao")
public class InsBankCardDaoImpl extends CommonDaoImpl<InsBankCard> implements InsBankCardDao {
	@Override
	public InsBankCard get(long id) {
		return this.getSqlSession().selectOne(getStatementPrefix() + ".get", id);
	}

	@Override
	public List<InsBankCard> getList() {
		List<InsBankCard> resultList = this.getSqlSession().selectList(getStatementPrefix() + ".getList");
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
	public List<InsBankCard> getBankCardList() {
		List<InsBankCard> resultList = this.getSqlSession().selectList(getStatementPrefix() + ".getBankCardList");
		if (resultList == null) {
			resultList = Collections.emptyList();
		}
		return resultList;
	}
}
