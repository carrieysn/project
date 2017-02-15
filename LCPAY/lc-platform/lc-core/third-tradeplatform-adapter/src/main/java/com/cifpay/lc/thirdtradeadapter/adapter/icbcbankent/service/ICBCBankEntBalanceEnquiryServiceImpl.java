package com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cifpay.lc.api.BusinessOutput;
import com.cifpay.lc.constant.ReturnCode;
import com.cifpay.lc.core.common.CoreBusinessContext;
import com.cifpay.lc.core.common.CoreBusinessExceptionHelper;
import com.cifpay.lc.core.common.CoreBusinessServiceImplBase;
import com.cifpay.lc.core.exception.CoreBusinessException;
import com.cifpay.lc.core.exception.CoreException;
import com.cifpay.lc.core.exception.CoreValidationRejectException;
import com.cifpay.lc.core.uid.ICBCBankEntQueryFSeqNoWorker;
import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.helper.EnterprisePayDefinition;
import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.helper.EnterprisePayDefinitionHelper;
import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.helper.ICBCTradeDateManager;
import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.helper.NCMessageSender;
import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.transcode.QACCBAL;
import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.xmlbean.qaccbal.QaccbalXmlRequest;
import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.xmlbean.qaccbal.QaccbalXmlRequestRecordDetail;
import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.xmlbean.qaccbal.QaccbalXmlResponse;
import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.xmlbean.qaccbal.QaccbalXmlResponseRecordDetail;
import com.cifpay.lc.thirdtradeadapter.api.constant.ICBCBankEntCurrencyType;
import com.cifpay.lc.thirdtradeadapter.api.input.icbcbankent.BalanceEnquiry;
import com.cifpay.lc.thirdtradeadapter.api.output.icbcbankent.BalanceEnquiryResult;
import com.cifpay.lc.thirdtradeadapter.api.service.icbcbankent.ICBCBankEntBalanceEnquiryService;
import com.cifpay.lc.util.DateUtil;

/**
 * 
 *
 */
