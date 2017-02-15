package com.cifpay.lc.std.sched.task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.cifpay.lc.core.interceptor.annotation.EnableGenerateRequestId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cifpay.lc.core.autoconfigure.lock.DisLock;
import com.cifpay.lc.core.autoconfigure.lock.DistributeLockManager;

@EnableGenerateRequestId
public abstract class SimpleTask {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected ExecutorService executor = Executors.newFixedThreadPool(20);

    @Autowired
    private DistributeLockManager distributeLockManager;

    public void execute() {
        String lockPath = getLockName();
        //避免不同节点并行
        DisLock lock = null;
        try {
            lock = distributeLockManager.getDisLock(lockPath);
            if (lock == null) {
                logger.info("获取分布式锁失败： lockPath={}", lockPath);
                return;
            }
            if (lock.acquire(5, TimeUnit.SECONDS)) {
                handleBusiness();
            }
        } catch (com.alibaba.dubbo.rpc.RpcException e) {
            logger.error("dubbo远程调用出现异常,err:{}", e);
        } catch (Exception e) {
            logger.error("获取分布式锁出现异常,err:{}", e);
        } finally {
            distributeLockManager.unlock(lock);
        }
    }

    public abstract String getLockName();

    public abstract void handleBusiness();
}
