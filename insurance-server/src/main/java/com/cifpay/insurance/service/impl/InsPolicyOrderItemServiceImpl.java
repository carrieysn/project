package com.cifpay.insurance.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cifpay.insurance.dao.InsPolicyOrderItemDao;
import com.cifpay.insurance.model.InsPolicyOrderItem;
import com.cifpay.insurance.service.InsPolicyOrderItemService;
import com.cifpay.starframework.cache.ServiceResultCodeCache;
import com.cifpay.starframework.model.ServiceResult;

@Service("insPolicyOrderItemService")
public class InsPolicyOrderItemServiceImpl implements InsPolicyOrderItemService {
private static ServiceResultCodeCache resultCode = ServiceResultCodeCache.getInstance();
@Autowired
private InsPolicyOrderItemDao insPolicyOrderItemDao;
@Override
public InsPolicyOrderItem get(long id) {
return insPolicyOrderItemDao.get(id);
}
@Override
public ServiceResult<String> add(InsPolicyOrderItem insPolicyOrderItem) {
ServiceResult<String> serviceResult = new ServiceResult<String>();
int result = insPolicyOrderItemDao.add(insPolicyOrderItem);
if (result == 1) {
	serviceResult.setCode(resultCode.get("common.sucess"));
} else {
serviceResult.setCode(resultCode.get("common.fail"));
}
return serviceResult;
}
@Override
public ServiceResult<String> addSelective(InsPolicyOrderItem insPolicyOrderItem) {
ServiceResult<String> serviceResult = new ServiceResult<String>();
int result = insPolicyOrderItemDao.addSelective(insPolicyOrderItem);
if (result == 1) {
	serviceResult.setCode(resultCode.get("common.sucess"));
} else {
serviceResult.setCode(resultCode.get("common.fail"));
}
return serviceResult;
}
@Override
public ServiceResult<String> update(InsPolicyOrderItem insPolicyOrderItem) {
ServiceResult<String> serviceResult = new ServiceResult<String>();
int result = insPolicyOrderItemDao.update(insPolicyOrderItem);
if (result == 1) {
	serviceResult.setCode(resultCode.get("common.sucess"));
} else {
serviceResult.setCode(resultCode.get("common.fail"));
}
return serviceResult;
}
@Override
public ServiceResult<String> updateSelective(InsPolicyOrderItem insPolicyOrderItem) {
ServiceResult<String> serviceResult = new ServiceResult<String>();
int result = insPolicyOrderItemDao.updateSelective(insPolicyOrderItem);
if (result == 1) {
	serviceResult.setCode(resultCode.get("common.sucess"));
} else {
serviceResult.setCode(resultCode.get("common.fail"));
}
return serviceResult;
}
@Override
public ServiceResult<String> delete(InsPolicyOrderItem insPolicyOrderItem) {
ServiceResult<String> serviceResult = new ServiceResult<String>();
int result = insPolicyOrderItemDao.delete(insPolicyOrderItem);
if (result == 1) {
	serviceResult.setCode(resultCode.get("common.sucess"));
} else {
serviceResult.setCode(resultCode.get("common.fail"));
}
return serviceResult;
}
@Override
public List<InsPolicyOrderItem> getList() {
return insPolicyOrderItemDao.getList();
}
@Override
public int getCount() {
return insPolicyOrderItemDao.getCount();
}
}
