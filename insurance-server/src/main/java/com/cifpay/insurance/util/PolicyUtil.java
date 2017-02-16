package com.cifpay.insurance.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang.RandomStringUtils;

public class PolicyUtil {

	private PolicyUtil() {
	}

	// 生成保单号
	public static String getRandomPolicyNo(int length) {
		String valueStr = "";
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; // 输出字母还是数字
			if ("char".equalsIgnoreCase(charOrNum)){ // 字符串
				int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; // 取得大写字母还是小写字母
				valueStr += (char) (choice + random.nextInt(26));
			} else if ("num".equalsIgnoreCase(charOrNum)) { // 数字
				valueStr += String.valueOf(random.nextInt(10));
			}
		}
		return valueStr;
	}

	// 生成充值订单号
	public static String getBillOrderNo() {
		long billNo = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String nowdate = sdf.format(new Date());
		billNo = Long.parseLong(nowdate);
		String rodom = RandomStringUtils.randomNumeric(5);
		return billNo + rodom;
	}

}
