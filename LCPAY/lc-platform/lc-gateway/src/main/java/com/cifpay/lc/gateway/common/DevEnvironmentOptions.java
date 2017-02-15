package com.cifpay.lc.gateway.common;

/**
 * 专门针对开发环境拟定的一些控制开关，例如是否禁用商户签名功能。
 * 
 * 
 *
 */
public abstract class DevEnvironmentOptions {

	private static abstract class PropertyGetter<T> {
		abstract T getProperty();
	}

	//
	/**
	 * 是否禁用Gateway接口的商户签名校验功能。JVM参数设置如下：<br>
	 * {@code
	 * -DdevEnvironmentOptions.disabledMerchantSignChecked=true
	 * }
	 */
	public static final boolean disabledMerchantSignChecked = new PropertyGetter<Boolean>() {
		@Override
		Boolean getProperty() {
			String prop = System.getProperty("devEnvironmentOptions.disabledMerchantSignChecked");
			if (null != prop && "true".equalsIgnoreCase(prop)) {
				return true;
			} else {
				return false;
			}
		}
	}.getProperty();

}
