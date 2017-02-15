package com.cifpay.lc.core.db.dao;

import org.springframework.stereotype.Repository;

import com.cifpay.lc.core.db.pojo.TradeType;

@Repository
public interface TradeTypeDao {

	TradeType selectByPrimaryKey(String tradeStatus);

	void deleteByPrimaryKey(String tradeStatus);

	void insert(TradeType tradeType);

	void insertSelective(TradeType tradeType);

	void updateByPrimaryKeySelective(TradeType tradeType);

	void updateByPrimaryKey(TradeType tradeType);

}
