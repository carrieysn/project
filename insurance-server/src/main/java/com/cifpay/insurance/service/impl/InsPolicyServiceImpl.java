package com.cifpay.insurance.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cifpay.insurance.PolicyChangeListTypeEnum;
import com.cifpay.insurance.PolicyStatusEnum;
import com.cifpay.insurance.base.Page;
import com.cifpay.insurance.bean.PolicyAmountChangeBean;
import com.cifpay.insurance.cache.InsCache;
import com.cifpay.insurance.config.WebConstant;
import com.cifpay.insurance.dao.InsGearingAdjustHisDao;
import com.cifpay.insurance.dao.InsGearingRuleDao;
import com.cifpay.insurance.dao.InsInsuredAmountInfoDao;
import com.cifpay.insurance.dao.InsInsurerInfoDao;
import com.cifpay.insurance.dao.InsPolicyChangeListDao;
import com.cifpay.insurance.dao.InsPolicyDao;
import com.cifpay.insurance.dao.InsPolicyHolderDao;
import com.cifpay.insurance.dao.InsPolicyOrderDao;
import com.cifpay.insurance.dao.InsUserDao;
import com.cifpay.insurance.exception.InsuranceBizRuntimeException;
import com.cifpay.insurance.model.InsGearingAdjustHis;
import com.cifpay.insurance.model.InsGearingRule;
import com.cifpay.insurance.model.InsInsuredAmountInfo;
import com.cifpay.insurance.model.InsInsurerInfo;
import com.cifpay.insurance.model.InsPolicy;
import com.cifpay.insurance.model.InsPolicyChangeList;
import com.cifpay.insurance.model.InsPolicyHolder;
import com.cifpay.insurance.model.InsPolicyOrder;
import com.cifpay.insurance.model.InsUser;
import com.cifpay.insurance.model.InsWarningRule;
import com.cifpay.insurance.model.SysCodeRule;
import com.cifpay.insurance.param.policy.CreateInsurancePolicyResult;
import com.cifpay.insurance.param.policy.GetPolicyListInfo;
import com.cifpay.insurance.push.InsuranceEventDefaultHandler;
import com.cifpay.insurance.push.event.InsurancePolicyEvent;
import com.cifpay.insurance.service.InsPolicyService;
import com.cifpay.insurance.service.SysCodeRuleService;
import com.cifpay.insurance.util.DateUtil;
import com.cifpay.starframework.cache.ServiceResultCodeCache;
import com.cifpay.starframework.model.ServiceResult;

@Service("insPolicyService")
public class InsPolicyServiceImpl implements InsPolicyService {
	private static final Logger logger = LogManager.getLogger(InsPolicyServiceImpl.class);
	private static ServiceResultCodeCache resultCode = ServiceResultCodeCache.getInstance();
	@Autowired
	private InsPolicyDao insPolicyDao;
	@Autowired
	private InsPolicyHolderDao insPolicyHolderDao;
	@Autowired
	private InsInsuredAmountInfoDao insInsuredAmountInfoDao;
	@Autowired
	private InsPolicyOrderDao insPolicyOrderDao;
	@Autowired
	private InsPolicyChangeListDao insPolicyChangeListDao;
	@Autowired
	private SysCodeRuleService sysCodeRuleService;
	@Autowired
	private InsInsurerInfoDao insInsurerInfoDao;
	@Autowired
	private InsUserDao insUserDao;
	@Autowired
	private InsGearingRuleDao insGearingRuleDao;
	@Autowired
	private InsGearingAdjustHisDao insGearingAdjustHisDao;
	
	@Override
	public InsPolicy get(long ID) {
		return insPolicyDao.get(ID);
	}

	@Override
	public ServiceResult<String> add(InsPolicy insPolicy) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insPolicyDao.add(insPolicy);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> addSelective(InsPolicy insPolicy) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insPolicyDao.addSelective(insPolicy);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> update(InsPolicy insPolicy) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insPolicyDao.update(insPolicy);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> updateSelective(InsPolicy insPolicy) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insPolicyDao.updateSelective(insPolicy);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> delete(InsPolicy insPolicy) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insPolicyDao.delete(insPolicy);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public List<InsPolicy> getList() {
		return insPolicyDao.getList();
	}

