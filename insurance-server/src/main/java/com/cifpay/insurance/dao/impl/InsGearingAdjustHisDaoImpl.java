package com.cifpay.insurance.dao.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cifpay.insurance.dao.InsGearingAdjustHisDao;
import com.cifpay.insurance.model.InsGearingAdjustHis;
import com.cifpay.starframework.dao.impl.CommonDaoImpl;

@Repository("insGearingAdjustHisDao")
public class InsGearingAdjustHisDaoImpl extends CommonDaoImpl<InsGearingAdjustHis> implements InsGearingAdjustHisDao {
@Override
public InsGearingAdjustHis get(long id) {
return this.getSqlSession().selectOne(getStatementPrefix() + ".get", id);
}
@Override
public List<InsGearingAdjustHis> getList() {List<InsGearingAdjustHis> resultList = this.getSqlSession().selectList(getStatementPrefix() + ".getList");
if (resultList == null) {
resultList = Collections.emptyList();
}
return resultList;
}
@Override
public List<InsGearingAdjustHis> getListByPolicyId(String policyId) {
	List<InsGearingAdjustHis> resultList = this.getSqlSession().selectList(getStatementPrefix() + ".getListByPolicyId",policyId);
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
