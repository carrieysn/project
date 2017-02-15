package com.cifpay.lc.core.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.cifpay.lc.constant.ReturnCode;
import com.cifpay.lc.core.exception.CoreBusinessException;

/**
 * 编程式事务管理助手类，提供简单的调用入口。业务模块在使用该助手类之前，
 * 需要各应用在自己的Spring上下文初始化定义DbTransactionHelper的Bean实例。
 * 
 * 
 *
 */
public class DbTransactionHelper {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private boolean isLoggerDebugEnabled = logger.isDebugEnabled();

	private PlatformTransactionManager txManager;

	public DbTransactionHelper(PlatformTransactionManager txManager) {
		this.txManager = txManager;
	}

	/**
	 * 使用默认的事务属性，开启DB事务
	 * 
	 * @return
	 * @throws CoreBusinessException
	 *             如果开户DB事务失败，则抛出CoreBusinessException异常。
	 */
	public DbTransaction beginTransaction() throws CoreBusinessException {
		try {
			DefaultTransactionDefinition def = new DefaultTransactionDefinition();
			def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			def.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);
			return new DbTransaction(txManager.getTransaction(def));
		} catch (Exception e) {
			logger.error("开启DB事务失败", e);
			throw new CoreBusinessException(ReturnCode.CORE_COMMON_BEGIN_DB_TRANSACTION_FAILED, "开启DB事务失败");
		}
	}

	/**
	 * 根据指定的事务属性（事务传播方式、事务隔离级别）来开启事务
	 * 
	 * @return
	 */
	public DbTransaction beginTransaction(Propagation propagation, Isolation isolation) {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(propagation.value());
		def.setIsolationLevel(isolation.value());
		return new DbTransaction(txManager.getTransaction(def));
	}

	/**
	 * 提交事务
	 * 
	 * @param transaction
	 *            开启事务时，由beginTransaction返回的事务对象
	 */
	public void commitTransaction(DbTransaction transaction) {
		if (null != transaction && null != transaction.getTransactionStatus()) {
			txManager.commit(transaction.getTransactionStatus());
			transaction.markCycleAsCompleted();
		} else {
			if (null != transaction && null == transaction.getTransactionStatus()) {
				logger.warn("***警告***该事务已经提交或回滚，无法进行提交，请检查代码逻辑编写是否有误。");
			} else if (isLoggerDebugEnabled) {
				logger.debug("~~~传递给commitTransaction()方法的DbTransaction对象为null，此次提交事务操作不作处理");
			}
		}
	}

	/**
	 * 提交事务（考虑到业务模块编写的复杂性（例如在try{}块中大量的return语句），允许在finally块再提交事务。
	 * 本方法会根据实际情况选择是否要真正地提交事务，如果该事务已经回滚过，则本方法会忽略此次调用。
	 * 
	 * @param transaction
	 *            开启事务时，由beginTransaction返回的事务对象
	 */
	public void commitTransactionInFinallyBlock(DbTransaction transaction) {
		if (null != transaction && null != transaction.getTransactionStatus()
				&& !transaction.getTransactionStatus().isRollbackOnly()) {
			txManager.commit(transaction.getTransactionStatus());
			transaction.markCycleAsCompleted();
		}
	}

	/**
	 * 回滚事务
	 * 
	 * @param transaction
	 *            开启事务时，由beginTransaction返回的事务对象
	 */
	public void rollbackTransaction(DbTransaction transaction) {
		if (null != transaction && null != transaction.getTransactionStatus()) {
			try {
				txManager.rollback(transaction.getTransactionStatus());
				transaction.markCycleAsCompleted();
			} catch (Exception e) {
				logger.warn("***警告***事务回滚失败，异常信息：{}", e.getMessage(), e);
			}
		} else {
			if (null != transaction && null == transaction.getTransactionStatus()) {
				logger.warn("***警告***该事务已经提交或回滚，无法进行回滚，请检查代码逻辑编写是否有误。");
			} else if (isLoggerDebugEnabled) {
				logger.debug("~~~传递给rollbackTransaction()方法的DbTransaction对象为null，此次提交事务操作不作处理");
			}
		}
	}
}
