package com.cifpay.insurance.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cifpay.insurance.InsuranceCertStatusEnum;
import com.cifpay.insurance.PolicyChangeListTypeEnum;
import com.cifpay.insurance.base.Page;
import com.cifpay.insurance.bean.PolicyAmountChangeBean;
import com.cifpay.insurance.bean.TodayAddedCertBean;
import com.cifpay.insurance.bean.VendorCertStaticBean;
import com.cifpay.insurance.bean.VendorReturnCertBean;
import com.cifpay.insurance.config.WebConstant;
import com.cifpay.insurance.dao.InsInsuranceCertDao;
import com.cifpay.insurance.dao.InsSalesOrderDao;
import com.cifpay.insurance.dao.InsSalesOrderItemsDao;
import com.cifpay.insurance.exception.InsuranceBizRuntimeException;
import com.cifpay.insurance.model.InsInsuranceCert;
import com.cifpay.insurance.model.InsPolicy;
import com.cifpay.insurance.model.InsSalesOrder;
import com.cifpay.insurance.model.InsSalesOrderItems;
import com.cifpay.insurance.model.InsVendorReturnAddress;
import com.cifpay.insurance.param.cert.CreateInsuranceCertBatchInfo;
import com.cifpay.insurance.param.cert.CreateInsuranceCertInfo;
import com.cifpay.insurance.param.cert.CreateInsuranceCertResult;
import com.cifpay.insurance.param.cert.GetInsuranceCertListInfo;
import com.cifpay.insurance.param.cert.OrderInfo;
import com.cifpay.insurance.param.cert.OrderItemCertInfo;
import com.cifpay.insurance.param.cert.OrderItemsInfo;
import com.cifpay.insurance.push.InsuranceEventDefaultHandler;
import com.cifpay.insurance.push.event.InsuranceCertStateChangeEvent;
import com.cifpay.insurance.push.event.InsurancePolicyEvent;
import com.cifpay.insurance.service.InsInsuranceCertService;
import com.cifpay.insurance.service.InsPolicyService;
import com.cifpay.insurance.service.InsVendorReturnAddressService;
import com.cifpay.insurance.service.SysCodeRuleService;
import com.cifpay.insurance.util.DateUtil;
import com.cifpay.starframework.cache.ServiceResultCodeCache;
import com.cifpay.starframework.model.ServiceResult;

@Service("insInsuranceCertService")
public class InsInsuranceCertServiceImpl implements InsInsuranceCertService {
	private static final Logger logger = LogManager.getLogger(InsInsuranceCertServiceImpl.class);
	private static ServiceResultCodeCache resultCode = ServiceResultCodeCache.getInstance();
	@Autowired
	private InsInsuranceCertDao insInsuranceCertDao;
	@Autowired
	private InsPolicyService insPolicyService;
	@Autowired
	private InsSalesOrderDao insSalesOrderDao;
	@Autowired
	private InsSalesOrderItemsDao insSalesOrderItemsDao;
	@Autowired
	private SysCodeRuleService sysCodeRuleService;
	@Autowired
	private InsVendorReturnAddressService insVendorReturnAddressService;//商户地址

	@Override
	public InsInsuranceCert get(long ID) {
		return insInsuranceCertDao.get(ID);
	}

