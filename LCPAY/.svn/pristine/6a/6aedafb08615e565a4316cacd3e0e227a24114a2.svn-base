package com.cifpay.lc.gateway.integration.normal;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.cifpay.lc.api.gateway.basic.signkey.MerFrontValidationMaterial;
import com.cifpay.lc.constant.ReturnCode;
import com.cifpay.lc.constant.enums.MerchantStatus;
import com.cifpay.lc.gateway.common.DevEnvironmentOptions;
import com.cifpay.lc.gateway.common.MerchantSummaryInfoCache;
import com.cifpay.lc.gateway.common.exception.GatewayValidationRejectException;
import com.cifpay.lc.gateway.input.NormalRequest;
import com.cifpay.lc.gateway.output.NormalResponse;
import com.cifpay.lc.util.MethodParameterUtils;
import com.cifpay.lc.util.security.Base64;
import com.cifpay.lc.util.security.MD5Utils;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class NormalMerchantHandler {
	private static Logger logger = LoggerFactory.getLogger(NormalMerchantHandler.class);
	private static boolean isLoggerDebugEnabled = logger.isDebugEnabled();
	
	@Autowired
	MerchantSummaryInfoCache merchantSummaryInfoCache;

	private ObjectMapper jsonMapper = new ObjectMapper();

	/**
	 * 针对Gateway接口接收到的第一手请求内容进行最基本的校验，包括参数非空、签名校验。
	 * 
	 * @param merReq
	 * @throws GatewayValidationRejectException
	 *             校验不通过时，通过抛出GatewayValidationRejectException异常表示。
	 */
	public void validateMerRequest(NormalRequest<?> request) throws GatewayValidationRejectException {
		if (isLoggerDebugEnabled) {
			logger.debug("~~~开始校验商户请求的JSON对象，商户ID：{}，签名：{}.", String.valueOf(request.getAppId()), String.valueOf(request.getSign()));
		}

		if (!StringUtils.hasText(request.getAppId())) {
			throw new GatewayValidationRejectException(ReturnCode.GW_GENERIC_VALIDATION_REJECTED, "非法请求，参数缺少商户ID");
		}

		if (!DevEnvironmentOptions.disabledMerchantSignChecked) {
			if (!StringUtils.hasText(request.getSign())) {
				throw new GatewayValidationRejectException(ReturnCode.GW_GENERIC_VALIDATION_REJECTED, "非法请求，参数缺少签名信息");
			}
		}

		if (!StringUtils.hasText(request.getData()) && !DevEnvironmentOptions.disabledMerchantSignChecked) {
			throw new GatewayValidationRejectException(ReturnCode.GW_GENERIC_VALIDATION_REJECTED, "非法请求，参数缺少业务数据");
		}
		//MerchantCache merchantCache = merchantCacheServant.getMerchantSummary(request.getAppId());
		MerFrontValidationMaterial material = merchantSummaryInfoCache.getMerchantSummary(request.getAppId());
		if (null == material) {
			throw new GatewayValidationRejectException(ReturnCode.GW_MER_ID_NOT_EXISTS, "商户ID未注册");
		}
		if (MerchantStatus.WAIT.getStatus().equals(material.getMerchantStatus())) {
			throw new GatewayValidationRejectException(ReturnCode.GW_MERCHANT_PENDING, "商户ID未审核");
		}
		if (MerchantStatus.CLOSED.getStatus().equals(material.getMerchantStatus())) {
			throw new GatewayValidationRejectException(ReturnCode.GW_MERCHANT_PENDING, "商户ID已禁用");
		}

		if (!DevEnvironmentOptions.disabledMerchantSignChecked) {
			if (null != request.getDecryptData()) {
				throw new GatewayValidationRejectException(ReturnCode.GW_GENERIC_VALIDATION_REJECTED, "非法请求，请传递编码后的JSON数据");
			}
			String decryptKey = merchantSummaryInfoCache.getDecryptKey(material);
			boolean verifySuccess = verify(request, decryptKey, request.getSign());
			if (!verifySuccess) {
				throw new GatewayValidationRejectException(ReturnCode.GW_MER_REQUEST_SIGN_INVALID, "非法请求，商户签名不正确");
			}
		}
	}

	/**
	 * 解码请求中的encodedJsonData参数成Java对象。
	 * 
	 * @param merReq
	 */
	public Object decodeRequestData(String rawData, MethodParameter parameter) {
		if (isLoggerDebugEnabled) {
			logger.debug("~~~解码后的商户请求数据，，数据：{}", rawData);
		}

		// 解析成Java对象
		Class<?> reqDataType = MethodParameterUtils.getRequestDataType(parameter);
		try {
			if (reqDataType.isAssignableFrom(String.class)) {
				return rawData;
			} else {
				Object data = jsonMapper.readValue(rawData, reqDataType);
				return data;
			}

		} catch (com.fasterxml.jackson.databind.exc.InvalidFormatException e) {
			String message = "参数不正确：" + (e.getPath().isEmpty() ? "" : e.getPath().get(0).getFieldName());
			if (isLoggerDebugEnabled) {
				logger.debug(message);
			}
			throw new GatewayValidationRejectException(ReturnCode.GW_GENERIC_VALIDATION_REJECTED, message, e);
		} catch (Exception e) {
			if (isLoggerDebugEnabled) {
				logger.debug("JSON解析失败，目标data类型：{}, 商户请求的encodedJsonData内容解码后的JSON内容为：{}", reqDataType.getName(), rawData);
			}
			throw new GatewayValidationRejectException(ReturnCode.GW_GENERIC_VALIDATION_REJECTED, "encodedJsonData数据格式不正确", e);
		}
	}

	public String sign(NormalRequest<?> request, NormalResponse response) {
		if (request == null) {
			throw new GatewayValidationRejectException(ReturnCode.GW_GENERIC_VALIDATION_REJECTED, "参数不正确");
		}
		if (response == null) {
			throw new GatewayValidationRejectException(ReturnCode.GW_GENERIC_VALIDATION_REJECTED, "返回值不正确");
		}

		MerFrontValidationMaterial material = merchantSummaryInfoCache.getMerchantSummary(request.getAppId());
		String signKey = merchantSummaryInfoCache.getEncryptKey(material);
		try {
			String strResponse = jsonMapper.writeValueAsString(response);
			String strSign = createSign(strResponse, signKey, request.getCharset());

			return strSign;

		} catch (Throwable e1) {
			throw new GatewayValidationRejectException(ReturnCode.GW_GENERIC_VALIDATION_REJECTED, "签名过程出错");
		}
	}

	/**
	 * 验签
	 * 
	 * @param request
	 * @param apiKey
	 * @param sign
	 * @return
	 */
	private boolean verify(NormalRequest<?> request, String apiKey, String sign) {
		try {
			String correctSign = createSign(request, apiKey, request.getCharset());
			if (correctSign.toUpperCase().equals(sign.toUpperCase())) {
				return true;
			}
		} catch (UnsupportedEncodingException e) {
			throw new GatewayValidationRejectException(ReturnCode.GW_MERCHANT_PENDING, "不支持的编码格式");
		}
		return false;
	}

	private String createSign(NormalRequest<?> request, String key, String encoding) throws UnsupportedEncodingException {
		// 所有参与传参的参数按照ASCII排序（升序）
		SortedMap<String, String> parameters = new TreeMap<String, String>();
		parameters.put("appId", request.getAppId());
		parameters.put("service", request.getService());
		parameters.put("charset", request.getCharset());
		parameters.put("signType", request.getSignType());
		parameters.put("version", request.getVersion());
		parameters.put("timesTamp", request.getTimesTamp());
		parameters.put("mobile", request.getMobile());
		String data = URLEncoder.encode(request.getData(), encoding);
		parameters.put("data", data);

		StringBuffer rawData = new StringBuffer();
		Set<Entry<String, String>> es = parameters.entrySet();

		for (Entry<String, String> entry : es) {
			String paramKey = (String) entry.getKey();
			Object paramValue = entry.getValue();
			if (null != paramValue && !"".equals(paramValue) && !"sign".equals(paramKey) && !"key".equals(paramKey)) {
				rawData.append(paramKey + "=" + paramValue + "&");
			}
		}
		rawData.deleteCharAt(rawData.length() - 1);

		return createSign(rawData.toString(), key, encoding);
	}

	/**
	 * 加签
	 * 
	 * @param sortedKeyValPairsString
	 * @param privateKey
	 * @return
	 */
	private static String createSign(String data, String key, String encoding) throws UnsupportedEncodingException {
		logger.debug("签名数据：" + data);

		String md5Sign = MD5Utils.calcMD5(data + key, encoding).toLowerCase();
		String base64Sign = Base64.encodeByte(md5Sign.getBytes(encoding));
		logger.debug("签名结果：" + base64Sign);
		return base64Sign;
	}

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		String rawData = "{\"returnCode\":0,\"returnMsg\":\"成功\",\"lcID\":\"42093741103751168\",\"tradeNo\":\"14703887\",\"tradeStatus\":\"88\",\"orderId\":null,\"amount\":\"500000\"}";

		String sign = createSign(rawData, "44a3e12349ca4128867bf73761e23e26", "utf-8");
		System.out.println(sign);

		String md5 = MD5Utils.calcMD5(rawData, "utf-8");
		System.out.println(md5);

	}

}
