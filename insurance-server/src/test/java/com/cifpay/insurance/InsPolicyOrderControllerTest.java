/**
 * File: InsPolicyOrderControllerTest.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年12月9日 上午11:12:21
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

import com.cifpay.insurance.param.policy.CreateChargeFeePolicyOrderInfo;
import com.cifpay.insurance.param.policy.CreatePolicyOrderInfo;
import com.cifpay.insurance.param.policy.GetPolicyOrderInfo;
import com.cifpay.insurance.param.policy.GetPolicyOrderListInfo;
import com.cifpay.insurance.param.policy.NoticeFrontPolicyOrderInfo;
import com.cifpay.insurance.param.policy.PolicyHolderInfo;
import com.cifpay.insurance.util.JacksonUtil;
import com.cifpay.lc.security.PubKeyTypeEnum;
import com.cifpay.lc.security.SecurityUtils;
import com.cifpay.lc.security.SerializerBean;
import com.cifpay.lc.security.util.ApacheBase64;
import com.cifpay.starframework.cache.ConfigPropertiesCache;

/**
 * 
 * @author 张均锋
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:applicationContext.xml", "classpath:applicationContextMvc-servlet.xml" })
public class InsPolicyOrderControllerTest {
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
	
	/** 投保订单 **/
	//@Test
	public void createPolicyOrderTest() throws Exception{
		CreatePolicyOrderInfo ci = new CreatePolicyOrderInfo();
		ci.setInsurancePeriod(3);
		ci.setInsuredid("在指定的销售网站中销售的符合交还险保险条款的商品");
		ci.setProduct("交易还原履约保证保险");
		ci.setPremium(15000000l);
		ci.setInsuredName("商品买家");
		PolicyHolderInfo holder = new PolicyHolderInfo();;
		ci.setPolicyHolderInfo(holder);
		holder.setContacts("ddd");
		holder.setEmail("xxx@gmail.com");
		holder.setHolderName("中兴技术有限公司");
		holder.setHolderType(1);
		holder.setIdNo("2839423492392239");
		holder.setIdType(1);
		holder.setPhone("13988888888");
		
		mockMvc.perform(MockMvcRequestBuilders.post("/insurance/policy/order/create")
				                                                 .param("vendorId", vendorId)
				                                                 .param("timestamp", new Date().toLocaleString())
				                                                 .param("data", JacksonUtil.toJson(ci))
				                          ) //执行请求  
				          .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("0"))
		                  .andDo(MockMvcResultHandlers.print());
	}
	
	/** 充值订单 **/
	//@Test
	public void createChargeFeePolicyOrderTest() throws Exception{
		CreateChargeFeePolicyOrderInfo ci = new CreateChargeFeePolicyOrderInfo();
		ci.setAmount(100000l);
		ci.setPolicyId(1l);;
		mockMvc.perform(MockMvcRequestBuilders.post("/insurance/policy/order/fee/charge/create")
				                                                 .param("vendorId", vendorId)
				                                                 .param("timestamp", new Date().toLocaleString())
				                                                 .param("data", JacksonUtil.toJson(ci))
				                          ) //执行请求  
				          .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("0"))
		                  .andDo(MockMvcResultHandlers.print());
	}
	
	/** 获取订单记录 **/
	//@Test
	public void getChargeFeePolicyOrderListTest() throws Exception{
		GetPolicyOrderListInfo pcl = new GetPolicyOrderListInfo();
		pcl.setPageNo(1);
		pcl.setPageSize(50);
		pcl.setBeginDate("2015-12-09");
		pcl.setEndDate("2015-12-17");
		pcl.setPolicyId(1l);
		mockMvc.perform(MockMvcRequestBuilders.post("/insurance/policy/order/list/get")
				                                                 .param("vendorId", vendorId)
				                                                 .param("timestamp", new Date().toLocaleString())
				                                                 .param("data", JacksonUtil.toJson(pcl))
				                          ) //执行请求  
				          .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("0"))
		                  .andDo(MockMvcResultHandlers.print());
	}
	
	/** 后台通知 **/
	@Test
	public void noticePolicyOrderTest() throws Exception{
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("orderId", "2015121418000003");
		m.put("lcId", "2015121000001");
		m.put("lcNo", "B2015121000001");
		m.put("lcState", "CREDIT_PAYED");
		mockMvc.perform(MockMvcRequestBuilders.post("/insurance/policy/order/notice")
				                                                 .param("vendorId", vendorId)
				                                                 .param("timestamp", new Date().toLocaleString())
				                                                 .param("data", JacksonUtil.toJson(m))
				                          ) //执行请求  
				          .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("0"))
		                  .andDo(MockMvcResultHandlers.print());
	}
	
	/** 前端通知 **/
	//@Test
	public void noticefrontPolicyOrderTest() throws Exception{
		NoticeFrontPolicyOrderInfo m = new NoticeFrontPolicyOrderInfo();
		m.setBillNo("2015121110000005");
		m.setLcId("2015121000001");
		m.setLcNo("B2015121000001");
		m.setLcState("CREDIT_RECEIVED");
		m.setTradeDate(new Date());
		mockMvc.perform(MockMvcRequestBuilders.post("/insurance/policy/order/front/notice")
				                                                 .param("vendorId", vendorId)
				                                                 .param("timestamp", new Date().toLocaleString())
				                                                 .param("data", JacksonUtil.toJson(m))
				                          ) //执行请求  
				          .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("0"))
		                  .andDo(MockMvcResultHandlers.print());
	}
	

	
	/**获取订单详细信息 **/
	//@Test
	public void getPolicyOrderInfoTest() throws Exception{
		GetPolicyOrderInfo m = new GetPolicyOrderInfo();
		m.setBillNo("2015121110000005");
		mockMvc.perform(MockMvcRequestBuilders.post("/insurance/policy/order/get")
				                                                 .param("vendorId", vendorId)
				                                                 .param("timestamp", new Date().toLocaleString())
				                                                 .param("data", JacksonUtil.toJson(m))
				                          ) //执行请求  
				          .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("0"))
		                  .andDo(MockMvcResultHandlers.print());
	}
}
