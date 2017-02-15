package com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cifpay.lc.api.BusinessOutput;
import com.cifpay.lc.api.VoidObject;
import com.cifpay.lc.core.common.CoreBusinessContext;
import com.cifpay.lc.core.common.CoreBusinessServiceImplBase;
import com.cifpay.lc.core.exception.CoreBusinessException;
import com.cifpay.lc.core.exception.CoreValidationRejectException;
import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.helper.ICBCTradeDateManager;
import com.cifpay.lc.thirdtradeadapter.api.output.icbcbankent.VirutalTradeDateInfo;
import com.cifpay.lc.thirdtradeadapter.api.service.icbcbankent.ICBCBankEntVirutalTradeDateQueryService;

/**
 * 
 *
 */
@Service("icbcBankEntVirutalTradeDateQueryService")
public class ICBCBankEntVirutalTradeDateQueryServiceImpl
		extends CoreBusinessServiceImplBase<VoidObject, VirutalTradeDateInfo>
		implements ICBCBankEntVirutalTradeDateQueryService {

	@Autowired
	private ICBCTradeDateManager icbcTradeDateManager;

	@Override
	protected void validate(VoidObject inputData, CoreBusinessContext context)
			throws CoreValidationRejectException {
	}

	@Override
	protected BusinessOutput<VirutalTradeDateInfo> processBusiness(VoidObject inputData,
			CoreBusinessContext context) throws CoreBusinessException {
		VirutalTradeDateInfo info = new VirutalTradeDateInfo();
		info.setRunningInTestEnvironmentMode(icbcTradeDateManager.isRunningInTestEnvironmentMode());
		info.setCurrentDatetimeForICBCTrade(icbcTradeDateManager.getCurrentDatetimeForICBCTrade());
		return BusinessOutput.success(info);
	}

}
