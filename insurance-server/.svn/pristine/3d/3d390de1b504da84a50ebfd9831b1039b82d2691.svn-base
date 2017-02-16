package com.cifpay.insurance.dao.impl;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.cifpay.insurance.base.Page;
import com.cifpay.insurance.dao.InsPolicyChangeListDao;
import com.cifpay.insurance.model.InsPolicyChangeList;
import com.cifpay.insurance.param.policy.GetPolicyChangeListInfo;
import com.cifpay.insurance.util.DateUtil;
import com.cifpay.insurance.util.StringUtils;
import com.cifpay.starframework.dao.impl.CommonDaoImpl;

@Repository("insPolicyChangeListDao")
public class InsPolicyChangeListDaoImpl extends CommonDaoImpl<InsPolicyChangeList> implements InsPolicyChangeListDao {
	@Override
	public InsPolicyChangeList get(long id) {
		return this.getSqlSession().selectOne(getStatementPrefix() + ".get", id);
	}

	@Override
	public List<InsPolicyChangeList> getList() {
		List<InsPolicyChangeList> resultList = this.getSqlSession().selectList(getStatementPrefix() + ".getList");
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

	@Override
	public List<InsPolicyChangeList> getPolicyChangeList(String vendorId, GetPolicyChangeListInfo getPolicyChangeListInfo, Page<InsPolicyChangeList> page) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("vendorId", vendorId);
		params.put("policyNo", getPolicyChangeListInfo.getPolicyNo());
		if (StringUtils.isNotEmpty(getPolicyChangeListInfo.getBeginDate())) {
			params.put("beginDate", DateUtil.parseDate(getPolicyChangeListInfo.getBeginDate(), "yyyy-MM-dd"));
		}
		if (StringUtils.isNotEmpty(getPolicyChangeListInfo.getEndDate())) {
			Date ed = DateUtil.addDay(DateUtil.parseDate(getPolicyChangeListInfo.getEndDate(), "yyyy-MM-dd"), 1);
			params.put("endDate", ed);
		}
		params.put("type", getPolicyChangeListInfo.getType());
		RowBounds rb = new RowBounds((getPolicyChangeListInfo.getPageNo()-1)*getPolicyChangeListInfo.getPageSize(), getPolicyChangeListInfo.getPageSize());
		List<InsPolicyChangeList> list = this.getSqlSession().selectList(getStatementPrefix()+".getPolicyChangeList", params, rb);
		page.setResult(list);
		if (list.size() > 0) {
			Integer result = (Integer) this.getSqlSession().selectOne(getStatementPrefix() + ".getPolicyChangeListCount", params);
			if (result != null) {
				page.setRecordCount(result);
			}
		}
		return list;
	}
}
