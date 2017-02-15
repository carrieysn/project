package com.cifpay.lc.constant;

import java.text.DecimalFormat;

/**
 * 业务常量类
 */
public class BizConstants {

    /**
     * 时间格式化格式
     */
    public static String DateFormat_std = "yyyy-MM-dd HH:mm:ss";

    public static DecimalFormat decimalFormat = new DecimalFormat("#");

    /**
     * 交易码 初始化证INIT，开证 OPEN，收证RECV，履约 APPOINTMENT，申请解付 APPLY，解付 TRANSFER，展期
     * DEFER，刹车 SUSPEND，定时任务 TIMERTASK, 原路退款REFUND
     */
    public enum LcTranCode {
        INIT("INIT"),
        OPEN("OPEN"),
        BATCH_OPEN("BATCH_OPEN"),
        RECV("RECV"),
        APPOINTMENT("APPOINTMENT"),
        APPLY("APPLY"),
        TRANSFER("TRANSFER"),
        DEFER("DEFER"),
        SUSPEND("SUSPEND"),
        INVALIDATE("INVALIDATE"),
        TIMERTASK("TIMERTASK"),
        REFUND("REFUND");

        private String tranCode;

        private LcTranCode(String tranCode) {
            this.tranCode = tranCode;
        }

        public String getTranCodeStr() {
            return tranCode;
        }
    }
}
