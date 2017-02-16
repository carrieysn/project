/**
 * File: InsBankCardController.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年12月25日 下午3:42:14
 */
package com.cifpay.insurance.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cifpay.insurance.base.BaseController;
import com.cifpay.insurance.cache.InsCache;
import com.cifpay.insurance.model.InsBankCard;
import com.cifpay.insurance.param.DataResponseInfo;
import com.cifpay.insurance.param.ResponseInfo;
import com.cifpay.insurance.param.vendor.LookupBankCardInfo;
import com.cifpay.insurance.param.vendor.LookupBankCardResult;
import com.cifpay.insurance.util.JacksonUtil;

/**
 * 银行卡
 * 
 * @author 张均锋
 *
 */
@Controller
@RequestMapping("/inner/insurance/bank/card")
public class InsBankCardController extends BaseController {

	/** 查找银行卡验证规则标识 **/
	private final static String VALIDATION_LOOKUPBANKCARDINFO ="com.cifpay.ins.vendor.LookupBankCardInfoSet";
	
	/**
	 *  搜索银行卡信息
	 *  
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/lookup", method = RequestMethod.POST, produces = { "text/json;charset=UTF-8" })
	public @ResponseBody String lookupBankcard(HttpServletRequest request) {
		ResponseInfo ri = null;
		try {
			//SystemParamReqInfo spri = populateSystemParamReq(request);
			LookupBankCardInfo ci = populateBizData(request, LookupBankCardInfo.class, VALIDATION_LOOKUPBANKCARDINFO);
			String icn = ci.getInputCardNo();
			List<LookupBankCardResult> bcs = new ArrayList<LookupBankCardResult>();
			InsBankCard bc;
			do {
				bc = InsCache.bankCardCache.getIfPresent(icn);
				if (bc != null) {
					LookupBankCardResult r = new LookupBankCardResult();
					BeanUtils.copyProperties(bc, r);
					bcs.add(r);
					bc = InsCache.bankCardCache.getIfPresent(icn+"_1");//bc不存在,bc1不可能存在
					if (bc != null) {
						r = new LookupBankCardResult();
						BeanUtils.copyProperties(bc, r);
						bcs.add(r);
					}
					break;
				}
				if(icn.length() == 1) {
					break;
				}
				icn = icn.substring(0, icn.length()-1);
			} while (true);
			ri = new DataResponseInfo(bcs);
		} catch (Exception e) {
			ri = handleRespException(e);
		}
		return JacksonUtil.toJson(ri);
	}
	
}
