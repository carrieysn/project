package com.cifpay.lc.std.business.dbtxn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cifpay.lc.core.common.DbTransaction;
import com.cifpay.lc.core.common.DbTransactionHelper;
import com.cifpay.lc.core.db.dao.TradeTypeDao;
import com.cifpay.lc.core.db.pojo.TradeType;

@Component
public class DbTransactionSampleImpl implements DbTransactionSample {

	@Autowired
	private DbTransactionHelper dbTransactionHelper;

	@Autowired
	private TradeTypeDao tradeTypeDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cifpay.lc.std.business.dbtxn.DbTransactionSampleI#
	 * executeInTransaction()
	 */
	@Override
	public void executeInTransaction() {

		DbTransaction transaction = dbTransactionHelper.beginTransaction();

		try {
			System.out.println("~~~[start] executeInTransaction");

			TradeType rec = new TradeType();
			rec.setTradeStatus(String.valueOf(System.currentTimeMillis()) + "_1");
			rec.setTradeDesc("Test the transaction feature - part1");
			tradeTypeDao.insert(rec);

			System.out.println("~~~executeInTransaction inserted 1");

			rec = new TradeType();
			rec.setTradeStatus(String.valueOf(System.currentTimeMillis()) + "_2");
			rec.setTradeDesc("Test the transaction feature - part2");
			tradeTypeDao.insert(rec);

			// mock exception
			if (1 / 0 == 0) {
				throw new RuntimeException("mock exception");
			}

			dbTransactionHelper.commitTransaction(transaction);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("rollback...");
			dbTransactionHelper.rollbackTransaction(transaction);
		} finally {
		}
	}
	

}
