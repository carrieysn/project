package com.cifpay.lc.bankadapter.unionpay.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.cifpay.lc.bankadapter.api.constant.TradeConstant;
import com.cifpay.lc.bankadapter.api.input.AbsTradeParam;
import com.cifpay.lc.bankadapter.api.input.unionpay.RefundCifParam;
import com.cifpay.lc.bankadapter.api.output.AbsBusinessResult;
import com.cifpay.lc.bankadapter.api.output.GeneralTradeResult;
import com.cifpay.lc.bankadapter.unionpay.constant.UnionPayConfig;
import com.cifpay.lc.bankadapter.unionpay.output.AuthHandleResult;
import com.cifpay.lc.bankadapter.unionpay.util.DataUtil;
import com.cifpay.lc.bankadapter.unionpay.util.EncryptDataUtil;
import com.cifpay.lc.bankadapter.unionpay.util.HttpClient;
import com.cifpay.lc.bankadapter.unionpay.util.UnionPaySettingUtil;
import com.cifpay.lc.bankadapter.universal.IBankDeal;
import com.cifpay.lc.bankadapter.universal.tool.StringTool;
import com.cifpay.lc.constant.ReturnCode;
import com.cifpay.lc.core.db.dao.AdminLcMerchantDao;
import com.cifpay.lc.core.db.dao.LcTrdUnionPayFlowDao;
import com.cifpay.lc.core.db.dao.TrdCodeDescDao;
import com.cifpay.lc.core.db.pojo.AdminLcMerchant;
import com.cifpay.lc.core.db.pojo.TrdCodeDesc;
import com.cifpay.lc.core.db.pojo.TrdUnionPayFlow;
import com.cifpay.lc.core.exception.BankAdapterException;
import com.cifpay.lc.core.uid.LcTrdFlowIdWorker;

/**
 * 银联订购 预授权完成撤销
 * 
 * @author Administrator
 *
 */
@Component
public class UnionPayDingAuthFinishUndoDeal implements IBankDeal {

	private static Logger LOGGER = LoggerFactory.getLogger(UnionPayDingAuthFinishUndoDeal.class);

	@Autowired
	private LcTrdFlowIdWorker idWork;

	@Autowired
	private LcTrdUnionPayFlowDao unionPayFlowDao;

	@Autowired
	private TrdCodeDescDao trdCodeDescDao;

	@Autowired
	private AdminLcMerchantDao adminLcMerchantDao;

	@Override
	public String getBankCode() {
		// TODO Auto-generated method stub
		return TradeConstant.TRADE_CONFIG.TRADE_UNION_PAY
				+ TradeConstant.TRADE_CONFIG.TRADE_UNIONPAY_DINGGOU_AUTH_FINISH_UNDO;
	}

	@Override
	public AbsBusinessResult bankDeal(AbsTradeParam tradeParam) throws Exception {
		// TODO Auto-generated method stub
		RefundCifParam param = (RefundCifParam) tradeParam;
		// 1. 转换发送银联参数
		Map<String, String> requestData = transToReqData(param);
		LOGGER.info("银联订购-预授权完成撤销交易请求数据！ {}", StringTool.printMap(requestData));
		// 2. 插入流水记录 TODO 设置状态为未知状态
		TrdUnionPayFlow flowInfo = insertUnionPayFlow(param);
		// 3. 发送请求
		String requestBackUrl = UnionPayConfig.backTransUrl;
		Map<String, String> responseData = HttpClient.post(requestData, requestBackUrl,
				UnionPaySettingUtil.encoding_UTF8);
		LOGGER.info("银联订购-预授权完成撤销交易请求返回数据！ {}", StringTool.printMap(responseData));
		GeneralTradeResult result = null;
		if (!responseData.isEmpty()) {
			if (EncryptDataUtil.validate(responseData, UnionPaySettingUtil.encoding_UTF8)) {
				// 4.转换银联接口返回结果
				AuthHandleResult authResult = (AuthHandleResult) DataUtil.convertMap(AuthHandleResult.class,
						responseData);
				String respCode = responseData.get("respCode");
				result = new GeneralTradeResult();
				result.setFlowId(flowInfo.getFlowId());
				// TODO 将返回码与系统码进行转换
				result = transToSysParam(result, respCode);
				result.setTradeResult(UnionPaySettingUtil.transRespCode(respCode));
				result.setQueryId(authResult.getQueryId());// 消费交易后返回 交易查询流水号
															// 供查询
				// 业务需求参数 需设置 TODO 将银联返回内容放至map
				// HashMap<String, Object> resultMap = new HashMap<String,
				// Object>();
				result.setResultMap(responseData);
				// 5. 请求返回后 更新银联交易渠道流水信息 TODO 将结果控制两个结果控制栏位存入流水表。
				updateUnionPayFlow(authResult, flowInfo.getFlowId());
			} else {
				LOGGER.info("验证签名结果[失败].");
				// 验签失败，需解决验签问题
				throw new BankAdapterException(ReturnCode.CORE_BA_RESULT_EXCEPTON_N105501, "验证签名结果[失败].");
			}
		} else {
			throw new BankAdapterException(ReturnCode.CORE_BA_UNDEFINE_N105999, "未获取到返回报文或返回http状态码非200.");
		}

		return result;
	}

