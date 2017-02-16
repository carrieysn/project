package com.cifpay.insurance.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.cifpay.insurance.base.BaseController;
import com.cifpay.insurance.model.InsGearingRule;
import com.cifpay.insurance.model.InsWarningRule;
import com.cifpay.insurance.param.DataResponseInfo;
import com.cifpay.insurance.param.ResponseInfo;
import com.cifpay.insurance.service.InsGearingRuleService;
import com.cifpay.insurance.service.InsWarningRuleService;
import com.cifpay.insurance.util.JacksonUtil;

/**
 * 
 * 信用分管理器
 * @author yanthe
 *
 */
@Controller
@RequestMapping("/inner/insurance/riskControl")
public class InsRiskController extends BaseController {
	private static final Logger LOG = LogManager.getLogger(InsRiskController.class);
	@Autowired
	private InsGearingRuleService insGearingRuleService;
	@Autowired
	private InsWarningRuleService insWarningRuleService;
	
	/**
	 * 调整杠杆规则
	 * @param request
	 * @return
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/gearingRule/adjust",method = RequestMethod.POST, produces = { "text/json;charset=UTF-8" })
	public @ResponseBody String adjustGearingRule(HttpServletRequest request) {
		ResponseInfo responseOut = new ResponseInfo();
		LOG.debug("开始调整杠杆规则");
		try {
			Long insUserId = (Long)request.getAttribute("insUserId");
			if(insUserId == null) insUserId = Long.valueOf(request.getParameter("insUserId"));
			JSONObject dataObj = (JSONObject)request.getAttribute("data");
			InsGearingRule bean = null;
			if (dataObj != null) {
				bean = (InsGearingRule)dataObj.toJavaObject(dataObj, InsGearingRule.class);
			} else {
				bean = (InsGearingRule)JacksonUtil.fromJson(request.getParameter("data"), InsGearingRule.class);
			}
			boolean flag = insGearingRuleService.adjustGearingRule(insUserId, bean.getId(), bean.getCreditScore(), bean.getGearing());
			if(!flag){
				responseOut.setCode("-1");
				responseOut.setMsg("调整杠杆规则失败");
			}
		} catch (Exception e) {
			responseOut =  handleRespException(e);
		}
		LOG.debug("返回调整杠杆规则结果");
		return  JacksonUtil.toJson(responseOut);
	}
	
	/**
	 * 获取预警设置信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/warningRule/get",method = RequestMethod.POST, produces = { "text/json;charset=UTF-8" })
	public @ResponseBody String getWarningRule(HttpServletRequest request) {
		DataResponseInfo ri = null;
		LOG.debug("开始获取预警设置信息");
		try {
			InsWarningRule rule = insWarningRuleService.getOne();
			ri = new DataResponseInfo();
			ri.setData(rule);
		} catch (Exception e) {
			ResponseInfo res = handleRespException(e);
			return JacksonUtil.toJson(res);
		}
		LOG.debug("返回获取预警设置信息");
		return JacksonUtil.toJson(ri);
	}
	
	/**
	 * 修改预警设置信息
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/warningRule/change",method = RequestMethod.POST, produces = { "text/json;charset=UTF-8" })
	public @ResponseBody String changeWarningRule(HttpServletRequest request) {
		ResponseInfo responseOut = new ResponseInfo();
		LOG.debug("开始预警设置信息");
		try {
			Long insUserId = (Long)request.getAttribute("insUserId");
			if(insUserId == null) insUserId = Long.valueOf(request.getParameter("insUserId"));
			JSONObject dataObj = (JSONObject)request.getAttribute("data");
			InsWarningRule bean = null;
			if (dataObj != null) {
				bean = (InsWarningRule)dataObj.toJavaObject(dataObj, InsWarningRule.class);
			} else {
				bean = (InsWarningRule)JacksonUtil.fromJson(request.getParameter("data"), InsWarningRule.class);
			}
			boolean flag = insWarningRuleService.changeWarningRule(insUserId, bean.getGreenMax(), bean.getYellowMax());
			if(!flag){
				responseOut.setCode("-1");
				responseOut.setMsg("预警设置信息失败");
			}
		} catch (Exception e) {
			responseOut =  handleRespException(e);
		}
		LOG.debug("返回预警设置信息结果");
		return  JacksonUtil.toJson(responseOut);
	}
}
