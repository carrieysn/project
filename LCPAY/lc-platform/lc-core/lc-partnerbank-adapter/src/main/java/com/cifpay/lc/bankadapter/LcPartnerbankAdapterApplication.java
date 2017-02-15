package com.cifpay.lc.bankadapter;

import com.cifpay.lc.bankadapter.integration.WebContextShutdownListener;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * LC-Partnerbank-Adapter应用初始化入口。
 */
@Configuration
@EnableAutoConfiguration(exclude = {WebMvcAutoConfiguration.EnableWebMvcConfiguration.class,
        DataSourceAutoConfiguration.class, JmxAutoConfiguration.class})
@ImportResource({"classpath:/com/cifpay/lc/bankadapter/config/spring-root-context.xml"})
@EnableCaching
public class LcPartnerbankAdapterApplication extends SpringBootServletInitializer {


    @Bean
    public WebContextShutdownListener webContextShutdownListener() {
        return new WebContextShutdownListener();
    }

}