	@Override
	public int getCount() {
		return insPolicyDao.getCount();
	}

	@Override
	@Transactional
	public ServiceResult<CreateInsurancePolicyResult> savePolicy(InsPolicy insPolicy) {
		CreateInsurancePolicyResult policyResult = new CreateInsurancePolicyResult();
		createInsPolicy(insPolicy);
	    policyResult.setPolicyId(insPolicy.getId()+"");
	    policyResult.setPolicyNo(insPolicy.getPolicyNo());
	    policyResult.setPremium(insPolicy.getPremium());
	    ServiceResult<CreateInsurancePolicyResult> serviceResult = new ServiceResult<CreateInsurancePolicyResult>();
	    serviceResult.setCode(resultCode.get("common.sucess"));
	    serviceResult.setObj(policyResult);
	    return serviceResult;
	}

	@Override
	public InsPolicy getPolicyByVendorId(String vendorId) {
		return insPolicyDao.getPolicyByVendorId(vendorId);
	}
	
	@Override
	public InsPolicy getPolicyByPolicyNo(String policyNo) {
		return insPolicyDao.getPolicyByPolicyNo(policyNo);
	}
	
	@Override
	@Transactional
	public void createInsPolicy(InsPolicy insPolicy) {
		if (insPolicyDao.isExistsVendorInsPolicy(insPolicy.getVendorId(), null)) {//
			throw new InsuranceBizRuntimeException(resultCode.get("biz.policy.repeat"), "商户不能重复投保！");
		}
		// 保存投保人
		InsPolicyHolder insPolicyHolder = insPolicy.getInsPolicyHolder();
		insPolicyHolder.setCreatedTime(new Date());
		insPolicyHolder.setModifiedTime(new Date());
		insPolicyHolderDao.add(insPolicyHolder);
		// 保存保单
		if (insPolicy.getInsurerInfoId() == null) {//保险人
			InsInsurerInfo iii = insInsurerInfoDao.getOne();
			insPolicy.setInsurerInfoId(iii.getId());
		}
		insPolicy.setPolicyHolderId(insPolicyHolder.getId());//投保人id
		insPolicy.setInsuredAmount(insPolicy.getPremium());
		String billNo = sysCodeRuleService.generateCodeNumber(WebConstant.CODE_INSPOLICY_POLICYNO);
		insPolicy.setPolicyNo(billNo);
		insPolicy.setInsureDate(new Date());
		insPolicy.setStatus(PolicyStatusEnum.NORMAL.val);
		
		InsGearingRule insGearingRule = insGearingRuleDao.getDefault();
		insPolicy.setCreditScore(insGearingRule.getCreditScore());//默认信用评分
		insPolicy.setGearing(insGearingRule.getGearing());//默认杠杆为1倍de 
		
		insPolicy.setValidFrom(new Date());
		insPolicy.setValidTo(DateUtil.addMonth(insPolicy.getValidFrom(), insPolicy.getInsurancePeriod()));//有效期
		insPolicy.setVersion(0);
		insPolicy.setCreatedTime(new Date());
		insPolicy.setModifiedTime(new Date());
		insPolicyDao.add(insPolicy);
		//保额信息
		InsInsuredAmountInfo iai = new InsInsuredAmountInfo();
		iai.setPolicyId(insPolicy.getId());
		iai.setInsuredAmount(insPolicy.getPremium());
		iai.setFrozenAmount(0l);
		iai.setBalance(insPolicy.getPremium());
		iai.setOptTime(new Date());
		iai.setVersion(0);
		insInsuredAmountInfoDao.add(iai);
        //设置缓存
	    InsCache.setInsPolicyCache(insPolicy.getVendorId(), insPolicy);
		//新建保单后，为保单保险证增加编码规则
		createVendorPolicyCertCodeRule(insPolicy.getVendorId());
		//3、通知保单实时信息！
		insPolicy.setInsInsuredAmountInfo(iai);
		noticePolicyRealtimeEvent(insPolicy);
	}
	
