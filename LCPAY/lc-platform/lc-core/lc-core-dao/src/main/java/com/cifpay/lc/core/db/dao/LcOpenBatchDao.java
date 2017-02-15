package com.cifpay.lc.core.db.dao;

import org.springframework.stereotype.Repository;

import com.cifpay.lc.core.db.pojo.LcOpenBatch;

@Repository
public interface LcOpenBatchDao {

	LcOpenBatch selectByPrimaryKey(Long batchOpenId);

	void deleteByPrimaryKey(Long batchOpenId);

	void insert(LcOpenBatch lcOpenBatch);

	void insertSelective(LcOpenBatch lcOpenBatch);

	void updateByPrimaryKeySelective(LcOpenBatch lcOpenBatch);

	void updateByPrimaryKey(LcOpenBatch lcOpenBatch);

}
