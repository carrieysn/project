package com.cifpay.lc.constant;

/**
 * core层【标准银信证业务】之下的银行适配接口环节的返回码，范围是105xxx，其中使用负数表示失败（错误码），即 -105xxx 用于表示失败。
 * 
 * 在此文件中定义的返回码常量名必须全部以“CORE_BA_”开头，例如：
 * 
 * @{@code int CORE_BA_PARAM_EXCEPTION_N105001 = -105001; }
 * 
 * 
 *
 */
interface CoreLcBankAdapterReturnCode {
	// 在此文件中定义的常量名全部以“CORE_BA_”开头
	// 交易处理正常 105000-105010
	int CORE_BA_TRADE_SUCCESS_105000 = 105000;
	int CORE_BA_TRADE_SUCCESS_105001 = 105001;
	
	// 接收的参数异常返回码
	int CORE_BA_PARAM_EXCEPTION_N105011 = -105011;
	// 业务处理中异常
	int CORE_BA_DEALING_EXCEPTION_N105012 = -105012;
	// 重复交易异常
	int CORE_BA_REPEAT_EXCEPTION_N105013 = -105013;
	// 无相关记录异常
	int CORE_BA_NORECORD_EXCEPTION_N105014 = -105014;


	// *******************xxxxStategy异常1051XX**************************//
	// 不被支持的交易类型
	int CORE_BA_NONSUPPORT_TRADETYPE_EXCEPTION_N105101 = -105101;

	// *****************银行具体处理实现 xxxxDeal 异常1054XX**************//
	
	// token错误
	int CORE_BA_TOKEN_EXCEPTON_N105402 = -105402;

	//*****************银联相应具体异常码1056xx，已定义在数据库*******************//
	
	
	//验签失败
	int CORE_BA_RESULT_EXCEPTON_N105501 = -105501;
	
	// 一般异常
	int CORE_BA_UNDEFINE_N105999 = -105999;
}
