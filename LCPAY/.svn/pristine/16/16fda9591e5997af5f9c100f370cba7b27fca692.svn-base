package com.cifpay.lc.util.security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5计算工具，不含任何业务有关的处理，只负责对String，byte[]
 * 进行MD5计算。跟业务处理有关的MD5相关处理，应放置到具体的业务级工具类中进行。
 * 
 * 
 *
 */
public abstract class MD5Utils {

	/**
	 * 计算string的MD5
	 * 
	 * @param source
	 * @return
	 */
	public static String calcMD5(String source) throws IllegalArgumentException {
		return calcMD5(source, "UTF-8");
	}

	public static String calcMD5(String source, String encoding) throws IllegalArgumentException {
		try {
			byte[] bt = source.getBytes(encoding);
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(bt);
			return bytesToHexString(md.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("MD5 is not supported.");
		} catch (UnsupportedEncodingException e1) {
			// should never happen
			throw new IllegalArgumentException(encoding + " is not supported.");
		} catch (Exception e1) {
			throw new IllegalArgumentException("Calculate MD5 failed.");
		}

	}

	/**
	 * 计算byte字节数组的MD5
	 * 
	 * @param bytes
	 * @return
	 */
	public static String calcMD5(byte[] bytes) throws RuntimeException {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(bytes);
			return bytesToHexString(md.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("MD5 is not supported.");
		} catch (Exception e1) {
			throw new IllegalArgumentException("Calculate MD5 failed.");
		}
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
}
