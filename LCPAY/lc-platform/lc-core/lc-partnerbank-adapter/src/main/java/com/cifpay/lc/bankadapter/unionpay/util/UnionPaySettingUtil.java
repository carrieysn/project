package com.cifpay.lc.bankadapter.unionpay.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cifpay.lc.bankadapter.unionpay.constant.UnionPayConfig;
import com.cifpay.lc.constant.ReturnCode;
import com.cifpay.lc.core.db.pojo.AdminCredentials;
import com.cifpay.lc.core.exception.BankAdapterException;

public class UnionPaySettingUtil {
	private static Logger LOGGER = LoggerFactory.getLogger(UnionPaySettingUtil.class);
	public static String encoding_UTF8 = "UTF-8";
	public static String encoding_GBK = "GBK";
	public static String version = "5.0.0";
	public static String signMethod = "01";
	public static String consume_bizType = "001001";// 消费 、消费撤销、退货、预授权
	public static String query_bizType = "000000";// 查询
	public static String token_bizType = "000902";// 业务类型 token支付
	public static String channelType = "07";
	public static String accessType_2 = "2";// 平台类商户
	public static String accessType_0 = "0";// 普通类商户
	public static String certificate_txnType = "72";// 订购实名认证类型
	public static String certificate_txnSubType = "01";// 订购实名认证子类型

	public static String consume_txnType = "01";// 订购消费交易类型
	public static String consume_txnSubType = "02";// 订购消费交易子类型

	public static String consume_undo_txnType = "31";// 订购消费撤销交易类型
	public static String consume_undo_txnSubType = "00";// 订购消费撤销交易子类型

	public static String refund_txnType = "04";// 订购退货交易类型
	public static String refund_txnSubType = "00";// 订购退货交易子类型

	public static String query_txnType = "00";// 订购查询状态交易子类型
	public static String query_txnSubType = "00";// 订购查询状态交易子类型

	public static String auth_txnType = "02";// 订购预授权交易类型
	public static String auth_txnSubType = "02";// 订购预授权交易子类型
	public static String token_auth_txnType = "02";// 无跳转预授权交易类型
	public static String token_auth_txnSubType = "01";// 无跳转预授权交易子类型

	public static String auth_finish_txnType = "03";// 订购预授权完成交易类型
	public static String auth_finish_txnSubType = "00";// 订购预授权完成交易子类型

	public static String auth_undo_txnType = "32";// 订购预授权撤销交易类型
	public static String auth_undo_txnSubType = "00";// 订购预授权撤销交易子类型
	public static String token_auth_undo_txnType = "32";// 无跳转预授权撤销交易类型
	public static String token_auth_undo_txnSubType = "00";// 无跳转预授权撤销交易子类型

	public static String auth_finish_undo_txnType = "33";// 订购预授权完成撤销交易类型
	public static String auth_finish_undo_txnSubType = "00";// 订购预授权完成撤销交易子类型
	public static String token_auth_finish_undo_txnType = "33";// 无跳转预授权完成撤销交易类型
	public static String token_auth_finish_undo_txnSubType = "00";// 无跳转预授权完成撤销交易子类型

	public static String opencard_txnType = "79";// 无跳转银联全渠道支付开通交易类型
	public static String opencard_txnSubType = "00";// 无跳转银联全渠道支付开通交易子类型

	public static String token_consume_txnType = "01";// 无跳转消费交易类型
	public static String token_consume_txnSubType = "01";// 无跳转消费交易子类型
	
	public static String token_sms_txnType = "77";// 无跳转短信交易类型
	public static String token_sms_txnSubType = "02";// 无跳转短信交易子类型

	public static final String UNIONPAY_RESULT_SUCCESS = "00";// 银联返回成功状态
	public static final String UNIONPAY_RESULT_FAIL = "01";// 银联返回失败状态
	public static final String UNIONPAY_RESULT_ASYN_SUCCESS = "01";// 银联返回失败状态

