/**
 * File: InsInsuranceCertController.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年11月23日 下午6:23:46
 */
package com.cifpay.insurance.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cifpay.insurance.base.BaseController;
import com.cifpay.insurance.base.Page;
import com.cifpay.insurance.bean.VendorCertStaticBean;
import com.cifpay.insurance.model.InsInsuranceCert;
import com.cifpay.insurance.model.InsPolicy;
import com.cifpay.insurance.model.InsPolicyHolder;
import com.cifpay.insurance.model.InsSalesOrder;
import com.cifpay.insurance.param.DataResponseInfo;
import com.cifpay.insurance.param.PageResponseInfo;
import com.cifpay.insurance.param.ResponseInfo;
import com.cifpay.insurance.param.SystemParamReqInfo;
import com.cifpay.insurance.param.cert.CreateInsuranceCertBatchInfo;
import com.cifpay.insurance.param.cert.CreateInsuranceCertInfo;
import com.cifpay.insurance.param.cert.CreateInsuranceCertResult;
import com.cifpay.insurance.param.cert.GetInsuranceCertListInfo;
import com.cifpay.insurance.param.cert.GetInsuranceCertListResult;
import com.cifpay.insurance.param.cert.InsuranceCertInfo;
import com.cifpay.insurance.param.cert.InsuranceCertInfo.InsCertOrderInfo;
import com.cifpay.insurance.param.cert.InsuranceCertInfo.InsCertOrderItem;
import com.cifpay.insurance.param.cert.InsuranceCertInfo.InsPolicyInfo;
import com.cifpay.insurance.param.cert.InsuranceCertNo;
import com.cifpay.insurance.param.cert.InsuranceCertReportInfo;
import com.cifpay.insurance.param.cert.InsuranceCertReportListResult;
import com.cifpay.insurance.param.policy.InsurancePolicyInfo;
import com.cifpay.insurance.param.policy.InsurerInfo;
import com.cifpay.insurance.param.policy.PolicyHolderInfo;
import com.cifpay.insurance.service.InsInsuranceCertService;
import com.cifpay.insurance.service.InsPolicyService;
import com.cifpay.insurance.util.DateUtil;
import com.cifpay.insurance.util.JacksonUtil;
import com.cifpay.starframework.model.ServiceResult;

/**
 * 保险证控制器
 * 
 * @author 张均锋
 *
 */
@Controller
@RequestMapping("/inner/insurance/certificate")
public class InsInsuranceCertController extends BaseController {

	@Autowired
	private InsPolicyService insPolicyService;
	@Autowired
	private InsInsuranceCertService insInsuranceCertService;
	
	/** 创建保险证验证规则标识 **/
	private final static String VALIDATION_CREATEINSINSURANCECERT = "com.cifpay.ins.cert.CreateInsuranceCertInfoSet";
	/** 批量创建保险证验证规则标识 **/
	private final static String VALIDATION_CREATEINSINSURANCECERTBATCH = "com.cifpay.ins.cert.CreateInsuranceCertInfoBatchSet";
	/** 查看保险证验证规则标识 **/
	private final static String VALIDATION_INSURANCECERTNO ="com.cifpay.ins.cert.InsuranceCertNoSet";
	/** 查看保险证列表验证规则标识 **/
	private final static String VALIDATION_GETINSURANCECERTLISTINFO = "com.cifpay.ins.cert.GetInsuranceCertListInfoSet";
	/** 保险证统计查询规则标识 **/
	private final static String VALIDATION_INSURANCECERTREPORTINFO = "com.cifpay.ins.cert.InsuranceCertReportInfoSet";
	
