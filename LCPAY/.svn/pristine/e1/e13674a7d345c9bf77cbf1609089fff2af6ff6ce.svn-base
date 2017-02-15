package com.cifpay.lc.core.db.dao;

import org.springframework.stereotype.Repository;

import com.cifpay.lc.core.db.pojo.AdminLcMerchant;

@Repository
public interface AdminLcMerchantDao {
    int deleteByPrimaryKey(Integer merId);

    int insert(AdminLcMerchant record);

    int insertSelective(AdminLcMerchant record);

    AdminLcMerchant selectByPrimaryKey(Integer merId);

    int updateByPrimaryKeySelective(AdminLcMerchant record);

    int updateByPrimaryKey(AdminLcMerchant record);
    
    AdminLcMerchant selectAdminLcMerchant(String merCode);
    
    AdminLcMerchant selectMerchantInfos(String merCode);
}