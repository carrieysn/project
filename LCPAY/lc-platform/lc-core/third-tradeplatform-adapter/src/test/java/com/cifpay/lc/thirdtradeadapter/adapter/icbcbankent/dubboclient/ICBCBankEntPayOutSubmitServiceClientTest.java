package com.cifpay.lc.thirdtradeadapter.adapter.icbcbankent.dubboclient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cifpay.lc.api.BusinessInput;
import com.cifpay.lc.api.BusinessOutput;
import com.cifpay.lc.thirdtradeadapter.api.constant.ICBCAccountPropType;
import com.cifpay.lc.thirdtradeadapter.api.constant.ICBCBankEntCurrencyType;
import com.cifpay.lc.thirdtradeadapter.api.input.icbcbankent.PayOutEnquiry;
import com.cifpay.lc.thirdtradeadapter.api.input.icbcbankent.PayOutInstruction;
import com.cifpay.lc.thirdtradeadapter.api.input.icbcbankent.PayOutInstructionDetail;
import com.cifpay.lc.thirdtradeadapter.api.output.icbcbankent.PayOutEnquiryResult;
import com.cifpay.lc.thirdtradeadapter.api.output.icbcbankent.PayOutResult;
import com.cifpay.lc.thirdtradeadapter.api.output.icbcbankent.PayOutResultDetail;
import com.cifpay.lc.thirdtradeadapter.api.service.icbcbankent.ICBCBankEntPayOutEnquiryService;
import com.cifpay.lc.thirdtradeadapter.api.service.icbcbankent.ICBCBankEntPayOutSubmitService;
import com.cifpay.lc.thirdtradeadapter.api.service.icbcbankent.ICBCBankEntVirutalTradeDateUpdateService;

/**
 * 
 * 
 *
 */
public class ICBCBankEntPayOutSubmitServiceClientTest {

	private String tradeDateStr = "2016-07-31";

	private ClassPathXmlApplicationContext context;
	private ICBCBankEntVirutalTradeDateUpdateService tradeDateUpdateService;
	private ICBCBankEntPayOutSubmitService icbcBankEntPayOutService;
	private ICBCBankEntPayOutEnquiryService icbcBankEntPayOutEnquiryService;

	private String originalInstructionNo;
	private String originalInstructionDetailNo;

	@Before
	public void init() {
		context = new ClassPathXmlApplicationContext(new String[] {
				"com/cifpay/lc/thirdtradeadapter/adapter/icbcbankent/dubboclient/test-calling-dubbo-service.xml" });
		context.start();
		tradeDateUpdateService = (ICBCBankEntVirutalTradeDateUpdateService) context
				.getBean("icbcBankEntVirutalTradeDateUpdateService");
		icbcBankEntPayOutService = (ICBCBankEntPayOutSubmitService) context.getBean("icbcBankEntPayOutSubmitService");
		icbcBankEntPayOutEnquiryService = (ICBCBankEntPayOutEnquiryService) context
				.getBean("icbcBankEntPayOutEnquiryService");

		setupTradeDate();
	}

