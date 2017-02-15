package com.cifpay.lc.bankadapter.universal.tool;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.cifpay.lc.bankadapter.universal.tool.HttpCallResult;
import com.cifpay.lc.bankadapter.universal.tool.HttpTool;

/**
 * 封装HTTP请求
 * 
 * @author songhaipeng 2013年12月4日
 */
public class HttpTool {
	private static Logger logger = LoggerFactory.getLogger(HttpTool.class);

	private final static String DEFAULT_CHARACTER_ENCODING = "UTF-8";

	/**
	 * 发送POST请求
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @throws Exception 
	 */
	public static HttpCallResult post(String url, Map<String, Object> params) throws Exception {
		return post(url, params, "UTF-8");
	}

	/**
	 * 发送GET请求
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	public static HttpCallResult get(String url) {

		// 输出日志
		logger.info("接口(GET)：" + url);

		HttpCallResult result = new HttpCallResult();
		HttpClientBuilder builder = HttpClientBuilder.create();
		CloseableHttpClient client = builder.build();
		HttpResponse response = null;
		try {
			HttpGet get = new HttpGet(url);
			get.setConfig(buildDefaultRequestConfig());
			response = client.execute(get);
			if (response != null) {
				result.setHttpEntity(response.getEntity());
				result.setContent(EntityUtils.toString(response.getEntity()));
				result.setStatusCode(response.getStatusLine().getStatusCode());
			}
			// 输出结果
			logger.info("结果：" + result.getStatusCode());
		} catch (Exception e) {
			logger.error("httpclient execute exception", e);
			result.setStatusCode(999);
		} finally {
			if (client != null) {
				try {
					client.close();
				} catch (IOException e) {
					logger.error("httpclient close exception");
				}
			}
		}
		return result;
	}

	/**
	 * 提交一个POST形式连接
	 * 
	 * @param url
	 *            -- 提交的URL地址
	 * @param params
	 *            -- 提交的FORM表单
	 * @param headers
	 *            -- HTTP头信息
	 * @return 返回数据
	 * public static String post(String url, String params, Map<String, String> headers, String encoding) {
		HttpURLConnection con = null;
		try {
			URL u = new URL(url);
			con = (HttpURLConnection) u.openConnection();
			con.setRequestMethod("POST");
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setUseCaches(false);
			for (String key : headers.keySet()) {
				con.setRequestProperty(key, headers.get(key));
			}
			if (StringUtils.hasText(encoding))
				encoding = DEFAULT_CHARACTER_ENCODING;
			OutputStreamWriter osw = new OutputStreamWriter(
					con.getOutputStream(), encoding);
			osw.write(params);
			osw.flush();
			osw.close();
		} catch (Exception e) {
			logger.error("url connect or write exception", e);
			if (con != null) {
				con.disconnect();
			}
			return "url request error";
		}
		try {
			StringBuffer sb = new StringBuffer();
			BufferedReader br = new BufferedReader(new InputStreamReader(
					con.getInputStream(), encoding));
			int byteRead = 0;
			char[] buffer = new char[8192];
			while ((byteRead = br.read(buffer, 0, 8192)) != -1) {
				sb.append(buffer, 0, byteRead);
			}
			return sb.toString();
		} catch (Exception e) {
			logger.error("url socket read exception", e);
			return "url request error";
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
	}*/

	/**
	 * 发送POST请求
	 * @param url
	 * @param params
	 * @param encoding
	 * @return
	 * @throws Exception 
	 */
	public static HttpCallResult post(String url, Map<String, Object> params,
			String encoding) throws Exception {

		// 输出日志
		logger.info("接口(POST)：" + url);
		logger.info("参数：" + params);

		if (StringUtils.hasText(encoding)) {
			encoding = DEFAULT_CHARACTER_ENCODING;
		}
		HttpCallResult result = new HttpCallResult();
		HttpClientBuilder builder = HttpClientBuilder.create();
		CloseableHttpClient client = null;

		if (url.contains("https")) {
			X509TrustManager x509mgr = new X509TrustManager() {

				public void checkClientTrusted(X509Certificate[] chain,
						String authType) throws CertificateException {
				}


				public void checkServerTrusted(X509Certificate[] chain,
						String authType) throws CertificateException {
				}


				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			};

			SSLContext sslContext = null;
			try {
				sslContext = SSLContext.getInstance("TLS");
				sslContext.init(null, new TrustManager[] { x509mgr }, null);
			} catch (NoSuchAlgorithmException e1) {
				logger.error("create SSLContext error", e1);
			} catch (KeyManagementException e1) {
				logger.error("create SSLContext error", e1);
			}

			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
					sslContext,
					SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			client = builder.setSSLSocketFactory(sslsf).build();
		} else {
			client = builder.build();
		}

		HttpResponse response = null;
		try {
			HttpPost post = new HttpPost(url);
			post.setConfig(buildDefaultRequestConfig());
			List<NameValuePair> nvps = map2NameValuePair(params);
			post.setEntity(new UrlEncodedFormEntity(nvps, encoding));
			response = client.execute(post);
			if (response != null) {
				result.setHttpEntity(response.getEntity());
				result.setContent(EntityUtils.toString(response.getEntity(),
						encoding));
				result.setStatusCode(response.getStatusLine().getStatusCode());
			}
			// 输出结果
			logger.info("结果：" + result.getStatusCode());
		} catch (Exception e) {
			throw e;
		} finally {
			if (client != null) {
				try {
					client.close();
				} catch (IOException e) {
					logger.error("httpclient close error");
				}
			}
		}
		return result;
	}

	/**
	 * 
	 * @return
	 */
	private static RequestConfig buildDefaultRequestConfig() {
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(30000).setConnectTimeout(30000)
				.setConnectionRequestTimeout(30000).build();
		return requestConfig;
	}

	/**
	 * 组装参数
	 * 
	 * @param params
	 * @return
	 */
	private static List<NameValuePair> map2NameValuePair(
			Map<String, Object> params) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		if (null != params && params.size() > 0) {
			Iterator<String> it = params.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next();
				Object value = params.get(key);
				if (null != value) {
					NameValuePair nvp = new BasicNameValuePair(key, value.toString());
					logger.debug(">>HttpCall params:->" + key + "    =   " + value);
					nvps.add(nvp);
				}
			}
		}
		return nvps;
	}

}
