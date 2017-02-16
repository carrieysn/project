/**
 * File: InsPolicyControllerTest.java
 *
 * Copyright：Copyright (c) 2016
 * Company：深圳市银信网银科技有限公司
 * Created on：2016年1月19日 下午3:49:10
 */
package com.cifpay.insurance;

import java.util.Date;

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

import com.cifpay.insurance.param.policy.GetPolicyChangeListInfo;
import com.cifpay.insurance.util.JacksonUtil;

/**
 * 
 * @author 张均锋
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:applicationContext.xml","classpath:applicationContextMvc-servlet.xml"})
public class InsPolicyControllerTest {

	private String vendorId = "115";

	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void gearingAdjustTest() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders
						.post("/inner/insurance/policy/gearingAdjust")
						.param("vendorId", vendorId)
						.param("timestamp", new Date().toLocaleString())
						.param("insUserId", "1")
						.param("policyId", "2")
						.param("gearingRuleId", "2")
						
				) // 执行请求
				.andExpect(MockMvcResultMatchers.jsonPath("$.code").value("0"))
				// .andExpect(MockMvcResultMatchers.jsonPath("$.data.goodsName").value("Iphone6s"))
				.andDo(MockMvcResultHandlers.print());
	}
}
