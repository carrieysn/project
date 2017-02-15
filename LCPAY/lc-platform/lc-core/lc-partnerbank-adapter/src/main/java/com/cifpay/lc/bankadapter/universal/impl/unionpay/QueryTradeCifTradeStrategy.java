package com.cifpay.lc.bankadapter.universal.impl.unionpay;

import java.util.Date;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.cifpay.lc.bankadapter.api.constant.TradeConstant;
import com.cifpay.lc.bankadapter.api.input.AbsTradeParam;
import com.cifpay.lc.bankadapter.api.input.unionpay.QueryTradeCifParam;
import com.cifpay.lc.bankadapter.api.output.AbsBusinessResult;
import com.cifpay.lc.bankadapter.api.output.GeneralTradeResult;
import com.cifpay.lc.bankadapter.universal.ITradeStrategy;
import com.cifpay.lc.bankadapter.universal.service.UnionPayTrdBusinessService;
import com.cifpay.lc.bankadapter.universal.service.impl.unionpay.UnionPayStategyService;
import com.cifpay.lc.bankadapter.universal.tool.BusinessTradeLock;
import com.cifpay.lc.constant.ReturnCode;
import com.cifpay.lc.core.db.pojo.UnionPayTrdMain;
import com.cifpay.lc.core.exception.BankAdapterException;

/**
 * 银联查询交易处理策略
 * 
 * @author linql
 *
 */
@Component
public class QueryTradeCifTradeStrategy implements ITradeStrategy {
	private static final Logger LOGGER = LoggerFactory.getLogger(QueryTradeCifTradeStrategy.class);
	@Autowired
	private UnionPayTrdBusinessService businessService;
	@Autowired
	private BusinessTradeLock lock;
	@Autowired
	private UnionPayStategyService stategyService;

	@Override
	public AbsBusinessResult process(AbsTradeParam tradeParam) throws Exception {
		LOGGER.info("QueryCifTradeStrategy 开始处理请求...");
		// 1. 检查参数
		new QueryTradeCifValidate().validate(tradeParam);
		QueryTradeCifParam param = (QueryTradeCifParam) tradeParam;
		Long lcId = param.getLcId();
		GeneralTradeResult reslut = null;
		UnionPayTrdMain tm = null;
		try {
			// 2. 锁定记录
			lock.lockLcId(lcId);
			// 3. 检查状态
			tm = businessService.selectByTxnKey(param.getOrderId(), param.getTxnTime());
			if (tm != null && param.isOnline()) {
				// 调用联机查询策略
				if (TradeConstant.TRADE_CONFIG.DINGGOU_BIZTYPE.equals(tm.getBizType())) {
					param.setTxnId(TradeConstant.TRADE_CONFIG.TRADE_UNIONPAY_DINGGOU_QUERY);
				} else if (TradeConstant.TRADE_CONFIG.WU_BIZTYPE.equals(tm.getBizType())) {
					param.setTxnId(TradeConstant.TRADE_CONFIG.TRADE_UNIONPAY_WU_QUERY);
				} else {
					throw new BankAdapterException(ReturnCode.CORE_BA_UNDEFINE_N105999, "错误的BizType类型");
				}

				reslut = (GeneralTradeResult) stategyService.tradeDealAdapter(param);

				if (TradeConstant.TRADE_CONFIG.TRADE_RESULT_SUCCEED_0.equals(reslut.getQueryTradeResult())
						|| TradeConstant.TRADE_CONFIG.TRADE_RESULT__NO_RECORD.equals(reslut.getQueryTradeResult())) {
					// 查询交易成功,更新同步结果main表
					UnionPayTrdMain main = new UnionPayTrdMain();
					String respCode = StringUtils.isEmpty(reslut.getResultMap().get("origRespCode"))
							? reslut.getResultMap().get("respCode") : reslut.getResultMap().get("origRespCode");
					String respMsg = StringUtils.isEmpty(reslut.getResultMap().get("origRespMsg"))
							? reslut.getResultMap().get("respMsg") : reslut.getResultMap().get("origRespMsg");
					main.setAsynRespCode(respCode);
					main.setAsynRespMsg(respMsg);
					if (TradeConstant.TRADE_CONFIG.TRADE_RESULT_SUCCEED_0.equals(reslut.getQueryTradeResult())) {
						main.setAsynTradeResult(reslut.getTradeResult());
						main.setSyncTradeResult(reslut.getTradeResult());
					} else {
						// 记录不存在，结果设置为失败
						main.setAsynTradeResult(TradeConstant.TRADE_CONFIG.TRADE_RESULT_FAIL_1);
						main.setSyncTradeResult(TradeConstant.TRADE_CONFIG.TRADE_RESULT_FAIL_1);
					}
					main.setLastUpdTime(new Date());
					main.setOrderId(param.getOrderId());
					main.setTxnTime(param.getTxnTime());
					main.setRtnSettleDate(reslut.getResultMap().get("settleDate"));
					businessService.updateMainAsynResult(main);
				} else {
					LOGGER.error("####查询交易失败！####");
				}

			} else {
				if (tm != null) {
					// 直接返回本地结果。
					LOGGER.info("直接返回本地结果");
					reslut = new GeneralTradeResult();
					reslut.setTradeResult(tm.getAsynTradeResult());
					reslut.setQueryId(tm.getRtnQueryId());
					reslut.setFlowId(tm.getFlowId());
					
					HashMap<String, String> map = new HashMap<String,String>();
					map.put("orderId", tm.getOrderId());
					map.put("txnTime", tm.getTxnTime());
					map.put("queryId", tm.getRtnQueryId());
					map.put("respCode", tm.getAsynRespCode());
					map.put("respMsg", tm.getAsynRespMsg());
					reslut.setResultMap(map);
					
					reslut.setQueryTradeResult("0");
				} else {
					LOGGER.info("记录不存在");
					reslut = new GeneralTradeResult();
					reslut.setSysReturnCode(ReturnCode.CORE_BA_NORECORD_EXCEPTION_N105014);
					reslut.setResultDesc("查询记录不存在");
					
					HashMap<String, String> map = new HashMap<String,String>();
					map.put("orderId", param.getOrderId());
					map.put("txnTime", param.getTxnTime());
					reslut.setResultMap(map);
					
					reslut.setQueryTradeResult("2");
				}
			}
		} catch (Exception e) {
			LOGGER.error("####QueryCifTradeStrategy处理错误####：{}", e.getMessage());
			// 失败，查询次数加一
			if (tm != null) {
				UnionPayTrdMain temp = new UnionPayTrdMain();
				temp.setOrderId(param.getOrderId());
				temp.setTxnTime(param.getTxnTime());
				businessService.updateMainQueryTimes(temp);
			}

			throw e;
		} finally {
			lock.unLockLcId(lcId);
		}

		return reslut;
	}

	@Override
	public String getStrategyType() {
		return QueryTradeCifParam.class.getName();
	}

}
