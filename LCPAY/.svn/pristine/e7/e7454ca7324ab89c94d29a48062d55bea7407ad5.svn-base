package com.cifpay.lc.core.uid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cifpay.lc.core.uid.base.SnowflakeWorkerIdManagerBaseedDB;
import com.cifpay.lc.util.uid.AbstractIdWorker;

/**
 * 负责生成LC表所需的LC_ID
 * 
 * 
 *
 */
@Component
public class LcIdWorker extends AbstractIdWorker {
	@Autowired
	private SnowflakeWorkerIdManagerBaseedDB workerIdManager;

	@Override
	protected long configureWorkerId() {
		return workerIdManager.assignWorkerIdForCurrentAppInstance();
	}
}
