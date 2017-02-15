package com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.service;

import java.util.Date;
import java.util.List;

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
import com.cifpay.lc.core.db.dao.ThirdICBCBankentPayentDao;
import com.cifpay.lc.core.db.pojo.ThirdICBCBankentPayent;
import com.cifpay.lc.core.exception.CoreBusinessException;
import com.cifpay.lc.core.exception.CoreException;
import com.cifpay.lc.core.exception.CoreValidationRejectException;
import com.cifpay.lc.core.uid.ICBCBankEntQueryFSeqNoWorker;
import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.helper.EnterprisePayDefinition;
import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.helper.EnterprisePayDefinitionHelper;
import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.helper.ICBCBankEntPayOutResultStatusUtils;
import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.helper.ICBCTradeDateManager;
import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.helper.NCMessageSender;
import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.transcode.QPAYENT;
import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.xmlbean.qpayent.QPayentXmlRequest;
import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.xmlbean.qpayent.QPayentXmlRequestRecordDetail;
import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.xmlbean.qpayent.QPayentXmlResponse;
import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.xmlbean.qpayent.QPayentXmlResponseRecordDetail;
import com.cifpay.lc.thirdtradeadapter.api.constant.ICBCBankEntCurrencyType;
import com.cifpay.lc.thirdtradeadapter.api.constant.ICBCBankTransactionResultStatus;
import com.cifpay.lc.thirdtradeadapter.api.input.icbcbankent.PayOutEnquiry;
import com.cifpay.lc.thirdtradeadapter.api.output.icbcbankent.PayOutEnquiryResult;
import com.cifpay.lc.thirdtradeadapter.api.service.icbcbankent.ICBCBankEntPayOutEnquiryService;
import com.cifpay.lc.util.DateUtil;

/**
 * 
 *
 */