	/** 创建保单号对应保险证的编码规则 **/
	private void createVendorPolicyCertCodeRule(String vendorId) {
		SysCodeRule s = sysCodeRuleService.getDefaultSysCodeRule(WebConstant.CODE_INSINSURANCECERT_INSURANCECERTNO);//复制一份默认的规则
		s.setCu(vendorId);
		s.setId(null);
		s.setCurValue(null);
		s.setVersion(0l);
		sysCodeRuleService.addSysCodeRule(s);
	}
	
	/**
	 * 更新原保单状态为指定新状态。
	 * 
	 * @param oldInsPolicy
	 * @param newStatus
	 * @return
	 */
	private void updatePolicyStatus(InsPolicy oldInsPolicy, int newStatus) {
		int i = insPolicyDao.updatePolicyStatus(oldInsPolicy, newStatus);
		if (i == 0) {
			throw new InsuranceBizRuntimeException(resultCode.get("biz.policy.status.update.fail"), "保单状态改变失败！");
		}
		InsCache.removeInsPolicyCache(oldInsPolicy.getVendorId());
	}
	
	@Transactional
	public void freezeInsuredAmount(PolicyAmountChangeBean policyAmountChangeBean) {
		int ret = 0;
		int count = 0;
		InsPolicy ip = null;
		InsInsuredAmountInfo iai = null;
		Long beforeInsuredAmount = null;//冻结前余额
		do {//1.修改保额
			count++;
			if (count > 10) {//最多重试10次
				throw new InsuranceBizRuntimeException(resultCode.get("biz.policy.real.insured.amount.freeze.fail"), "保单保额冻结失败！");
			}
			ip = InsCache.getInsPolicyCache(policyAmountChangeBean.getVendorId());//insPolicyDao.getPolicyByVendorId(policyAmountChangeBean.getVendorId());
			if (ip == null) {
				throw new InsuranceBizRuntimeException(resultCode.get("biz.policy.notfound"), "保单号不存在！");
			}
			if (ip.getStatus() == PolicyStatusEnum.NOT_EFFECTIVED.val) {
				throw new InsuranceBizRuntimeException(resultCode.get("biz.policy.notfound"), "保单号不存在！");
			} else if (ip.getStatus() == PolicyStatusEnum.WARNING.val) {
				throw new InsuranceBizRuntimeException(resultCode.get("biz.policy.real.insured.amount.islimit"), "保单实时保额已到达警报界限！");
			}
			iai = insInsuredAmountInfoDao.getInsInsuredAmountInfoByPolicyId(ip.getId());
			beforeInsuredAmount = iai.getBalance();
			setFreezeInsInsuredAmountInfo(policyAmountChangeBean.getChangeAmount(), iai);
			// 计算状态 ，通过预警设置
			PolicyStatusEnum pse = calculateInsuredAmountState(iai.getFrozenAmount(), ip.getInsuredAmount());
			if (pse == PolicyStatusEnum.WARNING) {
				throw new InsuranceBizRuntimeException(resultCode.get("biz.policy.real.insured.amount.islimit"), "保单实时保额预计已到达警报界限！不允许新增保险证！");
			}
			ret = insInsuredAmountInfoDao.updateSelective(iai);
			if (ret > 0) {
				if (ip.getStatus() != pse.val) {//如果状态已经发生改变！
					updatePolicyStatus(ip, pse.val);
				}
			}
		    if (count > 1) {
		    	logger.warn(String.format("第%d次尝试冻结保额！", count));
			    try {
			    	TimeUnit.MILLISECONDS.sleep(50);
				} catch (InterruptedException e) {
				}
		    }
		} while (ret == 0);//不成功循环尝试冻结
		
		//2、增加变动流水。
		addInsPolicyChangeListFromChangeBean(policyAmountChangeBean, ip, iai, beforeInsuredAmount, ip.getPremium());
		//3、通知保单实时信息， 此处不通知，由生成保险证入口处通知。
		//ip.setInsInsuredAmountInfo(iai);
		//noticePolicyRealtimeEvent(ip);
	}
	
