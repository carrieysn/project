/**
 * File: InsInsuranceCertControllerOpen.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年12月22日 下午8:52:49
 */
package com.cifpay.insurance.controller.openapi;

import java.util.ArrayList;
import java.util.Date;
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
import com.cifpay.insurance.param.DataResponseInfo;
import com.cifpay.insurance.param.PageResponseInfo;
import com.cifpay.insurance.param.ResponseInfo;
import com.cifpay.insurance.param.SystemParamReqInfo;
import com.cifpay.insurance.param.cert.GetInsuranceCertListInfo;
import com.cifpay.insurance.param.cert.GetInsuranceCertListResult;
import com.cifpay.insurance.param.cert.InsuranceCertReportInfo;
import com.cifpay.insurance.param.cert.InsuranceCertReportListResult;
import com.cifpay.insurance.service.InsInsuranceCertService;
import com.cifpay.insurance.util.DateUtil;
import com.cifpay.insurance.util.JacksonUtil;
import com.cifpay.insurance.util.StringUtils;

/**
 * 保险证控制器
 * 
 * @author 张均锋
 *
 */
@Controller
@RequestMapping("/insurance/certificate")
public class InsInsuranceCertControllerOpen extends BaseController {

	@Autowired
	private InsInsuranceCertService insInsuranceCertService;
	
	/** 查看保险证列表验证规则标识 **/
	private final static String VALIDATION_GETINSURANCECERTLISTINFO = "com.cifpay.ins.cert.GetInsuranceCertListInfoSet";
	/** 保险证统计查询规则标识 **/
	private final static String VALIDATION_INSURANCECERTREPORTINFO = "com.cifpay.ins.cert.InsuranceCertReportInfoSet";
	
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
			if (StringUtils.isNotEmpty(certInput.getCreatedTimeTo())) {
				Date cd = DateUtil.addDay(DateUtil.parseDate(certInput.getCreatedTimeTo(), "yyyy-MM-dd"), 1);
				certInput.setCreatedTimeTo(DateUtil.formatDate(cd, "yyyy-MM-dd"));
			}
			if (StringUtils.isNotEmpty(certInput.getReturnDateTo())) {
				Date cd = DateUtil.addDay(DateUtil.parseDate(certInput.getReturnDateTo(), "yyyy-MM-dd"), 1);
				certInput.setReturnDateTo(DateUtil.formatDate(cd, "yyyy-MM-dd"));
			}
			insInsuranceCertService.getInsInsuranceCertList(spri.getVendorId(), certInput, page);
			List<GetInsuranceCertListResult> resultList = new ArrayList<GetInsuranceCertListResult>();
			for(InsInsuranceCert cert:page.getResult()){
			    GetInsuranceCertListResult certListResult = new GetInsuranceCertListResult();
				certListResult.setCreatedTime(DateUtil.formatDate(cert.getCreatedTime(), "yyyy-MM-dd HH:mm:ss"));
				//获取保险证失效时间（生效时间，有效期限）
				certListResult.setExpiredTime(DateUtil.formatDate(cert.getExpiredTime(), "yyyy-MM-dd HH:mm:ss"));
				certListResult.setReturnDate(DateUtil.formatDate(cert.getReturnDate(), "yyyy-MM-dd HH:mm:ss"));
				certListResult.setInsuranceCertNo(cert.getInsuranceCertNo());
				certListResult.setPolicyNo(cert.getPolicyNo());
				certListResult.setPrice(cert.getInsSalesOrderItems().getPrice());
				certListResult.setQuantity(cert.getInsSalesOrderItems().getQuantity());
				certListResult.setIsLcOpen(cert.getIsLcOpen());
				certListResult.setStatus(cert.getStatus());
				resultList.add(certListResult);
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
					certListResult.setTimeType(cert.getType());
					resultList.add(certListResult);
				}
				if (cert.getCtype() == 1) {//所有保险证数量
					certListResult.setCertCount(cert.getCertCount());
					if (cert.getAmount() != null) {
						certListResult.setInsuredAmount(cert.getAmount());
					}
				} else if (cert.getCtype() == 2) {//退货
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
	
}