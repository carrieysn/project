package com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.cifpay.lc.core.common.DbTransaction;
import com.cifpay.lc.core.common.DbTransactionHelper;
import com.cifpay.lc.core.db.dao.ThirdICBCBankentPayentDao;
import com.cifpay.lc.core.db.pojo.ThirdICBCBankentPayent;
import com.cifpay.lc.core.exception.CoreException;
import com.cifpay.lc.core.exception.CoreValidationRejectException;
import com.cifpay.lc.core.uid.ICBCBankEntTradeFSeqNoWorker;
import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.exception.NCResultFailedException;
import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.exception.NCResultUncertainException;
import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.exception.NCWaitResponseTimeoutException;
import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.helper.EnterprisePayDefinition;
import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.helper.EnterprisePayDefinitionHelper;
import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.helper.ICBCBankEntPayOutResultStatusUtils;
import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.helper.ICBCTradeDateManager;
import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.helper.LcCurrencyICBCCurrencyMappingUtils;
import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.helper.NCMessageSender;
import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.transcode.PAYENT;
import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.xmlbean.payent.PayentXmlRequest;
import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.xmlbean.payent.PayentXmlRequestRecordDetail;
import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.xmlbean.payent.PayentXmlResponse;
import com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.xmlbean.payent.PayentXmlResponseRecordDetail;
import com.cifpay.lc.thirdtradeadapter.api.constant.ICBCAccountPropType;
import com.cifpay.lc.thirdtradeadapter.api.constant.ICBCBankEntCurrencyType;
import com.cifpay.lc.thirdtradeadapter.api.constant.ICBCBankTransactionResultStatus;
import com.cifpay.lc.thirdtradeadapter.api.input.icbcbankent.PayOutInstruction;
import com.cifpay.lc.thirdtradeadapter.api.input.icbcbankent.PayOutInstructionDetail;
import com.cifpay.lc.thirdtradeadapter.api.output.icbcbankent.PayOutResult;
import com.cifpay.lc.thirdtradeadapter.api.output.icbcbankent.PayOutResultDetail;
import com.cifpay.lc.thirdtradeadapter.api.service.icbcbankent.ICBCBankEntPayOutSubmitService;
import com.cifpay.lc.util.DateUtil;
import com.cifpay.lc.util.StringUtil;

/**
 * 
 *
 */
