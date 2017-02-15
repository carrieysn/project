package com.cifpay.lc.core.db.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.cifpay.lc.core.db.pojo.LcPageTemplate;

@Repository
public interface LcPageTemplateDao {

	LcPageTemplate selectByPrimaryKey(@Param("templateCode") String templateCode, @Param("version") String version);

	void deleteByPrimaryKey(@Param("templateCode") String templateCode, @Param("version") String version);

	void insert(LcPageTemplate lcPageTemplate);

	void insertSelective(LcPageTemplate lcPageTemplate);

	int updateByPrimaryKeySelective(LcPageTemplate lcPageTemplate);

	void updateByPrimaryKey(LcPageTemplate lcPageTemplate);
	
	//-----------------------------------------------
	
	List<LcPageTemplate> selectByTranStatus(int tranStatus);
	
	int lockRecord(@Param("templateCode") String templateCode, @Param("version") String version, 
			@Param("appNode") String appNode);
}
