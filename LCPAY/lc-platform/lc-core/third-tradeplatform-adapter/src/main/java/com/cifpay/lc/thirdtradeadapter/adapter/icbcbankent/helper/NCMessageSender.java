package com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.helper;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import com.cifpay.lc.constant.ReturnCode;
import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.exception.NCResponseContentInvalidException;
import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.exception.NCResponseParseException;
import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.exception.NCResultFailedException;
import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.exception.NCResultUncertainException;
import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.exception.NCSignException;
import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.exception.NCWaitResponseTimeoutException;
import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.transcode.TransCodeDefinition;
import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.xmlbean.NCRequest;
import com.cifpay.lc.util.DateUtil;
import com.cifpay.lc.util.StringUtil;

/**
 * Sender for sending transaction request to NC server.
 * 
 * 
 *
 */
@Component
public class NCMessageSender implements DisposableBean {

	private final Logger logger = LoggerFactory.getLogger(NCMessageSender.class);
	private final boolean isLoggerDebugEnabled = logger.isDebugEnabled();

	@Value("${icbcBankEnt.NCServer.signInterfaceURL}")
	private String signInterfaceURL;

	@Value("${icbcBankEnt.NCServer.tradeInterfaceURL}")
	private String tradeInterfaceURL;

	@Autowired
	private ICBCBankEntXmlRequestBuilder xmlRequestBuilder;

	@Autowired
	private ICBCBankEntXmlResponseParser xmlResponseParser;

	private final HttpClient httpClientForTrade;

