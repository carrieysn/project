package com.cifpay.insurance.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cifpay.insurance.dao.InsBankCardDao;
import com.cifpay.insurance.model.InsBankCard;
import com.cifpay.insurance.service.InsBankCardService;
import com.cifpay.starframework.cache.ServiceResultCodeCache;
import com.cifpay.starframework.model.ServiceResult;

@Service("insBankCardService")
public class InsBankCardServiceImpl implements InsBankCardService {
	private static ServiceResultCodeCache resultCode = ServiceResultCodeCache.getInstance();
	@Autowired
	private InsBankCardDao insBankCardDao;

	@Override
	public InsBankCard get(long id) {
		return insBankCardDao.get(id);
	}

	@Override
	public ServiceResult<String> add(InsBankCard insBankCard) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insBankCardDao.add(insBankCard);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> addSelective(InsBankCard insBankCard) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insBankCardDao.addSelective(insBankCard);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> update(InsBankCard insBankCard) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insBankCardDao.update(insBankCard);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> updateSelective(InsBankCard insBankCard) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insBankCardDao.updateSelective(insBankCard);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> delete(InsBankCard insBankCard) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insBankCardDao.delete(insBankCard);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public List<InsBankCard> getList() {
		return insBankCardDao.getList();
	}

	@Override
	public int getCount() {
		return insBankCardDao.getCount();
	}

	@Override
	public List<InsBankCard> getBankCardList() {
		return insBankCardDao.getBankCardList();
	}
}
