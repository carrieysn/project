package com.cifpay.lc.bankadapter;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 标准银信证核心业务应用的JUnit基本配置。
 * 
 * 
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:com/cifpay/lc/bankadapter/config/spring-root-context.xml" }, initializers = {
				TestLogbackInitializer.class })
public abstract class BusinessJUnitTestBase {
	
	@BeforeClass
	public static void beforeClass(){
		System.setProperty("spring.profiles.default", "JUNIT");
		System.setProperty("spring.profiles.active", "JUNIT");
	}

}