	/**
	 *  创建保险证
	 *  
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = { "text/json;charset=UTF-8" })
	public @ResponseBody String createInsInsuranceCert(HttpServletRequest request) {
		ResponseInfo ri = null;
		try {
			SystemParamReqInfo spri = populateSystemParamReq(request);
			CreateInsuranceCertInfo ci = populateBizData(request, CreateInsuranceCertInfo.class, VALIDATION_CREATEINSINSURANCECERT);
			ServiceResult<CreateInsuranceCertResult> ret = insInsuranceCertService.createInsInsuranceCert(spri.getVendorId(), ci);
			ri = new DataResponseInfo(ret.getCode(), ret.getObj());
		} catch (Exception e) {
			ri = handleRespException(e);
		}
		return JacksonUtil.toJson(ri);
	}
	
	/**
	 *  批量创建保险证
	 *  
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/batch/create", method = RequestMethod.POST, produces = { "text/json;charset=UTF-8" })
	public @ResponseBody String createInsInsuranceCertBatch(HttpServletRequest request) {
		ResponseInfo ri = null;
		try {
			SystemParamReqInfo spri = populateSystemParamReq(request);
			CreateInsuranceCertBatchInfo ci = populateBizData(request, CreateInsuranceCertBatchInfo.class, VALIDATION_CREATEINSINSURANCECERTBATCH);
			ServiceResult<List<CreateInsuranceCertResult>> ret = insInsuranceCertService.createInsInsuranceCertBatch(spri.getVendorId(), ci);
			ri = new DataResponseInfo(ret.getCode(), ret.getObj());
		} catch (Exception e) {
			ri = handleRespException(e);
		}
		return JacksonUtil.toJson(ri);
	}
	

	
	/**
	 * 方法描述: 查询保险证及其关联保单、商品信息 作 者： yeshengnan 日 期： 2015-11-27
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/display/get", method = RequestMethod.POST, produces = { "text/json;charset=UTF-8" })
	public @ResponseBody String getDisplayInsuranceCert(HttpServletRequest request) {
		ResponseInfo ri = null;
		try {
			SystemParamReqInfo spri = populateSystemParamReq(request);
			InsuranceCertNo certNo = populateBizData(request, InsuranceCertNo.class,VALIDATION_INSURANCECERTNO);
			InsInsuranceCert cert = insInsuranceCertService.getFullInsInsuranceCertByCertNo(certNo.getInsuranceCertNo());
			if (cert == null) {
				return JacksonUtil.toJson(new DataResponseInfo(resultCode.get("biz.insurance.cert.notfound"), "保险证不存在"));
			}
			InsuranceCertInfo ici = new InsuranceCertInfo();
			ici.setPolicyId(cert.getPolicyId());
			ici.setPolicyNo(cert.getPolicyNo());
			ici.setInsuranceCertId(cert.getId()+"");
			ici.setInsuranceCertNo(cert.getInsuranceCertNo());
			ici.setEffectiveTime(cert.getEffectiveTime());
			ici.setExpiredTime(cert.getExpiredTime());
			ici.setStatus(cert.getStatus());
			ici.setIsSign(cert.getIsSign());
			//保单
			InsPolicyInfo ip = ici.new InsPolicyInfo();
			ici.setInsPolicyInfo(ip);
			ip.setPolicyId(cert.getInsPolicy().getId());
			ip.setPolicyNo(cert.getInsPolicy().getPolicyNo());
			ip.setInsurername(cert.getInsPolicy().getInsInsurerInfo().getInsurername());//承保人
			ip.setHolderName(cert.getInsPolicy().getInsPolicyHolder().getHolderName());//投保人
			ip.setInsuredName(cert.getInsPolicy().getInsuredName());//被保人
			
			//商品订单
			InsCertOrderItem it = ici.new InsCertOrderItem();
			ici.setInsCertOrderItem(it);
			it.setGoodsName(cert.getInsSalesOrderItems().getGoodsName());
			it.setGoodsNo(cert.getInsSalesOrderItems().getGoodsNo());
			it.setPrice(cert.getInsSalesOrderItems().getPrice());
			it.setQuantity(cert.getInsSalesOrderItems().getQuantity());
			it.setTotalPrice(cert.getInsSalesOrderItems().getTotalPrice());
			//订单信息
			InsCertOrderInfo o = ici.new InsCertOrderInfo();
			InsSalesOrder iso = cert.getInsSalesOrderItems().getInsSalesOrder();
			it.setInsCertOrderInfo(o);
			o.setOrderNo(iso.getOrderNo());
			o.setReturnAddress(iso.getReturnAddress());
			o.setReturnContacts(iso.getReturnContacts());
			o.setReturnPhone(iso.getReturnPhone());
			ri = new DataResponseInfo(ici);
		} catch (Exception e) {
			ri = handleRespException(e);
		}
		return JacksonUtil.toJson(ri);
	}
	
	/**
	 * 方法描述: 查询保险证列表  作 者： yeshengnan 日 期： 2015-11-27
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/list/get",method = RequestMethod.POST, produces = { "text/json;charset=UTF-8" })
	public @ResponseBody String getInsInsuranceCertList(HttpServletRequest request) {
		PageResponseInfo ri = null;
		try {
			SystemParamReqInfo spri = populateSystemParamReq(request);
			GetInsuranceCertListInfo certInput = populateBizData(request, GetInsuranceCertListInfo.class,VALIDATION_GETINSURANCECERTLISTINFO);
			Page<InsInsuranceCert> page = new Page<InsInsuranceCert>();
			page.setPageNo(certInput.getPageNo());
			page.setPageSize(certInput.getPageSize());
			insInsuranceCertService.getInsInsuranceCertList(spri.getVendorId(), certInput, page);
			List<GetInsuranceCertListResult> resultList = new ArrayList<GetInsuranceCertListResult>();
			for(InsInsuranceCert cert:page.getResult()){
			    GetInsuranceCertListResult CertListResult = new GetInsuranceCertListResult();
				CertListResult.setCreatedTime(DateUtil.formatDate(cert.getCreatedTime(), "yyyy-MM-dd HH:mm:ss"));
				//获取保险证失效时间（生效时间，有效期限）
				CertListResult.setExpiredTime(DateUtil.formatDate(cert.getExpiredTime(), "yyyy-MM-dd HH:mm:ss"));
				CertListResult.setInsuranceCertNo(cert.getInsuranceCertNo());
				CertListResult.setPolicyNo(cert.getPolicyNo());
				CertListResult.setPrice(cert.getInsSalesOrderItems().getPrice());
				CertListResult.setQuantity(cert.getInsSalesOrderItems().getQuantity());
				CertListResult.setStatus(cert.getStatus());
				resultList.add(CertListResult);
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
	 * 方法描述：签收 作 者： yeshengnan 日 期： 2015-12-03
	 */
	@RequestMapping(value = "/logistics/sign",method = RequestMethod.POST,produces = { "text/json;charset=UTF-8" })
	public @ResponseBody String signLogistics(HttpServletRequest request){
		ResponseInfo responseInfo = null;
		try {
			SystemParamReqInfo spri = populateSystemParamReq(request);
			InsuranceCertNo  insuranceCertNo = populateBizData(request, InsuranceCertNo.class,VALIDATION_INSURANCECERTNO);
			String[] certNosArray = insuranceCertNo.getInsuranceCertNo().split("\\|");
			for(int i=0;i<certNosArray.length;i++){
				String certNo = certNosArray[i];
				InsInsuranceCert cert = new InsInsuranceCert();
				cert.setVendorId(spri.getVendorId());
				cert.setInsuranceCertNo(certNo);
				insInsuranceCertService.signLogistics(cert);
				responseInfo = new ResponseInfo();
			}
		} catch (Exception e) {
			responseInfo = handleRespException(e);
		}
		return JacksonUtil.toJson(responseInfo);
	}
	
