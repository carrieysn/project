package com.cifpay.lc.std.business.bank;

import com.cifpay.lc.api.BusinessOutput;
import com.cifpay.lc.api.VoidObject;
import com.cifpay.lc.api.gateway.basic.bankcode.BankCodeInfo;
import com.cifpay.lc.api.gateway.basic.bankcode.BankCodeQueryAllService;
import com.cifpay.lc.core.common.CoreBusinessContext;
import com.cifpay.lc.core.common.CoreBusinessServiceImplBase;
import com.cifpay.lc.core.db.dao.AdminCifpayLcBankDao;
import com.cifpay.lc.core.db.pojo.AdminCifpayLcBank;
import com.cifpay.lc.core.exception.CoreBusinessException;
import com.cifpay.lc.core.exception.CoreValidationRejectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("bankCodeQueryAllService")
public class BankCodeQueryAllServiceImpl extends CoreBusinessServiceImplBase<VoidObject, ArrayList<BankCodeInfo>>
		implements BankCodeQueryAllService {

	@Autowired
	private AdminCifpayLcBankDao adminCifpayLcBankDao;

	@Override
	protected void validate(VoidObject inputBean, CoreBusinessContext context) throws CoreValidationRejectException {
	}

	@Override
	protected BusinessOutput<ArrayList<BankCodeInfo>> processBusiness(VoidObject inputBean, CoreBusinessContext context)
			throws CoreBusinessException {

		ArrayList<BankCodeInfo> bankCodeList = new ArrayList<BankCodeInfo>();

		List<AdminCifpayLcBank> records = adminCifpayLcBankDao.selectAllValidRecords();
		if (null != records) {
			for (AdminCifpayLcBank lcBank : records) {
				BankCodeInfo bankCode = new BankCodeInfo();
				bankCode.setBankCode(lcBank.getBankCode());
				bankCode.setBankName(lcBank.getBankName());
				bankCode.setBankType(lcBank.getBankType());
				bankCodeList.add(bankCode);
			}
		}

		return BusinessOutput.success(bankCodeList);
	}

}
