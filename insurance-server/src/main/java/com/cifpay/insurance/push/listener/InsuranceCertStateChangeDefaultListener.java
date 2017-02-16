/**
 * File: InsuranceCertStateChangeDefaultListener.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年12月2日 下午4:28:30
 */
package com.cifpay.insurance.push.listener;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cifpay.insurance.InsuranceCertStatusEnum;
import com.cifpay.insurance.cache.InsCache;
import com.cifpay.insurance.model.InsInsuredAmountInfo;
import com.cifpay.insurance.model.InsPolicy;
import com.cifpay.insurance.param.msg.MsgResponseInfo;
import com.cifpay.insurance.param.msg.PolicyRealtimeInfo;
import com.cifpay.insurance.param.msg.RefundCertInfo;
import com.cifpay.insurance.param.msg.ReturnCertInfo;
import com.cifpay.insurance.param.msg.TodayAddedCertInfo;
import com.cifpay.insurance.push.DataCacheManager;
import com.cifpay.insurance.push.InsuranceMsgPusher;
import com.cifpay.insurance.push.MsgTypeEnum;
import com.cifpay.insurance.push.PushHelper;
import com.cifpay.insurance.push.event.InsuranceCertStateChangeEvent;
import com.cifpay.insurance.service.InsInsuredAmountInfoService;
import com.cifpay.insurance.util.RedisUtil;
import com.cifpay.insurance.util.SpringContextUtil;

/**
 * 保险证监听器默认实现
 * 
 * @author 张均锋
 *
 */
public class InsuranceCertStateChangeDefaultListener implements InsuranceCertStateChangeListener {
	private static final Logger logger = LoggerFactory.getLogger(InsuranceCertStateChangeDefaultListener.class);

	private static InsInsuredAmountInfoService insInsuredAmountInfoService = (InsInsuredAmountInfoService) SpringContextUtil.getBean("insInsuredAmountInfoService");
	
	@Override
	public void performed(InsuranceCertStateChangeEvent event) {
		InsuranceCertStatusEnum ie = InsuranceCertStatusEnum.toEnum(event.getStatus());
		if (ie == null) {
			logger.warn("非法保险证状态。[status："+event.getStatus()+"]");
			return;
		}
		switch (ie) {
		case NOT_EFFECTIVED:// 新增
			pushTodayAddedCert(event);
			break;
		//case SIGNED://今日签收也算新增???
		//	break;
		//case TO_OPEN://退货
		case TO_REFUND://待退款
			pushTodayReturnCert(event);
			break;
//		case IN_REFUND:
//			break;
		case FINISH_REFUND://完成退款
			if (event.getIsIcRefund() != null && event.getIsIcRefund() == 1) {//保险公司赔
				pushInsDoRefundSuccess(event);
			} else {//商户退
				pushVendorDoRefundSuccess(event);
			}
			break;
		case REFUSE_REFUND://拒绝退款
			pushRefuseRefund(event);
			break;
		case EXPIRED://失效
			pushExpiredCert(event);
			break;
		default:
			logger.warn("非法保险证状态。[status："+event.getStatus()+"]");
			return;
		}
	}

