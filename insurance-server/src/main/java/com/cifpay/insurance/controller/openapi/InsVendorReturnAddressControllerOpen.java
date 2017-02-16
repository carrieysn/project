/**
 * File: InsVendorReturnAddressControllerOpen.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年12月22日 下午9:00:23
 */
package com.cifpay.insurance.controller.openapi;

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
import com.cifpay.insurance.controller.InsVendorReturnAddressController;
import com.cifpay.insurance.model.InsVendorReturnAddress;
import com.cifpay.insurance.param.DataResponseInfo;
import com.cifpay.insurance.param.ResponseInfo;
import com.cifpay.insurance.param.SystemParamReqInfo;
import com.cifpay.insurance.param.vendor.AddVendorReturnAddressInfo;
import com.cifpay.insurance.param.vendor.GetVendorReturnAddressListResult;
import com.cifpay.insurance.param.vendor.VendorReturnAddressId;
import com.cifpay.insurance.service.InsVendorReturnAddressService;
import com.cifpay.insurance.util.JacksonUtil;


/**
 * 退货地址管理
 * 
 * @author 张均锋
 *
 */
@Controller
@RequestMapping("insurance/vendor/return/address")
public class InsVendorReturnAddressControllerOpen  extends BaseController {

	private static final Logger LOG = LogManager.getLogger(InsVendorReturnAddressController.class);
	@Autowired
	private InsVendorReturnAddressService insVendorReturnAddressService;
	
	/** 绑定退货地址规则标识 **/
	private final static String VALIDATION_ADDVENDORRETURNADDRESSINFO = "com.cifpay.ins.vendor.AddVendorReturnAddressInfoSet";
	/** 绑定退货地址id规则标识 **/
	private final static String VALIDATION_VENDORRETURNADDRESSID = "com.cifpay.ins.vendor.VendorReturnAddressIdSet";
	
	/**
	 * 添加退货地址
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = { "text/json;charset=UTF-8" })
	@ResponseBody
	public String addVendorReturnAddress(HttpServletRequest request) {
		ResponseInfo ri = null;
		try {
			SystemParamReqInfo spr = populateSystemParamReq(request);
			AddVendorReturnAddressInfo avra = populateBizData(request, AddVendorReturnAddressInfo.class, VALIDATION_ADDVENDORRETURNADDRESSINFO);
			InsVendorReturnAddress ivra = new InsVendorReturnAddress();
			ivra.setVendorId(spr.getVendorId());
			ivra.setHolderName(avra.getHolderName());
			ivra.setAddress(avra.getAddress());
			ivra.setContacts(avra.getContacts());
			ivra.setMobilePhone(avra.getMobilePhone());
			insVendorReturnAddressService.saveInsVendorReturnAddress(ivra);
			ri = new ResponseInfo();
		} catch (Exception e) {
			ri =  handleRespException(e);
		}
		return JacksonUtil.toJson(ri);
	}
	
	/**
	 * 删除退货地址
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces = { "text/json;charset=UTF-8" })
	@ResponseBody
	public String deleteVendorReturnAddress(HttpServletRequest request) {
		ResponseInfo ri = null;
		try {
			SystemParamReqInfo spr = populateSystemParamReq(request);
			VendorReturnAddressId vra = populateBizData(request, VendorReturnAddressId.class, VALIDATION_VENDORRETURNADDRESSID);
			insVendorReturnAddressService.deleteInsVendorReturnAddress(spr.getVendorId(), Long.valueOf(vra.getVendorReturnAddressId()));
			ri = new ResponseInfo();
		} catch (Exception e) {
			ri =  handleRespException(e);
		}
		return JacksonUtil.toJson(ri);
	}
	
	/**
	 * 设置默认退货地址
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/default/set", method = RequestMethod.POST, produces = { "text/json;charset=UTF-8" })
	@ResponseBody
	public String setVendorReturnAddressDefault(HttpServletRequest request) {
		ResponseInfo ri = null;
		try {
			SystemParamReqInfo spr = populateSystemParamReq(request);
			VendorReturnAddressId vra = populateBizData(request, VendorReturnAddressId.class, VALIDATION_VENDORRETURNADDRESSID);
			insVendorReturnAddressService.setDefaultInsVendorReturnAddress(spr.getVendorId(), Long.valueOf(vra.getVendorReturnAddressId()));
			ri = new ResponseInfo();
		} catch (Exception e) {
			ri =  handleRespException(e);
		}
		return JacksonUtil.toJson(ri);
	}
	
	/**
	 * 设置默认退货地址
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/list/get", method = RequestMethod.POST, produces = { "text/json;charset=UTF-8" })
	@ResponseBody
	public String getVendorReturnAddressList(HttpServletRequest request) {
		ResponseInfo ri = null;
		try {
			SystemParamReqInfo spr = populateSystemParamReq(request);
			List<InsVendorReturnAddress> list = insVendorReturnAddressService.getInsVendorReturnAddressList(spr.getVendorId());
			List<GetVendorReturnAddressListResult> rets = new ArrayList<GetVendorReturnAddressListResult>();
			for (InsVendorReturnAddress ivr: list) {
				GetVendorReturnAddressListResult r = new GetVendorReturnAddressListResult();
				rets.add(r);
				r.setVendorReturnAddressId(ivr.getId().toString());
				r.setHolderName(ivr.getHolderName());
				r.setAddress(ivr.getAddress());
				r.setContacts(ivr.getContacts());
				r.setMobilePhone(ivr.getMobilePhone());
				r.setIsDefault(ivr.getIsDefault());
			}
			ri = new DataResponseInfo(rets);
		} catch (Exception e) {
			ri =  handleRespException(e);
		}
		return JacksonUtil.toJson(ri);
	}

}