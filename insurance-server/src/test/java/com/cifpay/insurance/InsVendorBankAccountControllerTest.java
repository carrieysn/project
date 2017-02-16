/**
 * File: InsVendorBankAccountControllerTest.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年12月5日 下午5:06:19
 */
package com.cifpay.insurance;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cifpay.insurance.param.vendor.BindVendorBankAccountInfo;
import com.cifpay.insurance.param.vendor.VendorBankAccount;
import com.cifpay.insurance.util.JacksonUtil;
import com.cifpay.lc.security.PubKeyTypeEnum;
import com.cifpay.lc.security.SecurityUtils;
import com.cifpay.lc.security.SerializerBean;
import com.cifpay.lc.security.util.ApacheBase64;
import com.cifpay.security.tools.EncryptionTools;
import com.cifpay.starframework.cache.ConfigPropertiesCache;

/**
 * 
 * @author 张均锋
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:applicationContext.xml","classpath:applicationContextMvc-servlet.xml"})
public class InsVendorBankAccountControllerTest {
	private String vendorId = "115";
	
	@Autowired  
    private WebApplicationContext wac;  
    private MockMvc mockMvc;

    private ConfigPropertiesCache cfg = ConfigPropertiesCache.getInstance();
	private Map<String, Object> params = new HashMap<String, Object>();
	
    @Before  
    public void setUp() {  
       mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	   params.put("vendorId", vendorId);
	   params.put("timestamp", new Date());
    }
    
    @After
    public void after() throws InterruptedException {
    	//TimeUnit.MINUTES.sleep(5);
    }
    
    private MockHttpServletRequestBuilder getB(String url, Map<String, Object> map) throws Exception{
    	String data = ApacheBase64.encodeStr(JacksonUtil.toJson(map));
    	SerializerBean encodeBean =  SecurityUtils.encode(data, cfg.get("server.publicKey"), cfg.get("client.privateKey"), "11500", PubKeyTypeEnum.MRCH.getKey()) ;
    	return MockMvcRequestBuilders.post(url)
         .param("data", encodeBean.getData())
        .param("key", encodeBean.getKey())
        .param("mac", encodeBean.getMac());
    }
    
    @Test
	public void bindVendorBankAccountTest() throws Exception{
    	BindVendorBankAccountInfo ci = new BindVendorBankAccountInfo();
    	ci.setBankCode("ICBC");
    	ci.setBankName("建设银行");
    	ci.setAccountName("李天一");
    	ci.setBankAccount("87563453435353334");
    	ci.setReserveMobilePhone("13888888888");
    	params.put("data", ci);
    	
    	String url = "/insurance/vendor/bank/account/bind";
		mockMvc.perform(getB(url, params)
				                          ) //执行请求  
				          .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("0"))
				          //.andExpect(MockMvcResultMatchers.jsonPath("$.data.goodsName").value("Iphone6s"))
		                  .andDo(MockMvcResultHandlers.print());
	}
    
    //@Test
	public void unbindVendorBankAccountTest() throws Exception{
    	VendorBankAccount ci = new VendorBankAccount();
    	ci.setBankAccount("875634534353534534");
		
		mockMvc.perform(MockMvcRequestBuilders.post("/insurance/vendor/bank/account/unbind")
				                                                 .param("vendorId", vendorId)
				                                                 .param("timestamp", new Date().toLocaleString())
				                                                 .param("data", JacksonUtil.toJson(ci))
				                          ) //执行请求  
				          .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("0"))
				          //.andExpect(MockMvcResultMatchers.jsonPath("$.data.goodsName").value("Iphone6s"))
		                  .andDo(MockMvcResultHandlers.print());
	}
    
    //@Test
	public void setVendorBankAccountDefaultTest() throws Exception{
    	VendorBankAccount ci = new VendorBankAccount();
    	ci.setBankAccount("875634534353534534");
		
		mockMvc.perform(MockMvcRequestBuilders.post("/insurance/vendor/bank/account/default/set")
				                                                 .param("vendorId", vendorId)
				                                                 .param("timestamp", new Date().toLocaleString())
				                                                 .param("data", JacksonUtil.toJson(ci))
				                          ) //执行请求  
				          .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("0"))
				          //.andExpect(MockMvcResultMatchers.jsonPath("$.data.goodsName").value("Iphone6s"))
		                  .andDo(MockMvcResultHandlers.print());
	}
    
   // @Test
	public void getVendorBankAccountListTest() throws Exception{
    	String url = "/insurance/vendor/bank/account/list/get";
		mockMvc.perform(getB(url, params)) //执行请求  
				       .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("0"))
		               .andDo(MockMvcResultHandlers.print());
	}
}