	public NCMessageSender() {
		SocketConfig socketConfig = SocketConfig.custom().setSoTimeout(600000).build();
		RequestConfig requestConfig = RequestConfig.copy(RequestConfig.DEFAULT).setSocketTimeout(600000)
				.setConnectTimeout(120000).setConnectionRequestTimeout(120000).build();
		httpClientForTrade = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig)
				.setDefaultSocketConfig(socketConfig)
				.setUserAgent(
						new String(new byte[] { 67, 73, 70, 80, 65, 89, 45, 72, 84, 84, 80, 67, 76, 73, 69, 78, 84 }))
				.build();
	}

	@Override
	public void destroy() throws Exception {
		if (httpClientForTrade instanceof Closeable) {
			Closeable c = (Closeable) httpClientForTrade;
			try {
				c.close();
			} catch (Exception e) {
			}
		}
	}

	public void setSignInterfaceURL(String signInterfaceURL) {
		this.signInterfaceURL = signInterfaceURL;
	}

	public void setTradeInterfaceURL(String tradeInterfaceURL) {
		this.tradeInterfaceURL = tradeInterfaceURL;
	}

	public void setXmlRequestBuilder(ICBCBankEntXmlRequestBuilder xmlRequestBuilder) {
		this.xmlRequestBuilder = xmlRequestBuilder;
	}

	public void setXmlResponseParser(ICBCBankEntXmlResponseParser xmlResponseParser) {
		this.xmlResponseParser = xmlResponseParser;
	}

	/**
	 * 
	 * @param ncTransCode
	 * @param request
	 * @return
	 * @throws NCResultFailedException
	 * @throws NCResultUncertainException
	 */
	public <I extends NCRequest, O> O send(TransCodeDefinition<I, O> ncTransCode, I request)
			throws NCResultFailedException, NCResultUncertainException {
		if (null == ncTransCode || null == request) {
			throw new IllegalArgumentException("参数ncTransCode、request不允许为NULL");
		}
		if (8 != request.getTranDate().length()) {
			throw new IllegalArgumentException("参数TranDate格式不正确，正确格式yyyyMMdd");
		}
		if (12 != request.getTranTime().length()) {
			throw new IllegalArgumentException("参数TranTime格式不正确，正确格式HHmmssSSS000");
		}
		if (!ncTransCode.getTransCode().equals(request.getTransCode())) {
			throw new IllegalArgumentException("参数TransCode不正确");
		}

		HttpPost mypost = null;
		String plainXmlRequestContent;
		String finalXmlRequestContent;

		// build request XML
		try {
			plainXmlRequestContent = xmlRequestBuilder.buildXmlRequest(ncTransCode.getFreemarkerTemplateName(),
					request);
		} catch (Exception e) {
			throw new NCResultFailedException("构建NC请求" + ncTransCode.getTransCode() + "失败");
		}

		// sign the request XML if need
		if (ncTransCode.isNeedSignRequestBeforeSending()) {
			try {
				if (isLoggerDebugEnabled) {
					logger.debug("NC交易请求{}需要签名，PackageID: {}, URL: {}，签名前的报文内容: {}{}", ncTransCode.getTransCode(),
							request.getfSeqno(), this.signInterfaceURL, StringUtil.LINE_SEPARATOR,
							plainXmlRequestContent);
				}
				finalXmlRequestContent = signXmlRequestBeforeTrading(this.signInterfaceURL, plainXmlRequestContent);
				if (isLoggerDebugEnabled) {
					logger.debug("经NC签名后的报文内容: {}", finalXmlRequestContent);
				}
			} catch (NCSignException e) {
				logger.error("调用NC签名接口时发生异常，异常信息：{}", e.getMessage(), e);
				throw e;
			} catch (Exception e) {
				logger.error("调用NC签名接口时发生异常，异常信息：{}", e.getMessage(), e);
				throw new NCSignException("NC签名失败", e);
			}
		} else {
			if (isLoggerDebugEnabled) {
				logger.debug("该NC交易请求{}无需签名，报文内容: {}{}", ncTransCode.getTransCode(), StringUtil.LINE_SEPARATOR,
						plainXmlRequestContent);
			}
			finalXmlRequestContent = plainXmlRequestContent;
		}

		int httpResponseCode = -1;
		String httpResponseContent = "";
		boolean sendingHasBeenTriggered = false;
		try {
			String tranDatetime = request.getTranDate() + request.getTranTime();
			String sendTime = DateUtil.format(
					DateUtil.parse(tranDatetime.substring(0, tranDatetime.length() - 3), "yyyyMMddHHmmssSSS"),
					"yyyyMMddHHmmss");

			String ncTradeURL = tradeInterfaceURL + "?userID=" + request.getId() + "&PackageID=" + request.getfSeqno()
					+ "&SendTime=" + sendTime;

			mypost = new HttpPost(ncTradeURL);
			mypost.setHeader("Content-Type", "application/x-www-form-urlencoded");

			List<NameValuePair> formParams = new ArrayList<NameValuePair>();
			formParams.add(new BasicNameValuePair("TransCode", request.getTransCode()));
			formParams.add(new BasicNameValuePair("BankCode", request.getBankCode()));
			formParams.add(new BasicNameValuePair("GroupCIS", request.getCis()));
			formParams.add(new BasicNameValuePair("ID", request.getId()));
			formParams.add(new BasicNameValuePair("PackageID", request.getfSeqno()));
			formParams.add(new BasicNameValuePair("reqData", finalXmlRequestContent));
			HttpEntity formEntity = new UrlEncodedFormEntity(formParams, "GBK");

			mypost.setEntity(formEntity);

			logger.info("调用NC交易接口发送交易请求{}，PackageID: {}, URL: {}，交易报文: {}{}", ncTransCode.getTransCode(),
					request.getfSeqno(), ncTradeURL, StringUtil.LINE_SEPARATOR, finalXmlRequestContent);
			HttpResponse tradeHttpResponse = httpClientForTrade.execute(mypost);
			sendingHasBeenTriggered = true;

			httpResponseCode = tradeHttpResponse.getStatusLine().getStatusCode();
			httpResponseContent = EntityUtils.toString(tradeHttpResponse.getEntity(), "GBK");

			if (200 == httpResponseCode) {
				logger.info("工行NC交易{}接口返回HTTP内容，packageID: {}，HTTP {}，原始响应报文： {}{}", ncTransCode.getTransCode(),
						request.getfSeqno(), httpResponseCode, StringUtil.LINE_SEPARATOR,
						null != httpResponseContent ? httpResponseContent : "");
			} else {
				logger.error("工行NC交易{}接口返回HTTP内容，packageID: {}，HTTP {}，原始响应报文： {}{}", ncTransCode.getTransCode(),
						request.getfSeqno(), httpResponseCode, StringUtil.LINE_SEPARATOR,
						null != httpResponseContent ? httpResponseContent : "");
				throw new NCResultFailedException("NC交易接口返回HTTP" + httpResponseCode);
			}

			if (null == httpResponseContent) {
				throw new NCResponseContentInvalidException("无法读取工行NC交易" + ncTransCode.getTransCode() + "接口返回的报文");
			} else if (0 == httpResponseContent.length()) {
				throw new NCResponseContentInvalidException("工行NC交易" + ncTransCode.getTransCode() + "返回的报文内容为空");
			}

			String[] nameValuePair = httpResponseContent.split("=", 2);
			if (2 != nameValuePair.length) {
				throw new NCResponseContentInvalidException("工行NC交易" + ncTransCode.getTransCode() + "接口返回的报文格式不正确");
			}

			String resultFieldName = nameValuePair[0];
			String resultFieldValue = nameValuePair[1];

			try {
				if ("reqData".equals(resultFieldName)) {
					byte[] decodedXmlResponseBytes = Base64Utils
							.decode(resultFieldValue.replace("\r", "").replace("\n", "").getBytes("GBK"));
					String xmlResponseContent = new String(decodedXmlResponseBytes, "GBK");
					if (isLoggerDebugEnabled) {
						logger.debug("解码后的工行NC交易XML响应报文: {}{}", StringUtil.LINE_SEPARATOR, xmlResponseContent);
					}
					return xmlResponseParser.parseXmlResponse(xmlResponseContent, ncTransCode.getXmlResponseType());
				} else if ("errorCode".equals(resultFieldName)) {
					// errorCode=40418
					throw new NCResultFailedException("NC-errorCode-" + resultFieldValue);
				} else {
					throw new NCResponseContentInvalidException("工行NC交易接口返回的报文格式不正确");
				}
			} catch (NCResultFailedException | NCResponseContentInvalidException e) {
				throw e;
			} catch (Exception e) {
				throw new NCResponseParseException("无法解析工行NC交易接口返回的报文，" + e.getMessage(), e);
			}
		} catch (Exception e) {
			logger.error("调用工行NC交易{}接口失败，packageID: {}，异常信息：{}.", ncTransCode.getTransCode(), request.getfSeqno(),
					e.getMessage(), e);
			if (e instanceof NCResultFailedException) {
				throw (NCResultFailedException) e;
			} else if (e instanceof NCResultUncertainException) {
				throw (NCResultUncertainException) e;
			} else if (e instanceof SocketTimeoutException) {
				throw new NCWaitResponseTimeoutException("等待工行NC返回交易结果超时", e);
			} else if (e instanceof ConnectException) {
				throw new NCResultFailedException("工行NC交易接口暂时不可用", e);
			} else {
				if (sendingHasBeenTriggered) {
					throw new NCResultUncertainException(
							ReturnCode.CORE_3RD_ICBC_BANKENT_NC_TRADE_RESPONSE_PARSE_FAILED,
							"处理NC结果失败[" + e.getMessage() + "]，交易结果暂时不能确定", e);
				} else {
					throw new NCResultFailedException("调用工行NC交易接口失败，" + e.getMessage(), e);
				}
			}
		} finally {
			if (null != mypost) {
				try {
					mypost.releaseConnection();
				} catch (Exception e) {
				}
			}
		}
	}

	private String signXmlRequestBeforeTrading(String url, String sContent) throws NCSignException {
		HttpURLConnection urlConnection = null;
		try {
			URL aURL = new java.net.URL(url);
			urlConnection = (HttpURLConnection) aURL.openConnection();
			urlConnection.setConnectTimeout(30000);
			urlConnection.setReadTimeout(120000);
			urlConnection.setRequestMethod("POST");
			urlConnection.setDoInput(true);
			urlConnection.setDoOutput(true);
			urlConnection.setUseCaches(false);
			urlConnection.setRequestProperty("Content-Length", String.valueOf(sContent.getBytes("GBK").length));
			urlConnection.setRequestProperty("Content-Type", "INFOSEC_SIGN/1.0");

			BufferedOutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());
			out.write(sContent.getBytes("GBK"));
			out.flush();
			out.close();

			int responseCode = urlConnection.getResponseCode();
			if (HttpURLConnection.HTTP_OK != responseCode) {
				logger.warn("工行NC签名接口HTTP响应错误：HTTP{}", responseCode);
				String errResContent = "";
				try {
					String errResMessage = urlConnection.getResponseMessage();
					logger.warn("工行NC签名接口响应错误内容为：{}{}", StringUtil.LINE_SEPARATOR,
							(null != errResMessage ? errResMessage : ""));

					int contentLen = urlConnection.getContentLength();
					if (isLoggerDebugEnabled) {
						logger.debug("工行NC签名接口响应错误内容的字节长度为：{}", String.valueOf(contentLen));
					}

					BufferedInputStream resContentInput = new BufferedInputStream(urlConnection.getInputStream());
					byte[] resContentBytes = readFullBytes(resContentInput, contentLen);
					errResContent = new String(resContentBytes, "GBK");

					logger.warn("工行NC签名接口响应错误内容为：{}{}", StringUtil.LINE_SEPARATOR,
							(null != errResContent ? errResContent : ""));

					try {
						resContentInput.close();
					} catch (Exception e) {
					}

				} catch (Exception e) {
				}
				throw new NCSignException("工行NC签名接口响应HTTP" + responseCode);
			}

			String resContent = "";
			try {
				String resHttpMessage = urlConnection.getResponseMessage();
				logger.info("工行NC签名接口返回HTTP消息：{}, {}", responseCode, (null != resHttpMessage ? resHttpMessage : ""));

				final int contentLen = urlConnection.getContentLength();
				if (isLoggerDebugEnabled) {
					logger.debug("工行NC签名接口响应正文字节长度为：{}", String.valueOf(contentLen));
				}

				BufferedInputStream resContentInput = new BufferedInputStream(urlConnection.getInputStream());
				byte[] resContentBytes = readFullBytes(resContentInput, contentLen);
				resContent = new String(resContentBytes, "GBK");

				try {
					resContentInput.close();
				} catch (Exception e) {
				}

			} catch (Exception e) {
				logger.error("无法读取工行NC签名接口的响应正文内容：{}", e.getMessage(), e);
				throw new NCSignException("无法读取工行NC签名接口响应正文内容", e);
			}

			if (isLoggerDebugEnabled) {
				logger.debug("成功接收到NC返回的签名结果：{}{}", StringUtil.LINE_SEPARATOR, resContent);
			}

			resContent = resContent.replace("\r", "").replace("\n", "");
			int beginSign = resContent.indexOf("<sign>") + 6;
			int endSign = resContent.indexOf("</sign>");

			String repSignContent = "";
			if (beginSign >= 6 && endSign > beginSign) {
				repSignContent = resContent.substring(beginSign, endSign);
			} else {
				logger.error("接收到NC返回的签名结果不符合格式要求，内容如下：{}", StringUtil.LINE_SEPARATOR, resContent);
				throw new NCSignException("接收到NC返回的签名结果不符合格式要求");
			}
			return repSignContent;
		} catch (IOException e1) {
			throw new NCSignException("NC签名IO通讯异常", e1);
		} finally {
			if (null != urlConnection) {
				try {
					urlConnection.disconnect();
				} catch (Exception e) {
				}
			}
		}
	}

	private byte[] readFullBytes(BufferedInputStream resContentInput, final int contentLen) throws IOException {
		byte[] resContentBytes = new byte[contentLen];
		int storeOffset = 0;
		int remainBytesLenToRead = contentLen;

		final long maxWaitEndTime = System.currentTimeMillis() + 5 * 60 * 1000L;
		int actualReadTotal = 0;
		int currReadCount = 0;
		while (remainBytesLenToRead > 0) {
			currReadCount = resContentInput.read(resContentBytes, storeOffset, remainBytesLenToRead);
			if (currReadCount > 0) {
				actualReadTotal += currReadCount;
				storeOffset += currReadCount;
				remainBytesLenToRead = contentLen - actualReadTotal;
			}

			if (remainBytesLenToRead > 0) {
				if (isLoggerDebugEnabled) {
					logger.debug("未能一次性读取完银企签名结果字节内容，总共需要读取{}字节，当前已读取{}字节，等待100ms后再重试读取剩余字节...", contentLen,
							actualReadTotal);
				}
				if (System.currentTimeMillis() > maxWaitEndTime) {
					throw new IllegalStateException("等待NC签名结果超时");
				}
				try {
					Thread.sleep(100L);
				} catch (Exception e) {
				}
			}
		}
		return resContentBytes;
	}

}
