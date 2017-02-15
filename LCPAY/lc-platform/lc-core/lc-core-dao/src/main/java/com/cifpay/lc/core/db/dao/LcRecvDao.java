package com.cifpay.lc.core.db.dao;

import org.springframework.stereotype.Repository;

import com.cifpay.lc.core.db.pojo.LcRecv;

import java.util.List;

@Repository
public interface LcRecvDao {

    LcRecv selectByPrimaryKey(Long lcRecvId);

    List<LcRecv> selectByLcId(Long lcId);

    void deleteByPrimaryKey(Long lcRecvId);

    void insert(LcRecv lcRecv);

    void insertSelective(LcRecv lcRecv);

    void updateByPrimaryKeySelective(LcRecv lcRecv);

    void updateByPrimaryKey(LcRecv lcRecv);

}
