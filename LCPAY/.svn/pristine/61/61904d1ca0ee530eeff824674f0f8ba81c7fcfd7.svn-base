package com.cifpay.lc.core.db.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.cifpay.lc.core.db.pojo.LcConfirmPay;

@Repository
public interface LcConfirmPayDao {

	LcConfirmPay selectByPrimaryKey(Long lcConfirmId);

	void deleteByPrimaryKey(Long lcConfirmId);

	void insert(LcConfirmPay lcConfirmPay);

	void insertSelective(LcConfirmPay lcConfirmPay);

	void updateByPrimaryKeySelective(LcConfirmPay lcConfirmPay);

	void updateByPrimaryKey(LcConfirmPay lcConfirmPay);
	
	
	
	
	//------------------------------------------------------------

	List<LcConfirmPay> select4ExpiredAutoTransfer();

	/**
	 * 按履约ID查询申请解付记录
	 * 
	 * @param lcSendIds
	 * @return
	 */
	List<LcConfirmPay> selectBylcSendIds(@Param("lcSendIds") Long... lcSendIds);
	
	List<LcConfirmPay> selectByLcId(@Param("lcId") Long lcId);
	

	int updateProcessStatus(@Param("processStatus") int processStatus, @Param("updateTime") Date updateTime, @Param("lcConfirmIds") Long... lcConfirmIds);
}