	public GeneralTradeResult transToSysParam(GeneralTradeResult result, String respCode) {
		TrdCodeDesc codeDesc = new TrdCodeDesc();
		codeDesc.setPlatformId(TradeConstant.TRADE_CONFIG.TRADE_UNION_PAY);
		codeDesc.setRespCode(respCode);
		TrdCodeDesc getCodeDesc = trdCodeDescDao.selectBySelective(codeDesc);
		int sysCode = 0;
		String resultDesc = "";
		if (null == getCodeDesc) {
			sysCode = ReturnCode.CORE_BA_UNDEFINE_N105999;
		} else {
			sysCode = DataUtil.isEmpty(getCodeDesc.getSysCode()) ? ReturnCode.CORE_BA_UNDEFINE_N105999
					: Integer.valueOf(getCodeDesc.getSysCode());
			resultDesc = getCodeDesc.getSysDesc();
		}
		result.setSysReturnCode(sysCode);
		result.setResultDesc(resultDesc);
		return result;
	}

	// 记录银联交易渠道流水信息
	public TrdUnionPayFlow insertUnionPayFlow(RefundCifParam param) {

		TrdUnionPayFlow payFlow = new TrdUnionPayFlow();
		long flowId = idWork.nextId();
		payFlow.setFlowId(flowId);
		payFlow.setBusinessId(param.getBusinessId());
		payFlow.setLcId(param.getLcId());
		payFlow.setMerId(param.getMerId());
		payFlow.setSubMerId(param.getSubMerId());
		payFlow.setTxnTime(param.getTxnTime());
		payFlow.setTxnAmt(Long.valueOf(param.getTxnAmt()));
		payFlow.setOrigFlowId(param.getOrigOryId());// 原始预授权完成交易的queryId
		payFlow.setOrderId(param.getOrderId());
		payFlow.setTxnType(UnionPaySettingUtil.auth_finish_undo_txnType);
		payFlow.setTxnSubType(UnionPaySettingUtil.auth_finish_undo_txnSubType);
		payFlow.setBizType(UnionPaySettingUtil.consume_bizType);
		payFlow.setInsertTime(new Date());
		payFlow.setSyncTradeResult(UnionPaySettingUtil.TRADE_RESULT__UNKNOWN);// 默认未知状态
		unionPayFlowDao.insertSelective(payFlow);
		return payFlow;

	}

	// 请求返回后 更新银联交易渠道流水信息
	public void updateUnionPayFlow(AuthHandleResult result, long flowId) {

		TrdUnionPayFlow updataFlow = new TrdUnionPayFlow();
		updataFlow.setFlowId(flowId);
		String respCode = result.getRespCode();
		updataFlow.setNewFlowId(result.getQueryId());// 预授权完成撤销交易的交易流水号
		updataFlow.setSyncRespCode(respCode);
		updataFlow.setSyncRespMsg(result.getRespMsg());
		updataFlow.setSyncTradeResult(UnionPaySettingUtil.transRespCode(respCode));
		updataFlow.setLastUpdTime(new Date());
		unionPayFlowDao.updateUnionPayFlow(updataFlow);
	}

	public Map<String, String> transToReqData(RefundCifParam param) {
		return transToReqData4Level2(param);
	}

