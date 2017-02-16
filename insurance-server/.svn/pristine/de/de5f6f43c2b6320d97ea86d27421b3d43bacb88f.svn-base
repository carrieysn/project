/**
 * File: InsCertRefundBillController.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年12月17日 上午9:39:01
 */
package com.cifpay.insurance.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.cifpay.gateway.api.rslt.ReturnRslt;
import com.cifpay.gateway.exception.CPBussinessException;
import com.cifpay.insurance.InsCertRefundBillEnum;
import com.cifpay.insurance.LcOrderStateEnum;
import com.cifpay.insurance.base.BaseController;
import com.cifpay.insurance.base.Page;
import com.cifpay.insurance.bean.LcNoticeResultInfo;
import com.cifpay.insurance.exception.InsuranceBizRuntimeException;
import com.cifpay.insurance.lc.CifpayServiceAdapter;
import com.cifpay.insurance.lc.NoOrderPayInfo;
import com.cifpay.insurance.model.InsCertRefundBill;
import com.cifpay.insurance.model.InsReturnTrace;
import com.cifpay.insurance.model.InsUnopenRefundBill;
import com.cifpay.insurance.param.DataResponseInfo;
import com.cifpay.insurance.param.PageResponseInfo;
import com.cifpay.insurance.param.ResponseInfo;
import com.cifpay.insurance.param.SystemParamReqInfo;
import com.cifpay.insurance.param.cert.GetRefundBillListResult;
import com.cifpay.insurance.param.cert.InsCertRefundBillInfo;
import com.cifpay.insurance.param.cert.InsRefundBillDetailInfo;
import com.cifpay.insurance.param.cert.InsuranceCertNo;
import com.cifpay.insurance.param.cert.InsuranceCertRefundRefuseInfo;
import com.cifpay.insurance.param.cert.InsuranceCertReturnInfo;
import com.cifpay.insurance.param.cert.ReturnTraceListResult;
import com.cifpay.insurance.param.refund.GetRefundBillExportResult;
import com.cifpay.insurance.param.refund.GetRefundBillInfo;
import com.cifpay.insurance.service.InsCertRefundBillService;
import com.cifpay.insurance.service.InsReturnTraceService;
import com.cifpay.insurance.service.InsUnopenRefundBillService;
import com.cifpay.insurance.util.DateUtil;
import com.cifpay.insurance.util.JacksonUtil;
import com.cifpay.insurance.util.LcNoticeResultHelper;
import com.cifpay.insurance.util.StringUtils;

/**
 * 保险证退款单
 * 
 * @author 张均锋
 *
 */
@Controller
@RequestMapping("/inner/insurance/certificate/refund")
public class InsCertRefundBillController extends BaseController {
	/**任务线程池 **/
	@Autowired
	private ThreadPoolTaskExecutor executor;
	@Autowired
	private InsCertRefundBillService insCertRefundBillService;
	@Autowired
	private InsUnopenRefundBillService insUnopenRefundBillService;
	@Autowired
	private InsReturnTraceService insReturnTraceService;
	@Autowired
	private CifpayServiceAdapter cifpayServiceAdapter;
	
