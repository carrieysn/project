package com.cifpay.lc.bankadapter.unionpay.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.cifpay.lc.bankadapter.api.IBankTradeService;
import com.cifpay.lc.bankadapter.api.constant.TradeConstant;
import com.cifpay.lc.bankadapter.api.input.AbsTradeParam;
import com.cifpay.lc.bankadapter.api.input.unionpay.OpenCardCallBackCifParam;
import com.cifpay.lc.bankadapter.api.output.GeneralTradeResult;
import com.cifpay.lc.bankadapter.unionpay.constant.UnionPayConfig;
import com.cifpay.lc.bankadapter.unionpay.util.AcpService;
import com.cifpay.lc.bankadapter.unionpay.util.DataUtil;
import com.cifpay.lc.bankadapter.unionpay.util.LogUtil;
import com.cifpay.lc.bankadapter.unionpay.util.ThreeDESUtil;
import com.cifpay.lc.bankadapter.unionpay.util.UnionPaySettingUtil;
import com.cifpay.lc.bankadapter.universal.IBankDeal;
import com.cifpay.lc.constant.ReturnCode;
import com.cifpay.lc.core.db.dao.AdminLcMerchantDao;
import com.cifpay.lc.core.db.dao.LcTrdUnionPayTokenDao;
import com.cifpay.lc.core.db.dao.TrdCodeDescDao;
import com.cifpay.lc.core.db.pojo.AdminCredentials;
import com.cifpay.lc.core.db.pojo.AdminLcMerchant;
import com.cifpay.lc.core.db.pojo.TrdCodeDesc;
import com.cifpay.lc.core.db.pojo.UnionPayTrdToken;
import com.cifpay.lc.core.exception.BankAdapterException;

/**
 * 
 * @author Administrator 如果返回结果为null或者异常，应用层不用处理
 */
@Component
public class OpenCardResponseDeal implements IBankDeal {
	private static Logger LOGGER = LoggerFactory.getLogger(OpenCardResponseDeal.class);
	@Autowired
	IBankTradeService tradeService;

	@Autowired
	private LcTrdUnionPayTokenDao lcTrdUnionPayTokenDao;

	@Autowired
	private TrdCodeDescDao trdCodeDescDao;

	@Autowired
	private AdminLcMerchantDao adminLcMerchantDao;

