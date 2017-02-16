/**
 * File: PushHelper.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年12月2日 下午4:58:07
 */
package com.cifpay.insurance.push;

import com.cifpay.insurance.model.InsPolicy;
import com.cifpay.insurance.param.msg.MsgResponseInfo;
import com.cifpay.insurance.param.msg.PolicyRealtimeInfo;

/**
 * 推送工具类
 * 
 * @author 张均锋
 *
 */
public class PushHelper {
	//private static InsInsuranceCertService insInsuranceCertService = (InsInsuranceCertService) SpringContextUtil.getBean("insInsuranceCertService");
	
	/**
	 * 获取保单实时信息
	 * 
	 * @param ip
	 * @param vendorId
	 * @return
	 */
	public static PolicyRealtimeInfo getPolicyRealtimeInfo(InsPolicy ip, String vendorId) {
		/*Long usedInsuredAmount = insInsuranceCertService.getUsedInsuredAmount(vendorId);
		if (usedInsuredAmount == null)
			usedInsuredAmount = 0l;*/
		PolicyRealtimeInfo pr = new PolicyRealtimeInfo();
		pr.setInsuredAmount(ip.getInsuredAmount());
		pr.setValidFrom(ip.getValidFrom());
		pr.setValidTo(ip.getValidTo());
		pr.setPremium(ip.getPremium());
		//pr.setUsedInsuredAmount(usedInsuredAmount);
		pr.setRealIncome(0l);
		pr.setRealInsuredAmount(ip.getInsInsuredAmountInfo().getBalance());
		pr.setStatus(ip.getStatus());
		return pr;
	}

	/** 创建业务消息　 **/
	public static MsgResponseInfo createMsgResponseInfo(int type, String vendorId, Object content) {
		MsgResponseInfo mr = new MsgResponseInfo();
		mr.setMsgType(type);
		mr.setSource(1);
		mr.setVendorId(vendorId);
		mr.setContent(content);
		return mr;
	}
	
}
