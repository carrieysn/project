package com.cifpay.lc.core.db.dao;

import org.springframework.stereotype.Repository;

import com.cifpay.lc.core.db.pojo.LcType;

@Repository
public interface LcTypeDao {

	LcType selectByPrimaryKey(String lcType);

	void deleteByPrimaryKey(String lcType);

	void insert(LcType lcType);

	void insertSelective(LcType lcType);

	void updateByPrimaryKeySelective(LcType lcType);

	void updateByPrimaryKey(LcType lcType);

}
