package com.cifpay.lc.core.db.dao;

import org.springframework.stereotype.Repository;

import com.cifpay.lc.core.db.pojo.AdminCredentials;

@Repository
public interface AdminCredentialsDao {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminCredentials record);

    int insertSelective(AdminCredentials record);

    AdminCredentials selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdminCredentials record);

    int updateByPrimaryKeyWithBLOBs(AdminCredentials record);

    int updateByPrimaryKey(AdminCredentials record);
    
    AdminCredentials selectAdminCredentials(String merCode);
}