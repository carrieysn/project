package com.cifpay.lc.core.db.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cifpay.lc.core.db.pojo.PreLc;

import org.apache.ibatis.annotations.Param;

@Repository
public interface PreLcDao {

//	PreLc selectByPrimaryKey(Long lcId);

    void deleteByPrimaryKey(Long lcId);

    boolean insert(PreLc preLc);

    void insertSelective(PreLc preLc);

    void updateByPrimaryKeySelective(PreLc preLc);

    void updateByPrimaryKey(PreLc preLc);

    PreLc selectByLcId(Long lcId);

    /**
     * 查询订单号对应的预开证记录
     *
     * @param mid     商户号
     * @param orderId 订单号
     * @return
     */
    List<PreLc> selectByMidOrderId(@Param("mid") String mid, @Param("orderId") String orderId);

    /**
     * 软删除数据
     *
     * @param lcId
     */
    boolean softDeleteByLcId(@Param("lcId") Long lcId);

    boolean softDeleteByLcIds(@Param("lcIds") Long... lcIds);

    /**
     * 批量插入记录
     *
     * @param preLcList
     */
    void bulkInsert(@Param("preLcList") List<PreLc> preLcList);

    /**
     * 查询批次下所有的预开证记录
     *
     * @param openBatchId
     * @return
     */
    List<PreLc> selectByOpenBatchId(@Param("openBatchId") Long openBatchId);

    /**
     * 根据商户号和订单查询 并按创建时间倒序排序
     *
     * @param mid
     * @param orderId
     * @return
     */
    List<PreLc> selectByMidOrderIdDesc(@Param("mid") String mid, @Param("orderId") String orderId);
}
