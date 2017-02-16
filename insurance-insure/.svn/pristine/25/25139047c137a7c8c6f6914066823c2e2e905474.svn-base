/**
 * File: SecurityInterceptor.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年12月21日 上午10:16:25
 */
package com.cifpay.insurance.filter;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.cifpay.insurance.bean.ResponseInfo;
import com.cifpay.insurance.util.JacksonUtil;
import com.cifpay.insurance.util.StringUtils;
import com.cifpay.security.exception.SecurityException;
import com.cifpay.security.util.AES256Util;
import com.cifpay.security.util.RSAUtils;
import com.cifpay.starframework.cache.ConfigPropertiesCache;
import com.cifpay.starframework.cache.ServiceResultCodeCache;

/**
 * 安全拦截器，对接口的数据进行拦截，验证处理 。
 * 
 * @author 张均锋
 *
 */
public class SecurityInterceptor implements HandlerInterceptor/*extends HandlerInterceptorAdapter*/ {
	protected static final Logger logger = LogManager.getLogger(SecurityInterceptor.class);
	protected static ServiceResultCodeCache resultCode = ServiceResultCodeCache.getInstance();

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String data = request.getParameter("data");
		String key = request.getParameter("key");
		String mac = request.getParameter("mac");
		String publicKey = ConfigPropertiesCache.getInstance().get("client.publicKey");
		String privateKey = ConfigPropertiesCache.getInstance().get("server.privateKey");
		if(StringUtils.isEmpty(privateKey)) {
			logger.error("privateKey is null,Decrypt is Fail.");
			throw new SecurityException("privateKey is null,Decrypt is Fail.");
		}
		if (StringUtils.isEmpty(data) || StringUtils.isEmpty(key) || StringUtils.isEmpty(mac)) {
			ResponseInfo ri = new ResponseInfo();
			ri.setCode(resultCode.get("common.request.param.error"));
			ri.setMsg("传入参数不合法");
			render(response, JacksonUtil.toJson(ri), "text/plain;charset=UTF-8");
			return false;
		}
		try {
			//解密数据
			//data = URLDecoder.decode(data, "UTF-8");
			String decodeData = URLDecoder.decode(data, "UTF-8").replaceAll(" ", "+");
			String decodeKey = key;//URLDecoder.decode(key, "UTF-8");
			String decodeMac = mac;//URLDecoder.decode(mac, "UTF-8");
			String aesKey = RSAUtils.decryptByPrivateKey(decodeKey, privateKey);
			String decryptData = AES256Util.decrypt(decodeData, aesKey);
			boolean valid = RSAUtils.verify(decryptData, publicKey, decodeMac);
			if (!valid) {
				throw new RuntimeException("参数被非法篡改！");
			}
			Map<String, Object> dataMap = JSON.parseObject(decryptData, Map.class);
			/*String decData = SecurityUtils.decode(data, key, mac, publicKey, privateKey);
			String decryptData = new String(ApacheBase64.b64decode(decData), "UTF-8");
			*/
			for(String k : dataMap.keySet()){//替换请求中解密后的数据
				request.setAttribute(k, dataMap.get(k));
			}
		} catch (Exception e) {
			String msg = "";
			if (e.getMessage() != null) {
				msg = e.getMessage();
			}
			logger.error("Decrypt is Fail.\n"+msg, e);
			ResponseInfo ri = new ResponseInfo();
			ri.setCode(resultCode.get("common.system.error"));
			ri.setMsg("系统异常！解密出现异常！"+msg);
			render(response, JacksonUtil.toJson(ri), "text/plain;charset=UTF-8");
			return false;
		}
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		//返回加密处理
		/*super.postHandle(request, response, handler, modelAndView);*/
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	protected void render(HttpServletResponse response, String text, String contentType) {
	    try {
	      response.setContentType(contentType);
	      response.getWriter().write(text);
	    } catch (IOException e) {
	       logger.error(e.getMessage(), e);
	    }
	 }
}
