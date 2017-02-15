package com.cifpay.lc.std.business.dbtxn;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cifpay.lc.std.business.BusinessJUnitTestBase;

public class DbTransactionTest extends BusinessJUnitTestBase {

	@Autowired
	private DbTransactionSample sample;

	@Test
	public void testTransactionFeature() {
		try {
			sample.executeInTransaction();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
