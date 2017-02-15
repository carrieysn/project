package com.cifpay.lc.core.uid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cifpay.lc.core.uid.base.SnowflakeWorkerIdManagerBaseedDB;
import com.cifpay.lc.util.uid.AbstractIdWorker;

@Component
public class UnionUserAccountIdWorker  extends AbstractIdWorker {
    @Autowired
    private SnowflakeWorkerIdManagerBaseedDB workerIdManager;

    @Override
    protected long configureWorkerId() {
        return workerIdManager.assignWorkerIdForCurrentAppInstance();
    }
}
