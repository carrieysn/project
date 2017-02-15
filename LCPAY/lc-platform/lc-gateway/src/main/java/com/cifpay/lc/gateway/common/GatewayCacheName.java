package com.cifpay.lc.gateway.common;

/**
 * 维护Gateway所有可能使用到的缓存名字
 * 
 * 
 *
 */
public interface GatewayCacheName {

	/**
	 * 跟商户校验有关的商户概要信息
	 */
	String MERCHANT_PRIV_SUMMARY_INFO_CACHE = "CIFPAY-GATEWAY-MER_PRIV_SUMMARY";
	
	String BANK_CODE_INFO_CACHE = "CIFPAY-GATEWAY-BANK_CODE_BASE_INFO";

}