@Service("icbcBankEntBalanceEnquiryService")
public class ICBCBankEntBalanceEnquiryServiceImpl extends
		CoreBusinessServiceImplBase<BalanceEnquiry, BalanceEnquiryResult> implements ICBCBankEntBalanceEnquiryService {
	private final Logger logger = LoggerFactory.getLogger(ICBCBankEntBalanceEnquiryServiceImpl.class);
	private final boolean isLoggerDebugEnabled = logger.isDebugEnabled();

	@Autowired
	private ICBCBankEntQueryFSeqNoWorker qryFSeqNoWorker;

	@Autowired
	private EnterprisePayDefinitionHelper payDefinitionHelper;

	@Autowired
	private ICBCTradeDateManager icbcTradeDateManager;

	@Autowired
	private NCMessageSender ncMessageSender;

	@Override
	protected void validate(BalanceEnquiry enquiry, CoreBusinessContext context)
			throws CoreValidationRejectException {
		if (null == enquiry) {
			throw new CoreValidationRejectException(ReturnCode.CORE_COMMON_MISSING_BUSINESS_INPUT, "非法的服务调用请求");
		}

		if (!StringUtils.hasText(enquiry.getCallerSystemId())) {
			throw new CoreValidationRejectException(ReturnCode.CORE_COMMON_VALIDATION_REJECTED, "CallerSystemId不允许为空");
		}
		if (!StringUtils.hasText(enquiry.getPayerEnterpriseCode())) {
			throw new CoreValidationRejectException(ReturnCode.CORE_COMMON_VALIDATION_REJECTED,
					"PayerEnterpriseCode不允许为空");
		}

		EnterprisePayDefinition payDefinition = null;
		try {
			payDefinition = payDefinitionHelper.findEnterprisePayDefinition(enquiry.getCallerSystemId(),
					enquiry.getPayerEnterpriseCode());
			context.setAttribute("payDefinition", payDefinition);
		} catch (IllegalArgumentException e) {
			throw new CoreValidationRejectException(ReturnCode.CORE_COMMON_VALIDATION_REJECTED, "非法请求，请检查授权是否合法");
		}
	}

	@Override
	protected BusinessOutput<BalanceEnquiryResult> processBusiness(BalanceEnquiry enquiry,
			CoreBusinessContext context) throws CoreBusinessException {
		final BusinessOutput<BalanceEnquiryResult> output = new BusinessOutput<BalanceEnquiryResult>();
		BalanceEnquiryResult enquiryResult = null;

		EnterprisePayDefinition payDefinition = (EnterprisePayDefinition) context.getAttribute("payDefinition");

		Date currentDatetimeForTrade = icbcTradeDateManager.getCurrentDatetimeForICBCTrade();
		QaccbalXmlRequest qpayentXmlRequest = prepareNCRequest(currentDatetimeForTrade, payDefinition);

		try {
			if (isLoggerDebugEnabled) {
				logger.debug("~~~Start to call NCMessageSender ...");
			}
			QaccbalXmlResponse payentXmlResponse = ncMessageSender.send(QACCBAL.getInstance(), qpayentXmlRequest);
			if (isLoggerDebugEnabled) {
				logger.debug("~~~End to call NCMessageSender.");
			}
			if (null != payentXmlResponse.getRds() && payentXmlResponse.getRds().size() > 0) {
				enquiryResult = parseNCSenderResult(payentXmlResponse);
				output.setData(enquiryResult);
				output.setReturnCode(ReturnCode.GENERAL_SUCCESS);
				output.setReturnMsg("查询成功");
			} else {
				enquiryResult = parseNCSenderResult(payentXmlResponse);
				output.setData(enquiryResult);
				output.setReturnCode(ReturnCode.CORE_3RD_ICBC_BANKENT_BALANCE_ENQUIRY_FAILED);
				output.setReturnMsg("查询失败");
			}

		} catch (CoreException e) {
			logger.warn("查询请求处理失败，错误信息：{}", e.getMessage(), e);
			output.setReturnCode(e.getReturnCode());
			output.setReturnMsg("查询失败，" + e.getOriginalMessage());
		} catch (Exception e) {
			logger.warn("NC余额查询发生未知错误，错误信息：{}", e.getMessage(), e);
			CoreBusinessException coreEx = CoreBusinessExceptionHelper.convertToCoreBusinessException(e);
			output.setReturnCode(coreEx.getReturnCode());
			if (ReturnCode.UNKNOWN_ERROR == coreEx.getReturnCode()) {
				output.setReturnMsg("查询失败，" + coreEx.getOriginalMessage());
			} else {
				output.setReturnMsg(coreEx.getOriginalMessage());
			}
		}

		return output;
	}

	private QaccbalXmlRequest prepareNCRequest(Date currentDatetimeForTrade, EnterprisePayDefinition payDefinition) {
		String fSeqNo = "Q" + String.valueOf(qryFSeqNoWorker.nextId());
		String tranDate = DateUtil.format(currentDatetimeForTrade, "yyyyMMdd");
		String tranTime = DateUtil.format(currentDatetimeForTrade, "HHmmssSSS000");

		QaccbalXmlRequest qaccbalXmlRequest = new QaccbalXmlRequest();
		qaccbalXmlRequest.setTransCode(QACCBAL.getInstance().getTransCode());
		qaccbalXmlRequest.setCis(payDefinition.getIcbcCIS());
		qaccbalXmlRequest.setBankCode(payDefinition.getIcbcBankCode());
		qaccbalXmlRequest.setId(payDefinition.getIcbcCertId());
		qaccbalXmlRequest.setTranDate(tranDate);
		qaccbalXmlRequest.setTranTime(tranTime);
		qaccbalXmlRequest.setfSeqno(fSeqNo);
		qaccbalXmlRequest.setTotalNum("1");

		QaccbalXmlRequestRecordDetail rd = new QaccbalXmlRequestRecordDetail();
		rd.setiSeqno("1");
		rd.setAccNo(payDefinition.getIcbcPayAccNo());
		qaccbalXmlRequest.addRd(rd);

		return qaccbalXmlRequest;
	}

	private BalanceEnquiryResult parseNCSenderResult(QaccbalXmlResponse qaccbalXmlResponse) {
		BalanceEnquiryResult enquiryResult = new BalanceEnquiryResult();
		QaccbalXmlResponseRecordDetail rd = qaccbalXmlResponse.getRds().get(0);

		enquiryResult.setAccNo(rd.getAccNo());
		if (StringUtils.hasText(rd.getCurrType())) {
			enquiryResult.setCurrencyType(ICBCBankEntCurrencyType.parseCurrencyTypeCode(rd.getCurrType()));
		} else {
			enquiryResult.setCurrencyType(ICBCBankEntCurrencyType.CNY);
		}
		enquiryResult.setAcctProperty(rd.getAcctProperty());
		enquiryResult.setAccBalance(Long.parseLong(rd.getAccBalance()));
		enquiryResult.setBalance(Long.parseLong(rd.getBalance()));
		enquiryResult.setUsableBalance(Long.parseLong(rd.getUsableBalance()));
		enquiryResult.setFrzAmt(Long.parseLong(rd.getFrzAmt()));

		return enquiryResult;
	}

}
