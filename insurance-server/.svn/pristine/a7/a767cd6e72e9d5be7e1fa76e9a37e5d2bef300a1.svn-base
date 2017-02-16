/**
 * File: InsVendorBankAccountController.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年12月5日 下午2:36:50
 */
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
import com.cifpay.insurance.model.InsVendorBankAccount;
import com.cifpay.insurance.param.DataResponseInfo;
import com.cifpay.insurance.param.ResponseInfo;
import com.cifpay.insurance.param.SystemParamReqInfo;
import com.cifpay.insurance.param.vendor.BindVendorBankAccountInfo;
import com.cifpay.insurance.param.vendor.GetVendorBankAccountListResult;
import com.cifpay.insurance.param.vendor.VendorBankAccount;
import com.cifpay.insurance.service.InsVendorBankAccountService;
import com.cifpay.insurance.util.JacksonUtil;

/**
 * 商户银行账户管理。
 * 
 * @author 张均锋
 *
 */
@Controller
@RequestMapping("/inner/insurance/vendor/bank/account")
public class InsVendorBankAccountController extends BaseController {

	private static final Logger LOG = LogManager.getLogger(InsVendorBankAccountController.class);
	@Autowired
	private InsVendorBankAccountService insVendorBankAccountService;
	
	/** 绑定银行卡规则标识 **/
	private final static String VALIDATION_BINDVENDORBANKACCOUNTINFO = "com.cifpay.ins.vendor.BindVendorBankAccountInfoSet";
	/** 绑定银行卡规则标识 **/
	private final static String VALIDATION_VENDORBANKACCOUNT = "com.cifpay.ins.vendor.VendorBankAccountSet";
	/**
	 * 绑定银行卡
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/bind", method = RequestMethod.POST, produces = { "text/json;charset=UTF-8" })
	@ResponseBody
	public String bindVendorBankAccount(HttpServletRequest request) {
		ResponseInfo ri = null;
		try {
			SystemParamReqInfo spr = populateSystemParamReq(request);
			BindVendorBankAccountInfo bvba = populateBizData(request, BindVendorBankAccountInfo.class, VALIDATION_BINDVENDORBANKACCOUNTINFO);
			InsVendorBankAccount ivba = new InsVendorBankAccount();
			ivba.setVendorId(spr.getVendorId());
			ivba.setBankCode(bvba.getBankCode());
			ivba.setBankName(bvba.getBankName());
			ivba.setBankAccount(bvba.getBankAccount());
			ivba.setAccountName(bvba.getAccountName());
			ivba.setReserveMobilePhone(bvba.getReserveMobilePhone());
			insVendorBankAccountService.saveInsVendorBankAccount(ivba);
			ri = new ResponseInfo();
		} catch (Exception e) {
			ri =  handleRespException(e);
		}
		return JacksonUtil.toJson(ri);
	}
	
	/**
	 * 解绑银行卡
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/unbind", method = RequestMethod.POST, produces = { "text/json;charset=UTF-8" })
	@ResponseBody
	public String unbindVendorBankAccount(HttpServletRequest request) {
		ResponseInfo ri = null;
		try {
			SystemParamReqInfo spr = populateSystemParamReq(request);
			VendorBankAccount bvba = populateBizData(request, VendorBankAccount.class, VALIDATION_VENDORBANKACCOUNT);
			insVendorBankAccountService.unbindInsVendorBankAccount(spr.getVendorId(), bvba.getBankAccount());
			ri = new ResponseInfo();
		} catch (Exception e) {
			ri =  handleRespException(e);
		}
		return JacksonUtil.toJson(ri);
	}
	
	/**
	 * 设置默认银行卡
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/default/set", method = RequestMethod.POST, produces = { "text/json;charset=UTF-8" })
	@ResponseBody
	public String setVendorBankAccountDefault(HttpServletRequest request) {
		ResponseInfo ri = null;
		try {
			SystemParamReqInfo spr = populateSystemParamReq(request);
			VendorBankAccount bvba = populateBizData(request, VendorBankAccount.class, VALIDATION_VENDORBANKACCOUNT);
			insVendorBankAccountService.setDefaultInsVendorBankAccount(spr.getVendorId(), bvba.getBankAccount());
			ri = new ResponseInfo();
		} catch (Exception e) {
			ri =  handleRespException(e);
		}
		return JacksonUtil.toJson(ri);
	}
	
	/**
	 * 设置默认银行卡
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/list/get", method = RequestMethod.POST, produces = { "text/json;charset=UTF-8" })
	@ResponseBody
	public String getVendorBankAccountList(HttpServletRequest request) {
		ResponseInfo ri = null;
		try {
			SystemParamReqInfo spr = populateSystemParamReq(request);
			List<InsVendorBankAccount> list = insVendorBankAccountService.getInsVendorBankAccountList(spr.getVendorId());
			List<GetVendorBankAccountListResult> rets = new ArrayList<GetVendorBankAccountListResult>();
			for (InsVendorBankAccount ivb: list) {
				GetVendorBankAccountListResult r = new GetVendorBankAccountListResult();
				rets.add(r);
				r.setBankName(ivb.getBankName());
				r.setAccountName(ivb.getAccountName());
				r.setBankAccount(ivb.getBankAccount());
				r.setReserveMobilePhone(ivb.getReserveMobilePhone());
				r.setIsDefault(ivb.getIsDefault());
			}
			ri = new DataResponseInfo(rets);
		} catch (Exception e) {
			ri =  handleRespException(e);
		}
		return JacksonUtil.toJson(ri);
	}
	
}
