package com.cifpay.insurance.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cifpay.insurance.dao.InsInsurerInfoDao;
import com.cifpay.insurance.model.InsInsurerInfo;
import com.cifpay.insurance.service.InsInsurerInfoService;
import com.cifpay.starframework.cache.ServiceResultCodeCache;
import com.cifpay.starframework.model.ServiceResult;

@Service("insInsurerInfoService")
public class InsInsurerInfoServiceImpl implements InsInsurerInfoService {
	private static ServiceResultCodeCache resultCode = ServiceResultCodeCache
			.getInstance();
	@Autowired
	private InsInsurerInfoDao insInsurerInfoDao;

	@Override
	public InsInsurerInfo get(long ID) {
		InsInsurerInfo insInsurerInfo = null;
		insInsurerInfo = insInsurerInfoDao.get(ID);
		return insInsurerInfo;
	}

	@Override
	public ServiceResult<String> add(InsInsurerInfo insInsurerInfo) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insInsurerInfoDao.add(insInsurerInfo);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> addSelective(InsInsurerInfo insInsurerInfo) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insInsurerInfoDao.addSelective(insInsurerInfo);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> update(InsInsurerInfo insInsurerInfo) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insInsurerInfoDao.update(insInsurerInfo);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> updateSelective(InsInsurerInfo insInsurerInfo) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insInsurerInfoDao.updateSelective(insInsurerInfo);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> delete(InsInsurerInfo insInsurerInfo) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insInsurerInfoDao.delete(insInsurerInfo);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public List<InsInsurerInfo> getList() {
		return insInsurerInfoDao.getList();
	}

	@Override
	public int getCount() {
		return insInsurerInfoDao.getCount();
	}
	
	@Override
	public InsInsurerInfo getOne() {
		return insInsurerInfoDao.getOne();
	}
}
