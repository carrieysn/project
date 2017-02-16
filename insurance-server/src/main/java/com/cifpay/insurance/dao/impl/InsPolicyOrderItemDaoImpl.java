package com.cifpay.insurance.dao.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cifpay.insurance.dao.InsPolicyOrderItemDao;
import com.cifpay.insurance.model.InsPolicyOrderItem;
import com.cifpay.starframework.dao.impl.CommonDaoImpl;

@Repository("insPolicyOrderItemDao")
public class InsPolicyOrderItemDaoImpl extends CommonDaoImpl<InsPolicyOrderItem> implements InsPolicyOrderItemDao {
@Override
public InsPolicyOrderItem get(long id) {
return this.getSqlSession().selectOne(getStatementPrefix() + ".get", id);
}
@Override
public List<InsPolicyOrderItem> getList() {List<InsPolicyOrderItem> resultList = this.getSqlSession().selectList(getStatementPrefix() + ".getList");
if (resultList == null) {
resultList = Collections.emptyList();
}
return resultList;
}
@Override
public int getCount() {
Integer result = (Integer) this.getSqlSession().selectOne(getStatementPrefix() + ".getCount");
return result != null ? result.intValue() : 0;
}
}
