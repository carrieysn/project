/**
 * File: SysCodeRuleServiceTest.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年12月10日 下午7:36:41
 */
package com.cifpay.insurance;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cifpay.insurance.config.WebConstant;
import com.cifpay.insurance.model.InsInsuranceCert;
import com.cifpay.insurance.service.SysCodeRuleService;

/**
 * 编码规则服务接口测试
 * 
 * @author 张均锋
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class SysCodeRuleServiceTest {

	@Autowired
	private SysCodeRuleService sysCodeRuleService;

	/**订单号 **/
	//@Test
	public void generateCodeNumberTest() {
		String codeName = WebConstant.CODE_INSPOLICYORDER_BILLNO;
		String val = sysCodeRuleService.generateCodeNumber(codeName);
		System.out.println("InsPolicyOrder_billNo codeno------------->"+val);
		
		Assert.assertNotNull(val);
	}
	
	/**保单号 **/
	@Test
	public void generatePolicyNoCodeNumberTest() {
		String codeName = WebConstant.CODE_INSPOLICY_POLICYNO;
		String val = sysCodeRuleService.generateCodeNumber(codeName);
		System.out.println("InsPolicy_policyNo codeno------------->"+val);
		
		Assert.assertNotNull(val);
	}
	
	/** 保险证编号 **/
	//@Test
	public void generateInsCertCodeNumberTest() {
		InsInsuranceCert c = new InsInsuranceCert();
		c.setPolicyNo("V525Y57");
		String codeName = WebConstant.CODE_INSINSURANCECERT_INSURANCECERTNO;
		String val = sysCodeRuleService.generateCodeNumber(codeName, c, null);
		System.out.println("InsPolicyOrder_billNo codeno------------->"+val);
		

		Assert.assertNotNull(val);
	}
	
}
