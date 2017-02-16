package com.cifpay.insurance.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cifpay.insurance.dao.InsGearingRuleDao;
import com.cifpay.insurance.dao.InsUserDao;
import com.cifpay.insurance.model.InsGearingRule;
import com.cifpay.insurance.model.InsUser;
import com.cifpay.insurance.service.InsGearingRuleService;
import com.cifpay.insurance.service.InsPolicyService;
import com.cifpay.starframework.cache.ServiceResultCodeCache;
import com.cifpay.starframework.model.ServiceResult;

@Service("insGearingRuleService")
public class InsGearingRuleServiceImpl implements InsGearingRuleService {
private static ServiceResultCodeCache resultCode = ServiceResultCodeCache.getInstance();
@Autowired
private InsGearingRuleDao insGearingRuleDao;
@Autowired
private InsUserDao insUserDao;
@Autowired
private InsPolicyService insPolicyService;
@Override
public InsGearingRule get(int id) {
return insGearingRuleDao.get(id);
}
@Override
public ServiceResult<String> add(InsGearingRule insGearingRule) {
ServiceResult<String> serviceResult = new ServiceResult<String>();
int result = insGearingRuleDao.add(insGearingRule);
if (result == 1) {
	serviceResult.setCode(resultCode.get("common.sucess"));
} else {
serviceResult.setCode(resultCode.get("common.fail"));
}
return serviceResult;
}
@Override
public ServiceResult<String> addSelective(InsGearingRule insGearingRule) {
ServiceResult<String> serviceResult = new ServiceResult<String>();
int result = insGearingRuleDao.addSelective(insGearingRule);
if (result == 1) {
	serviceResult.setCode(resultCode.get("common.sucess"));
} else {
serviceResult.setCode(resultCode.get("common.fail"));
}
return serviceResult;
}
@Override
public ServiceResult<String> update(InsGearingRule insGearingRule) {
ServiceResult<String> serviceResult = new ServiceResult<String>();
int result = insGearingRuleDao.update(insGearingRule);
if (result == 1) {
	serviceResult.setCode(resultCode.get("common.sucess"));
} else {
serviceResult.setCode(resultCode.get("common.fail"));
}
return serviceResult;
}
@Override
public ServiceResult<String> updateSelective(InsGearingRule insGearingRule) {
ServiceResult<String> serviceResult = new ServiceResult<String>();
int result = insGearingRuleDao.updateSelective(insGearingRule);
if (result == 1) {
	serviceResult.setCode(resultCode.get("common.sucess"));
} else {
serviceResult.setCode(resultCode.get("common.fail"));
}
return serviceResult;
}
@Override
public ServiceResult<String> delete(InsGearingRule insGearingRule) {
ServiceResult<String> serviceResult = new ServiceResult<String>();
int result = insGearingRuleDao.delete(insGearingRule);
if (result == 1) {
	serviceResult.setCode(resultCode.get("common.sucess"));
} else {
serviceResult.setCode(resultCode.get("common.fail"));
}
return serviceResult;
}
@Override
public List<InsGearingRule> getList() {
return insGearingRuleDao.getList();
}
@Override
public int getCount() {
return insGearingRuleDao.getCount();
}

@Override
@Transactional
public boolean adjustGearingRule(Long insUserId, Integer id, Integer creditScore, Integer gearing) {
	if(insUserId != null && id != null && creditScore != null && gearing != null){
		InsUser insUser = insUserDao.get(insUserId);
		InsGearingRule insGearingRule = insGearingRuleDao.get(id);
		if(insGearingRule != null && insUser != null && (!insGearingRule.getCreditScore().equals(creditScore) || !insGearingRule.getGearing().equals(gearing))){
			insGearingRule.setCreditScore(creditScore);
			insGearingRule.setGearing(gearing);
			insGearingRule.setModifiedTime(new Date());
			insGearingRule.setModifiedUser(insUser.getUserAccount());
			insGearingRuleDao.update(insGearingRule);
			
			insPolicyService.reCalculateInsuredAmountState();
			return true;
		}
	}
	return false;
}

@Override
public InsGearingRule getDefault() {
	return insGearingRuleDao.getDefault();
}
}
