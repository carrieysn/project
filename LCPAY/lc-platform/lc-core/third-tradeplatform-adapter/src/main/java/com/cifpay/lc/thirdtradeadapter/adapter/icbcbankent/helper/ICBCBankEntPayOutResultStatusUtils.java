package com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.helper;

import org.springframework.util.StringUtils;

import com.cifpay.lc.thirdtradeadapter.api.constant.ICBCBankTransactionResultStatus;

/**
 * 
 *
 */
public abstract class ICBCBankEntPayOutResultStatusUtils {
	public static ICBCBankTransactionResultStatus mappingTransactionResultStatus(String bankTxnResultCode) {
		ICBCBankTransactionResultStatus txnStatus;

		// 0：提交成功,等待银行处理
		// 1：授权成功, 等待银行处理
		// 2：等待授权
		// 3：等待二次授权
		// 4：等待银行答复
		// 5：主机返回待处理
		// 6：被银行拒绝
		// 7：处理成功
		// 8：指令被拒绝授权
		// 9：银行正在处理
		// 10：预约指令
		// 11：预约取消

		if ("7".equals(bankTxnResultCode)) {
			// 交易成功
			txnStatus = ICBCBankTransactionResultStatus.BANK_SUCCESS;
		} else if (!StringUtils.hasText(bankTxnResultCode) || "6".equals(bankTxnResultCode)
				|| "8".equals(bankTxnResultCode) || "11".equals(bankTxnResultCode)) {
			// 交易失败
			txnStatus = ICBCBankTransactionResultStatus.BANK_FAILED;
		} else if (StringUtils.hasText(bankTxnResultCode)) {
			// 交易结果处理中
			txnStatus = ICBCBankTransactionResultStatus.BANK_PROCESSING;
		} else {
			// 交易结果不明确
			txnStatus = ICBCBankTransactionResultStatus.RESULT_UNCERTAIN;
		}

		return txnStatus;
	}
}
