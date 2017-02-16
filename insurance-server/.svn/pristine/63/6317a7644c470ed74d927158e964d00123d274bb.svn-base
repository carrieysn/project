/**
 * File: InsPolicyOrderController.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年12月8日 下午8:39:37
 */
package com.cifpay.insurance.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cifpay.gateway.api.rslt.OpenRslt;
import com.cifpay.gateway.api.rslt.ReturnRslt;
import com.cifpay.gateway.exception.CPBussinessException;
import com.cifpay.insurance.base.BaseController;
import com.cifpay.insurance.base.Page;
import com.cifpay.insurance.lc.CifpayServiceAdapter;
import com.cifpay.insurance.lc.OrderPayInfo;
import com.cifpay.insurance.model.InsPolicyOrder;
import com.cifpay.insurance.model.InsPolicyOrderItem;
import com.cifpay.insurance.param.DataResponseInfo;
import com.cifpay.insurance.param.PageResponseInfo;
import com.cifpay.insurance.param.ResponseInfo;
import com.cifpay.insurance.param.SystemParamReqInfo;
import com.cifpay.insurance.param.policy.CreateChargeFeePolicyOrderInfo;
import com.cifpay.insurance.param.policy.CreateChargeFeePolicyOrderResult;
import com.cifpay.insurance.param.policy.CreatePolicyOrderInfo;
import com.cifpay.insurance.param.policy.CreatePolicyOrderResult;
import com.cifpay.insurance.param.policy.GetPolicyOrderInfo;
import com.cifpay.insurance.param.policy.GetPolicyOrderListInfo;
import com.cifpay.insurance.param.policy.GetPolicyOrderListResult;
import com.cifpay.insurance.param.policy.GetPolicyOrderResult;
import com.cifpay.insurance.param.policy.NoticeFrontPolicyOrderInfo;
import com.cifpay.insurance.param.policy.NoticeFrontPolicyOrderResult;
import com.cifpay.insurance.param.policy.PolicyHolderInfo;
import com.cifpay.insurance.param.policy.PolicyOrderPayInfo;
import com.cifpay.insurance.service.InsPolicyOrderService;
import com.cifpay.insurance.service.InsPolicyService;
import com.cifpay.insurance.util.DateUtil;
import com.cifpay.insurance.util.JacksonUtil;
import com.cifpay.insurance.util.StringUtils;

/**
 * 
 * @author 张均锋
 *
 */
@Controller
@RequestMapping("/inner/insurance/policy/order")
public class InsPolicyOrderController extends BaseController {
	private static final Logger logger = LogManager.getLogger(InsPolicyOrderController.class);
	
	@Autowired
	private InsPolicyOrderService insPolicyOrderService;
	@Autowired
	private InsPolicyService insPolicyService;
	/**任务线程池 **/
	@Autowired
	private ThreadPoolTaskExecutor executor;
	@Autowired
	private CifpayServiceAdapter cifpayServiceAdapter;
	
	/** 创建商户订单验证规则标识 **/
	private final static String VALIDATION_CREATEPOLICYORDERINFO = "com.cifpay.ins.policy.CreatePolicyOrderInfoSet";
	/** 创建充值订单验证规则标识 **/
	private final static String VALIDATION_CREATECHARGEFEEPOLICYORDERINFO = "com.cifpay.ins.policy.CreateChargeFeePolicyOrderInfoSet";
	/** 查询保费充值记录验证规则标识 **/
	private final static String VALIDATION_GETPOLICYORDERLIST = "com.cifpay.ins.policy.GetPolicyOrderListInfoSet";
	/** 订单前端通知验证规则标识 **/
	private final static String VALIDATION_NOTICEFRONTPOLICYORDERINFO = "com.cifpay.ins.policy.NoticeFrontPolicyOrderInfoSet";
	/** 查询投保/保费充值信息验证规则标识 **/
	private final static String VALIDATION_GETPOLICYORDERINFO = "com.cifpay.ins.policy.GetPolicyOrderInfoSet";
	/** 商户订单支付信息验证规则标识**/
	private final static String VALIDATION_POLICYORDERPAYINFOSET="com.cifpay.ins.policy.PolicyOrderPayInfoSet";

