package com.cifpay.lc.util.security;

import java.io.UnsupportedEncodingException;
import java.security.Provider;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES加解密工具类
 * 
 * 
 *
 */
public abstract class AESUtils {

	private static boolean triedLoadBouncycastleProvider = false;
	private static String providerName = "SunJCE";

	// 优先使用bouncycastle的Provider，其次是Sun (Oracle)的Provider
	static {
		if (!triedLoadBouncycastleProvider) {
			synchronized (AESUtils.class) {
				if (!triedLoadBouncycastleProvider) {
					try {
						ClassLoader clzLoader = Thread.currentThread().getContextClassLoader();
						if (null == clzLoader) {
							clzLoader = AESUtils.class.getClassLoader();
						}
						Class<?> bcProviderClz = clzLoader
								.loadClass("org.bouncycastle.jce.provider.BouncyCastleProvider");
						Security.addProvider((Provider) bcProviderClz.newInstance());
						providerName = "BC";
						System.out.println("Found and loaded Bouncycastle provider.");
					} catch (ClassNotFoundException e) {
						System.out.println("No Bouncycastle provider is found, use the provider by JDK");
					} catch (ClassCastException e) {
						System.out.println(
								"Cannot cast org.bouncycastle.jce.provider.BouncyCastleProvider to java.security.Provider.");
					} catch (InstantiationException e) {
						System.out.println("Cannot instantiate org.bouncycastle.jce.provider.BouncyCastleProvider");
					} catch (IllegalAccessException e) {
						System.out.println("Cannot instantiate org.bouncycastle.jce.provider.BouncyCastleProvider");
					} finally {
						triedLoadBouncycastleProvider = true;
					}
				}
			}
		}
	}

	public static String encryptStringToBase64(String message, String aesKey) throws IllegalArgumentException {
		try {
			return encryptBytesToBase64(message.getBytes("UTF-8"), aesKey);
		} catch (UnsupportedEncodingException e) {
			// should never happen
			throw new IllegalArgumentException("UTF-8 is not supported by JVM");
		}
	}

	public static String decryptStringFromBase64(String encrypedBase64, String aesKey) throws IllegalArgumentException {
		byte[] resultBytes = decryptBytesFromBase64(encrypedBase64, aesKey);
		try {
			return new String(resultBytes, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// should never happen
			throw new IllegalArgumentException("UTF-8 is not supported by JVM");
		}
	}

	public static String encryptBytesToBase64(byte[] bytes, String aesKey) throws IllegalArgumentException {
		if (null == aesKey) {
			throw new IllegalArgumentException("aesKey必须不为null");
		} else if (16 != aesKey.getBytes().length) {
			throw new IllegalArgumentException("aesKey的字节长度必须是16");
		}

		try {
			SecretKeySpec key = new SecretKeySpec(aesKey.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding", providerName);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] resultBytes = cipher.doFinal(bytes);
			return Base64.encodeByte(resultBytes);
		} catch (Exception e) {
			throw new IllegalArgumentException("AES加密失败，输入的数据不符合格式要求", e);
		}
	}

	public static byte[] decryptBytesFromBase64(String encrypedBase64, String aesKey) throws IllegalArgumentException {
		if (null == aesKey) {
			throw new IllegalArgumentException("aesKey必须不为null");
		} else if (16 != aesKey.getBytes().length) {
			throw new IllegalArgumentException("aesKey的字节长度必须是16");
		}

		try {
			byte[] encryptedBytes = Base64.decodeByte(encrypedBase64);
			SecretKeySpec key = new SecretKeySpec(aesKey.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding", providerName);
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] resultBytes = cipher.doFinal(encryptedBytes);
			return resultBytes;
		} catch (Exception e) {
			throw new IllegalArgumentException("AES解密失败，输入的数据不符合格式要求", e);
		}
	}

}
