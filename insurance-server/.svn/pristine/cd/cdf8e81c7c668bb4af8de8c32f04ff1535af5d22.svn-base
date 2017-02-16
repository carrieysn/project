package com.cifpay.insurance.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cifpay.insurance.dao.InsUnopenRefundBillDao;
import com.cifpay.insurance.model.InsUnopenRefundBill;
import com.cifpay.insurance.service.InsUnopenRefundBillService;
import com.cifpay.starframework.cache.ServiceResultCodeCache;
import com.cifpay.starframework.model.ServiceResult;

@Service("insUnopenRefundBillService")
public class InsUnopenRefundBillServiceImpl implements InsUnopenRefundBillService {
	private static ServiceResultCodeCache resultCode = ServiceResultCodeCache.getInstance();
	@Autowired
	private InsUnopenRefundBillDao insUnopenRefundBillDao;

	@Override
	public InsUnopenRefundBill get(long id) {
		return insUnopenRefundBillDao.get(id);
	}

	@Override
	public ServiceResult<String> add(InsUnopenRefundBill insUnopenRefundBill) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insUnopenRefundBillDao.add(insUnopenRefundBill);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> addSelective(InsUnopenRefundBill insUnopenRefundBill) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insUnopenRefundBillDao.addSelective(insUnopenRefundBill);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> update(InsUnopenRefundBill insUnopenRefundBill) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insUnopenRefundBillDao.update(insUnopenRefundBill);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> updateSelective(InsUnopenRefundBill insUnopenRefundBill) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insUnopenRefundBillDao
				.updateSelective(insUnopenRefundBill);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> delete(InsUnopenRefundBill insUnopenRefundBill) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = insUnopenRefundBillDao.delete(insUnopenRefundBill);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public List<InsUnopenRefundBill> getList() {
		return insUnopenRefundBillDao.getList();
	}

	@Override
	public int getCount() {
		return insUnopenRefundBillDao.getCount();
	}

	@Override
	public List<InsUnopenRefundBill> getUnexpiredInsUnopenRefundBills() {
		return insUnopenRefundBillDao.getUnexpiredInsUnopenRefundBills();
	}

	@Override
	public List<InsUnopenRefundBill> getExpiredInsUnopenRefundBills() {
		return insUnopenRefundBillDao.getExpiredInsUnopenRefundBills();
	}
}
