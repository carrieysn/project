package mock.merchant.common.tool;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mock.merchant.common.LcGatewayConfig;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class HTTPTool {

	private static final Logger LOGGER = LoggerFactory.getLogger(HTTPTool.class);

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

	public static String post(String url, JSONObject jsonBody) throws Exception {
		LOGGER.info("===========发送内容 -start ===========");
		LOGGER.info("== 发送内容：{}", jsonBody.toString());
		LOGGER.info("===========发送内容 -end ===========");

		HttpClient client = null;
		PostMethod postMethod = null;
		StringBuffer result = new StringBuffer();
		try {
			client = new HttpClient();
			postMethod = new PostMethod(url);
			postMethod.setRequestHeader("Content-Type", "application/json");
			postMethod.setRequestEntity(new StringRequestEntity(jsonBody.toString(), "", "UTF-8"));
			client.executeMethod(postMethod);
			// 打印服务器返回的状态
			LOGGER.info("===========返回内容 -start ===========");
			LOGGER.info("== 返回状态：{}", postMethod.getStatusLine());
			// 打印返回的信息
			LOGGER.info("==");
			InputStream stream = postMethod.getResponseBodyAsStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
			String line;
			while (null != (line = br.readLine())) {
				result.append(line).append("\n");
			}
		} catch (Exception e) {
			LOGGER.error("#####HTTPTool.post异常:{}#####", e.getMessage());
			throw e;
		} finally {
			// 释放连接
			postMethod.releaseConnection();
		}
		LOGGER.info("==返回内容：{}", result.toString());
		LOGGER.info("===========返回内容 -end ===========");
		return result.toString();
	}

	public static JSONObject getReqData(HttpServletRequest req) {
		JSONObject reqData = new JSONObject();
		Enumeration<String> paramNames = req.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			String[] paramValues = req.getParameterValues(paramName);
			if (paramValues.length == 1) {
				String paramValue = paramValues[0];
				if (paramValue.length() != 0) {
					LOGGER.info("参数：{}", paramName + "=" + paramValue);
					reqData.put(paramName, paramValue);
				}
			}
		}
		return reqData;
	}

	public static HashMap<String, String> getReqDataMap(HttpServletRequest req) {
		HashMap<String, String> reqDataMap = new HashMap<String, String>();
		Enumeration<String> paramNames = req.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			String[] paramValues = req.getParameterValues(paramName);
			if (paramValues.length == 1) {
				String paramValue = paramValues[0];
				if (paramValue.length() != 0) {
					LOGGER.info("参数：{}", paramName + "=" + paramValue);
					reqDataMap.put(paramName, paramValue);
				}
			}
		}
		return reqDataMap;
	}

	public static JSONObject getReqBatchDataOpen(HttpServletRequest req) {
		ArrayList<String> arr = new ArrayList<String>();
		arr.add("merId");
		arr.add("payerAccountType");
		arr.add("payerBankCode");
		arr.add("payerBankAccountNo");
		arr.add("payerMobile");
		arr.add("returnUrlBatch");
		arr.add("noticeUrlBatch");
		arr.add("remarkBatch");

		Map<String, String[]> map = new HashMap<String, String[]>();

		JSONObject reqData = new JSONObject();
		JSONArray jsonArray = new JSONArray();

		Enumeration<String> paramNames = req.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			String[] paramValues = req.getParameterValues(paramName);
			if (arr.contains(paramName)) {
				reqData.put(paramName, paramValues[0]);
			} else {
				map.put(paramName, paramValues);
			}
		}

		// int totalCount =
		// Integer.parseInt(reqData.get("totalCount").toString());
		//
		// //验证一下输入总数和银信证总数是否相等
		// int count = map.get("productCode").length;
		// if(totalCount != count) {
		// return null;
		// }
		int count = map.get("productCode").length;

		for (int i = 0; i < count; i++) {
			JSONObject jsonObject_lc = new JSONObject();
			for (String key : map.keySet()) {
				jsonObject_lc.put(key, map.get(key)[i]);
			}
			jsonArray.add(jsonObject_lc);
		}

		reqData.put("lcList", jsonArray);

		return reqData;
	}

	public static JSONObject getReqBatchDataApply(HttpServletRequest req) {
		ArrayList<String> arr = new ArrayList<String>();
		arr.add("merchantId");

		Map<String, String[]> map = new HashMap<String, String[]>();

		JSONObject reqData = new JSONObject();
		JSONArray jsonArray = new JSONArray();

		Enumeration<String> paramNames = req.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			String[] paramValues = req.getParameterValues(paramName);
			if (arr.contains(paramName)) {
				reqData.put(paramName, paramValues[0]);
			} else {
				map.put(paramName, paramValues);
			}
		}
		reqData.put("merId", LcGatewayConfig.getMockLcStandardMerchantId());

		int count = map.get("lcId").length;

		for (int i = 0; i < count; i++) {
			JSONObject jsonObject_lc = new JSONObject();
			for (String key : map.keySet()) {
				jsonObject_lc.put(key, map.get(key)[i]);
			}
			jsonArray.add(jsonObject_lc);
		}

		reqData.put("listWithdraw", jsonArray);

		return reqData;
	}
}