	/**
	 * 创建投保订单
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = { "text/json;charset=UTF-8" })
	@ResponseBody
	public String createPolicyOrder(HttpServletRequest request) {
		ResponseInfo responseOut = null;
		try {
			SystemParamReqInfo spri = populateSystemParamReq(request);// 系统输入参数
			CreatePolicyOrderInfo cpo = populateBizData(request, CreatePolicyOrderInfo.class,VALIDATION_CREATEPOLICYORDERINFO);
			PolicyHolderInfo policyHolderInfo = cpo.getPolicyHolderInfo();
			// 投保订单信息封装
			InsPolicyOrder ipo = new InsPolicyOrder();
			ipo.setVendorId(spri.getVendorId());// 商户号
			ipo.setAmount(cpo.getPremium());
			ipo.setType(1);//投保订单
			
			InsPolicyOrderItem ipoi = new InsPolicyOrderItem();//订单项
			ipo.setInsPolicyOrderItem(ipoi);
			ipoi.setProduct(cpo.getProduct());
			String insuredName = cpo.getInsuredName();
			if (StringUtils.isEmpty(insuredName)){
				insuredName = configProperties.get("insure.insuredName");
				if (StringUtils.isEmpty(insuredName)) {
					throw new IllegalArgumentException("未配置[insure.insuredName]键值！");
				}
			}
			ipoi.setInsuredName(insuredName);// 被保人为空 默认
			ipoi.setInsurancePeriod(cpo.getInsurancePeriod());
			ipoi.setInsuredid(cpo.getInsuredid());// 保险标的
			// 投保人信息封装
			//InsPolicyHolder insPolicyHolder = new InsPolicyHolder();
			ipoi.setHolderType(policyHolderInfo.getHolderType());
			ipoi.setHolderName(policyHolderInfo.getHolderName());
			ipoi.setPhone(policyHolderInfo.getPhone());
			ipoi.setIdType(policyHolderInfo.getIdType());
			ipoi.setIdNo(policyHolderInfo.getIdNo());
			ipoi.setContacts(policyHolderInfo.getContacts());
			ipoi.setPhone(policyHolderInfo.getPhone());
			ipoi.setEmail(policyHolderInfo.getEmail());
			
			insPolicyOrderService.createInsPolicyOrder(ipo);
			CreatePolicyOrderResult ret = new CreatePolicyOrderResult();
			ret.setBillNo(ipo.getBillNo());
			ret.setPremium(ipo.getAmount());
			responseOut = new DataResponseInfo(ret);
		} catch (Exception e) {
			responseOut =  handleRespException(e);
		}
		return JacksonUtil.toJson(responseOut);
	}
	
	/**
	 * 创建充值订单
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/fee/charge/create", method = RequestMethod.POST, produces = { "text/json;charset=UTF-8" })
	@ResponseBody
	public String createChargeFeePolicyOrder(HttpServletRequest request) {
		ResponseInfo responseOut = null;
		try {
			SystemParamReqInfo sysParam = populateSystemParamReq(request);
			CreateChargeFeePolicyOrderInfo feeCharge = populateBizData(request,CreateChargeFeePolicyOrderInfo.class,VALIDATION_CREATECHARGEFEEPOLICYORDERINFO);
			// 保单ID
			long policyId = feeCharge.getPolicyId();
			long amount = feeCharge.getAmount();

			if (logger.isDebugEnabled()) {
				logger.debug("createPolicyFeeCharge(HttpServletRequest request)");
				logger.debug("对保单ID:policyid=" + policyId + "进行充值>>>>>");
			}
			InsPolicyOrder ipo = new InsPolicyOrder();
			ipo.setVendorId(sysParam.getVendorId());
			ipo.setPolicyId(policyId);
			ipo.setAmount(amount);
			insPolicyOrderService.createChargeFeePolicyOrder(ipo);
			CreateChargeFeePolicyOrderResult chargeResult = new CreateChargeFeePolicyOrderResult();
			chargeResult.setBillNo(ipo.getBillNo());
			chargeResult.setPolicyId(ipo.getPolicyId());
			responseOut = new DataResponseInfo(chargeResult);
		} catch (Exception e) {
			responseOut =  handleRespException(e);
		}
		return JacksonUtil.toJson(responseOut);
	}
	
	/**
	 * 查询订单列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/list/get", method = RequestMethod.POST, produces = { "text/json;charset=UTF-8" })
	@ResponseBody
	public String getPolicyOrderList(HttpServletRequest request) {
		PageResponseInfo ri = null;
		try {
			populateSystemParamReq(request);
			GetPolicyOrderListInfo gc = populateBizData(request, GetPolicyOrderListInfo.class, VALIDATION_GETPOLICYORDERLIST);
			if (logger.isDebugEnabled()) {
				logger.debug("getPolicyFeeCharge(HttpServletRequest request)");
				logger.debug("查询保费充值记录信息>>>>");
			}
			if (gc.getEndDate() != null) {
				Date to = DateUtil.parseDate(gc.getEndDate(), "yyyy-MM-dd");
				gc.setEndDate(DateUtil.formatDate(DateUtil.addDay(to, 1), "yyyy-MM-dd"));
			}
			Page<InsPolicyOrder> page = new Page<InsPolicyOrder>();
			page.setPageNo(gc.getPageNo());
			page.setPageSize(gc.getPageSize());
			insPolicyOrderService.getInsPolicyOrderList(gc, page);
			List<GetPolicyOrderListResult> rets = new ArrayList<GetPolicyOrderListResult>();
			for (InsPolicyOrder ipo: page.getResult()) {
				GetPolicyOrderListResult ret = new GetPolicyOrderListResult();
				ret.setAmount(ipo.getAmount());
				ret.setBeforePremium(ipo.getBeforePremium());
				ret.setBillNo(ipo.getBillNo());
				ret.setOrderTime(ipo.getCreatedTime());
				ret.setStatus(ipo.getStatus());
				rets.add(ret);
			}
			ri = new PageResponseInfo();
			ri.setRecordCount(page.getRecordCount());
			ri.setData(rets);
		} catch (Exception e) {
			ResponseInfo responseOut = handleRespException(e);
			return JacksonUtil.toJson(responseOut);
		}
		return JacksonUtil.toJson(ri);
	}
	
	/**
	 * 订单交易状态通知
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/notice", method = RequestMethod.POST, produces = { "text/json;charset=UTF-8" })
	@ResponseBody
	public String noticePolicyOrder(HttpServletRequest request) {
		ResponseInfo ri = null;
		try {
			Map<String, Object> bankDataMap = decodeBankData(request);
			//Map<String, Object> bankDataMap = JacksonUtil.fromJson(request.getParameter("data"), Map.class);//for test
			logger.debug("订单交易通知接收到数据："+bankDataMap);
			final InsPolicyOrder ipo = new InsPolicyOrder();
			ipo.setBillNo((String)bankDataMap.get("orderId"));
			ipo.setLcId((String)bankDataMap.get("lcId"));
			ipo.setLcNo((String)bankDataMap.get("lcNo"));
			ipo.setLcState((String)bankDataMap.get("lcState"));
			ipo.setTradeDate(DateUtil.parseDate((String)bankDataMap.get("tradeDate"), "yyyy-MM-dd HH:mm:ss"));
			executor.execute(new Runnable() {//异步处理
			    @Override
				public void run() {
					insPolicyOrderService.noticePolicyOrder(ipo);
				}
			});
			ri = new ResponseInfo();
		} catch (Exception e) {
			ri = handleRespException(e);
		}
		return JacksonUtil.toJson(ri);
	}
	
	/**
	 * 开商户订单银信证
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/lc/open", method = RequestMethod.POST, produces = { "text/json;charset=UTF-8" })
	@ResponseBody
	public String openLcPolicyOrder(HttpServletRequest request) {
		ResponseInfo ri = null;
		try {
			//SystemParamReqInfo sysParam = populateSystemParamReq(request);
			PolicyOrderPayInfo pop = populateBizData(request,PolicyOrderPayInfo.class,VALIDATION_POLICYORDERPAYINFOSET);
			//结果返回
			OpenRslt rst = tryOpenLc4VendorOrder(pop);
			ri = new DataResponseInfo(rst);
		} catch (CPBussinessException be) {
			logger.error("调用银信证接口失败！", be);
			ri = new ResponseInfo();
			ri.setCode(resultCode.get("common.system.error"));
			ri.setMsg(String.format("调用银信证接口失败！[errorCode：%s，errMsg：%s]",be.getErrcode(), be.getMessage()));
		} catch (Exception e) {
			ri =  handleRespException(e);
		}
		return JacksonUtil.toJson(ri);
	}
	
	/**
	 * 订单交易状态前端通知
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/front/notice", method = RequestMethod.POST, produces = { "text/json;charset=UTF-8" })
	@ResponseBody
	public String noticeFrontPolicyOrder(HttpServletRequest request) {
		ResponseInfo ri = null;
		InsPolicyOrder ipo = null;
		try {
			//SystemParamReqInfo sysParam = populateSystemParamReq(request);
			//NoticeFrontPolicyOrderInfo nfp = populateBizData(request,NoticeFrontPolicyOrderInfo.class,VALIDATION_NOTICEFRONTPOLICYORDERINFO);
			Map<String, Object> bankDataMap = frontDecodeBankData(request);
			//Map<String, Object> bankDataMap = JacksonUtil.fromJson(request.getParameter("data"), Map.class);//for test
			logger.debug("前端通知接收到数据："+bankDataMap);
			String lcId = (String) bankDataMap.get("lcId");//返回银信证流水号
		    String orderId = (String) bankDataMap.get("orderId");//订单号
		    String lcNo = (String) bankDataMap.get("lcNo");//银信证编号
			String lcState = (String) bankDataMap.get("lcState");//银信证状态
			String tradeDate = (String) bankDataMap.get("tradeDate");//银行交易时间
			NoticeFrontPolicyOrderInfo nfp = new NoticeFrontPolicyOrderInfo();
			nfp.setLcId(lcId);
			nfp.setBillNo(orderId);
			nfp.setLcNo(lcNo);
			nfp.setLcState(lcState);
			nfp.setTradeDate(DateUtil.parseDate((String)tradeDate, "yyyy-MM-dd HH:mm:ss"));
			
			ipo = tryReceiveLc(nfp);//收证
			ipo = tryCompletePerformance(nfp);//履约
			ipo = tryPay(nfp, ipo.getAmount());//解付
			//结果返回
			NoticeFrontPolicyOrderResult ret = new NoticeFrontPolicyOrderResult();
			ret.setVendorId(ipo.getVendorId());
			ret.setBillNo(ipo.getBillNo());
			ret.setStatus(ipo.getStatus());
			ret.setAmount(ipo.getAmount());
			ri = new DataResponseInfo(ret);
		} catch (CPBussinessException be) {
			logger.error("调用银信证接口失败！", be);
			ri = new ResponseInfo();
			ri.setCode(resultCode.get("common.system.error"));
			ri.setMsg(String.format("调用银信证接口失败！[errorCode：%s，errMsg：%s]",be.getErrcode(), be.getMessage()));
		} catch (Exception e) {
			ri =  handleRespException(e);
		}
		return JacksonUtil.toJson(ri);
	}
	
	/**
	 * 银信证开证
	 * 
	 * @param paramMap
	 * @return
	 * @throws CPBussinessException
	 */
	private OpenRslt tryOpenLc4VendorOrder(PolicyOrderPayInfo pop) throws CPBussinessException {
		OrderPayInfo pi = new OrderPayInfo();
		pi.setOpenBankCode(pop.getBankCode());
		pi.setOrderId(pop.getOrderNo());
		pi.setAmount(pop.getPremium());
		pi.setPayerMobile(pop.getMobilePhone());
		pi.setReturnUrl(pop.getReturnUrl());
		pi.setNoticeUrl(configProperties.get("insurance-server.domain")+"/inner/insurance/policy/order/notice");
		pi.setMrchOrderUrl(pop.getMrchOrderUrl());
		pi.setOrderAmount(pi.getAmount());
		OpenRslt openRslt = cifpayServiceAdapter.openLcWithOrder(pi);
		return openRslt;
	}
	
