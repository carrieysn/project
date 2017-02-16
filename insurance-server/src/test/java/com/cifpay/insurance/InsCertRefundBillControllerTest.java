/**
 * File: InsCertRefundBillControllerTest.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年12月18日 下午6:20:48
 */
package com.cifpay.insurance;

import java.util.Date;
import java.util.concurrent.TimeUnit;

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

import com.cifpay.insurance.param.cert.InsuranceCertNo;
import com.cifpay.insurance.param.cert.InsuranceCertReturnInfo;
import com.cifpay.insurance.param.refund.GetRefundBillInfo;
import com.cifpay.insurance.util.JacksonUtil;

/**
 * 
 * @author 张均锋
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:applicationContext.xml","classpath:applicationContextMvc-servlet.xml"})
public class InsCertRefundBillControllerTest {
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
    	//TimeUnit.MINUTES.sleep(5);
    }
    
	/** 申请退货 **/
	//@Test
	public void applyReturnInsuranceCertTest() throws Exception{
		InsuranceCertReturnInfo ci = new InsuranceCertReturnInfo();
		ci.setInsuranceCertNo("201512140000000001/AA0018");
		ci.setLogisticsBillNo("lg0000001");
		ci.setLogisticsCompany("顺风快递");
		ci.setPayeeAccName("小李");
		ci.setPayeeBankAccount("6212264588886244599");
		ci.setPayeeBankCode("ICBC");
		ci.setPayeeMobilePhone("18844962233");
		
		mockMvc.perform(MockMvcRequestBuilders.post("/inner/insurance/certificate/refund/apply")
				                                                 .param("vendorId", vendorId)
				                                                 .param("timestamp", new Date().toLocaleString())
				                                                 .param("data", JacksonUtil.toJson(ci))
				                          ) //执行请求  
				          .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("0"))
				          //.andExpect(MockMvcResultMatchers.jsonPath("$.data.goodsName").value("Iphone6s"))
		                  .andDo(MockMvcResultHandlers.print());
	}
	
	/** 确认退款 **/
	//@Test
	public void confirmRefundTest() throws Exception{
		InsuranceCertNo ci = new InsuranceCertNo();
		ci.setInsuranceCertNo("201512140000000001/AA0009");
		mockMvc.perform(MockMvcRequestBuilders.post("/insurance/certificate/refund/confirm")
				                                                 .param("vendorId", vendorId)
				                                                 .param("timestamp", new Date().toLocaleString())
				                                                 .param("data", JacksonUtil.toJson(ci))
				                          ) //执行请求  
				          .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("0"))
				          //.andExpect(MockMvcResultMatchers.jsonPath("$.data.goodsName").value("Iphone6s"))
		                  .andDo(MockMvcResultHandlers.print());
	}
	
	/** 过期的未开证的退款单检测，由保险公司代开证。**/
	//@Test
	public void checkExpiredUnopenRefundBillTest() throws Exception {
		InsuranceCertNo ci = new InsuranceCertNo();
		mockMvc.perform(MockMvcRequestBuilders.post("/inner/insurance/certificate/refund/expired/unopen/check")
				                                                 .param("vendorId", vendorId)
				                                                 .param("timestamp", new Date().toLocaleString())
				                                                 .param("data", JacksonUtil.toJson(ci))
				                          ) //执行请求  
				          .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("0"))
				          //.andExpect(MockMvcResultMatchers.jsonPath("$.data.goodsName").value("Iphone6s"))
		                  .andDo(MockMvcResultHandlers.print());
		
	}
	

	
	/** 未过期的未开证的退款单检测，继续由商户开证。 **/
	//@Test
	public void checkUnexpiredUnopenRefundBillTest() throws Exception {
		InsuranceCertNo ci = new InsuranceCertNo();
		mockMvc.perform(MockMvcRequestBuilders.post("/inner/insurance/certificate/refund/unexpired/unopen/check")
				                                                 .param("vendorId", vendorId)
				                                                 .param("timestamp", new Date().toLocaleString())
				                                                 .param("data", JacksonUtil.toJson(ci))
				                          ) //执行请求  
				          .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("0"))
				          //.andExpect(MockMvcResultMatchers.jsonPath("$.data.goodsName").value("Iphone6s"))
		                  .andDo(MockMvcResultHandlers.print());
		
	}
	
	/** 获取退款单 **/
	//@Test
	public void getInsCertRefundBillTest() throws Exception {
		InsuranceCertNo ci = new InsuranceCertNo();
		ci.setInsuranceCertNo("201512140000000001/AA0009");
		mockMvc.perform(MockMvcRequestBuilders.post("/insurance/certificate/refund/bill/get")
				                                                 .param("vendorId", vendorId)
				                                                 .param("timestamp", new Date().toLocaleString())
				                                                 .param("data", JacksonUtil.toJson(ci))
				                          ) //执行请求  
				          .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("0"))
				          //.andExpect(MockMvcResultMatchers.jsonPath("$.data.goodsName").value("Iphone6s"))
		                  .andDo(MockMvcResultHandlers.print());
		
	}
	
	/** 查询退款单列表 **/
	//@Test
	public void getInsCertRefundBillListTest() throws Exception {
		GetRefundBillInfo ci = new GetRefundBillInfo();
		ci.setBillStatus("0");
		ci.setPageNo(1);
		ci.setPageSize(10);
		mockMvc.perform(MockMvcRequestBuilders.post("/inner/insurance/certificate/refund/bill/getList")
				                                                 .param("vendorId", vendorId)
				                                                 .param("timestamp", new Date().toLocaleString())
				                                                 .param("data", JacksonUtil.toJson(ci))
				                          ) //执行请求  
				          .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("0"))
				          //.andExpect(MockMvcResultMatchers.jsonPath("$.data.goodsName").value("Iphone6s"))
		                  .andDo(MockMvcResultHandlers.print());
		
	}
	/** 查询退款单详细信息 **/
	@Test
	public void getInsCertRefundBillInfoTest() throws Exception {
		InsuranceCertNo certno  = new InsuranceCertNo();
		certno.setInsuranceCertNo("201601030000000002/AA0005");
		mockMvc.perform(MockMvcRequestBuilders.post("/inner/insurance/certificate/refund/bill/getRefundInfo")
				                                                 .param("vendorId", vendorId)
				                                                 .param("timestamp", new Date().toLocaleString())
				                                                 .param("data", JacksonUtil.toJson(certno))
				                          ) //执行请求  
				          .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("0"))
				          //.andExpect(MockMvcResultMatchers.jsonPath("$.data.goodsName").value("Iphone6s"))
		                  .andDo(MockMvcResultHandlers.print());
		
	}
}
