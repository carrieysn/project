package com.cifpay.lc.bankadapter.api.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cifpay.lc.bankadapter.api.constant.TradeConstant;
import com.cifpay.lc.bankadapter.api.input.unionpay.ExpiryCifParam;
import com.cifpay.lc.bankadapter.api.input.unionpay.OpenCardParam;
import com.cifpay.lc.bankadapter.api.input.unionpay.OpenCifParam;
import com.cifpay.lc.bankadapter.api.input.unionpay.PayCifParam;
import com.cifpay.lc.bankadapter.api.input.unionpay.QueryTradeCifParam;
import com.cifpay.lc.bankadapter.api.input.unionpay.RefundCifParam;
import com.cifpay.lc.bankadapter.api.input.unionpay.SmsCifParam;

public class UnionPayTradeParamFactory extends AbsGetTradeParamFactory {
	private static final Logger LOGGER = LoggerFactory.getLogger(UnionPayTradeParamFactory.class);
	private static final String CP300 = "CP300";
	private static final String CP200 = "CP200";
	private static final String CP500 = "CP500";
	private static final String CREDIT = "30";// 信用卡
	private static final String DEBIT = "10"; // 储蓄卡
	private static final String FIRST = "0";
	private static final String CURRENCY_CODE = "156";

	public static String DINGGOU_BIZTYPE = "001001";// 定购
	public static String QUERY_BIZTYPE = "000000";// 查询
	public static String WU_BIZTYPE = "000902";// 无跳转

