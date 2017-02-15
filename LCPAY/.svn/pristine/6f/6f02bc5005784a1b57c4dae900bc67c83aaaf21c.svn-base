package com.cifpay.lc.constant;

/**
 * core层【标准银信证业务】公共的返回码，范围是103xxx，其中使用负数表示失败（错误码），即 -103xxx 用于表示失败。
 * -103 1xx 参数验证错误
 * -103 2xx 银信证已失效、已成功
 * -103 3xx 业务处理失败
 * -103 4xx 调用其他模块出错
 * <p>
 * 在此文件中定义的返回码常量名必须全部以“CORE_STD_”开头，例如：
 *
 * @{@code int CORE_STD_LC_NOT_EXISTS = -103001; }
 */
interface CoreStandardLcReturnCode {
    // 在此文件中定义的常量名全部以“CORE_STD_”开头

    /**
     * 银信证记录不存在
     */
    int CORE_STD_LC_NOT_EXISTS = -103101;
    /**
     * 银信证预开证记录不存在
     */
    int CORE_STD_PRE_LC_NOT_EXISTS = -103102;
    /**
     * 银信证类型不存在
     */
    int CORE_STD_LC_TYPE_NOT_EXISTS = -103103;
    /**
     * 银信证银行代码不存在
     */
    int CORE_STD_LC_BANK_NOT_EXISTS = -103104;
    /**
     * 银信证产品不存在
     */
    int CORE_STD_LC_PRODUCT_NOT_EXISTS = -103105;
    /**
     * 银信证批次不存在
     */
    int CORE_STD_LC_BATCH_NOT_EXISTS = -103106;


    /**
     * 银信证批次已部分支付成功
     */
    int CORE_STD_LC_BATCH_PARTIAL_SUCCESS = -103108;
    /**
     * 履约金额超限
     */
    int CORE_STD_LC_APPOINTMENT_AMT_EXCEED = -103109;
    /**
     * 金额不正确
     */
    int CORE_STD_LC_AMT_INVALID = -103110;
    /**
     * 币种不正确
     */
    int CORE_STD_LC_CURRENCY_INVALID = -103111;
    /**
     * 收证有效时间不正确
     */
    int CORE_STD_LC_DATE_INVALID_RECV = -103112;
    /**
     * 履约有效时间不正确
     */
    int CORE_STD_LC_DATE_INVALID_APPOINTMENT = -103113;
    /**
     * 申请解付有效时间不正确
     */
    int CORE_STD_LC_DATE_INVALID_APPLY = -103114;
    /**
     * 执行解付有效时间不正确
     */
    int CORE_STD_LC_DATE_INVALID_TRANSFER = -103115;


    /**
     * 开证记录不存在
     */
    int CORE_STD_LC_OPEN_NOT_EXISTS = -103116;

    /**
     * 收证记录不存在
     */
    int CORE_STD_LC_RECV_NOT_EXISTS = -103117;

    /**
     * 履约记录不存在
     */
    int CORE_STD_LC_APPOINTMENT_NOT_EXISTS = -103118;

    /**
     * 履约记录与银信证不匹配
     */
    int CORE_STD_LC_APPOINTMENT_NOT_EQUAL_LCID = -103119;

    /**
     * 申请解付记录不存在
     */
    int CORE_STD_LC_APPLY_NOT_EXISTS = -103120;

    /**
     * 执行解付记录不存在
     */
    int CORE_STD_LC_TRANSFER_NOT_EXISTS = -103121;

    /**
     * 申请解付正在处理中
     */
    int CORE_STD_LC_APPLY_IS_INPROCESS_EXISTS = -103122;


    /**
     * 调用银行接口交易抛出异常
     */
    int CORE_STD_BANK_ITF_EXCEPTION = -103123;

    /**
     * 调用银行接口交易失败
     */
    int CORE_STD_BANK_ITF_RESULT_FAIL = -103124;

    /**
     * 调用银行接口交易结果不确定
     */
    int CORE_STD_BANK_ITF_RESULT_UNKNOWN = -103125;

    /**
     * 调用银行接口交易已经成功完成
     */
    int CORE_STD_BANK_ITF_RESULT_SUCCESS = -103126;


    /**
     * 银信证状态不正确
     */
    int CORE_STD_LC_STATUS_CHECK_UNPASSED = -103127;


    /**
     * 请填写失效类型
     */
    int CORE_STD_LC_INVALIDATE_TYPE_MISS = -103128;
    /**
     * 不支持的失效方式
     */
    int CORE_STD_INVALIDATE_UNSUPPORT_TYPE = -103129;
    /**
     * 只有多次解付银信证可以对履约进行失效
     */
    int CORE_STD_LC_INVALID_ONLY_MULTIPLE_SUPPORTED = -103130;

