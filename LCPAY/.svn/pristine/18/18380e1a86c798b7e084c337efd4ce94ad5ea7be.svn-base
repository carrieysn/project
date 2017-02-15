package com.cifpay.lc.std.bankadapter;

import com.alibaba.fastjson.JSON;
import com.cifpay.lc.api.BusinessOutput;
import com.cifpay.lc.api.gateway.bank.PaymentSuccessService;
import com.cifpay.lc.bankadapter.api.constant.TradeConfig;
import com.cifpay.lc.bankadapter.api.output.GeneralTradeResult;
import com.cifpay.lc.constant.enums.PayHandler;
import com.cifpay.lc.constant.enums.PayMethod;
import com.cifpay.lc.std.StandardLCBusinessApplication;
import com.cifpay.lc.std.domain.paychannel.ReqReservedBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by sweet on 17-1-4.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = StandardLCBusinessApplication.class)
public class UnionCallbackTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private PaymentSuccessService paymentSuccessService;

    @Test
    @Commit
    public void successCallback() {

        ReqReservedBean reqReservedBean = new ReqReservedBean();
        reqReservedBean.setPayHandler(PayHandler.FREEZE);
        reqReservedBean.setPayMethod(PayMethod.UNION_CREDIT);

        GeneralTradeResult result = new GeneralTradeResult();
        result.setQueryId(UUID.randomUUID().toString().replace("-", ""));
        Map<String, String> map = new HashMap<>();
        map.put("orderId", "98974954015436800");  //lcOpenId,将此开证记录修改为“付款成功”
        map.put("reqReserved", JSON.toJSONString(reqReservedBean));
        result.setResultMap(map);
        result.setTradeResult(TradeConfig.TRADE_RESULT_SUCCEED_0);
        result.setResultDesc("单元测试");

        BusinessOutput<String> callback = paymentSuccessService.cifpayCallBack(result);

        Assert.notNull(callback);
        Assert.isTrue(callback.isSuccess(), callback.getReturnMsg());
    }
}
