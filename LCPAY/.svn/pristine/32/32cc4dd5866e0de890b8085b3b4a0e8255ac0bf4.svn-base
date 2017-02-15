package com.cifpay.lc.core.db.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cifpay.lc.core.db.pojo.UnionPayTrdToken;

@Repository
public interface LcTrdUnionPayTokenDao {

	UnionPayTrdToken selectByPrimaryKey(Map<String,String> map);

	UnionPayTrdToken selectBySelective(UnionPayTrdToken unionPayTrdToken);

	void deleteByPrimaryKey(String merId, String userId);

	void insert(UnionPayTrdToken unionPayTrdToken);
	
	int insertSelective(UnionPayTrdToken unionPayTrdToken);

	void updateByPrimaryKeySelective(UnionPayTrdToken unionPayTrdToken);

	void updateByPrimaryKey(UnionPayTrdToken unionPayTrdToken);

	void updateTokenResult(UnionPayTrdToken unionPayTrdToken);
	
	
	
}
