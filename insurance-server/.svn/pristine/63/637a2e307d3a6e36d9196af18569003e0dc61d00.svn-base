/**
 * File: InsBankCardControllerTest.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年12月25日 下午5:55:31
 */
package com.cifpay.insurance;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cifpay.insurance.param.vendor.LookupBankCardInfo;
import com.cifpay.insurance.util.JacksonUtil;

/**
 * 
 * @author 张均锋
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:applicationContext.xml", "classpath:applicationContextMvc-servlet.xml" })
public class InsBankCardControllerTest {

	private String vendorId = "115";

	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@After
	public void after() throws InterruptedException {
		// TimeUnit.MINUTES.sleep(5);
	}

	/**银行卡搜索 **/
	@Test
	public void lookupBankcardTest() throws Exception {
		LookupBankCardInfo ci = new LookupBankCardInfo();
		ci.setInputCardNo("4096707865");
		mockMvc.perform(MockMvcRequestBuilders
						.post("/inner/insurance/bank/card/lookup")
						.param("vendorId", vendorId)
						.param("timestamp", new Date().toLocaleString())
						.param("data", JacksonUtil.toJson(ci))) // 执行请求
				.andExpect(MockMvcResultMatchers.jsonPath("$.code").value("0"))
				.andDo(MockMvcResultHandlers.print());
	}

}
