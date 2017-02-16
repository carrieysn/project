package com.cifpay.insurance.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cifpay.insurance.dao.InsReturnTraceDao;
import com.cifpay.insurance.model.InsReturnTrace;
import com.cifpay.insurance.service.InsReturnTraceService;
import com.cifpay.starframework.cache.ServiceResultCodeCache;
import com.cifpay.starframework.model.ServiceResult;

@Service("insReturnTraceService")
public class InsReturnTraceServiceImpl implements InsReturnTraceService {
	private static ServiceResultCodeCache resultCode = ServiceResultCodeCache
			.getInstance();
	@Autowired
	private InsReturnTraceDao insReturnTraceDao;

	@Override
	public InsReturnTrace get(long ID) {
		return insReturnTraceDao.get(ID);
	}

	@Override
	public ServiceResult<String> add(InsReturnTrace insReturnTrace) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insReturnTraceDao.add(insReturnTrace);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> addSelective(InsReturnTrace insReturnTrace) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insReturnTraceDao.addSelective(insReturnTrace);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> update(InsReturnTrace insReturnTrace) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insReturnTraceDao.update(insReturnTrace);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> updateSelective(InsReturnTrace insReturnTrace) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insReturnTraceDao.updateSelective(insReturnTrace);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> delete(InsReturnTrace insReturnTrace) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insReturnTraceDao.delete(insReturnTrace);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public List<InsReturnTrace> getList() {
		return insReturnTraceDao.getList();
	}

	@Override
	public int getCount() {
		return insReturnTraceDao.getCount();
	}

	@Override
	public List<InsReturnTrace> getListByCertNo(String certNo) {
	   List<InsReturnTrace> returnTraceList = new ArrayList<InsReturnTrace>();
	   returnTraceList = insReturnTraceDao.getListByCertNo(certNo);
	   return returnTraceList;
	}
}
