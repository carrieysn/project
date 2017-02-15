package com.cifpay.lc.api.gateway.bank;

import com.cifpay.lc.api.BusinessOutput;
import com.cifpay.lc.api.BusinessService;
import com.cifpay.lc.bankadapter.api.output.GeneralTradeResult;

import java.util.HashMap;

public interface PaymentSuccessService extends BusinessService {

    /**
     * 银联回调统一处理
     *
     * @param encoding
     * @param map
     */
    void GeneralCallBack(String encoding, HashMap<String, String> map);

    /**
     * 银联回调银信证处理 & 定时任务回调
     *
     * @param result
     * @return
     */
    BusinessOutput<String> cifpayCallBack(GeneralTradeResult result);

    /**
     * 储蓄卡开卡后台回调
     *
     * @param encoding
     * @param map
     */
    void openCardBack(String encoding, HashMap<String, String> map);

    /**
     * 储蓄卡开卡前台回调
     *
     * @param encoding
     * @param map
     * @return
     */
    BusinessOutput<Boolean> openCardFront(String encoding, HashMap<String, String> map);
}
