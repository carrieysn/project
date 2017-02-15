package com.cifpay.lc.constant;

/**
 * core层【标准银信证业务】之下的银行适配接口环节的返回码，范围是107xxx，其中使用负数表示失败（错误码），即 -108xxx 用于表示失败。
 * <p>
 * 在此文件中定义的返回码常量名必须全部以“PRODUCT_”开头，例如：
 *
 * @{@code int PRODUCT_EXCEPTION_N108001 = -108001; }
 */
interface CoreProductReturnCode {

    /**
     * 产品不支持当前币种
     */
    int PRODUCT_CURRENCY_NOT_SUPPORTED = -107002;

    /**
     * 支付金额过小
     */
    int PRODUCT_AMOUNT_MIN_NOT_SUPPORTED = -107003;

    /**
     * 支付金额过大
     */
    int PRODUCT_AMOUNT_MAX_NOT_SUPPORTED = -107004;

    /**
     * 标准银信证不可以修改到期时间
     */
    int PRODUCT_STANDARD_CAN_NOT_MODIFY = -107005;

    /**
     * 付款人信息不可以为空
     */
    int PRODUCT_PAY_ACCOUNT_CAN_NOT_EMPTY = -107006;
    /**
     * 产品不支持当前付款人类型
     */
    int PRODUCT_PAYER_TYPE_NOT_EMPTY = -107007;
    /**
     * 银信证产品不支持批量开证
     */
    int CORE_STD_LC_PRODUCT_NOT_SUPPORTED_MULTIPLE_OPEN = -107008;
}