    /**
     * 当前节点不支持自动流程
     */
    int CORE_STD_UNSUPPORT_AUTOFLOW = -103131;

    /**
     * 进行自动流程出错
     */
    int CORE_STD_AUTOFLOW_ERROR = -103132;


    /**
     * 短信验证码不正确
     */
    int CORE_STD_SMS_CODE_INCORRECT = -103133;
    /**
     * 履约验证码错误
     */
    int CORE_STD_APPOINTMENT_CODE_INVALID = -103134;


    /**
     * 不支持当前类型的履约操作
     */
    int CORE_STD_UNSUPPORT_APPOINTMENT = -103135;
    /**
     * 不支持当前类型的申请解付操作
     */
    int CORE_STD_UNSUPPORT_APPLY = -103136;


    /**
     * 未找到对应的银联二级商户号
     */
    int CORE_STD_SECOND_MER_NOT_EXIST = -103137;


    /**
     * 请填写履约ID
     */
    int CORE_STD_LC_APPOINTMENT_ID_MISS = -103138;
    /**
     * 不支持的支付方式
     */
    int CORE_STD_UNSUPPORT_OPEN_TYPE = -103139;
    /**
     * 不支持的解付方式
     */
    int CORE_STD_UNSUPPORT_TRANSFER_TYPE = -103140;
    /**
     * 重复退款
     */
    int CORE_STD_REFUND_REPEAT = -103141;
    /**
     * 退款金额不正确
     */
    int CORE_STD_AMOUNT_INVALID = -103142;
    /**
     * 发送消息失败
     */
    int CORE_STD_SMS_SEND_ERROR = -103143;
    /**
     * 插入银信证数据失败
     */
    int CORE_STD_LC_INSERT_ERROR = -103144;
    /**
     * 异步通知商户失败
     */
    int CORE_STD_LC_ASYNCHRONOUS_FAIL = -103145;


    /**
     * 失效记录不存在
     */
    int CORE_STD_LC_INVALID_NOT_EXISTS = -103146;

    /**
     * 退款记录不存在
     */
    int CORE_STD_LC_REFUND_NOT_EXISTS = -103147;



    /**
     * 构建银联参数出错
     */
    int CORE_STD_UNION_PARAM_ERROR = -103401;
    /**
     * 银联回调处理出错
     */
    int CORE_STD_UNION_CALLBACK_ERROR = -103402;

    /**
     * 银信证已失效
     */
    int CORE_STD_LC_INVALID = -103201;
    /**
     * 履约有效期失效
     */
    int CORE_STD_LC_VALIDITY_FAIL_APPOINTMENT = -103202;

    /**
     * 申请解付有效期失效
     */
    int CORE_STD_LC_VALIDITY_FAIL_APPLY = -103203;

    /**
     * 解付有效期失效
     */
    int CORE_STD_LC_VALIDITY_FAIL_TRANSFER = -103204;
    /**
     * 银信证已支付成功
     */
    int CORE_STD_LC_ALREDAY_SUCCESS = -103205;


    /**
     * 开证失败
     */
    int CORE_STD_LC_OPEN_ERROR = -103301;
    /**
     * 收证失败
     */
    int CORE_STD_LC_RECV_ERROR = -103302;
    /**
     * 履约失败
     */
    int CORE_STD_APPOINTMENT_ERROR = -103303;
    /**
     * 部分履约失败
     */
    int CORE_STD_APPOINTMENT_PART_ERROR = -103304;
    /**
     * 展期失败
     */
    int CORE_STD_LC_DEFER_ERROR = -103305;
    /**
     * 申请解付失败
     */
    int CORE_STD_APPLY_ERROR = -103306;
    /**
     * 退款失败
     */
    int CORE_STD_LC_REFUND_ERROR = -103307;
    /**
     * 刹车失败
     */
    int CORE_STD_LC_SUSPEND_ERROR = -103308;
    /**
     * 执行解付失败
     */
    int CORE_STD_LC_TRANSFER_ERROR = -103309;
    /**
     * 批量开证失败
     */
    int CORE_STD_LC_BATCH_OPEN_ERROR = -103310;
    /**
     * 批量申请收款失败
     */
    int CORE_STD_LC_BATCH_APPLY_ERROR = -103311;
    /**
     * 失效失败
     */
    int CORE_STD_INVALIDATE_ERROR = -103312;
    /**
     * 履约失效失败
     */
    int CORE_STD_INVALIDATE_APPOINTMENT_ERROR = -103313;

    /**
     * 通用错误
     */
    int CORE_STD_LC_FAIL = -103999;
}
