package com.cifpay.lc.constant.merchant;

import com.cifpay.lc.constant.enums.LcTranStatus;

/**
 * 返回给商户的交易状态
 */
public enum MerchantTranStatus {
    UNKNOWN("UNKNOWN"),
    SUCCESS("SUCCESS"),
    FAIL("FAIL");

    private String merchantTranStatus;

    private MerchantTranStatus(String tranStatus) {
        this.merchantTranStatus = tranStatus;
    }

    public String getMerchantTranStatus() {
        return merchantTranStatus;
    }

    public static MerchantTranStatus parse(LcTranStatus lcTranStatus) {
        MerchantTranStatus merchantTranStatus = MerchantTranStatus.UNKNOWN;

        if (lcTranStatus != null) {
            switch (lcTranStatus) {
                case SUCCESS: {
                    merchantTranStatus = MerchantTranStatus.SUCCESS;
                    break;
                }
                case FAIL: {
                    merchantTranStatus = MerchantTranStatus.FAIL;
                    break;
                }
            }
        }

        return merchantTranStatus;
    }
}
