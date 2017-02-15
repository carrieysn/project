package com.cifpay.lc.core.db.dao;

import org.springframework.stereotype.Repository;

import com.cifpay.lc.core.db.pojo.LcSendOrg;

@Repository
public interface LcSendOrgDao {

	LcSendOrg selectByPrimaryKey(String orgId);

	void deleteByPrimaryKey(String orgId);

	void insert(LcSendOrg lcSendOrg);

	void insertSelective(LcSendOrg lcSendOrg);

	void updateByPrimaryKeySelective(LcSendOrg lcSendOrg);

	void updateByPrimaryKey(LcSendOrg lcSendOrg);

}