	private void setupTradeDate() {
		try {
			Date tradeDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.parse(tradeDateStr + " " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
			Calendar c = Calendar.getInstance();
			c.setTime(tradeDate);
			c.add(Calendar.MINUTE, -7);
			tradeDate = c.getTime();

			BusinessOutput<Date> output = tradeDateUpdateService.execute(new BusinessInput<Date>(tradeDate));
			System.out.println();
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println(
					"~~~更新TradeDate完毕，returnCode: " + output.getReturnCode() + ", returnMsg: " + output.getReturnMsg());
			System.out.println("~~~当前TradeDate: " + tradeDate);
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testICBCBankEntPayOutService() {

		this.originalInstructionNo = String.valueOf(System.currentTimeMillis());

		PayOutInstruction instruction = new PayOutInstruction();
		instruction.setCallerSystemId("CIFPAYLC");
		instruction.setPayerEnterpriseCode("XDS");
		instruction.setInstructionNo(this.originalInstructionNo);
		// instruction.setTotalAmount(1L);

		long totalAmount = 0L;

		this.originalInstructionDetailNo = String.valueOf(System.currentTimeMillis()) + "A1";
		PayOutInstructionDetail inDetail = new PayOutInstructionDetail();
		inDetail.setInstructionDetailNo(this.originalInstructionDetailNo);
		inDetail.setRecvAccountPropType(ICBCAccountPropType.PERSONAL_ACCOUNT);
		inDetail.setRecvBankCode("ICBC");
		inDetail.setRecvBankName("中国工商银行");
		inDetail.setRecvBankAccountNo("4000020829200148508");
		inDetail.setRecvBankAccountName("邻商惕半刺尝但农酵瘟入晋率咕");
		inDetail.setRecvBankCityName("广东省深圳市");
		inDetail.setCurrencyType(ICBCBankEntCurrencyType.CNY);
		inDetail.setPayAmount(1L);
		totalAmount += inDetail.getPayAmount();
		inDetail.setFundUseDesc("用途描述");
		inDetail.setSummary("摘要");
		instruction.addInstructionDetail(inDetail);

		// inDetail = new PayOutInstructionDetail();
		// inDetail.setInstructionDetailNo(String.valueOf(System.currentTimeMillis())
		// + "A2");
		// inDetail.setRecvAccountPropType(ICBCAccountPropType.PERSONAL_ACCOUNT);
		// inDetail.setRecvBankCode("ICBC");
		// inDetail.setRecvBankName("中国工商银行");
		// inDetail.setRecvBankAccountNo("4000020829200148508");
		// inDetail.setRecvBankAccountName("邻商惕半刺尝但农酵瘟入晋率咕");
		// inDetail.setRecvBankCityName("广东省深圳市");
		// inDetail.setCurrencyType(ICBCBankEntCurrencyType.CNY);
		// inDetail.setPayAmount(3L);
		// totalAmount += inDetail.getPayAmount();
		// inDetail.setFundUseDesc("用途描述二");
		// inDetail.setSummary("摘要二");
		// instruction.addInstructionDetail(inDetail);

		instruction.setTotalAmount(totalAmount);
		BusinessOutput<PayOutResult> businessOutput = icbcBankEntPayOutService
				.execute(new BusinessInput<PayOutInstruction>(instruction));
		Assert.assertNotNull(businessOutput);

		System.out.println("~~~~~~~~~~Output~~~~~~~~~~~~");
		System.out.println("ReturnCode: " + businessOutput.getReturnCode());
		System.out.println("ReturnMsg: " + businessOutput.getReturnMsg());

		PayOutResult result = businessOutput.getData();
		Assert.assertNotNull(result);
		Assert.assertNotNull(result.getAdapterReturnedBatchSeqNo());

		System.out.println();
		System.out.println("AdapterReturnedBatchSeqNo: " + result.getAdapterReturnedBatchSeqNo());

		if (businessOutput.isSuccess()) {
			Assert.assertNotNull(result.getBankReturnedSerialNo());
			Assert.assertTrue(result.getBankReturnedSerialNo().trim().length() > 0);
			System.out.println("BankReturnedSerialNo: " + result.getBankReturnedSerialNo());

			Assert.assertNotNull(result.getResultDetails());
			Assert.assertTrue(result.getResultDetails().size() > 0);

			for (PayOutResultDetail resDetail : result.getResultDetails()) {
				Assert.assertNotNull(resDetail.getAdapterReturnedDetailSeqNo());
				Assert.assertTrue(resDetail.getAdapterReturnedDetailSeqNo().trim().length() > 0);
				System.out.println("AdapterReturnedDetailSeqNo: " + resDetail.getAdapterReturnedDetailSeqNo());
				Assert.assertNotNull(resDetail.getBankResultStatus());
				System.out.println("BankResultStatus: " + resDetail.getBankResultStatus());
				System.out.println("OriginalDetailResultCode: " + resDetail.getOriginalDetailResultCode());
				System.out.println("BankAdditionReturnCode: " + resDetail.getBankAdditionReturnCode());
				System.out.println("BankAdditionReturnMsg: " + resDetail.getBankAdditionReturnMsg());
			}
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println();

	}

	@After
	public void testICBCBankEntPayOutEnquiryService() {

		PayOutEnquiry instruction = new PayOutEnquiry();
		instruction.setCallerSystemId("CIFPAYLC");
		instruction.setPayerEnterpriseCode("XDS");
		instruction.setOriginalInstructionNo(this.originalInstructionNo);
		instruction.setOriginalInstructionDetailNo(this.originalInstructionDetailNo);

		System.out.println("查询交易...");
		BusinessOutput<PayOutEnquiryResult> businessOutput = icbcBankEntPayOutEnquiryService
				.execute(new BusinessInput<PayOutEnquiry>(instruction));
		Assert.assertNotNull(businessOutput);

		System.out.println("~~~~~~~~~~查询交易Output~~~~~~~~~~~~");
		System.out.println("ReturnCode: " + businessOutput.getReturnCode());
		System.out.println("ReturnMsg: " + businessOutput.getReturnMsg());

		PayOutEnquiryResult result = businessOutput.getData();
		Assert.assertNotNull(result);
		Assert.assertNotNull(result.getAdapterReturnedBatchSeqNo());

		System.out.println();
		System.out.println("AdapterReturnedBatchSeqNo: " + result.getAdapterReturnedBatchSeqNo());

		if (businessOutput.isSuccess()) {
			Assert.assertNotNull(result.getBankReturnedSerialNo());
			Assert.assertTrue(result.getBankReturnedSerialNo().trim().length() > 0);
			System.out.println("BankReturnedSerialNo: " + result.getBankReturnedSerialNo());

			Assert.assertNotNull(result.getAdapterReturnedDetailSeqNo());
			Assert.assertTrue(result.getAdapterReturnedDetailSeqNo().trim().length() > 0);
			System.out.println("AdapterReturnedDetailSeqNo: " + result.getAdapterReturnedDetailSeqNo());
			Assert.assertNotNull(result.getBankResultStatus());
			System.out.println("BankResultStatus: " + result.getBankResultStatus());
			System.out.println("OriginalDetailResultCode: " + result.getOriginalDetailResultCode());
			System.out.println("BankAddiDetailReturnCode: " + result.getBankAddiDetailReturnCode());
			System.out.println("BankAddiDetailReturnMsg: " + result.getBankAddiDetailReturnMsg());
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println();

		destroy();
	}

	private void destroy() {
		if (null != context) {
			context.close();
		}
	}
}
