package com.cifpay.insurance.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cifpay.insurance.cache.InsCache;
import com.cifpay.insurance.dao.InsUserDao;
import com.cifpay.insurance.dao.InsWarningRuleDao;
import com.cifpay.insurance.model.InsUser;
import com.cifpay.insurance.model.InsWarningRule;
import com.cifpay.insurance.service.InsPolicyService;
import com.cifpay.insurance.service.InsWarningRuleService;
import com.cifpay.starframework.cache.ServiceResultCodeCache;
import com.cifpay.starframework.model.ServiceResult;

@Service("insWarningRuleService")
public class InsWarningRuleServiceImpl implements InsWarningRuleService {
	private static ServiceResultCodeCache resultCode = ServiceResultCodeCache.getInstance();
	@Autowired
	private InsWarningRuleDao insWarningRuleDao;
	@Autowired
	private InsUserDao insUserDao;
	@Autowired
	private InsPolicyService insPolicyService;

	@Override
	public InsWarningRule get(int id) {
		return insWarningRuleDao.get(id);
	}

	@Override
	public ServiceResult<String> add(InsWarningRule insWarningRule) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insWarningRuleDao.add(insWarningRule);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> addSelective(InsWarningRule insWarningRule) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insWarningRuleDao.addSelective(insWarningRule);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> update(InsWarningRule insWarningRule) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insWarningRuleDao.update(insWarningRule);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> updateSelective(InsWarningRule insWarningRule) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insWarningRuleDao.updateSelective(insWarningRule);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> delete(InsWarningRule insWarningRule) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insWarningRuleDao.delete(insWarningRule);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public List<InsWarningRule> getList() {
		return insWarningRuleDao.getList();
	}

	@Override
	public int getCount() {
		return insWarningRuleDao.getCount();
	}

	@Override
	public InsWarningRule getOne() {
		return insWarningRuleDao.getOne();
	}

	@Override
	@Transactional
	public boolean changeWarningRule(Long insUserId, Double greenMax, Double yellowMax) {
		if(insUserId != null && greenMax != null && yellowMax != null && greenMax > 0 && yellowMax < 1 && yellowMax.compareTo(greenMax) > 0){
			InsUser insUser = insUserDao.get(insUserId);
			InsWarningRule insWarningRule = insWarningRuleDao.getOne();
			if(insUser != null && insWarningRule != null && (!insWarningRule.getGreenMax().equals(greenMax) || !insWarningRule.getYellowMax().equals(yellowMax))){
				insWarningRule.setGreenMax(greenMax);
				insWarningRule.setYellowMin(greenMax);
				insWarningRule.setYellowMax(yellowMax);
				insWarningRule.setRedMin(yellowMax);
				insWarningRule.setModifiedTime(new Date());
				insWarningRule.setModifiedUser(insUser.getUserAccount());
				insWarningRuleDao.update(insWarningRule);
				InsCache.removeInsWarningRuleCache();
				//更新保单相关状态
				insPolicyService.reCalculateInsuredAmountState();
				return true;
			}
		}
		return false;
	}
}
