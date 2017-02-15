package com.cifpay.lc.core.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.cifpay.lc.core.db.pojo.LcRefund;

@Repository
public interface LcRefundDao {

	LcRefund selectByPrimaryKey(long lcRefundId);

	void deleteByPrimaryKey(long lcRefundId);

	int insert(LcRefund lcRefund);

	void insertSelective(LcRefund lcRefund);

	void updateByPrimaryKeySelective(LcRefund lcRefund);

	void updateByPrimaryKey(LcRefund lcRefund);

	List<LcRefund> selectByLcId(@Param("lcIds") Long... lcIds);

	List<LcRefund> selectByMidOrderId(@Param("mid") String merId, @Param("orderId") String orderId);
}
