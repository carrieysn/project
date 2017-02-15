package com.cifpay.lc.std.sched;

import com.cifpay.lc.std.sched.integration.WebContextShutdownListener;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 标准银信证定时批处理应用的初始化入口。
 */
@Configuration
@EnableAutoConfiguration(exclude = {WebMvcAutoConfiguration.EnableWebMvcConfiguration.class, DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = {"com.cifpay.lc"})
@ImportResource({"classpath:/com/cifpay/lc/std/sched/config/spring-root-context.xml"})
@EnableScheduling
@EnableAspectJAutoProxy
@EnableCaching
public class StandardLCSchedulerApplication extends SpringBootServletInitializer {


    @Bean
    public WebContextShutdownListener webContextShutdownListener() {
        return new WebContextShutdownListener();
    }

}