	public GeneralTradeResult bankDeal(AbsTradeParam tradeParam) throws Exception {
		try {
			OpenCardCallBackCifParam params = (OpenCardCallBackCifParam) tradeParam;
			String encoding = params.getEncoding();
			HashMap<String, String> reqParam = params.getReqParam();
			LOGGER.info("OpenCardResponseDeal接收后台通知开始");
			LogUtil.printRequestLog(reqParam);

			Map<String, String> valideData = null;
			if (null != reqParam && !reqParam.isEmpty()) {
				Iterator<Entry<String, String>> it = reqParam.entrySet().iterator();
				valideData = new HashMap<String, String>(reqParam.size());
				while (it.hasNext()) {
					Entry<String, String> e = it.next();
					String key = (String) e.getKey();
					String value = (String) e.getValue();
					value = new String(value.getBytes(encoding), encoding);
					valideData.put(key, value);
				}
			}
			if (null == valideData) {
				throw new Exception("参数为空！");
			}
			// 重要！验证签名前不要修改reqParam中的键值对的内容，否则会验签不过
			if (!AcpService.validate(valideData, encoding)) {
				LOGGER.info("验证签名结果[失败].");
				// 验签失败，需解决验签问题
				throw new BankAdapterException(ReturnCode.CORE_BA_RESULT_EXCEPTON_N105501, "验证签名结果[失败].");
			} else {
				LOGGER.info("验证签名结果[成功].");

				// 交易成功，更新商户订单状态
				String asynRespCode = valideData.get("respCode");
				String phoneNo = null;
				String accNo = "";
				String encryptionAccNo = "";
				// 将返回码与系统码进行转换
				String asynTradeResult = UnionPaySettingUtil.transRespCode(asynRespCode);
				if (TradeConstant.TRADE_CONFIG.TRADE_RESULT_SUCCEED_0.equals(asynTradeResult)) {
					// 保存token相关信息
					String tokenStr = valideData.get("tokenPayData");
					// 透传信息例：subMerId=100001#accNo=138000138000
					String reqReserved = valideData.get("reqReserved");
					String subMerId = "";

					if (!DataUtil.isEmpty(reqReserved)) {
						String[] paramArray = reqReserved.split("#");
						subMerId = paramArray[0].split("=")[1];
						accNo = paramArray[1].split("=")[1];
						encryptionAccNo = ThreeDESUtil.desEncryption(accNo);
					}
					String resultToken = tokenStr.substring(1, tokenStr.length() - 1);
					Map<String, String> tokenMap = new HashMap<String, String>();
					String[] arrays = resultToken.split("&");
					for (int i = 0; i < arrays.length; i++) {
						String object = arrays[i];
						String[] param = object.split("=");
						tokenMap.put(param[0], param[1]);
					}
					UnionPayTrdToken trdToken = new UnionPayTrdToken();
					trdToken.setMerId(subMerId);// 设置 二级商户
					trdToken.setToken(ThreeDESUtil.desEncryption(tokenMap.get("token")));
					trdToken.setTokenBegin(tokenMap.get("tokenBegin"));
					trdToken.setTokenEnd(tokenMap.get("tokenEnd"));
					trdToken.setTokenLevel(tokenMap.get("tokenLevel"));
					trdToken.setTokenType(tokenMap.get("tokenType"));
					trdToken.setTrId(tokenMap.get("trId"));
					trdToken.setAccNo(encryptionAccNo);

					Map<String, String> queryMap = new HashMap<String, String>();
					queryMap.put("merId", subMerId);
					queryMap.put("accNo", encryptionAccNo);
					UnionPayTrdToken queryToken = lcTrdUnionPayTokenDao.selectByPrimaryKey(queryMap);
					if (queryToken == null) {
						// 不存在则插入
						lcTrdUnionPayTokenDao.insertSelective(trdToken);
					} else {
						// 存在则根据merId accNo 更新
						lcTrdUnionPayTokenDao.updateTokenResult(trdToken);
					}

					// 解析出手机号
					AdminLcMerchant merChant = adminLcMerchantDao.selectMerchantInfos(subMerId);
					if (null == merChant) {
						LOGGER.info("二级商户信息不能为空");
						throw new BankAdapterException(ReturnCode.CORE_BA_PARAM_EXCEPTION_N105011, "二级商户信息不能为空.");
					}
					String customerInfo = valideData.get("customerInfo");
					AdminCredentials adminCredentials = merChant.getAdminCredentials();
					String certPath = UnionPayConfig.cifpayCert_path + adminCredentials.getCreFileName();// 私钥证书
					String certPwd = ThreeDESUtil.decDecrypt(adminCredentials.getCrePassword());
					Map<String, String> map = AcpService.parseCustomerInfo4Multiple(customerInfo, encoding, certPath,
							certPwd);
					phoneNo = map.get("phoneNo");
					if (!StringUtils.isEmpty(phoneNo)) {
						phoneNo = phoneNo.trim();
					} else {
						phoneNo = " IS NULL";
					}
				}

				GeneralTradeResult result = new GeneralTradeResult();
				result = transToSysParam(result, asynRespCode);
				result.setTradeResult(asynTradeResult);
				valideData.put("phoneNo", phoneNo);
				valideData.put("accNo", accNo);
				result.setResultMap(valideData);

				return result;
			}
		} catch (Exception e) {
			LOGGER.error("OpenCardResponseDeal错误：", e);
			GeneralTradeResult result = new GeneralTradeResult();
			result.setTradeResult("1");// 设置失败
			result.setResultDesc("绑定银行卡失败：" + e.getMessage());
			return result;
		}
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

	@Override
	public String getBankCode() {
		// TODO Auto-generated method stub
		return TradeConstant.TRADE_CONFIG.TRADE_UNION_PAY
				+ TradeConstant.TRADE_CONFIG.TRADE_UNIONPAY_OPEN_CARD_RESPONSE;
	}
}
