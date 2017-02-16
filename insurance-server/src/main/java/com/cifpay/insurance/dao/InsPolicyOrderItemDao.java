package com.cifpay.insurance.dao;

import java.util.List;

import com.cifpay.insurance.model.InsPolicyOrderItem;
import com.cifpay.starframework.dao.CommonDao;

public interface InsPolicyOrderItemDao extends CommonDao<InsPolicyOrderItem> {
public InsPolicyOrderItem get(long id);
public List<InsPolicyOrderItem> getList();
public int getCount();
}
