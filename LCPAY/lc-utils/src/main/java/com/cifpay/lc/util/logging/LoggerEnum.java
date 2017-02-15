package com.cifpay.lc.util.logging;

/**
 * Created by sweet on 16-12-23.
 */
public final class LoggerEnum {

    /**
     * dubbo context 传输requestId
     */
    public static final String DUBBO_CONTEXT_REQUEST_ID_KEY = "requestId";


    public static enum Scene {
        UNKNOWN("unknown", "未知"),
        VOID("void", "空"),

        PREOPENLC("preOpenLc", "预开证"),
        OPENLC("openLc", "开证"),
        RECVLC("recvLc", "收证"),
        APPOINTMENTLC("appointmentLc", "履约"),
        DEFERLC("deferLc", "展期"),
        APPLYLC("applyLc", "申请解付"),
        SUSPENDLC("suspendLc", "暂停"),
        TRANSFERLC("transferLc", "解付转账"),
        INVALIDATELC("invalidateLc", "失效"),
        REFUNDLC("refundLc", "退款"),

        BATCHINITLC("batchInitLc", "批量预开证"),
        BATCHOPENLC("batchOpenLc", "批量开证"),
        BATCHAPPLYLC("batchOpenLc", "批量申请解付"),
        BATCHTRANSFERLC("batchTransferLc", "批量执行解付"),

        SENDSMS("sendSms", "发短信"),
        SENDMESSAGE("sendMessage", "发送消息队列消息"),

        QUEUE_OPEN("queueOpen", "开证"),
        QUEUE_RECV("queueRecv", "收证"),
        QUEUE_APPLY("queueApply", "申请解付"),
        QUEUE_APPOINTMENT("queueAppointment", "履约"),
        QUEUE_TRANSFER("queueTransfer", "执行解付"),
        QUEUE_REFUND("queueRefund", "执行解付"),
        QUEUE_EXPIRY("queueExpiry", "执行解付"),

        NOTIFY_MERCH("notifyMerch", "通知商户"),

        BANK_FREEZE("bankFreeze","银行冻结"),
        BANK_UNFREEZE("bankUnfreeze","银行解冻"),
        BANK_PAY("bankPay","银行支付业务上送"),
        BANK_SC_PAY("bankSCPay","银行清算中心业务上送"),
        BANK_SYNC("bankSync","银行同步业务上送"),
        BANK_SC_SYNC("bankSCSync","银行结算中心同步业务上送"),

        UNION_OPEN("unionOpen","银联开证"),
        UNION_PAY("unionPay","银联执行解付"),
        UNION_EXPIRY("unionExpiry","银联失效"),
        UNION_QUERY("unionQuery","银联查询交易"),
        UNION_REFUND("unionRefund","银联退证"),
        UNION_SMS("unionSms","银联发送短信验证码"),
        UNION_OPEN_CARD("unionOpenCard","银联开通无跳转"),
        UNION_OPEN_CARD_CALLBACK("unionOpenCardCallBack","银联开通无跳转回调"),
        UNION_BACKRCV("unionBackRcv","银联后台通知"),

        GATEWAY("gateway", "场景"),;
        
        public String val;

        public String desc;

        private Scene(String val, String desc) {
            this.val = val;
            this.desc = desc;
        }
    }
}
