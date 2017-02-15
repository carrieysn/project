package com.cifpay.lc.core.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.cifpay.lc.core.db.pojo.LcPage;

@Repository
public interface LcPageDao {

	LcPage selectByPrimaryKey(Long lcId);

	void deleteByPrimaryKey(Long lcId);

	void insert(LcPage lcPage);

	void insertSelective(LcPage lcPage);

	int updateByPrimaryKeySelective(LcPage lcPage);

	void updateByPrimaryKey(LcPage lcPage);

	
	//-----------------------------------------------
	
	List<LcPage> selectByTranStatus(int tranStatus);
	
	int updateStatus4ReGenLcPage(Long lcId);
	
	int lockRecord(@Param("lcId") Long lcId, @Param("appNode") String appNode);
	
	
}