	private void addInsPolicyChangeListFromChangeBean(PolicyAmountChangeBean policyAmountChangeBean, InsPolicy ip, InsInsuredAmountInfo iai, long beforeInsuredAmount, long beforePremium) {
		InsPolicyChangeList pcl = new InsPolicyChangeList();
		pcl.setRefId(policyAmountChangeBean.getRefId());
		pcl.setRefVoucherNo(policyAmountChangeBean.getRefVoucherNo());
		pcl.setChangeAmount(policyAmountChangeBean.getChangeAmount());
		pcl.setType(policyAmountChangeBean.getType().val);
		pcl.setBeforeInsuredAmount(beforeInsuredAmount);
		pcl.setCurInsuredAmount(iai.getBalance());//当前保额
		pcl.setBeforePremium(beforePremium);
		pcl.setCurPremium(ip.getPremium());
		pcl.setVendorId(policyAmountChangeBean.getVendorId());
		pcl.setChangeTime(new Date());
		addInsPolicyChangeList(pcl);
	}

	/** 设置冻结保额信息 **/
	private void setFreezeInsInsuredAmountInfo(long freezeAmount, InsInsuredAmountInfo updateIA) {
		updateIA.setFrozenAmount(updateIA.getFrozenAmount() + freezeAmount);
		updateIA.setBalance(updateIA.getBalance() - freezeAmount);
		if (updateIA.getBalance() < 0) {
			throw new InsuranceBizRuntimeException(resultCode.get("biz.policy.real.insured.amount.notenough"), "保单保额不足！");
		}
		updateIA.setOptTime(new Date());
	}

	/** 设置解冻保额信息 **/
	private void setUnfreezeInsInsuredAmountInfo(InsPolicy ip, long freezeAmount, InsInsuredAmountInfo updateIA) {
		updateIA.setFrozenAmount(updateIA.getFrozenAmount() - freezeAmount);
		updateIA.setBalance(updateIA.getBalance() + freezeAmount);
		updateIA.setOptTime(new Date());
		//TODO 计算状态，如果状态改变，同时也需改变保单状态
		//iai.setStatus(status);
		//设置缓存
	    //InsCache.setInsPolicyCache(insPolicy.getVendorId(), insPolicy);
	}
	
	@Transactional
	public void unfreezeInsuredAmount(PolicyAmountChangeBean policyAmountChangeBean) {
//		if (policyAmountChangeBean.getType() != PolicyChangeListTypeEnum.EXPIRED_CERT 
//				&& policyAmountChangeBean.getType() != PolicyChangeListTypeEnum.REFUSE_REFUND) {
//			throw new IllegalArgumentException("不是失效或拒绝退款，不能释放保额！");
//		}
		int ret = 0;
		int count = 0;
		InsPolicy ip = null;
		InsInsuredAmountInfo iai = null;
		Long beforeInsuredAmount = null;//解冻前余额
		do {//1.修改保额
			count++;
			if (count > 10) {//最多重试10次
				throw new InsuranceBizRuntimeException(resultCode.get("biz.policy.real.insured.amount.unfreeze.fail"), "保单保额解冻失败！");
			}
			ip = InsCache.getInsPolicyCache(policyAmountChangeBean.getVendorId());//insPolicyDao.getPolicyByVendorId(policyAmountChangeBean.getVendorId());
			if (ip == null) {
				throw new InsuranceBizRuntimeException(resultCode.get("biz.policy.notfound"), "保单号不存在！");
			}
			if (ip.getStatus() == PolicyStatusEnum.NOT_EFFECTIVED.val) {
				throw new InsuranceBizRuntimeException(resultCode.get("biz.policy.notfound"), "保单号不存在！");
			}
			iai = insInsuredAmountInfoDao.getInsInsuredAmountInfoByPolicyId(ip.getId());
			beforeInsuredAmount = iai.getBalance();
			setUnfreezeInsInsuredAmountInfo(ip, policyAmountChangeBean.getChangeAmount(), iai);
			ret = insInsuredAmountInfoDao.updateSelective(iai);
		    if (count > 1) {
		    	logger.warn(String.format("第%d次尝试解冻保额！", count));
			    try {
			    	TimeUnit.MILLISECONDS.sleep(50);
				} catch (InterruptedException e) {
				}
		    }
		} while (ret == 0);//不成功循环尝试冻结
		
		//2、增加变动流水。
		addInsPolicyChangeListFromChangeBean(policyAmountChangeBean, ip, iai, beforeInsuredAmount, ip.getPremium());
		//3、通知保单实时信息
		ip.setInsInsuredAmountInfo(iai);
		noticePolicyRealtimeEvent(ip);
		
	}
	
