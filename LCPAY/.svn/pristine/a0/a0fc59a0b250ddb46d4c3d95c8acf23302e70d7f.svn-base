package com.cifpay.lc.core.db.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cifpay.lc.core.db.pojo.LcLog;

@Repository
public interface LcLogDao {

	LcLog selectByPrimaryKey(Long logId);

	void deleteByPrimaryKey(Long logId);

	void insert(LcLog lcLog);

	void insertSelective(LcLog lcLog);

	void updateByPrimaryKeySelective(LcLog lcLog);

	void updateByPrimaryKey(LcLog lcLog);

	
	//----------------------------------------
	
	List<LcLog> selectByLcId(Long lcId);
}
