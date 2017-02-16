package com.cifpay.insurance.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cifpay.insurance.dao.InsReturnTypeDao;
import com.cifpay.insurance.model.InsReturnType;
import com.cifpay.insurance.service.InsReturnTypeService;
import com.cifpay.starframework.cache.ServiceResultCodeCache;
import com.cifpay.starframework.model.ServiceResult;

@Service("insReturnTypeService")
public class InsReturnTypeServiceImpl implements InsReturnTypeService {
	private static ServiceResultCodeCache resultCode = ServiceResultCodeCache.getInstance();
	@Autowired
	private InsReturnTypeDao insReturnTypeDao;

	@Override
	public InsReturnType get(long id) {
		return insReturnTypeDao.get(id);
	}

	@Override
	public ServiceResult<String> add(InsReturnType insReturnType) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insReturnTypeDao.add(insReturnType);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> addSelective(InsReturnType insReturnType) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insReturnTypeDao.addSelective(insReturnType);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> update(InsReturnType insReturnType) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insReturnTypeDao.update(insReturnType);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> updateSelective(InsReturnType insReturnType) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insReturnTypeDao.updateSelective(insReturnType);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> delete(InsReturnType insReturnType) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insReturnTypeDao.delete(insReturnType);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public List<InsReturnType> getList() {
		return insReturnTypeDao.getList();
	}

	@Override
	public int getCount() {
		return insReturnTypeDao.getCount();
	}

	@Override
	public InsReturnType getInsReturnTypeByCode(String code) {
		return insReturnTypeDao.getInsReturnTypeByCode(code);
	}
}
