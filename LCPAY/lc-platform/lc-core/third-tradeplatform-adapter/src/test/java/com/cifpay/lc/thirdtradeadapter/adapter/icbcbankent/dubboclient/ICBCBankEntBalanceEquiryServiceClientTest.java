package com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.dubboclient;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cifpay.lc.api.BusinessInput;
import com.cifpay.lc.api.BusinessOutput;
import com.cifpay.lc.thirdtradeadapter.api.input.icbcbankent.BalanceEnquiry;
import com.cifpay.lc.thirdtradeadapter.api.output.icbcbankent.BalanceEnquiryResult;
import com.cifpay.lc.thirdtradeadapter.api.service.icbcbankent.ICBCBankEntBalanceEnquiryService;

/**
 * 
 * 
 *
 */
public class ICBCBankEntBalanceEquiryServiceClientTest {

	ClassPathXmlApplicationContext context;
	ICBCBankEntBalanceEnquiryService icbcBankEntBalanceEnquiryService;

	@Before
	public void init() {
		context = new ClassPathXmlApplicationContext(new String[] {
				"com/cifpay/lc/thirdtradeadapter/adapter/icbcbankent/dubboclient/test-calling-dubbo-service.xml" });
		context.start();
		icbcBankEntBalanceEnquiryService = (ICBCBankEntBalanceEnquiryService) context
				.getBean("icbcBankEntBalanceEnquiryService"); // 获取远程服务代理
	}

	@After
	public void destroy() {
		if (null != context) {
			context.close();
		}
	}

	@Test
	public void testICBCBankEntPayOutService() {

		BalanceEnquiry instruction = new BalanceEnquiry();
		instruction.setCallerSystemId("CIFPAYLC");
		instruction.setPayerEnterpriseCode("XDS");

		BusinessOutput<BalanceEnquiryResult> businessOutput = icbcBankEntBalanceEnquiryService
				.execute(new BusinessInput<BalanceEnquiry>(instruction));
		Assert.assertNotNull(businessOutput);

		System.out.println("~~~~~~~~~~Output~~~~~~~~~~~~");
		System.out.println("ReturnCode: " + businessOutput.getReturnCode());
		System.out.println("ReturnMsg: " + businessOutput.getReturnMsg());

		if (businessOutput.isSuccess()) {

			BalanceEnquiryResult result = businessOutput.getData();
			Assert.assertNotNull(result);
			Assert.assertNotNull(result.getAccNo());

			System.out.println();
			System.out.println("AccNo: " + result.getAccNo());

			if (businessOutput.isSuccess()) {
				Assert.assertNotNull(result.getAccBalance());
				Assert.assertNotNull(result.getBalance());
				Assert.assertNotNull(result.getUsableBalance());
				Assert.assertNotNull(result.getFrzAmt());
				Assert.assertNotNull(result.getCurrencyType());
				System.out.println("CurrencyType: " + result.getCurrencyType());
				System.out.println("AcctProperty: " + result.getAcctProperty());
				System.out.println("AccBalance: " + result.getAccBalance());
				System.out.println("Balance: " + result.getBalance());
				System.out.println("UsableBalance: " + result.getUsableBalance());
				System.out.println("FrzAmt: " + result.getFrzAmt());

			}
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println();

	}
}
