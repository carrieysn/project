package com.cifpay.lc.bankadapter.unionpay.constant;

public class UnionPayConfigTool {
	
	public final static ConfigLoader PAY_CONFIG = new ConfigLoader("/com/cifpay/lc/bankadapter/unionpay/acp_sdk_dev.properties");
		

	public static String getString(String key,ConfigLoader config) {
		return config.getString(key);
	}

	public static int getInt(String key, int deValue,ConfigLoader config) {
		return config.getInt(key, deValue);
	}

	public static boolean getBoolean(String key, boolean deValue,ConfigLoader config) {
		return config.getBoolean(key, deValue);
	}

}
