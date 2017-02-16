package com.cifpay.insurance.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cifpay.insurance.InsCertRefundBillEnum;
import com.cifpay.insurance.InsuranceCertStatusEnum;
import com.cifpay.insurance.LcOrderStateEnum;
import com.cifpay.insurance.PolicyChangeListTypeEnum;
import com.cifpay.insurance.base.Page;
import com.cifpay.insurance.bean.LcNoticeResultInfo;
import com.cifpay.insurance.bean.PolicyAmountChangeBean;
import com.cifpay.insurance.cache.InsCache;
import com.cifpay.insurance.config.WebConstant;
import com.cifpay.insurance.dao.InsCertRefundBillDao;
import com.cifpay.insurance.dao.InsReturnTraceDao;
import com.cifpay.insurance.dao.InsUnopenRefundBillDao;
import com.cifpay.insurance.exception.InsuranceBizRuntimeException;
import com.cifpay.insurance.model.InsCertRefundBill;
import com.cifpay.insurance.model.InsInsuranceCert;
import com.cifpay.insurance.model.InsReturnTrace;
import com.cifpay.insurance.model.InsReturnType;
import com.cifpay.insurance.model.InsUnopenRefundBill;
import com.cifpay.insurance.model.InsVendorBankAccount;
import com.cifpay.insurance.param.cert.InsuranceCertRefundRefuseInfo;
import com.cifpay.insurance.param.refund.GetRefundBillInfo;
import com.cifpay.insurance.service.InsCertRefundBillService;
import com.cifpay.insurance.service.InsInsuranceCertService;
import com.cifpay.insurance.service.InsPolicyService;
import com.cifpay.insurance.service.InsVendorBankAccountService;
import com.cifpay.insurance.util.DateUtil;
import com.cifpay.starframework.cache.ServiceResultCodeCache;
import com.cifpay.starframework.model.ServiceResult;

@Service("insCertRefundBillService")
public class InsCertRefundBillServiceImpl implements InsCertRefundBillService {
	private static final Logger logger = LogManager.getLogger(InsCertRefundBillServiceImpl.class);
	private static ServiceResultCodeCache resultCode = ServiceResultCodeCache.getInstance();
	@Autowired
	private InsCertRefundBillDao insCertRefundBillDao;
	@Autowired
	private ThreadPoolTaskExecutor executor;

	@Autowired
	private InsPolicyService insPolicyService;
	@Autowired
	private InsUnopenRefundBillDao insUnopenRefundBillDao;
	@Autowired
	private InsInsuranceCertService insInsuranceCertService;
	@Autowired
	private InsVendorBankAccountService insVendorBankAccountService;//商户银行卡
	
	//@Autowired
	//private InsReturnTypeService insReturnTypeService;
	@Autowired
	private InsReturnTraceDao insReturnTraceDao;
	
	@Override
	public InsCertRefundBill get(long id) {
		return insCertRefundBillDao.get(id);
	}
	
	@Override
	public ServiceResult<String> add(InsCertRefundBill insCertRefundBill) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insCertRefundBillDao.add(insCertRefundBill);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> addSelective(InsCertRefundBill insCertRefundBill) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insCertRefundBillDao.addSelective(insCertRefundBill);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> update(InsCertRefundBill insCertRefundBill) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insCertRefundBillDao.update(insCertRefundBill);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> updateSelective(InsCertRefundBill insCertRefundBill) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insCertRefundBillDao.updateSelective(insCertRefundBill);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> delete(InsCertRefundBill insCertRefundBill) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insCertRefundBillDao.delete(insCertRefundBill);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public List<InsCertRefundBill> getList() {
		return insCertRefundBillDao.getList();
	}

	@Override
	public int getCount() {
		return insCertRefundBillDao.getCount();
	}
	
