package com.cifpay.lc.autoconfiguration;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * Created by sweet on 17-1-4.
 */
@TestConfiguration
public class CuratorTestConfiguration {
    @Value("${dubbo.registry.zookeeper.address:localhost:2181}")
    private String hosts;

    @Bean
    public CuratorFramework curatorFramework() {
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
}
