package com.cifpay.lc.core.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.cifpay.lc.core.db.pojo.GuidWorkerId;

@Repository
public interface GuidWorkerIdDao {
	GuidWorkerId selectByPrimaryKey(@Param("machineId") String machineId, @Param("appInstanceId") String appInstanceId);

	void deleteByPrimaryKey(@Param("machineId") String machineId, @Param("appInstanceId") String appInstanceId);

	void insert(GuidWorkerId guidWorkerId);

	void insertSelective(GuidWorkerId guidWorkerId);

	void updateByPrimaryKeySelective(GuidWorkerId guidWorkerId);

	void updateByPrimaryKey(GuidWorkerId guidWorkerId);

	/**
	 * 查询出所有worker id
	 * 
	 * @return
	 */
	List<Integer> selectAllWorkerIds();

	/**
	 * 在表中当前最大的worker id值的基础上加1，新增一条worker id记录。
	 * 
	 * @param guidWorkerId
	 * @return
	 */
	void insertBaseCurrentMaxWorkerId(GuidWorkerId guidWorkerId);
	
	/**
	 * 统计同一个worker id被分配的次数，原则上一个worker id只会被分配给一个应用节点，如果出现重复分配的情况，需要重新分配。
	 * 
	 * @param workerId
	 * @return
	 */
	int countWithSameWorkerId(Integer workerId);

}
