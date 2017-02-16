package com.cifpay.insurance.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cifpay.insurance.bean.CreateInsurancePolicyInfo;
import com.cifpay.insurance.bean.GetPolicyOrderInfo;
import com.cifpay.insurance.bean.InsurancePolicyInfo;
import com.cifpay.insurance.bean.ReturnCreatInsure;
import com.cifpay.insurance.config.WebConstant;
import com.cifpay.insurance.model.InsUser;
import com.cifpay.insurance.util.DateUtil;
import com.cifpay.insurance.util.JacksonUtil;
import com.cifpay.insurance.util.StringUtils;
import com.cifpay.starframework.adapter.HttpCall;
import com.cifpay.starframework.cache.ConfigPropertiesCache;
import com.cifpay.starframework.cache.ServiceResultCodeCache;
import com.cifpay.starframework.util.MathUtil;


/**
 * 
 * 类 描 述： 投保控制类
 * 创 建 人：yeshengnan
 * 创建时间： 2015-12-4
 *
 */
@Controller
@RequestMapping(value="/api/insure")
public class PolicyController {
	
	private static Logger logger = LoggerFactory.getLogger(PolicyController.class);
	private static ServiceResultCodeCache resultCode = ServiceResultCodeCache.getInstance();
	private static ConfigPropertiesCache configProperties = ConfigPropertiesCache.getInstance();
	//private static ThreadPoolTaskExecutor executor = (ThreadPoolTaskExecutor)SpringUtil.getBean("threadPoolTaskExecutor");
	private InsUser loginUser;
	/**
	 * 
	 * 方法描述: 进入投保页面
	 * @return 
	 * 返回类型： ModelAndView
	 */
	@RequestMapping(value = "/transTobuyPage")
	public  ModelAndView  transTobuyPage(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		loginUser = (InsUser) request.getSession().getAttribute(WebConstant.SESSIONUSER);
		if(null == loginUser){
			model.setViewName("index");
		}else{
			model.setViewName("ins_policy");
		}
		
		return model;
	} 
	
	/**
	 * 
	 * 方法描述:  判断商户是否投保
	 * @return 
	 * @throws IOException 
	 */
	@ResponseBody
	@RequestMapping(value = "/validateBuy")
	public void  validateBuyPolicy(HttpServletRequest request, HttpServletResponse response)  {
		JSONObject jsonResult = new JSONObject();
		//Map<String, String> map = new HashMap<String,String>();
		//String serverUrl = configProperties.get("insurance-server.domain")+"/insurance/policy/isBuyInsure?vendorId="+6655;
		try {
			StringBuilder inputData = new StringBuilder();
			inputData.append(WebConstant.INSURANCE_SERVER_DOMAIN);
			inputData.append("/inner/insurance/policy/isBuyInsure");
			Map<String,String> para = new HashMap<String,String>();
			loginUser = (InsUser) request.getSession().getAttribute(WebConstant.SESSIONUSER);
			if(loginUser == null ){
				jsonResult.put("msg", "11");
			}else{
		        para.put("vendorId", loginUser.getId().toString());//当前登录商户号
				HttpCall httpCall = new HttpCall();
				Object result = httpCall.httpCall(inputData.toString(), WebConstant.REQUEST_POST,para);
				String ret = (String)result;
				if(ret != null && ret.length() > 0) {
					JSONObject json = JSON.parseObject(ret); 
					String code = json.getString("code");
					if( "20001".equals(code)){ 
						jsonResult.put("msg", "1");
					}else{
						jsonResult.put("msg", "0");
					}
						
				}
			}
		    response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print(jsonResult.toString());
		} catch (Exception e) {
			logger.error("验证商户是否投保失败！",e);
		}
	} 
	