	@Override
	public ServiceResult<String> add(InsInsuranceCert insInsuranceCert) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insInsuranceCertDao.add(insInsuranceCert);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.success"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> addSelective(InsInsuranceCert insInsuranceCert) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insInsuranceCertDao.addSelective(insInsuranceCert);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.success"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> update(InsInsuranceCert insInsuranceCert) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insInsuranceCertDao.update(insInsuranceCert);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.success"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> updateSelective(InsInsuranceCert insInsuranceCert) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insInsuranceCertDao.updateSelective(insInsuranceCert);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.success"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> delete(InsInsuranceCert insInsuranceCert) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insInsuranceCertDao.delete(insInsuranceCert);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.success"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public List<InsInsuranceCert> getList() {
		return insInsuranceCertDao.getList();
	}

	@Override
	public int getCount() {
		return insInsuranceCertDao.getCount();
	}
	
	@Override
	public InsInsuranceCert getInsInsuranceCertByNo(String InsuranceCertNo) {
		return insInsuranceCertDao.getInsInsuranceCertByNo(InsuranceCertNo);
	}

	@Transactional
	public ServiceResult<CreateInsuranceCertResult> createInsInsuranceCert(String vendorId, CreateInsuranceCertInfo createInsuranceCertInfo) {
		ServiceResult<CreateInsuranceCertResult> ret = new ServiceResult<CreateInsuranceCertResult>();
		// 创建保险证信息
		InsPolicy ip = insPolicyService.getPolicyByVendorId(vendorId);
		if (ip == null) {
			ret.setCode(resultCode.get("biz.policy.notfound"));
			return ret;
		}
		List<InsInsuranceCert> certs = createInsInsuranceCert(createInsuranceCertInfo.getNoticeUrl(), createInsuranceCertInfo.getOrderInfo(), ip);
		CreateInsuranceCertResult cr = createInsuranceCertResultAndNotice(certs);
		ret.setCode(resultCode.get("common.success"));
		ret.setObj(cr);
		//通知保额变化
		noticePolicyRealtimeEvent(vendorId);
		return ret;
	}
	
	/**
	 * 通知保单实时信息，若有异常则忽略
	 * 
	 * @param ip
	 */
	private void noticePolicyRealtimeEvent(String vendorId) {
		try {//TODO 使用缓存
			InsurancePolicyEvent event = new InsurancePolicyEvent(this);
			event.setVendorId(vendorId);
			InsuranceEventDefaultHandler.getInstance().doPremiumChange(event);
		} catch (Exception e) {
			logger.warn("通知保单实时信息出现异常，忽略", e);
		}
	}
	
	private List<InsInsuranceCert> createInsInsuranceCert(String noticeUrl, OrderInfo oi, InsPolicy ip) {
		InsSalesOrder oldSo = insSalesOrderDao.getFullInsSalesOrder(ip.getVendorId(), oi.getOrderNo());
		if (oldSo != null) {//TODO 返回存在的保险证号？
			throw new InsuranceBizRuntimeException(resultCode.get("biz.insurance.cert.order.exists"), "商户商品订单已存在！");
		}
		// 订单信息
		InsSalesOrder so = new InsSalesOrder();
		so.setVendorId(ip.getVendorId());
		so.setOrderDate(DateUtil.parseDate(oi.getOrderDate(), "yyyy-MM-dd"));
		so.setOrderNo(oi.getOrderNo());
		so.setPayMode(oi.getPayMode());
		so.setBuyerName(oi.getBuyerName());
		so.setBuyerMobilePhone(oi.getBuyerMobilePhone());
		//受理方退货地址信息
		InsVendorReturnAddress addr = insVendorReturnAddressService.getDefaultInsVendorReturnAddress(ip.getVendorId());
		if (addr == null) {
			throw new InsuranceBizRuntimeException(resultCode.get("biz.vendor.returnaddress.default.notset"), "未设置默认退货地址！");
		}
		so.setReturnAddress(addr.getAddress());
		so.setReturnContacts(addr.getContacts());
		so.setReturnPhone(addr.getMobilePhone());
		insSalesOrderDao.add(so);
		// 订单商品明细信息
		List<InsSalesOrderItems> items = new ArrayList<InsSalesOrderItems>();
		List<InsInsuranceCert> certs = new ArrayList<InsInsuranceCert>();
		for (OrderItemsInfo oii: oi.getOrderItemsInfo()) {
			InsSalesOrderItems iso = new InsSalesOrderItems();
			iso.setSalesOrderId(so.getId());
			iso.setOrderNo(so.getOrderNo());
			iso.setGoodsName(oii.getGoodsName());
			iso.setGoodsNo(oii.getGoodsNo());
			iso.setPrice(oii.getPrice());
			iso.setQuantity(oii.getQuantity());
			iso.setTotalPrice(oii.getTotalPrice());
			items.add(iso);
			InsInsuranceCert cert = addInsuranceCert(noticeUrl, ip, iso);
			certs.add(cert);
		}
		insSalesOrderItemsDao.addBatch(items);
		return certs;
	}
	
	/** 设置返回结果 **/
	private CreateInsuranceCertResult createInsuranceCertResultAndNotice(List<InsInsuranceCert> certs) {
		CreateInsuranceCertResult cr = new CreateInsuranceCertResult();
		for (InsInsuranceCert cert: certs) {
			OrderItemCertInfo oi = new OrderItemCertInfo();
			cr.getOrderItemCertInfos().add(oi);
			cr.setOrderNo(cert.getInsSalesOrderItems().getOrderNo());
			oi.setGoodsNo(cert.getInsSalesOrderItems().getGoodsNo());
			oi.setInsuranceCertId(cert.getId().toString());
			oi.setInsuranceCertNo(cert.getInsuranceCertNo());	
			//通知保险证状态改变事件（新增）
			noticeCertAddEvent(cert, InsuranceCertStatusEnum.NOT_EFFECTIVED);
		}
		return cr;
	}
	
	private InsInsuranceCert addInsuranceCert(String noticeUrl, InsPolicy ip, InsSalesOrderItems iso) {
		//保险证基本信息
		InsInsuranceCert cert = new InsInsuranceCert();
		cert.setPolicyNo(ip.getPolicyNo());
		cert.setPolicyId(ip.getId());
		cert.setVendorId(ip.getVendorId());
		cert.setNoticeUrl(noticeUrl);
		if (cert.getEffectivePeriod() == null) {
			cert.setEffectivePeriod(7);// 默认7天
		}
		cert.setStatus(InsuranceCertStatusEnum.NOT_EFFECTIVED.val);// 未生效
		cert.setIsSign(0);//未签收
		cert.setTotalPrice(iso.getTotalPrice());
		cert.setCreatedTime(new Date());
		cert.setModifiedTime(new Date());
		cert.setVersion(0);
		cert.setIsLcOpen(0);
		//TODO 一次获取多个号码段。
		String newInsuranceCertNo = sysCodeRuleService.generateCodeNumber(WebConstant.CODE_INSINSURANCECERT_INSURANCECERTNO, cert, cert.getVendorId());
		cert.setInsuranceCertNo(newInsuranceCertNo);// 保险证号
		insInsuranceCertDao.add(cert);//addBatch??
		iso.setInsuranceCertId(cert.getId());
		cert.setInsSalesOrderItems(iso);//须设置
		//冻结保额，增加变动流水
		freezeInsuredAmount(cert);
		return cert;
	}
	
	private void freezeInsuredAmount(InsInsuranceCert cert) {
		PolicyAmountChangeBean b = new PolicyAmountChangeBean();
		b.setChangeAmount(cert.getTotalPrice());
		b.setRefId(cert.getId());
		b.setRefVoucherNo(cert.getInsuranceCertNo());
		b.setType(PolicyChangeListTypeEnum.ADD_CERT);
		b.setVendorId(cert.getVendorId());
		insPolicyService.freezeInsuredAmount(b);
	}
	
	/**
	 * 通知保险证状态改变事件
	 * 
	 * @param cert
	 * @param changeStatus
	 */
	private void noticeCertAddEvent(InsInsuranceCert cert, InsuranceCertStatusEnum changeStatus) {
		//TODO 应用缓存处理
		try {
			InsuranceCertStateChangeEvent ce = new InsuranceCertStateChangeEvent(this);
			ce.setVendorId(cert.getVendorId());
			ce.setStatus(changeStatus.val);
			ce.setTotalPrice(cert.getTotalPrice());
			ce.setIsIcRefund(cert.getIsIcRefund());
			InsuranceEventDefaultHandler.getInstance().doInsuranceCertStateChange(ce);
		} catch (Exception e) {
			logger.warn("通知保险证状态改变出现异常，忽略", e);
		}
	}
	
	
	@Transactional
	public ServiceResult<List<CreateInsuranceCertResult>> createInsInsuranceCertBatch(String vendorId, CreateInsuranceCertBatchInfo createInsuranceCertBatchInfo) {
		ServiceResult<List<CreateInsuranceCertResult>> ret = new ServiceResult<List<CreateInsuranceCertResult>>();
		// 创建保险证信息
		InsPolicy ip = insPolicyService.getPolicyByVendorId(vendorId);
		if (ip == null) {
			ret.setCode(resultCode.get("biz.policy.notfound"));
			return ret;
		}
        
		Map<String, List<InsInsuranceCert>> mcerts = new LinkedHashMap<String, List<InsInsuranceCert>>();
		for (OrderInfo oi :createInsuranceCertBatchInfo.getOrderInfos()) {
			List<InsInsuranceCert> certs = createInsInsuranceCert(createInsuranceCertBatchInfo.getNoticeUrl(), oi, ip);
			mcerts.put(oi.getOrderNo(), certs);
		}
		//创建返回结果并发送通知。
		List<CreateInsuranceCertResult> resultList = new ArrayList<CreateInsuranceCertResult>();
		for (List<InsInsuranceCert> certs :mcerts.values()) {
			CreateInsuranceCertResult cr = createInsuranceCertResultAndNotice(certs);
			resultList.add(cr);
		}
		ret.setCode(resultCode.get("common.success"));
		ret.setObj(resultList);
		return ret;
	}

	@Override
	public InsInsuranceCert getInsInsuranceCertByCertNo(String certNo) {
		InsInsuranceCert cert = insInsuranceCertDao.getInsInsuranceCertByCertNo(certNo);
		return cert;
	}

	public List<InsInsuranceCert> getInsInsuranceCertList(String vendorId, GetInsuranceCertListInfo certList, Page<InsInsuranceCert> page) {
		return insInsuranceCertDao.getInsInsuranceCertList(vendorId, certList, page);
	}

	@Override
	public void updateEffectiveState(InsInsuranceCert insInsuranceCert) {
		//设置生效
		insInsuranceCert.setStatus(2);
		int i = insInsuranceCertDao.updateEffectiveState(insInsuranceCert);
		if (i == 0) {
			throw new InsuranceBizRuntimeException(resultCode.get("common.record.version.ischanged"), "本次生效状态变更失败，记录已被更新！");
		}
	}
	
	@Override
	public void updateLcOpenState(InsInsuranceCert insInsuranceCert) {
		//设置开证成功
		insInsuranceCert.setIsLcOpen(1);
		int i = insInsuranceCertDao.updateLcOpenState(insInsuranceCert);
		if (i == 0) {
			throw new InsuranceBizRuntimeException(resultCode.get("common.record.version.ischanged"), "本次退货申请失败，记录已被更新！");
		}
		//通知保险证状态改变事件（退货）
		//noticeCertAddEvent(insInsuranceCert, InsuranceCertStatusEnum.TO_OPEN);
	}
	
	@Override
	public void updateToRefundState(InsInsuranceCert insInsuranceCert) {
		InsInsuranceCert iic = insInsuranceCert;
        //1、更新保险证状态
		if (insInsuranceCert.getVersion() == null) {
			iic = insInsuranceCertDao.get(insInsuranceCert.getId());
		}
		if (iic.getStatus() == null || iic.getStatus() != InsuranceCertStatusEnum.EFFECTIVE.val) {
			throw new InsuranceBizRuntimeException(resultCode.get("biz.insurance.cert.uneffectived"), "保险证不是生效状态，不能申请退货！");
		}
		//设置待退款状态
		iic.setStatus(InsuranceCertStatusEnum.TO_REFUND.val);// 待退款
		int i = insInsuranceCertDao.updateToRefundState(iic);
		if (i == 0) {
			throw new InsuranceBizRuntimeException(resultCode.get("common.record.version.ischanged"), "本次更新待退款状态失败，记录已被更新！");
		}
		//通知保险证状态改变事件（待退款）
		noticeCertAddEvent(iic, InsuranceCertStatusEnum.TO_REFUND);
	}
	
	@Override
	public void updateRefuseRefund(InsInsuranceCert insInsuranceCert) {
        //1、更新保险证状态
		InsInsuranceCert iic = insInsuranceCertDao.get(insInsuranceCert.getId());
		if (iic.getStatus() == null || iic.getStatus() != InsuranceCertStatusEnum.TO_REFUND.val) {
			throw new InsuranceBizRuntimeException(resultCode.get("biz.insurance.cert.nottoberefunded"), "保险证不是待退款状态，不能变更为拒绝退款状态！");
		}
		iic.setStatus(InsuranceCertStatusEnum.REFUSE_REFUND.val);// 拒绝退款
		iic.setIsIcRefund(insInsuranceCert.getIsIcRefund());
		int i = insInsuranceCertDao.updateRefuseRefundState(iic);
		if (i == 0) {
			throw new InsuranceBizRuntimeException(resultCode.get("common.record.version.ischanged"),"本次拒绝退款失败，记录已被更新！");
		}
		//通知保险证状态改变事件（拒绝退款）
		noticeCertAddEvent(iic, InsuranceCertStatusEnum.REFUSE_REFUND);
	}
	
	@Override
	public void updateRefundSuccessState(InsInsuranceCert insInsuranceCert) {
        //1、更新保险证状态
		InsInsuranceCert iic = insInsuranceCertDao.get(insInsuranceCert.getId());
		if (iic.getStatus() == null || iic.getStatus() != InsuranceCertStatusEnum.TO_REFUND.val) {
			throw new InsuranceBizRuntimeException(resultCode.get("biz.insurance.cert.nottoberefunded"), "保险证不是待退款状态，不能变更为拒绝退款状态！");
		}
		iic.setStatus(InsuranceCertStatusEnum.FINISH_REFUND.val);// 退款成功
		iic.setIsIcRefund(insInsuranceCert.getIsIcRefund());
		int i = insInsuranceCertDao.updateRefundSuccessState(iic);
		if (i == 0) {
			throw new InsuranceBizRuntimeException(resultCode.get("common.record.version.ischanged"),"本次完成退款失败，记录已被更新！");
		}
		//通知保险证状态改变事件（拒绝退款）
		noticeCertAddEvent(iic, InsuranceCertStatusEnum.FINISH_REFUND);
	}
	
	public TodayAddedCertBean getTodayAddedCert(String vendorId) {
		Date beginDate = DateUtil.getDateYMD();
		Date endDate = DateUtil.addDay(beginDate, 1);
		return insInsuranceCertDao.getTodayAddedCert(vendorId, beginDate, endDate, InsuranceCertStatusEnum.NOT_EFFECTIVED.val);
	}
	
	@Override
	public TodayAddedCertBean getTodayAllCert(String vendorId) {
		Date beginDate = DateUtil.getDateYMD();
		Date endDate = DateUtil.addDay(beginDate, 1);
		return insInsuranceCertDao.getTodayAddedCert(vendorId, beginDate, endDate, null);
	}
	
	@Override
	public VendorReturnCertBean getVendorTodayReturnCert(String vendorId) {
		Date beginDate = DateUtil.getDateYMD();
		Date endDate = DateUtil.addDay(beginDate, 1);
		return insInsuranceCertDao.getVendorReturnCert(vendorId, beginDate, endDate, null);
	}

	@Override
	public VendorReturnCertBean getVendorAllToRefundCert(String vendorId) {
		return insInsuranceCertDao.getVendorReturnCert(vendorId, null, null, InsuranceCertStatusEnum.TO_REFUND.val);
	}

	@Override
	public void signLogistics(InsInsuranceCert insInsuranceCert) {
		InsInsuranceCert getCert = insInsuranceCertDao.getInsInsuranceCertByNo(insInsuranceCert.getInsuranceCertNo());
		if (getCert == null || !getCert.getVendorId().equals(insInsuranceCert.getVendorId())) {
			throw new InsuranceBizRuntimeException(resultCode.get("biz.insurance.cert.notfound"), "商户保险证号不存在！");
		}
		if (getCert.getStatus() == null || getCert.getStatus() != InsuranceCertStatusEnum.NOT_EFFECTIVED.val) {
			throw new InsuranceBizRuntimeException(resultCode.get("biz.insurance.cert.refuseRefund"), "保险证号为"+getCert.getInsuranceCertNo()+"的保险证不能被签收！");
		}
		insInsuranceCert.setId(getCert.getId());
		//insInsuranceCert.setStatus(InsuranceCertStatusEnum.SIGNED.val);// 已签收
		insInsuranceCert.setIsSign(1);// 已签收
		insInsuranceCert.setSignTime(new Date());
		insInsuranceCert.setVersion(getCert.getVersion());
		insInsuranceCert.setModifiedTime(new Date());
		int i = insInsuranceCertDao.updateSelective(insInsuranceCert);
		if (i == 0) {
			throw new InsuranceBizRuntimeException(resultCode.get("common.record.version.ischanged"),"签收失败，记录已被更新！");
		}
	}

	@Override
	public List<VendorCertStaticBean> getVendorCertStatic(String vendorId, String policyNo) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("vendorId", vendorId);
		params.put("policyNo", policyNo);
		Date todayBegin = DateUtil.getDateYMD();//今日开始
		Date todayEnd = DateUtil.addDay(todayBegin, 1);
		params.put("todayBegin", todayBegin);
		params.put("todayEnd", todayEnd);
		Date yesterdayBegin = DateUtil.addDay(todayBegin, -1);//昨日开始
		params.put("yesterdayBegin", yesterdayBegin);
		params.put("yesterdayEnd", todayBegin);
		Date thisMonthBegin = DateUtil.getThisMonthStartDate();//本月开始
		Date thisMonthEnd = todayBegin;//本月截止今日（不包含今日）
		params.put("thisMonthBegin", thisMonthBegin);
		params.put("thisMonthEnd", thisMonthEnd);
		Date lastMonthBegin = DateUtil.addMonth(thisMonthBegin, -1);//上月开始
		params.put("lastMonthBegin", lastMonthBegin);
		params.put("lastMonthEnd", thisMonthBegin);//不包含本月开始日
		Date thisYearBegin = DateUtil.getThisYearStartDate();
		Date thisYearEnd = todayBegin; //今年截止今日（不包含今日）
		params.put("thisYearBegin", thisYearBegin);
		params.put("thisYearEnd", thisYearEnd);
		Date lastYearBegin = DateUtil.addYear(thisYearBegin, -1);
		params.put("lastYearBegin", lastYearBegin);
		params.put("lastYearEnd", thisYearBegin);//不包含今年开始日
		return insInsuranceCertDao.getVendorCertStatic(params);
	}
	
	public void checkExpiredInsuranceCert() {
		List<InsInsuranceCert> ics = getExpiredInsInsuranceCerts(null);
		if (ics.isEmpty()) return;
		InsInsuranceCert ic = null;
		for (int i = 0; i < ics.size(); i++) {
			ic = ics.get(i);
			ic.setModifiedTime(new Date());
			ic.setStatus(InsuranceCertStatusEnum.EXPIRED.val);
			try {
				updateExpiredStatus(ic);
			} catch (Exception e) {//出错，继续
				logger.error(String.format("失效处理失败！[vendorId：%s，certNo：%s]", ic.getVendorId(), ic.getInsuranceCertNo()), e);
			}
		}
		//通知保险证状态改变事件（失效）
		noticeCertAddEvent(ic, InsuranceCertStatusEnum.EXPIRED);
	}
	
	@Transactional
	public void updateExpiredStatus(InsInsuranceCert insInsuranceCert) {
		int i = insInsuranceCertDao.updateExpiredStatus(insInsuranceCert);
		if (i == 0) {
			throw new InsuranceBizRuntimeException(resultCode.get("common.record.version.ischanged"),"本次失效状态变更失败，记录已被更新！");
		}
		unfreezeInsuredAmount(insInsuranceCert);
	}
	
	/**
	 * 失效释放保额
	 * 
	 * @param cert
	 * @param type
	 */
	private void unfreezeInsuredAmount(InsInsuranceCert cert) {
		PolicyAmountChangeBean b = new PolicyAmountChangeBean();
		b.setChangeAmount(cert.getTotalPrice());
		b.setRefId(cert.getId());
		b.setRefVoucherNo(cert.getInsuranceCertNo());
		b.setType(PolicyChangeListTypeEnum.EXPIRED_CERT);
		b.setVendorId(cert.getVendorId());
		insPolicyService.unfreezeInsuredAmount(b);
	}
	
	@Override
	public List<InsInsuranceCert> getExpiredInsInsuranceCerts(String vendorId) {
		Date d = DateUtil.addDay(DateUtil.getDateYMD(), 1);
		return insInsuranceCertDao.getExpiredInsInsuranceCerts(vendorId, d);
	}

	@Override
	public void checkSignedInsuranceCert() {
		List<InsInsuranceCert> ics = getSignedInsInsuranceCerts(null);
		if (ics.isEmpty()) return;
		InsInsuranceCert ic = null;
		InsInsuranceCert updateIC = new InsInsuranceCert();
		for (int i = 0; i < ics.size(); i++) {
			ic = ics.get(i);
			updateIC.setId(ic.getId());
			updateIC.setVersion(ic.getVersion());
			updateIC.setModifiedTime(new Date());
			updateIC.setStatus(InsuranceCertStatusEnum.EFFECTIVE.val);
			Date effectivedTime = DateUtil.parseDate(DateUtil.formatDate(ic.getSignTime(), "yyyy-MM-dd"), "yyyy-MM-dd");
			effectivedTime = DateUtil.addDay(effectivedTime, 1);//生效时间从第二天０点开始。
			Date expiredTime = DateUtil.addDay(effectivedTime, ic.getEffectivePeriod());
			updateIC.setEffectiveTime(effectivedTime);//生效时间
			updateIC.setExpiredTime(expiredTime);//过期时间
			//insInsuranceCertDao.updateSelective(updateIC);
			try {
				updateEffectiveState(updateIC);
			} catch (Exception e) {//on error go on...
				logger.error(String.format("生效处理失败！[vendorId：%s，certNo：%s]", ic.getVendorId(), ic.getInsuranceCertNo()), e);
			}
		}
		
	}

	@Override
	public List<InsInsuranceCert> getSignedInsInsuranceCerts(String vendorId) {
		return insInsuranceCertDao.getSignedInsInsuranceCerts(vendorId, DateUtil.getDateYMD());
	}
	
	@Override
	public InsInsuranceCert getFullInsInsuranceCertByCertNo(String InsuranceCertNo) {
		return insInsuranceCertDao.getFullInsInsuranceCertByCertNo(InsuranceCertNo);
	}
}
