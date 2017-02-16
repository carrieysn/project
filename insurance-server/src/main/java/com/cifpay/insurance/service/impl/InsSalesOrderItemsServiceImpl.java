package com.cifpay.insurance.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cifpay.insurance.dao.InsSalesOrderItemsDao;
import com.cifpay.insurance.model.InsSalesOrderItems;
import com.cifpay.insurance.service.InsSalesOrderItemsService;
import com.cifpay.starframework.cache.ServiceResultCodeCache;
import com.cifpay.starframework.model.ServiceResult;

@Service("insSalesOrderItemsService")
public class InsSalesOrderItemsServiceImpl implements InsSalesOrderItemsService {
	private static ServiceResultCodeCache resultCode = ServiceResultCodeCache.getInstance();
	@Autowired
	private InsSalesOrderItemsDao insSalesOrderItemsDao;

	@Override
	public InsSalesOrderItems get(long id) {
		return insSalesOrderItemsDao.get(id);
	}

	@Override
	public ServiceResult<String> add(InsSalesOrderItems insSalesOrderItems) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insSalesOrderItemsDao.add(insSalesOrderItems);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> addSelective(InsSalesOrderItems insSalesOrderItems) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insSalesOrderItemsDao.addSelective(insSalesOrderItems);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> update(InsSalesOrderItems insSalesOrderItems) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insSalesOrderItemsDao.update(insSalesOrderItems);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> updateSelective(InsSalesOrderItems insSalesOrderItems) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insSalesOrderItemsDao.updateSelective(insSalesOrderItems);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> delete(InsSalesOrderItems insSalesOrderItems) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insSalesOrderItemsDao.delete(insSalesOrderItems);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public List<InsSalesOrderItems> getList() {
		return insSalesOrderItemsDao.getList();
	}

	@Override
	public int getCount() {
		return insSalesOrderItemsDao.getCount();
	}

	@Override
	public InsSalesOrderItems getInsSalesOrderItemsByCertNo(String insuranceCertNo) {
		return insSalesOrderItemsDao.getInsSalesOrderItemsByCertNo(insuranceCertNo);
	}
}
