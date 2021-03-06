package com.cifpay.lc.bankadapter.universal.tool;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cifpay.lc.constant.ReturnCode;
import com.cifpay.lc.core.common.DbTransaction;
import com.cifpay.lc.core.common.DbTransactionHelper;
import com.cifpay.lc.core.db.dao.LcTrdLockDao;
import com.cifpay.lc.core.db.pojo.TrdLock;
import com.cifpay.lc.core.exception.BankAdapterException;

@Component
public class BusinessTradeLock {
	@Autowired
	LcTrdLockDao lockDao;
	@Autowired
	private DbTransactionHelper dbTransactionHelper;

	public void lockLcId(Long lcId) {
		lockByDB(lcId);
	}

	public void unLockLcId(Long lcId) {
		unLockByDB(lcId);
	}

	private void lockByDB(Long lcId) {
		TrdLock trdLock = new TrdLock();
		trdLock.setLcId(lcId);
		trdLock.setInsertTime(new Date());
		DbTransaction dt = null;
		try {
			dt = dbTransactionHelper.beginTransaction();
			lockDao.insert(trdLock);
			dbTransactionHelper.commitTransaction(dt);
		} catch (Exception e) {
			dbTransactionHelper.rollbackTransaction(dt);
			e.printStackTrace();
			throw new BankAdapterException(ReturnCode.CORE_BA_DEALING_EXCEPTION_N105012, "业务正在处理中，请不要重复提交！");
		}
	}

	private void unLockByDB(Long lcId) {
		DbTransaction dt = null;
		try {
			dt = dbTransactionHelper.beginTransaction();
			lockDao.deleteByPrimaryKey(lcId);
			dbTransactionHelper.commitTransaction(dt);
		} catch (Exception e) {
			dbTransactionHelper.rollbackTransaction(dt);
			e.printStackTrace();
		}
	}
	private void lockByRedis(String LcId) {

	}
	
	private void unLockByRedis(String LcId) {

	}
}
