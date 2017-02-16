/**
 * File: UserVendorLoginControllerOpen.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年12月22日 下午9:01:14
 */
package com.cifpay.insurance.controller.openapi;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cifpay.insurance.InsuranceCertStatusEnum;
import com.cifpay.insurance.base.BaseController;
import com.cifpay.insurance.cache.InsCache;
import com.cifpay.insurance.exception.InsuranceBizRuntimeException;
import com.cifpay.insurance.model.InsPolicy;
import com.cifpay.insurance.model.InsVendor;
import com.cifpay.insurance.param.DataResponseInfo;
import com.cifpay.insurance.param.ResponseInfo;
import com.cifpay.insurance.param.SystemParamReqInfo;
import com.cifpay.insurance.param.user.UserVendorAccountLoginInfo;
import com.cifpay.insurance.param.user.UserVendorAccountLoginResult;
import com.cifpay.insurance.push.DataCacheManager;
import com.cifpay.insurance.push.InsuranceEventDefaultHandler;
import com.cifpay.insurance.push.event.InsuranceCertStateChangeEvent;
import com.cifpay.insurance.push.event.InsurancePolicyEvent;
import com.cifpay.insurance.service.InsVendorService;
import com.cifpay.insurance.util.JacksonUtil;

/**
 * 用户登录管理
 * 
 * @author 张均锋
 *
 */
@Controller
@RequestMapping("/insurance/user/vendor")
public class UserVendorLoginControllerOpen extends BaseController {
	private final static String VALIDATION_USERVENDORACCOUNTLOGININFO = "com.cifpay.ins.user.UserVendorAccountLoginInfoSet";
	@Autowired
	private InsVendorService insVendorService;

	/**
	 * 授权登录
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/account/login", method = RequestMethod.POST, produces = { "text/json;charset=UTF-8" })
	public @ResponseBody String authLogin(HttpServletRequest request) {
		ResponseInfo ri = null;
		try {
			// SystemParamReqInfo spri = populateSystemParamReq(request);
			UserVendorAccountLoginInfo ci = populateBizData(request, UserVendorAccountLoginInfo.class, VALIDATION_USERVENDORACCOUNTLOGININFO);
			// do login job...
			InsVendor iu = insVendorService.getInsVendorByLoginAccount(ci.getUserAccount());
			if (iu == null) {
				ri = new ResponseInfo();
				ri.setCode(resultCode.get("common.framework.username.password.error"));
			    ri.setMsg("用户名密码错误");
			    return JacksonUtil.toJson(ri);
			}
			if (!iu.getLoginPassword().equals(ci.getPassword())) {
				ri = new ResponseInfo();
				ri.setCode(resultCode.get("common.framework.username.password.error"));
			    ri.setMsg("用户名密码错误");
			    return JacksonUtil.toJson(ri);
			}
			// 推送登录用户数据至push服务器。(异步），须请求推送。
			//pushUserData(WebConstant.MERCHID);
			UserVendorAccountLoginResult r = new UserVendorAccountLoginResult();
			r.setUserAccount(ci.getUserAccount());
			r.setVendorId(iu.getId()+"");
			InsPolicy ip = null;
			try {
				ip = InsCache.getInsPolicyCache(r.getVendorId());	
			} catch (InsuranceBizRuntimeException e) {
				ri = new ResponseInfo();
				ri.setCode(resultCode.get("biz.policy.notfound"));
			    ri.setMsg("您尚未投保，请先投保！");
			    return JacksonUtil.toJson(ri);
			}
			if (ip == null) {
				ri = new ResponseInfo();
				ri.setCode(resultCode.get("biz.policy.notfound"));
			    ri.setMsg("您尚未投保，请先投保！");
			    return JacksonUtil.toJson(ri);
			}
			return JacksonUtil.toJson(new DataResponseInfo(r));
		} catch (Exception e) {
			ri = handleRespException(e);
			return JacksonUtil.toJson(ri);
		}
	}
	
	private void pushUserData(String vendorId) {
		DataCacheManager.loadTodayVendorDataCache(vendorId);//先加载今天数据
		//保单信息。
		InsurancePolicyEvent event = new InsurancePolicyEvent(this);
		event.setVendorId(vendorId);
		event.setTag(-1);//表初始化
		InsuranceEventDefaultHandler.getInstance().doPremiumChange(event);
		
		//保险证信息的
		//今日新增保险证
		InsuranceCertStateChangeEvent ce = new InsuranceCertStateChangeEvent(this);
		ce.setVendorId(vendorId);
		ce.setStatus(InsuranceCertStatusEnum.NOT_EFFECTIVED.val);
		ce.setTag(-1);//表初始化
		InsuranceEventDefaultHandler.getInstance().doInsuranceCertStateChange(ce);
		//今日退货
		ce = new InsuranceCertStateChangeEvent(this);
		ce.setVendorId(vendorId);
		ce.setStatus(InsuranceCertStatusEnum.TO_REFUND.val);
		ce.setTag(-1);//表初始化
		InsuranceEventDefaultHandler.getInstance().doInsuranceCertStateChange(ce);
	}

	/**
	 * 请求推送数据
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/message/push/request", method = RequestMethod.POST, produces = { "text/json;charset=UTF-8" })
	public @ResponseBody String requestPushMessage(HttpServletRequest request) {
		ResponseInfo ri = null;
		try {
			SystemParamReqInfo spri = populateSystemParamReq(request);
			// 推送登录用户数据至push服务器。(异步）
			pushUserData(spri.getVendorId());
			ri = new ResponseInfo();
		} catch (Exception e) {
			ri = handleRespException(e);
		}
		return JacksonUtil.toJson(ri);
	}
}
