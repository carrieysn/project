package com.cifpay.lc.bankadapter.universal.service.impl.unionpay;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.cifpay.lc.bankadapter.api.constant.TradeConstant;
import com.cifpay.lc.bankadapter.api.input.AbsTradeParam;
import com.cifpay.lc.bankadapter.api.output.AbsBusinessResult;
import com.cifpay.lc.bankadapter.universal.IBankDeal;
import com.cifpay.lc.constant.ReturnCode;
import com.cifpay.lc.core.db.dao.LcTrdUnionPayMainDao;
import com.cifpay.lc.core.db.pojo.UnionPayTrdMain;
import com.cifpay.lc.core.exception.BankAdapterException;
import com.cifpay.lc.util.DateUtil;

@Component
public class UnionPayStategyService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UnionPayStategyService.class);
	@Autowired
	private LcTrdUnionPayMainDao mainDao;

	private final Map<String, IBankDeal> bankDealProcessersMap = new HashMap<String, IBankDeal>();

	@Autowired
	public void initBankDealProcessers(List<IBankDeal> bankDealProcessers) {
		if (null != bankDealProcessers) {
			for (IBankDeal bd : bankDealProcessers) {
				bankDealProcessersMap.put(bd.getBankCode(), bd);
			}
		}
	}

	/**
	 * {@code
	 * 返回值： | a. 不存在 -> -1 
	 *                 | b. 存在 
	 *                    ----成功 -> 0 
	 *                    ----失败 -> 1 
	 *                    ----状态未明 -> 2
	 * }
	 * 
	 * @throws Exception
	 */
	public int checkState(UnionPayTrdMain unionPayTrdMain) {
		int i = -1;
		if (unionPayTrdMain == null) {
			LOGGER.info("=====检查交易状态->不存在记录！");
			i = -1;
		} else {
			String syncTradeResult = unionPayTrdMain.getSyncTradeResult();
			String asynTradeResult = unionPayTrdMain.getAsynTradeResult();
			if (TradeConstant.TRADE_CONFIG.TRADE_RESULT_SUCCEED_0.equals(asynTradeResult)) {
				LOGGER.info("=====检查交易状态->存在记录，异步状态为成功！");
				i = 0;
			} else if (TradeConstant.TRADE_CONFIG.TRADE_RESULT_FAIL_1.equals(asynTradeResult)) {
				LOGGER.info("=====检查交易状态->存在记录，异步状态为失败！");
				i = 1;
			} else if (TradeConstant.TRADE_CONFIG.TRADE_RESULT_UNKNOWN_2.equals(asynTradeResult)) {
				LOGGER.info("=====检查交易状态->存在记录，异步状态为未明！");
				i = 2;
			} else if (TradeConstant.TRADE_CONFIG.TRADE_RESULT_SUCCEED_0.equals(syncTradeResult)) {
				LOGGER.info("=====检查交易状态->存在记录，同步状态为成功！");
				i = 3;
			} else if (TradeConstant.TRADE_CONFIG.TRADE_RESULT_FAIL_1.equals(syncTradeResult)) {
				LOGGER.info("=====检查交易状态->存在记录，同步状态为失败！");
				i = 4;
			} else if (TradeConstant.TRADE_CONFIG.TRADE_RESULT_UNKNOWN_2.equals(syncTradeResult)) {
				LOGGER.info("=====检查交易状态->存在记录，同步状态为未明！");
				i = 5;
			} else {
				throw new BankAdapterException(ReturnCode.CORE_BA_PARAM_EXCEPTION_N105011, "非法的交易状态!");
			}
		}

		// TODO 检查是否交易尝试次数过多
		return i;
	}

	public UnionPayTrdMain getAuth(String queryId) {
		UnionPayTrdMain trdMain = mainDao.selectByQueryId(queryId);
		return trdMain;
	}

	public UnionPayTrdMain getPay(String queryId) {
		UnionPayTrdMain trdMain = mainDao.selectByQueryId(queryId);
		return trdMain;
	}

	public boolean checkAuth(UnionPayTrdMain tm) {
		boolean b = false;
		if (tm != null) {
			if (TradeConstant.TRADE_CONFIG.TRADE_RESULT_SUCCEED_0.equals(tm.getAsynTradeResult())) {
				LOGGER.info("=====检查预授权情况->存在记录，状态为成功！");
				b = true;
			} else if (TradeConstant.TRADE_CONFIG.TRADE_RESULT_FAIL_1.equals(tm.getAsynTradeResult())) {
				LOGGER.info("=====检查预授权情况->存在记录，状态为失败，程序即将返回！");
			} else if (TradeConstant.TRADE_CONFIG.TRADE_RESULT_UNKNOWN_2.equals(tm.getAsynTradeResult())) {
				LOGGER.info("=====检查预授权情况->存在记录，状态为未明，程序即将返回！");
			}
		} else {
			LOGGER.info("=====检查预授权情况->不存在记录，程序即将返回！");
		}
		return b;
	}

	public boolean checkPay(UnionPayTrdMain tm) {
		boolean b = false;
		if (tm != null) {
			if (TradeConstant.TRADE_CONFIG.TRADE_RESULT_SUCCEED_0.equals(tm.getAsynTradeResult())) {
				LOGGER.info("=====检查支付交易情况->存在记录，状态为成功！");
				b = true;
			} else if (TradeConstant.TRADE_CONFIG.TRADE_RESULT_FAIL_1.equals(tm.getAsynTradeResult())) {
				LOGGER.info("=====检查支付交易情况->存在记录，状态为失败，程序即将返回！");
			} else if (TradeConstant.TRADE_CONFIG.TRADE_RESULT_UNKNOWN_2.equals(tm.getAsynTradeResult())) {
				LOGGER.info("=====检查支付交易情况->存在记录，状态为未明，程序即将返回！");
			}
		} else {
			LOGGER.info("=====检查支付交易情况->不存在记录，程序即将返回！");
		}
		return b;
	}

	public void stopRefundCif() {
		String begin = "22:30:00";
		String end = "23:30:00";
		DateFormat df = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		String currentTime = df.format(date);
		if (currentTime.compareTo(end) <= 0 && currentTime.compareTo(begin) >= 0) {
			throw new BankAdapterException(ReturnCode.CORE_BA_UNDEFINE_N105999,
					"本段时间内(" + begin + "-" + end + ")不可进行退票操作！");
		}
	}

	public boolean isClearing(UnionPayTrdMain payTm) {
		if (StringUtils.isEmpty(payTm.getRtnSettleDate())) {
			throw new BankAdapterException(ReturnCode.CORE_BA_UNDEFINE_N105999, "settleDate is null.");
		}
		String settleDate = procesSettleDate(payTm.getRtnSettleDate(), payTm.getTxnTime()) + "23:00:00";

		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		DateFormat df = new SimpleDateFormat("YYYYMMddHH:mm:ss");
		String currentMMDD = df.format(date);
		if (currentMMDD.compareTo(settleDate) <= 0) {
			// 当前日期小于等于清算日期，即未清算
			return false;
		} else {
			return true;
		}
	}

	// 将settleDate由MMdd格式转化为yyyyMMdd格式
	public static String procesSettleDate(String settleDate, String txnTime) {
		String yyyy = null;
		if (settleDate != null && settleDate.matches("((0\\d)|(1[012]))(([012]\\d)|(3[01]))")) {
			int month = Integer.parseInt(settleDate.substring(0, 2));
			int sytemMonth = Integer.parseInt(txnTime.substring(4, 6));
			if (Math.abs(sytemMonth - month) < 2) {
				yyyy = txnTime.substring(0, 4);
			} else if (sytemMonth > month) {
				yyyy = String.valueOf(Integer.parseInt(txnTime.substring(0, 4)) + 1);
			} else {
				yyyy = String.valueOf(Integer.parseInt(txnTime.substring(0, 4)) - 1);
			}
		} else {
			throw new RuntimeException("settleDate格式不合法：" + settleDate);
		}
		return yyyy + settleDate;
	}

	public AbsBusinessResult tradeDealAdapter(AbsTradeParam param) throws Exception {
		String key = TradeConstant.TRADE_CONFIG.TRADE_UNION_PAY + param.getTxnId();
		LOGGER.info("=====选择处理类-key:{}", key);
		IBankDeal deal = bankDealProcessersMap.get(key);
		LOGGER.info("=====选择处理类-Deal:{}", deal);
		if (deal != null) {
			return deal.bankDeal(param);
		} else {
			throw new BankAdapterException(ReturnCode.CORE_BA_NONSUPPORT_TRADETYPE_EXCEPTION_N105101,
					"未找到适配的处理器! " + key);
		}
	}

}
