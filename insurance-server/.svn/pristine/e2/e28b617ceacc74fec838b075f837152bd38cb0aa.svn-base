package com.cifpay.insurance.service;

import java.util.List;

import com.cifpay.insurance.model.InsVendorBankAccount;
import com.cifpay.starframework.service.CommonService;

public interface InsVendorBankAccountService extends CommonService<InsVendorBankAccount> {
	public InsVendorBankAccount get(long id);

	public List<InsVendorBankAccount> getList();

	public int getCount();

	/**
	 * 保存商户银行卡
	 * 
	 * @param entity
	 */
	public void saveInsVendorBankAccount(InsVendorBankAccount entity);

	/**
	 * 据商户ID获取商户银行卡列表信息
	 * 
	 * @param vendorId
	 * @return
	 */
	public List<InsVendorBankAccount> getInsVendorBankAccountList(String vendorId);
	
	/**
	 * 设置默认银行卡
	 * 
	 * @param vendorId
	 * @param bankAccount
	 */
	public void setDefaultInsVendorBankAccount(String vendorId, String bankAccount);
	
	/**
	 * 解绑银行卡。
	 * 
	 * @param vendorId
	 * @param bankAccount
	 */
	public void unbindInsVendorBankAccount(String vendorId, String bankAccount);

	/**
	 * 获取商户默认银行卡
	 * 
	 * @param vendorId
	 * @return
	 */
	public InsVendorBankAccount getDefaultInsVendorBankAccount(String vendorId);
}
