package com.cifpay.insurance.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cifpay.insurance.base.Page;
import com.cifpay.insurance.dao.InsPolicyChangeListDao;
import com.cifpay.insurance.model.InsPolicyChangeList;
import com.cifpay.insurance.param.policy.GetPolicyChangeListInfo;
import com.cifpay.insurance.service.InsPolicyChangeListService;
import com.cifpay.starframework.cache.ServiceResultCodeCache;
import com.cifpay.starframework.model.ServiceResult;

@Service("insPolicyChangeListService")
public class InsPolicyChangeListServiceImpl implements InsPolicyChangeListService {
	private static ServiceResultCodeCache resultCode = ServiceResultCodeCache.getInstance();
	@Autowired
	private InsPolicyChangeListDao insPolicyChangeListDao;

	@Override
	public InsPolicyChangeList get(long id) {
		return insPolicyChangeListDao.get(id);
	}

	@Override
	public ServiceResult<String> add(InsPolicyChangeList insPolicyChangeList) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insPolicyChangeListDao.add(insPolicyChangeList);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> addSelective(InsPolicyChangeList insPolicyChangeList) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insPolicyChangeListDao.addSelective(insPolicyChangeList);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> update(InsPolicyChangeList insPolicyChangeList) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insPolicyChangeListDao.update(insPolicyChangeList);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> updateSelective(InsPolicyChangeList insPolicyChangeList) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insPolicyChangeListDao.updateSelective(insPolicyChangeList);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> delete(InsPolicyChangeList insPolicyChangeList) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insPolicyChangeListDao.delete(insPolicyChangeList);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public List<InsPolicyChangeList> getList() {
		return insPolicyChangeListDao.getList();
	}

	@Override
	public int getCount() {
		return insPolicyChangeListDao.getCount();
	}

	@Override
	public List<InsPolicyChangeList> getPolicyChangeList(String vendorId, GetPolicyChangeListInfo getPolicyChangeListInfo, Page<InsPolicyChangeList> page) {
		return insPolicyChangeListDao.getPolicyChangeList(vendorId, getPolicyChangeListInfo, page);
	}
}
