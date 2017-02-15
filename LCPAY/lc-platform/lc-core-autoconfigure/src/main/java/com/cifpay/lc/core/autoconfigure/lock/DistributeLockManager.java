package com.cifpay.lc.core.autoconfigure.lock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnBean(CuratorConfiguration.class)
public class DistributeLockManager {
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	public CuratorFramework client;
	
	@Autowired
	LockPathFreeManager lockPathFreeManager;
	
	public DisLock getDisLock(String lockPath) {
		int count = 0;
		boolean succ = false;
		do{
			succ = createLockPath(lockPath);
			count++;
		}while(!succ && count < 5);
		
		if(succ){
			InterProcessMutex lock = new InterProcessMutex(client,lockPath);
			DisLock disLock = new DisLock(lock,lockPath);
			return disLock;
		}else{
			throw new RuntimeException("======== 无法预期的错误导致无法创建zookeeper目录 =========");
		}
	}
	
	private boolean createLockPath(String lockPath){
		try {
			Stat stat = client.checkExists().forPath(lockPath);
			if(stat == null){
				client.create().creatingParentsIfNeeded().forPath(lockPath);
				lockPathFreeManager.add(lockPath);
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public void unlock(DisLock disLock) {
		if(disLock == null) return;
		try {
			if(disLock.getLock() != null && disLock.isOwnLock()){
				disLock.getLock().release();
			}
		} catch (Exception e) {
			logger.error("释放分布式锁异常",e);
			throw new RuntimeException(e);
		}
	}
	
	public void setClient(CuratorFramework client) {
		this.client = client;
	}
	
	public void setLockPathFreeManager(LockPathFreeManager lockPathFreeManager) {
		this.lockPathFreeManager = lockPathFreeManager;
	}

}