	/**
	 * 收证处理 
	 * 
	 * @param sysParam
	 * @param nfp
	 * @return
	 */
	private InsPolicyOrder tryReceiveLc(NoticeFrontPolicyOrderInfo nfp) {//尝试收证
		InsPolicyOrder ipo = new InsPolicyOrder();
		//ipo.setVendorId(sysParam.getVendorId());
		ipo.setBillNo(nfp.getBillNo());
		ipo.setLcId(nfp.getLcId());
		ipo.setLcNo(nfp.getLcNo());
		ipo.setLcState(nfp.getLcState());//TODO 必须只能是收证状态的调用，其它状态不能调用！
		ipo.setTradeDate(nfp.getTradeDate());
		insPolicyOrderService.noticePolicyOrder(ipo); //收证
		return ipo;
	}
	
	/**
	 * 履约处理
	 * 
	 * @param sysParam
	 * @param nfp
	 * @return
	 * @throws CPBussinessException
	 */
	private InsPolicyOrder tryCompletePerformance(NoticeFrontPolicyOrderInfo nfp) throws CPBussinessException{
		//调用履约方法
		ReturnRslt rslt = cifpayServiceAdapter.send(nfp.getLcId(), nfp.getBillNo()); //没有异常即为成功
		InsPolicyOrder ipo = new InsPolicyOrder();
		//ipo.setVendorId(sysParam.getVendorId());
		ipo.setBillNo(rslt.getOrderId());
		ipo.setLcId(rslt.getLcId());
		ipo.setLcNo(rslt.getLcNo());
		ipo.setLcState(rslt.getLcState());
		ipo.setTradeDate(DateUtil.parseDate(rslt.getTradeDate(), "yyyy-MM-dd HH:mm:ss"));
		insPolicyOrderService.noticePolicyOrder(ipo); //状态通知
		return ipo;
	}
	
