package com.cifpay.lc.core.http;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.stereotype.Component;

@Component
public class HttpConnectionManager {

	PoolingHttpClientConnectionManager cm = null;

	@PostConstruct
	public void init() {
		ConnectionSocketFactory plainFactory = PlainConnectionSocketFactory.getSocketFactory();
		LayeredConnectionSocketFactory sslFactory = SSLConnectionSocketFactory.getSocketFactory();
		Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory> create()
				.register("http", plainFactory).register("https", sslFactory).build();
		cm = new PoolingHttpClientConnectionManager(registry);
		cm.setMaxTotal(200);
		cm.setDefaultMaxPerRoute(20);
	}

	public CloseableHttpClient getHttpClient() {
		CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();
		// 如果不采用连接池就是这种方式获取连接*/
		// CloseableHttpClient httpClient = HttpClients.createDefault();
		return httpClient;
	}

	public void close(CloseableHttpResponse response) {
		if (response != null) {
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
