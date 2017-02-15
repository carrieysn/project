package com.cifpay.lc.std.business.service.lc;

import java.io.IOException;
import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.cifpay.lc.api.BusinessInput;
import com.cifpay.lc.api.BusinessOutput;
import com.cifpay.lc.api.gateway.lc.InitLcService;
import com.cifpay.lc.constant.enums.AccountPropertyType;
import com.cifpay.lc.domain.lc.InitLcInputBean;
import com.cifpay.lc.domain.lc.InitLcOutputBean;
import com.cifpay.lc.std.business.BusinessJUnitTestBase;

public class InitLcServiceImplTest extends BusinessJUnitTestBase {

	@Autowired
	private InitLcService initLcService;

	@Test
	public void testExecute() {
		BusinessInput<InitLcInputBean> input = new BusinessInput<InitLcInputBean>();
		InitLcInputBean data = new InitLcInputBean();
		input.setData(data);

		data.setMerId("110011");
		data.setProductCode("PD001");
		data.setOrderId("1234567890");
		data.setAmount(BigDecimal.valueOf(1000));
		data.setCurrency("CNY");

		data.setPayerBankCode("ICBC");
		data.setPayerBankAccountNo("62221234957873");
		data.setPayerAccountType(AccountPropertyType.PERSONAL);

		data.setRecvBankCode("ICBC");
		data.setRecvBankAccountNo("62221234957873");
		data.setRecvAccountType(AccountPropertyType.PERSONAL);

		data.setReturnUrl("http://localhost.com/return");
		data.setNoticeUrl("http://localhost.com/notice");
		data.setMrchOrderUrl("http://localhost.com/orderDetail");

		data.setRemark("单元测试");

		BusinessOutput<InitLcOutputBean> output = initLcService.execute(input);
		Assert.assertNotNull(output);
		Assert.assertEquals(0, output.getReturnCode());
		Assert.assertNotNull(output.getData());

		InitLcOutputBean outputBean = output.getData();
		try {
			System.out.println(JSON.json(outputBean));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Assert.assertTrue(outputBean.getLcId().longValue() != 0);
	}

}
