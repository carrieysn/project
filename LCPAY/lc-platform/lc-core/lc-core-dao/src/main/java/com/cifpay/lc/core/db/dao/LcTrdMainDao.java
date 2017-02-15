package com.cifpay.lc.core.db.dao;

import org.springframework.stereotype.Repository;

import com.cifpay.lc.core.db.pojo.TrdMain;

@Repository
public interface LcTrdMainDao {

	TrdMain selectByPrimaryKey(Long businessId);
	
	TrdMain selectBySelective(TrdMain trdMain);

	void deleteByPrimaryKey(Long businessId);

	void insert(TrdMain trdMain);

	int insertSelective(TrdMain trdMain);

	void updateByPrimaryKeySelective(TrdMain trdMain);

	void updateByPrimaryKey(TrdMain trdMain);
	
	void updateResult(TrdMain trdMain);
}