@Service("icbcBankEntPayOutSubmitService")
public class ICBCBankEntPayOutSubmitServiceImpl extends CoreBusinessServiceImplBase<PayOutInstruction, PayOutResult>
		implements ICBCBankEntPayOutSubmitService {

	private final Logger logger = LoggerFactory.getLogger(ICBCBankEntPayOutSubmitServiceImpl.class);
	private final boolean isLoggerDebugEnabled = logger.isDebugEnabled();

	private static final String ICBC_INTERFACE_RETURN_CODE_SUCCESS = "0";

	@Autowired
	private ICBCBankEntTradeFSeqNoWorker fSeqNoWorker;

	@Autowired
	private EnterprisePayDefinitionHelper payDefinitionHelper;

	@Autowired
	private ICBCTradeDateManager icbcTradeDateManager;

	@Autowired
	private NCMessageSender ncMessageSender;

	@Autowired
	private DbTransactionHelper dbTransactionHelper;

	@Autowired
	private ThirdICBCBankentPayentDao payentDao;

	@Override
	protected void validate(PayOutInstruction payOutInstruction, CoreBusinessContext context)
			throws CoreValidationRejectException {
		if (null == payOutInstruction) {
			throw new CoreValidationRejectException(ReturnCode.CORE_COMMON_MISSING_BUSINESS_INPUT, "非法的服务调用请求");
		}

		if (!StringUtils.hasText(payOutInstruction.getCallerSystemId())) {
			throw new CoreValidationRejectException(ReturnCode.CORE_COMMON_VALIDATION_REJECTED, "CallerSystemId不允许为空");
		}
		if (!StringUtils.hasText(payOutInstruction.getPayerEnterpriseCode())) {
			throw new CoreValidationRejectException(ReturnCode.CORE_COMMON_VALIDATION_REJECTED,
					"PayerEnterpriseCode不允许为空");
		}
		if (!StringUtils.hasText(payOutInstruction.getInstructionNo())) {
			throw new CoreValidationRejectException(ReturnCode.CORE_COMMON_VALIDATION_REJECTED, "InstructionNo不允许为空");
		}
		if (null == payOutInstruction.getTotalAmount() || payOutInstruction.getTotalAmount() <= 0) {
			throw new CoreValidationRejectException(ReturnCode.CORE_COMMON_VALIDATION_REJECTED, "交易指令总金额不允许为空，且必须大于零");
		}
		if (null == payOutInstruction.getInstructionDetails() || payOutInstruction.getInstructionDetails().isEmpty()) {
			throw new CoreValidationRejectException(ReturnCode.CORE_COMMON_VALIDATION_REJECTED,
					"交易指令明细不允许为空，指令中必须至少包含一条指令明细");
		}

		EnterprisePayDefinition payDefinition = null;
		try {
			payDefinition = payDefinitionHelper.findEnterprisePayDefinition(payOutInstruction.getCallerSystemId(),
					payOutInstruction.getPayerEnterpriseCode());
			context.setAttribute("payDefinition", payDefinition);
		} catch (IllegalArgumentException e) {
			throw new CoreValidationRejectException(ReturnCode.CORE_COMMON_VALIDATION_REJECTED, "非法请求，请检查授权是否合法");
		}

		Long detailAmountSum = 0L;
		for (PayOutInstructionDetail inDetail : payOutInstruction.getInstructionDetails()) {
			if (!StringUtils.hasText(inDetail.getInstructionDetailNo())) {
				throw new CoreValidationRejectException(ReturnCode.CORE_COMMON_VALIDATION_REJECTED,
						"交易指令明细中的InstructionDetailNo不允许为空");
			}
			if (null == inDetail.getRecvAccountPropType()) {
				throw new CoreValidationRejectException(ReturnCode.CORE_COMMON_VALIDATION_REJECTED,
						"交易指令明细中的RecvAccountType不允许为NULL");
			}
			if (!StringUtils.hasText(inDetail.getRecvBankCode())) {
				throw new CoreValidationRejectException(ReturnCode.CORE_COMMON_VALIDATION_REJECTED,
						"交易指令明细中的RecvBankCode不允许为空");
			}
			if (!StringUtils.hasText(inDetail.getRecvBankName())) {
				throw new CoreValidationRejectException(ReturnCode.CORE_COMMON_VALIDATION_REJECTED,
						"交易指令明细中的RecvBankName不允许为空");
			}
			if (!StringUtils.hasText(inDetail.getRecvBankAccountNo())) {
				throw new CoreValidationRejectException(ReturnCode.CORE_COMMON_VALIDATION_REJECTED,
						"交易指令明细中的RecvBankAccountNo不允许为空");
			}

			// cross-bank
			if (!payDefinition.getPayerBankCode().equals(inDetail.getRecvBankCode())) {
				if (!StringUtils.hasText(inDetail.getRecvBankAccountName())) {
					throw new CoreValidationRejectException(ReturnCode.CORE_COMMON_VALIDATION_REJECTED,
							"跨行交易指令明细中的RecvBankAccountName不允许为空");
				}
				if (!StringUtils.hasText(inDetail.getRecvBankCityName())) {
					throw new CoreValidationRejectException(ReturnCode.CORE_COMMON_VALIDATION_REJECTED,
							"跨行交易指令明细中的RecvBankCityName不允许为空");
				}
			}

			if (null == inDetail.getCurrencyType()) {
				throw new CoreValidationRejectException(ReturnCode.CORE_COMMON_VALIDATION_REJECTED,
						"交易指令明细中的CurrencyType不允许为空");
			}
			if (null == inDetail.getPayAmount() || inDetail.getPayAmount() <= 0) {
				throw new CoreValidationRejectException(ReturnCode.CORE_COMMON_VALIDATION_REJECTED,
						"交易指令明细中的交易金额不允许为空，且必须大于零");
			}
			if (!StringUtils.hasText(inDetail.getFundUseDesc())) {
				throw new CoreValidationRejectException(ReturnCode.CORE_COMMON_VALIDATION_REJECTED,
						"交易指令明细中的款项用途不允许为空");
			}

			detailAmountSum += inDetail.getPayAmount();
		}

		if (payOutInstruction.getTotalAmount().longValue() != detailAmountSum.longValue()) {
			throw new CoreValidationRejectException(ReturnCode.CORE_COMMON_VALIDATION_REJECTED,
					"交易指令总金额和交易指令明细中的交易金额总和不相符");
		}
	}

	@Override
	public BusinessOutput<PayOutResult> processBusiness(PayOutInstruction instruction,
			CoreBusinessContext context) {
		EnterprisePayDefinition payDefinition = (EnterprisePayDefinition) context.getAttribute("payDefinition");
		Date currentDatetimeForTrade = icbcTradeDateManager.getCurrentDatetimeForICBCTrade();

		// insert DB
		List<ThirdICBCBankentPayent> payentRecords = saveInstruction(payDefinition, currentDatetimeForTrade,
				instruction);

		PayentXmlRequest payentXmlRequest = prepareNCRequest(currentDatetimeForTrade, payentRecords);
		CoreException tradingException = null;

		try {
			if (isLoggerDebugEnabled) {
				logger.debug("~~~Start to call NCMessageSender ...");
			}
			PayentXmlResponse payentXmlResponse = ncMessageSender.send(PAYENT.getInstance(), payentXmlRequest);
			if (isLoggerDebugEnabled) {
				logger.debug("~~~End to call NCMessageSender.");
			}

			tradingException = fillPayentRecordsWithNCResult(payentXmlResponse, payentRecords);

		} catch (CoreException e) {
			if (e instanceof CoreValidationRejectException) {
				logger.debug("交易请求校验未通过，{}", e.getMessage(), e);
			} else if (e instanceof NCResultUncertainException) {
				if (e instanceof NCWaitResponseTimeoutException) {
					logger.warn("等待NC响应交易结果超时，系统将会自动通过异步同步机制确认该笔交易的处理结果，CallerSystemId: {}, InstructionNo: {}.",
							instruction.getCallerSystemId(), instruction.getInstructionNo());
				} else {
					logger.warn(
							"因系统异常未能确定当前交易的结果，系统将会自动通过异步同步机制确认该笔交易的处理结果，CallerSystemId: {}, InstructionNo: {}.错误信息：{}{}",
							instruction.getCallerSystemId(), instruction.getInstructionNo(), e.getMessage(),
							StringUtil.LINE_SEPARATOR, e);
				}
			} else {
				logger.error("NC交易处理失败，错误信息：{}", e.getMessage(), e);
			}
			fillPayentRecordsWhenException(payentRecords, e);
			tradingException = e;
		} catch (Exception e) {
			logger.error("NC交易发生未知错误，错误信息：{}", e.getMessage(), e);
			fillPayentRecordsWhenException(payentRecords, e);
			tradingException = CoreBusinessExceptionHelper.convertToCoreBusinessException(e);
		}

		// update DB after sending
		if (isLoggerDebugEnabled) {
			logger.debug("工行银企支付交易处理完毕，准备更新数据库，batchSeqNo: {} ...", payentXmlRequest.getfSeqno());
		}
		updatePayentRecords(payentRecords);

		// construct the output data to client
		ThirdICBCBankentPayent firstPayent = payentRecords.get(0);

		BusinessOutput<PayOutResult> output = new BusinessOutput<PayOutResult>();
		PayOutResult payoutResult = new PayOutResult();
		payoutResult.setInstructionNo(instruction.getInstructionNo());
		payoutResult.setTotalAmount(instruction.getTotalAmount());
		payoutResult.setAdapterReturnedBatchSeqNo(firstPayent.getBatchSeqno());
		payoutResult.setBankReturnedSerialNo(firstPayent.getBankRetBatchSerialNo());
		output.setData(payoutResult);

		int payentRecordsCount = payentRecords.size();
		int successCount = 0;
		int failCount = 0;

		for (ThirdICBCBankentPayent payentRecord : payentRecords) {
			PayOutResultDetail rDetail = new PayOutResultDetail();
			rDetail.setInstructionDetailNo(payentRecord.getCallerInstructionDetailNo());
			rDetail.setCurrencyType(ICBCBankEntCurrencyType.parseCurrencyTypeCode(payentRecord.getIcbcCurrencyType()));
			rDetail.setPayAmount(payentRecord.getPayAmt());
			rDetail.setCrossBankFlag("2".equals(payentRecord.getCrossBankFlag()) ? Boolean.TRUE : Boolean.FALSE);
			if (StringUtils.hasText(payentRecord.getSameCityFlag())) {
				rDetail.setSameCityFlag("1".equals(payentRecord.getSameCityFlag()) ? Boolean.TRUE : Boolean.FALSE);
			}
			rDetail.setCorporatePersonalFlag(
					ICBCAccountPropType.parseAccountPropType(payentRecord.getCorporatePersonalFlag()));
			rDetail.setAdapterReturnedDetailSeqNo(payentRecord.getDetailSeqno());
			rDetail.setBankReturnedDetailOrderNo(payentRecord.getBankRetDetailOrderNo());
			rDetail.setBankResultStatus(
					ICBCBankTransactionResultStatus.parseTxnStatus(payentRecord.getTxnResultStatus()));
			rDetail.setOriginalDetailResultCode(payentRecord.getBankOriDetailResultCode());
			rDetail.setBankAdditionReturnCode(payentRecord.getBankAddiDetailReturnCode());
			rDetail.setBankAdditionReturnMsg(payentRecord.getBankAddiDetailReturnMsg());
			payoutResult.addResultDetail(rDetail);

			switch (rDetail.getBankResultStatus()) {
			case BANK_SUCCESS:
				successCount++;
				break;
			case BANK_FAILED:
				failCount++;
				break;
			default:
			}
		}

		if (successCount == payentRecordsCount) {
			output.setReturnCode(ReturnCode.GENERAL_SUCCESS);
			if (payentRecordsCount > 1) {
				output.setReturnMsg("批次全部交易成功");
			} else {
				output.setReturnMsg("交易成功");
			}
		} else if (failCount == payentRecordsCount) {
			if (null != tradingException) {
				output.setReturnCode(tradingException.getReturnCode());
				output.setReturnMsg(tradingException.getOriginalMessage());
			} else {
				output.setReturnCode(ReturnCode.CORE_3RD_ICBC_BANKENT_PAYOUT_RESULT_FAILED);
				output.setReturnMsg("交易失败");
			}
		} else if (successCount > 0) {
			output.setReturnCode(ReturnCode.CORE_3RD_ICBC_BANKENT_PAYOUT_RESULT_PARTIAL_SUCCESS);
			output.setReturnMsg("批次部分交易成功其余交易失败或银行处理中");
		} else {
			output.setReturnCode(ReturnCode.CORE_3RD_ICBC_BANKENT_PAYOUT_RESULT_BANK_PROCESSING);
			output.setReturnMsg("银行处理中但交易结果尚未确定");
		}

		logger.info("工行银企支付交易处理完毕，BatchSeqNo: {}, TotalAmt: {}, ReturnCode: {}, ReturnMsg: {}.",
				payentXmlRequest.getfSeqno(), payentXmlRequest.getTotalAmt(), output.getReturnCode(),
				output.getReturnMsg());

		return output;
	}

	private PayentXmlRequest prepareNCRequest(Date currentDatetimeForTrade,
			List<ThirdICBCBankentPayent> payentRecords) {
		ThirdICBCBankentPayent firstEle = payentRecords.get(0);

		String fSeqNo = firstEle.getBatchSeqno();
		String tranDate = firstEle.getTranDate();
		String tranTime = firstEle.getTranTime();
		String signTime = DateUtil.format(currentDatetimeForTrade, "yyyyMMddHHmmssSSS");

		PayentXmlRequest payentXmlRequest = new PayentXmlRequest();
		payentXmlRequest.setTransCode(PAYENT.getInstance().getTransCode());
		payentXmlRequest.setCis(firstEle.getIcbcCis());
		payentXmlRequest.setBankCode(firstEle.getIcbcBankCode());
		payentXmlRequest.setId(firstEle.getIcbcCertId());
		payentXmlRequest.setTranDate(tranDate);
		payentXmlRequest.setTranTime(tranTime);
		payentXmlRequest.setfSeqno(fSeqNo);
		payentXmlRequest.setOnlBatF("1");
		payentXmlRequest.setSettleMode("0");
		payentXmlRequest.setTotalNum(String.valueOf(payentRecords.size()));
		payentXmlRequest.setSignTime(signTime);

		long totalAmount = 0L;
		List<PayentXmlRequestRecordDetail> rds = new ArrayList<PayentXmlRequestRecordDetail>();
		for (int i = 0, size = payentRecords.size(); i < size; i++) {
			ThirdICBCBankentPayent payentRec = payentRecords.get(i);
			PayentXmlRequestRecordDetail rd = new PayentXmlRequestRecordDetail();
			rd.setiSeqno(payentRec.getDetailSeqno());
			rd.setPayType(payentRec.getIcbcPayType());
			rd.setPayAccNo(payentRec.getPayAccountNo());
			rd.setRecAccNo(payentRec.getRecvAccountNo());
			rd.setRecAccNameCN(payentRec.getRecvAccountName());
			rd.setSysIOFlg(payentRec.getCrossBankFlag());
			rd.setIsSameCity(payentRec.getSameCityFlag());
			rd.setProp(payentRec.getCorporatePersonalFlag());
			rd.setRecCityName(payentRec.getRecvBankCityName());
			rd.setRecBankName(payentRec.getRecvBankName());
			rd.setCurrType(payentRec.getIcbcCurrencyType());
			rd.setPayAmt(payentRec.getPayAmt().toString());
			rd.setUseCN(payentRec.getFundUseDesc());
			rd.setSummary(payentRec.getSummary());

			rds.add(rd);

			totalAmount += payentRec.getPayAmt();
		}
		payentXmlRequest.setRds(rds);
		payentXmlRequest.setTotalAmt(String.valueOf(totalAmount));

		return payentXmlRequest;
	}

	private List<ThirdICBCBankentPayent> saveInstruction(EnterprisePayDefinition payDefinition,
			Date currentDatetimeForTrade, PayOutInstruction instruction) {
		List<ThirdICBCBankentPayent> payentRecords = new ArrayList<ThirdICBCBankentPayent>();

		String batchSeqno = String.valueOf(fSeqNoWorker.nextId());
		String tranDate = DateUtil.format(currentDatetimeForTrade, "yyyyMMdd");
		String tranTime = DateUtil.format(currentDatetimeForTrade, "HHmmssSSS000");

		for (int i = 0, size = instruction.getInstructionDetails().size(); i < size; i++) {
			PayOutInstructionDetail inDetail = instruction.getInstructionDetails().get(i);
			ThirdICBCBankentPayent payentRecord = new ThirdICBCBankentPayent();
			payentRecord.setBatchSeqno(batchSeqno);
			payentRecord.setDetailSeqno(String.valueOf(i + 1));
			payentRecord.setCallerCode(instruction.getCallerSystemId());
			payentRecord.setCallerInstructionNo(instruction.getInstructionNo());
			payentRecord.setCallerInstructionDetailNo(inDetail.getInstructionDetailNo());
			payentRecord.setPayEnterpriseCode(instruction.getPayerEnterpriseCode());
			payentRecord.setTranDate(tranDate);
			payentRecord.setTranTime(tranTime);
			payentRecord.setCurrencyCode(LcCurrencyICBCCurrencyMappingUtils
					.mappingFromICBCBankEntCurrencyType(inDetail.getCurrencyType()).getCode());
			payentRecord.setIcbcCurrencyType(inDetail.getCurrencyType().getCode());
			payentRecord.setPayAmt(inDetail.getPayAmount());
			payentRecord.setIcbcCis(payDefinition.getIcbcCIS());
			payentRecord.setIcbcBankCode(payDefinition.getIcbcBankCode());
			payentRecord.setIcbcCertId(payDefinition.getIcbcCertId());
			payentRecord.setIcbcPayType(payDefinition.getIcbcDefaultPayType());
			payentRecord.setPayAccountNo(payDefinition.getIcbcPayAccNo());
			payentRecord.setRecvBankCode(inDetail.getRecvBankCode());
			payentRecord.setRecvBankName(inDetail.getRecvBankName());
			payentRecord.setRecvBankCityName(inDetail.getRecvBankCityName());
			payentRecord.setRecvAccountNo(inDetail.getRecvBankAccountNo());
			payentRecord.setRecvAccountName(inDetail.getRecvBankAccountName());
			payentRecord.setFundUseDesc(inDetail.getFundUseDesc());
			payentRecord.setSummary(inDetail.getSummary());
			payentRecord.setCorporatePersonalFlag(inDetail.getRecvAccountPropType().getCode());
			if (payDefinition.getPayerBankCode().equals(inDetail.getRecvBankCode())) {
				payentRecord.setCrossBankFlag("1");
			} else {
				payentRecord.setCrossBankFlag("2");
			}
			if (StringUtils.hasText(inDetail.getRecvBankCityName())) {
				if (inDetail.getRecvBankCityName().contains(payDefinition.getPayerCityName())) {
					payentRecord.setSameCityFlag("1");
				} else {
					payentRecord.setSameCityFlag("2");
				}
			}
			payentRecord.setTxnResultStatus(ICBCBankTransactionResultStatus.NEW.getCode());
			payentRecords.add(payentRecord);
		}

		DbTransaction dbTransaction = dbTransactionHelper.beginTransaction();
		try {
			// FIXME: use batch insert instead ??
			for (ThirdICBCBankentPayent payentRecord : payentRecords) {
				payentDao.insertSelective(payentRecord);
			}
			dbTransactionHelper.commitTransaction(dbTransaction);
		} catch (Exception e) {
			logger.error("新增保存ThirdICBCBankentPayent记录失败，{}", e.getMessage(), e);
			dbTransactionHelper.rollbackTransaction(dbTransaction);
			throw CoreBusinessExceptionHelper.convertToCoreBusinessException(e);
		}

		return payentRecords;
	}

	private void updatePayentRecords(List<ThirdICBCBankentPayent> payentRecords) {
		DbTransaction dbTransaction = null;
		try {
			dbTransaction = dbTransactionHelper.beginTransaction();
			// FIXME: use batch update instead ??
			for (ThirdICBCBankentPayent payentRecord : payentRecords) {
				payentDao.updateByPrimaryKeySelective(payentRecord);
			}
			dbTransactionHelper.commitTransaction(dbTransaction);
		} catch (Exception e) {
			logger.error("更新保存ThirdICBCBankentPayent记录失败，{}", e.getMessage(), e);
			dbTransactionHelper.rollbackTransaction(dbTransaction);

			logger.info("更新NC结果到DB时失败，故将的交易状态为【交易成功】的交易结果记录调整为【银行处理中】返回调用端，BatchSeqno：{}...",
					payentRecords.get(0).getBatchSeqno());
			for (ThirdICBCBankentPayent payentRecord : payentRecords) {
				if (ICBCBankTransactionResultStatus.BANK_SUCCESS.getCode().equals(payentRecord.getTxnResultStatus())) {
					payentRecord.setTxnResultStatus(ICBCBankTransactionResultStatus.BANK_PROCESSING.getCode());
				}
			}
		}
	}

	private NCResultFailedException fillPayentRecordsWithNCResult(PayentXmlResponse payentXmlResponse,
			List<ThirdICBCBankentPayent> payentRecords) {

		Map<String, PayentXmlResponseRecordDetail> resRdMappings = new HashMap<String, PayentXmlResponseRecordDetail>();
		if (null != payentXmlResponse && null != payentXmlResponse.getRds()) {
			for (PayentXmlResponseRecordDetail resRd : payentXmlResponse.getRds()) {
				resRdMappings.put(resRd.getiSeqno(), resRd);
			}
		}

		String bankAddiBatchReturnCode = payentXmlResponse.getRetCode();
		String bankAddiBatchReturnMsg = payentXmlResponse.getRetMsg();
		String bankRetBatchSerialNo = payentXmlResponse.getSerialNo();
		boolean wholeFailed = !ICBC_INTERFACE_RETURN_CODE_SUCCESS.equals(bankAddiBatchReturnCode);

		PayentXmlResponseRecordDetail matchedResRd = null;
		for (ThirdICBCBankentPayent payent : payentRecords) {
			payent.setBankRetBatchSerialNo(bankRetBatchSerialNo);
			payent.setBankAddiBatchReturnCode(bankAddiBatchReturnCode);
			payent.setBankAddiBatchReturnMsg(bankAddiBatchReturnMsg);

			matchedResRd = resRdMappings.get(payent.getDetailSeqno());
			if (null != matchedResRd) {
				payent.setTxnResultStatus(ICBCBankEntPayOutResultStatusUtils
						.mappingTransactionResultStatus(matchedResRd.getResult()).getCode());
				payent.setBankRetDetailOrderNo(matchedResRd.getOrderNo());
				payent.setBankOriDetailResultCode(matchedResRd.getResult());
				payent.setBankAddiDetailReturnCode(matchedResRd.getiRetCode());
				payent.setBankAddiDetailReturnMsg(matchedResRd.getiRetMsg());
			} else if (wholeFailed) {
				payent.setTxnResultStatus(ICBCBankTransactionResultStatus.BANK_FAILED.getCode());
			} else {
				payent.setTxnResultStatus(ICBCBankTransactionResultStatus.RESULT_UNCERTAIN.getCode());
			}
		}

		if (wholeFailed) {
			return new NCResultFailedException("交易失败，" + bankAddiBatchReturnCode + "-" + bankAddiBatchReturnMsg);
		} else {
			return null;
		}
	}

	private void fillPayentRecordsWhenException(List<ThirdICBCBankentPayent> payentRecords, Exception exception) {
		ICBCBankTransactionResultStatus txnStatus;
		if (exception instanceof NCResultUncertainException) {
			txnStatus = ICBCBankTransactionResultStatus.RESULT_UNCERTAIN;
		} else {
			txnStatus = ICBCBankTransactionResultStatus.BANK_FAILED;
		}
		for (ThirdICBCBankentPayent payent : payentRecords) {
			payent.setTxnResultStatus(txnStatus.getCode());
		}
	}

}