	/** 查看保险证验证规则标识 **/
	private final static String VALIDATION_INSURANCECERTNO ="com.cifpay.ins.cert.InsuranceCertNoSet";
	/** 保险证退货验证规则标识 **/
	private final static String VALIDATION_INSURANCECERTRETURNINFO = "com.cifpay.ins.cert.InsuranceCertReturnInfoSet";
	/** 拒绝退款验证规则标识 **/
	private final static String VALIDATION_INSURANCECERTREFUNDREFUSE = "com.cifpay.ins.cert.InsuranceCertRefundRefuseInfoSet";
	/**查看退款单列表**/
	private final static String VALIDATION_GETREFUNDBILLINFO = "com.cifpay.ins.cert.GetRefundBillInfoSet";
	/**
	 *  申请退货
	 *  
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/apply", method = RequestMethod.POST, produces = { "text/json;charset=UTF-8" })
	public @ResponseBody String applyReturnInsuranceCert(HttpServletRequest request) {
		ResponseInfo ri = null;
		try {
			SystemParamReqInfo spri = populateSystemParamReq(request);
			InsuranceCertReturnInfo ci = populateBizData(request, InsuranceCertReturnInfo.class, VALIDATION_INSURANCECERTRETURNINFO);
			//1、新增退款单
			InsCertRefundBill bill = setInsCertRefundBill(spri, ci);
			insCertRefundBillService.applyReturnInsuranceCert(bill);
			//2、自动开出商户银信证。
			NoOrderPayInfo nop = new NoOrderPayInfo();
			nop.setAmount(bill.getRefundAmount());
			nop.setPayerAcctName(bill.getPayerAccName());
			nop.setPayerBankAcctno(bill.getPayerBankAccount());
			nop.setPayerBankCode(bill.getPayerBankCode());
			nop.setPayerCardType("DEBIT_CARD");
			nop.setPayerMobile(bill.getPayerMobilePhone());
			nop.setRecvAcctName(bill.getPayeeAccName());
			nop.setRecvBankAcctno(bill.getPayeeBankAccount());
			nop.setRecvBankCode(bill.getPayeeBankCode());
			nop.setRecvMobile(bill.getPayeeMobilePhone());
			try {
				//if (true) throw new CPBussinessException("233", "mmssss");//TODO for test.
				bill.setPayerType(0);//商户
				tryOpenLc(nop, bill, null);
			} catch (CPBussinessException e) {
				//开证失败，把开证失败的退款单记录至“未开证退款单”表
				bill.setLcTradeResult(String.format("尝试商户开证出现异常！errorCode：%s；errorMsg：%s", e.getErrcode(), e.getMessage()));
				insCertRefundBillService.updateOpenFailState(bill);
				logger.warn(String.format("尝试商户开证出现异常！24小时内如果商户账户还是开证不成功，那么将由保险公司代开证[certNo：%s；errorCode：%s；errorMsg：%s]", bill.getInsuranceCertNo(), e.getErrcode(), e.getMessage()), e);
			}
			ri = new ResponseInfo();
		} catch (Exception e) {
			ri = handleRespException(e);
		}
		return JacksonUtil.toJson(ri);
	}
	
	/**
	 * 开证、异步收证和履约
	 * @param nop
	 * @param upBill
	 * @param unopenBill 未开证信息
	 * @throws CPBussinessException
	 */
	private void tryOpenLc(NoOrderPayInfo nop, InsCertRefundBill upBill, InsUnopenRefundBill unopenBill) throws CPBussinessException {
		// 商户开证
		LcNoticeResultInfo openLCRST = null;
		nop.setNoticeUrl(configProperties.get("insurance-server.domain")+"/inner/insurance/certificate/refund/notice");
		ReturnRslt rst = cifpayServiceAdapter.noOrderOpenLc(nop);
		openLCRST = LcNoticeResultHelper.convertLcNoticeResult(rst);
		// 开证成功！
		if (rst.getOrderState().equals(LcOrderStateEnum.CREDIT_OPENED.val)) {
			upBill.setLcState(rst.getLcState());
			upBill.setLcTradeDate(DateUtil.parseDate(rst.getTradeDate(), "yyyy-MM-dd HH:mm:ss"));
			upBill.setModifiedTime(new Date());
			upBill.setLcId(rst.getLcId());
			upBill.setLcNo(rst.getLcNo());
			upBill.setLcOrderId(rst.getOrderId());
			upBill.setLcOrderState(rst.getOrderState());
			upBill.setBillStatus(InsCertRefundBillEnum.TO_REFUND.val);
			if (unopenBill == null) {
				insCertRefundBillService.updateOpenSuccessState(upBill);
			} else {//二次开证
				insCertRefundBillService.updateVendorSecondOpenSuccessState(upBill, unopenBill);
			}
			//进行异步收证和履约
			tryRecvAndSendAsyn(openLCRST, nop, upBill);
		} else {//?
			throw new InsuranceBizRuntimeException(resultCode.get("biz.insurance.cert.refund.lc.open.fail"), "保险证开证失败！[订单号："+rst.getOrderId()+", orderState："+rst.getOrderState()+"]");
		}
	}
	
