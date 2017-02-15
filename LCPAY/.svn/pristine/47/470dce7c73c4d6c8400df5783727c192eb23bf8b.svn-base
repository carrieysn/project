package com.cifpay.lc.core.db.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.cifpay.lc.core.db.pojo.LcPageTemplateDetail;

@Repository
public interface LcPageTemplateDetailDao {

	LcPageTemplateDetail selectByPrimaryKey(@Param("templateCode") String templateCode, @Param("version") String version, @Param("templateFileName") String templateFileName);

	void deleteByPrimaryKey(@Param("templateCode") String templateCode, @Param("version") String version, @Param("templateFileName") String templateFileName);

	void insert(LcPageTemplateDetail lcPageTemplateDetail);

	void insertSelective(LcPageTemplateDetail lcPageTemplateDetail);

	void updateByPrimaryKeySelective(LcPageTemplateDetail lcPageTemplateDetail);

	void updateByPrimaryKey(LcPageTemplateDetail lcPageTemplateDetail);
	
	/*-------------------------------------------------------------------------------------*/
	
	List<LcPageTemplateDetail> selectByCodeVersion(@Param("templateCode") String templateCode, @Param("version") String version);
}
