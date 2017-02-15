package com.cifpay.lc.gateway.testdata;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import org.junit.Test;

import com.cifpay.lc.util.LcMd5SignTool;
import com.cifpay.lc.util.DateUtil;

public class GenerateVersionBWithdrawReqDataTest {

    public static final String DATE_FORMAT = "yyyyMMdd HH:mm:ss";

    public static void main(String[] args) {
        System.out.println(new SimpleDateFormat(DATE_FORMAT).format(new Date()));
        System.out.println(UUID.randomUUID().toString().replace("-", ""));

        System.out.println(System.getProperty("user.dir"));
    }

    @Test
    public void testSign() {
        String privateKey = "11c9c951a5ad422d88ccbfafa6912d5e";

        Map<String, String> params = new LinkedHashMap<String, String>();
        params.put("requestId", UUID.randomUUID().toString().replace("-", ""));
        params.put("requestTime", DateUtil.format(new Date(), "yyyyMMddHHmmss"));
        params.put("merId", "1000001");
        // params.put("sign", "");
//		params.put("userCode", "1002");
        params.put("userCode", "angelshiqian04");
        params.put("orderId", "W" + String.valueOf(System.currentTimeMillis()));
        params.put("orderDesc", "提现测试");
        params.put("amount", "18"); // 金额单位为分
        params.put("currency", "CNY");
        params.put("payerBankCode", "ICBC");

        // 工行提供的测试用的付款账号4000023029200124946
        params.put("payerBankAcctNo", "4000023029200124946");

        params.put("payerMobile", "");
        // params.put("payeeBankCode", "PAB");
        params.put("payeeBankCode", "ICBC");

        // 工行提供的测试用的收款账号4000020829200148508
        params.put("payeeBankAcctNo", "4000020829200148508");
        // params.put("payeeBankAcctNo", "6210000000000000011");

        // 工行提供的测试用的收款人姓名（跟收款账号4000020829200148508对应）
        params.put("payeeName", "邻商惕半刺尝但农酵瘟入晋率咕");
        params.put("payeeMobile", "13800138000");

        // 跨行所需的额外参数
        // params.put("payeeAcctType", "1");
        // params.put("payeeBankName", "平安银行");
        // params.put("payeeCityName", "广东省广州市");

        params.put("payeeAcctType", "1");
        params.put("payeeBankName", "工商银行");
        params.put("payeeCityName", "广东省深圳市");

        params.put("noticeUrl", "http://192.168.51.164:9080/withdraw/notify");

        String sign = LcMd5SignTool.signMap(params, privateKey);
        params.put("sign", sign);

        for (Map.Entry<String, String> entry : params.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }

    }

    // @Test
    public void testSign2() {
        String privateKey = "11c9c951a5ad422d88ccbfafa6912d5e";

        Map<String, String> params = new LinkedHashMap<String, String>();
        params.put("requestId", UUID.randomUUID().toString().replace("-", ""));
        params.put("requestTime", DateUtil.format(new Date(), "yyyyMMddHHmmss"));
        params.put("merId", "1000001");
        // params.put("sign", "");
        // params.put("userCode", "1002");
        params.put("userCode", "angelshiqian04");
        params.put("orderId", "W" + String.valueOf(System.currentTimeMillis()));
        params.put("orderDesc", "提现测试");
        params.put("amount", "1"); // 金额单位为分
        params.put("currency", "CNY");
        params.put("payerBankCode", "ICBC");

        // 工行提供的测试用的付款账号4000023029200124946
        params.put("payerBankAcctNo", "4000023029200124946");

        params.put("payerMobile", "");
        // params.put("payeeBankCode", "PAB");
        // params.put("payeeBankCode", "ICBC");
        params.put("payeeBankCode", "BOC");

        // 工行提供的测试用的收款账号123456
        params.put("payeeBankAcctNo", "123457");

        // 工行提供的测试用的收款人姓名（跟收款账号4000020829200148508对应）
        params.put("payeeName", "异地B");
        params.put("payeeMobile", "13800138000");

        // 跨行所需的额外参数
        params.put("payeeAcctType", "1");
        params.put("payeeBankName", "中国银行");
        params.put("payeeCityName", "北京市");

        params.put("noticeUrl", "http://192.168.59.193:9080/withdraw/notify");

        String sign = LcMd5SignTool.signMap(params, privateKey);
        params.put("sign", sign);

        for (Map.Entry<String, String> entry : params.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }

    }

}
