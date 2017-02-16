package com.cifpay.insurance.dao;

import java.util.List;

import com.cifpay.insurance.base.Page;
import com.cifpay.insurance.model.InsUser;
import com.cifpay.insurance.param.user.GetUserAccountInfo;
import com.cifpay.starframework.dao.CommonDao;

public interface InsUserDao extends CommonDao<InsUser> {
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
	public List<InsUser> getInsUserList(GetUserAccountInfo bean,Page<InsUser> page);
	
	/**
	 * 更新用户信息
	 * @param user
	 */
	public int updateInsUser(InsUser user);
	
}
