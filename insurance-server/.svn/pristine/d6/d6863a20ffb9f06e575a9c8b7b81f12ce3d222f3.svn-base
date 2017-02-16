package com.cifpay.insurance.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.cifpay.insurance.base.BaseController;
import com.cifpay.insurance.base.Page;
import com.cifpay.insurance.model.InsGearingAdjustHis;
import com.cifpay.insurance.model.InsPolicy;
import com.cifpay.insurance.model.InsPolicyHolder;
import com.cifpay.insurance.param.DataResponseInfo;
import com.cifpay.insurance.param.PageResponseInfo;
import com.cifpay.insurance.param.ResponseInfo;
import com.cifpay.insurance.param.SystemParamReqInfo;
import com.cifpay.insurance.param.policy.CreateInsurancePolicyInfo;
import com.cifpay.insurance.param.policy.CreateInsurancePolicyResult;
import com.cifpay.insurance.param.policy.GetPolicyExportResult;
import com.cifpay.insurance.param.policy.GetPolicyListInfo;
import com.cifpay.insurance.param.policy.GetPolicyListResult;
import com.cifpay.insurance.param.policy.InsurancePolicyInfo;
import com.cifpay.insurance.param.policy.InsurerInfo;
import com.cifpay.insurance.param.policy.PolicyHolderInfo;
import com.cifpay.insurance.service.InsGearingAdjustHisService;
import com.cifpay.insurance.service.InsInsurerInfoService;
import com.cifpay.insurance.service.InsPolicyHolderService;
import com.cifpay.insurance.service.InsPolicyService;
import com.cifpay.insurance.util.DateUtil;
import com.cifpay.insurance.util.JacksonUtil;
import com.cifpay.insurance.util.PolicyUtil;
import com.cifpay.insurance.util.StringUtils;
import com.cifpay.starframework.cache.ServiceResultCodeCache;
import com.cifpay.starframework.model.ServiceResult;
import com.cifpay.starframework.util.JsonUtil;

/**
 * 
 * 
 * @author 叶胜南
 *
 */
@Controller
@RequestMapping("/inner/insurance/policy")
public class InsPolicyController extends BaseController {

	private static final Logger LOG = LogManager.getLogger(InsPolicyController.class);
	private static ServiceResultCodeCache resultCode = ServiceResultCodeCache.getInstance();
	@Autowired
	private InsPolicyService insPolicyService;
	@Autowired
	private InsPolicyHolderService insPolicyHolderService;
	@Autowired
	private InsInsurerInfoService insInsurerInfoService;
	@Autowired
	private InsGearingAdjustHisService insGearingAdjustHisService;
	
	/** 创建保险证验证规则标识 **/
	private final static String VALIDATION_CREATEINSINSURANCEPOLICYINFO = "com.cifpay.ins.policy.CreateInsurancePolicyInfoSet";
	/** 查询保单列表规则 **/
	private final static String VALIDATION_GETPOLICYLISTINFO = "com.cifpay.ins.policy.GetPolicyListInfoSet";
	
	
	
