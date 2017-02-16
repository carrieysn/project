package com.cifpay.insurance.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cifpay.insurance.dao.InsGearingAdjustHisDao;
import com.cifpay.insurance.model.InsGearingAdjustHis;
import com.cifpay.insurance.service.InsGearingAdjustHisService;
import com.cifpay.insurance.util.StringUtils;
import com.cifpay.starframework.cache.ServiceResultCodeCache;
import com.cifpay.starframework.model.ServiceResult;

@Service("insGearingAdjustHisService")
public class InsGearingAdjustHisServiceImpl implements InsGearingAdjustHisService {
private static ServiceResultCodeCache resultCode = ServiceResultCodeCache.getInstance();
@Autowired
private InsGearingAdjustHisDao insGearingAdjustHisDao;
@Override
public InsGearingAdjustHis get(long id) {
return insGearingAdjustHisDao.get(id);
}
@Override
public ServiceResult<String> add(InsGearingAdjustHis insGearingAdjustHis) {
ServiceResult<String> serviceResult = new ServiceResult<String>();
int result = insGearingAdjustHisDao.add(insGearingAdjustHis);
if (result == 1) {
	serviceResult.setCode(resultCode.get("common.sucess"));
} else {
serviceResult.setCode(resultCode.get("common.fail"));
}
return serviceResult;
}
@Override
public ServiceResult<String> addSelective(InsGearingAdjustHis insGearingAdjustHis) {
ServiceResult<String> serviceResult = new ServiceResult<String>();
int result = insGearingAdjustHisDao.addSelective(insGearingAdjustHis);
if (result == 1) {
	serviceResult.setCode(resultCode.get("common.sucess"));
} else {
serviceResult.setCode(resultCode.get("common.fail"));
}
return serviceResult;
}
@Override
public ServiceResult<String> update(InsGearingAdjustHis insGearingAdjustHis) {
ServiceResult<String> serviceResult = new ServiceResult<String>();
int result = insGearingAdjustHisDao.update(insGearingAdjustHis);
if (result == 1) {
	serviceResult.setCode(resultCode.get("common.sucess"));
} else {
serviceResult.setCode(resultCode.get("common.fail"));
}
return serviceResult;
}
@Override
public ServiceResult<String> updateSelective(InsGearingAdjustHis insGearingAdjustHis) {
ServiceResult<String> serviceResult = new ServiceResult<String>();
int result = insGearingAdjustHisDao.updateSelective(insGearingAdjustHis);
if (result == 1) {
	serviceResult.setCode(resultCode.get("common.sucess"));
} else {
serviceResult.setCode(resultCode.get("common.fail"));
}
return serviceResult;
}
@Override
public ServiceResult<String> delete(InsGearingAdjustHis insGearingAdjustHis) {
ServiceResult<String> serviceResult = new ServiceResult<String>();
int result = insGearingAdjustHisDao.delete(insGearingAdjustHis);
if (result == 1) {
	serviceResult.setCode(resultCode.get("common.sucess"));
} else {
serviceResult.setCode(resultCode.get("common.fail"));
}
return serviceResult;
}
@Override
public List<InsGearingAdjustHis> getList() {
return insGearingAdjustHisDao.getList();
}
@Override
public List<InsGearingAdjustHis> getListByPolicyId(String policyId) {
	if(StringUtils.isEmpty(policyId)) return null;
	return insGearingAdjustHisDao.getListByPolicyId(policyId);
}
@Override
public int getCount() {
return insGearingAdjustHisDao.getCount();
}
}
