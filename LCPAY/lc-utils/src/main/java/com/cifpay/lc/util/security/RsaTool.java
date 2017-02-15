package com.cifpay.lc.util.security;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

public class RsaTool {

	private static final String RSA = "RSA";

	private static final int KEY_SIZE = 1024;

	private static final String PUBLIC_KEY = "publicKey";

	private static final String PRIVATE_KEY = "privatekey";

	/** 对密文进行Base64加密 */
	private static final String Base64_TYPE = "Base64";

	/** 对密文进行十六进制编码 */
	private static final String HEX_TYPE = "HEX";

	/** 十六进制值 */
	private final static String HEX_VALUE = "0123456789ABCDEF";

	/** RSA最大加密明文大小 */
	private static final int MAX_ENCRYPT_BLOCK = 117;

	/** RSA最大解密密文大小 */
	private static final int MAX_DECRYPT_BLOCK = 128;

	private static final String TRANSFORMATION_SHA1WITHRSA = "SHA1WithRSA";

	/**
	 * 生成公钥和私钥
	 */
	public static HashMap<String, Object> getKeys() throws NoSuchAlgorithmException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(RSA);
		keyPairGen.initialize(KEY_SIZE);
		KeyPair keyPair = keyPairGen.generateKeyPair();
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		map.put(PUBLIC_KEY, publicKey);
		map.put(PRIVATE_KEY, privateKey);
		return map;
	}

	/**
	 * 取得公钥,用Base64加密
	 */
	public static String getPublicKeyBase64(Map<String, Key> keyMap) {

		return getPublicKey(keyMap, Base64_TYPE);
	}

	/**
	 * 取得公钥,用HEX加密
	 */
	public static String getPublicKeyHex(Map<String, Key> keyMap) {

		return getPublicKey(keyMap, HEX_TYPE);
	}

	/**
	 * 取得公钥,返回指定格式的加密公钥
	 */
	private static String getPublicKey(Map<String, Key> keyMap, String encryptType) {

		Key key = keyMap.get(PUBLIC_KEY);

		if (HEX_TYPE.equalsIgnoreCase(encryptType)) {
			return toHex(key.getEncoded());
		}

		return Base64.encodeByte(key.getEncoded());
	}

	/**
	 * 取得私钥,用Base64加密
	 */
	public static String getPrivateKeyBase64(Map<String, Key> keyMap) {

		return getPrivateKey(keyMap, Base64_TYPE);
	}

	/**
	 * 取得私钥,默认用HEX加密
	 */
	public static String getPrivateKeyHex(Map<String, Key> keyMap) {

		return getPrivateKey(keyMap, HEX_TYPE);
	}

	/**
	 * 取得私钥,返回指定格式的加密私钥
	 */
	private static String getPrivateKey(Map<String, Key> keyMap, String encryptType) {

		Key key = keyMap.get(PRIVATE_KEY);

		if (HEX_TYPE.equalsIgnoreCase(encryptType)) {
			return toHex(key.getEncoded());
		}

		return Base64.encodeByte(key.getEncoded());
	}

	/**
	 * 公钥部分
	 */

	/**
	 * 用公钥加密数据,用Base64解密公钥
	 */
	public static String encryptByPublicKeyBase64(String data, String publicKey) throws Exception {

		return encryptByPublicKey(data, publicKey, Base64_TYPE);
	}

	/**
	 * 用公钥加密数据,用HEX解密公钥
	 */
	public static String encryptByPublicKeyHex(String data, String publicKey) throws IOException, Exception {

		return encryptByPublicKey(data, publicKey, HEX_TYPE);
	}

	/**
	 * 用公钥加密数据,按指定格式解密公钥,默认用Base64解密
	 */
	private static String encryptByPublicKey(String data, String publicKey, String decryptType)
			throws IOException, Exception {

		if (HEX_TYPE.equalsIgnoreCase(decryptType)) {

			return encryptByPublicKey(data, toByte(publicKey));
		}

		return encryptByPublicKey(data, Base64.decodeByte(publicKey));
	}

	/**
	 * 用公钥加密,加密后的数据有很多乱码 所以用Base64再加一道密
	 * 
	 */
	private static String encryptByPublicKey(String data, byte[] keyBytes) throws Exception {

		// 取得公钥
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(RSA);
		Key key = keyFactory.generatePublic(x509KeySpec);

		// 对数据加密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, key);

		byte[] result = encryptWithBlock(data.getBytes(), cipher);

		String result64 = Base64.encodeByte(result);

		return result64;
	}

	/**
	 * 用公钥解密数据,用Base64解密公钥
	 */
	public static String decryptByPublicKeyBase64(String data, String publicKey) throws Exception {

		return decryptByPublicKey(data, publicKey, Base64_TYPE);
	}

	/**
	 * 用公钥解密数据,用HEX解密公钥
	 */
	public static String decryptByPublicKeyHex(String data, String publicKey) throws IOException, Exception {

		return decryptByPublicKey(data, publicKey, HEX_TYPE);
	}

	/**
	 * 用公钥解密数据,按指定格式解密公钥,默认用Base64解密
	 */
	private static String decryptByPublicKey(String data, String publicKey, String decryptType)
			throws IOException, Exception {

		if (HEX_TYPE.equalsIgnoreCase(decryptType)) {

			return decryptByPublicKey(data, toByte(publicKey));
		}

		return decryptByPublicKey(data, Base64.decodeByte(publicKey));
	}

	/**
	 * 用公钥解密
	 */
	private static String decryptByPublicKey(String data, byte[] keyBytes) throws Exception {

		byte[] dataBytes = Base64.decodeByte(data);

		// 取得公钥
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(RSA);
		Key key = keyFactory.generatePublic(x509KeySpec);

		// 对数据解密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, key);

		return new String(decryptWithBlock(dataBytes, cipher));
	}

	/**
	 * 私钥部分
	 */

	/**
	 * 用私钥加密数据,用Base64解密私钥
	 */
	public static String encryptByPrivateKeyBase64(String data, String privateKey) throws Exception {

		return encryptByPrivateKey(data, privateKey, Base64_TYPE);
	}

	/**
	 * 用私钥加密数据,用HEX解密私钥
	 */
	public static String encryptByPrivateKeyHex(String data, String privateKey) throws IOException, Exception {

		return encryptByPrivateKey(data, privateKey, HEX_TYPE);
	}

	/**
	 * 用私钥加密数据,按指定格式解密私钥,默认用Base64解密
	 */
	private static String encryptByPrivateKey(String data, String privateKey, String decryptType)
			throws IOException, Exception {

		if (HEX_TYPE.equalsIgnoreCase(decryptType)) {

			return encryptByPrivateKey(data, toByte(privateKey));
		}

		return encryptByPrivateKey(data, Base64.decodeByte(privateKey));
	}

	/**
	 * 用私钥加密,加密后的数据有很多乱码 所以用Base64再加一道密
	 */
	private static String encryptByPrivateKey(String data, byte[] keyBytes) throws Exception {

		// 取得私钥
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(RSA);
		Key key = keyFactory.generatePrivate(pkcs8KeySpec);

		// 对数据加密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, key);

		byte[] result = encryptWithBlock(data.getBytes(), cipher);

		String result64 = Base64.encodeByte(result);

		return result64;

	}

	/**
	 * 用私钥解密数据,用Base64解密私钥
	 */
	public static String decryptByPrivateKeyBase64(String data, String privateKey) throws Exception {

		return decryptByPrivateKey(data, privateKey, Base64_TYPE);
	}

	/**
	 * 用私钥解密数据,用HEX解密私钥
	 */
	public static String decryptByPrivateKeyHex(String data, String privateKey) throws IOException, Exception {

		return decryptByPrivateKey(data, privateKey, HEX_TYPE);
	}

	/**
	 * 用私钥解密数据,按指定格式解密私钥,默认用Base64解密
	 */
	private static String decryptByPrivateKey(String data, String privateKey, String decryptType)
			throws IOException, Exception {

		if (HEX_TYPE.equalsIgnoreCase(decryptType)) {

			return decryptByPrivateKey(data, toByte(privateKey));
		}

		return decryptByPrivateKey(data, Base64.decodeByte(privateKey));
	}

	/**
	 * 用私钥解密
	 */
	private static String decryptByPrivateKey(String data, byte[] keyBytes) throws Exception {

		byte[] dataBytes = Base64.decodeByte(data);

		// 取得私钥
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(RSA);
		Key key = keyFactory.generatePrivate(pkcs8KeySpec);

		// 对数据解密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, key);

		return new String(decryptWithBlock(dataBytes, cipher));
	}

	/** --------------------------------------------------------------------- */

	/**
	 * 字符串转十六进制字节
	 */
	private static byte[] toByte(String hexString) {

		int len = hexString.length() / 2;
		byte[] result = new byte[len];

		for (int i = 0; i < len; i++) {
			result[i] = Integer.valueOf(hexString.substring(2 * i, 2 * i + 2), 16).byteValue();
		}

		return result;

	}

	/**
	 * 十六进制字节转字符串
	 */
	private static String toHex(byte[] buf) {

		if (buf == null) {
			return "";
		}

		StringBuffer result = new StringBuffer(2 * buf.length);
		for (int i = 0; i < buf.length; i++) {
			appendHex(result, buf[i]);
		}

		return result.toString();

	}

	private static void appendHex(StringBuffer sb, byte b) {

		sb.append(HEX_VALUE.charAt((b >> 4) & 0x0f)).append(HEX_VALUE.charAt(b & 0x0f));
	}

	/**
	 * 分块加密 加密要求密文最大长度为117字节
	 */
	private static byte[] encryptWithBlock(byte[] data, Cipher cipher) throws Exception {
		byte[] cache;
		int i = 0, offSet = 0, inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		// 对数据分段加密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
				cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(data, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_ENCRYPT_BLOCK;
		}

		byte[] decryptedData = out.toByteArray();
		out.close();

		return decryptedData;
	}

	/**
	 * 分块解密 解密要求密文最大长度为128字节
	 */
	private static byte[] decryptWithBlock(byte[] data, Cipher cipher) throws Exception {

		byte[] cache;
		int i = 0, offSet = 0, inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		// 对数据分段解密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
				cache = cipher.doFinal(data, offSet, MAX_DECRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(data, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_DECRYPT_BLOCK;
		}

		byte[] decryptedData = out.toByteArray();
		out.close();

		return decryptedData;
	}

	/**
	 * 签名
	 */
	public static String sign(String data, String privateKey) throws Exception {

		byte[] dataBytes = Base64.decodeByte(privateKey);
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(dataBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(RSA);
		PrivateKey key = keyFactory.generatePrivate(pkcs8KeySpec);

		Signature sign = Signature.getInstance(TRANSFORMATION_SHA1WITHRSA);

		sign.initSign(key);
		sign.update(data.getBytes("utf-8"));

		/** 签名 */
		byte[] result = sign.sign();

		if (null == result) {
			throw new RuntimeException("RSASign Encrypt ERROR! Return value is NULL!");
		}

		return Base64.encodeByte(result);
	}

	/**
	 * 验签
	 */
	public static boolean verifySign(byte[] data, byte[] signData, String publicKey) throws Exception {

		X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(Base64.decodeByte(publicKey));

		KeyFactory keyfactory = KeyFactory.getInstance(RSA);
		PublicKey key = keyfactory.generatePublic(publicKeySpec);

		Signature sign = Signature.getInstance(TRANSFORMATION_SHA1WITHRSA);
		// Signature sign = Signature.getInstance("NONEwithRSA");

		sign.initVerify(key);
		sign.update(data);

		return sign.verify(signData);

	}

	// /** 测试签名数据 */
	// static String test_sign =
	// "ai5M0u7k8nTgBcAAMMFGVAUjiqLZxaSmadZApPNeodsQ/MM5XcDPgi7iTlglCKOY9bhj4Zk4BZ3lO3yNmPraIutuMqApl0Uhv6OkPJXIgeuy2V9BgUDJzFDbBt0M8h4rg6Y7c9I52gaaGEEioIGvsbF7MHMdEpFJQDTS49EWBNo=";
	// /** 私钥加密的数据 */
	// static String test_encryptByPri =
	// "CHBgWh/1OyianIDhrpZ2he4CQsoE7zaTLGLA1kIdxM8mp0m1qiFqkMhAYz27uA6qXyrFmkwZzhVpLI3E2eTwVEJMr4xSFHycgYyVnw/bKnls77FbHV/K7wg14zK8cuz3FHjbwftoVHtZWKb01AqFLUUxkJhCOfqP1KLcMawLRHA=";
	// /** 公钥加密的数据 */
	// static String test_encryptByPub =
	// "cshaefYxF5fk32AicG0ioZzsVREqXbIZQxRXU5btKgW+bV5ZLWsFlk3fEudRuAWq7bShlw69nokR19WPb4sAvNSvlMjFtMDAXI6GyM6myQxZSz/VHAI1mCqrCvPWbokxmwNpORQM/Yoixb7FtgC9GphtHN5qLQLLbkaitbFjRvE=";
	//
	// static String php_pub_der =
	// "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDKn2U0YrlgTjy4s2tEEFlIF/KR6nTdlvZ+OLKrGbG0mO9a/ChQ1i2A/Dte0YRJDMV3wveywnH3FwHqmtVdyNXbG6TCkem2gvg606PQI+w5Rp5HAoLI6EyDrsfdhkvzOhjoYJJpqHjoBz7ILDd/1AnWgNfhdolWyEshpUpBHHjCBQIDAQAB";
	// static String php_pri_der =
	// "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAMqfZTRiuWBOPLiza0QQWUgX8pHqdN2W9n44sqsZsbSY71r8KFDWLYD8O17RhEkMxXfC97LCcfcXAeqa1V3I1dsbpMKR6baC+DrTo9Aj7DlGnkcCgsjoTIOux92GS/M6GOhgkmmoeOgHPsgsN3/UCdaA1+F2iVbISyGlSkEceMIFAgMBAAECgYA1LLWTlOEPXBjlY/ifiSbVG6vGDWCUEp0nrNt+uaSAP655EznFVR/l/Mvb62IrBZYBAwKcQBlGfhw7pFygWyd7/FD3+9x2LKI1kruhhGcyjFW8y71SJIMZa7n9P+bILLC0vyt5zkcH3nWiR3GQ+wbvJyy9PnIjDYk5Sc7xBpaKdQJBAO4weHQCA60DYY5MQvu7kahUJKK8io2hXzL2loEjwgg58Abx/dgZg/5dHpY33W3yL2wERRSK4qs6gb9+ZU7jH68CQQDZxhc6nF3+AVGktFpimfbEQYOfdrtbf5spO1DJOy8LXcfA945OIopXH02sJi3byTzsQLk58uF9hx+Xjzx0p9KLAkEAjHPOg3GwozPsMdGsae5n7J95XvDYrr1qeo3Hn+zq/JjVffLyYZxiJfrZD3Fvw+ZcqlA2P+/Jy3hbjATHTmZFhwJBAK6zwmjLjn+50SRfZu8Y8qTIpe3kUzN48CGoqwt0Bi6JIjmEn03BWdfAjhAfZMSf8NbaqX16gjiwkMCGMQqoozcCQQCPNURQMcaGhP36DEkpuovNtTv3wfb+erJejsZcuK7R965djuy6lakvEWt3JAK1HzK9Q4Rwlt2YHsfLW5XxEW9m";
	//
	// static String cifpay_pri =
	// "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBANybRYBsib9BQ8hK55fMSZ5A0BW4iwkkCdH71B1VOjFPNUs8b/fc4L7hdJOIPv10qTtQ1L22kOm52zYgm84FItgpOtiPuCKu3o479hYzMdIHcNWpfrIokM8r5lRJdsZm9k9LISiZKDAadbW++Re7M3m74QTts3edqluA/gKxlaFDAgMBAAECgYEAlTKB/8foJSwbjoNyO8yS4W+OC5KinYz9A0Hgg/IXCDA8p+R5CVYdvZq2/+w+RaY3qgER0Dh/q0LiSaomFWCN3ggPY+4yqhY1ERYS+g4SNjaHwzBxhQYQE318a9DifqC+0MHNHdZUp00Xq8Fowx83e1Ueu3O475XgZ6XoHVLQdwkCQQDxhAvi0/8cHydqZrN30kx9uY1RbQvx55mHA924YXzghCm7ZAQzT6wWiuGgowLiuSimIR6TZcuvS9eAqnrBcqcHAkEA6dY1UU0YRsuUwKjoecUvcJpdaDg/AiuK65Yj5WzT6VqFV+8X3hm59PDcAIHS+o0qbJaPKij2+J8LoFt1t0II5QJAVHyK4+ihX6cOVMxkj9ADbh/Q7eElwpPwQyj5ER0ZYDkbmDAewAxo+OHQmC8uPT3kmGkRlyKPJF11n88DblS7VQJANlOYC413yGrf4mrbp/7R6L1UWjRHHz/yhI1bRaAlE5/+PtHDn8wmUevrkQqYc7F8W8IW/NqLlnONBfJ5xNvIqQJAQrKc9sukyi5kKKuKIW4YcR8TqlRv+T0mV6SX3wLrd11QRhYfFUAccy5YvhVzjRc6COBmzlLxBd4CNDs+2jIatg==";
	//
	// public static void main(String[] args) throws Exception {
	//
	// /** 用公钥解密数据 */
	// System.out.println(decryptByPublicKeyBase64(test_encryptByPri,
	// php_pub_der));
	//
	// /** 用私钥解密数据 */
	// System.out.println(decryptByPrivateKeyBase64(test_encryptByPub,
	// php_pri_der));
	//
	// /** 验签 */
	// String text =
	// "<root><returnMsg>履约成功</returnMsg><validTime>2014-12-15</validTime><tradeID>test_123!%</tradeID></root>";
	// System.out.println(verifySign(text.getBytes(),
	// Base64.decodeByte(test_sign), php_pub_der));
	//
	// /** 签名 */
	// System.out.println(sign(text, cifpay_pri));
	//
	// }
	//
	// /**
	// * JAVA-PHP
	// * 公钥加密测试
	// * */
	// private static String encrypt(String data, byte[] keyBytes) throws
	// Exception {
	//
	// // 取得公钥
	// X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
	// KeyFactory keyFactory = KeyFactory.getInstance(RSA);
	// Key key = keyFactory.generatePublic(x509KeySpec);
	//
	// // 对数据加密
	// Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
	// cipher.init(Cipher.ENCRYPT_MODE, key);
	//
	// byte[] result = encryptWithBlock(data.getBytes(),cipher);
	//
	// String result64 = Base64.encodeByte(result);
	//
	// return result64;
	// }
	//
	// /**
	// * JAVA-PHP
	// * 私钥解密测试
	// * */
	// private static String decrypt(String data, byte[] keyBytes) throws
	// Exception {
	//
	// byte[] dataBytes = Base64.decodeByte(data);
	//
	// // 取得私钥
	// PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
	// KeyFactory keyFactory = KeyFactory.getInstance(RSA);
	// Key key = keyFactory.generatePrivate(pkcs8KeySpec);
	//
	// // 对数据解密
	// Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
	// cipher.init(Cipher.DECRYPT_MODE, key);
	//
	// return new String(decryptWithBlock(dataBytes, cipher));
	// }

}
