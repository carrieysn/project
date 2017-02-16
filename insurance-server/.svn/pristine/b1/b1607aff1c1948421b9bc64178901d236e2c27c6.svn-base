package com.cifpay.insurance.service;

import java.util.List;

import com.cifpay.insurance.base.Page;
import com.cifpay.insurance.bean.LcNoticeResultInfo;
import com.cifpay.insurance.model.InsCertRefundBill;
import com.cifpay.insurance.model.InsUnopenRefundBill;
import com.cifpay.insurance.param.cert.InsuranceCertRefundRefuseInfo;
import com.cifpay.insurance.param.refund.GetRefundBillInfo;
import com.cifpay.starframework.service.CommonService;

public interface InsCertRefundBillService extends CommonService<InsCertRefundBill> {
	public InsCertRefundBill get(long id);

	public List<InsCertRefundBill> getList();

	public int getCount();
	/**
	 * 据保险证号查找非开证失败的退款单.
	 * 
	 * @param insuranceCertNo
	 * @return
	 */
	public InsCertRefundBill getInsCertRefundBillByCertNo(String insuranceCertNo);
	
	/**
	 * 增加商户开的退款单，同时更新保险证状态为待退款。
	 * 
	 * @param insCertRefundBill
	 */
	public void addVendorInsCertRefundBill(InsCertRefundBill insCertRefundBill);

	/**
	 * 退款申请单，同时更新保险证状态为待退款。务必确保保险证状态为待退款同时生成退款单；然自动开出商户银信证，开证不成功将在23h小时内定期尝试开证，24
	 * h内不成功，将开出保险公司银信证。
	 * 
	 * @param insCertRefundBill
	 */
	public void applyReturnInsuranceCert(InsCertRefundBill insCertRefundBill);

	/**
	 * 更新开证成功状态（待退款）。更新保险证为待退款。
	 * 
	 * @param entity
	 * @return
	 */
	public void updateOpenSuccessState(InsCertRefundBill entity);
	
	/**
	 * 更新商户二次开证成功状态。删除未开证列表对应数据，更新开证成功状态。
	 * 
	 * @param entity
	 * @param unopenBill 未开证记录
	 * @return
	 */
	public void updateVendorSecondOpenSuccessState(InsCertRefundBill entity, InsUnopenRefundBill unopenBill);

	/**
	 * 开证失败，把开证失败的退款单记录至“未开证退款单”表；记录退货跟踪信息
	 * 
	 * @param entity
	 * @return
	 */
	public void updateOpenFailState(InsCertRefundBill entity);

	/**
	 * 更新单据状态为退款成功，同时更新保险证状态为已退款
	 * 
	 * @param entity
	 * @return
	 */
	public void updateRefundSuccessState(InsCertRefundBill entity);
	
	/**
	 * 退款失败，更新交易结果
	 * 
	 * @param entity
	 * @return
	 */
	public void updateRefundFailState(InsCertRefundBill entity);
	

	/**
	 * 更新单据状态为退款中
	 * 
	 * @param entity
	 * @return
	 */
	public void updateInRefundState(InsCertRefundBill entity);
	
	/**
	 * 更新拒绝退款状态，同时更新保险证状态为拒绝退款状态及解冻保额
	 * 
	 * @param entity
	 * @return
	 */
	public void updateRefuseRefundState(InsCertRefundBill entity);

	/**
	 * 执行拒绝退款
	 * 
	 * @param refundRefuse
	 * @return
	 */
	public InsCertRefundBill refuseRefund(InsuranceCertRefundRefuseInfo refundRefuse);

	/**
	 * 执行确认退款操作，标识为退款中
	 * 
	 * @param insuranceCertNo
	 *            保险证号
	 */
	public InsCertRefundBill confirmRefund(String insuranceCertNo);

	/**
	 * 退款单通知，退款成功后释放对应的保险证冻结的保额，同时扣减保费，调整保单保额。
	 * 
	 * @param lcNoticeResultInfo
	 */
	public void noticeInsCertRefundBillTradeState(LcNoticeResultInfo lcNoticeResultInfo);

	/**
	 * 检查未过期的（商户还在允许开证期内）未开证退款单，在23h内定期将触发商家再次开证，如果超出23h将由保险公司代开证。确保24h内向买家开证成功！
	 * 
	 */
	//public void checkUnexpiredUnopenRefundBill();
	
	/**
	 * 检查过期的（即商户无法在规定时间内开出证了，须由保险公司开）未开证退款单，在23h内定期将触发商家再次开证，如果超出23h将由保险公司代开证。确保24h内向买家开证成功！
	 * 
	 */
	//public void checkExpiredUnopenRefundBill();

	/**
	 * 新增保险公司退款单。
	 * 
	 * @param insUnopenRefundBill
	 * @return
	 */
	public InsCertRefundBill addInsCompanyInsCertRefundBill(InsUnopenRefundBill insUnopenRefundBill);
	
	/**
	 * 根据查询条件查询退款单
	 * @param bean
	 * @param page
	 * @return
	 */
	public List<InsCertRefundBill> getInsCertRefundBills(GetRefundBillInfo bean, Page<InsCertRefundBill> page);
}
