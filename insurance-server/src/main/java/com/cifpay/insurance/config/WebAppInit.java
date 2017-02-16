/**
 * File: WebAppInit.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年12月25日 下午4:08:15
 */
package com.cifpay.insurance.config;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.cifpay.insurance.cache.InsCache;
import com.cifpay.insurance.model.InsBankCard;
import com.cifpay.insurance.model.InsReturnType;
import com.cifpay.insurance.service.InsBankCardService;
import com.cifpay.insurance.service.InsReturnTypeService;
import com.cifpay.insurance.util.SpringContextUtil;

/**
 * web初始化.
 * 
 * @author 张均锋
 *
 */
public class WebAppInit {
	protected static final Logger logger = LogManager.getLogger(WebAppInit.class);

	public void loadCache() {
		logger.info("加载缓存...");
		new Thread(new Runnable() {
			@Override
			public void run() {
				loadInsBankCardCache();
				//loadInsReturnTypeCache();
			}
		}).start();
	}

	/**
	 * 异步加载银行卡信息缓存。
	 */
	private void loadInsBankCardCache() {
		try {
			InsBankCardService service = (InsBankCardService) SpringContextUtil.getBean("insBankCardService");
			List<InsBankCard> list = service.getBankCardList();
			InsBankCard bc;
			for (int i = 0; i < list.size(); i++) {
				bc = list.get(i);
				if (bc.getCardBin() == null)
					continue;
				if (InsCache.bankCardCache.getIfPresent(bc.getCardBin()) != null) {
					InsCache.bankCardCache.put(bc.getCardBin() + "_1", bc);// 最多一个重复
				} else {
					InsCache.bankCardCache.put(bc.getCardBin(), bc);
				}

			}
		} catch (Exception e) {
			logger.error("加载银行卡号归属信息异常！", e);
		}
	}
	
	/*private void loadInsReturnTypeCache() {
		try {
			InsReturnTypeService service = (InsReturnTypeService) SpringContextUtil.getBean("insReturnTypeService");
			List<InsReturnType> list = service.getList();
			InsReturnType bc;
			for (int i = 0; i < list.size(); i++) {
				bc = list.get(i);
				InsCache.insReturnTypeCache.put(bc.getCode(), bc);
			}
		} catch (Exception e) {
			logger.error("加载退货类型信息异常！", e);
		}
	}*/
	
	/**
	 * 加载商户保险证统计数据
	 */
	public void loadVendorCertStatic() {
		
	}
	
	
}
