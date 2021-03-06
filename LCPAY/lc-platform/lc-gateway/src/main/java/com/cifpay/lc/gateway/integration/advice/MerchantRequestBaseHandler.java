package com.cifpay.lc.gateway.integration.advice;

import com.cifpay.lc.api.gateway.basic.signkey.MerFrontValidationMaterial;
import com.cifpay.lc.constant.ReturnCode;
import com.cifpay.lc.constant.enums.MerchantStatus;
import com.cifpay.lc.domain.security.MerchantRequest;
import com.cifpay.lc.gateway.common.DevEnvironmentOptions;
import com.cifpay.lc.gateway.common.MerchantSummaryInfoCache;
import com.cifpay.lc.gateway.common.exception.GatewayAuthenticationFailure;
import com.cifpay.lc.gateway.common.exception.GatewayValidationRejectException;
import com.cifpay.lc.util.LcMd5SignTool;
import com.cifpay.lc.util.MethodParameterUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;

/**
 * 针对Gateway接口接收到的第一手请求内容进行最基本的处理，包括参数非空、签名校验；数据解码并解析成Java对象。
 */
@Component
public class MerchantRequestBaseHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private boolean isLoggerDebugEnabled = logger.isDebugEnabled();

    @Autowired
    private MerchantSummaryInfoCache merchantSummaryInfoCache;

    private ObjectMapper jsonMapper = new ObjectMapper();

    /**
     * 针对Gateway接口接收到的第一手请求内容进行最基本的校验，包括参数非空、签名校验。
     *
     * @param merReq
     * @throws GatewayValidationRejectException 校验不通过时，通过抛出GatewayValidationRejectException异常表示。
     */
    public void validateMerRequest(MerchantRequest<?> merReq) throws GatewayValidationRejectException {
        if (isLoggerDebugEnabled) {
            logger.debug("~~~开始校验商户请求的JSON对象，商户ID：{}，签名：{}.", String.valueOf(merReq.getMerId()), String.valueOf(merReq.getSign()));
        }

        if (!StringUtils.hasText(merReq.getMerId())) {
            throw new GatewayValidationRejectException(ReturnCode.GW_GENERIC_VALIDATION_REJECTED, "非法请求，参数缺少商户ID");
        }

        if (!DevEnvironmentOptions.disabledMerchantSignChecked) {
            if (!StringUtils.hasText(merReq.getSign())) {
                throw new GatewayValidationRejectException(ReturnCode.GW_GENERIC_VALIDATION_REJECTED, "非法请求，参数缺少签名信息");
            }
        }

        if (!StringUtils.hasText(merReq.getEncodedJsonData()) && !DevEnvironmentOptions.disabledMerchantSignChecked) {
            throw new GatewayValidationRejectException(ReturnCode.GW_GENERIC_VALIDATION_REJECTED, "非法请求，参数缺少编码后的JSON数据");
        }

        if (null != merReq.getData()) {
            throw new GatewayValidationRejectException(ReturnCode.GW_GENERIC_VALIDATION_REJECTED, "非法请求，请传递编码后的JSON数据");
        }

        // 验证商户签名
        MerFrontValidationMaterial material = merchantSummaryInfoCache.getMerchantSummary(merReq.getMerId());
        if (null == material) {
            throw new GatewayAuthenticationFailure(ReturnCode.GW_MER_ID_NOT_EXISTS, "商户ID未注册");
        }
        if (MerchantStatus.WAIT.getStatus().equals(material.getMerchantStatus())) {
            throw new GatewayAuthenticationFailure(ReturnCode.GW_MERCHANT_PENDING, "商户ID未审核");
        }
        if (MerchantStatus.CLOSED.getStatus().equals(material.getMerchantStatus())) {
            throw new GatewayAuthenticationFailure(ReturnCode.GW_MERCHANT_PENDING, "商户ID已禁用");
        }

        String decryptKey = merchantSummaryInfoCache.getDecryptKey(material);
        String correctSign = LcMd5SignTool.signString(merReq.getEncodedJsonData(), decryptKey);
        if (!correctSign.toUpperCase().equals(merReq.getSign().toUpperCase())) {
            throw new GatewayAuthenticationFailure(ReturnCode.GW_MER_REQUEST_SIGN_INVALID, "非法请求，商户签名不正确");
        }
    }

    /**
     * 解码请求中的encodedJsonData参数成Java对象。
     *
     * @param merReq
     */
    public void decodeRequestData(MerchantRequest<Object> merReq, MethodParameter parameter) {
        String reqDataBase64Json = merReq.getEncodedJsonData();

        // Base64解码
        String reqDataJson;
        try {
            reqDataJson = new String(Base64Utils.decodeFromString(reqDataBase64Json), merReq.getCharset());
        } catch (Exception e) {
            if (isLoggerDebugEnabled) {
                logger.debug("Base64解码失败，商户请求的encodedJsonData内容为：{}", reqDataBase64Json);
            }
            throw new GatewayValidationRejectException(ReturnCode.GW_GENERIC_VALIDATION_REJECTED, "encodedJsonData数据格式不正确");
        }

        if (isLoggerDebugEnabled) {
            logger.debug("~~~解码后的商户请求数据，商户ID：{}，数据：{}", merReq.getMerId(), reqDataJson);
        }

        // 解析成Java对象
        Class<?> reqDataType = MethodParameterUtils.getRequestDataType(parameter);
        try {
            Object data = jsonMapper.readValue(reqDataJson, reqDataType);
            merReq.setData(data);
        } catch (com.fasterxml.jackson.databind.exc.InvalidFormatException e) {
            String message = "参数不正确：" + (e.getPath().isEmpty() ? "" : e.getPath().get(0).getFieldName());
            if (isLoggerDebugEnabled) {
                logger.debug(message);
            }
            throw new GatewayValidationRejectException(ReturnCode.GW_GENERIC_VALIDATION_REJECTED, message, e);
        } catch (Exception e) {
            if (isLoggerDebugEnabled) {
                logger.debug("JSON解析失败，目标data类型：{}, 商户请求的encodedJsonData内容解码后的JSON内容为：{}", reqDataType.getName(), reqDataJson);
            }
            throw new GatewayValidationRejectException(ReturnCode.GW_GENERIC_VALIDATION_REJECTED, "encodedJsonData数据格式不正确", e);
        }
    }

}
