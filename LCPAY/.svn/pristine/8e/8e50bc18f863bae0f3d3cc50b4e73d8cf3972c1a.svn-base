package com.cifpay.lc.core.common;

import org.springframework.transaction.TransactionStatus;

/**
 * 配合DbTransactionHelper使用的用于表示一个事务。
 * 
 * 
 *
 */
public class DbTransaction {

	private TransactionStatus transactionStatus;

	public DbTransaction(TransactionStatus transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public TransactionStatus getTransactionStatus() {
		return transactionStatus;
	}

	protected void markCycleAsCompleted() {
		transactionStatus = null;
	}
	
}
