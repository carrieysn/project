package com.cifpay.insurance.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cifpay.insurance.dao.InsVendorDao;
import com.cifpay.insurance.model.InsVendor;
import com.cifpay.insurance.service.InsVendorService;
import com.cifpay.starframework.cache.ServiceResultCodeCache;
import com.cifpay.starframework.model.ServiceResult;

@Service("insVendorService")
public class InsVendorServiceImpl implements InsVendorService {
	private static ServiceResultCodeCache resultCode = ServiceResultCodeCache.getInstance();
	@Autowired
	private InsVendorDao insVendorDao;

	@Override
	public InsVendor get(long id) {
		return insVendorDao.get(id);
	}

	@Override
	public ServiceResult<String> add(InsVendor insVendor) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insVendorDao.add(insVendor);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> addSelective(InsVendor insVendor) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insVendorDao.addSelective(insVendor);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> update(InsVendor insVendor) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insVendorDao.update(insVendor);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> updateSelective(InsVendor insVendor) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insVendorDao.updateSelective(insVendor);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> delete(InsVendor insVendor) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insVendorDao.delete(insVendor);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public List<InsVendor> getList() {
		return insVendorDao.getList();
	}

	@Override
	public int getCount() {
		return insVendorDao.getCount();
	}

	@Override
	public InsVendor getInsVendorByLoginAccount(String loginAccount) {
		return insVendorDao.getInsVendorByLoginAccount(loginAccount);
	}
}
