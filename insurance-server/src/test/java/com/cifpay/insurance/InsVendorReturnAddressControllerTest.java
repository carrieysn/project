/**
 * File: InsVendorReturnAddressControllerTest.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年12月5日 下午6:50:29
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

import com.cifpay.insurance.param.vendor.AddVendorReturnAddressInfo;
import com.cifpay.insurance.param.vendor.VendorReturnAddressId;
import com.cifpay.insurance.util.JacksonUtil;


/**
 * 
 * @author 张均锋
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:applicationContext.xml","classpath:applicationContextMvc-servlet.xml"})
public class InsVendorReturnAddressControllerTest {

	private String vendorId = "115";
	
	@Autowired  
    private WebApplicationContext wac;  
    private MockMvc mockMvc;
  
    @Before  
    public void setUp() {  
       mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
    
   //@Test
	public void addVendorReturnAddressTest() throws Exception{
    	AddVendorReturnAddressInfo ci = new AddVendorReturnAddressInfo();
    	ci.setHolderName("华为22");
    	ci.setAddress("上海华为路22xxx");
    	ci.setContacts("李四222ddd");
    	ci.setMobilePhone("13988897922");
		
		mockMvc.perform(MockMvcRequestBuilders.post("/inner/insurance/vendor/return/address/add")
				                                                 .param("vendorId", vendorId)
				                                                 .param("timestamp", new Date().toLocaleString())
				                                                 .param("data", JacksonUtil.toJson(ci))
				                          ) //执行请求  
				          .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("0"))
				          //.andExpect(MockMvcResultMatchers.jsonPath("$.data.goodsName").value("Iphone6s"))
		                  .andDo(MockMvcResultHandlers.print());
	}
    
    //@Test
	public void deleteVendorReturnAddressTest() throws Exception{
		VendorReturnAddressId ci = new VendorReturnAddressId();
    	ci.setVendorReturnAddressId("8");
		
		mockMvc.perform(MockMvcRequestBuilders.post("/insurance/vendor/return/address/delete")
				                                                 .param("vendorId", vendorId)
				                                                 .param("timestamp", new Date().toLocaleString())
				                                                 .param("data", JacksonUtil.toJson(ci))
				                          ) //执行请求  
				          .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("0"))
				          //.andExpect(MockMvcResultMatchers.jsonPath("$.data.goodsName").value("Iphone6s"))
		                  .andDo(MockMvcResultHandlers.print());
	}
    
   // @Test
	public void setVendorReturnAddressDefaultTest() throws Exception{
    	VendorReturnAddressId ci = new VendorReturnAddressId();
    	ci.setVendorReturnAddressId("7");
		
		mockMvc.perform(MockMvcRequestBuilders.post("/inner/insurance/vendor/return/address/default/set")
				                                                 .param("vendorId", vendorId)
				                                                 .param("timestamp", new Date().toLocaleString())
				                                                 .param("data", JacksonUtil.toJson(ci))
				                          ) //执行请求  
				          .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("0"))
				          //.andExpect(MockMvcResultMatchers.jsonPath("$.data.goodsName").value("Iphone6s"))
		                  .andDo(MockMvcResultHandlers.print());
	}
    
    @Test
	public void getVendorReturnAddressListTest() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.post("/inner/insurance/vendor/return/address/list/get")
				                                                 .param("vendorId", vendorId)
				                                                 .param("timestamp", new Date().toLocaleString())
				                                                 //.param("data", JacksonUtil.toJson(ci))
				                          ) //执行请求  
				          .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("0"))
				          //.andExpect(MockMvcResultMatchers.jsonPath("$.data.goodsName").value("Iphone6s"))
		                  .andDo(MockMvcResultHandlers.print());
	}
	
}
