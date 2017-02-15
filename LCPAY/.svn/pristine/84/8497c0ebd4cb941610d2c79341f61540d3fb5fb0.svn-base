package com.cifpay.lc.std.example;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.cifpay.lc.api.BusinessOutput;
import com.cifpay.lc.api.example.ServerClockService;
import com.cifpay.lc.constant.ReturnCode;
import com.cifpay.lc.core.common.CoreBusinessContext;
import com.cifpay.lc.core.common.CoreBusinessInterceptorConfig;
import com.cifpay.lc.core.common.CoreBusinessServiceImplBase;
import com.cifpay.lc.core.exception.CoreBusinessException;
import com.cifpay.lc.core.exception.CoreValidationRejectException;
import com.cifpay.lc.std.interceptor.BusinessLoggingInterceptor;

/**
 * 样例
 * 
 * 
 *
 */
@Service("serverClockService")
@CoreBusinessInterceptorConfig({BusinessLoggingInterceptor.class})
public class ServerClockServiceImpl extends CoreBusinessServiceImplBase<String, Date> implements ServerClockService {

	// @Autowired
	// private DbTransactionHelper dbTransactionHelper;
	//
	// DbTransaction transaction = dbTransactionHelper.beginTransaction();
	//
	// try {
	// } catch (Throwable e) {
	// dbTransactionHelper.rollbackTransaction(transaction);
	// CoreBusinessExceptionHelper.throwAsCoreBusinessException(e);
	// } finally {
	// dbTransactionHelper.commitTransactionInFinallyBlock(transaction);
	// }
	

	@Override
	protected void validate(String inputBean, CoreBusinessContext context) throws CoreValidationRejectException {
		
	}
	
	@Override
	protected BusinessOutput<Date> processBusiness(String input, CoreBusinessContext context)
			throws CoreBusinessException {
		BusinessOutput<Date> output = new BusinessOutput<Date>();
		output.setData(new Date());
		
		output.setReturnCode(ReturnCode.GENERAL_SUCCESS);
		output.setReturnMsg("操作成功");
		return output;
	}

}
