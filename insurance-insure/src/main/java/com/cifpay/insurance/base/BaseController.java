/**
 * File: BaseController.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年11月25日 上午9:45:06
 */
package com.cifpay.insurance.base;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.cifpay.insurance.bean.ResponseInfo;
import com.cifpay.insurance.bean.SystemParamReqInfo;
import com.cifpay.insurance.exception.InsuranceBizRuntimeException;
import com.cifpay.insurance.util.JacksonUtil;
import com.cifpay.insurance.util.StringUtils;
import com.cifpay.starframework.cache.ConfigPropertiesCache;
import com.cifpay.starframework.cache.ServiceResultCodeCache;

/**
 * 交还险基础控制类
 * 
 * @author 张均锋
 *
 */
public class BaseController {
	protected static final Logger logger = LogManager.getLogger(BaseController.class);
	protected static ServiceResultCodeCache resultCode = ServiceResultCodeCache.getInstance();
	protected static ConfigPropertiesCache configProperties = ConfigPropertiesCache.getInstance();
	
	/**
	 * 处理异常，返回异常代码json
	 * @param e
	 * @return
	 */
	public ResponseInfo handleRespException(Exception e) {
		logger.error("出现异常",e);
		ResponseInfo ri = new ResponseInfo();
		if (e instanceof InsuranceBizRuntimeException) {
			InsuranceBizRuntimeException ie = (InsuranceBizRuntimeException)e;
			ri.setCode(ie.getCode()+"");
			ri.setMsg(ie.getMessage());
		} else {
			ri.setCode(resultCode.get("common.system.error"));
			String emsg = "系统异常！";
			/*if (e.getMessage() != null) {
				emsg += e.getMessage();
			}*/
			ri.setMsg(emsg);
		}
		return ri;
	}
	
	/**
	 * 解析系统参数.
	 * @param request
	 * @return
	 */
	protected SystemParamReqInfo populateSystemParamReq(HttpServletRequest request) {
		SystemParamReqInfo spri = new SystemParamReqInfo();
		String vendorId = (String)request.getAttribute("vendorId");
		String service = null;
		String timestamp = null;
		if (vendorId == null) {
			vendorId = request.getParameter("vendorId");
			service = request.getParameter("service");
			timestamp = request.getParameter("timestamp");
		} else {
			service = (String)request.getAttribute("service");
			timestamp = (String)request.getAttribute("timestamp");
		}
		spri.setVendorId(vendorId);
		spri.setService(service);
		spri.setTimestamp(timestamp);
		if (StringUtils.isEmpty(spri.getVendorId())) {
			throw new InsuranceBizRuntimeException(resultCode.get("common.request.param.error"), "请求参数不正确");
		}
		return spri;
	}
	
	/**
	 * 解析业务参数
	 * 
	 * @param request
	 * @param clazz
	 * @return
	 */
	protected <T> T populateBizData(HttpServletRequest request,  Class<T> clazz, String validationSetName) {
		JSONObject dataObj = (JSONObject)request.getAttribute("data");
		T bean = null;
		String data = null;
		try {
			if (dataObj != null) {
				bean = dataObj.toJavaObject(dataObj, clazz);
			} else {
				data = request.getParameter("data");
				bean = (T)JacksonUtil.fromJson(data, clazz);
			}
		} catch (Exception e) {
			throw new InsuranceBizRuntimeException(resultCode.get("common.request.param.error"), "解析参数出现异常！[data="+(dataObj != null ? dataObj.toJSONString() : data)+"]");
		}
		return bean;
	}
	
}
