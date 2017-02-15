package com.cifpay.lc.core.db.dao;

import org.springframework.stereotype.Repository;

import com.cifpay.lc.core.db.pojo.TrdFlow;

@Repository
public interface LcTrdFlowDao {

	int insertSelective(TrdFlow trdFlow);

	void updateResult(TrdFlow trdFlow);

	TrdFlow queryLastFlowByBizId(Long bizId);
}
