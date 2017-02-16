package com.cifpay.insurance.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cifpay.insurance.base.BaseController;
import com.cifpay.insurance.model.InsGearingRule;
import com.cifpay.insurance.param.DataResponseInfo;
import com.cifpay.insurance.param.ResponseInfo;
import com.cifpay.insurance.param.policy.InsGearingRuleListInfo;
import com.cifpay.insurance.service.InsGearingRuleService;
import com.cifpay.insurance.util.JacksonUtil;

/**
 * 
 * 信用分管理器
 * @author yanthe
 *
 */
@Controller
@RequestMapping("/inner/insurance/gearingRule")
public class InsGearingRuleController extends BaseController {

	private static final Logger LOG = LogManager.getLogger(InsGearingRuleController.class);
	@Autowired
	private InsGearingRuleService insGearingRuleService;
	
	/**
	 * 获取信用评级规则列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/list/get",method = RequestMethod.POST, produces = { "text/json;charset=UTF-8" })
	public @ResponseBody String getInsPolicyList(HttpServletRequest request) {
		DataResponseInfo ri = null;
		LOG.debug("开始获取信用评级规则列表");
		try {
			List<InsGearingRule> temp = insGearingRuleService.getList();
			List<InsGearingRuleListInfo> list = new ArrayList<InsGearingRuleListInfo>();
			if(temp != null){
				InsGearingRuleListInfo info = null;
				for(InsGearingRule rule : temp){
					info = new InsGearingRuleListInfo();
					info.setId(rule.getId());
					info.setCode(rule.getCode());
					info.setCreditScore(rule.getCreditScore());
					info.setGearing(rule.getGearing());
					list.add(info);
				}
			}
			ri = new DataResponseInfo();
			ri.setData(list);
		} catch (Exception e) {
			ResponseInfo res = handleRespException(e);
			return JacksonUtil.toJson(res);
		}
		LOG.debug("返回信用评级规则列表");
		return JacksonUtil.toJson(ri);
	}
}