	/**
	 * 方法描述: 投保
	 * @return 
	 * 返回类型：Map<String,Object>
	 */
	@RequestMapping(value = "/buyInsurance")
	@ResponseBody
	public  Map<String,Object>  buyInsurance(CreateInsurancePolicyInfo createInsurancePolicyInfo,HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String,Object>();
		try {
			createInsurancePolicyInfo.setProduct(configProperties.get("ins.productName"));
			long getpremium = createInsurancePolicyInfo.getPremium()*100;
			createInsurancePolicyInfo.setPremium(getpremium);
			String policyJson = JacksonUtil.toJson(createInsurancePolicyInfo);
			//String serverUrl = configProperties.get("insurance-server.domain")+"/insurance/policy/create?data="+policyJson+"&vendorId=6655";
			StringBuilder inputData = new StringBuilder(); 
			inputData.append(WebConstant.INSURANCE_SERVER_DOMAIN);
			inputData.append("/inner/insurance/policy/order/create");
			Map<String,String> param = new HashMap<String,String>();
			param.put("data",policyJson);
			loginUser = (InsUser) request.getSession().getAttribute(WebConstant.SESSIONUSER);
			param.put("vendorId",loginUser.getId().toString());//当前登录商户号，默认
			HttpCall httpCall = new HttpCall();
			Object result = httpCall.httpCall(inputData.toString(), WebConstant.REQUEST_POST,param);
			String ret = (String)result;
			if(ret != null && ret.length() > 0) {
				JSONObject json = JSON.parseObject(ret);
				String code = json.getString("code");
				String data = json.getString("data");
				
				if(StringUtils.isNotEmpty(code)&& "0".equals(code)){
					JSONObject obj = JSONObject.parseObject(data);
					ReturnCreatInsure insure = new ReturnCreatInsure();
					insure.setBillNo(obj.getString("billNo"));
					long premium = Long.valueOf(obj.getLongValue("premium"));
					String getamount = MathUtil.formatDouble((double)premium/100, "#,##0.00");
					insure.setPremium(premium);
					insure.setAmount(getamount);
					insure.setProduct(configProperties.get("ins.productName"));//险种名称 默认
					map.put("msg", WebConstant.SUCCESS);
					map.put("data", insure);
				}else if("20001".equals(code)){
					map.put("msg", WebConstant.REAPT);
				}else{
					map.put("msg", WebConstant.FAIL);
				}
			  }
		} catch (NumberFormatException e) {
			map.put("msg", WebConstant.FAIL);
			logger.error("订单生成失败！", e);
		}
		
		return map;
     }
		
