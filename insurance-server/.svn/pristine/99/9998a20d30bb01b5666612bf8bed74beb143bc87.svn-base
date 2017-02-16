/**
 * File: InsPolicyOrderControllerOpen.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年12月22日 下午8:24:44
 */
package com.cifpay.insurance.controller.openapi;

import java.util.ArrayList;
import java.util.Date;
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
import com.cifpay.insurance.base.Page;
import com.cifpay.insurance.controller.InsPolicyOrderController;
import com.cifpay.insurance.model.InsPolicyOrder;
import com.cifpay.insurance.param.PageResponseInfo;
import com.cifpay.insurance.param.ResponseInfo;
import com.cifpay.insurance.param.policy.GetPolicyOrderListInfo;
import com.cifpay.insurance.param.policy.GetPolicyOrderListResult;
import com.cifpay.insurance.service.InsPolicyOrderService;
import com.cifpay.insurance.util.DateUtil;
import com.cifpay.insurance.util.JacksonUtil;

/**
 * 订单内部接口
 * 
 * @author 张均锋
 *
 */
@Controller
@RequestMapping("/insurance/policy/order")
public class InsPolicyOrderControllerOpen extends BaseController {
	private static final Logger logger = LogManager.getLogger(InsPolicyOrderController.class);
	
	@Autowired
	private InsPolicyOrderService insPolicyOrderService;

	/** 查询保费充值记录验证规则标识 **/
	private final static String VALIDATION_GETPOLICYORDERLIST = "com.cifpay.ins.policy.GetPolicyOrderListInfoSet";

	/**
	 * 查询订单列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/list/get", method = RequestMethod.POST, produces = { "text/json;charset=UTF-8" })
	@ResponseBody
	public String getPolicyOrderList(HttpServletRequest request) {
		PageResponseInfo ri = null;
		try {
			populateSystemParamReq(request);
			GetPolicyOrderListInfo gc = populateBizData(request, GetPolicyOrderListInfo.class, VALIDATION_GETPOLICYORDERLIST);
			if (logger.isDebugEnabled()) {
				logger.debug("getPolicyFeeCharge(HttpServletRequest request)");
				logger.debug("查询保费充值记录信息>>>>");
			}
			if (gc.getEndDate() != null) {
				Date to = DateUtil.parseDate(gc.getEndDate(), "yyyy-MM-dd");
				gc.setEndDate(DateUtil.formatDate(DateUtil.addDay(to, 1), "yyyy-MM-dd"));
			}
			Page<InsPolicyOrder> page = new Page<InsPolicyOrder>();
			page.setPageNo(gc.getPageNo());
			page.setPageSize(gc.getPageSize());
			insPolicyOrderService.getInsPolicyOrderList(gc, page);
			List<GetPolicyOrderListResult> rets = new ArrayList<GetPolicyOrderListResult>();
			for (InsPolicyOrder ipo: page.getResult()) {
				GetPolicyOrderListResult ret = new GetPolicyOrderListResult();
				ret.setAmount(ipo.getAmount());
				ret.setBeforePremium(ipo.getBeforePremium());
				ret.setBillNo(ipo.getBillNo());
				ret.setOrderTime(ipo.getCreatedTime());
				ret.setStatus(ipo.getStatus());
				rets.add(ret);
			}
			ri = new PageResponseInfo();
			ri.setRecordCount(page.getRecordCount());
			ri.setData(rets);
		} catch (Exception e) {
			ResponseInfo responseOut = handleRespException(e);
			return JacksonUtil.toJson(responseOut);
		}
		return JacksonUtil.toJson(ri);
	}
}