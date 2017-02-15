package com.cifpay.lc.thirdtradeadapter.integration;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.cifpay.lc.util.uid.AbstractIdWorker;

/**
 * 提供一个应用启动时自动执行一些初始化逻辑的切入口。
 * 
 * 
 *
 */
@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class AutorunOnStartup implements InitializingBean {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private List<AbstractIdWorker> idWorkers;

	@Override
	public void afterPropertiesSet() throws Exception {
		initAllIdWorkers();

		// TODO 更多的初始化..
	}

	/**
	 * 统一初始化所有Snowflake ID生成器
	 */
	private void initAllIdWorkers() {
		if (null != idWorkers && !idWorkers.isEmpty()) {
			logger.info("初始化Snowflake ID生成器...");
			for (AbstractIdWorker idWorker : idWorkers) {
				logger.info("初始化 {} ...", idWorker.getClass().getName());
				idWorker.checkAndInit();
			}
		}
	}

}
