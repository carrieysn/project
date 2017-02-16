package com.cifpay.insurance.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cifpay.insurance.dao.InsVendorReturnAddressDao;
import com.cifpay.insurance.exception.InsuranceBizRuntimeException;
import com.cifpay.insurance.model.InsPolicyHolder;
import com.cifpay.insurance.model.InsVendorReturnAddress;
import com.cifpay.insurance.service.InsPolicyHolderService;
import com.cifpay.insurance.service.InsVendorReturnAddressService;
import com.cifpay.starframework.cache.ServiceResultCodeCache;
import com.cifpay.starframework.model.ServiceResult;

@Service("insVendorReturnAddressService")
public class InsVendorReturnAddressServiceImpl implements InsVendorReturnAddressService {
	private static ServiceResultCodeCache resultCode = ServiceResultCodeCache.getInstance();
	@Autowired
	private InsVendorReturnAddressDao insVendorReturnAddressDao;
	
	@Autowired
	private InsPolicyHolderService insPolicyHolderService;

	@Override
	public InsVendorReturnAddress get(long id) {
		return insVendorReturnAddressDao.get(id);
	}

	@Override
	public ServiceResult<String> add(InsVendorReturnAddress insVendorReturnAddress) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insVendorReturnAddressDao.add(insVendorReturnAddress);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> addSelective(InsVendorReturnAddress insVendorReturnAddress) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insVendorReturnAddressDao.addSelective(insVendorReturnAddress);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> update(InsVendorReturnAddress insVendorReturnAddress) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insVendorReturnAddressDao.update(insVendorReturnAddress);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> updateSelective(InsVendorReturnAddress insVendorReturnAddress) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insVendorReturnAddressDao.updateSelective(insVendorReturnAddress);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> delete(InsVendorReturnAddress insVendorReturnAddress) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insVendorReturnAddressDao.delete(insVendorReturnAddress);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public List<InsVendorReturnAddress> getList() {
		return insVendorReturnAddressDao.getList();
	}

	@Override
	public int getCount() {
		return insVendorReturnAddressDao.getCount();
	}

	@Override
	public void saveInsVendorReturnAddress(InsVendorReturnAddress entity) {
		InsPolicyHolder insPolicyHolder = insPolicyHolderService.getPolicyHolderByVendorId(entity.getVendorId());
		if (insPolicyHolder == null) {
			throw new InsuranceBizRuntimeException(resultCode.get("biz.policy.holder.notfound"), "商户对应投保人信息不存在！");
		}
		int count = insVendorReturnAddressDao.getVendorReturnAddressCount(entity.getVendorId());
		if (count == 0) {//没有记录时，设置为默认退货地址。
			entity.setIsDefault(1);
		} else {
			entity.setIsDefault(0);
		}
		entity.setHolderName(insPolicyHolder.getHolderName());
		entity.setPolicyHolderId(insPolicyHolder.getId());
		entity.setIsValid(1);
		entity.setCreatedTime(new Date());
		entity.setModifiedTime(new Date());
		insVendorReturnAddressDao.add(entity);
	}

	@Override
	public List<InsVendorReturnAddress> getInsVendorReturnAddressList(String vendorId) {
		return insVendorReturnAddressDao.getInsVendorReturnAddressListByVendorId(vendorId);
	}

	@Transactional
	public void setDefaultInsVendorReturnAddress(String vendorId, Long id) {
		InsVendorReturnAddress ivr = insVendorReturnAddressDao.getValidInsVendorReturnAddress(id);
		if (ivr == null) {
			throw new InsuranceBizRuntimeException(resultCode.get("biz.vendor.returnaddress.notfound"), "指定退货地址id不存在！");
		}
		if (!ivr.getVendorId().equals(vendorId)) {
			throw new InsuranceBizRuntimeException(resultCode.get("biz.vendor.returnaddress.notfound"), "指定退货地址id不存在！");
		}
		if (ivr.getIsDefault() == 1) return;
		insVendorReturnAddressDao.updateInsVendorReturnAddressDefault(ivr.getId());//设置默认
		insVendorReturnAddressDao.updateOtherNotDefault(ivr.getId());//设置其它为非默认
	}

	@Override
	public void deleteInsVendorReturnAddress(String vendorId, Long id) {
		InsVendorReturnAddress ivr = insVendorReturnAddressDao.getValidInsVendorReturnAddress(id);
		if (ivr == null) {
			throw new InsuranceBizRuntimeException(resultCode.get("biz.vendor.returnaddress.notfound"), "指定退货地址id不存在！");
		}
		if (!ivr.getVendorId().equals(vendorId)) {
			throw new InsuranceBizRuntimeException(resultCode.get("biz.vendor.returnaddress.notfound"), "指定退货地址id不存在！");
		}
		if (ivr.getIsDefault() != null && ivr.getIsDefault() == 1) {
			throw new InsuranceBizRuntimeException(resultCode.get("biz.vendor.returnaddress.default.unbind.notallow"), "不能解绑默认退货地址！");
		}
		ivr.setIsValid(0);
		ivr.setModifiedTime(new Date());
		insVendorReturnAddressDao.update(ivr);
	}
	
	@Override
	public InsVendorReturnAddress getDefaultInsVendorReturnAddress(String vendorId) {
		return insVendorReturnAddressDao.getDefaultInsVendorReturnAddress(vendorId);
	}
}
