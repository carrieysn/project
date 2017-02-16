/**
 * File: InsurancePolicyDefaultListener.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年12月2日 下午4:31:18
 */
package com.cifpay.insurance.push.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cifpay.insurance.model.InsPolicy;
import com.cifpay.insurance.param.msg.MsgResponseInfo;
import com.cifpay.insurance.param.msg.PolicyRealtimeInfo;
import com.cifpay.insurance.push.InsuranceMsgPusher;
import com.cifpay.insurance.push.MsgTypeEnum;
import com.cifpay.insurance.push.PushHelper;
import com.cifpay.insurance.push.event.InsurancePolicyEvent;
import com.cifpay.insurance.service.InsPolicyService;
import com.cifpay.insurance.util.SpringContextUtil;

/**
 * 保单监听器默认实现
 * 
 * @author 张均锋
 *
 *
 *
 *
 *
 */
public class InsurancePolicyDefaultListener implements InsurancePolicyListener {
	private static final Logger logger = LoggerFactory.getLogger(InsurancePolicyDefaultListener.class);
	private InsPolicyService insPolicyService = (InsPolicyService) SpringContextUtil.getBean("insPolicyService");
	
	@Override
	public void performed(InsurancePolicyEvent event) {
		pushPremiumAdd(event);
	}
	
	/**
	 * 推送保费新增。
	 * 
	 * @param event
	 */
	private void pushPremiumAdd(InsurancePolicyEvent event) {
		InsPolicy ip = event.getCurInsPolicy();
		if (ip == null) {
			ip = insPolicyService.getFullInsPolicy(event.getVendorId());
			if (ip == null) {
				logger.warn("商户保单信息不存在！[vendorId：" + event.getVendorId() +"]");
				return;
			}
		}
		PolicyRealtimeInfo pri = PushHelper.getPolicyRealtimeInfo(ip, event.getVendorId());
		MsgResponseInfo mr = PushHelper.createMsgResponseInfo(MsgTypeEnum.ADDPREMINUM.val, ip.getVendorId(), pri);
		InsuranceMsgPusher.doPush(mr);
	}
}
