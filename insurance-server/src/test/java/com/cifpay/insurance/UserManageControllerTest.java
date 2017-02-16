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

import com.cifpay.insurance.param.user.GetUserAccountInfo;
import com.cifpay.insurance.param.user.UserAccountOperateInfo;
import com.cifpay.insurance.param.user.UserAccountSaveInfo;
import com.cifpay.insurance.param.user.UserAccountUpdateInfo;
import com.cifpay.insurance.util.JacksonUtil;
import com.cifpay.starframework.security.HashUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:applicationContext.xml","classpath:applicationContextMvc-servlet.xml"})
public class UserManageControllerTest {
	
	private String vendorId = "11";
	
	
	@Autowired  
    private WebApplicationContext wac;  
    private MockMvc mockMvc;
    
    @Before  
    public void setUp() {  
       mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
	//@Test
    public void getUserListTest() throws Exception{
    	GetUserAccountInfo ci = new GetUserAccountInfo();
		ci.setPageNo(1);
		ci.setPageSize(10);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/inner/insurance/user/manage/list")
				                                                 .param("vendorId", vendorId)
				                                                 .param("timestamp", new Date().toLocaleString())
				                                                 .param("data", JacksonUtil.toJson(ci))
				                          ) //执行请求  
				          .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("0"))
				          //.andExpect(MockMvcResultMatchers.jsonPath("$.data.goodsName").value("Iphone6s"))
		                  .andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void saveUserTest() throws Exception{
		UserAccountSaveInfo user = new UserAccountSaveInfo();
    	user.setUserAccount("jtest12");
    	user.setUserName("Lucy");
    	user.setPassword("1");
    	user.setIsEnable(1);
		mockMvc.perform(MockMvcRequestBuilders.post("/inner/insurance/user/manage/save")
				                                                 .param("vendorId", vendorId)
				                                                 .param("timestamp", new Date().toLocaleString())
				                                                 .param("data", JacksonUtil.toJson(user))
				                          ) //执行请求  
				          .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("0"))
				          //.andExpect(MockMvcResultMatchers.jsonPath("$.data.goodsName").value("Iphone6s"))
		                  .andDo(MockMvcResultHandlers.print());
	}
	
	//@Test
	public void updateUserTest() throws Exception{
		UserAccountUpdateInfo user = new UserAccountUpdateInfo();
    	user.setId(53l);
    	user.setUserName("Lucy lili");
    	user.setIsEnable(0);
		mockMvc.perform(MockMvcRequestBuilders.post("/inner/insurance/user/manage/update")
				                                                 .param("vendorId", vendorId)
				                                                 .param("timestamp", new Date().toLocaleString())
				                                                 .param("data", JacksonUtil.toJson(user))
				                          ) //执行请求  
				          .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("0"))
				          //.andExpect(MockMvcResultMatchers.jsonPath("$.data.goodsName").value("Iphone6s"))
		                  .andDo(MockMvcResultHandlers.print());
	}
	
	//@Test
	public void deleteUserTest() throws Exception{
    	UserAccountOperateInfo info = new UserAccountOperateInfo();
    	info.setId(53l);
		mockMvc.perform(MockMvcRequestBuilders.post("/inner/insurance/user/manage/delete")
				                                                 .param("vendorId", vendorId)
				                                                 .param("timestamp", new Date().toLocaleString())
				                                                 .param("data", JacksonUtil.toJson(info))
				                          ) //执行请求  
				          .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("0"))
				          //.andExpect(MockMvcResultMatchers.jsonPath("$.data.goodsName").value("Iphone6s"))
		                  .andDo(MockMvcResultHandlers.print());
	}
	
	//@Test
	public void selectUserTest() throws Exception{
    	UserAccountOperateInfo info = new UserAccountOperateInfo();
    	info.setId(52l);
		mockMvc.perform(MockMvcRequestBuilders.post("/inner/insurance/user/manage/get")
				                                                 .param("vendorId", vendorId)
				                                                 .param("timestamp", new Date().toLocaleString())
				                                                 .param("data", JacksonUtil.toJson(info))
				                          ) //执行请求  
				          .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("0"))
				          //.andExpect(MockMvcResultMatchers.jsonPath("$.data.goodsName").value("Iphone6s"))
		                  .andDo(MockMvcResultHandlers.print());
	}
}
