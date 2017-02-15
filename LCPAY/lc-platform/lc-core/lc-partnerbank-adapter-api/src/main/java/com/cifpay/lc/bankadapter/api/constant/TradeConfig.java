package com.cifpay.lc.bankadapter.api.constant;

public class TradeConfig {

	public static final String TRADE_DEFAULT_CURRENCY = "CNY";
	public static final String TRADE_DEFAULT_HOLDDATE = "9999-12-31";

	public static final String TRADE_TYPE_FREEZE = "FREEZE";
	public static final String TRADE_TYPE_UNFREEZE = "UNFREEZE";
	public static final String TRADE_TYPE_PAY = "PAY";
	public static final String TRADE_TYPE_SYNC = "SYNC";

	public static final String TRADE_TYPE_SCPAY = "SC_PAY";
	public static final String TRADE_TYPE_SCSYNC = "SC_SYNC";

	public static final String TRADE_RESULT_SUCCEED_0 = "0";
	public static final String TRADE_RESULT_FAIL_1 = "1";
	public static final String TRADE_RESULT_UNKNOWN_2 = "2";

	public static final String TRADE_RESULT__NO_RECORD = "2";

	public static final String TRADE_BANK_ICBC = "ICBC";
	public static final String TRADE_BANK_BOC = "BOC";
	public static final String TRADE_BANK_ABC = "ABC";
	public static final String TRADE_UNION_PAY = "UNION_PAY";

	public static final String TRADE_CUSTOMER_TYPE_PERSONAL = "PERSONAL";
	public static final String TRADE_CUSTOMER_TYPE_ENTERPRISE = "ENTERPRISE";

	public static final int TRADE_FAIL_COUNT = 3;

	public static final int TRADE_NOTIFY_COUNT = 3;

	public static final String TRADE_UNIONPAY_DINGGOU = "DINGGOU";
	public static final String TRADE_DINGGOU_CONSUME = "CONSUME";
	public static final String TRADE_DINGGOU_CONSUMEUNDO = "CONSUME_UNDO";
	public static final String TRADE_DINGGOU_REFUND = "REFUND";
	public static final String TRADE_DINGGOU_QUERY = "QUERY";
	public static final String TRADE_DINGGOU_AUTH = "AUTH";
	public static final String TRADE_DINGGOU_AUTHUNDO = "AUTH_UNDO";
	public static final String TRADE_DINGGOU_AUTHFINISH = "AUTH_FINISH";
	public static final String TRADE_DINGGOU_AUTHFINISHUNDO = "AUTH_FINISH_UNDO";

	// 交易类型（2位）+交易子类型（2位）+银信证类型（3位）+卡别（1位 0借记卡 1信用卡 2不区分）+识别码（2位 默认00）
	public static final String TRADE_UNIONPAY_WU_CONSUME = "01-01-200-0-00";// 无跳转消费类交易
	public static final String TRADE_UNIONPAY_DINGGOU_CONSUME = "01-02-200-1-00";// 订购消费类交易
	public static final String TRADE_UNIONPAY_WU_AUTH = "02-01-300-0-00";// 无跳转预授权交易
	public static final String TRADE_UNIONPAY_DINGGOU_AUTH = "02-02-300-1-00";// 订购预授权交易
	public static final String TRADE_UNIONPAY_DINGGOU_QUERY = "00-00-000-2-00";// 订购交易状态查询
	public static final String TRADE_UNIONPAY_WU_QUERY = "00-00-000-2-01";// 无跳转交易状态查询

	public static final String TRADE_UNIONPAY_WU_AUTH_FINISH = "03-00-300-0-00";// 无跳转预授权完成交易
	public static final String TRADE_UNIONPAY_DINGGOU_AUTH_FINISH = "03-00-300-1-00";// 订购预授权完成交易
	public static final String TRADE_UNIONPAY_DINGGOU_REAL_AUTH = "72-10-000-2-00";// 订购实名认证
	public static final String TRADE_UNIONPAY_WU_OPEN_CARD = "79-00-000-2-00";// 无跳转开通交易

	public static final String TRADE_UNIONPAY_WU_CONSUME_UNDO = "31-00-200-0-00";// 无跳转消费撤销交易
	public static final String TRADE_UNIONPAY_WU_CONSUME_REFUND = "04-00-200-0-00";// 无跳转消费退款交易（无跳转退货）

	public static final String TRADE_UNIONPAY_WU_AUTH_FINISH_UNDO = "33-00-300-0-00";// 无跳转预授权完成撤销交易

	public static final String TRADE_UNIONPAY_DINGGOU_CONSUME_UNDO = "31-00-300-1-00";// 订购消费撤销交易
	public static final String TRADE_UNIONPAY_DINGGOU_CONSUME_REFUND = "04-00-300-1-00";// 订购消费退款交易（订购退货）
	public static final String TRADE_UNIONPAY_DINGGOU_AUTH_FINISH_UNDO = "33-00-300-1-00";// 订购完成撤销交易
	public static final String TRADE_UNIONPAY_DINGGOU_AUTH_UNDO = "32-00-300-1-00";// 订购预授权撤销交易
	public static final String TRADE_UNIONPAY_WU_AUTH_UNDO = "32-00-300-0-00";// 无跳转预授权撤销交易

	public static final String TRADE_UNIONPAY_00_00_200_0_00 = "00-00-200-0-00";// CP200借记卡退证
	public static final String TRADE_UNIONPAY_00_00_200_1_00 = "00-00-200-1-00";// CP200信用卡退证
	public static final String TRADE_UNIONPAY_00_00_300_0_00 = "00-00-300-0-00";// CP300借记卡退证
	public static final String TRADE_UNIONPAY_00_00_300_1_00 = "00-00-300-1-00";// CP300信用卡退证

	public static final String TRADE_UNIONPAY_BACK_RCV_RESPONSE = "UNIONPAY_BACK_RCV_RESPONSE";// 银联异步通知后台处理
	public static final String TRADE_UNIONPAY_OPEN_CARD_RESPONSE = "UNIONPAY_OPEN_CARD_RESPONSE";// 无跳转开通前后台响应处理类
	public static final String TRADE_UNIONPAY_WU_SMS = "77-02-000-0-00";// 无跳转短信
	public static final String DINGGOU_BIZTYPE = "001001";// 定购
	public static final String WU_BIZTYPE = "000902";// 无跳转
}
