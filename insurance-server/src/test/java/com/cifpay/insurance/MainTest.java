/**
 * File: MainTest.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年12月12日 上午10:36:37
 */
package com.cifpay.insurance;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;

import com.cifpay.insurance.util.DateUtil;
import com.cifpay.lc.security.GenerateKeyTools;
import com.cifpay.lc.security.rsa.key.KeyPair;

/**
 * 
 * @author 张均锋
 *
 */
public class MainTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		GenerateKeyTools rsa = new GenerateKeyTools();
		KeyPair keyPair = rsa.generateRSAKeys(2048);
		String priKey = keyPair.getPrivateKey();
		String pubKey = keyPair.getPublicKey();
		Calendar c = Calendar.getInstance();
		c.set(Calendar.MONTH, Calendar.JANUARY);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		String y = "2015-01-01 12:00:00";
		Date d = DateUtil.parseDate(y, "yyyy-MM-dd 23:59:59");
		
		System.out.println(String.format("参数，vendorid:%s,userId:%s", "990-0399898","3223"));
		
		System.out.println(String.format("失效处理失败！vendorId：%s，certNo：%s", "3242343", "werw"));
		
		
	    BigDecimal num1 = new BigDecimal(8.6);
	    BigDecimal result = num1.divide(new BigDecimal(3.5),2, BigDecimal.ROUND_HALF_EVEN);
	    System.out.println(String.format("the result is :%s", result));
		
	    System.out.println(String.format("发送消息到推送服务器[%s]失败：%s", "127.0.0.1", "msg"));
		
		
		 
	}

}
