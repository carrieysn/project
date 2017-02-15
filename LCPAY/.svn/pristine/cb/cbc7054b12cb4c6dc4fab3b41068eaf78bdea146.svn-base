package com.cifpay.lc.core.uid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cifpay.lc.core.uid.base.SnowflakeWorkerIdManagerBaseedDB;
import com.cifpay.lc.util.uid.AbstractIdWorker;

/**
 * 负责生成CIFPAY_LC_TRD_FLOW表所需的FLOW_ID
 * 
 * @author linql
 *
 */
@Component
public class LcTrdFlowIdWorker extends AbstractIdWorker {
	@Autowired
	private SnowflakeWorkerIdManagerBaseedDB workerIdManager;

	@Override
	protected long configureWorkerId() {
		return workerIdManager.assignWorkerIdForCurrentAppInstance();
	}

}
