package com.cifpay.insurance.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cifpay.insurance.bean.PayOrderParam;
import com.cifpay.insurance.config.WebConstant;
import com.cifpay.insurance.util.JacksonUtil;
import com.cifpay.starframework.adapter.HttpCall;
/**
 * 
 * 类 名 称： PayInsuranceController
 * 类 描 述： 银信证支付
 * 创 建 人：yeshengnan
 * 创建时间： 2015-12-9
 *
 */
@Controller
@RequestMapping(value="/chargePay")
public class PayOrderChargeController {
	
	private static Logger logger = LoggerFactory.getLogger(PayOrderChargeController.class);
    
    /**
     * 充值银信证开证
     * @param param
     * @param req
     * @return
     */
    @RequestMapping(value="/openLcOrder",method=RequestMethod.POST)
    public ModelAndView openLcOrder(PayOrderParam po, HttpServletRequest req){
    	ModelAndView model = new ModelAndView();
    	StringBuilder serverUrl = new StringBuilder(); 
    	serverUrl.append(WebConstant.INSURANCE_SERVER_DOMAIN);
    	serverUrl.append("/inner/insurance/policy/order/lc/open");
    	po.setReturnUrl(WebConstant.INSURANCE_INSURE_DOMAIN+"/chargePay/return");
    	po.setMrchOrderUrl(WebConstant.INSURANCE_INSURE_DOMAIN+"/chargePay/return");//??
    	Map<String, String> payParam = new HashMap<String, String>();
    	//payParam.put("vendorId", getVendorId(req));
    	payParam.put("data", JacksonUtil.toJson(po));
    	try {
			HttpCall httpCall = new HttpCall();
			String ret = (String)httpCall.httpCall(serverUrl.toString(), WebConstant.REQUEST_POST, payParam);
			if(ret != null && ret.length() > 0) {
				JSONObject json = JSON.parseObject(ret);
				String code = json.getString("code");
				if("0".equals(code)) {
					JSONObject jd = json.getJSONObject("data");
					if (jd != null) {
						model.setViewName("trans_pay");
						model.addObject("openRslt", json.getJSONObject("data"));
						return model;
					}
				}
				model.addObject("errorCode", code);
				model.addObject("errMsg", String.format("银信证开证失败！%s", json.getString("msg")));
			} else {
				model.addObject("errMsg", "银信证开证失败！");
			}
			model.setViewName("error");
			return model;
		} catch (Exception e) {
			logger.error("银信证预开证失败！"+e.getMessage(), e);
			model.setViewName("error");
			model.addObject("errMsg", e.getMessage());
			return model;
		}
    } 
    
    /**
     * 开证后回调进行履约解付
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/return")
	public ModelAndView returnPay(HttpServletRequest request, HttpServletResponse response){
		ModelAndView model = new ModelAndView();
		try {
			//页面回调的数据需要进行base64解码 
			StringBuilder inputData = new StringBuilder(); 
			inputData.append(WebConstant.INSURANCE_SERVER_DOMAIN);
			inputData.append("/inner/insurance/policy/order/front/notice");
			Map<String,String> param = new HashMap<String,String>();
			param.put("data",request.getParameter("data"));
			param.put("key",request.getParameter("key"));
			param.put("mac",request.getParameter("mac"));
			HttpCall httpCall = new HttpCall();
			String ret = (String)httpCall.httpCall(inputData.toString(), WebConstant.REQUEST_POST,param);
			if(ret != null && ret.length() > 0) {
				JSONObject json = JSON.parseObject(ret);
				String code = json.getString("code");
				if("0".equals(code)) {
					JSONObject jd = json.getJSONObject("data");
					int status = jd.getIntValue("status");//0-未支付；1-已支付
					if(status == 1){//已支付
					    model.setViewName("charge/charge_success");
					    return model;
					}
				}
				model.addObject("errorCode", code);
				model.addObject("errMsg", String.format("支付失败！%s", json.getString("msg")));
			} else {
				model.addObject("errMsg", "支付失败！");
			}
			model.setViewName("error");
		    return	model;
		} catch (Exception e) {
			logger.error("系统出现异常", e);
			model.setViewName("error");
	    	model.addObject("errMsg", "请求错误！系统异常");
	    	return model;
		}
	}

}
