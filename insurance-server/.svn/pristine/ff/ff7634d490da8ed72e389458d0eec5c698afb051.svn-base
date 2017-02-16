package com.cifpay.insurance.service;

import java.util.List;

import com.cifpay.insurance.base.Page;
import com.cifpay.insurance.model.InsUser;
import com.cifpay.insurance.param.user.GetUserAccountInfo;
import com.cifpay.starframework.service.CommonService;

public interface InsUserService extends CommonService<InsUser> {
	public InsUser get(long id);

	public List<InsUser> getList();

	public int getCount();
	
	/**
	 * 据用户账号获取用户信息
	 * 
	 * @param userAccount
	 * @return
	 */
	public InsUser getInsUserByUserAccount(String userAccount);
	
	/**
	 * 查询用户列表
	 * @param bean
	 * @param page
	 * @return
	 */
	public List<InsUser> getInsUserList(GetUserAccountInfo bean, Page<InsUser> page);
	
	/**
	 * 据用户ID获取用户信息
	 * 
	 * @param id
	 * @return
	 */
	public InsUser getInsUserById(long id);
	/**
	 * 更新用户信息
	 * @param info
	 * @return
	 */
	public int updateUserBySelect(InsUser info);
	
    /**
     * 新增用户
     * @param info
     * @return
     */
	public int saveUserBySelect(InsUser info);
	/**
	 * 删除用户
	 * @param info
	 * @return
	 */
	public int delUserById(InsUser info);
}
