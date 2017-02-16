package com.cifpay.insurance.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cifpay.insurance.dao.SysCodeRuleDtDao;
import com.cifpay.insurance.model.SysCodeRuleDt;
import com.cifpay.insurance.service.SysCodeRuleDtService;
import com.cifpay.starframework.cache.ServiceResultCodeCache;
import com.cifpay.starframework.model.ServiceResult;

@Service("sysCodeRuleDtService")
public class SysCodeRuleDtServiceImpl implements SysCodeRuleDtService {
private static ServiceResultCodeCache resultCode = ServiceResultCodeCache.getInstance();
@Autowired
private SysCodeRuleDtDao sysCodeRuleDtDao;
@Override
public SysCodeRuleDt get(long id) {
return sysCodeRuleDtDao.get(id);
}
@Override
public ServiceResult<String> add(SysCodeRuleDt sysCodeRuleDt) {
ServiceResult<String> serviceResult = new ServiceResult<String>();
int result = sysCodeRuleDtDao.add(sysCodeRuleDt);
if (result == 1) {
	serviceResult.setCode(resultCode.get("common.sucess"));
} else {
serviceResult.setCode(resultCode.get("common.fail"));
}
return serviceResult;
}
@Override
public ServiceResult<String> addSelective(SysCodeRuleDt sysCodeRuleDt) {
ServiceResult<String> serviceResult = new ServiceResult<String>();
int result = sysCodeRuleDtDao.addSelective(sysCodeRuleDt);
if (result == 1) {
	serviceResult.setCode(resultCode.get("common.sucess"));
} else {
serviceResult.setCode(resultCode.get("common.fail"));
}
return serviceResult;
}
@Override
public ServiceResult<String> update(SysCodeRuleDt sysCodeRuleDt) {
ServiceResult<String> serviceResult = new ServiceResult<String>();
int result = sysCodeRuleDtDao.update(sysCodeRuleDt);
if (result == 1) {
	serviceResult.setCode(resultCode.get("common.sucess"));
} else {
serviceResult.setCode(resultCode.get("common.fail"));
}
return serviceResult;
}
@Override
public ServiceResult<String> updateSelective(SysCodeRuleDt sysCodeRuleDt) {
ServiceResult<String> serviceResult = new ServiceResult<String>();
int result = sysCodeRuleDtDao.updateSelective(sysCodeRuleDt);
if (result == 1) {
	serviceResult.setCode(resultCode.get("common.sucess"));
} else {
serviceResult.setCode(resultCode.get("common.fail"));
}
return serviceResult;
}
@Override
public ServiceResult<String> delete(SysCodeRuleDt sysCodeRuleDt) {
ServiceResult<String> serviceResult = new ServiceResult<String>();
int result = sysCodeRuleDtDao.delete(sysCodeRuleDt);
if (result == 1) {
	serviceResult.setCode(resultCode.get("common.sucess"));
} else {
serviceResult.setCode(resultCode.get("common.fail"));
}
return serviceResult;
}
@Override
public List<SysCodeRuleDt> getList() {
return sysCodeRuleDtDao.getList();
}
@Override
public int getCount() {
return sysCodeRuleDtDao.getCount();
}
}
