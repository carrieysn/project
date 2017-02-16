package com.cifpay.insurance.dao;

import java.util.List;

import com.cifpay.insurance.base.Page;
import com.cifpay.insurance.model.InsCertRefundBill;
import com.cifpay.insurance.param.refund.GetRefundBillInfo;
import com.cifpay.starframework.dao.CommonDao;

public interface InsCertRefundBillDao extends CommonDao<InsCertRefundBill> {
	public InsCertRefundBill get(long id);

	public List<InsCertRefundBill> getList();

	public int getCount();
	
	/**
	 * 更新单据状态为退款中
	 * 
	 * @param entity
	 * @return
	 */
	public int updateInRefundState(InsCertRefundBill entity);
	
	/**
	 * 更新单据状态为退款成功
	 * 
	 * @param entity
	 * @return
	 */
	public int updateRefundSuccessState(InsCertRefundBill entity);
	
	/**
	 * 更新单据状态为退款失败
	 * 
	 * @param entity
	 * @return
	 */
	public int updateRefundFailState(InsCertRefundBill entity);
	
	/**
	 * 据银信证id,订单号查找单据
	 * 
	 * @param lcId
	 * @param orderId
	 * @return
	 */
	public InsCertRefundBill getInsCertRefundBillByOrderId(String lcId, String orderId);
	
	/**
	 * 更新开证失败状态
	 * 
	 * @param entity
	 * @return
	 */
	public int updateOpenFailState(InsCertRefundBill entity);
	/**
	 * 更新开证成功状态（待退款）
	 * 
	 * @param entity
	 * @return
	 */
	public int updateOpenSuccessState(InsCertRefundBill entity);
	
	/**
	 * 更新拒绝退款状态
	 * 
	 * @param entity
	 * @return
	 */
	public int updateRefuseRefundState(InsCertRefundBill entity);
	
	/**
	 * 据保险证号查找非开证失败的退款单.
	 * 
	 * @param insuranceCertNo
	 * @return
	 */
	public InsCertRefundBill getInsCertRefundBillByCertNo(String insuranceCertNo);
	
	/**
	 * 根据查询条件查询退款单
	 * @param bean
	 * @param page
	 * @return
	 */
	public List<InsCertRefundBill> getInsCertRefundBills(GetRefundBillInfo bean, Page<InsCertRefundBill> page);
}
