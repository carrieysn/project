package com.cifpay.insurance.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cifpay.insurance.LcStateEnum;
import com.cifpay.insurance.PolicyOrderStatusEnum;
import com.cifpay.insurance.base.Page;
import com.cifpay.insurance.config.WebConstant;
import com.cifpay.insurance.dao.InsPolicyOrderDao;
import com.cifpay.insurance.dao.InsPolicyOrderItemDao;
import com.cifpay.insurance.exception.InsuranceBizRuntimeException;
import com.cifpay.insurance.model.InsPolicy;
import com.cifpay.insurance.model.InsPolicyHolder;
import com.cifpay.insurance.model.InsPolicyOrder;
import com.cifpay.insurance.model.InsPolicyOrderItem;
import com.cifpay.insurance.param.policy.GetPolicyOrderListInfo;
import com.cifpay.insurance.service.InsPolicyOrderService;
import com.cifpay.insurance.service.InsPolicyService;
import com.cifpay.insurance.service.SysCodeRuleService;
import com.cifpay.starframework.cache.ServiceResultCodeCache;
import com.cifpay.starframework.model.ServiceResult;

@Service("insPolicyOrderService")
public class InsPolicyOrderServiceImpl implements InsPolicyOrderService {
	private static final Logger logger = LoggerFactory.getLogger(InsPolicyOrderServiceImpl.class);
	private static ServiceResultCodeCache resultCode = ServiceResultCodeCache.getInstance();
	@Autowired
	private InsPolicyService insPolicyService;
	@Autowired
	private InsPolicyOrderDao insPolicyOrderDao;
	@Autowired
	private InsPolicyOrderItemDao insPolicyOrderItemDao;
	@Autowired
	private SysCodeRuleService sysCodeRuleService;
	
	@Override
	public InsPolicyOrder get(long id) {
		return insPolicyOrderDao.get(id);
	}

	@Override
	public ServiceResult<String> add(InsPolicyOrder insPolicyOrder) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insPolicyOrderDao.add(insPolicyOrder);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> addSelective(InsPolicyOrder insPolicyOrder) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insPolicyOrderDao.addSelective(insPolicyOrder);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> update(InsPolicyOrder insPolicyOrder) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insPolicyOrderDao.update(insPolicyOrder);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> updateSelective(InsPolicyOrder insPolicyOrder) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insPolicyOrderDao.updateSelective(insPolicyOrder);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> delete(InsPolicyOrder insPolicyOrder) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insPolicyOrderDao.delete(insPolicyOrder);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public List<InsPolicyOrder> getList() {
		return insPolicyOrderDao.getList();
	}

	@Override
	public int getCount() {
		return insPolicyOrderDao.getCount();
	}

	public InsPolicyOrderDao getInsPolicyOrderDao() {
		return insPolicyOrderDao;
	}

	public void setInsPolicyOrderDao(InsPolicyOrderDao insPolicyOrderDao) {
		this.insPolicyOrderDao = insPolicyOrderDao;
	}

	@Transactional
	public void createInsPolicyOrder(InsPolicyOrder insPolicyOrder) {
		InsPolicy ip = insPolicyService.getPolicyByVendorId(insPolicyOrder.getVendorId());
		if (ip != null) {
			throw new InsuranceBizRuntimeException(resultCode.get("biz.policy.repeat"), "商户不能重复投保！[保单号："+ip.getPolicyNo()+"]");
		}
		String billNo = sysCodeRuleService.generateCodeNumber(WebConstant.CODE_INSPOLICYORDER_BILLNO);
		insPolicyOrder.setBillNo(billNo);
		insPolicyOrder.setType(0);//投保订单
		insPolicyOrder.setStatus(0);// 默认未支付
		insPolicyOrder.setCreatedTime(new Date());
		insPolicyOrder.setModifiedTime(new Date());
		insPolicyOrder.setVersion(0);
		
		insPolicyOrderDao.add(insPolicyOrder);
		
		InsPolicyOrderItem item = insPolicyOrder.getInsPolicyOrderItem();
		item.setPolicyOrderId(insPolicyOrder.getId());
		insPolicyOrderItemDao.add(item);
	}

	public void createChargeFeePolicyOrder(InsPolicyOrder insPolicyOrder) {
		InsPolicy ip = insPolicyService.get(insPolicyOrder.getPolicyId());
		if (ip == null) {
			throw new InsuranceBizRuntimeException(resultCode.get("biz.policy.notfound"), "保单不存在！");
		}
		InsPolicyOrder ipo = new InsPolicyOrder();
		ipo.setVendorId(insPolicyOrder.getVendorId());// 商户ID
		ipo.setAmount(insPolicyOrder.getAmount());
		ipo.setPolicyId(ip.getId());
		String billNo = sysCodeRuleService.generateCodeNumber(WebConstant.CODE_INSPOLICYORDER_BILLNO);
		ipo.setBillNo(billNo);
		ipo.setStatus(0);// 默认未支付
		ipo.setType(1);//充值订单
		ipo.setBeforePremium(ip.getPremium());
		ipo.setCreatedTime(new Date());
		ipo.setModifiedTime(new Date());
		ipo.setVersion(0);
		insPolicyOrderDao.add(ipo);
		insPolicyOrder.setBillNo(ipo.getBillNo());
		insPolicyOrder.setPolicyId(ipo.getPolicyId());
	}
	
