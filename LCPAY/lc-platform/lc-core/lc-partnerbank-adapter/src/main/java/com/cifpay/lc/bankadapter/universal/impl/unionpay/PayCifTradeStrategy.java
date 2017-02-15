package com.cifpay.lc.bankadapter.universal.impl.unionpay;

import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cifpay.lc.bankadapter.api.constant.TradeConstant;
import com.cifpay.lc.bankadapter.api.input.AbsTradeParam;
import com.cifpay.lc.bankadapter.api.input.unionpay.PayCifParam;
import com.cifpay.lc.bankadapter.api.output.AbsBusinessResult;
import com.cifpay.lc.bankadapter.api.output.GeneralTradeResult;
import com.cifpay.lc.bankadapter.universal.ITradeStrategy;
import com.cifpay.lc.bankadapter.universal.service.UnionPayTrdBusinessService;
import com.cifpay.lc.bankadapter.universal.service.impl.unionpay.UnionPayStategyService;
import com.cifpay.lc.bankadapter.universal.tool.BusinessTradeLock;
import com.cifpay.lc.constant.ReturnCode;
import com.cifpay.lc.core.db.pojo.UnionPayTrdMain;
import com.cifpay.lc.core.exception.BankAdapterException;
import com.cifpay.lc.core.uid.LcTrdMainIdWorker;

/**
 * 银联支付处理策略
 * 
 * @author linql
 *
 */
@Component
public class PayCifTradeStrategy implements ITradeStrategy {
	private static final Logger LOGGER = LoggerFactory.getLogger(PayCifTradeStrategy.class);
	@Autowired
	private UnionPayTrdBusinessService businessService;
	@Autowired
	private BusinessTradeLock lock;
	@Autowired
	private LcTrdMainIdWorker idWork;
	@Autowired
	private UnionPayStategyService stategyService;

	@Override
	public AbsBusinessResult process(AbsTradeParam tradeParam) throws Exception {
		LOGGER.info("PayCifTradeStrategy 开始处理请求...");
		// 1. 检查参数
		new PayCifValidate().validate(tradeParam);
		PayCifParam param = (PayCifParam) tradeParam;
		Long lcId = param.getLcId();
		GeneralTradeResult reslut = null;
		try {
			// 2. 锁定记录
			lock.lockLcId(lcId);
			// 3. 检查状态
			UnionPayTrdMain tm = businessService.selectBySelective(tradeParam);
			Long businessId = null;
			int i = stategyService.checkState(tm);
			if (i == 1 || i == -1) {
				// 3.1 查询原预授权交易
				UnionPayTrdMain authTm = stategyService.getAuth(param.getOrigOryId());
				boolean isAuth = stategyService.checkAuth(authTm);
				if (!isAuth) {
					throw new BankAdapterException(ReturnCode.CORE_BA_NORECORD_EXCEPTION_N105014, "无成功的预授权记录！");
				} 
				// -1（新业务）1（明确失败记录）进行交易操作，重新进行冻结
				if (i == -1) {
					// i=-1，即新业务，插入业务表
					UnionPayTrdMain tmp = new UnionPayTrdMain();
					businessId = idWork.nextId();// 新业务，生成业务ID
					tmp.setBusinessId(businessId);
					tmp.setLcId(param.getLcId());
					tmp.setTxnId(param.getTxnId());
					tmp.setTxnType(param.getTxnType());
					tmp.setTxnSubType(param.getTxnSubType());
					tmp.setBizType(param.getBizType());
					tmp.setMerId(param.getMerId());
					tmp.setOrderId(param.getOrderId());// 生成新ID
					tmp.setTxnTime(param.getTxnTime());
					tmp.setTxnAmt(param.getTxnAmt());
					tmp.setChannelType(param.getChannelType());
					//tmp.setMerId(UnionPaySettingUtil.getMerId(param.getBizType()));
					tmp.setSubMerId(param.getSubMerId());
					tmp.setUserId(param.getUserId());
					tmp.setReqReserved(param.getReqReserved());

					tmp.setSyncTradeResult(TradeConstant.TRADE_CONFIG.TRADE_RESULT_UNKNOWN_2);// 2同步交易状态未知
					tmp.setInsertTime(new Date());// 插入时间

					LOGGER.info("无交易记录，往业务交易表插入记录!");
					businessService.insertMainSelective(tmp);
				} else {
					// i==1（明确失败记录）
					UnionPayTrdMain tmp = new UnionPayTrdMain();
					businessId = tm.getBusinessId();
					tmp.setBusinessId(businessId);
					
					BeanUtils.copyProperties(param, tmp);
					
					tmp.setOrderId(param.getOrderId());// 生成新ID
					tmp.setTxnTime(param.getTxnTime());
					tmp.setSyncTradeResult(TradeConstant.TRADE_CONFIG.TRADE_RESULT_UNKNOWN_2);// 2同步交易状态未知
					tmp.setAsynTradeResult("9");
					businessService.updateMainByPrimaryKeySelective(tmp);
				}

				// 4.进行联机交易操作
				param.setBusinessId(businessId);
				reslut = (GeneralTradeResult) stategyService.tradeDealAdapter(param);

				if (reslut != null) {
					// 更新交易业务表
					UnionPayTrdMain target = new UnionPayTrdMain();
					Map<String, String> resultMap = reslut.getResultMap();
					String syncRespCode = (String) resultMap.get("respCode");
					String syncRespMsg = (String) resultMap.get("respMsg");
					target.setSyncRespCode(syncRespCode);
					target.setSyncRespMsg(syncRespMsg);
					target.setSyncTradeResult(reslut.getTradeResult());// 交易结果
					target.setRtnQueryId(reslut.getQueryId());
					target.setFlowId(reslut.getFlowId());
					target.setBusinessId(businessId);
					LOGGER.info("联机交易结果，更新返回结果!");
					businessService.updateMainResult(target);
				}
			} else {
				// 成功或者状态未明，不进行交易操作
				throw new BankAdapterException(ReturnCode.CORE_BA_REPEAT_EXCEPTION_N105013, "【重复交易】交易已成功或者处理中");
			}
		} catch (Exception e) {
			LOGGER.error("####PayCifTradeStrategy处理错误####：{}", e.getMessage());
			throw e;
		} finally {
			lock.unLockLcId(lcId);
		}

		return reslut;
	}

	@Override
	public String getStrategyType() {
		return PayCifParam.class.getName();
	}

}