	@Override
	public InsCertRefundBill getInsCertRefundBillByCertNo(String insuranceCertNo) {
		return insCertRefundBillDao.getInsCertRefundBillByCertNo(insuranceCertNo);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void addVendorInsCertRefundBill(InsCertRefundBill insCertRefundBill) {
        //1、更新保险证状态
		InsInsuranceCert iic = insInsuranceCertService.getInsInsuranceCertByNo(insCertRefundBill.getInsuranceCertNo());
		if (iic == null || !iic.getVendorId().equals(insCertRefundBill.getVendorId())) {
			throw new InsuranceBizRuntimeException(resultCode.get("biz.insurance.cert.notfound"), "商户保险证号不存在！");
		}
		if (iic.getStatus() == null || iic.getStatus() != InsuranceCertStatusEnum.EFFECTIVE.val) {
			throw new InsuranceBizRuntimeException(resultCode.get("biz.insurance.cert.uneffectived"), "保险证不是生效状态！");
		}
		//2、新增退款申请单
		addInsCertRefundBill(iic, insCertRefundBill);
		//设置待退款状态
		iic.setReturnDate(insCertRefundBill.getReturnDate());
		insInsuranceCertService.updateToRefundState(iic);
		//增加跟踪记录
		addReturnTrace(insCertRefundBill, WebConstant.RT_SUBMIT_RETURN);
	}
	
	/** 增加跟踪记录 **/
	private void addReturnTrace(InsCertRefundBill insCertRefundBill, String returnTypeCode) {
		InsReturnType rt = InsCache.getInsReturnTypeCache(returnTypeCode);//insReturnTypeService.getInsReturnTypeByCode(returnTypeCode);
		InsReturnTrace r = new InsReturnTrace();
		r.setInsuranceCertId(insCertRefundBill.getInsuranceCertId());
		r.setInsuranceCertNo(insCertRefundBill.getInsuranceCertNo());
		r.setOptType(returnTypeCode);
		r.setOptTime(new Date());
		r.setDescription(rt.getDescZh());
		Calendar cl = Calendar.getInstance();
		cl.setTime(r.getOptTime());
		r.setOptMicrosecond(cl.get(Calendar.MILLISECOND));
		insReturnTraceDao.add(r);
	}
	
	/** 增加退款申请单 **/
	private void addInsCertRefundBill(InsInsuranceCert iic, InsCertRefundBill bill) {
		bill.setVendorId(iic.getVendorId());
		bill.setInsuranceCertId(iic.getId());
		bill.setInsuranceCertNo(iic.getInsuranceCertNo());
		bill.setRefundAmount(iic.getTotalPrice());
		//付款人信息
		InsVendorBankAccount vba = insVendorBankAccountService.getDefaultInsVendorBankAccount(iic.getVendorId());
		if (vba == null) {
			throw new InsuranceBizRuntimeException(resultCode.get("biz.vendor.bankaccount.default.notset"), "未设置默认银行卡号！");
		}
		bill.setPayerBankAccount(vba.getBankAccount());
		bill.setPayerBankCode(vba.getBankCode());
		bill.setPayerBankName(vba.getBankName());
		bill.setPayerMobilePhone(vba.getReserveMobilePhone());
		bill.setPayerAccName(vba.getAccountName());
		bill.setPayerType(0);//商户
		//收款人信息
		bill.setBillStatus(InsCertRefundBillEnum.TO_OPEN.val);
		bill.setReturnDate(new Date());// 退货日期
		bill.setCreatedTime(new Date());
		bill.setModifiedTime(new Date());
		bill.setVersion(0);
		insCertRefundBillDao.add(bill);
	}

	//@Transactional
	public void applyReturnInsuranceCert(InsCertRefundBill insCertRefundBill) {
		//1、生成退款单及更新保险证状态
		//InsCertRefundBillService service = (InsCertRefundBillService)SpringContextUtil.getBean("insCertRefundBillService");
		//service.addVendorInsCertRefundBill(insCertRefundBill);//独立事务生成退款单。
		addVendorInsCertRefundBill(insCertRefundBill);
		//开证在外部进行。
	}
	
	private void addInsUnopenRefundBill(InsCertRefundBill bill) {
		InsUnopenRefundBill unopenBill = new InsUnopenRefundBill();
		unopenBill.setCertRefundBillId(bill.getId());
		unopenBill.setVendorId(bill.getVendorId());
		unopenBill.setInsuranceCertId(bill.getInsuranceCertId());
		unopenBill.setInsuranceCertNo(bill.getInsuranceCertNo());
		unopenBill.setLogisticsBillNo(bill.getLogisticsBillNo());
		unopenBill.setLogisticsCompany(bill.getLogisticsCompany());
		unopenBill.setRefundAmount(bill.getRefundAmount());
		unopenBill.setPayerAccName(bill.getPayerAccName());
		unopenBill.setPayerBankAccount(bill.getPayerBankAccount());
		unopenBill.setPayerBankCode(bill.getPayerBankCode());
		unopenBill.setPayerBankName(bill.getPayerBankName());
		unopenBill.setPayerMobilePhone(bill.getPayerMobilePhone());
		unopenBill.setPayeeAccName(bill.getPayeeAccName());
		unopenBill.setPayeeBankAccount(bill.getPayeeBankAccount());
		unopenBill.setPayeeBankCode(bill.getPayeeBankCode());
		unopenBill.setPayeeBankName(bill.getPayeeBankName());
		unopenBill.setPayeeMobilePhone(bill.getPayeeMobilePhone());
		//截止时间
		int limit = 23;//小时
		try {
			limit = Integer.parseInt(configProperties.get("vendor.openLcLimit"));
		} catch (Exception e) {
		}
		unopenBill.setExpiredTime(DateUtil.addHour(bill.getReturnDate(), limit));
		insUnopenRefundBillDao.add(unopenBill);
	}
	
	@Transactional
	public void updateOpenSuccessState(InsCertRefundBill entity) {
		entity.setBillStatus(InsCertRefundBillEnum.TO_REFUND.val);//待退款（开证成功！）
		int i = insCertRefundBillDao.updateOpenSuccessState(entity);
		if (i == 0) {
			throw new InsuranceBizRuntimeException(resultCode.get("common.record.version.ischanged"), "本次更新待退款单状态失败（待退款），记录已被更新！");
		}
		//设置开证成功
		InsInsuranceCert iic = new InsInsuranceCert();
		iic.setId(entity.getInsuranceCertId());
		iic.setInsuranceCertNo(entity.getInsuranceCertNo());
		insInsuranceCertService.updateLcOpenState(iic);
		if (1 == entity.getPayerType()) {//保险公司开证成功
			addReturnTrace(entity, WebConstant.RT_IC_OPEN_SUCCESS);
		} else {//商户开证成功！
			addReturnTrace(entity, WebConstant.RT_VENDOR_OPEN_SUCCESS);
		}
	}
	
	@Transactional
	public void updateVendorSecondOpenSuccessState(InsCertRefundBill entity, InsUnopenRefundBill unopenBill) {
		updateOpenSuccessState(entity);
		insUnopenRefundBillDao.delete(unopenBill);//开证成功，直接从未开证列表清除
	}
	
	@Transactional
	public void updateOpenFailState(InsCertRefundBill entity) {
		addInsUnopenRefundBill(entity);
		addReturnTrace(entity, WebConstant.RT_VENDOR_OPEN_FAIL);
		updateRefundFailState(entity);
	}
	
	@Transactional
	public void updateRefundSuccessState(InsCertRefundBill entity) {
		entity.setBillStatus(InsCertRefundBillEnum.REFUND_SUCCESS.val);//退款成功
		int i = insCertRefundBillDao.updateRefundSuccessState(entity);
		if (i == 0) {
			throw new InsuranceBizRuntimeException(resultCode.get("common.record.version.ischanged"), "本次更新待退款单状态失败（退款成功），记录已被更新！");
		}
		//更新保险证状态为已退款
		InsInsuranceCert c = new InsInsuranceCert();
		c.setId(entity.getInsuranceCertId());
		c.setInsuranceCertNo(entity.getInsuranceCertNo());
		if (entity.getPayerType() == 1) {//若是保险公司退款
			c.setIsIcRefund(1);
		} else {
			c.setIsIcRefund(0);
		}
		insInsuranceCertService.updateRefundSuccessState(c);

		//记录跟踪信息
		addReturnTrace(entity, WebConstant.RT_VENDOR_REFUND);
	}
	
	@Override
	public void updateRefundFailState(InsCertRefundBill entity) {
		InsCertRefundBill up = entity;
		if (entity.getVersion() == null) {
			up = this.get(entity.getId());
			//up.setBillStatus(entity.getBillStatus());
			up.setLcTradeResult(entity.getLcTradeResult());
		}
		int i = insCertRefundBillDao.updateRefundFailState(entity);
		if (i == 0) {
			logger.error("更新银行交易结果失败，可能版本号已被更新！");
		}
	}
	
	public void updateInRefundState(InsCertRefundBill entity) {
		entity.setBillStatus(InsCertRefundBillEnum.IN_REFUND.val);//退款中（提交银行退款解付指令）
		int i = insCertRefundBillDao.updateInRefundState(entity);
		if (i == 0) {
			throw new InsuranceBizRuntimeException(resultCode.get("common.record.version.ischanged"), "本次更新待退款单状态失败（退款中），记录已被更新！");
		}
	}
	
	@Transactional
	public void updateRefuseRefundState(InsCertRefundBill entity) {
		entity.setBillStatus(InsCertRefundBillEnum.REFUSE_REFUND.val);//拒绝退款
		int i = insCertRefundBillDao.updateRefuseRefundState(entity);
		if (i == 0) {
			throw new InsuranceBizRuntimeException(resultCode.get("common.record.version.ischanged"), "本次更新待退款单状态失败（拒绝退款），记录已被更新！");
		}
		//更新保险证状态为已退款
		InsInsuranceCert upCert = new InsInsuranceCert();
		upCert.setId(entity.getInsuranceCertId());
		upCert.setStatus(InsuranceCertStatusEnum.REFUSE_REFUND.val);// 拒绝退款
		if (entity.getPayerType() == 1) {//若是保险公司退款
			upCert.setIsIcRefund(1);
		} else {
			upCert.setIsIcRefund(0);
		}
		insInsuranceCertService.updateRefuseRefund(upCert);
		unfreezeInsuredAmount(entity, PolicyChangeListTypeEnum.REFUSE_REFUND);
		//记录跟踪信息
		addReturnTrace(entity, WebConstant.RT_VENDOR_REFUSE_REFUND);
	}
	
	@Transactional
	public InsCertRefundBill confirmRefund(String insuranceCertNo) {
		//1更新退款单
		InsCertRefundBill bill = insCertRefundBillDao.getInsCertRefundBillByCertNo(insuranceCertNo);
		if (bill == null) {
			throw new InsuranceBizRuntimeException(resultCode.get("biz.insurance.cert.refund.bill.notfound"), "保险证退款单不存在！");
		}
		if (bill.getBillStatus() != InsCertRefundBillEnum.TO_REFUND.val) {
			throw new InsuranceBizRuntimeException(resultCode.get("biz.insurance.cert.refund.bill.status.nottorefund"), "保险证退款单不是待退款状态！");
		}
		bill.setBillStatus(InsCertRefundBillEnum.IN_REFUND.val);//退款中（提交银行退款解付指令）
		updateInRefundState(bill);
		return bill;
	}
	
	@Transactional
	public void noticeInsCertRefundBillTradeState(LcNoticeResultInfo lcNoticeResultInfo) {
		InsCertRefundBill bill = insCertRefundBillDao.getInsCertRefundBillByOrderId(lcNoticeResultInfo.getLcId(), lcNoticeResultInfo.getOrderId());
		if (bill == null) {
			throw new InsuranceBizRuntimeException(resultCode.get("biz.insurance.cert.refund.bill.notfound"), "保险证退款单不存在！[订单号："+lcNoticeResultInfo.getOrderId()+"]");
		}
		if (bill.getBillStatus() == InsCertRefundBillEnum.REFUND_SUCCESS.val) {//已支付
			logger.warn("保险证退款单["+lcNoticeResultInfo.getOrderId()+"]已完成支付！无须再处理。");
			return;
		}
		if (lcNoticeResultInfo.getOrderState().equals(bill.getLcOrderState())) {
			logger.warn("保险证退款单["+lcNoticeResultInfo.getOrderId()+"]已进行状态["+lcNoticeResultInfo.getOrderState()+"]处理！无须再处理。");
			return;
		}
		bill.setLcState(lcNoticeResultInfo.getLcState());
		bill.setLcOrderState(lcNoticeResultInfo.getOrderState());
		bill.setLcTradeDate(DateUtil.parseDate(lcNoticeResultInfo.getTradeDate(), "yyyy-MM-dd HH:mm:ss"));
		bill.setModifiedTime(new Date());
		if (LcOrderStateEnum.CREDIT_OPENED.val.equals(lcNoticeResultInfo.getOrderState())) {//已开证
			bill.setLcId(lcNoticeResultInfo.getLcId());
			bill.setLcNo(lcNoticeResultInfo.getLcNo());
			bill.setLcOrderId(lcNoticeResultInfo.getOrderId());
			bill.setLcOrderState(lcNoticeResultInfo.getOrderState());
			bill.setBillStatus(InsCertRefundBillEnum.TO_REFUND.val);
		} else if (LcOrderStateEnum.CREDIT_PAYED.val.equals(lcNoticeResultInfo.getOrderState())) {//支付完成
			bill.setBillStatus(InsCertRefundBillEnum.REFUND_SUCCESS.val);
			//退款成功后释放对应的保险证冻结的保额，同时扣减保费，调整保单保额。
			unfreezeInsuredAmount(bill, PolicyChangeListTypeEnum.VENDOR_REFUND_CERT);//先释放保额
			if (bill.getPayerType() == 1) {//若是保险公司退款，则扣保费，调整保额
				deductPolicyPremium(bill);//包含保险公司退款流水
			}
			updateRefundSuccessState(bill);//退款成功后须更新保险证状态及退款单状态
			return;
		}
		//退款未成功的，继续更新状态
		insCertRefundBillDao.updateSelective(bill);
	}
	
	/** 退款扣保费 **/
	private void deductPolicyPremium(InsCertRefundBill bill) {
		PolicyAmountChangeBean b = new PolicyAmountChangeBean();
		b.setChangeAmount(bill.getRefundAmount());
		b.setRefId(bill.getInsuranceCertId());
		b.setRefVoucherNo(bill.getInsuranceCertNo());
		b.setType(PolicyChangeListTypeEnum.IC_REFUND_CERT);
		b.setVendorId(bill.getVendorId());
		insPolicyService.deductPolicyPremium(b);
	}
	
	/**
	 * 解冻保额 
	 * @param bill
	 * @param type 确认退款和拒绝退款
	 */
	private void unfreezeInsuredAmount(InsCertRefundBill bill, PolicyChangeListTypeEnum type) {
		PolicyAmountChangeBean b = new PolicyAmountChangeBean();
		b.setChangeAmount(bill.getRefundAmount());
		b.setRefId(bill.getInsuranceCertId());
		b.setRefVoucherNo(bill.getInsuranceCertNo());
		b.setType(type);
		b.setVendorId(bill.getVendorId());
		insPolicyService.unfreezeInsuredAmount(b);
	}
	
	@Transactional
	public InsCertRefundBill refuseRefund(InsuranceCertRefundRefuseInfo refundRefuse) {
		//1更新退款单状态
		InsCertRefundBill bill = this.insCertRefundBillDao.getInsCertRefundBillByCertNo(refundRefuse.getInsuranceCertNo());
		if (bill == null) {
			throw new InsuranceBizRuntimeException(resultCode.get("biz.insurance.cert.refund.bill.notfound"), "保险证退款单不存在！");
		}
		if (bill.getBillStatus() == InsCertRefundBillEnum.REFUND_FAIL.val || bill.getBillStatus() == InsCertRefundBillEnum.REFUSE_REFUND.val) {
			logger.warn("保险证退款单["+bill.getLcOrderId()+"]已是退款失败或拒绝退款状态！无须再处理。");
			return null;
		}
		bill.setBillStatus(InsCertRefundBillEnum.REFUSE_REFUND.val);//拒绝退款
		bill.setRefuseRefundCause(refundRefuse.getRefuseRefundCause());
		updateRefuseRefundState(bill);
		return bill;
	}
	
	@Transactional
	public InsCertRefundBill addInsCompanyInsCertRefundBill(InsUnopenRefundBill insUnopenRefundBill) {
		//1新增保险公司开证
		InsCertRefundBill newBill = new InsCertRefundBill();
		newBill.setVendorId(insUnopenRefundBill.getVendorId());
		newBill.setInsuranceCertId(insUnopenRefundBill.getInsuranceCertId());
		newBill.setInsuranceCertNo(insUnopenRefundBill.getInsuranceCertNo());
		newBill.setLogisticsCompany(insUnopenRefundBill.getLogisticsCompany());
		newBill.setLogisticsBillNo(insUnopenRefundBill.getLogisticsBillNo());
		newBill.setReturnDate(new Date());
		newBill.setRefundAmount(insUnopenRefundBill.getRefundAmount());
		//付款人信息
		newBill.setPayerType(1);//保险公司
		newBill.setPayerAccName(configProperties.get("ins.acctName"));
		newBill.setPayerBankAccount(configProperties.get("ins.bankAcctno"));
		newBill.setPayerBankCode(configProperties.get("ins.bankCode"));
	    newBill.setPayerBankName(configProperties.get("ins.bankName"));
		newBill.setPayerMobilePhone(configProperties.get("ins.mobile"));
		//收款人信息
		newBill.setPayeeAccName(insUnopenRefundBill.getPayeeAccName());
		newBill.setPayeeBankAccount(insUnopenRefundBill.getPayeeBankAccount());
		newBill.setPayeeBankCode(insUnopenRefundBill.getPayeeBankCode());
		newBill.setPayeeBankName(insUnopenRefundBill.getPayeeBankName());
		newBill.setPayeeMobilePhone(insUnopenRefundBill.getPayeeMobilePhone());
		//其它
		newBill.setBillStatus(InsCertRefundBillEnum.TO_OPEN.val);
        newBill.setVersion(0);
		newBill.setCreatedTime(new Date());
		newBill.setModifiedTime(new Date());
		int i = insCertRefundBillDao.add(newBill);
		//2更新原商户开证退款单状态为开证失败！
		if (i != 0) {
			InsCertRefundBill vendorBill = new InsCertRefundBill();
			vendorBill.setBillStatus(InsCertRefundBillEnum.OPEN_FAIL.val);
			vendorBill.setId(insUnopenRefundBill.getCertRefundBillId());
			i = insCertRefundBillDao.updateOpenFailState(vendorBill);
			//3删除未开证列表中的退款单记录
			if (i != 0) {
			    i = insUnopenRefundBillDao.delete(insUnopenRefundBill);//商户23h未开证成功的，直接从未开证列表清除
			}
		}
		if (i == 0) {
			throw new RuntimeException("新增保险公司退款单失败！[保险证号："+insUnopenRefundBill.getInsuranceCertNo()+"]");
		}
		return newBill;
	}

	@Override
	public List<InsCertRefundBill> getInsCertRefundBills(GetRefundBillInfo bean, Page<InsCertRefundBill> page) {
		return insCertRefundBillDao.getInsCertRefundBills(bean, page);
	}
	
}
