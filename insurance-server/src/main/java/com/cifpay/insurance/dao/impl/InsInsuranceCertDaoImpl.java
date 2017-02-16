package com.cifpay.insurance.dao.impl;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.cifpay.insurance.base.Page;
import com.cifpay.insurance.bean.TodayAddedCertBean;
import com.cifpay.insurance.bean.VendorCertStaticBean;
import com.cifpay.insurance.bean.VendorReturnCertBean;
import com.cifpay.insurance.dao.InsInsuranceCertDao;
import com.cifpay.insurance.model.InsInsuranceCert;
import com.cifpay.insurance.param.cert.GetInsuranceCertListInfo;
import com.cifpay.insurance.util.StringUtils;
import com.cifpay.starframework.dao.impl.CommonDaoImpl;
import com.google.common.collect.Maps;

@Repository("insInsuranceCertDao")
public class InsInsuranceCertDaoImpl extends CommonDaoImpl<InsInsuranceCert> implements InsInsuranceCertDao {
	@Override
	public InsInsuranceCert get(long ID) {
		return this.getSqlSession().selectOne(getStatementPrefix() + ".get", ID);
	}

	@Override
	public List<InsInsuranceCert> getList() {
		List<InsInsuranceCert> resultList = this.getSqlSession().selectList(getStatementPrefix() + ".getList");
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
	public InsInsuranceCert getInsInsuranceCertByCertNo(String certNo) {
		return this.getSqlSession().selectOne(getStatementPrefix()+".getInsInsuranceCertByCertNo", certNo);
	}
	
	public Map<String, Object> fitMap(GetInsuranceCertListInfo certList)
	{
		Map<String, Object> map = Maps.newHashMap();
		if(StringUtils.isNotEmpty(certList.getCreatedTimeFrom()))
		{
			map.put("createTimeFrom", certList.getCreatedTimeFrom());
		}
		if(StringUtils.isNotEmpty(certList.getCreatedTimeTo()))
		{
			map.put("createTimeTo", certList.getCreatedTimeTo());
		} 	
		if(StringUtils.isNotEmpty(certList.getReturnDateFrom()))
		{
			map.put("returnDateFrom", certList.getReturnDateFrom());
		} 
		if(StringUtils.isNotEmpty(certList.getReturnDateTo()))
		{
			map.put("returnDateTo",certList.getReturnDateTo());
		} 
		if(certList.getInsuranceCertNo() != null)
		{
			map.put("insuranceCertNo", certList.getInsuranceCertNo());
		} 
		if(certList.getStatus() != null)
		{
			map.put("status", certList.getStatus()/*.split(",")*/);
		} 
		return map;
	} 

	@Override
	public List<InsInsuranceCert> getInsInsuranceCertList(String vendorId, GetInsuranceCertListInfo certList, Page<InsInsuranceCert> page) {
		Map<String, Object> m = fitMap(certList);
		m.put("vendorId", vendorId);
		List<InsInsuranceCert> insInsuranceCertList = this.getSqlSession().selectList(getStatementPrefix()+".getInsInsuranceCertList", m, new RowBounds((page.getPageNo()-1)*page.getPageSize(),page.getPageSize()));
		page.setResult(insInsuranceCertList);
		if (insInsuranceCertList.size() > 0) {
			Integer result = (Integer) this.getSqlSession().selectOne(getStatementPrefix() + ".getInsInsuranceCertListCount", m);
			if (result != null) {
				page.setRecordCount(result);
			}
		}
		return insInsuranceCertList;
	}

	@Override
	public InsInsuranceCert getInsInsuranceCertByNo(String InsuranceCertNo) {
		return this.getSqlSession().selectOne(getStatementPrefix() + ".getInsInsuranceCertByNo", InsuranceCertNo);
	}

	@Override
	public int updateEffectiveState(InsInsuranceCert insInsuranceCert) {
		return this.getSqlSession().update(getStatementPrefix() + ".updateEffectiveState", insInsuranceCert);
	}
	
	@Override
	public int updateLcOpenState(InsInsuranceCert insInsuranceCert) {
		return this.getSqlSession().update(getStatementPrefix() + ".updateLcOpenState", insInsuranceCert);
	}
	
	@Override
	public int updateToRefundState(InsInsuranceCert insInsuranceCert) {
		return this.getSqlSession().update(getStatementPrefix() + ".updateToRefundState", insInsuranceCert);
	}

	/*@Override
	public int updateInRefundState(InsInsuranceCert insInsuranceCert) {
		return this.getSqlSession().update(getStatementPrefix() + ".updateInRefundState", insInsuranceCert);
	}*/
	
	@Override
	public int updateRefuseRefundState(InsInsuranceCert insInsuranceCert) {
		return this.getSqlSession().update(getStatementPrefix() + ".updateRefuseRefundState", insInsuranceCert);
	}
	
	@Override
	public int updateRefundSuccessState(InsInsuranceCert insInsuranceCert) {
		return this.getSqlSession().update(getStatementPrefix() + ".updateRefundSuccessState", insInsuranceCert);
	}
	
	@Override
	public int updateExpiredStatus(InsInsuranceCert insInsuranceCert) {
		return this.getSqlSession().update(getStatementPrefix() + ".updateExpiredStatus", insInsuranceCert);
	}
	
	@Override
	public TodayAddedCertBean getTodayAddedCert(String vendorId, Date beginDate, Date endDate, Integer status) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("vendorId", vendorId);
		params.put("beginDate", beginDate);
		params.put("endDate", endDate);
		params.put("status", status);
		return this.getSqlSession().selectOne(getStatementPrefix() + ".getTodayAddedCert", params);
	}

	@Override
	public VendorReturnCertBean getVendorReturnCert(String vendorId, Date beginDate, Date endDate, Integer status) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("vendorId", vendorId);
		params.put("beginDate", beginDate);
		params.put("endDate", endDate);
		params.put("status", status);
		return this.getSqlSession().selectOne(getStatementPrefix() + ".getVendorReturnCert", params);
	}

	@Override
	public List<VendorCertStaticBean> getVendorCertStatic(Map<String, Object> staticTimesMap) {
		return this.getSqlSession().selectList(getStatementPrefix() + ".getVendorCertStatic", staticTimesMap);
	}
	
	@Override
	public List<InsInsuranceCert> getExpiredInsInsuranceCerts(String vendorId, Date expiredTime) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("vendorId", vendorId);
		params.put("expiredTime", expiredTime);
		return this.getSqlSession().selectList(getStatementPrefix() + ".getExpiredInsInsuranceCerts", params);
	}
	
	@Override
	public List<InsInsuranceCert> getSignedInsInsuranceCerts(String vendorId, Date signTime) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("vendorId", vendorId);
		params.put("signTime", signTime);
		return this.getSqlSession().selectList(getStatementPrefix() + ".getSignedInsInsuranceCerts", params);
	}
	
	@Override
	public InsInsuranceCert getFullInsInsuranceCertByCertNo(String InsuranceCertNo) {
		return this.getSqlSession().selectOne(getStatementPrefix() + ".getFullInsInsuranceCertByCertNo", InsuranceCertNo);
	}
	
}
