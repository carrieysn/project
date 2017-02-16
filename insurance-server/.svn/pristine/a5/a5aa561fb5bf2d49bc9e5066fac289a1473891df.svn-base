package com.cifpay.insurance.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cifpay.insurance.dao.InsInsuredAmountInfoDao;
import com.cifpay.insurance.model.InsInsuredAmountInfo;
import com.cifpay.insurance.service.InsInsuredAmountInfoService;
import com.cifpay.starframework.cache.ServiceResultCodeCache;
import com.cifpay.starframework.model.ServiceResult;

@Service("insInsuredAmountInfoService")
public class InsInsuredAmountInfoServiceImpl implements InsInsuredAmountInfoService {
	private static ServiceResultCodeCache resultCode = ServiceResultCodeCache.getInstance();
	@Autowired
	private InsInsuredAmountInfoDao insInsuredAmountInfoDao;

	@Override
	public InsInsuredAmountInfo get(long id) {
		return insInsuredAmountInfoDao.get(id);
	}

	@Override
	public ServiceResult<String> add(InsInsuredAmountInfo insInsuredAmountInfo) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insInsuredAmountInfoDao.add(insInsuredAmountInfo);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> addSelective(InsInsuredAmountInfo insInsuredAmountInfo) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insInsuredAmountInfoDao.addSelective(insInsuredAmountInfo);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> update(InsInsuredAmountInfo insInsuredAmountInfo) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insInsuredAmountInfoDao.update(insInsuredAmountInfo);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> updateSelective(InsInsuredAmountInfo insInsuredAmountInfo) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insInsuredAmountInfoDao.updateSelective(insInsuredAmountInfo);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> delete(InsInsuredAmountInfo insInsuredAmountInfo) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insInsuredAmountInfoDao.delete(insInsuredAmountInfo);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public List<InsInsuredAmountInfo> getList() {
		return insInsuredAmountInfoDao.getList();
	}

	@Override
	public int getCount() {
		return insInsuredAmountInfoDao.getCount();
	}
	
	@Override
	public InsInsuredAmountInfo getInsInsuredAmountInfoByPolicyId(Long policyId) {
		return insInsuredAmountInfoDao.getInsInsuredAmountInfoByPolicyId(policyId);
	}
}
