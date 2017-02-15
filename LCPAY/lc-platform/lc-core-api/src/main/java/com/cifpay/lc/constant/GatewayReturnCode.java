package com.cifpay.lc.constant;

/**
 * gateway层的返回码，范围是101xxx，其中使用负数表示失败（错误码），即 -101xxx 用于表示失败。
 * <p>
 * 在此文件中定义的返回码常量名必须全部以“GW_”开头，例如：
 *
 * @{@code int GW_MERCHANT_ID_NOT_EXISTS = -101001; }
 */
interface GatewayReturnCode {
    // 在此文件中定义的常量名全部以“GW_”开头

    /**
     * 非法的Gateway请求
     */
    int GW_BAD_REQUEST = -101000;

    /**
     * 输入参数校验不通过
     */
    int GW_GENERIC_VALIDATION_REJECTED = -101001;

    /**
     * 请求的merId不存在
     */
    int GW_MER_ID_NOT_EXISTS = -101002;

    /**
     * 商户未开通
     */
    int GW_MERCHANT_PENDING = -101003;

    /**
     * 验证签名失败
     */
    int GW_MER_REQUEST_SIGN_INVALID = -101004;

    /**
     * 银联付款失败
     */
    int GW_UNION_ERROR = -101005;


    /**
     * 核心业务暂时不可用
     */
    int GW_CORE_BUSINESS_UNAVAILABLE = -101998;

    /**
     * Gateway层未知错误
     */
    int GW_UNKOWN_ERROR = -101999;
}
