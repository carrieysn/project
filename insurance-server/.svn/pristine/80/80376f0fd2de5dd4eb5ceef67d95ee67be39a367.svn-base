/**
 * File: UserLoginController.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年12月22日 下午9:01:14
 */
package com.cifpay.insurance.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cifpay.insurance.InsuranceCertStatusEnum;
import com.cifpay.insurance.base.BaseController;
import com.cifpay.insurance.model.InsUser;
import com.cifpay.insurance.param.DataResponseInfo;
import com.cifpay.insurance.param.ResponseInfo;
import com.cifpay.insurance.param.SystemParamReqInfo;
import com.cifpay.insurance.param.user.UserAccountLoginInfo;
import com.cifpay.insurance.param.user.UserAccountLoginResult;
import com.cifpay.insurance.push.InsuranceEventDefaultHandler;
import com.cifpay.insurance.push.event.InsuranceCertStateChangeEvent;
import com.cifpay.insurance.push.event.InsurancePolicyEvent;
import com.cifpay.insurance.service.InsInsuranceCertService;
import com.cifpay.insurance.service.InsPolicyService;
import com.cifpay.insurance.service.InsUserService;
import com.cifpay.insurance.util.JacksonUtil;

/**
 * 用户登录管理
 * 
 * @author 张均锋
 *
 */
@Controller
@RequestMapping("/inner/insurance/user")
public class UserLoginController  extends BaseController {
	private final static String VALIDATION_REMOTELOGININFO = "com.cifpay.ins.user.UserAccountLoginInfoSet";
	@Autowired
	private InsInsuranceCertService insInsuranceCertService;
	@Autowired
	private InsPolicyService insPolicyService;
	@Autowired
	private InsUserService insUserService;

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
			UserAccountLoginInfo ci = populateBizData(request, UserAccountLoginInfo.class, VALIDATION_REMOTELOGININFO);
			// do login job...
			InsUser iu = insUserService.getInsUserByUserAccount(ci.getUserAccount());
			if (iu == null) {
				ri = new ResponseInfo();
				ri.setCode(resultCode.get("common.framework.username.password.error"));
			    ri.setMsg("用户名或密码错误");
			    return JacksonUtil.toJson(ri);
			}
			if (!iu.getPassword().equals(ci.getPassword())) {
				ri = new ResponseInfo();
				ri.setCode(resultCode.get("common.framework.username.password.error"));
			    ri.setMsg("用户名或密码错误");
			    return JacksonUtil.toJson(ri);
			}
			// 推送登录用户数据至push服务器。(异步），须请求推送。
			//pushUserData(WebConstant.MERCHID);
			UserAccountLoginResult r = new UserAccountLoginResult();
			BeanUtils.copyProperties(iu, r);
			return JacksonUtil.toJson(new DataResponseInfo(r));
		} catch (Exception e) {
			ri = handleRespException(e);
			return JacksonUtil.toJson(ri);
		}
	}
	
	private void pushUserData(String vendorId) {
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
	 * 登出
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/account/logout", method = RequestMethod.POST, produces = { "text/json;charset=UTF-8" })
	public @ResponseBody String logout(HttpServletRequest request) {
		ResponseInfo ri = null;
		try {
			SystemParamReqInfo spri = populateSystemParamReq(request);
			// do login job...
			UserAccountLoginResult r = new UserAccountLoginResult();
			ri = new DataResponseInfo(r);
			ri.setMsg("登出成功");
		} catch (Exception e) {
			ri = handleRespException(e);
		}
		return JacksonUtil.toJson(ri);
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
