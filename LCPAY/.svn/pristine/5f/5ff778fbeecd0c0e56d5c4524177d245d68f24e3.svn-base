package com.cifpay.lc.core.autoconfigure.fastdfs;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.pool2.impl.GenericKeyedObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.tobato.fastdfs.conn.FdfsConnectionPool;
import com.github.tobato.fastdfs.conn.PooledConnectionFactory;
import com.github.tobato.fastdfs.conn.TrackerConnectionManager;
import com.github.tobato.fastdfs.domain.DefaultThumbImageConfig;
import com.github.tobato.fastdfs.domain.ThumbImageConfig;
import com.github.tobato.fastdfs.service.DefaultFastFileStorageClient;
import com.github.tobato.fastdfs.service.DefaultTrackerClient;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.github.tobato.fastdfs.service.TrackerClient;

@Configuration
@ConditionalOnProperty(name="cifpay.fastdfs.enable", havingValue="false")
public class FastdfsAutoConfiguration {

	@Value("${fastdfs.strTrackerList:localhost:22122}")
	private String strTrackerList;

	@Value("${fastdfs.connectTimeout:30000}")
	private int connectTimeout;

	@Value("${fastdfs.soTimeout:600000}")
	private int soTimeout;

	@Value("${fastdfs.charset:UTF-8}")
	private String charset;

	@Bean
	public FastFileStorageClient fdfsFileClient() {
		DefaultFastFileStorageClient client = new DefaultFastFileStorageClient();
		return client;
	}

	@Bean
	public TrackerClient fdfsTrackerClient() {
		TrackerClient trackerClient = new DefaultTrackerClient();
		return trackerClient;
	}

	@Bean
	public TrackerConnectionManager fdfsTrackerConnectionManager() {
		TrackerConnectionManager trackerConnMgr = new TrackerConnectionManager();
		List<String> trackerList = new ArrayList<String>();

		// strTrackerList格式:
		// 192.168.163.25:22122,192.168.163.26:22122
		String[] trackerAddrArray = strTrackerList.trim().split(",");
		for (String trackerAddr : trackerAddrArray) {
			trackerList.add(trackerAddr.trim());
		}
		trackerConnMgr.setTrackerList(trackerList);
		return trackerConnMgr;
	}

	@Bean
	public ThumbImageConfig fdfsThumbImageConfig() {
		DefaultThumbImageConfig thumbConfig = new DefaultThumbImageConfig();
		thumbConfig.setWidth(150);
		thumbConfig.setHeight(150);
		return thumbConfig;
	}

	@Bean
	public FdfsConnectionPool fdfsConnectionPool() {
		GenericKeyedObjectPoolConfig poolConfig = new GenericKeyedObjectPoolConfig();
		poolConfig.setMaxTotal(50); // 从池中借出的对象的最大数目50
		poolConfig.setTestWhileIdle(true);
		poolConfig.setBlockWhenExhausted(true);
		poolConfig.setMaxWaitMillis(100);
		poolConfig.setMinEvictableIdleTimeMillis(180000); // 视休眠时间超过了180秒的对象为过期
		poolConfig.setTimeBetweenEvictionRunsMillis(60000); // 每过60秒进行一次后台对象清理的行动
		poolConfig.setNumTestsPerEvictionRun(-1);

		FdfsConnectionPool pool = new FdfsConnectionPool(fdfsPooledConnectionFactory(), poolConfig);
		return pool;
	}

	@Bean
	public PooledConnectionFactory fdfsPooledConnectionFactory() {
		PooledConnectionFactory connFactory = new PooledConnectionFactory();
		connFactory.setConnectTimeout(connectTimeout);
		connFactory.setSoTimeout(soTimeout);
		connFactory.setCharsetName(charset);
		return connFactory;
	}

}
