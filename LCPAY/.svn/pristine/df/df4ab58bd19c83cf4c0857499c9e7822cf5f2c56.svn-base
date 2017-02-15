package com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cifpay.lc.api.BusinessOutput;
import com.cifpay.lc.constant.ReturnCode;
import com.cifpay.lc.core.common.CoreBusinessContext;
import com.cifpay.lc.core.common.CoreBusinessServiceImplBase;
import com.cifpay.lc.core.exception.CoreBusinessException;
import com.cifpay.lc.core.exception.CoreValidationRejectException;
import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.helper.ICBCTradeDateManager;
import com.cifpay.lc.thirdtradeadapter.api.service.icbcbankent.ICBCBankEntVirutalTradeDateUpdateService;

@Service("icbcBankEntVirutalTradeDateUpdateService")
public class ICBCBankEntVirutalTradeDateUpdateServiceImpl extends CoreBusinessServiceImplBase<Date, Date>
		implements ICBCBankEntVirutalTradeDateUpdateService {

	@Autowired
	private ICBCTradeDateManager icbcTradeDateManager;

	@Override
	protected void validate(Date date, CoreBusinessContext context)
			throws CoreValidationRejectException {
		if (null == date) {
			throw new CoreValidationRejectException(ReturnCode.CORE_COMMON_VALIDATION_REJECTED, "缺少输入数据");
		}
	}

	@Override
	protected BusinessOutput<Date> processBusiness(Date tradeDateForTest, CoreBusinessContext context)
			throws CoreBusinessException {

		Date currentTradeDate = null;

		icbcTradeDateManager.updateReferenceTradeDateTimeForTestMode(tradeDateForTest);
		currentTradeDate = icbcTradeDateManager.getCurrentDatetimeForICBCTrade();

		BusinessOutput<Date> output = new BusinessOutput<Date>(currentTradeDate);
		output.setReturnCode(ReturnCode.GENERAL_SUCCESS);
		output.setReturnMsg("更新成功");
		return output;
	}

}
