package com.cifpay.insurance.service;

import java.util.List;

import com.cifpay.insurance.model.InsSalesOrder;
import com.cifpay.starframework.service.CommonService;

public interface InsSalesOrderService extends CommonService<InsSalesOrder> {
public InsSalesOrder get(long id);
public List<InsSalesOrder> getList();
public int getCount();
}
