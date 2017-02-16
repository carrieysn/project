/**
 * File: InsInsuranceCertServiceTest.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年11月24日 上午10:51:51
 */
package com.cifpay.insurance;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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

import com.cifpay.insurance.param.cert.CreateInsuranceCertInfo;
import com.cifpay.insurance.param.cert.GetInsuranceCertListInfo;
import com.cifpay.insurance.param.cert.InsuranceCertNo;
import com.cifpay.insurance.param.cert.InsuranceCertReportInfo;
import com.cifpay.insurance.param.cert.OrderInfo;
import com.cifpay.insurance.param.cert.OrderItemsInfo;
import com.cifpay.insurance.util.DateUtil;
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
@ContextConfiguration(locations={"classpath:applicationContext.xml","classpath:applicationContextMvc-servlet.xml"})
public class InsInsuranceCertControllerTest {

	private String vendorId = "11";
	
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
	
	/** 创建保险证 **/
	@Test
	public void createInsInsuranceCertControllerTest() throws Exception{
		CreateInsuranceCertInfo ci = new CreateInsuranceCertInfo();
		ci.setNoticeUrl("xxx");
		OrderInfo oi = new OrderInfo();//订单
		ci.setOrderInfo(oi);
		oi.setOrderNo(DateUtil.formatDate(new Date(), "yyyyMMddHHmmss"));
		oi.setPayMode(1); 
		oi.setOrderDate("2015-12-11");
		List<OrderItemsInfo> oiis = new ArrayList<OrderItemsInfo>();
		oi.setOrderItemsInfo(oiis);
		OrderItemsInfo oii = new OrderItemsInfo();
		oii.setGoodsName("Iphone6s1");
		oii.setGoodsNo("XD--dsf1");
		oii.setPrice(2521000l);
		oii.setQuantity(1);
		oii.setTotalPrice(2521000l);
		oiis.add(oii);
		params.put("data", ci);
		String url = "/inner/insurance/certificate/create";
		/*mockMvc.perform(getB(url, params)) //执行请求  
				          .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("0"))
		                  .andDo(MockMvcResultHandlers.print());*/
		mockMvc.perform(MockMvcRequestBuilders.post("/inner/insurance/certificate/create")
                .param("vendorId", vendorId)
                .param("timestamp", new Date().toLocaleString())
                .param("data", JacksonUtil.toJson(ci))
				) //执行请求  
				.andExpect(MockMvcResultMatchers.jsonPath("$.code").value("0"))
				.andDo(MockMvcResultHandlers.print());
	}
	
	/** 批量创建保险证 **/
	//@Test
	public void createInsInsuranceCertBatchControllerTest() throws Exception{
		/*CreateInsuranceCertBatchInfo ci = new CreateInsuranceCertBatchInfo();
		ci.setNoticeUrl("xxx");
		ci.setPolicyId(1l);
		List<OrderInfo> orders = new ArrayList<OrderInfo>();
		ci.setOrderInfos(orders);
		OrderInfo oi = new OrderInfo();//订单1
		oi.setOrderNo("1128000001");
		orders.add(oi);
		oi.setGoodsName("Iphone6s");
		oi.setGoodsNo("XD--dsf");
		oi.setPrice(600000l);
		oi.setQuantity(1);
		oi.setTotalPrice(600000l);
		BuyerInfo bi = new BuyerInfo();//买家
		oi.setBuyerInfo(bi);
		bi.setBuyerName("小李");
		bi.setMobilePhone("1388888888");
		bi.setPaymentId("sidfwl222222222");
		bi.setPayMode(1);
		
		OrderInfo oi2 = new OrderInfo();//订单2
		oi2.setOrderNo("1128000002");
		orders.add(oi2);
		oi2.setGoodsName("Iphone5s");
		oi2.setGoodsNo("XD--2333");
		oi2.setPrice(500000l);
		oi2.setQuantity(1);
		oi2.setTotalPrice(500000l);
		oi2.setBuyerInfo(bi);
		
		OrderInfo oi3 = new OrderInfo();//订单3
		oi3.setOrderNo("1128000003");
		orders.add(oi3);
		oi3.setGoodsName("Iphone4s");
		oi3.setGoodsNo("XD--422222s");
		oi3.setPrice(400000l);
		oi3.setQuantity(1);
		oi3.setTotalPrice(400000l);
		oi3.setBuyerInfo(bi);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/insurance/certificate/batch/create")
				                                                 .param("vendorId", vendorId)
				                                                 .param("timestamp", new Date().toLocaleString())
				                                                 .param("data", JacksonUtil.toJson(ci))
				                          ) //执行请求  
				          .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("0"))
		                  .andDo(MockMvcResultHandlers.print());*/
	}
	