@Service("icbcBankEntPayOutEnquiryService")
public class ICBCBankEntPayOutEnquiryServiceImpl extends CoreBusinessServiceImplBase<PayOutEnquiry, PayOutEnquiryResult>
		implements ICBCBankEntPayOutEnquiryService {
	private final Logger logger = LoggerFactory.getLogger(ICBCBankEntPayOutEnquiryServiceImpl.class);
	private final boolean isLoggerDebugEnabled = logger.isDebugEnabled();

	@Autowired
	private ICBCBankEntQueryFSeqNoWorker qryFSeqNoWorker;

	@Autowired
	private EnterprisePayDefinitionHelper payDefinitionHelper;

	@Autowired
	private ICBCTradeDateManager icbcTradeDateManager;

	@Autowired
	private NCMessageSender ncMessageSender;

	@Autowired
	private ThirdICBCBankentPayentDao payentDao;

	@Override
	protected void validate(PayOutEnquiry enquiry, CoreBusinessContext context)
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
		if (!StringUtils.hasText(enquiry.getOriginalInstructionNo())) {
			throw new CoreValidationRejectException(ReturnCode.CORE_COMMON_VALIDATION_REJECTED,
					"OriginalInstructionNo不允许为空");
		}
		if (!StringUtils.hasText(enquiry.getOriginalInstructionDetailNo())) {
			throw new CoreValidationRejectException(ReturnCode.CORE_COMMON_VALIDATION_REJECTED,
					"OriginalInstructionDetailNo不允许为空");
		}

		EnterprisePayDefinition payDefinition = null;
		try {
			payDefinition = payDefinitionHelper.findEnterprisePayDefinition(enquiry.getCallerSystemId(),
					enquiry.getPayerEnterpriseCode());
			context.setAttribute("payDefinition", payDefinition);
		} catch (IllegalArgumentException e) {
			throw new CoreValidationRejectException(ReturnCode.CORE_COMMON_VALIDATION_REJECTED, "非法请求，请检查授权是否合法");
		}

		// Obtain the original PAYENT record by the given
		// OriginalInstructionNo from DB
		ThirdICBCBankentPayent payent = payentDao.selectDetailByInstructionDetailNo(enquiry.getCallerSystemId(),
				enquiry.getOriginalInstructionNo(), enquiry.getOriginalInstructionDetailNo());
		if (null == payent) {
			throw new CoreValidationRejectException(ReturnCode.CORE_COMMON_DB_RECORD_NOT_FOUND, "交易记录不存在");
		} else {
			context.setAttribute("payent", payent);
		}

	}

	@Override
	protected BusinessOutput<PayOutEnquiryResult> processBusiness(PayOutEnquiry enquiry,
			CoreBusinessContext context) throws CoreBusinessException {
		final BusinessOutput<PayOutEnquiryResult> output = new BusinessOutput<PayOutEnquiryResult>();
		PayOutEnquiryResult enquiryResult = null;

		EnterprisePayDefinition payDefinition = (EnterprisePayDefinition) context.getAttribute("payDefinition");
		ThirdICBCBankentPayent payent = (ThirdICBCBankentPayent) context.getAttribute("payent");
		String qryfSeqno = payent.getBatchSeqno();
		String qryiSeqno = payent.getDetailSeqno();

		Date currentDatetimeForTrade = icbcTradeDateManager.getCurrentDatetimeForICBCTrade();
		QPayentXmlRequest qpayentXmlRequest = prepareNCRequest(qryfSeqno, qryiSeqno, currentDatetimeForTrade,
				payDefinition);

		try {
			if (isLoggerDebugEnabled) {
				logger.debug("~~~Start to call NCMessageSender ...");
			}
			QPayentXmlResponse payentXmlResponse = ncMessageSender.send(QPAYENT.getInstance(), qpayentXmlRequest);
			if (isLoggerDebugEnabled) {
				logger.debug("~~~End to call NCMessageSender.");
			}
			enquiryResult = parseNCSenderResult(payentXmlResponse, payent);
			output.setData(enquiryResult);
			output.setReturnCode(ReturnCode.GENERAL_SUCCESS);
			output.setReturnMsg("查询成功，请查看查询结果中的交易状态");
		} catch (CoreException e) {
			logger.warn("查询请求处理失败，故交易结果状态仍未能明确，错误信息：{}", e.getMessage(), e);
			output.setReturnCode(e.getReturnCode());
			output.setReturnMsg("查询失败，" + e.getOriginalMessage());
		} catch (Exception e) {
			logger.warn("NC交易查询发生未知错误，错误信息：{}", e.getMessage(), e);
			CoreBusinessException coreEx = CoreBusinessExceptionHelper.convertToCoreBusinessException(e);
			output.setReturnCode(coreEx.getReturnCode());
			if (ReturnCode.UNKNOWN_ERROR == coreEx.getReturnCode()) {
				output.setReturnMsg("查询失败，" + coreEx.getOriginalMessage());
			} else {
				output.setReturnMsg(coreEx.getOriginalMessage());
			}
		}

		if (null != enquiryResult) {
			// update db
			try {
				updateRecordsAfterBankEnquiry(payent, enquiryResult);
			} catch (Throwable e) {
				logger.warn("调用银行交易查询接口结束，但更新我方数据库时发生未知错误，系统后续将会再次发起查询", e);
				if (output.isSuccess()) {
					// 对于此特殊情况，先返回调用端“查询失败”的保守结果
					CoreBusinessException coreEx = CoreBusinessExceptionHelper.convertToCoreBusinessException(e);
					output.setReturnCode(coreEx.getReturnCode());
					output.setReturnMsg("查询失败，" + coreEx.getOriginalMessage());
				}
			}
		}

		return output;
	}

	private void updateRecordsAfterBankEnquiry(ThirdICBCBankentPayent payent, PayOutEnquiryResult enquiryResult) {
		ThirdICBCBankentPayent updPayent = new ThirdICBCBankentPayent();
		updPayent.setBatchSeqno(payent.getBatchSeqno());
		updPayent.setDetailSeqno(payent.getDetailSeqno());
		if (null != enquiryResult.getSameCityFlag()) {
			updPayent.setSameCityFlag(enquiryResult.getSameCityFlag().booleanValue() ? "1" : "2");
		}
		updPayent.setBankRetBatchSerialNo(enquiryResult.getBankReturnedSerialNo());
		updPayent.setTxnResultStatus(enquiryResult.getBankResultStatus().getCode());
		updPayent.setBankOriDetailResultCode(enquiryResult.getOriginalDetailResultCode());
		updPayent.setBankAddiDetailReturnCode(enquiryResult.getBankAddiDetailReturnCode());
		updPayent.setBankAddiDetailReturnMsg(enquiryResult.getBankAddiDetailReturnMsg());
		payentDao.updateByPrimaryKeySelective(updPayent);
	}

	private QPayentXmlRequest prepareNCRequest(String qryfSeqno, String qryiSeqno, Date currentDatetimeForTrade,
			EnterprisePayDefinition payDefinition) {
		String fSeqNo = "Q" + String.valueOf(qryFSeqNoWorker.nextId());
		String tranDate = DateUtil.format(currentDatetimeForTrade, "yyyyMMdd");
		String tranTime = DateUtil.format(currentDatetimeForTrade, "HHmmssSSS000");

		QPayentXmlRequest qpayentXmlRequest = new QPayentXmlRequest();
		qpayentXmlRequest.setTransCode(QPAYENT.getInstance().getTransCode());
		qpayentXmlRequest.setCis(payDefinition.getIcbcCIS());
		qpayentXmlRequest.setBankCode(payDefinition.getIcbcBankCode());
		qpayentXmlRequest.setId(payDefinition.getIcbcCertId());
		qpayentXmlRequest.setTranDate(tranDate);
		qpayentXmlRequest.setTranTime(tranTime);
		qpayentXmlRequest.setfSeqno(fSeqNo);
		qpayentXmlRequest.setQryfSeqno(qryfSeqno);

		QPayentXmlRequestRecordDetail rd = new QPayentXmlRequestRecordDetail();
		rd.setiSeqno("1");
		rd.setQryiSeqno(qryiSeqno);
		qpayentXmlRequest.addRd(rd);

		return qpayentXmlRequest;
	}

	private PayOutEnquiryResult parseNCSenderResult(QPayentXmlResponse payentXmlResponse,
			ThirdICBCBankentPayent payent) {
		PayOutEnquiryResult enquiryResult = new PayOutEnquiryResult();

		enquiryResult.setOriginalInstructionNo(payent.getCallerInstructionNo());
		enquiryResult.setOriginalInstructionDetailNo(payent.getCallerInstructionDetailNo());
		enquiryResult.setAdapterReturnedBatchSeqNo(payent.getBatchSeqno());
		enquiryResult.setAdapterReturnedDetailSeqNo(payent.getDetailSeqno());
		enquiryResult.setBankReturnedSerialNo(payentXmlResponse.getQrySerialNo());
		enquiryResult.setCurrencyType(ICBCBankEntCurrencyType.parseCurrencyTypeCode(payent.getIcbcCurrencyType()));
		enquiryResult.setPayAmount(payent.getPayAmt());
		if (StringUtils.hasText(payent.getCrossBankFlag())) {
			if ("1".equals(payent.getCrossBankFlag())) {
				enquiryResult.setCrossBankFlag(Boolean.FALSE);
			} else {
				enquiryResult.setCrossBankFlag(Boolean.TRUE);
			}
		}

		List<QPayentXmlResponseRecordDetail> responseRds = payentXmlResponse.getRds();
		if (null != responseRds && responseRds.size() > 0) {
			QPayentXmlResponseRecordDetail rd = responseRds.get(0);
			if (StringUtils.hasText(rd.getIsSameCity())) {
				if ("1".equals(rd.getIsSameCity())) {
					enquiryResult.setSameCityFlag(Boolean.FALSE);
				} else {
					enquiryResult.setSameCityFlag(Boolean.TRUE);
				}
			}
			enquiryResult.setBankResultStatus(
					ICBCBankEntPayOutResultStatusUtils.mappingTransactionResultStatus(rd.getResult()));
			enquiryResult.setOriginalDetailResultCode(rd.getResult());
			enquiryResult.setBankAddiDetailReturnCode(rd.getInstrRetCode());
			enquiryResult.setBankAddiDetailReturnMsg(rd.getInstrRetMsg());
		} else if ("B0116".equals(payentXmlResponse.getRetCode())
				|| "没有符合条件的记录".equals(payentXmlResponse.getRetMsg())) {
			// <RetCode>B0116</RetCode>
			// <RetMsg>没有符合条件的记录</RetMsg>
			enquiryResult.setBankResultStatus(ICBCBankTransactionResultStatus.BANK_FAILED);
			enquiryResult.setBankAddiDetailReturnCode(payentXmlResponse.getRetCode());
			enquiryResult.setBankAddiDetailReturnMsg(payentXmlResponse.getRetMsg());
		} else {
			enquiryResult.setBankResultStatus(ICBCBankTransactionResultStatus.RESULT_UNCERTAIN);
		}

		return enquiryResult;
	}

}
