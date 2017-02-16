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
import com.cifpay.insurance.model.InsReturnTrace;
import com.cifpay.insurance.model.InsSalesOrderItems;
import com.cifpay.insurance.param.DataResponseInfo;
import com.cifpay.insurance.param.ResponseInfo;
import com.cifpay.insurance.param.SystemParamReqInfo;
import com.cifpay.insurance.param.cert.InsuranceCertNo;
import com.cifpay.insurance.param.cert.ReturnGoodsResult;
import com.cifpay.insurance.param.cert.ReturnTraceListResult;
import com.cifpay.insurance.service.InsPolicyHolderService;
import com.cifpay.insurance.service.InsReturnTraceService;
import com.cifpay.insurance.service.InsSalesOrderItemsService;
import com.cifpay.insurance.util.JacksonUtil;
import com.cifpay.starframework.util.DateUtil;


/**
 * 
 * 类 名 称： InsReturnTraceController
 * 类 描 述： 退款，退货
 * 创 建 人： yeshengnan
 * 创建时间： 2015-12-01
 */
@Controller
@RequestMapping("/inner/insurance/certificate/return/trace")
public class InsReturnTraceController extends BaseController{
	
	private static final Logger LOG = LogManager.getLogger(InsInsuranceCertController.class);
	@Autowired
	private InsReturnTraceService insReturnTraceService;
	@Autowired
	private InsSalesOrderItemsService insSalesOrderItemsService;
	@Autowired
	private InsPolicyHolderService insPolicyHolderService;
	
	/** 保险证号查询退款进度列表验证规则标识 **/
	private final static String VALIDATION_INSURANCECERTNO ="com.cifpay.ins.cert.InsuranceCertNoSet";
	
	
	/**
	 * 方法描述: 根据保险证号查询退款进度列表  作 者： yeshengnan 日 期： 2015-12-01
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/list/get",method = RequestMethod.POST, produces = { "text/json;charset=UTF-8" })
	public @ResponseBody String getInsReturnTraceList(HttpServletRequest request) {
		ResponseInfo ri = null;
		try {
			//SystemParamReqInfo spri = populateSystemParamReq(request);
			InsuranceCertNo cetNo = populateBizData(request, InsuranceCertNo.class, VALIDATION_INSURANCECERTNO);
			if (LOG.isDebugEnabled()) {
				LOG.debug("getInsReturnTraceList(HttpServletRequest request)");
			}
			List<InsReturnTrace> traceList =  insReturnTraceService.getListByCertNo(cetNo.getInsuranceCertNo());
		    List<ReturnTraceListResult> tracelistResult = new ArrayList<ReturnTraceListResult>();
		    for(InsReturnTrace insReturnTrace:traceList){
		    	ReturnTraceListResult trace = new ReturnTraceListResult();
		    	trace.setDescription(insReturnTrace.getDescription());
		    	trace.setInsuranceCertNo(cetNo.getInsuranceCertNo());
		    	trace.setOptTime(DateUtil.formatDate(insReturnTrace.getOptTime(), "yyyy-MM-dd HH:mm:ss"));
		    	trace.setOptType(insReturnTrace.getOptType());
		    	tracelistResult.add(trace);
		    }
		    ri = new DataResponseInfo(tracelistResult);
		}catch(Exception e){
			ri = handleRespException(e);
		}
	
		return JacksonUtil.toJson(ri);
     }
	
	/**
	 * 方法描述: 查询对应保险证下的退货信息  作 者： yeshengnan 日 期： 2015-12-01
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/get",method = RequestMethod.POST, produces = { "text/json;charset=UTF-8" })
	public @ResponseBody String getInsSalesOrder(HttpServletRequest request) {
		
		ResponseInfo ri = null;
		try {
			SystemParamReqInfo spri = populateSystemParamReq(request);
			InsuranceCertNo cetNo = populateBizData(request, InsuranceCertNo.class, VALIDATION_INSURANCECERTNO);
			if (LOG.isDebugEnabled()) {
				LOG.debug("getInsSalesOrder(HttpServletRequest request)");
				LOG.debug("商户号为："+spri.getVendorId() + "查询退货信息***");
			}
			InsSalesOrderItems salesOrder =  insSalesOrderItemsService.getInsSalesOrderItemsByCertNo(cetNo.getInsuranceCertNo());
			//InsPolicyHolder policyHolder = insPolicyHolderService.getInsPolicyHolderByCertNo(cetNo.getInsuranceCertNo());
			ReturnGoodsResult goods = new ReturnGoodsResult();
			goods.setGoodsName(salesOrder.getGoodsName());
			goods.setGoodsNo(salesOrder.getGoodsNo());
			goods.setOrderNo(salesOrder.getOrderNo());
			goods.setPrice(salesOrder.getPrice());
			goods.setQuantity(salesOrder.getQuantity());
			//goods.setRepresentatives(policyHolder.getHolderName());//投保人 缓存
			goods.setReturnAddress(salesOrder.getInsSalesOrder().getReturnAddress());
			goods.setReturnContacts(salesOrder.getInsSalesOrder().getReturnContacts());
			goods.setReturnPhone(salesOrder.getInsSalesOrder().getReturnPhone());
			goods.setTotalPrice(salesOrder.getTotalPrice());
		    ri = new DataResponseInfo(goods);
		}catch(Exception e){
			ri = handleRespException(e);
		}
	
		return JacksonUtil.toJson(ri);
		
	}
	
	
}
