package com.cifpay.lc.core.message.server;

import com.cifpay.lc.core.message.server.integration.WebContextShutdownListener;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
@ComponentScan(basePackages = {"com.cifpay.lc"})
@EnableAspectJAutoProxy
@EnableAutoConfiguration(exclude = {WebMvcAutoConfiguration.EnableWebMvcConfiguration.class,
        DataSourceAutoConfiguration.class, JmxAutoConfiguration.class })
@ImportResource({"classpath:/com/cifpay/lc/core/message/config/spring-root-context.xml"})
public class Application extends SpringBootServletInitializer {

	/*@Bean
    @ConditionalOnMissingBean
	MessageConverter Jackson2JsonMessageConverter(){
		return new Jackson2JsonMessageConverter();
	}*/


    @Bean
    public WebContextShutdownListener webContextShutdownListener() {
        return new WebContextShutdownListener();
    }
}
