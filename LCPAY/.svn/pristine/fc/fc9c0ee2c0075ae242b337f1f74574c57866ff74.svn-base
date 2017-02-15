package com.cifpay.lc.core.db.dao;

import com.cifpay.lc.core.db.pojo.AdminLcMerCre;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminLcMerCreDao {
    int deleteByPrimaryKey(Integer merId);

    int insert(AdminLcMerCre record);

    int insertSelective(AdminLcMerCre record);

    AdminLcMerCre selectByPrimaryKey(Integer merId);

    int updateByPrimaryKeySelective(AdminLcMerCre record);

    int updateByPrimaryKey(AdminLcMerCre record);

    /**
     * 查询商户号对应的“银联二级商户号”
     *
     * @param merCode
     * @param cardType
     * @return
     */
    List<AdminLcMerCre> selectByMerCodeAndCardType(@Param("merCode") String merCode, @Param("cardType") int cardType);
}