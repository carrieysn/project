package com.cifpay.insurance.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cifpay.insurance.base.Page;
import com.cifpay.insurance.dao.InsUserDao;
import com.cifpay.insurance.model.InsUser;
import com.cifpay.insurance.param.user.GetUserAccountInfo;
import com.cifpay.insurance.service.InsUserService;
import com.cifpay.starframework.cache.ServiceResultCodeCache;
import com.cifpay.starframework.model.ServiceResult;

@Service("insUserService")
public class InsUserServiceImpl implements InsUserService {
	
	private static ServiceResultCodeCache resultCode = ServiceResultCodeCache.getInstance();
	@Autowired
	private InsUserDao insUserDao;

	@Override
	public InsUser get(long id) {
		return insUserDao.get(id);
	}

	@Override
	public ServiceResult<String> add(InsUser insUser) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insUserDao.add(insUser);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> addSelective(InsUser insUser) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insUserDao.addSelective(insUser);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> update(InsUser insUser) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insUserDao.update(insUser);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> updateSelective(InsUser insUser) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insUserDao.updateSelective(insUser);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> delete(InsUser insUser) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insUserDao.delete(insUser);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public List<InsUser> getList() {
		return insUserDao.getList();
	}

	@Override
	public int getCount() {
		return insUserDao.getCount();
	}

	@Override
	public InsUser getInsUserByUserAccount(String userAccount) {
		return insUserDao.getInsUserByUserAccount(userAccount);
	}

	@Override
	public List<InsUser> getInsUserList(GetUserAccountInfo bean,Page<InsUser> page) {
		return insUserDao.getInsUserList(bean,page);
	}
	@Override
	public InsUser getInsUserById(long id) {
		return insUserDao.get(id);
	}
	@Override
	public int updateUserBySelect(InsUser info) {
		info.setModifiedTime(new Date());//更新时间
		return insUserDao.updateInsUser(info);
     }
		
	@Override
	public int saveUserBySelect(InsUser info) {
		info.setCreatedTime(new Date());//新增时间
		return insUserDao.add(info);
    }
	@Override
	public int delUserById(InsUser info) {
		return insUserDao.delete(info);
    }

}
