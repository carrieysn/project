package com.cifpay.lc.core.db.dao;

import com.cifpay.lc.core.db.pojo.AdminCifpayLcBank;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminCifpayLcBankDao {

    AdminCifpayLcBank selectByPrimaryKey(Integer id);

    int deleteByPrimaryKey(Integer id);

    int insert(AdminCifpayLcBank record);

    int insertSelective(AdminCifpayLcBank record);

    int updateByPrimaryKeySelective(AdminCifpayLcBank record);

    int updateByPrimaryKey(AdminCifpayLcBank record);


    AdminCifpayLcBank selectByBankCode(@Param("bankCode") String bankCode);

    List<AdminCifpayLcBank> selectAllValidRecords();
}
