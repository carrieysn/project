package com.cifpay.insurance.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cifpay.insurance.dao.InsSalesOrderDao;
import com.cifpay.insurance.model.InsSalesOrder;
import com.cifpay.insurance.service.InsSalesOrderService;
import com.cifpay.starframework.cache.ServiceResultCodeCache;
import com.cifpay.starframework.model.ServiceResult;

@Service("insSalesOrderService")
public class InsSalesOrderServiceImpl implements InsSalesOrderService {
private static ServiceResultCodeCache resultCode = ServiceResultCodeCache.getInstance();
@Autowired
private InsSalesOrderDao insSalesOrderDao;
@Override
public InsSalesOrder get(long id) {
return insSalesOrderDao.get(id);
}
@Override
public ServiceResult<String> add(InsSalesOrder insSalesOrder) {
ServiceResult<String> serviceResult = new ServiceResult<String>();
int result = insSalesOrderDao.add(insSalesOrder);
if (result == 1) {
	serviceResult.setCode(resultCode.get("common.sucess"));
} else {
serviceResult.setCode(resultCode.get("common.fail"));
}
return serviceResult;
}
@Override
public ServiceResult<String> addSelective(InsSalesOrder insSalesOrder) {
ServiceResult<String> serviceResult = new ServiceResult<String>();
int result = insSalesOrderDao.addSelective(insSalesOrder);
if (result == 1) {
	serviceResult.setCode(resultCode.get("common.sucess"));
} else {
serviceResult.setCode(resultCode.get("common.fail"));
}
return serviceResult;
}
@Override
public ServiceResult<String> update(InsSalesOrder insSalesOrder) {
ServiceResult<String> serviceResult = new ServiceResult<String>();
int result = insSalesOrderDao.update(insSalesOrder);
if (result == 1) {
	serviceResult.setCode(resultCode.get("common.sucess"));
} else {
serviceResult.setCode(resultCode.get("common.fail"));
}
return serviceResult;
}
@Override
public ServiceResult<String> updateSelective(InsSalesOrder insSalesOrder) {
ServiceResult<String> serviceResult = new ServiceResult<String>();
int result = insSalesOrderDao.updateSelective(insSalesOrder);
if (result == 1) {
	serviceResult.setCode(resultCode.get("common.sucess"));
} else {
serviceResult.setCode(resultCode.get("common.fail"));
}
return serviceResult;
}
@Override
public ServiceResult<String> delete(InsSalesOrder insSalesOrder) {
ServiceResult<String> serviceResult = new ServiceResult<String>();
int result = insSalesOrderDao.delete(insSalesOrder);
if (result == 1) {
	serviceResult.setCode(resultCode.get("common.sucess"));
} else {
serviceResult.setCode(resultCode.get("common.fail"));
}
return serviceResult;
}
@Override
public List<InsSalesOrder> getList() {
return insSalesOrderDao.getList();
}
@Override
public int getCount() {
return insSalesOrderDao.getCount();
}
}
