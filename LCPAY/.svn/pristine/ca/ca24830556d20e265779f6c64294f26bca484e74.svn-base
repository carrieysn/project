package com.cifpay.lc.std.sched;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * 标准银信证核心业务应用的JUnit基本配置。
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = StandardLCSchedulerApplication.class)
@WebAppConfiguration
public abstract class TaskJUnitTestBase {

    @BeforeClass
    public static void beforeClass() {
        System.setProperty("spring.profiles.default", "JUNIT");
        System.setProperty("spring.profiles.active", "JUNIT");
    }

}
