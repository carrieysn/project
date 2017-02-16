package com.cifpay.insurance.service;

import java.util.List;

import com.cifpay.insurance.base.Page;
import com.cifpay.insurance.bean.TodayAddedCertBean;
import com.cifpay.insurance.bean.VendorCertStaticBean;
import com.cifpay.insurance.bean.VendorReturnCertBean;
import com.cifpay.insurance.model.InsInsuranceCert;
import com.cifpay.insurance.param.cert.CreateInsuranceCertBatchInfo;
import com.cifpay.insurance.param.cert.CreateInsuranceCertInfo;
import com.cifpay.insurance.param.cert.CreateInsuranceCertResult;
import com.cifpay.insurance.param.cert.GetInsuranceCertListInfo;
import com.cifpay.starframework.model.ServiceResult;
import com.cifpay.starframework.service.CommonService;

public interface InsInsuranceCertService extends CommonService<InsInsuranceCert> {
	public InsInsuranceCert get(long ID);

	public List<InsInsuranceCert> getList();

	public int getCount();
	/**
	 * 据保险证号查询保险证信息
	 * 
	 * @param InsuranceCertNo
	 * @return
	 */
	public InsInsuranceCert getInsInsuranceCertByNo(String InsuranceCertNo);
	
	/**
	 * 创建保险证
	 * 
	 * @param vendorId
	 * @param createInsuranceCertInfo
	 */
	public ServiceResult<CreateInsuranceCertResult> createInsInsuranceCert(String vendorId, CreateInsuranceCertInfo createInsuranceCertInfo);

	/**
	 * 批量创建保险证
	 * 
	 * @param vendorId
	 * @param createInsuranceCertBatchInfo
	 */
	public ServiceResult<List<CreateInsuranceCertResult>> createInsInsuranceCertBatch(String vendorId, CreateInsuranceCertBatchInfo createInsuranceCertBatchInfo);

	/**
	 * 保险证号查询保险证信息
	 * 
	 * @param certNo
	 * @return
	 */
	public InsInsuranceCert getInsInsuranceCertByCertNo(String certNo);

	/**
	 * /** 查询条件对象获取保险证列表
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
	public void updateEffectiveState(InsInsuranceCert insInsuranceCert);
	
	/**
	 * 更新是否开证成功
	 * 
	 * @param insInsuranceCert
	 */
	public void updateLcOpenState(InsInsuranceCert insInsuranceCert);

	/**
	 * 更新待退款状态
	 * 
	 * @param insInsuranceCert
	 */
	public void updateToRefundState(InsInsuranceCert insInsuranceCert);
	
	/**
	 * 拒绝退款，更新拒绝退款状态
	 * 
	 * @param insInsuranceCert
	 * @return
	 */
	public void updateRefuseRefund(InsInsuranceCert insInsuranceCert);
	
	/**
	 * 更新已退款状态
	 * 
	 * @param insInsuranceCert
	 * @return
	 */
	public void updateRefundSuccessState(InsInsuranceCert insInsuranceCert);
	
	/**
	 * 获取商户今日新增保险证（只包含未生效的）
	 * 
	 * @param vendorId
	 * @return
	 */
	public TodayAddedCertBean getTodayAddedCert(String vendorId);
	
	/**
	 * 获取商户今日所有保险证
	 * 
	 * @param vendorId
	 * @return
	 */
	public TodayAddedCertBean getTodayAllCert(String vendorId);

	/**
	 * 获取商户今日退货保险证信息
	 * 
	 * @param vendorId
	 * @return
	 */
	public VendorReturnCertBean getVendorTodayReturnCert(String vendorId);

	/**
	 * 获取商户所有的待退款保险证信息
	 * 
	 * @param vendorId
	 * @return
	 */
	public VendorReturnCertBean getVendorAllToRefundCert(String vendorId);

	/**
	 * 物流签收
	 * 
	 * @param insInsuranceCert
	 * @return
	 */
	public void signLogistics(InsInsuranceCert insInsuranceCert);

	/**
	 * 获取用户上年、本年、上月、本月、昨天、今天的保险证统计数据
	 * 
	 * @param vendorId
	 * @param policyNo
	 * @return
	 */
	public List<VendorCertStaticBean> getVendorCertStatic(String vendorId, String policyNo);

	/**
	 * 检查过期保险证，并更新保险证状态，释放保额。
	 * 
	 * @param vendorId
	 */
	public void checkExpiredInsuranceCert();
	
	/**
	 * 更新保险证失效状态。同时释放保额。
	 * 
	 * @param insInsuranceCert
	 * @return
	 */
	public void updateExpiredStatus(InsInsuranceCert insInsuranceCert);

	/**
	 * 获取过期保险证列表。
	 * 
	 * @param vendorId
	 * @return
	 */
	public List<InsInsuranceCert> getExpiredInsInsuranceCerts(String vendorId);

	/**
	 * 在每天0点的时候检查已签收的保险证，并更新保险证状态为“生效”，同时更新保险证有效期时间
	 * 
	 */
	public void checkSignedInsuranceCert();

	/**
	 * 获取状态为已签收的保险证列表。
	 * @param vendorId
	 * @return
	 */
	public List<InsInsuranceCert> getSignedInsInsuranceCerts(String vendorId);
	
	/**
	 * 获取保险证及其关联信息。（参考保险证展示）
	 * 
	 * @param InsuranceCertNo
	 * @return
	 */
	public InsInsuranceCert getFullInsInsuranceCertByCertNo(String InsuranceCertNo);
}