	/** 签收 **/
	//@Test
	public void signLogisticsTest() throws Exception{
		InsuranceCertNo  ci = new InsuranceCertNo();
		ci.setInsuranceCertNo("201512140000000001/AA0018");
		
		mockMvc.perform(MockMvcRequestBuilders.post("/inner/insurance/certificate/logistics/sign")
				                                                 .param("vendorId", vendorId)
				                                                 .param("timestamp", new Date().toLocaleString())
				                                                 .param("data", JacksonUtil.toJson(ci))
				                          ) //执行请求  
				          .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("0"))
				          //.andExpect(MockMvcResultMatchers.jsonPath("$.data.goodsName").value("Iphone6s"))
		                  .andDo(MockMvcResultHandlers.print());
	}
	
	/** 签收检测 **/
	//@Test
	public void checkSignedInsuranceCertTest() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.post("/inner/insurance/certificate/signed/check")
				                                                 .param("vendorId", vendorId)
				                                                 .param("timestamp", new Date().toLocaleString())
				                                                 //.param("data", JacksonUtil.toJson(ci))
				                          ) //执行请求  
				          .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("0"))
				          //.andExpect(MockMvcResultMatchers.jsonPath("$.data.goodsName").value("Iphone6s"))
		                  .andDo(MockMvcResultHandlers.print());
	}
	
	/** 失效检测 **/
	//@Test
	public void checkExpiredInsuranceCertTest() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.post("/inner/insurance/certificate/expired/check")
				                                                 .param("vendorId", vendorId)
				                                                 .param("timestamp", new Date().toLocaleString())
				                                                 //.param("data", JacksonUtil.toJson(ci))
				                          ) //执行请求  
				          .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("0"))
				          //.andExpect(MockMvcResultMatchers.jsonPath("$.data.goodsName").value("Iphone6s"))
		                  .andDo(MockMvcResultHandlers.print());
	}
	
	/** 获取统计报表 **/
	//@Test
	public void getCertificateReportTest() throws Exception{
		InsuranceCertReportInfo ci = new InsuranceCertReportInfo();
		ci.setPolicyNo("test");
		
		mockMvc.perform(MockMvcRequestBuilders.post("/inner/insurance/certificate/report/get")
				                                                 .param("vendorId", vendorId)
				                                                 .param("timestamp", new Date().toLocaleString())
				                                                 .param("data", JacksonUtil.toJson(ci))
				                          ) //执行请求  
				          .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("0"))
				          //.andExpect(MockMvcResultMatchers.jsonPath("$.data.goodsName").value("Iphone6s"))
		                  .andDo(MockMvcResultHandlers.print());
	}
	
	/** 查询保险证列表 **/
	//@Test
	public void getInsInsuranceCertListTest() throws Exception{
		GetInsuranceCertListInfo ci = new GetInsuranceCertListInfo();
		ci.setPageNo(2);
		ci.setPageSize(3);
		ci.setStatus("0");
		
		mockMvc.perform(MockMvcRequestBuilders.post("/insurance/certificate/list/get")
				                                                 .param("vendorId", vendorId)
				                                                 .param("timestamp", new Date().toLocaleString())
				                                                 .param("data", JacksonUtil.toJson(ci))
				                          ) //执行请求  
				          .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("0"))
				          //.andExpect(MockMvcResultMatchers.jsonPath("$.data.goodsName").value("Iphone6s"))
		                  .andDo(MockMvcResultHandlers.print());
	}
	
	/** 查看展示保险证信息 **/
	//@Test
	public void getDisplayInsuranceCertTest() throws Exception {
		InsuranceCertNo  ci = new InsuranceCertNo();
		ci.setInsuranceCertNo("201512140000000001/AA0009");
		mockMvc.perform(MockMvcRequestBuilders.post("/insurance/certificate/display/get")
				                                                 .param("vendorId", vendorId)
				                                                 .param("timestamp", new Date().toLocaleString())
				                                                 .param("data", JacksonUtil.toJson(ci))
				                          ) //执行请求  
				          .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("0"))
				          //.andExpect(MockMvcResultMatchers.jsonPath("$.data.goodsName").value("Iphone6s"))
		                  .andDo(MockMvcResultHandlers.print());
		
	}
}