	/**
	 * 异步收证和履约
	 * 
	 * @param bill
	 */
	private void tryRecvAndSendAsyn(final LcNoticeResultInfo openLCRST, final NoOrderPayInfo nop, InsCertRefundBill upBill) {
		/*executor.execute(new Runnable() {
			@Override
			public void run() {*/
				ReturnRslt rst = null;
				try {
					// 1、收证
					rst = cifpayServiceAdapter.receive(openLCRST.getLcId(), nop.getPayerBankCode());
					// 成功！
					LcNoticeResultInfo lcNoticeResultInfo = LcNoticeResultHelper.convertLcNoticeResult(rst);
					insCertRefundBillService.noticeInsCertRefundBillTradeState(lcNoticeResultInfo);
				} catch (CPBussinessException e) {
					upBill.setLcTradeResult(String.format("收证失败！errorCode：%s；errorMsg：%s", e.getErrcode(), e.getMessage()));
					insCertRefundBillService.updateRefundFailState(upBill);
					logger.error(String.format("收证失败！[errorCode：%s；errorMsg：%s]", e.getErrcode(), e.getMessage()), e);
					return;
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
				try {
					// 2、履约
					rst = cifpayServiceAdapter.send(openLCRST.getLcId(), openLCRST.getOrderId());
					// 成功！
					LcNoticeResultInfo lcNoticeResultInfo = LcNoticeResultHelper.convertLcNoticeResult(rst);
					insCertRefundBillService.noticeInsCertRefundBillTradeState(lcNoticeResultInfo);
				} catch (CPBussinessException e) {
					upBill.setLcTradeResult(String.format("履约失败！errorCode：%s；errorMsg：%s", e.getErrcode(), e.getMessage()));
					insCertRefundBillService.updateRefundFailState(upBill);
					logger.error(String.format("履约失败！[errorCode：%s；errorMsg：%s]", e.getErrcode(), e.getMessage()), e);
					return;
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
		/*	}
		});*/
	}
	
	/**
	 * 解付
	 * @param upBill
	 */
	private void tryPay(InsCertRefundBill upBill) {
		ReturnRslt rslt = null;
		try {
			rslt = cifpayServiceAdapter.paying(upBill.getLcId(), upBill.getLcOrderId(), upBill.getRefundAmount());// 没有异常即为成功
			LcNoticeResultInfo lcNoticeResultInfo = LcNoticeResultHelper.convertLcNoticeResult(rslt);
    		insCertRefundBillService.noticeInsCertRefundBillTradeState(lcNoticeResultInfo);
		} catch (CPBussinessException e) {
			upBill.setVersion(null);//?
			upBill.setBillStatus(InsCertRefundBillEnum.REFUND_FAIL.val);//退款失败！
			upBill.setLcTradeResult(String.format("申请解付失败！errorCode：%s；errorMsg：%s", e.getErrcode(), e.getMessage()));
			insCertRefundBillService.updateRefundFailState(upBill);
			throw new InsuranceBizRuntimeException(resultCode.get("biz.insurance.cert.refund.submit.fail"), String.format("提交申请解付失败！[errorCode：%s；errorMsg：%s]", e.getErrcode(), e.getMessage()), e);
		}
	}
	
	/**
	 * 拒绝解付
	 * 
	 * @param upBill
	 */
	private void refusePay(InsCertRefundBill upBill) {
		ReturnRslt rslt = null;
		try {//退回银信证
			rslt = cifpayServiceAdapter.reBackLc(upBill.getLcId());// 没有异常即为成功
    		LcNoticeResultInfo lcNoticeResultInfo = LcNoticeResultHelper.convertLcNoticeResult(rslt);
    		insCertRefundBillService.noticeInsCertRefundBillTradeState(lcNoticeResultInfo);
		} catch (CPBussinessException e) {
			upBill.setVersion(null);
			upBill.setLcTradeResult(String.format("退回银信证失败！errorCode：%s；errorMsg：%s", e.getErrcode(), e.getMessage()));
			insCertRefundBillService.updateRefundFailState(upBill);
			throw new InsuranceBizRuntimeException(resultCode.get("biz.insurance.cert.refund.lc.reback.fail"), String.format("退回银信证失败！[errorCode：%s；errorMsg：%s]", e.getErrcode(), e.getMessage()), e);
		}
		
	}
	
	/** 增加退款申请单 **/
	private InsCertRefundBill setInsCertRefundBill(SystemParamReqInfo spri, InsuranceCertReturnInfo icr) {
		InsCertRefundBill bill = new InsCertRefundBill();
		bill.setInsuranceCertNo(icr.getInsuranceCertNo());
		bill.setVendorId(spri.getVendorId());
		bill.setLogisticsBillNo(icr.getLogisticsBillNo());
		bill.setLogisticsCompany(icr.getLogisticsCompany());
		bill.setPayerType(0);//商户
		//收款人信息
		bill.setPayeeBankAccount(icr.getPayeeBankAccount());
		bill.setPayeeBankCode(icr.getPayeeBankCode());
		bill.setPayeeBankName(icr.getPayeeBankName());
		bill.setPayeeMobilePhone(icr.getPayeeMobilePhone());
		bill.setPayeeAccName(icr.getPayeeAccName());
		return bill;
	}
	
	/**
	 * 方法描述:确认退款  作 者： yeshengnan 日 期： 2015-12-01
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/confirm", method = RequestMethod.POST, produces = { "text/json;charset=UTF-8" })
	public @ResponseBody String confirmRefund(HttpServletRequest request) {
		ResponseInfo ri = null;
		try {
			//SystemParamReqInfo spri = populateSystemParamReq(request);
			InsuranceCertNo certNo = populateBizData(request, InsuranceCertNo.class,VALIDATION_INSURANCECERTNO);
			//1、标识退款单为退款中状态
			InsCertRefundBill bill = insCertRefundBillService.confirmRefund(certNo.getInsuranceCertNo());
			//2、执行提交银行解付指令.
			tryPay(bill);
			ri = new ResponseInfo();
		} catch (Exception e) {
			ri = handleRespException(e);
		}
		return JacksonUtil.toJson(ri);
	}
	
	/**
	 * 方法描述:拒绝退款  作 者： yeshengnan 日 期： 2015-12-02
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/refuse", method = RequestMethod.POST, produces = { "text/json;charset=UTF-8" })
	public @ResponseBody String refuseRefund(HttpServletRequest request) {
		ResponseInfo ri = null;
		try {
			//SystemParamReqInfo spri = populateSystemParamReq(request);
			InsuranceCertRefundRefuseInfo refundRefuse = populateBizData(request, InsuranceCertRefundRefuseInfo.class,VALIDATION_INSURANCECERTREFUNDREFUSE);
			//1、直接标记为拒绝退款状态
			InsCertRefundBill bill = insCertRefundBillService.refuseRefund(refundRefuse);
			//2、执行退回指令.
			if (bill != null) {
				refusePay(bill);
			}
			ri = new ResponseInfo();
		} catch (Exception e) {
			ri = handleRespException(e);
		}
		return JacksonUtil.toJson(ri);
	}
	
	/**
	 * 退款交易状态通知
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
			final LcNoticeResultInfo ret = LcNoticeResultHelper.convertLcNoticeResult(bankDataMap);
			executor.execute(new Runnable() {//异步处理
			    @Override
				public void run() {
			    	try {
			    		insCertRefundBillService.noticeInsCertRefundBillTradeState(ret);
					} catch (Exception e) {
						logger.error("调用退款交易状态通知接口出现异常！", e);
					}
			    	
				}
			});
			ri = new ResponseInfo();
		} catch (Exception e) {
			ri = handleRespException(e);
		}
		return JacksonUtil.toJson(ri);
	}
	
	/**
	 *  过期的未开证的退款单检测，由保险公司代开证。
	 *  <br>注：在23h内将触发商家再次开证，如果超出23h将由保险公司代开证。
	 *  <br>本接口由定时服务器调用。
	 *  
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/expired/unopen/check", method = RequestMethod.POST, produces = { "text/json;charset=UTF-8" })
	public @ResponseBody String checkExpiredUnopenRefundBill(HttpServletRequest request) {
		ResponseInfo ri = null;
		try {
			List<InsUnopenRefundBill> unexpiredList = insUnopenRefundBillService.getExpiredInsUnopenRefundBills();
			for (InsUnopenRefundBill bill: unexpiredList) {//TODO 规模数据处理
				InsCertRefundBill newBill = null;
				try {
					 //独立事务新增保险公司退款单。
					newBill = insCertRefundBillService.addInsCompanyInsCertRefundBill(bill);
					// 自动空单开证
					NoOrderPayInfo nop = new NoOrderPayInfo();
					nop.setAmount(newBill.getRefundAmount());
					nop.setPayerAcctName(newBill.getPayerAccName());
					nop.setPayerBankAcctno(newBill.getPayerBankAccount());
					nop.setPayerBankCode(newBill.getPayerBankCode());
					nop.setPayerCardType(configProperties.get("ins.cardType"));
					nop.setPayerMobile(newBill.getPayerMobilePhone());
					nop.setRecvAcctName(newBill.getPayeeAccName());
					nop.setRecvBankAcctno(newBill.getPayeeBankAccount());
					nop.setRecvBankCode(newBill.getPayeeBankCode());
					nop.setRecvMobile(newBill.getPayeeMobilePhone());
					tryOpenLc(nop, newBill, null);//开保险公司证bill.setPayerType(1)
				} catch (CPBussinessException e) {
					//TODO 保险公司开证失败后处理 ？
					newBill.setLcTradeResult(String.format("尝试保险公司开证出现异常！errorCode：%s；errorMsg：%s", e.getErrcode(), e.getMessage()));
					insCertRefundBillService.updateRefundFailState(newBill);
					logger.warn(String.format("尝试保险公司开证出现异常！[certNo：%s；errorCode：%s；errorMsg：%s]", bill.getInsuranceCertNo(), e.getErrcode(), e.getMessage()), e);
				} catch (Exception e) {//异常继续下一个
					logger.warn("新增保险公司退款单业务失败！[保险证号："+bill.getInsuranceCertNo()+"]", e);
				}
			}
			ri = new ResponseInfo();
		} catch (Exception e) {
			ri = handleRespException(e);
		}
		return JacksonUtil.toJson(ri);
	}
	
	/**
	 *  未过期的未开证的退款单检测，继续由商户开证。
	 *  <br>在23h内将触发商家再次开证，如果超出23h将由保险公司代开证。
	 *  <br>本接口由定时服务器调用。
	 *  
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/unexpired/unopen/check", method = RequestMethod.POST, produces = { "text/json;charset=UTF-8" })
	public @ResponseBody String checkUnexpiredUnopenRefundBill(HttpServletRequest request) {
		ResponseInfo ri = null;
		try {
			List<InsUnopenRefundBill> unexpiredList = insUnopenRefundBillService.getUnexpiredInsUnopenRefundBills();
			for (InsUnopenRefundBill bill: unexpiredList) {//TODO 规模数据处理
				// 自动空单开证
				NoOrderPayInfo nop = new NoOrderPayInfo();
				nop.setAmount(bill.getRefundAmount());
				nop.setPayerAcctName(bill.getPayerAccName());
				nop.setPayerBankAcctno(bill.getPayerBankAccount());
				nop.setPayerBankCode(bill.getPayerBankCode());
				nop.setPayerCardType("DEBIT_CARD");
				nop.setPayerMobile(bill.getPayerMobilePhone());
				nop.setRecvAcctName(bill.getPayeeAccName());
				nop.setRecvBankAcctno(bill.getPayeeBankAccount());
				nop.setRecvBankCode(bill.getPayeeBankCode());
				nop.setRecvMobile(bill.getPayeeMobilePhone());
				InsCertRefundBill rbill = null;
				try {
					rbill = new InsCertRefundBill();
					rbill.setId(bill.getCertRefundBillId());
					rbill.setInsuranceCertId(bill.getInsuranceCertId());
					rbill.setInsuranceCertNo(bill.getInsuranceCertNo());
					rbill.setPayerType(0);//商户的证
					tryOpenLc(nop, rbill, bill);//二次开证
				} catch (CPBussinessException e) {
					//addReturnTrace(rbill, WebConstant.RT_VENDOR_OPEN_FAIL);//无须重复记录在跟踪表
					rbill.setLcTradeResult(String.format("尝试商户开证出现异常！errorCode：%s；errorMsg：%s", e.getErrcode(), e.getMessage()));
					insCertRefundBillService.updateRefundFailState(rbill);
					logger.warn(String.format("尝试商户开证出现异常！24小时内如果商户账户还是开证不成功，那么将由保险公司代开证[certNo：%s；errorCode：%s；errorMsg：%s]", bill.getInsuranceCertNo(), e.getErrcode(), e.getMessage()), e);
				} catch (Exception e) {
					logger.error("尝试商户开证出现异常！[保险证号："+bill.getInsuranceCertNo()+"]"+e.getMessage(), e);
				}
			}
			ri = new ResponseInfo();
		} catch (Exception e) {
			ri = handleRespException(e);
		}
		return JacksonUtil.toJson(ri);
	}
	
	@RequestMapping(value = "/bill/get", method = RequestMethod.POST, produces = { "text/json;charset=UTF-8" })
	public @ResponseBody String getInsCertRefundBill(HttpServletRequest request) {
		ResponseInfo ri = null;
		try {
			//SystemParamReqInfo spri = populateSystemParamReq(request);
			InsuranceCertNo certNo = populateBizData(request, InsuranceCertNo.class,VALIDATION_INSURANCECERTNO);
			InsCertRefundBill bill = insCertRefundBillService.getInsCertRefundBillByCertNo(certNo.getInsuranceCertNo());
			if (bill == null) {
				return JacksonUtil.toJson(new DataResponseInfo(resultCode.get("biz.insurance.cert.refund.bill.notfound"), "保险证退款单不存在！"));
			}
			InsCertRefundBillInfo bi = new InsCertRefundBillInfo();
			BeanUtils.copyProperties(bill, bi);
			ri = new DataResponseInfo(bi);
		} catch (Exception e) {
			ri = handleRespException(e);
		}
		return JacksonUtil.toJson(ri);
	}
	/**
	 * 查询退款单列表
	 * 日期：2016-01-15 17:00
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/bill/list/get", method = RequestMethod.POST, produces = { "text/json;charset=UTF-8" })
	public @ResponseBody String getInsCertRefundBillList(HttpServletRequest request) {
		
		PageResponseInfo ri = null;
		try {
			//SystemParamReqInfo spri = populateSystemParamReq(request);
			GetRefundBillInfo param = populateBizData(request, GetRefundBillInfo.class,VALIDATION_GETREFUNDBILLINFO);
			Page<InsCertRefundBill> page = new Page<InsCertRefundBill>();
			page.setPageNo(param.getPageNo());
			page.setPageSize(param.getPageSize());
		    insCertRefundBillService.getInsCertRefundBills(param, page);
		    List<GetRefundBillListResult> listResult = new ArrayList<GetRefundBillListResult>();
		    for(InsCertRefundBill bill: page.getResult()){
		    	GetRefundBillListResult result = new GetRefundBillListResult();
				result.setBillStatus(bill.getBillStatus());
				result.setInsuranceCertNo(bill.getInsuranceCertNo());
				result.setLcOpenTime(bill.getLcOpenTime());
				result.setLcPayedTime(bill.getLcPayedTime());
				result.setRefundAmount(bill.getRefundAmount());
				listResult.add(result);
			}
			ri = new PageResponseInfo();
			ri.setRecordCount(page.getRecordCount());
			ri.setData(listResult);
		} catch (Exception e) {
			ResponseInfo res = handleRespException(e);
			return JacksonUtil.toJson(res);
		}
		return JacksonUtil.toJson(ri);
		
	}
	/**
	 * 查询退款单详情
	 * 日期：2016-01-16 15:30
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/bill/certInfo/get", method = RequestMethod.POST, produces = { "text/json;charset=UTF-8" })
	public @ResponseBody String getInsCertRefundBillInfo(HttpServletRequest request) {
		
		ResponseInfo ri = null;
		try {
			//SystemParamReqInfo spri = populateSystemParamReq(request);
			InsuranceCertNo certNo = populateBizData(request, InsuranceCertNo.class,VALIDATION_INSURANCECERTNO);
			InsCertRefundBill bill = insCertRefundBillService.getInsCertRefundBillByCertNo(certNo.getInsuranceCertNo());
			if (bill == null) {
				return JacksonUtil.toJson(new DataResponseInfo(resultCode.get("biz.insurance.cert.refund.bill.notfound"), "保险证退款单不存在！"));
			}
			List<InsReturnTrace> traceList =  insReturnTraceService.getListByCertNo(certNo.getInsuranceCertNo());
		    List<ReturnTraceListResult> tracelistResult = new ArrayList<ReturnTraceListResult>();
		    for(InsReturnTrace insReturnTrace:traceList){
		    	ReturnTraceListResult trace = new ReturnTraceListResult();
		    	trace.setDescription(insReturnTrace.getDescription());
		    	trace.setInsuranceCertNo(certNo.getInsuranceCertNo());
		    	trace.setOptTime(DateUtil.formatDate(insReturnTrace.getOptTime(), "yyyy-MM-dd HH:mm:ss"));
		    	trace.setOptType(insReturnTrace.getOptType());
		    	tracelistResult.add(trace);
		    }
			
			 InsRefundBillDetailInfo info = new InsRefundBillDetailInfo();
			 GetRefundBillListResult result = new GetRefundBillListResult();
			 BeanUtils.copyProperties(bill, result);
			 info.setBillInfo(result);
			 info.setTracelistResult(tracelistResult);
			 ri = new DataResponseInfo(info);
		} catch (Exception e) {
			ri = handleRespException(e);
		}
		return JacksonUtil.toJson(ri);
	   
	}
	
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/list/export",method = RequestMethod.POST, produces = { "text/json;charset=UTF-8" })
	public @ResponseBody String exportRefundBillList(HttpServletRequest request) {
		PageResponseInfo ri = new PageResponseInfo();
		try {
			JSONObject dataObj = (JSONObject)request.getAttribute("data");
			GetRefundBillInfo bean = null;
			if (dataObj != null) {
				bean = (GetRefundBillInfo)dataObj.toJavaObject(dataObj, GetRefundBillInfo.class);
			} else {
				bean = (GetRefundBillInfo)JacksonUtil.fromJson(request.getParameter("data"), GetRefundBillInfo.class);
			}
			List<InsCertRefundBill> list = insCertRefundBillService.getInsCertRefundBills(bean,null);
			if(list != null && list.size()>0){
				List<GetRefundBillExportResult> resultList = new ArrayList<GetRefundBillExportResult>();
				for(InsCertRefundBill bill: list){
					GetRefundBillExportResult result = new GetRefundBillExportResult();
					result.setInsuranceCertNo(bill.getInsuranceCertNo());
					result.setBillStatus(String.valueOf(bill.getBillStatus()));
					result.setLcOpenTime(DateUtil.formatDate(bill.getLcOpenTime(), "yyyy-MM-dd HH:mm:ss"));
					result.setLcPayedTime(DateUtil.formatDate(bill.getLcPayedTime(), "yyyy-MM-dd HH:mm:ss"));
					result.setRefundAmount(StringUtils.fmtMicrometer(String.format("%.2f",(Double)(bill.getRefundAmount()/100d)).toString()));
					resultList.add(result);
				}
				ri.setRecordCount(list.size());
				ri.setData(resultList);
			}
		} catch (Exception e) {
			ResponseInfo res = handleRespException(e);
			return JacksonUtil.toJson(res);
		}
		return JacksonUtil.toJson(ri);
	}
	
	
}