	/**
	 * 方法描述:  判断商户是否投保
	 * @return 
	 * 返回类型： Map<String,String>
	 */
	@RequestMapping(value = "/isBuyInsure",method = RequestMethod.POST, produces = { "text/json;charset=UTF-8" })
	@ResponseBody
	public String  validateBuyPolicy(HttpServletRequest request) {
		ResponseInfo responseOut = null;
		try {
			SystemParamReqInfo spri = populateSystemParamReq(request);// 系统输入参数
			InsPolicy getinsPolicy = insPolicyService.getPolicyByVendorId(spri.getVendorId());
			responseOut = new ResponseInfo();
			if (null != getinsPolicy) {
				responseOut.setCode(resultCode.get("biz.policy.repeat"));
				responseOut.setMsg("您已投保，无需重复投保！");
			}
		} catch (Exception e) {
			responseOut =  handleRespException(e);
		}
		return  JacksonUtil.toJson(responseOut);
		
	} 
	
	
	/**
	 * 方法描述: 创建保单 作 者： yeshengnan 日 期： 2015-11-19
	 * 
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = { "text/json;charset=UTF-8" })
	@ResponseBody
	public String createPolicy(HttpServletRequest request) {
		
		ResponseInfo responseOut = null;
		try {
			SystemParamReqInfo spri = populateSystemParamReq(request);// 系统输入参数
			CreateInsurancePolicyInfo policyInfo = populateBizData(request, CreateInsurancePolicyInfo.class,VALIDATION_CREATEINSINSURANCEPOLICYINFO);
			// jsonStr="{\"product\":\"交还险\",\"holderName\":\"张三\",\"holderType\":\"个人\",\"idType\":\"身份证\",\"idNo\":\"233\",\"contacts\":\"小红\",\"phone\":\"13566777\",\"email\":\"rer@163.com\",\"insuredName\":\"被保人\",\"insurancePeriod\":\"2\",\"premium\":\"10000\",\"insuredId\":\"id\"}";
			InsPolicy getinsPolicy = insPolicyService.getPolicyByVendorId(spri.getVendorId());
			if (null != getinsPolicy) {
				return JsonUtil.getResultJsonString(resultCode.get("biz.policy.repeat"), "您已投保，无需重复投保！");
			}
			if (LOG.isDebugEnabled()) {
				LOG.debug("createInsPolicy(ServletRequest request)");
				LOG.debug("商户vendorId:" + spri.getVendorId() + "开始投保***");
			}
			PolicyHolderInfo policyHolderInfo = policyInfo.getPolicyHolderInfo();
			// 保单信息封装
			InsPolicy insPolicy = new InsPolicy();
			insPolicy.setVendorId(spri.getVendorId());
			insPolicy.setProduct(policyInfo.getProduct());
			insPolicy.setPolicyNo(PolicyUtil.getRandomPolicyNo(7));// 保单号 默认
			String insuredName = policyInfo.getInsuredName();
			if (StringUtils.isEmpty(insuredName)){
				insuredName = configProperties.get("insure.insuredName");
				if (StringUtils.isEmpty(insuredName)) {
					throw new IllegalArgumentException("未配置[insure.insuredName]键值！");
				}
			}
			insPolicy.setInsuredName(insuredName);// 被保人为空 默认
			insPolicy.setInsurancePeriod(policyInfo.getInsurancePeriod());
			insPolicy.setPremium(policyInfo.getPremium());
			insPolicy.setInsureDate(new Date());
			insPolicy.setStatus(0);// 设置保单状态未生效 默认
			insPolicy.setInsurerInfoId(1l);// 设置保险人ID为1 默认
			insPolicy.setInsuredid(policyInfo.getInsuredid());// 保险标的
			// 投保人信息封装
			InsPolicyHolder insPolicyHolder = new InsPolicyHolder();
			insPolicyHolder.setVendorId(spri.getVendorId());// 商户号
			insPolicyHolder.setHolderType(policyHolderInfo.getHolderType());
			insPolicyHolder.setHolderName(policyHolderInfo.getHolderName());
			insPolicyHolder.setPhone(policyHolderInfo.getPhone());
			insPolicyHolder.setIdType(policyHolderInfo.getIdType());
			insPolicyHolder.setIdNo(policyHolderInfo.getIdNo());
			insPolicyHolder.setContacts(policyHolderInfo.getContacts());
			insPolicyHolder.setPhone(policyHolderInfo.getPhone());
			insPolicyHolder.setEmail(policyHolderInfo.getEmail());
			insPolicyHolder.setCreatedTime(new Date());
			insPolicy.setInsPolicyHolder(insPolicyHolder);
			ServiceResult<CreateInsurancePolicyResult> ret = insPolicyService.savePolicy(insPolicy);
			responseOut = new DataResponseInfo(ret.getCode(), ret.getObj());
		} catch (Exception e) {
			responseOut =  handleRespException(e);
		}
		return JacksonUtil.toJson(responseOut);
	}

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
				policyInfo.setInitPremium(insPolicy.getInitPremium());
				policyInfo.setInsureDate(insPolicy.getInsureDate());
				policyInfo.setGearing(insPolicy.getGearing());
				policyInfo.setCreditScore(insPolicy.getCreditScore());
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
	
	/**
	 * 获取保单列表信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/list/get",method = RequestMethod.POST, produces = { "text/json;charset=UTF-8" })
	public @ResponseBody String getInsPolicyList(HttpServletRequest request) {
		PageResponseInfo ri = null;
		try {
			GetPolicyListInfo bean = populateBizData(request, GetPolicyListInfo.class,VALIDATION_GETPOLICYLISTINFO);
			Page<InsPolicy> page = new Page<InsPolicy>();
			page.setPageNo(bean.getPageNo());
			page.setPageSize(bean.getPageSize());
			insPolicyService.getInsPolicyList(bean, page);
			List<GetPolicyListResult> resultList = new ArrayList<GetPolicyListResult>();
			for(InsPolicy ip: page.getResult()){
				GetPolicyListResult result = new GetPolicyListResult();
				result.setInsuredAmount(ip.getInsuredAmount());
				result.setVendorId(ip.getVendorId());
				result.setPolicyId(ip.getId());
				result.setPolicyNo(ip.getPolicyNo());
				result.setProduct(ip.getProduct());
				result.setInitPremium(ip.getInitPremium());
				result.setPremium(ip.getPremium());
				result.setInsuredAmount(ip.getInsuredAmount());
				result.setPayMode(ip.getPayMode());
				result.setInsuredName(ip.getInsuredName());
				if(ip.getInsureDate() != null) result.setInsureDate(ip.getInsureDate().getTime());
				result.setStatus(ip.getStatus());
				result.setGearing(ip.getGearing());
				if(ip.getAuditTime() != null) result.setAuditTime(ip.getAuditTime().getTime());
				if (null != ip.getInsInsurerInfo()) {
					InsurerInfo insurerInfo = new InsurerInfo();
					insurerInfo.setInsurername(ip.getInsInsurerInfo().getInsurername());
					insurerInfo.setAddress(ip.getInsInsurerInfo().getAddress());
					insurerInfo.setHotline(ip.getInsInsurerInfo().getHotline());
					result.setInsurerInfo(insurerInfo);
				}
				PolicyHolderInfo holder = new PolicyHolderInfo();
				InsPolicyHolder h = ip.getInsPolicyHolder();
				holder.setHolderName(h.getHolderName());
				holder.setHolderType(h.getHolderType());
				holder.setIdType(h.getIdType());
				holder.setIdNo(h.getIdNo());
				holder.setPhone(h.getPhone());
				holder.setEmail(h.getEmail());
				holder.setContacts(h.getContacts());
				result.setPolicyHolderInfo(holder);
				resultList.add(result);
			}
			ri = new PageResponseInfo();
			ri.setRecordCount(page.getRecordCount());
			ri.setData(resultList);
		} catch (Exception e) {
			ResponseInfo res = handleRespException(e);
			return JacksonUtil.toJson(res);
		}
		return JacksonUtil.toJson(ri);
	}
	
	/**
	 * 获取保单列表信息
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/list/export",method = RequestMethod.POST, produces = { "text/json;charset=UTF-8" })
	public @ResponseBody String exportInsPolicyList(HttpServletRequest request) {
		PageResponseInfo ri = new PageResponseInfo();
		try {
			JSONObject dataObj = (JSONObject)request.getAttribute("data");
			GetPolicyListInfo bean = null;
			if (dataObj != null) {
				bean = (GetPolicyListInfo)dataObj.toJavaObject(dataObj, GetPolicyListInfo.class);
			} else {
				bean = (GetPolicyListInfo)JacksonUtil.fromJson(request.getParameter("data"), GetPolicyListInfo.class);
			}
			
			List<InsPolicy> list = insPolicyService.getInsPolicyList(bean,null);
			
			//装载数据
			List<GetPolicyExportResult> resultList = new ArrayList<GetPolicyExportResult>();
			if(list != null && list.size()>0){
				for(InsPolicy ip: list){
					GetPolicyExportResult result = new GetPolicyExportResult();
					result.setPolicyNo(ip.getPolicyNo());
					result.setHolderName(ip.getInsPolicyHolder().getHolderName());
					result.setIdNo(ip.getInsPolicyHolder().getIdNo());
					result.setInitPremium(StringUtils.fmtMicrometer(String.format("%.2f",(Double)(ip.getInitPremium()/100d))).toString());
					result.setInsureDate(DateUtil.formatDate(ip.getInsureDate(), "yyyy-MM-dd HH:mm:ss"));
					resultList.add(result);
				}
			}
			ri.setRecordCount(resultList.size());
			ri.setData(resultList);
		} catch (Exception e) {
			ResponseInfo res = handleRespException(e);
			return JacksonUtil.toJson(res);
		}
		return JacksonUtil.toJson(ri);
	}
	
	/**
	 * 获取信用调整历史
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/gearingAdjustHis/list/get",method = RequestMethod.POST, produces = { "text/json;charset=UTF-8" })
	public @ResponseBody String getGearingAdjustHisList(HttpServletRequest request) {
		DataResponseInfo ri = null;
		LOG.debug("开始获取信用调整历史");
		try {
			String policyId = request.getParameter("policyId");
			List<InsGearingAdjustHis> temp = insGearingAdjustHisService.getListByPolicyId(policyId);
			ri = new DataResponseInfo();
			ri.setData(temp);
		} catch (Exception e) {
			ResponseInfo res = handleRespException(e);
			return JacksonUtil.toJson(res);
		}
		LOG.debug("返回信用调整历史");
		return JacksonUtil.toJson(ri);
	}
	
	/**
	 * 信用调整
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/gearingAdjust",method = RequestMethod.POST, produces = { "text/json;charset=UTF-8" })
	public @ResponseBody String gearingAdjust(HttpServletRequest request) {
		ResponseInfo responseOut = new ResponseInfo();
		LOG.debug("开始信用调整");
		try {
			Long insUserId = Long.valueOf(request.getParameter("insUserId"));
			Long policyId = Long.valueOf(request.getParameter("policyId"));
			Integer gearingRuleId = Integer.valueOf(request.getParameter("gearingRuleId"));
			
			InsPolicy insPolicy = insPolicyService.adjustGearing(insUserId,policyId,gearingRuleId);
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
				policyInfo.setInitPremium(insPolicy.getInitPremium());
				policyInfo.setInsureDate(insPolicy.getInsureDate());
				policyInfo.setGearing(insPolicy.getGearing());
				policyInfo.setCreditScore(insPolicy.getCreditScore());
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
			logger.error(ex);
			return JsonUtil.getResultJsonString(resultCode.get("biz.policy.notfound"), "保单不存在！");
		} catch (Exception e) {
			responseOut =  handleRespException(e);
		}
		return JacksonUtil.toJson(responseOut);
	}

}
