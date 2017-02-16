package com.cifpay.insurance.dao.impl;

import java.util.Collections;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.cifpay.insurance.base.Page;
import com.cifpay.insurance.dao.InsUserDao;
import com.cifpay.insurance.model.InsUser;
import com.cifpay.insurance.param.user.GetUserAccountInfo;
import com.cifpay.starframework.dao.impl.CommonDaoImpl;

@Repository("insUserDao")
public class InsUserDaoImpl extends CommonDaoImpl<InsUser> implements InsUserDao {
	@Override
	public InsUser get(long id) {
		return this.getSqlSession().selectOne(getStatementPrefix() + ".get", id);
	}

	@Override
	public List<InsUser> getList() {
		List<InsUser> resultList = this.getSqlSession().selectList(getStatementPrefix() + ".getList");
		if (resultList == null) {
			resultList = Collections.emptyList();
		}
		return resultList;
	}

	@Override
	public int getCount() {
		Integer result = (Integer) this.getSqlSession().selectOne(getStatementPrefix() + ".getCount");
		return result != null ? result.intValue() : 0;
	}

	@Override
	public InsUser getInsUserByUserAccount(String userAccount) {
		return this.getSqlSession().selectOne(getStatementPrefix() + ".getInsUserByUserAccount", userAccount);
	}

	@Override
	public List<InsUser> getInsUserList(GetUserAccountInfo bean,Page<InsUser> page) {
		List<InsUser> list = this.getSqlSession().selectList(getStatementPrefix()+".selectPageUser", bean, new RowBounds((page.getPageNo()-1)*page.getPageSize(),page.getPageSize()));
		page.setResult(list);
		if (list.size() > 0) {
			Integer result = (Integer) this.getSqlSession().selectOne(getStatementPrefix() + ".selectPageUserCount", bean);
			if (result != null) {
				page.setRecordCount(result);
			}
		}
		return list;
	}

	@Override
	public int updateInsUser(InsUser user) {
		return this.getSqlSession().update(getStatementPrefix() + ".updateSelectUserInfo", user);
	}
}