	@Transactional
	public void addPolicyPremium(InsPolicyOrder insPolicyOrder) {
		int ret = 0;
		int count = 0;
		InsInsuredAmountInfo iai = null;
		InsPolicy ip = null;
		Long beforePremium = null;//增加前保费
		Long beforeInsuredAmount = null;//增加前余额
		do {//1.修改保额
			count++;
			if (count > 10) {//最多重试10次
				throw new InsuranceBizRuntimeException(resultCode.get("biz.policy.premium.add.fail"), "保单增加保费失败！");
			}
			ip = insPolicyDao.get(insPolicyOrder.getPolicyId());
			if (ip == null) {
				throw new InsuranceBizRuntimeException(resultCode.get("biz.policy.notfound"), "保单号不存在！");
			}
		    iai = insInsuredAmountInfoDao.getInsInsuredAmountInfoByPolicyId(ip.getId());
		    beforeInsuredAmount = iai.getBalance();
		    beforePremium = ip.getPremium();
		    setChangePremiumAndInsuredAmount(insPolicyOrder.getAmount(), ip, iai);//增加保费
		    //setAddPolicyAndInsuredAmount(insPolicyOrder.getAmount(), ip, iai);
		    ret = insPolicyDao.updateChangePolicyPremium(ip);
		    if (ret != 0) {//更新保单成功后，再更新保额。
		    	ret = insInsuredAmountInfoDao.updateSelective(iai);
		    }
		    if (count > 1) {
		    	logger.warn(String.format("第%d次尝试扣减保额！", count));
			    try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
				}
		    }
		} while (ret == 0);//不成功循环尝试增加
		//2、增加变动流水。
		InsPolicyChangeList pcl = new InsPolicyChangeList();
		pcl.setRefId(insPolicyOrder.getId());
		pcl.setRefVoucherNo(insPolicyOrder.getBillNo());
		pcl.setChangeAmount(insPolicyOrder.getAmount());
		pcl.setType(PolicyChangeListTypeEnum.CHARGE_PREMIUM.val);
		pcl.setBeforeInsuredAmount(beforeInsuredAmount);
		pcl.setCurInsuredAmount(iai.getBalance());//当前保额
		pcl.setBeforePremium(beforePremium);
		pcl.setCurPremium(ip.getPremium());
		pcl.setVendorId(insPolicyOrder.getVendorId());
		pcl.setChangeTime(new Date());
		addInsPolicyChangeList(pcl);
		//刷新缓存
	    InsCache.removeInsPolicyCache(ip.getVendorId());
		//3、通知保单实时信息！
		ip.setInsInsuredAmountInfo(iai);
		noticePolicyRealtimeEvent(ip);
	}
	
	/**
	 * 通知保单实时信息，若有异常则忽略
	 * 
	 * @param ip
	 */
	private void noticePolicyRealtimeEvent(InsPolicy ip) {
		try {
			InsurancePolicyEvent event = new InsurancePolicyEvent(this);
			event.setVendorId(ip.getVendorId());
			event.setCurInsPolicy(ip);
			InsuranceEventDefaultHandler.getInstance().doPremiumChange(event);
		} catch (Exception e) {
			logger.warn("通知保单实时信息出现异常，忽略", e);
		}
	}
	
	/** 设置增加费信息 **/
	@Deprecated
	private void setAddPolicyAndInsuredAmount(Long addingPremium, InsPolicy ip, InsInsuredAmountInfo iai) {
		ip.setPremium(ip.getPremium() + addingPremium);//保费增加
		Long oldInsuredAmount = ip.getInsuredAmount();//原来保额
		ip.setInsuredAmount(ip.getPremium()*ip.getGearing());//现在保额。
		//TODO 计算状态 ，通过预警设置
		if (ip.getStatus() == 0) {
			ip.setStatus(PolicyStatusEnum.NORMAL.val);
		}
		ip.setModifiedTime(new Date());
		
		//保额信息
		long newAddingInsuredAmount = ip.getInsuredAmount() - oldInsuredAmount;//新增的保额
		iai.setInsuredAmount(ip.getInsuredAmount());
		iai.setBalance(iai.getBalance() + newAddingInsuredAmount);//余额变动,新增的保额累加到实时保额
		iai.setOptTime(new Date());
	}
	
	public boolean isExistsVendorInsPolicy(String vendorId, Long excludeId) {
		return insPolicyDao.isExistsVendorInsPolicy(vendorId, excludeId);
	}
	
	@Transactional
	public void deductPolicyPremium(PolicyAmountChangeBean policyAmountChangeBean) {
		int ret = 0;
		int count = 0;
		InsInsuredAmountInfo iai = null;
		InsPolicy ip = null;
		Long beforePremium = null;//增加前保费
		Long beforeInsuredAmount = null;//增加前余额
		do {//1.修改保额
			count++;
			if (count > 10) {//最多重试10次
				throw new InsuranceBizRuntimeException(resultCode.get("biz.policy.real.insured.amount.deduct.fail"), "保单扣减保费失败！");
			}
			ip = insPolicyDao.getPolicyByVendorId(policyAmountChangeBean.getVendorId());
			if (ip == null) {
				throw new InsuranceBizRuntimeException(resultCode.get("biz.policy.notfound"), "保单号不存在！");
			}
		    iai = insInsuredAmountInfoDao.getInsInsuredAmountInfoByPolicyId(ip.getId());
		    beforeInsuredAmount = iai.getBalance();
		    beforePremium = ip.getPremium();
		    setChangePremiumAndInsuredAmount(-policyAmountChangeBean.getChangeAmount(), ip, iai);//扣保费
		   // setDeductPolicyAndInsuredAmount(policyAmountChangeBean.getChangeAmount(), ip, iai);
		    ret = insPolicyDao.updateChangePolicyPremium(ip);
		    if (ret != 0) {//更新保单成功后，再更新保额。
		    	ret = insInsuredAmountInfoDao.updateSelective(iai);
		    }
		    if (count > 1) {
		    	logger.warn(String.format("第%d次尝试扣减保额！", count));
			    try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
				}
		    }
		} while (ret == 0);//不成功循环尝试增加

		//2、增加变动流水。
		InsPolicyChangeList pcl = new InsPolicyChangeList();
		pcl.setRefId(policyAmountChangeBean.getRefId());
		pcl.setRefVoucherNo(policyAmountChangeBean.getRefVoucherNo());
		pcl.setChangeAmount(policyAmountChangeBean.getChangeAmount());
		pcl.setType(policyAmountChangeBean.getType().val);
		pcl.setBeforeInsuredAmount(beforeInsuredAmount);
		pcl.setCurInsuredAmount(iai.getBalance());//当前保额
		pcl.setBeforePremium(beforePremium);
		pcl.setCurPremium(ip.getPremium());
		pcl.setVendorId(policyAmountChangeBean.getVendorId());
		pcl.setChangeTime(new Date());
		addInsPolicyChangeList(pcl);
		//刷新缓存
	    InsCache.removeInsPolicyCache(ip.getVendorId());
		//3、通知保单实时信息！
		ip.setInsInsuredAmountInfo(iai);
		noticePolicyRealtimeEvent(ip);
	}
	
	/** 设置扣减保费信息 **/
	@Deprecated
	private void setDeductPolicyAndInsuredAmount(Long amount, InsPolicy ip, InsInsuredAmountInfo iai) {
		ip.setPremium(ip.getPremium() - amount);//保费扣减
		Long oldInsuredAmount = ip.getInsuredAmount();
		ip.setInsuredAmount(ip.getPremium()*ip.getGearing());//保额变动。
		if (ip.getStatus() == 0) {
			ip.setStatus(PolicyStatusEnum.NORMAL.val);
		}
		ip.setModifiedTime(new Date());
		
		//保额信息
		long newDeductingInsuredAmount = oldInsuredAmount - ip.getInsuredAmount();//减少的保额
		iai.setInsuredAmount(ip.getInsuredAmount());
		iai.setBalance(iai.getBalance() - newDeductingInsuredAmount);//余额变动,余额也相应扣减减少的保额
		iai.setOptTime(new Date());
	}
	
	/**
	 * 设置保费或保额变动信息 
	 * 
	 * @param amount 大于0为新增保费；小于0为减少保费，等于0为调整杠杆
	 * @param ip
	 * @param iai
	 */
	private void setChangePremiumAndInsuredAmount(Long amount, InsPolicy ip, InsInsuredAmountInfo iai) {
		ip.setPremium(ip.getPremium() + amount);//保费扣减
		Long oldInsuredAmount = ip.getInsuredAmount();//原保额
		Long newInsuredAmount = ip.getPremium()*ip.getGearing();//新保额
		ip.setInsuredAmount(newInsuredAmount);//保额变动。
		//保额信息
		long changingInsuredAmount = newInsuredAmount - oldInsuredAmount;//变动的保额,大于0就增加保额，小于0就减少保额
		iai.setInsuredAmount(ip.getInsuredAmount());
		iai.setBalance(iai.getBalance() + changingInsuredAmount);//余额变动
		iai.setOptTime(new Date());
		// 计算状态 ，通过预警设置
		PolicyStatusEnum pse = calculateInsuredAmountState(iai.getFrozenAmount(), newInsuredAmount);
		ip.setStatus(pse.val);
	}
	
	/**
	 * 计算预警状态。
	 * 
	 * @param usedInsuredAmount
	 * @param insuredAmount
	 * @param wr
	 * @return
	 */
	private PolicyStatusEnum calculateInsuredAmountState(Long usedInsuredAmount, Long insuredAmount) {
		InsWarningRule wr = InsCache.getInsWarningRuleCache();
		double iuseper = usedInsuredAmount*1.0/insuredAmount;
		BigDecimal bg = new BigDecimal(iuseper);
		double useper = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		if (useper > wr.getRedMin()){//红色区
			return PolicyStatusEnum.WARNING;
		} else if (useper > wr.getYellowMin() && useper <= wr.getYellowMax()) {//黄色区
			return PolicyStatusEnum.PRE_WARN;
		} else if (useper <= wr.getGreenMax()) {//绿色区
			return PolicyStatusEnum.NORMAL;
		} else {
			throw new IllegalArgumentException("预警设置错误！！");
		}
	}
	
	@Override
	public InsPolicy getFullInsPolicy(String vendorId) {
		return insPolicyDao.getFullInsPolicy(vendorId);
	}
	
	@Override
	public List<InsPolicy> getInsPolicyList(GetPolicyListInfo bean, Page<InsPolicy> page) {
		return insPolicyDao.getInsPolicyList(bean, page);
	}

	@Transactional
	public InsPolicy adjustGearing(Long insUserId, Long policyId, Integer gearingRuleId) {
		InsPolicy insPolicy = insPolicyDao.getPolicyByPolicyId(policyId);
		InsGearingRule rule = insGearingRuleDao.get(gearingRuleId);
		if (rule.getGearing() == insPolicy.getGearing()) {
			throw new InsuranceBizRuntimeException(resultCode.get("biz.policy.gearing.adjust.notchange"), "杠杆没有变化！");
		}
		//1更新保单信息
		insPolicy.setCreditScore(rule.getCreditScore());
		insPolicy.setGearing(rule.getGearing());
		insPolicy.setModifiedTime(new Date());
		//调整保额信息
		InsInsuredAmountInfo iai = insInsuredAmountInfoDao.getInsInsuredAmountInfoByPolicyId(policyId);
		Long beforeInsuredAmount = insPolicy.getInsuredAmount();//调整之前保额
		setChangePremiumAndInsuredAmount(0l, insPolicy, iai);
		int i = insPolicyDao.updateSelective(insPolicy);
		if (i > 0) {
			i = insInsuredAmountInfoDao.updateSelective(iai);
		}
		if (i == 0) {
			throw new InsuranceBizRuntimeException(resultCode.get("biz.policy.gearing.adjust.fail"), "保单调整杠杆失败！可能记录已被更新。");
		}
		//2增加调整历史记录
		InsUser insUser = insUserDao.get(insUserId);
		InsGearingAdjustHis his = new InsGearingAdjustHis();
		his.setPolicyId(insPolicy.getId());
		his.setAdjusttime(new Date());
		his.setCreditScore(insPolicy.getCreditScore());
		his.setGearing(insPolicy.getGearing());
		his.setPremium(insPolicy.getPremium());
		his.setInsuredAmount(insPolicy.getInsuredAmount());
		his.setCreatedUser(insUser.getUserAccount());
		his.setModifiedUser(insUser.getUserAccount());
		his.setCreatedTime(new Date());
		insGearingAdjustHisDao.add(his);
		
		//3、增加变动流水。
		//addInsPolicyChangeList(policyAmountChangeBean, ip, iai, beforeInsuredAmount, ip.getPremium());
		InsPolicyChangeList pcl = new InsPolicyChangeList();
		pcl.setRefId(insPolicy.getId());
		pcl.setRefVoucherNo(insPolicy.getPolicyNo());
		pcl.setChangeAmount(Math.abs(insPolicy.getInsuredAmount()-beforeInsuredAmount));//绝对值。
		pcl.setType(PolicyChangeListTypeEnum.ADJUST_GEARING.val);
		pcl.setBeforeInsuredAmount(beforeInsuredAmount);
		pcl.setCurInsuredAmount(iai.getBalance());//当前保额
		pcl.setBeforePremium(insPolicy.getPremium());
		pcl.setCurPremium(insPolicy.getPremium());
		pcl.setVendorId(insPolicy.getVendorId());
		pcl.setChangeTime(new Date());
		addInsPolicyChangeList(pcl);
		//清除缓存?同步？
		InsCache.removeInsPolicyCache(insPolicy.getVendorId());
		//4、通知保单实时信息
		insPolicy.setInsInsuredAmountInfo(iai);
	 	noticePolicyRealtimeEvent(insPolicy);
		return insPolicy;
	}
	
	private void addInsPolicyChangeList(InsPolicyChangeList pcl) {
		int i = insPolicyChangeListDao.add(pcl);
		if (i == 0) {
			throw new InsuranceBizRuntimeException(resultCode.get("biz.policy.change.list.add.fail"), "新增保单变动流水失败！");
		}
	}
	
	@Override
	public void reCalculateInsuredAmountState() {
		//TODO 海量数据处理
		List<InsPolicy> list = insPolicyDao.getAllInsPolicyAndInsuredAmountInfo();
		for (InsPolicy ip: list) {
			PolicyStatusEnum pse = calculateInsuredAmountState(ip.getInsInsuredAmountInfo().getFrozenAmount(), ip.getInsuredAmount());
			if (ip.getStatus() != pse.val) {//状态改变
				updatePolicyStatus(ip, pse.val);
				ip.setStatus(pse.val);//通知客户端状态变更
			 	noticePolicyRealtimeEvent(ip);
			}
		}
	}
}
