package com.cifpay.lc.core.db.dao;

import com.cifpay.lc.core.db.pojo.AdminSmsTemplate;

public interface AdminSmsTemplateDao {
    int deleteByPrimaryKey(Long id);

    int insert(AdminSmsTemplate adminSmsTemplate);

    int insertSelective(AdminSmsTemplate adminSmsTemplate);

    AdminSmsTemplate selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AdminSmsTemplate adminSmsTemplate);

    int updateByPrimaryKey(AdminSmsTemplate adminSmsTemplate);
    
    /**
     * 通过短信模版类型查询短信模版
     * @param smsType
     * @return
     */
    AdminSmsTemplate selectBysmsType(int smsType);
}