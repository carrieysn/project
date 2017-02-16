/**
 * File: InsPolicyChangeListControllerOpen.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年12月7日 下午3:28:37
 */
package com.cifpay.insurance.controller.openapi;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cifpay.insurance.base.BaseController;
import com.cifpay.insurance.base.Page;
import com.cifpay.insurance.model.InsPolicyChangeList;
import com.cifpay.insurance.param.PageResponseInfo;
import com.cifpay.insurance.param.SystemParamReqInfo;
import com.cifpay.insurance.param.policy.GetPolicyChangeListInfo;
import com.cifpay.insurance.param.policy.GetPolicyChangeListResult;
import com.cifpay.insurance.service.InsPolicyChangeListService;
import com.cifpay.insurance.util.JacksonUtil;

/**
 * 保单变动管理
 * 
 * @author 张均锋
 *
 */
@Controller
@RequestMapping("/insurance/policy/change")
public class InsPolicyChangeListControllerOpen extends BaseController {
	/** 创建充值订单验证规则标识 **/
	private final static String VALIDATION_GETPOLICYCHANGELISTINFO = "com.cifpay.ins.policy.GetPolicyChangeListInfoSet";
	@Autowired
	private InsPolicyChangeListService insPolicyChangeListService;

	@RequestMapping(value = "/list/get",method = RequestMethod.POST, produces = { "text/json;charset=UTF-8" })
	public @ResponseBody String getPolicyChangeList(HttpServletRequest request) {
		try {
			SystemParamReqInfo spri = populateSystemParamReq(request);
			GetPolicyChangeListInfo gpc = populateBizData(request, GetPolicyChangeListInfo.class, VALIDATION_GETPOLICYCHANGELISTINFO);
			Page<InsPolicyChangeList> page = new Page<InsPolicyChangeList>();
			page.setPageNo(gpc.getPageNo());
			page.setPageSize(gpc.getPageSize());
			List<InsPolicyChangeList> pcl = insPolicyChangeListService.getPolicyChangeList(spri.getVendorId(), gpc, page);
			List<GetPolicyChangeListResult> resultList = new ArrayList<GetPolicyChangeListResult>();
			for(InsPolicyChangeList ic:pcl){
				GetPolicyChangeListResult pclr = new GetPolicyChangeListResult();
				pclr.setChangeTime(ic.getChangeTime());
				pclr.setChangeAmount(ic.getChangeAmount());
				pclr.setCurInsuredAmount(ic.getCurInsuredAmount());
				pclr.setCurPremium(ic.getCurPremium());
				pclr.setRefVoucherNo(ic.getRefVoucherNo());
				pclr.setType(ic.getType());
				resultList.add(pclr);
			}
			PageResponseInfo ri = new PageResponseInfo();
			ri.setRecordCount(page.getRecordCount());
			ri.setData(resultList);
			return JacksonUtil.toJson(ri);
		} catch (Exception e) {
			return JacksonUtil.toJson(handleRespException(e));
		}
	}
}
