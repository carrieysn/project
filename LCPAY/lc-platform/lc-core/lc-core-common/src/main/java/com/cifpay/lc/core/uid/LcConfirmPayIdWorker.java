package com.cifpay.lc.core.uid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cifpay.lc.core.uid.base.SnowflakeWorkerIdManagerBaseedDB;
import com.cifpay.lc.util.uid.AbstractIdWorker;

/**
 * 负责生成LC_CONFIRM_PAY表所需的LC_CONFIRM_ID
 * 
 * 
 *
 */
@Component
public class LcConfirmPayIdWorker extends AbstractIdWorker {

	@Autowired
	private SnowflakeWorkerIdManagerBaseedDB workerIdManager;
	
	@Override
	protected long configureWorkerId() {
		return workerIdManager.assignWorkerIdForCurrentAppInstance();
	}

}
