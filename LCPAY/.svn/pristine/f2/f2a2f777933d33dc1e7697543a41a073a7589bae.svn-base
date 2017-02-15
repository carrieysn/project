package com.cifpay.lc.gateway;

import com.cifpay.lc.gateway.integration.WebContextShutdownListener;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Gateway WEB应用初始化入口。
 */
@Configuration
@EnableCaching
@EnableAutoConfiguration(exclude = {WebMvcAutoConfiguration.EnableWebMvcConfiguration.class, DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = {"com.cifpay.lc"})
@ImportResource({"classpath:/com/cifpay/lc/gateway/config/load-propeties.xml", "classpath:/com/cifpay/lc/gateway/config/dubbo-consumer.xml"})
public class LCGatewayApplication extends SpringBootServletInitializer /*extends SpringBootServletInitializer implements WebApplicationInitializer*/ {

    @Bean
    public WebContextShutdownListener webContextShutdownListener() {
        return new WebContextShutdownListener();
    }

}
