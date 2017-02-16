package com.cifpay.insurance.dao;

import java.util.List;

import com.cifpay.insurance.model.InsVendorBankAccount;
import com.cifpay.starframework.dao.CommonDao;

public interface InsVendorBankAccountDao extends CommonDao<InsVendorBankAccount> {
	public InsVendorBankAccount get(long id);

	public List<InsVendorBankAccount> getList();

	public int getCount();

	/**
	 * 获取相同银行账号数量
	 * 
	 * @param insVendorBankAccount
	 * @return
	 */
	public int getSameBankAccountCount(InsVendorBankAccount insVendorBankAccount);
	
	/**
	 * 据银行账号获取有效银行卡信息
	 * 
	 * @param bankAccount
	 * @return
	 */
	public InsVendorBankAccount getInsVendorBankAccount(String vendorId, String bankAccount);
	
	/**
	 * 据商户号获取银行卡列表信息
	 * 
	 * @param vendorId
	 * @return
	 */
	public List<InsVendorBankAccount> getInsVendorBankAccountListByVendorId(String vendorId);
	
	/**
	 * 获取指定商户的银行卡总数。
	 * 
	 * @param vendorId
	 * @return
	 */
	public int getVendorBankAccountCount(String vendorId);
	
	/**
	 * 更新除指定id外，其它银行卡号都为非默认。
	 * 
	 * @param excludeId
	 * @return
	 */
	public int updateOtherNotDefault(Long excludeId);
	
	/**
	 * 更新指定id的银行卡记录为默认。
	 * 
	 * @param id
	 * @return
	 */
	public int updateInsVendorBankAccountDefault(long id);
	
	/**
	 * 获取商户默认银行卡
	 * 
	 * @param vendorId
	 * @return
	 */
	public InsVendorBankAccount getDefaultInsVendorBankAccount(String vendorId);
}
