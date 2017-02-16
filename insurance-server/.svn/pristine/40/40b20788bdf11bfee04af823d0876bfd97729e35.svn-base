/**
 * File: InsVendorController.java
 *
 * Copyright：Copyright (c) 2016
 * Company：深圳市银信网银科技有限公司
 * Created on：2016年1月15日 下午2:16:17
 */
package com.cifpay.insurance.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cifpay.insurance.base.BaseController;
import com.cifpay.insurance.model.InsVendor;
import com.cifpay.insurance.param.DataResponseInfo;
import com.cifpay.insurance.param.ResponseInfo;
import com.cifpay.insurance.param.user.UserVendorAccountLoginInfo;
import com.cifpay.insurance.param.user.UserVendorAccountLoginResult;
import com.cifpay.insurance.service.InsVendorService;
import com.cifpay.insurance.util.JacksonUtil;

/**
 * 商户登录
 * 
 * @author 张均锋
 *
 */
@Controller
@RequestMapping("/inner/insurance/user/vendor")
public class InsVendorController extends BaseController {
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
			//DataCacheManager.loadTodayVendorDataCache(r.getVendorId());
			return JacksonUtil.toJson(new DataResponseInfo(r));
		} catch (Exception e) {
			ri = handleRespException(e);
			return JacksonUtil.toJson(ri);
		}
	}

}
