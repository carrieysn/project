package com.cifpay.lc.gateway.controller.webpay.mobile;

import org.springframework.beans.factory.annotation.Autowired;

import com.cifpay.lc.api.gateway.basic.signkey.MerFrontValidationMaterial;
import com.cifpay.lc.gateway.common.MerchantSummaryInfoCache;
//import com.cifpay.lc.core.cache.pojo.MerchantCache;
//import com.cifpay.lc.core.cache.service.MerchantCacheServant;
import com.cifpay.lc.gateway.controller.GatewayBaseController;

/**
 * 输入支付信息（适用手机、PAD等移动终端的浏览器，包括终端上APP应用内嵌的浏览器组件）
 * 
 * 
 *
 */
public class MobileWebPayBaseController extends GatewayBaseController {

	@Autowired
	protected MerchantSummaryInfoCache merchantSummaryInfoCache;

	protected String getMerchantSignKey(String merId) {
		MerFrontValidationMaterial material = merchantSummaryInfoCache.getMerchantSummary(merId);
		
		String signKey = merchantSummaryInfoCache.getDecryptKey(material);
		return signKey;
	}

}