    /**
     * 查看保单信息
     * @param request
     * @param response
     * @return
     */
	@ResponseBody
	@RequestMapping(value="/showPolicyInfo")
	public ModelAndView  getPolicyInfo(HttpServletRequest request, HttpServletResponse response){
		ModelAndView model = new ModelAndView();
		try {
			InsurancePolicyInfo policyInfo = null;
			StringBuffer sub = new StringBuffer();
			sub.append(WebConstant.INSURANCE_SERVER_DOMAIN);
			sub.append("/inner/insurance/policy/get");
			Map<String,String> param = new HashMap<String,String>();
			loginUser = (InsUser) request.getSession().getAttribute(WebConstant.SESSIONUSER);
			param.put("vendorId", loginUser.getId().toString());
			HttpCall httpCall = new HttpCall();
			Object resultObj = httpCall.httpCall(sub.toString(), WebConstant.REQUEST_POST,param);
			String resultStr = (String)resultObj;
			JSONObject json = JSON.parseObject(resultStr);
			String code = json.getString("code");
			String data = json.getString("data");
			model.setViewName("policy_info");
			if(StringUtils.isNotEmpty(code)&& "0".equals(code)){
		    	policyInfo = JacksonUtil.populateBizData(InsurancePolicyInfo.class, data);
		    	long premium = policyInfo.getPremium()/100;
		    	policyInfo.setPremium(premium);
		    	long insuredAmount = policyInfo.getInsuredAmount()/100;
		    	policyInfo.setInsuredAmount(insuredAmount);
		    	model.addObject("policyInfo", policyInfo);
		    }else if(resultCode.get("biz.policy.notfound").equals(code)){
		    	model.addObject("policyInfo", null);
		    }
		} catch (Exception e) {
			logger.error("查询保单失败！", e);
		}
		return  model;
	}
	/**
	 * 验证是否支付成功并返回保单信息
	 * @param orderNo 订单号
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getPolicyInfos")
	public Map<String,Object>  getPolicyInfoAfterPay(String orderNo,HttpServletRequest request){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		InsurancePolicyInfo policyInfo = null;
		try {
			StringBuffer sub = new StringBuffer();
			sub.append(WebConstant.INSURANCE_SERVER_DOMAIN);
			sub.append("/inner/insurance/policy/order/get");
			loginUser = (InsUser) request.getSession().getAttribute(WebConstant.SESSIONUSER);
			String vendorId = loginUser.getId().toString();//当前登录商户号
			GetPolicyOrderInfo orderInfo = new GetPolicyOrderInfo();
			orderInfo.setBillNo(orderNo);
			Map<String,String> param = new HashMap<String,String>();
			param.put("vendorId", loginUser.getId().toString());
			param.put("data", JacksonUtil.toJson(orderInfo));
			HttpCall httpCall = new HttpCall();
			Object resultObj = httpCall.httpCall(sub.toString(), WebConstant.REQUEST_POST,param);
			String resultStr = (String)resultObj;
			JSONObject json = JSON.parseObject(resultStr);
			String code = json.getString("code");
			String data = json.getString("data");
			if(StringUtils.isNotEmpty(code) && code.equals("0")){
			JSONObject obj = JSONObject.parseObject(data);
		    String status = obj.getString("status");
				if(StringUtils.isNotEmpty(status) && status.equals("1")){//已支付
					StringBuffer sbffer = new StringBuffer();
					sbffer.append(WebConstant.INSURANCE_SERVER_DOMAIN);
					sbffer.append("/inner/insurance/policy/get");
					Map<String,String> params = new HashMap<String,String>();
					params.put("vendorId", vendorId);
					HttpCall httpCalls = new HttpCall();
					Object objs = httpCalls.httpCall(sbffer.toString(), WebConstant.REQUEST_POST,params);
					String objStr = (String)objs;
					JSONObject jsonObj = JSON.parseObject(objStr);
					String getcode = jsonObj.getString("code");
					String getdata = jsonObj.getString("data");
					if(StringUtils.isNotEmpty(getcode)&& "0".equals(getcode)){
				    	policyInfo = JacksonUtil.populateBizData(InsurancePolicyInfo.class, getdata);
				    	long premium = policyInfo.getPremium()/100;
				    	policyInfo.setPremium(premium);
				    	long insuredAmount = policyInfo.getInsuredAmount()/100;
				    	policyInfo.setInsuredAmount(insuredAmount);
				    	String validFrom = DateUtil.formatDate(policyInfo.getValidFrom(), "yyyy-MM-dd HH:mm");
				    	String validTo = DateUtil.formatDate(policyInfo.getValidTo(), "yyyy-MM-dd HH:mm");
				    	resultMap.put("msg", "0");
				    	resultMap.put("resultData", policyInfo);
				    	resultMap.put("validFrom", validFrom);
				    	resultMap.put("validTo", validTo);
				    }
				}else{
					logger.debug("支付失败！");
					resultMap.put("msg", "1");
				}
			}else{
				logger.debug("支付后查询订单错误代码："+code);
				resultMap.put("msg", "2");
			}
		} catch (Exception e) {
			logger.error("查询保单失败！", e);
			resultMap.put("msg", "2");
		}
		return  resultMap;
	}
	
	@RequestMapping(value="/downLoad")
	@ResponseBody
	public void downloadFile(HttpServletRequest request,HttpServletResponse response) throws IOException{
		loginUser = (InsUser) request.getSession().getAttribute(WebConstant.SESSIONUSER);
		String root = request.getSession().getServletContext().getRealPath("/");
		String fileName = request.getParameter("fileName");
		String path = "/download/";
		 //客服端使用保存文件的对话框  
        response.setHeader("Content-disposition", "attachment;filename="+fileName);  
        //通知客服文件的MIME类型  
        response.setContentType("application/msword"); 
		//response.addHeader("Content-Disposition", "filename=\"" + fileName + "\"");
		try
		{
		java.io.OutputStream os = response.getOutputStream();
		java.io.FileInputStream fis = new java.io.FileInputStream(root + path + fileName);
		byte[] b = new byte[1024];
		int i = 0;
		while ( (i = fis.read(b)) > 0 )
		{
		os.write(b, 0, i);
		}
		
		fis.close();
		os.flush();
		os.close();
		}catch ( Exception e ){
			e.printStackTrace();
		} 
    }
	


} 