package com.cifpay.lc.gateway.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.cifpay.lc.api.BusinessInput;
import com.cifpay.lc.api.BusinessOutput;
import com.cifpay.lc.api.VoidObject;
import com.cifpay.lc.api.gateway.basic.bankcode.BankCodeInfo;
import com.cifpay.lc.api.gateway.basic.bankcode.BankCodeQueryAllService;
import com.cifpay.lc.gateway.common.exception.GatewayProcessException;

@Component
public class BankCodeInfoCache {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private BankCodeQueryAllService bankCodeQueryAllService;

	@Cacheable(cacheNames = GatewayCacheName.BANK_CODE_INFO_CACHE)
	public Map<String, BankCodeInfo> getBankCodeMappings() {

		logger.info("从核心业务层获取BANK CODE基础信息.");

		BusinessInput<VoidObject> bi = new BusinessInput<VoidObject>();
		BusinessOutput<ArrayList<BankCodeInfo>> bo = bankCodeQueryAllService.execute(bi);
		if (bo.isFailed()) {
			throw new GatewayProcessException(bo.getReturnCode(), bo.getReturnMsg());
		}
		ArrayList<BankCodeInfo> codeList = bo.getData();
		Map<String, BankCodeInfo> mappings = new HashMap<String, BankCodeInfo>();
		if (null != codeList) {
			for (BankCodeInfo c : codeList) {
				mappings.put(c.getBankCode(), c);
			}
		}
		return mappings;
	}

}
