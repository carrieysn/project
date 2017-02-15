package com.cifpay.lc.std.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HTTPTool {

	private static final Logger LOGGER = LoggerFactory.getLogger(HTTPTool.class);

	public static void main(String[] args) {
		String url = "http://222.73.117.158/msg/HttpBatchSendSM";
		String mobile = "15800564940";
		String msg = "您好，您的验证码是4803";
		Map<String, String> map = new HashMap<String,String>();
		map.put("account", "yanqiuping_sw");
		map.put("pswd", "Shuaiwen27");
		map.put("mobile", mobile);
		map.put("msg", msg);
		map.put("needstatus", String.valueOf(true));
		try {
			String rs = postMap(url, map);
			System.out.println("rs:"+rs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static String postMap(String url, Map<String, String> map) throws Exception {
		LOGGER.info("===========发送内容 -start ===========");
		LOGGER.info("== 发送内容：{}", map.toString());
		LOGGER.info("===========发送内容 -end ===========");
		StringBuffer result = new StringBuffer();
		HttpClient client = null;
		PostMethod postMethod = null;
		try {

			Set<Map.Entry<String, String>> s = map.entrySet();
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			Iterator<Map.Entry<String, String>> iter = s.iterator();
			while (iter.hasNext()) {
				Map.Entry<String, String> m = iter.next();
				String key = m.getKey();
				String value = m.getValue();
				nvps.add(new NameValuePair(key, value));
			}
			// 填入各个表单域的值
			NameValuePair[] data = nvps.toArray(new NameValuePair[0]);

			client = new HttpClient();
			postMethod = new PostMethod(url);
			postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			// 将表单的值放入postMethod中
			postMethod.setRequestBody(data);
			// 执行postMethod
			int statusCode = client.executeMethod(postMethod);
			// 打印服务器返回的状态
			LOGGER.info("===========返回内容 -start ===========");
			LOGGER.info("== 返回状态：{}", statusCode);
			// 打印返回的信息
			LOGGER.info("==");
			if (statusCode == 302) {
				Header header = postMethod.getResponseHeader("location");
				if (header != null) {
					String newUri = header.getValue();
					if ((newUri == null) || (newUri.equals(""))) {
						newUri = "/";
					}
					result.append(newUri);
				}
			} else {
				InputStream stream = postMethod.getResponseBodyAsStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
				String line;
				while (null != (line = br.readLine())) {
					result.append(line).append("\n");
				}
			}
		} catch (Exception e) {
			LOGGER.error("#####HTTPTool.postMap异常:{}#####", e.getMessage());
			throw e;
		} finally {
			// 释放连接
			postMethod.releaseConnection();
		}
		LOGGER.info("==返回内容：{}", result.toString());
		LOGGER.info("===========返回内容 -end ===========");
		return result.toString();
	}

}
