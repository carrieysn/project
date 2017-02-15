package com.cifpay.lc.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class LcMd5SignTool {
	private final static String SIGN_FIELD_NAME = "sign";

	public static String signMap(Map<String, String> params, String privateKey) {
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);
		StringBuilder content = new StringBuilder();
		boolean firstField = true;
		for (int i = 0; i < keys.size(); ++i) {
			String key = (String) keys.get(i);
			if (null == key || 0 == key.trim().length() || SIGN_FIELD_NAME.equals(key)) {
				continue;
			}
			String value = params.get(key);
			if (null != value && value.trim().length() > 0) {
				if (firstField) {
					firstField = false;
				} else {
					content.append('&');
				}
				content.append(key);
				content.append('=');
				content.append(value);
			}
		}
		String signcontent = content + privateKey;
		return encrypt(signcontent);
	}

	public static String signString(String sortedKeyValPairsString, String privateKey) {
		String signcontent = sortedKeyValPairsString + privateKey;
		return encrypt(signcontent);
	}

	private static String bytesToHexString(byte[] bArray) {
		StringBuilder sb = new StringBuilder(bArray.length);

		for (int i = 0; i < bArray.length; ++i) {
			String sTemp = Integer.toHexString(0xFF & bArray[i]);
			if (sTemp.length() < 2)
				sb.append(0);
			sb.append(sTemp.toUpperCase());
		}
		return sb.toString();
	}

	private static String encrypt(String source) {
		try {
			byte[] bt = source.getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(bt);
			return bytesToHexString(md.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("MD5 is not supported.");
		} catch (UnsupportedEncodingException e1) {
			// should never happen
			throw new RuntimeException("UTF-8 is not supported.");
		}
	}
}