	@Override
	public <T> T getTradeParam(Class<T> clz, String... params) throws InstantiationException, IllegalAccessException {
		LOGGER.info("============================== BANK ADAPTER LOG BEGIN ==============================");
		LOGGER.info("银信证类型：{}  银行卡类型：{}", params[0], params[1]);
		LOGGER.info("是否第一次开通：{}", params.length >= 3 ? params[2] : "否");
		T t = null;
		String txnId = null;
		String txnType = null;
		String txnSubType = null;
		String bizType = null;
		if (OpenCifParam.class.getName().equals(clz.getName())) {
			OpenCifParam param = new OpenCifParam();
			// 1. 开证
			if (CP200.equals(params[0]) && DEBIT.equals(params[1])) {
				// 1.1 CP200 储蓄卡
				if (params.length >= 3 && FIRST.equals(params[2])) {
					// 1.1.1 第一次 开通交易
					txnId = TradeConstant.TRADE_CONFIG.TRADE_UNIONPAY_WU_OPEN_CARD;
					txnType = "72";
					txnSubType = "10";
					bizType = WU_BIZTYPE;
				} else {
					// 1.1.2 非第一次，无跳转消费交易
					txnId = TradeConstant.TRADE_CONFIG.TRADE_UNIONPAY_WU_CONSUME;
					txnType = "01";
					txnSubType = "01";
					bizType = WU_BIZTYPE;
				}

			} else if (CP200.equals(params[0]) && CREDIT.equals(params[1])) {
				// 1.2 CP200 信用卡
				txnId = TradeConstant.TRADE_CONFIG.TRADE_UNIONPAY_DINGGOU_CONSUME;
				txnType = "01";
				txnSubType = "02";
				bizType = DINGGOU_BIZTYPE;
			} else if ((CP300.equals(params[0]) || CP500.equals(params[0])) && DEBIT.equals(params[1])) {
				// 1.3 CP300 储蓄卡
				if (params.length >= 3 && FIRST.equals(params[2])) {
					// 1.3.1 第一次 开通交易
					txnId = TradeConstant.TRADE_CONFIG.TRADE_UNIONPAY_WU_OPEN_CARD;
					txnType = "72";
					txnSubType = "10";
					bizType = WU_BIZTYPE;
				} else {
					// 1.1.2 非第一次，无跳转消费交易
					txnId = TradeConstant.TRADE_CONFIG.TRADE_UNIONPAY_WU_AUTH;
					txnType = "02";
					txnSubType = "01";
					bizType = WU_BIZTYPE;
				}

			} else if ((CP300.equals(params[0]) || CP500.equals(params[0])) && CREDIT.equals(params[1])) {
				// 1.4 CP300 信用卡
				txnId = TradeConstant.TRADE_CONFIG.TRADE_UNIONPAY_DINGGOU_AUTH;
				txnType = "02";
				txnSubType = "02";
				bizType = DINGGOU_BIZTYPE;
			}
			param.setTxnId(txnId);
			param.setTxnType(txnType);
			param.setTxnSubType(txnSubType);
			param.setBizType(bizType);
			param.setCurrencyCode(CURRENCY_CODE);
			t = (T) param;
		} else if (PayCifParam.class.getName().equals(clz.getName())) {
			PayCifParam param = new PayCifParam();
			// 2.解付
			if ((CP300.equals(params[0]) || CP500.equals(params[0])) && DEBIT.equals(params[1])) {
				// 2.3 CP300 储蓄卡
				txnId = TradeConstant.TRADE_CONFIG.TRADE_UNIONPAY_WU_AUTH_FINISH;
				txnType = "03";
				txnSubType = "00";
				bizType = WU_BIZTYPE;
			} else if ((CP300.equals(params[0]) || CP500.equals(params[0])) && CREDIT.equals(params[1])) {
				// 2.4 CP300 信用卡
				txnId = TradeConstant.TRADE_CONFIG.TRADE_UNIONPAY_DINGGOU_AUTH_FINISH;
				txnType = "03";
				txnSubType = "00";
				bizType = DINGGOU_BIZTYPE;
			}
			param.setTxnId(txnId);
			param.setTxnType(txnType);
			param.setTxnSubType(txnSubType);
			param.setBizType(bizType);
			param.setCurrencyCode(CURRENCY_CODE);
			t = (T) param;
		} else if (RefundCifParam.class.getName().equals(clz.getName())) {
			RefundCifParam param = new RefundCifParam();
			// 3.退证
			if (CP200.equals(params[0]) && DEBIT.equals(params[1])) {
				// 3.1 CP200 储蓄卡
				txnId = TradeConstant.TRADE_CONFIG.TRADE_UNIONPAY_00_00_200_0_00;
				bizType = WU_BIZTYPE;
			} else if (CP200.equals(params[0]) && CREDIT.equals(params[1])) {
				// 3.2 CP200 信用卡
				txnId = TradeConstant.TRADE_CONFIG.TRADE_UNIONPAY_00_00_200_1_00;
				bizType = DINGGOU_BIZTYPE;
			} else if ((CP300.equals(params[0]) || CP500.equals(params[0])) && DEBIT.equals(params[1])) {
				// 3.3 CP300 储蓄卡
				txnId = TradeConstant.TRADE_CONFIG.TRADE_UNIONPAY_00_00_300_0_00;
				bizType = WU_BIZTYPE;
			} else if ((CP300.equals(params[0]) || CP500.equals(params[0])) && CREDIT.equals(params[1])) {
				// 3.4 CP300 信用卡
				txnId = TradeConstant.TRADE_CONFIG.TRADE_UNIONPAY_00_00_300_1_00;
				bizType = DINGGOU_BIZTYPE;
			}
			param.setTxnId(txnId);
			param.setTxnType("00");
			param.setTxnSubType("00");
			param.setBizType(bizType);
			param.setCurrencyCode(CURRENCY_CODE);
			t = (T) param;
		} else if (ExpiryCifParam.class.getName().equals(clz.getName())) {
			ExpiryCifParam param = new ExpiryCifParam();
			if ((CP300.equals(params[0]) || CP500.equals(params[0])) && DEBIT.equals(params[1])) {
				// CP300 储蓄卡
				txnId = TradeConstant.TRADE_CONFIG.TRADE_UNIONPAY_WU_AUTH_UNDO;
				txnType = "32";
				txnSubType = "00";
				bizType = WU_BIZTYPE;
			} else if ((CP300.equals(params[0]) || CP500.equals(params[0])) && CREDIT.equals(params[1])) {
				// 2.4 CP300 信用卡
				txnId = TradeConstant.TRADE_CONFIG.TRADE_UNIONPAY_DINGGOU_AUTH_UNDO;
				txnType = "32";
				txnSubType = "00";
				bizType = DINGGOU_BIZTYPE;
			}
			param.setTxnId(txnId);
			param.setTxnType(txnType);
			param.setTxnSubType(txnSubType);
			param.setBizType(bizType);
			param.setCurrencyCode(CURRENCY_CODE);
			t = (T) param;
		} else if (QueryTradeCifParam.class.getName().equals(clz.getName())) {
			QueryTradeCifParam param = new QueryTradeCifParam();
			param.setTxnType("00");
			param.setTxnSubType("00");
			if (DEBIT.equals(params[1])) {
				bizType = WU_BIZTYPE;
			} else if (CREDIT.equals(params[1])) {
				bizType = DINGGOU_BIZTYPE;
			}
			param.setBizType(bizType);
			t = (T) param;
		} else if (OpenCardParam.class.getName().equals(clz.getName())) {
			OpenCardParam param = new OpenCardParam();
			param.setTxnId(TradeConstant.TRADE_CONFIG.TRADE_UNIONPAY_WU_OPEN_CARD);
			param.setTxnType("79");
			param.setTxnSubType("00");
			param.setBizType(WU_BIZTYPE);
			t = (T) param;
		}else if (SmsCifParam.class.getName().equals(clz.getName())) {
			SmsCifParam param = new SmsCifParam();
			param.setTxnId(TradeConstant.TRADE_CONFIG.TRADE_UNIONPAY_WU_SMS);
			param.setTxnType("77");
			param.setTxnSubType("02");
			param.setBizType(WU_BIZTYPE);
			t = (T) param;
		}

		return t;
	}

	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		UnionPayTradeParamFactory f = new UnionPayTradeParamFactory();
		OpenCifParam param = f.getTradeParam(OpenCifParam.class, CP300, CREDIT, "123456");
		System.out.println(param);
	}
}
