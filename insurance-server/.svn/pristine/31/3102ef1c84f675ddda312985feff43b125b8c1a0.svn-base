package com.cifpay.insurance.dao.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.cifpay.insurance.base.Page;
import com.cifpay.insurance.dao.InsPolicyDao;
import com.cifpay.insurance.model.InsPolicy;
import com.cifpay.insurance.param.policy.GetPolicyListInfo;
import com.cifpay.insurance.util.StringUtils;
import com.cifpay.starframework.dao.impl.CommonDaoImpl;
import com.google.common.collect.Maps;

@Repository("insPolicyDao")
public class InsPolicyDaoImpl extends CommonDaoImpl<InsPolicy> implements
		InsPolicyDao {
	@Override
	public InsPolicy get(long ID) {
		return this.getSqlSession().selectOne(getStatementPrefix() + ".get", ID);
	}

	@Override
	public List<InsPolicy> getList() {
		List<InsPolicy> resultList = this.getSqlSession().selectList(getStatementPrefix() + ".getList");
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
	public InsPolicy getPolicyByVendorId(String vendorId) {
		InsPolicy policy = new InsPolicy();
		policy.setVendorId(vendorId);
		Map<String,Object> map = fitMap( policy);
		InsPolicy insPolicy = this.getSqlSession().selectOne(getStatementPrefix()+".getInsPolicy",map);
		return insPolicy;
	}
	
	@Override
	public InsPolicy getPolicyByPolicyId(Long policyId) {
		InsPolicy policy = new InsPolicy();
		policy.setId(policyId);
		Map<String,Object> map = fitMap(policy);
		InsPolicy insPolicy = this.getSqlSession().selectOne(getStatementPrefix()+".getInsPolicy",map);
		return insPolicy;
	}
	
	@Override
	public InsPolicy getPolicyByPolicyNo(String policyNo) {
		InsPolicy policy = new InsPolicy();
		policy.setPolicyNo(policyNo);
		Map<String,Object> map = fitMap( policy);
		InsPolicy insPolicy = this.getSqlSession().selectOne(getStatementPrefix()+".getInsPolicy",map);
		return insPolicy;
	}
	
	public Map<String, Object> fitMap(InsPolicy policy)
	{
		Map<String, Object> map = Maps.newHashMap();
		if(StringUtils.isNotEmpty(policy.getVendorId()))
		{
			map.put("vendorId", policy.getVendorId());
		}
		if(policy.getId() != null && policy.getId() > 0){
			map.put("policyId", policy.getId());
		}
		if(StringUtils.isNotEmpty(policy.getPolicyNo()))
		{
			map.put("policyNo", policy.getPolicyNo());
		} 	
		return map;
	} 
	
	@Override
	public int updateChangePolicyPremium(InsPolicy insPolicy) {
		return getSqlSession().update((new StringBuilder()).append(getStatementPrefix()).append(".updateChangePolicyPremium").toString(), insPolicy);
	}
	
	@Override
	public boolean isExistsVendorInsPolicy(String vendorId, Long excludeId) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("vendorId", vendorId);
		params.put("excludeId", excludeId);
		Integer result = (Integer) this.getSqlSession().selectOne(getStatementPrefix() + ".isExistsVendorInsPolicy", params);
		return result != null && result > 0;
	}
	
	@Override
	public InsPolicy getFullInsPolicy(String vendorId) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("vendorId", vendorId);
		return this.getSqlSession().selectOne(getStatementPrefix()+".getFullInsPolicy", params);
	}
	
	@Override
	public List<InsPolicy> getInsPolicyList(GetPolicyListInfo bean, Page<InsPolicy> page) {
		
		List<InsPolicy> list = null;
		if(page != null){
			list = this.getSqlSession().selectList(getStatementPrefix()+".getInsPolicyList", bean, new RowBounds((page.getPageNo()-1)*page.getPageSize(),page.getPageSize()));
			page.setResult(list);
			if (list.size() > 0) {
				Integer result = (Integer) this.getSqlSession().selectOne(getStatementPrefix() + ".getInsPolicyListCount", bean);
				if (result != null) {
					page.setRecordCount(result);
				}
			}
		}else{
			list = this.getSqlSession().selectList(getStatementPrefix()+".getInsPolicyList", bean);
		}
		return list;
	}
	
	@Override
	public int updatePolicyStatus(InsPolicy oldInsPolicy, int newStatus) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("id", oldInsPolicy.getId());
		params.put("version", oldInsPolicy.getVersion());
		params.put("oldStatus", oldInsPolicy.getStatus());
		params.put("newStatus", newStatus);
		return getSqlSession().update((new StringBuilder()).append(getStatementPrefix()).append(".updatePolicyStatus").toString(), params);
	}
	
	@Override
	public List<InsPolicy> getAllInsPolicyAndInsuredAmountInfo() {
		List<InsPolicy> resultList = this.getSqlSession().selectList(getStatementPrefix() + ".getAllInsPolicyAndInsuredAmountInfo");
		if (resultList == null) {
			resultList = Collections.emptyList();
		}
		return resultList;
	}

}