	public static final String TRADE_RESULT__SUCCESS = "0";// 交易状态成功
	public static final String TRADE_RESULT__FAIL = "1";// 交易状态失败
	public static final String TRADE_RESULT__UNKNOWN = "2";// 交易状态未知
	public static final String TRADE_RESULT__NO_RECORD = "2";// 查询记录不存在（对查询交易）

	public static String backUrl = UnionPayConfig.backUrl;// 后台通知地址
	public static String backDingCertificationUrl = UnionPayConfig.backDingCertificationUrl;// 后台通知地址
	public static String openCartFrontUrL = UnionPayConfig.openCartFrontUrL;
	public static String openCartBackUrL = UnionPayConfig.openCartBackUrL;

	public static String getCurrentTime() {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	}

	public static String getOrderId() {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	}

	public static String transRespCode4Origin(String respCode) {
		if (DataUtil.isEmpty(respCode)) {
			return null;
		} else {
			if (respCode.equals("00") || respCode.equals("A6")) {
				return TRADE_RESULT__SUCCESS;
			} else if (respCode.equals("03") || respCode.equals("04") || respCode.equals("05")) {
				return TRADE_RESULT__UNKNOWN;
			} else {
				return TRADE_RESULT__FAIL;
			}

		}
	}

	public static String transRespCode(String respCode) {
		if (DataUtil.isEmpty(respCode)) {
			return TRADE_RESULT__UNKNOWN;
		} else {
			if (respCode.equals("00") || respCode.equals("A6")) {
				return TRADE_RESULT__SUCCESS;
			} else if (respCode.equals("03") || respCode.equals("04") || respCode.equals("05")) {
				return TRADE_RESULT__UNKNOWN;
			} else {
				return TRADE_RESULT__FAIL;
			}

		}
	}

	public static String transRespCode4Query(String respCode) {
		if (DataUtil.isEmpty(respCode)) {
			// 查询失败
			return TRADE_RESULT__FAIL;
		} else {
			if (respCode.equals("00") || respCode.equals("A6")) {
				return TRADE_RESULT__SUCCESS;
			} else if (respCode.equals("34")) {
				return TRADE_RESULT__NO_RECORD;
			} else {
				// 查询失败
				return TRADE_RESULT__FAIL;
			}

		}
	}

	public static String getMerId(String bizType) {
		if (consume_bizType.equals(bizType)) {
			return getDingGouMerId();
		} else if (token_bizType.equals(bizType)) {
			return getTokenMerId();
		} else {
			throw new BankAdapterException(ReturnCode.CORE_BA_UNDEFINE_N105999, "错误的bizType.");
		}
	}

	public static String getDingGouMerId() {

		String merId = UnionPayConfig.dingGou_merId;
		if (DataUtil.isEmpty(merId)) {
			throw new BankAdapterException(ReturnCode.CORE_BA_UNDEFINE_N105999, "配置文件获取商户代码异常.");
		}
		return merId;
	}

	public static String getTokenMerId() {

		String merId = UnionPayConfig.token_merId;
		if (DataUtil.isEmpty(merId)) {
			throw new BankAdapterException(ReturnCode.CORE_BA_UNDEFINE_N105999, "配置文件获取商户代码异常.");
		}
		return merId;
	}

	public static Map<String, String> signData(Map<String, String> inputParam, AdminCredentials adminCredentials) {
		Map<String, String> resultMap = new HashMap<String, String>();
		String certPath = "";
		String certPwd = "";
		try {
			certPath = UnionPayConfig.cifpayCert_path + adminCredentials.getCreFileName();// 私钥证书
			certPwd = ThreeDESUtil.decDecrypt(adminCredentials.getCrePassword());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.error("####signData####", e);
			throw new BankAdapterException(ReturnCode.CORE_BA_PARAM_EXCEPTION_N105011, "获取证书信息异常.");
		} // 密码
		resultMap = EncryptDataUtil.sign(inputParam, certPath, certPwd, UnionPaySettingUtil.encoding_UTF8);
		return resultMap;

	}

}
