package com.cifpay.lc.core.autoconfigure.lock;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCache.StartMode;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name="cifpay.lock.enable", havingValue="true")
public class CuratorConfiguration {

	@Value("${dubbo.registry.zookeeper.address:localhost:2181}")
	private String hosts;
	
	@Bean
	public CuratorFramework curatorFramework(){
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3); 
		//CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(hosts, retryPolicy);
		CuratorFramework curatorFramework = CuratorFrameworkFactory.builder().
				namespace("cifpay").
	            connectString(hosts).
	            retryPolicy(retryPolicy).
	            build();
		curatorFramework.start();
		
		return curatorFramework;
	}
	
	@Bean
	@ConditionalOnBean(CuratorFramework.class)
	public LockPathFreeManager LockPathFreeManager(CuratorFramework client){
		LockPathFreeManager lockPathFreeManager = new LockPathFreeManager();
		lockPathFreeManager.setClient(client);
		return lockPathFreeManager;
	}
	
	
}