	/**
	 * 增加保险证
	 * 
	 * @param event
	 */
	private void pushTodayAddedCert(InsuranceCertStateChangeEvent event) {
		TodayAddedCertInfo tac = new TodayAddedCertInfo();
		if (event.getTag() == -1) {//直接获取
			try {
				String key = DataCacheManager.getTodayAddedCertKey(event.getVendorId());
				Map<String, String> map = RedisUtil.hgetall(key);
				tac.setTodayAddedCertCount(Long.valueOf(map.get("todayAddedCertCount")));
				tac.setTodayAddedAmount(Long.valueOf(map.get("todayAddedAmount")));
				tac.setTodayAllCertCount(Long.valueOf(map.get("todayAllCertCount")));
				tac.setTodayAllAmount(Long.valueOf(map.get("todayAllAmount")));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			DataCacheManager.getsetVendorTodayCertStatic(event, tac);
		}
		MsgResponseInfo mr = PushHelper.createMsgResponseInfo(MsgTypeEnum.ADD_CERT.val, event.getVendorId(), tac);
		InsuranceMsgPusher.doPush(mr);
	}

	/**
	 * 保险证失效
	 * 
	 * @param event
	 */
	private void pushExpiredCert(InsuranceCertStateChangeEvent event) {
		InsPolicy ip = InsCache.getInsPolicyCache(event.getVendorId());
		if (ip == null) {
			logger.warn("商户保单信息不存在！[vendorId：" + event.getVendorId() +"]");
			return;
		}
		InsInsuredAmountInfo ia = insInsuredAmountInfoService.getInsInsuredAmountInfoByPolicyId(ip.getId());
		ip.setInsInsuredAmountInfo(ia);

		PolicyRealtimeInfo pri = PushHelper.getPolicyRealtimeInfo(ip, event.getVendorId());
		MsgResponseInfo mr = PushHelper.createMsgResponseInfo(MsgTypeEnum.EXPIRED_CERT.val, ip.getInsPolicyHolder().getVendorId(), pri);
		InsuranceMsgPusher.doPush(mr);
	}

	/**
	 * 申请退货
	 * 
	 * @param event
	 */
	private void pushTodayReturnCert(InsuranceCertStateChangeEvent event) {
		ReturnCertInfo rc = new ReturnCertInfo();
		if (event.getTag() == -1) {//直接获取
			try {
				String key = DataCacheManager.getReturnCertKey(event.getVendorId());//今日商户退货键
				Map<String, String> map = RedisUtil.hgetall(key);
				rc.setTodayReturnCertCount(Long.valueOf(map.get("todayReturnCertCount")));
				rc.setTodayAmount(Long.valueOf(map.get("todayAmount")));
				rc.setAllToRefundCertCount(Long.valueOf(map.get("allToRefundCertCount")));
				rc.setAllToRefundAmount(Long.valueOf(map.get("allToRefundAmount")));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			DataCacheManager.getsetVendorReturnCertStatic(event, rc);
		}
		MsgResponseInfo mr = PushHelper.createMsgResponseInfo(MsgTypeEnum.RETURN_CERT.val, event.getVendorId(), rc);
		InsuranceMsgPusher.doPush(mr);
	}

	/**
	 * 商户退款成功
	 * 
	 * @param event
	 */
	private void pushVendorDoRefundSuccess(InsuranceCertStateChangeEvent event) {
		RefundCertInfo rc = new RefundCertInfo();
		DataCacheManager.decVendorRefundCertStatic(event, rc);
		MsgResponseInfo mr = PushHelper.createMsgResponseInfo(MsgTypeEnum.REFUND_SUCCESS.val, event.getVendorId(), rc);
		InsuranceMsgPusher.doPush(mr);
	}

	/**
	 * 保险公司赔付成功。
	 * 
	 * @param event
	 */
	private void pushInsDoRefundSuccess(InsuranceCertStateChangeEvent event) {
		RefundCertInfo rc = new RefundCertInfo();
		DataCacheManager.decVendorRefundCertStatic(event, rc);
		MsgResponseInfo mr = PushHelper.createMsgResponseInfo(MsgTypeEnum.INS_COMPENSATE_SUCCESS.val, event.getVendorId(), rc);
		InsuranceMsgPusher.doPush(mr);
	}
	
	/**
	 * 拒绝退款
	 * 
	 * @param event
	 */
	private void pushRefuseRefund(InsuranceCertStateChangeEvent event) {
		RefundCertInfo rc = new RefundCertInfo();
		DataCacheManager.decVendorRefundCertStatic(event, rc);
		MsgResponseInfo mr = PushHelper.createMsgResponseInfo(MsgTypeEnum.REFUSE_REFUND.val, event.getVendorId(), rc);
		InsuranceMsgPusher.doPush(mr);
	}

}
