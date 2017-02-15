package com.cifpay.lc.core.db.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cifpay.lc.core.db.pojo.Message;

@Repository
public interface MessageDao {
	
	long selectPrimaryKey();
	
    int deleteByPrimaryKey(Long id);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Long id);
    
    List<Message> listMessageBySelective(Message message);

    boolean updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);
}