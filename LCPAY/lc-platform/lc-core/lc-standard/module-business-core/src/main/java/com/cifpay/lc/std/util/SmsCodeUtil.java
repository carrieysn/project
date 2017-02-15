package com.cifpay.lc.std.util;

public class SmsCodeUtil {
	
	/**
	 * 生成六位随机短信验证码
	 * @return
	 */
	public static String randomcode() {
		int random = (int) ((Math.random() * 9 + 1) * 100000);
		return String.valueOf(random);
	}

}