	/**
	 * 解付处理
	 * 
	 * @param sysParam
	 * @param nfp
	 * @param amount
	 * @return
	 * @throws CPBussinessException
	 */
	private InsPolicyOrder tryPay(NoticeFrontPolicyOrderInfo nfp, Long amount) throws CPBussinessException{
		ReturnRslt rslt = cifpayServiceAdapter.paying(nfp.getLcId(), nfp.getBillNo(), amount);//解付
		InsPolicyOrder ipo = new InsPolicyOrder();
		//ipo.setVendorId(sysParam.getVendorId());
		ipo.setBillNo(rslt.getOrderId());
		ipo.setLcId(rslt.getLcId());
		ipo.setLcNo(rslt.getLcNo());
		ipo.setLcState(rslt.getLcState());
		ipo.setTradeDate(DateUtil.parseDate(rslt.getTradeDate(), "yyyy-MM-dd HH:mm:ss"));
		insPolicyOrderService.noticePolicyOrder(ipo); //状态通知
		return ipo;
	}
	
	/**
	 * 查询订单详细信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/get", method = RequestMethod.POST, produces = { "text/json;charset=UTF-8" })
	@ResponseBody
	public String getPolicyOrderInfo(HttpServletRequest request) {
		ResponseInfo ri = null;
		try {
			populateSystemParamReq(request);
			GetPolicyOrderInfo gc = populateBizData(request, GetPolicyOrderInfo.class, VALIDATION_GETPOLICYORDERINFO);
			InsPolicyOrder ipo = insPolicyOrderService.getInsPolicyOrderByBillNo(gc.getBillNo());
			if (ipo == null) {
				ri = new ResponseInfo();
				ri.setCode(resultCode.get("biz.policy.order.notfound"));
				ri.setMsg("投保保单(充值)订单号不存在！");
				return JacksonUtil.toJson(ri);
			}
			GetPolicyOrderResult ret = new GetPolicyOrderResult();
			ret.setAmount(ipo.getAmount());
			ret.setBillNo(ipo.getBillNo());
			ret.setPolicyId(ipo.getPolicyId());
			ret.setStatus(ipo.getStatus());
			ret.setType(ipo.getType());
			return JacksonUtil.toJson(new DataResponseInfo(ret));
		} catch (Exception e) {
			ResponseInfo responseOut = handleRespException(e);
			return JacksonUtil.toJson(responseOut);
		}
	}
}
