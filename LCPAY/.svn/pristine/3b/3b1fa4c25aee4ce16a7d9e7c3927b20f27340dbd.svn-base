package com.cifpay.lc.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

/**
 * 字符串分组工具类
 * 
 * 
 *
 */
public abstract class StringUtil {

	public static final String LINE_SEPARATOR;
	
	static {
		String newLine;
		try (Formatter f = new Formatter()) {
			newLine = f.format("%n").toString();
		} catch (Exception e) {
			newLine = "\n";
		}
		LINE_SEPARATOR = newLine;
	}
	
	/**
	 * 将字符按分成多个块，每一块的字节长度不超时指定的值，并保证分隔后的单个中文字符不被破坏。
	 * 
	 * @param srcString
	 * @param srcStringEncoding
	 * @param chunkBytesLength
	 * @return
	 */
	public static List<String> splitToChunks(String srcString, String srcStringEncoding, int chunkBytesLength) {
		List<String> chunkList = new ArrayList<String>();
		try {
			final int srcTotalLen = srcString.length();
			int startIndex = 0;
			int endIndex = chunkBytesLength > srcTotalLen ? srcTotalLen : chunkBytesLength;
			while (startIndex < srcTotalLen) {
				String subString = srcString.substring(startIndex, endIndex);
				while (subString.getBytes(srcStringEncoding).length > chunkBytesLength) {
					--endIndex;
					subString = srcString.substring(startIndex, endIndex);
				}
				if (endIndex == startIndex) {
					throw new IllegalArgumentException("chunkBytesLength值太小，该值必须大于一个可见字符的字节长度");
				}
				chunkList.add(subString);
				startIndex = endIndex;
				endIndex = startIndex + chunkBytesLength;
				if (endIndex > srcTotalLen) {
					endIndex = srcTotalLen;
				}
			}
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException("The encoding " + srcStringEncoding + " is not supported");
		}

		return chunkList;
	}

	/**
	 * 判断是否为数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isDigit(String str) {
		if (str == null) {
			return false;
		}
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
	}
}
