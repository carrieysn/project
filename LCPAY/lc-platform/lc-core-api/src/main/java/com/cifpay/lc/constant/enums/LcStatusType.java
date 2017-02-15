package com.cifpay.lc.constant.enums;

/**
 * * 银信证状态： 00新建、10开证、20已收证、30已履约、31已展期、40已申请解付、41已刹车、50已执行解付、88已解付完成、90已失效
 *
 * @author sweet
 */
public enum LcStatusType {
    NEW("00", "新建"),
    ONPAYING("01", "付款处理中"),
    OPENED("10", "已开证"),
    RECIEVED("20", "已收证"),
    APPOINTMENT_DONE("30", "已履约"),
    APPOINTMENT_PART_DONE("31", "多次履约"),
    DEFER("32", "已展期"),
    APPLIED("40", "已申请解付"),
    SUSPEND("41", "已刹车"),
    TRANSFERED("50", "已执行解付"),
    SUCCESS("88", "已解付完成"),
    RETREAT("90", "已失效退回"),
    REFUND("90", "已原路退回"),
    PRE_INVALID("91", "预失效");
//    INVALIDED("99", "已失效");

    private String statusCode;
    private String statusDesc;

    private LcStatusType(String statusCode, String statusDesc) {
        this.statusCode = statusCode;
        this.statusDesc = statusDesc;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public static LcStatusType parse(String code) {
        LcStatusType lcStatus = null;
        if (NEW.statusCode.equals(code)) {
            lcStatus = NEW;
        } else if (OPENED.statusCode.equals(code)) {
            lcStatus = OPENED;
        } else if (RECIEVED.statusCode.equals(code)) {
            lcStatus = RECIEVED;
        } else if (APPOINTMENT_PART_DONE.statusCode.equals(code)) {
            lcStatus = APPOINTMENT_PART_DONE;
        } else if (APPOINTMENT_DONE.statusCode.equals(code)) {
            lcStatus = APPOINTMENT_DONE;
        } else if (DEFER.statusCode.equals(code)) {
            lcStatus = DEFER;
        } else if (APPLIED.statusCode.equals(code)) {
            lcStatus = APPLIED;
        } else if (SUSPEND.statusCode.equals(code)) {
            lcStatus = SUSPEND;
        } else if (TRANSFERED.statusCode.equals(code)) {
            lcStatus = TRANSFERED;
        } else if (SUCCESS.statusCode.equals(code)) {
            lcStatus = SUCCESS;
        } else if (RETREAT.statusCode.equals(code)) {
            lcStatus = RETREAT;
        }

        return lcStatus;
    }

    public static String getDesc(String code) {
        String strRet = "";
        if (NEW.statusCode.equals(code)) {
            strRet = NEW.statusDesc;
        } else if (OPENED.statusCode.equals(code)) {
            strRet = OPENED.statusDesc;
        } else if (RECIEVED.statusCode.equals(code)) {
            strRet = RECIEVED.statusDesc;
        } else if (APPOINTMENT_PART_DONE.statusCode.equals(code)) {
            strRet = APPOINTMENT_PART_DONE.statusDesc;
        } else if (APPOINTMENT_DONE.statusCode.equals(code)) {
            strRet = APPOINTMENT_DONE.statusDesc;
        } else if (DEFER.statusCode.equals(code)) {
            strRet = DEFER.statusDesc;
        } else if (APPLIED.statusCode.equals(code)) {
            strRet = APPLIED.statusDesc;
        } else if (SUSPEND.statusCode.equals(code)) {
            strRet = SUSPEND.statusDesc;
        } else if (TRANSFERED.statusCode.equals(code)) {
            strRet = TRANSFERED.statusDesc;
        } else if (SUCCESS.statusCode.equals(code)) {
            strRet = SUCCESS.statusDesc;
        } else if (RETREAT.statusCode.equals(code)) {
            strRet = RETREAT.statusDesc;
        }

        return strRet;
    }
}
