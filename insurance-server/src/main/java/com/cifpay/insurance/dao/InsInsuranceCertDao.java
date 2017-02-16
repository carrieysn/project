package com.cifpay.insurance.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.cifpay.insurance.base.Page;
import com.cifpay.insurance.bean.TodayAddedCertBean;
import com.cifpay.insurance.bean.VendorCertStaticBean;
import com.cifpay.insurance.bean.VendorReturnCertBean;
import com.cifpay.insurance.model.InsInsuranceCert;
import com.cifpay.insurance.param.cert.GetInsuranceCertListInfo;
import com.cifpay.starframework.dao.CommonDao;

public interface InsInsuranceCertDao extends CommonDao<InsInsuranceCert> {
	public InsInsuranceCert get(long ID);

	public List<InsInsuranceCert> getList();

	public int getCount();

	/**
	 * 保险证号获取保险证信息
	 * 
	 * @param certNo
	 * @return
	 */
	public InsInsuranceCert getInsInsuranceCertByCertNo(String certNo);

	/**
	 * 查询条件对象获取保险证列表
	 * 
	 * @param certList
	 * @param page
	 * @return
	 */
	public List<InsInsuranceCert> getInsInsuranceCertList(String vendorId, GetInsuranceCertListInfo certList, Page<InsInsuranceCert> page);

	/**
	 * 更新生效状态
	 * @param insInsuranceCert
	 * @return
	 */
	public int updateEffectiveState(InsInsuranceCert insInsuranceCert);
	
	/**
	 * 更新开证状态
	 * 
	 * @param insInsuranceCert
	 * @return
	 */
	public int updateLcOpenState(InsInsuranceCert insInsuranceCert);
	
	/**
	 * 更新待退款状态
	 * 
	 * @param insInsuranceCert
	 * @return
	 */
	public int updateToRefundState(InsInsuranceCert insInsuranceCert);

	/**
	 * 更新待退款中状态
	 * 
	 * @param insInsuranceCert
	 * @return
	 */
	//public int updateInRefundState(InsInsuranceCert insInsuranceCert);

	/**
	 * 更新拒绝退款状态
	 * 
	 * @param insInsuranceCert
	 * @return
	 */
	public int updateRefuseRefundState(InsInsuranceCert insInsuranceCert);

	/**
	 * 更新已退款状态
	 * 
	 * @param insInsuranceCert
	 * @return
	 */
	public int updateRefundSuccessState(InsInsuranceCert insInsuranceCert);
	
	/**
	 * 更新保险证失效状态。
	 * 
	 * @param insInsuranceCert
	 * @return
	 */
	public int updateExpiredStatus(InsInsuranceCert insInsuranceCert);

	/**
	 * 据保险证号查询保险证信息
	 * 
	 * @param InsuranceCertNo
	 * @return
	 */
	public InsInsuranceCert getInsInsuranceCertByNo(String InsuranceCertNo);

	/**
	 * 获取商户今日新增保险证
	 * 
	 * @param vendorId
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public TodayAddedCertBean getTodayAddedCert(String vendorId, Date beginDate, Date endDate, Integer status);

	/**
	 * 获取商户退货保险证信息
	 * 
	 * @param vendorId
	 * @param beginDate
	 *            可为空
	 * @param endDate
	 *            不能为空
	 * @return
	 */
	public VendorReturnCertBean getVendorReturnCert(String vendorId, Date beginDate, Date endDate, Integer status);

	/**
	 * 统计时间类别,key为指定时间维度名称，value为指定时间值。 <br>
	 * 有以下参数key：
	 * <ul>
	 * <li>vendorId：商户号</li>
	 * <li>policyNo：保单号</li>
	 * <li>todayBegin：今日开始时间（含）</li>
	 * <li>todayEnd：今日结束时间（不含）</li>
	 * <li>yesterdayBegin：昨日开始时间（含）</li>
	 * <li>yesterdayEnd：昨日结束时间（不含）</li>
	 * <li>thisMonthBegin：本月开始时间（含）</li>
	 * <li>thisMonthEnd：本月结束时间（不含）</li>
	 * <li>lastMonthBegin：上月开始时间（含）</li>
	 * <li>lastMonthEnd：上月结束时间（不含）</li>
	 * <li>thisYearBegin：今年开始时间（含）</li>
	 * <li>thisYearEnd：今年结束时间（不含）</li>
	 * <li>lastYearBegin：去年开始时间（含）</li>
	 * <li>lastYearEnd：去年结束时间（不含）</li>
	 * </ul>
	 * 
	 * @param staticTimesMap
	 * @return
	 */
	public List<VendorCertStaticBean> getVendorCertStatic(Map<String, Object> staticTimesMap);
	
	/**
	 * 获取过期保险证列表。
	 * 
	 * @param vendorId
	 * @param expiredTime 
	 *               不包含当天
	 * @return
	 */
	public List<InsInsuranceCert> getExpiredInsInsuranceCerts(String vendorId, Date expiredTime);
	
	/**
	 * 获取已签收的保险证列表信息
	 * 
	 * @param vendorId
	 * @param signTime
	 * @return
	 */
	public List<InsInsuranceCert> getSignedInsInsuranceCerts(String vendorId, Date signTime);
	
	/**
	 * 获取保险证及其关联信息。（参考保险证展示）
	 * 
	 * @param InsuranceCertNo
	 * @return
	 */
	public InsInsuranceCert getFullInsInsuranceCertByCertNo(String InsuranceCertNo);
}
