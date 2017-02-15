package com.cifpay.lc.constant.enums;

/**
 * 付款方式
 */
public enum PayMethod {

    NORMAL("BANK", "银行开证", OpenChannel.BANK),
    UNION_CREDIT("UNION_CREDIT", "银联信用卡开证", OpenChannel.UNION),
    UNION_DEPOSIT("UNION_DEPOSIT", "银联储蓄卡开证", OpenChannel.UNION);

    private String code;
    private String desc;
    private OpenChannel openChannel;//支付渠道

    private PayMethod(String code, String desc, OpenChannel openChannel) {
        this.code = code;
        this.desc = desc;
        this.openChannel = openChannel;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public OpenChannel getOpenChannel() {
        return openChannel;
    }

    public static PayMethod parse(String code) {
        PayMethod channel = null;
        if (NORMAL.code.equals(code)) {
            channel = NORMAL;
        } else if (UNION_CREDIT.code.equals(code)) {
            channel = UNION_CREDIT;
        } else if (UNION_DEPOSIT.code.equals(code)) {
            channel = UNION_DEPOSIT;
        }

        return channel;
    }
}
