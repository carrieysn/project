/**
 * File: UserLoginControllerTest.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年12月2日 下午7:58:27
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

import com.cifpay.insurance.param.user.UserAccountLoginInfo;
import com.cifpay.insurance.service.InsInsuranceCertService;
import com.cifpay.insurance.util.JacksonUtil;
import com.cifpay.starframework.security.HashUtil;

/**
 * 
 * @author 张均锋
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:applicationContext.xml","classpath:applicationContextMvc-servlet.xml"})
public class UserLoginControllerTest {
	@Autowired
	private InsInsuranceCertService insInsuranceCertService;
	
	private String vendorId = "11";
	
	private String policyNo = "V525Y57";
	
	@Autowired  
    private WebApplicationContext wac;  
    private MockMvc mockMvc;
  
    @Before  
    public void setUp() {  
       mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
    
   // @Test
	public void authLoginTest() throws Exception{
    	UserAccountLoginInfo ci = new UserAccountLoginInfo();
		ci.setUserAccount("test");
		ci.setPassword(HashUtil.md5("111111"));
		
		mockMvc.perform(MockMvcRequestBuilders.post("/inner/insurance/user/account/login")
				                                                 .param("timestamp", new Date().toLocaleString())
				                                                 .param("data", JacksonUtil.toJson(ci))
				                          ) //执行请求  
				          .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("0"))
				          //.andExpect(MockMvcResultMatchers.jsonPath("$.data.goodsName").value("Iphone6s"))
		                  .andDo(MockMvcResultHandlers.print());
	}
    
	@Test
    public void requestPushMessageTest() throws Exception{
    	UserAccountLoginInfo ci = new UserAccountLoginInfo();
		ci.setUserAccount("zjf");
		ci.setPassword("1");
		
		mockMvc.perform(MockMvcRequestBuilders.post("/inner/insurance/user/message/push/request")
				                                                 .param("vendorId", vendorId)
				                                                 .param("timestamp", new Date().toLocaleString())
				                                                 .param("data", JacksonUtil.toJson(ci))
				                          ) //执行请求  
				          .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("0"))
				          //.andExpect(MockMvcResultMatchers.jsonPath("$.data.goodsName").value("Iphone6s"))
		                  .andDo(MockMvcResultHandlers.print());
	}
}
