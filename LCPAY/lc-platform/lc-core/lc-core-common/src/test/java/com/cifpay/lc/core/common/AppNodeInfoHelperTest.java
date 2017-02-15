package com.cifpay.lc.core.common;

import org.junit.Test;

import com.cifpay.lc.util.AppNodeInfoHelper;

/**
 * 
 *
 */
public class AppNodeInfoHelperTest {

	@Test
	public void testGetAppPath() {
		Class<?> clz = getClass();
		String file = clz.getProtectionDomain().getCodeSource().getLocation().getPath();
		System.out.println(file);
		
		String path = AppNodeInfoHelper.getAppBinaryPath();
		System.out.println(path);
	}

}
