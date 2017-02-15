package com.cifpay.lc.gateway.controller.lc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cifpay.lc.api.gateway.lc.LcPageService;
import com.cifpay.lc.constant.ReturnCode;
import com.cifpay.lc.domain.lc.GenLcPageOutputBean;
import com.cifpay.lc.domain.lc.LcPageProperty;
import com.cifpay.lc.domain.lc.QueryLcPageOutputBean;
import com.cifpay.lc.gateway.common.exception.GatewayValidationRejectException;
import com.cifpay.lc.domain.security.MerchantRequest;
import com.cifpay.lc.gateway.input.lc.LcScreenFetchUrlsReqData;

@RestController
@RequestMapping("/lcscreen")
public class LcPageController {

	@Autowired
	private LcPageService lcPageService;

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(path = "/geturls", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> getLcScreenUrls(@RequestBody MerchantRequest<LcScreenFetchUrlsReqData> merReq,
			HttpServletResponse response) {

		if (logger.isDebugEnabled()) {
			logger.debug("收到到商户请求数据：{}", merReq.getData());
		}

		Map<String, Object> result = new HashMap<String, Object>();

		try {
			LcScreenFetchUrlsReqData reqData = merReq.getData();

			String lcIdsStr = reqData.getLcIds();

			if (!StringUtils.hasText(lcIdsStr)) {
				throw new GatewayValidationRejectException(ReturnCode.GW_GENERIC_VALIDATION_REJECTED,
						"请求参数中的lcIds不允许为空");
			}

			String[] lcIds = lcIdsStr.split(",");

			ArrayList<Long> reqLcIds = new ArrayList<Long>();
			List<Map<String, String>> urls = new ArrayList<Map<String, String>>();
			for (String lcId : lcIds) {
				try {
					reqLcIds.add(Long.valueOf(lcId));
				} catch (Exception parseEx) {
					throw new GatewayValidationRejectException(ReturnCode.GW_GENERIC_VALIDATION_REJECTED,
							"请求参数中的lcId不正确");
				}
			}

			QueryLcPageOutputBean queryResult = lcPageService.queryLcPage(reqLcIds);
			for (LcPageProperty pageProp : queryResult.getListLcPage()) {
				Map<String, String> urlEnity = new HashMap<String, String>();
				urlEnity.put("lcId", pageProp.getLcId().toString());

				if (1 == pageProp.getIsPage()) {
					// TODO 获取证化页面的HTTP地址（暂时未完成）
					//urlEnity.put("url", pageProp.getLcPageUri());
					urlEnity.put("url", pageProp.getLcPageUri());
				} else {
					// TODO 获取证化页面的HTTP地址（暂时未完成）
					//urlEnity.put("url", "http://gateway.cifpay.com/notfound.html");
					urlEnity.put("url", "");
				}
				urls.add(urlEnity);
			}

			result.put("lcScreenUrls", urls);

		} catch (GatewayValidationRejectException e) {
			result.put("errorCode", e.getReturnCode());
			result.put("errorMsg", e.getMessage());
		}

		return result;
	}

	@RequestMapping("/gen")
	@ResponseBody
	public Map<String, Object> genPage() {

		if (logger.isDebugEnabled()) {
			logger.debug("进入genPage");
		}

		Map<String, Object> result = new HashMap<String, Object>();

		try {

			String lcIdsStr = "11929249342631936,11929249443295232";
			String[] arr = lcIdsStr.split(",");

			ArrayList<Long> listLcIds = new ArrayList<Long>();

			for (String lcIdStr : arr) {
				listLcIds.add(Long.parseLong(lcIdStr));
			}

			GenLcPageOutputBean output = lcPageService.genLcPage(listLcIds);


			
			result.put("ReturnCode", output.getReturnCode());
			result.put("ReturnMsg", output.getReturnMsg());

		} catch (GatewayValidationRejectException e) {
			result.put("errorCode", e.getReturnCode());
			result.put("errorMsg", e.getMessage());
		}

		return result;
	}

	@RequestMapping("/query")
	@ResponseBody
	public Map<String, Object> query() {

		if (logger.isDebugEnabled()) {
			logger.debug("进入query");
		}

		Map<String, Object> result = new HashMap<String, Object>();

		try {

			String lcIdsStr = "11929249342631936";
			String[] arr = lcIdsStr.split(",");

			ArrayList<Long> listLcIds = new ArrayList<Long>();

			for (String lcIdStr : arr) {
				listLcIds.add(Long.parseLong(lcIdStr));
			}

			QueryLcPageOutputBean output = lcPageService.queryLcPage(listLcIds);

			System.out.println(output.getListLcPage().get(0).getLcPageUri());

		} catch (GatewayValidationRejectException e) {
			result.put("errorCode", e.getReturnCode());
			result.put("errorMsg", e.getMessage());
		}

		return result;
	}
}
