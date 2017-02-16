package com.cifpay.insurance.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cifpay.insurance.dao.InsPolicyHolderDao;
import com.cifpay.insurance.model.InsPolicyHolder;
import com.cifpay.insurance.service.InsPolicyHolderService;
import com.cifpay.starframework.cache.ServiceResultCodeCache;
import com.cifpay.starframework.model.ServiceResult;

@Service("insPolicyHolderService")
public class InsPolicyHolderServiceImpl implements InsPolicyHolderService {
	private static ServiceResultCodeCache resultCode = ServiceResultCodeCache.getInstance();
	@Autowired
	private InsPolicyHolderDao insPolicyHolderDao;

	@Override
	public InsPolicyHolder get(long ID) {
		return insPolicyHolderDao.get(ID);
	}

	@Override
	public ServiceResult<String> add(InsPolicyHolder insPolicyHolder) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insPolicyHolderDao.add(insPolicyHolder);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> addSelective(InsPolicyHolder insPolicyHolder) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insPolicyHolderDao.addSelective(insPolicyHolder);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> update(InsPolicyHolder insPolicyHolder) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insPolicyHolderDao.update(insPolicyHolder);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> updateSelective(InsPolicyHolder insPolicyHolder) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insPolicyHolderDao.updateSelective(insPolicyHolder);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> delete(InsPolicyHolder insPolicyHolder) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insPolicyHolderDao.delete(insPolicyHolder);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public List<InsPolicyHolder> getList() {
		return insPolicyHolderDao.getList();
	}

	@Override
	public int getCount() {
		return insPolicyHolderDao.getCount();
	}
    /**
     * 根据保险证号查询投保人信息
     */
	@Override
	public InsPolicyHolder getInsPolicyHolderByCertNo(String certNo) {
		return insPolicyHolderDao.getPolicyHolderByCertNo(certNo);
	}

	@Override
	public InsPolicyHolder getPolicyHolderByVendorId(String vendorId) {
		return insPolicyHolderDao.getPolicyHolderByVendorId(vendorId);
	}
}