	@Override
	public List<InsPolicyOrder> getInsPolicyOrderList(GetPolicyOrderListInfo getChargeFeePolicyOrderListInfo, Page<InsPolicyOrder> page) {
		return insPolicyOrderDao.getInsPolicyOrderList(getChargeFeePolicyOrderListInfo, page);
	}
	
	/**
	 * 创建保单。
	 * 
	 * @param ipo
	 */
	private void createInsPolicy(InsPolicyOrder ipo) {
		InsPolicyOrderItem item = ipo.getInsPolicyOrderItem();
		if (item == null) {//impossible;
			throw new InsuranceBizRuntimeException(resultCode.get("biz.policy.order.item.notfound"), "保单投保订单明细不存在！");
		}
		// 投保人
		InsPolicyHolder insPolicyHolder = new InsPolicyHolder();
		insPolicyHolder.setVendorId(ipo.getVendorId());// 商户ID
		insPolicyHolder.setHolderType(item.getHolderType());
		insPolicyHolder.setHolderName(item.getHolderName());
		insPolicyHolder.setPhone(item.getPhone());
		insPolicyHolder.setIdType(item.getIdType());
		insPolicyHolder.setIdNo(item.getIdNo());
		insPolicyHolder.setContacts(item.getContacts());
		insPolicyHolder.setPhone(item.getPhone());
		insPolicyHolder.setEmail(item.getEmail());
		// 保单
		InsPolicy insPolicy = new InsPolicy();
		insPolicy.setVendorId(ipo.getVendorId());
		insPolicy.setInsPolicyHolder(insPolicyHolder);
		insPolicy.setProduct(item.getProduct());
		insPolicy.setInsuredName(item.getInsuredName());// 被保人为空 默认
		insPolicy.setInsurancePeriod(item.getInsurancePeriod());
		insPolicy.setInitPremium(ipo.getAmount());
		insPolicy.setPremium(ipo.getAmount());
		insPolicy.setPayMode(1);
		insPolicy.setInsuredid(item.getInsuredid());// 保险标的
		
		insPolicyService.createInsPolicy(insPolicy);
		ipo.setPolicyId(insPolicy.getId());//最后更新保单id
	}
	
	@Override
	@Transactional
	public void noticePolicyOrder(InsPolicyOrder insPolicyOrder) {
		InsPolicyOrder ipo = insPolicyOrderDao.getInsPolicyOrderByBillNo(insPolicyOrder.getBillNo());
		if (ipo == null) {
			throw new InsuranceBizRuntimeException(resultCode.get("biz.policy.order.notfound"), "缴费订单不存在！[订单号："+insPolicyOrder.getBillNo()+"]");
		}
		insPolicyOrder.setAmount(ipo.getAmount());//返回？
		if (ipo.getStatus() == PolicyOrderStatusEnum.PAY_FINISH.val) {//已支付
			insPolicyOrder.setStatus(ipo.getStatus());//返回？
			insPolicyOrder.setVendorId(ipo.getVendorId());
			logger.warn("订单["+insPolicyOrder.getBillNo()+"]已完成支付！无须再处理。");
			return;
		}
		if (insPolicyOrder.getLcState().equals(ipo.getLcState())) {
			insPolicyOrder.setStatus(ipo.getStatus());//返回？
			insPolicyOrder.setVendorId(ipo.getVendorId());
			logger.warn("订单["+insPolicyOrder.getBillNo()+"]已进行状态["+insPolicyOrder.getLcState()+"]处理！无须再处理。");
			return;
		}
		ipo.setLcId(insPolicyOrder.getLcId());//第二次没必要
		ipo.setLcNo(insPolicyOrder.getLcNo());
		ipo.setLcState(insPolicyOrder.getLcState());
		ipo.setTradeDate(insPolicyOrder.getTradeDate());
		ipo.setModifiedTime(new Date());
		if (LcStateEnum.CREDIT_PAYED.val.equals(insPolicyOrder.getLcState())) {//支付完成
			ipo.setStatus(PolicyOrderStatusEnum.PAY_FINISH.val);
			InsPolicyOrder ipoPolicy = new InsPolicyOrder(); 
			BeanUtils.copyProperties(ipo, ipoPolicy);
			if (ipo.getType() == 0) {//投保,须生成保单
				createInsPolicy(ipoPolicy);
				ipo.setPolicyId(ipoPolicy.getId());//新保单id
			} else {//充值
				insPolicyService.addPolicyPremium(ipoPolicy);
				ipo.setBeforePremium(ipoPolicy.getBeforePremium());//充值后余额
			}
		}
		int i = insPolicyOrderDao.updateSelective(ipo);
		if (i == 0) {//?
			throw new InsuranceBizRuntimeException(resultCode.get("common.record.version.ischanged"), "订单数据已被更新！[订单号："+insPolicyOrder.getBillNo()+"]");
		}
		insPolicyOrder.setStatus(ipo.getStatus());//返回？
		insPolicyOrder.setVendorId(ipo.getVendorId());
	}
	
	@Override
	public InsPolicyOrder getInsPolicyOrderByBillNo(String billNo) {
		return insPolicyOrderDao.getInsPolicyOrderByBillNo(billNo);
	}
}
