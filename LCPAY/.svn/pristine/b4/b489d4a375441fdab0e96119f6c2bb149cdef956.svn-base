package com.cifpay.lc.core.autoconfigure.lock;

import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.recipes.locks.InterProcessMutex;

public class DisLock {

	private InterProcessMutex lock;
	
	private String lockPath;
	
	private boolean ownLock;
		
	public DisLock(InterProcessMutex lock,String lockPath){
		this.lock = lock;
		this.lockPath = lockPath;
		ownLock = false;
	}

	public InterProcessMutex getLock() {
		return lock;
	}

	public void setLock(InterProcessMutex lock) {
		this.lock = lock;
	}

	public String getLockPath() {
		return lockPath;
	}

	public void setLockPath(String lockPath) {
		this.lockPath = lockPath;
	}
	
	public void acquire(){
		try {
			lock.acquire();
			ownLock = true;
		} catch (Exception e) {
			throw new RuntimeException("获取分布式锁异常，lockPath:" + lockPath);
		}
	}
	
	public boolean acquire(long time, TimeUnit unit){
		try {
			boolean has = lock.acquire(time,unit);
			if(has){
				ownLock = true;
			}
			return has;
		} catch (Exception e) {
			throw new RuntimeException("获取分布式锁异常，lockPath:" + lockPath);
		}
	}

	public boolean isOwnLock() {
		return ownLock;
	}
	
}
