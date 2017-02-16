package com.cifpay.insurance.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cifpay.insurance.bean.RemoteLoginInfo;
import com.cifpay.insurance.config.WebConstant;
import com.cifpay.insurance.model.InsUser;
import com.cifpay.insurance.util.JacksonUtil;
import com.cifpay.insurance.util.StringUtils;
import com.cifpay.starframework.adapter.HttpCall;
import com.cifpay.starframework.security.HashUtil;
@Controller
public class LoginController {
	private static Logger logger = LoggerFactory.getLogger(MainController.class);
	
	
	@ResponseBody
	@RequestMapping(value="/login",method = RequestMethod.POST)
	public Map<String,Object> login(String userName,String password,HttpServletRequest req){
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		try {
			StringBuffer sub = new StringBuffer();
			sub.append(WebConstant.INSURANCE_SERVER_DOMAIN);
			sub.append("/inner/insurance/user/vendor/account/login");
			RemoteLoginInfo loginInfo = new RemoteLoginInfo();
			loginInfo.setUserAccount(userName);
			loginInfo.setPassword(HashUtil.md5(password));
			Map<String,String> param = new HashMap<String,String>();
			param.put("data", JacksonUtil.toJson(loginInfo));
			HttpCall httpCall = new HttpCall();
			Object resultObj = httpCall.httpCall(sub.toString(), WebConstant.REQUEST_POST,param);
			String resultStr = (String)resultObj;
			JSONObject json = JSON.parseObject(resultStr);
			String code = json.getString("code");
			String data = json.getString("data");
			if(StringUtils.isNotEmpty(code) && code.equals("0")){
				JSONObject dataObj = JSONObject.parseObject(data);
				String vendorId = dataObj.getString("vendorId");
				String userAccount = dataObj.getString("userAccount");
				InsUser insUser = new InsUser();
				insUser.setId(Long.valueOf(vendorId));
				insUser.setUserAccount(userAccount);
			    req.getSession().setAttribute(WebConstant.SESSIONUSER, insUser);
			    map.put("msg", "0");
			}else{
				map.put("msg", "1");
			}
		} catch (Exception e) {
			map.put("msg", "1");
			logger.error("远程登录失败！", e);
		}
		     return map;
	}
	
	@RequestMapping(value="/loginOut")
	public ModelAndView loginOut(HttpServletRequest request){
		ModelAndView model = new ModelAndView();
	    InsUser user =  (InsUser) request.getSession().getAttribute("user");
	    
	    if(user != null){
	    	request.getSession().removeAttribute("user");
		}
	    model.setViewName("index");
	    return model;
	    
	 }
	
	

}
