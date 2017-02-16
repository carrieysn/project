package com.cifpay.insurance.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cifpay.insurance.dao.InsVendorBankAccountDao;
import com.cifpay.insurance.exception.InsuranceBizRuntimeException;
import com.cifpay.insurance.model.InsVendorBankAccount;
import com.cifpay.insurance.service.InsVendorBankAccountService;
import com.cifpay.starframework.cache.ServiceResultCodeCache;
import com.cifpay.starframework.model.ServiceResult;

@Service("insVendorBankAccountService")
public class InsVendorBankAccountServiceImpl implements InsVendorBankAccountService {
	private static ServiceResultCodeCache resultCode = ServiceResultCodeCache.getInstance();
	@Autowired
	private InsVendorBankAccountDao insVendorBankAccountDao;

	@Override
	public InsVendorBankAccount get(long id) {
		return insVendorBankAccountDao.get(id);
	}

	@Override
	public ServiceResult<String> add(InsVendorBankAccount insVendorBankAccount) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insVendorBankAccountDao.add(insVendorBankAccount);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> addSelective(InsVendorBankAccount insVendorBankAccount) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insVendorBankAccountDao.addSelective(insVendorBankAccount);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> update(
			InsVendorBankAccount insVendorBankAccount) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insVendorBankAccountDao.update(insVendorBankAccount);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> updateSelective(
			InsVendorBankAccount insVendorBankAccount) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insVendorBankAccountDao
				.updateSelective(insVendorBankAccount);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> delete(
			InsVendorBankAccount insVendorBankAccount) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insVendorBankAccountDao.delete(insVendorBankAccount);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public List<InsVendorBankAccount> getList() {
		return insVendorBankAccountDao.getList();
	}

	@Override
	public int getCount() {
		return insVendorBankAccountDao.getCount();
	}

	@Override
	public void saveInsVendorBankAccount(InsVendorBankAccount entity) {
		int count = insVendorBankAccountDao.getSameBankAccountCount(entity);
		if (count > 0) {
			throw new InsuranceBizRuntimeException(resultCode.get("biz.vendor.bankaccount.exists"), "已存在银行卡号！");
		}
		count = insVendorBankAccountDao.getVendorBankAccountCount(entity.getVendorId());
		if (count == 0) {//没有记录时，设置为默认银行卡。
			entity.setIsDefault(1);
		} else {
			entity.setIsDefault(0);
		}
		entity.setIsValid(1);
		entity.setCreatedTime(new Date());
		entity.setModifiedTime(new Date());
		insVendorBankAccountDao.add(entity);
	}

	@Override
	public List<InsVendorBankAccount> getInsVendorBankAccountList(String vendorId) {
		return insVendorBankAccountDao.getInsVendorBankAccountListByVendorId(vendorId);
	}

	@Transactional
	public void setDefaultInsVendorBankAccount(String vendorId, String bankAccount) {
		InsVendorBankAccount ivb = insVendorBankAccountDao.getInsVendorBankAccount(vendorId, bankAccount);
		if (ivb == null) {
			throw new InsuranceBizRuntimeException(resultCode.get("biz.vendor.bankaccount.notfound"), "银行卡号不存在！");
		}
		if (ivb.getIsDefault() == 1) return;
		insVendorBankAccountDao.updateInsVendorBankAccountDefault(ivb.getId());//设置默认
		insVendorBankAccountDao.updateOtherNotDefault(ivb.getId());//设置其它为非默认
	}

	@Override
	public void unbindInsVendorBankAccount(String vendorId, String bankAccount) {
		InsVendorBankAccount ivb = insVendorBankAccountDao.getInsVendorBankAccount(vendorId, bankAccount);
		if (ivb == null) {
			throw new InsuranceBizRuntimeException(resultCode.get("biz.vendor.bankaccount.notfound"), "银行卡号不存在！");
		}
		if (ivb.getIsDefault() != null && ivb.getIsDefault() == 1) {
			throw new InsuranceBizRuntimeException(resultCode.get("biz.vendor.bankaccount.default.unbind.notallow"), "不能解绑默认银行卡！");
		}
		ivb.setIsValid(0);
		ivb.setModifiedTime(new Date());
		insVendorBankAccountDao.update(ivb);
	}
	
	@Override
	public InsVendorBankAccount getDefaultInsVendorBankAccount(String vendorId) {
		return insVendorBankAccountDao.getDefaultInsVendorBankAccount(vendorId);
	}
}
