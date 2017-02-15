/**
 * 
 */
package com.cifpay.lc.std.testdao;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cifpay.lc.core.db.dao.DatabaseInfoDao;
import com.cifpay.lc.std.business.BusinessJUnitTestBase;

/**
 * 
 *
 */
public class DatabaseInfoDaoTest extends BusinessJUnitTestBase {

	@Autowired
	private DatabaseInfoDao databaseInfoDao;
	
	@Test
	public void testGetCurrentDbTime() {
		Date time = databaseInfoDao.getCurrentDbTime();
		System.out.println("time: " + time);
		Assert.assertNotNull(time);
	}

}
