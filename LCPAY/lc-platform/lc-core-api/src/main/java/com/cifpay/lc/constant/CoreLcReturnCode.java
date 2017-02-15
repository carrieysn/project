package com.cifpay.lc.constant;

/**
 * gateway层的返回码，范围是106xxx，其中使用负数表示失败（错误码），即 -106xxx 用于表示失败。
 * <p>
 * 在此文件中定义的返回码常量名必须全部以“CORE_PRE_LC 或 CORE_LC”开头，例如：
 *
 * @{@code int CORE_MSG_NOT_EXISTS = -106001; }
 */
public interface CoreLcReturnCode {

    /**
     * 传入参数错误
     */
    int CORE_LC_PARAMETER_INVALID = -106401;

    /**
     * 没有操作权限
     */
    int CORE_LC_AUTHENTICATION_FAILURE = -106401;

}
