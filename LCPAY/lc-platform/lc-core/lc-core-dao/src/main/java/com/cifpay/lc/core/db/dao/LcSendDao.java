package com.cifpay.lc.core.db.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.cifpay.lc.core.db.pojo.LcSend;

@Repository
public interface LcSendDao {

    LcSend selectByPrimaryKey(Long lcSendId);

    void deleteByPrimaryKey(Long lcSendId);

    boolean insert(LcSend lcSend);

    void insertSelective(LcSend lcSend);

    int updateByPrimaryKeySelective(LcSend lcSend);

    void updateByPrimaryKey(LcSend lcSend);

    List<LcSend> selectBylcSendIds(@Param("lcSendIds") Long... lcSendIds);

    List<LcSend> selectBylcId(@Param("lcId") Long lcId);

    List<LcSend> selectBylcIds(@Param("lcIds") Long... lcIds);

    /**
     * 更新步骤状态
     *
     * @param processStatus
     * @param updateTime
     * @param lcSendIds
     */
    boolean updateProcessStatus(@Param("processStatus") int processStatus, @Param("updateTime") Date updateTime, @Param("lcSendIds") Long... lcSendIds);
}
