package com.cifpay.insurance.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cifpay.insurance.base.BaseController;
import com.cifpay.insurance.bean.CreateChargeFeePolicyOrderInfo;
import com.cifpay.insurance.bean.SystemParamReqInfo;
import com.cifpay.insurance.config.WebConstant;
import com.cifpay.insurance.util.JacksonUtil;
import com.cifpay.starframework.adapter.HttpCall;
import com.cifpay.starframework.cache.ServiceResultCodeCache;
import com.cifpay.starframework.util.MathUtil;

/**
 * 
 * 保费充值
 * @author 叶胜南
 *
 */
@Controller
@RequestMapping(value="/insurance/policy/order/charge")
public class OrderChargeController extends BaseController {
	
	private static Logger logger = LoggerFactory.getLogger(OrderChargeController.class);
	private static ServiceResultCodeCache resultCode = ServiceResultCodeCache.getInstance();
	
	/**
	 * 创建保费充值订单
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/display",method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView orderCharge(HttpServletRequest request){
		
		ModelAndView model = new ModelAndView();
		
		try {
			SystemParamReqInfo spr = (SystemParamReqInfo) super.populateSystemParamReq(request);
			//充值保单流水号
			//long  policyId = Long.valueOf(request.getParameter("policyId"));
			//充值金额，单位:分
			//long amount = Long.valueOf(request.getParameter("amount"));
			CreateChargeFeePolicyOrderInfo chargeInfo = super.populateBizData(request, CreateChargeFeePolicyOrderInfo.class, null);
			//chargeInfo.setAmount(amount);
			//chargeInfo.setPolicyId(policyId);
			StringBuffer sub = new StringBuffer();
			sub.append(WebConstant.INSURANCE_SERVER_DOMAIN);
			sub.append("/inner/insurance/policy/order/fee/charge/create");
			Map<String,String> param = new HashMap<String,String>();
			param.put("vendorId", spr.getVendorId());//当前登录商户号
			param.put("data", JacksonUtil.toJson(chargeInfo));
			HttpCall httpCall = new HttpCall();
			Object resultObj = httpCall.httpCall(sub.toString(), WebConstant.REQUEST_POST,param);
			String resultStr = (String)resultObj;
			JSONObject json = JSON.parseObject(resultStr);
			String code = json.getString("code");
			String data = json.getString("data");
			if(StringUtils.isNotEmpty(code) && code.equals("0")){ 
				JSONObject obj = JSONObject.parseObject(data);
				String billNo = obj.getString("billNo");
				//String getPolicyId = obj.getString("policyId");
				model.setViewName("charge/order_charge");
				model.addObject("billNo", billNo);
			    double amount = (double)chargeInfo.getAmount()/100;
				String getamount = MathUtil.formatDouble(amount, "#,##0.00");
				model.addObject("amout", getamount);
				model.addObject("getamout",chargeInfo.getAmount());
			}else if(code.equals(resultCode.get("biz.policy.notfound"))){
				model.setViewName("error");
				model.addObject("errMsg", "保单不存在！");
			} else {
				logger.error(code+"-->"+json.getString("msg"));
				model.setViewName("error");
				model.addObject("errorCode", code);
				model.addObject("errMsg", json.getString("msg"));
			} 
		} catch (Exception e) {
			logger.error("创建充值订单出现异常......", e);
			model.setViewName("error");
			model.addObject("errMsg", "创建充值订单失败！");
		}
		 return model;
		
	}
	
	

}