	public Map<String, String> transToReqData4Level2(RefundCifParam param) {

		Map<String, String> inputParam = new HashMap<String, String>();
		inputParam.put("version", UnionPaySettingUtil.version);
		inputParam.put("encoding", UnionPaySettingUtil.encoding_UTF8);
		inputParam.put("signMethod", UnionPaySettingUtil.signMethod);
		inputParam.put("txnType", UnionPaySettingUtil.auth_finish_undo_txnType);
		inputParam.put("txnSubType", UnionPaySettingUtil.auth_finish_undo_txnSubType);
		inputParam.put("bizType", UnionPaySettingUtil.consume_bizType);
		inputParam.put("channelType", UnionPaySettingUtil.channelType);
		//String merId = UnionPaySettingUtil.getDingGouMerId();
		String subMerId = param.getSubMerId();// 二级商户代码
		inputParam.put("merId", subMerId);
		//param.setMerId(merId);
	
		//inputParam.put("subMerId", subMerId);
		AdminLcMerchant merChant = adminLcMerchantDao.selectMerchantInfos(subMerId);
		if (null == merChant) {
			LOGGER.info("二级商户信息不能为空");
			throw new BankAdapterException(ReturnCode.CORE_BA_PARAM_EXCEPTION_N105011, "二级商户信息不能为空.");
		}
		//inputParam.put("subMerName", merChant.getMerchantName());// 二级商户名称
		//inputParam.put("subMerAbbr", merChant.getMerSiteName());// 二级商户简称
		inputParam.put("accessType", UnionPaySettingUtil.accessType_0);// 商户类商户
		inputParam.put("orderId", param.getOrderId());
		inputParam.put("txnTime", param.getTxnTime());
		inputParam.put("reqReserved", param.getReqReserved());
		inputParam.put("currencyCode", StringUtils.isEmpty(param.getCurrencyCode()) ? "156" : param.getCurrencyCode());
		inputParam.put("txnAmt", String.valueOf(param.getTxnAmt()));
		inputParam.put("origQryId", param.getOrigOryId());// 原预授权交易返回的的queryId
		inputParam.put("backUrl", UnionPaySettingUtil.backUrl);// 后台通知地址？？后续需设置
		// inputParam = EncryptDataUtil.sign(inputParam,
		// UnionPaySettingUtil.encoding_UTF8);
		inputParam = UnionPaySettingUtil.signData(inputParam, merChant.getAdminCredentials());
		return inputParam;
	}

	public Map<String, String> transToReqData4Level1(RefundCifParam param) {

		Map<String, String> inputParam = new HashMap<String, String>();
		inputParam.put("version", UnionPaySettingUtil.version);
		inputParam.put("encoding", UnionPaySettingUtil.encoding_UTF8);
		inputParam.put("signMethod", UnionPaySettingUtil.signMethod);
		inputParam.put("txnType", UnionPaySettingUtil.auth_finish_undo_txnType);
		inputParam.put("txnSubType", UnionPaySettingUtil.auth_finish_undo_txnSubType);
		inputParam.put("bizType", UnionPaySettingUtil.consume_bizType);
		inputParam.put("channelType", UnionPaySettingUtil.channelType);
		String merId = UnionPaySettingUtil.getDingGouMerId();
		inputParam.put("merId", merId);
		param.setMerId(merId);
		String subMerId = param.getSubMerId();// 二级商户代码
		inputParam.put("subMerId", subMerId);
		AdminLcMerchant merChant = adminLcMerchantDao.selectMerchantInfos(subMerId);
		if (null == merChant) {
			LOGGER.info("二级商户信息不能为空");
			throw new BankAdapterException(ReturnCode.CORE_BA_PARAM_EXCEPTION_N105011, "二级商户信息不能为空.");
		}
		inputParam.put("subMerName", merChant.getMerchantName());// 二级商户名称
		inputParam.put("subMerAbbr", merChant.getMerSiteName());// 二级商户简称
		inputParam.put("accessType", UnionPaySettingUtil.accessType_2);// 平台类商户
		inputParam.put("orderId", param.getOrderId());
		inputParam.put("txnTime", param.getTxnTime());
		inputParam.put("reqReserved", param.getReqReserved());
		inputParam.put("currencyCode", StringUtils.isEmpty(param.getCurrencyCode()) ? "156" : param.getCurrencyCode());
		inputParam.put("txnAmt", String.valueOf(param.getTxnAmt()));
		inputParam.put("origQryId", param.getOrigOryId());// 原预授权交易返回的的queryId
		inputParam.put("backUrl", UnionPaySettingUtil.backUrl);// 后台通知地址？？后续需设置
		// inputParam = EncryptDataUtil.sign(inputParam,
		// UnionPaySettingUtil.encoding_UTF8);
		inputParam = UnionPaySettingUtil.signData(inputParam, merChant.getAdminCredentials());
		return inputParam;
	}

}