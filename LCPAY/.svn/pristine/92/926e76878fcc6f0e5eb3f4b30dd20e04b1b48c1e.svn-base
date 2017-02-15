package com.cifpay.lc.core.autoconfigure.lock;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LockPathFreeManager {
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	public CuratorFramework client;
	
	public ConcurrentHashMap<String,Long> waitFreeLockPaths = new ConcurrentHashMap<String,Long>();
	
	ExecutorService es = Executors.newSingleThreadExecutor();
	
	boolean running = false;
	
	Lock lock = new ReentrantLock();
	
	public void add(String path){
		try{
			if(lock.tryLock(2,TimeUnit.SECONDS)){
				waitFreeLockPaths.put(path, System.currentTimeMillis());
				if(!running){
					es.submit(new PathFreeManager());
				}
			}
		}catch(Exception e){
		}finally{
			lock.unlock();
		}
	}
	
	class PathFreeManager implements Runnable{
		public void run() {
			while(!waitFreeLockPaths.isEmpty()){
				Set<String> paths = waitFreeLockPaths.keySet();
				for(String lockPath : paths){
					long intavel = System.currentTimeMillis() - waitFreeLockPaths.get(lockPath);
					//每分钟钟清理一次不需要的节点
					if(intavel > 60000){
						boolean succ = deleteLockPath(lockPath);
						if(succ){
							waitFreeLockPaths.remove(lockPath);
						}
					}
				}
			}
			running = false;
		}
	}
	
	public boolean deleteLockPath(String lockPath){
		try {
			Stat stat = client.checkExists().forPath(lockPath);
			if(stat != null && 
					client.getChildren().forPath(lockPath).isEmpty()){
				client.delete().guaranteed().forPath(lockPath);
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	
	public void setClient(CuratorFramework client) {
		this.client = client;
	}
}
