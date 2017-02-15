package com.cifpay.lc.std.interceptor;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.cifpay.lc.api.BusinessInput;
import com.cifpay.lc.api.BusinessOutput;
import com.cifpay.lc.api.gateway.lc.ApplyService;
import com.cifpay.lc.api.gateway.lc.OpenLcService;
import com.cifpay.lc.api.gateway.sms.SmsSendMessageService;
import com.cifpay.lc.api.message.lc.OpenLcNotifyMessageService;
import com.cifpay.lc.constant.ReturnCode;
import com.cifpay.lc.constant.enums.LcTranStatus;
import com.cifpay.lc.constant.enums.SmsType;
import com.cifpay.lc.core.cache.pojo.LcProductCache;
import com.cifpay.lc.core.cache.service.LcProductCacheServant;
import com.cifpay.lc.core.common.CoreBusinessContext;
import com.cifpay.lc.core.component.CoreBusinessInterceptor;
import com.cifpay.lc.core.db.dao.LcDao;
import com.cifpay.lc.core.db.pojo.Lc;
import com.cifpay.lc.core.exception.CoreBusinessException;
import com.cifpay.lc.domain.lc.ApplyInputBean;
import com.cifpay.lc.domain.message.LcFreezeParamBean;
import com.cifpay.lc.domain.message.SmsParamBean;
import com.cifpay.lc.domain.sms.SmsSendOutputBean;
import com.cifpay.lc.exception.DistributeLockException;
import com.cifpay.lc.util.security.ThreeDESUtil;

@Component
public class LcAutoSendSMSInterceptor implements CoreBusinessInterceptor {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private LcDao lcDao;

	@Autowired
	private LcProductCacheServant lcProductCacheServant;

	@Autowired
	private SmsSendMessageService smsSendMessageService;

	@Override
	public void beforeProcessBusiness(	Object serviceInstance,
										Serializable inputData,
										CoreBusinessContext context) throws CoreBusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterProcessBusiness(	Object serviceInstance,
										Serializable inputData,
										BusinessOutput<Serializable> businessOutput,
										CoreBusinessContext context) throws CoreBusinessException {

		if (businessOutput.isSuccess()) {

			// 开证成功阶段
			if (serviceInstance instanceof OpenLcNotifyMessageService && inputData instanceof LcFreezeParamBean) {
				// 获取参数
				LcFreezeParamBean<JSONObject> inputBean = (LcFreezeParamBean) inputData;

				// 自动发送短信流程
				if (inputBean.getLcTranStatus().equals(LcTranStatus.SUCCESS)) {
					// 查询产品
					Lc lc = lcDao.selectByPrimaryKey(inputBean.getLcId());
					LcProductCache lcProductCache = lcProductCacheServant.getLcProductCache(lc.getProductCode());

					autoSendSMSProcess(inputBean, lc, lcProductCache);
				}

			}

			// 申请解付阶段
			if (serviceInstance instanceof ApplyService) {
				ApplyInputBean inputBean = (ApplyInputBean) inputData;				
				
				// 查询产品
				Lc lc = lcDao.selectByPrimaryKey(inputBean.getLcId());
				LcProductCache lcProductCache = lcProductCacheServant.getLcProductCache(lc.getProductCode());
				
				validateSMS(inputBean,lc,lcProductCache);
			}

		}

	}

	@Override
	public void onException(Object serviceInstance,
							Serializable inputData,
							BusinessOutput<Serializable> businessOutput,
							Throwable exception,
							CoreBusinessContext context) throws CoreBusinessException {
		// TODO Auto-generated method stub

	}

	/**
	 * 开证成功后，发送短信
	 * 
	 * @param businessInstance
	 * @param inputData
	 * @param lc
	 * @param lcProductCache
	 */
	private void autoSendSMSProcess(LcFreezeParamBean<JSONObject> inputBean, Lc lc, LcProductCache lcProductCache) {

		if (lcProductCache == null) {
			logger.error("未找到对应产品，LcId=" + inputBean.getLcId());
			return;
		}

		// 开证成功之后发送短信
		if (lcProductCache.getAutoSendSms() != null && lcProductCache.getAutoSendSms()) {
			try {

				SmsParamBean smsParamBean = new SmsParamBean();

				smsParamBean.setSmstype(SmsType.SMS_CP500.getCode());
				smsParamBean.setMerId(lc.getMid());
				smsParamBean.setAmount(lc.getLcAmount());
				smsParamBean.setOrderContent(lc.getOrderContent());
				smsParamBean.setPhone(ThreeDESUtil.decDecrypt(lc.getPayerMobile()));
				smsParamBean.setCreateTime(lc.getCreateTime());

				if (lc.getPayerAccno() != null) {
					String accNo = ThreeDESUtil.decDecrypt(lc.getPayerAccno());
					int accNoLength = accNo.length();
					int accNoLengthSub = accNoLength - 4;
					smsParamBean.setCardNo(accNo.substring(accNoLengthSub, accNoLength));
				}

				BusinessOutput<SmsSendOutputBean> smsOutputBean = smsSendMessageService.execute(new BusinessInput<SmsParamBean>(smsParamBean));

				if (smsOutputBean.isSuccess()) {
					// 更新银信证表，保存短信验证码
					Lc updateLc = new Lc();
					updateLc.setLcId(lc.getLcId());
					updateLc.setSmsCode(smsParamBean.getSmsCode());
					lcDao.updateByPrimaryKeySelective(updateLc);
					
					logger.info("==============CP500发送短信成功==============");					
				} else {
					logger.error("===============CP500发送短信失败============");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	// 申请解付前，判断验证码是否正确
	private void validateSMS(ApplyInputBean inputBean,Lc lc, LcProductCache lcProductCache) {
		if (lcProductCache == null) {
			logger.error("未找到对应产品，LcId=" + inputBean.getLcId());
			return;
		}
		
		if(lcProductCache.getAutoSendSms() != null && lcProductCache.getAutoSendSms()){
			if(inputBean.getSignCode() == null){
				throw new DistributeLockException(ReturnCode.SERVER_PARAM_ERROR,"验证码不正确");
			}else{
				if(!inputBean.getSignCode().equals(lc.getSmsCode())){
					logger.error("=========================CP500验证短信失败===========================");
					throw new DistributeLockException(ReturnCode.SERVER_PARAM_ERROR,"验证码不正确");
				}
			}			
			
		}
		
	}

}
