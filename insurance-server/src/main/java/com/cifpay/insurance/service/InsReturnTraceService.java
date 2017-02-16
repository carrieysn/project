package com.cifpay.insurance.service;

import java.util.List;

import com.cifpay.insurance.model.InsReturnTrace;
import com.cifpay.starframework.service.CommonService;

public interface InsReturnTraceService extends CommonService<InsReturnTrace> {
public InsReturnTrace get(long ID);
public List<InsReturnTrace> getList();
public int getCount();
public List<InsReturnTrace> getListByCertNo(String certNo);
}
