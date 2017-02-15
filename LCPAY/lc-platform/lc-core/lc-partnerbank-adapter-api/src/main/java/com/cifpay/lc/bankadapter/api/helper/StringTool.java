package com.cifpay.lc.bankadapter.api.helper;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class StringTool {
	private static Logger LOGGER = LoggerFactory.getLogger(StringTool.class);

	public static String printPhone(String phone) {
		if (LOGGER.isDebugEnabled()) {
			return phone;
		}
		
		if (!StringUtils.isEmpty(phone)) {
			return phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
		}
		return "";
	}

	public static String printCard(String cardNo) {
		if (LOGGER.isDebugEnabled()) {
			return cardNo;
		}
		
		if (cardNo != null && cardNo != "") {
			if (cardNo.length() > 4) {// 判断是否长度大于等于4
				String strsub1 = cardNo.substring(cardNo.length() - 4, cardNo.length());// 截取两个数字之间的部分
				return "********" + strsub1;
			} else if (cardNo.length() == 1) {
				return "****";
			} else {
				String strsub1 = cardNo.substring(cardNo.length() - 1, cardNo.length());// 截取两个数字之间的部分
				return "****" + strsub1;
			}
		}
		return "";
	}

	public static String printMap(Map<String, String> map) {
		if (LOGGER.isDebugEnabled()) {
			return map.toString();
		}
		StringBuilder sb = new StringBuilder("Map[");
		if (map != null && map.size() > 0) {
			for (Map.Entry entry : map.entrySet()) {
				String key = entry.getKey().toString();
				String value = (String) entry.getValue();
				if ("accNo".equalsIgnoreCase(key)) {
					value = printCard(entry.getValue().toString());
				} else if ("signature".equalsIgnoreCase(key)) {
					value = printCard(entry.getValue().toString());
				} else if ("customerInfo".equalsIgnoreCase(key)) {
					value = printCard(entry.getValue().toString());
				} else if ("phoneNo".equalsIgnoreCase(key)) {
					value = printPhone(entry.getValue().toString());
				} else if ("tokenPayData".equalsIgnoreCase(key)) {
					value = printCard(entry.getValue().toString());
				} else if ("reqReserved".equalsIgnoreCase(key)) {
					value = printCard(entry.getValue().toString());
				} else if ("smsCode".equalsIgnoreCase(key) || "cvn2".equalsIgnoreCase(key)
						|| "expired".equalsIgnoreCase(key)) {
					value = "******";
				}
				sb.append(key + " = " + value + ",");
			}
			sb.append("]");
		}
		return sb.toString();
	}

}
