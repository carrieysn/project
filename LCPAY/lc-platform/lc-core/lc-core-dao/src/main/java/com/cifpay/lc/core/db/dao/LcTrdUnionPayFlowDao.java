package com.cifpay.lc.core.db.dao;

import org.springframework.stereotype.Repository;

import com.cifpay.lc.core.db.pojo.TrdUnionPayFlow;

@Repository
public interface LcTrdUnionPayFlowDao {
	
    int deleteByPrimaryKey(Long flowId);

    int insert(TrdUnionPayFlow record);

    int insertSelective(TrdUnionPayFlow record);

    TrdUnionPayFlow selectByPrimaryKey(Long flowId);

    int updateByPrimaryKeySelective(TrdUnionPayFlow record);

    int updateByPrimaryKey(TrdUnionPayFlow record);
    
    void updateUnionPayFlow(TrdUnionPayFlow record);
    
    void updateUnionPayFlowAsyn(TrdUnionPayFlow record);
}