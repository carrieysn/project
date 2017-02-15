package com.cifpay.lc.thirdtradeadapter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.WebApplicationInitializer;

import com.cifpay.lc.thirdtradeadapter.integration.WebContextShutdownListener;

/**
 * Non-Standard Business应用初始化入口。
 * 
 * 
 *
 */
@Configuration
@EnableAutoConfiguration(exclude = { WebMvcAutoConfiguration.EnableWebMvcConfiguration.class,
		DataSourceAutoConfiguration.class })
@ImportResource({ "classpath:/com/cifpay/lc/thirdtradeadapter/config/spring-root-context.xml" })
public class ThirdTradeplatformAdapterApplication extends SpringBootServletInitializer
		implements WebApplicationInitializer {

	public static void main(String[] args) {
		// System.setProperty("spring.devtools.restart.enabled", "false");
		SpringApplication.run(ThirdTradeplatformAdapterApplication.class, args);
	}

	@Bean
	public WebContextShutdownListener webContextShutdownListener() {
		return new WebContextShutdownListener();
	}
}
