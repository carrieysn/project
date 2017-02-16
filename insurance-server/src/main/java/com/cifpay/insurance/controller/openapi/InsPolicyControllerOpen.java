/**
 * File: InsPolicyControllerOpen.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年12月22日 下午8:57:01
 */
package com.cifpay.insurance.controller.openapi;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cifpay.insurance.base.BaseController;
import com.cifpay.insurance.controller.InsPolicyController;
import com.cifpay.insurance.model.InsPolicy;
import com.cifpay.insurance.model.InsPolicyHolder;
import com.cifpay.insurance.param.DataResponseInfo;
import com.cifpay.insurance.param.ResponseInfo;
import com.cifpay.insurance.param.SystemParamReqInfo;
import com.cifpay.insurance.param.policy.InsurancePolicyInfo;
import com.cifpay.insurance.param.policy.InsurerInfo;
import com.cifpay.insurance.param.policy.PolicyHolderInfo;
import com.cifpay.insurance.service.InsPolicyService;
import com.cifpay.insurance.util.JacksonUtil;
import com.cifpay.starframework.cache.ServiceResultCodeCache;
import com.cifpay.starframework.util.JsonUtil;

/**
 * 
 * @author 张均锋
 *
 */
@Controller
@RequestMapping("/insurance/policy")
public class InsPolicyControllerOpen  extends BaseController {

	private static final Logger LOG = LogManager.getLogger(InsPolicyController.class);
	private static ServiceResultCodeCache resultCode = ServiceResultCodeCache.getInstance();
	@Autowired
	private InsPolicyService insPolicyService;
	
	/**
	 * 方法描述: 查询保单信息 作 者： yeshengnan 日 期： 2015-11-23
	 * 
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/get", method = RequestMethod.POST, produces = { "text/json;charset=UTF-8" })
	@ResponseBody
	public String getPolicy(HttpServletRequest request) {
		ResponseInfo responseOut = null;
		try {
			SystemParamReqInfo spri = populateSystemParamReq(request);// 系统输入参数
			// 商户号
			String merchId = spri.getVendorId();
			if (LOG.isDebugEnabled()) {
				LOG.debug("getPolicy(HttpServletRequest request)");
				LOG.debug(merchId + "开始查询保单信息***");
			}
			InsPolicy insPolicy = insPolicyService.getPolicyByVendorId(merchId);
			//InsInsurerInfo insInsurerInfo = insInsurerInfoService.get(insPolicy.getInsInsurerInfoId());
			InsPolicyHolder insPolicyHolder = insPolicy.getInsPolicyHolder();
			if (null != insPolicy) {
				InsurancePolicyInfo policyInfo = new InsurancePolicyInfo();
				policyInfo.setInsuredAmount(insPolicy.getInsuredAmount());
				policyInfo.setPolicyId(insPolicy.getId());
				policyInfo.setPolicyNo(insPolicy.getPolicyNo());
				policyInfo.setProduct(insPolicy.getProduct());
				policyInfo.setPremium(insPolicy.getPremium());
				policyInfo.setInsuredAmount(insPolicy.getInsuredAmount());
				policyInfo.setPayMode(insPolicy.getPayMode());
				policyInfo.setValidFrom(insPolicy.getValidFrom());
				policyInfo.setValidTo(insPolicy.getValidTo());
				policyInfo.setInsuredName(insPolicy.getInsuredName());
				if (null != insPolicy.getInsInsurerInfo()) {
					InsurerInfo insurerInfo = new InsurerInfo();
					insurerInfo.setInsurername(insPolicy.getInsInsurerInfo().getInsurername());
					insurerInfo.setAddress(insPolicy.getInsInsurerInfo().getAddress());
					insurerInfo.setHotline(insPolicy.getInsInsurerInfo().getHotline());
					policyInfo.setInsurerInfo(insurerInfo);
				}
				PolicyHolderInfo holder = new PolicyHolderInfo();
				holder.setHolderName(insPolicyHolder.getHolderName());
				holder.setHolderType(insPolicyHolder.getHolderType());
				holder.setIdType(insPolicyHolder.getIdType());
				holder.setIdNo(insPolicyHolder.getIdNo());
				holder.setPhone(insPolicyHolder.getPhone());
				holder.setEmail(insPolicyHolder.getEmail());
				holder.setContacts(insPolicyHolder.getContacts());
				policyInfo.setPolicyHolderInfo(holder);
				responseOut = new DataResponseInfo(policyInfo);
			}
		}catch (NullPointerException ex){
			return JsonUtil.getResultJsonString(resultCode.get("biz.policy.notfound"), "保单不存在！");
		} catch (Exception e) {
			responseOut =  handleRespException(e);
		}
		return JacksonUtil.toJson(responseOut);

	}

}