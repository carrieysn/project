package com.cifpay.lc.std.business.service.lc;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cifpay.lc.api.BusinessInput;
import com.cifpay.lc.api.BusinessOutput;
import com.cifpay.lc.api.gateway.lc.OpenLcService;
import com.cifpay.lc.domain.lc.OpenLcInputBean;
import com.cifpay.lc.domain.lc.OpenLcOutputBean;
import com.cifpay.lc.std.business.BusinessJUnitTestBase;

public class OpenLcServiceImplTest extends BusinessJUnitTestBase {

	@Autowired
	private OpenLcService openLcService;

	@Test
	public void testExecute() {
		BusinessInput<OpenLcInputBean> input = new BusinessInput<OpenLcInputBean>();
		OpenLcInputBean data = new OpenLcInputBean();
		input.setData(data);

		data.setMerId("110011");
		data.setLcId(1050399511412736L);
		data.setRemark("单元测试");

		BusinessOutput<OpenLcOutputBean> output = openLcService.execute(input);
		Assert.assertNotNull(output);
		Assert.assertEquals(0, output.getReturnCode());
//		Assert.assertNotNull(output.getData());
	}

}