	/**
	 * 获取保险证报表统计数据。
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/report/get",method = RequestMethod.POST, produces = { "text/json;charset=UTF-8" })
	public @ResponseBody String getCertificateReport(HttpServletRequest request) {
		ResponseInfo ri = null;
		try {
			SystemParamReqInfo spri = populateSystemParamReq(request);
			InsuranceCertReportInfo certInput = populateBizData(request, InsuranceCertReportInfo.class,VALIDATION_INSURANCECERTREPORTINFO);
			List<VendorCertStaticBean> certList = insInsuranceCertService.getVendorCertStatic(spri.getVendorId(), certInput.getPolicyNo());
			List<InsuranceCertReportListResult> resultList = new ArrayList<InsuranceCertReportListResult>();
			int lastType = -1;
			InsuranceCertReportListResult certListResult = null;
			for(VendorCertStaticBean cert:certList){
				if (lastType != cert.getType()) {//new begin
					lastType = cert.getType();
					certListResult = new InsuranceCertReportListResult();
					resultList.add(certListResult);
				}
				certListResult.setTimeType(cert.getType());
				if (cert.getCtype() == 1) {//所有保险证数量
					certListResult.setCertCount(cert.getCertCount());
					if (cert.getAmount() != null) {
						certListResult.setInsuredAmount(cert.getAmount());
					}
				} else if (cert.getType() == 2) {//退货
					certListResult.setReturnCertCount(cert.getCertCount());
					if (cert.getAmount() != null) {
						certListResult.setReturnAmount(cert.getAmount());
					}
				} else;//impossible;
			}
			ri = new DataResponseInfo(resultList);
		} catch (Exception e) {
			ri = handleRespException(e);
		}
		return JacksonUtil.toJson(ri);
	}
	
	/**
	 *  检查已签收保险证，并更新保险证状态，释放保额。
	 *  
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/signed/check", method = RequestMethod.POST, produces = { "text/json;charset=UTF-8" })
	public @ResponseBody String checkSignedInsuranceCert(HttpServletRequest request) {
		ResponseInfo ri = null;
		try {
			//SystemParamReqInfo spri = populateSystemParamReq(request);
			insInsuranceCertService.checkSignedInsuranceCert();
			ri = new ResponseInfo();
		} catch (Exception e) {
			ri = handleRespException(e);
		}
		return JacksonUtil.toJson(ri);
	}
	
	/**
	 *  检查过期保险证，并更新保险证状态，释放保额。本接口由定时服务器调用。
	 *  
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/expired/check", method = RequestMethod.POST, produces = { "text/json;charset=UTF-8" })
	public @ResponseBody String checkExpiredInsuranceCert(HttpServletRequest request) {
		ResponseInfo ri = null;
		try {
			//SystemParamReqInfo spri = populateSystemParamReq(request);
			insInsuranceCertService.checkExpiredInsuranceCert();
			ri = new ResponseInfo();
		} catch (Exception e) {
			ri = handleRespException(e);
		}
		return JacksonUtil.toJson(ri);
	}
	
	/**
	 * 方法描述: 根据保险证号查看保险证 作 者： yeshengnan 日 期： 2015-12-17
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/getcert",method = RequestMethod.POST,produces = { "text/json;charset=UTF-8" })
	public @ResponseBody String  getInsuranceCert(HttpServletRequest request) {
		ResponseInfo responseOut = null;
		try {
			SystemParamReqInfo spri = populateSystemParamReq(request);
			InsuranceCertNo certNo = populateBizData(request, InsuranceCertNo.class,VALIDATION_INSURANCECERTNO);
			InsInsuranceCert cert = insInsuranceCertService.getInsInsuranceCertByCertNo(certNo.getInsuranceCertNo());
			if(null != cert){
				String policyNo = cert.getPolicyNo();
				InsPolicy insPolicy = insPolicyService.getPolicyByPolicyNo(policyNo);
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
					String result = JacksonUtil.toJson(responseOut);
					logger.debug("查看保险证返回数据："+"["+result+"]");
				}
			}else{
				    responseOut = new ResponseInfo();
				    responseOut.setCode(resultCode.get("biz.insurance.cert.notfound"));
					responseOut.setMsg("保险证不存在！");
			}
		} catch (Exception e) {
			responseOut = handleRespException(e);
		}
		return JacksonUtil.toJson(responseOut);
	}
	
	
}
