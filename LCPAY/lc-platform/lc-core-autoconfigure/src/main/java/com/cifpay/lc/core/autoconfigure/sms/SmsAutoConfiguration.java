package com.cifpay.lc.core.autoconfigure.sms;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name="cifpay.sms.enable", havingValue="true")
public class SmsAutoConfiguration {

	@Value("${cifpay.sms.url:}")
	private String url;

	@Value("${cifpay.sms.username:}")
	private String username;

	@Value("${cifpay.sms.password:}")
	private String password;

	@Value("${cifpay.sms.sendflag:}")
	private String sendflag;

	@Bean
	public SmsProperties getSmsProperties(){
		SmsProperties smsProperties = new SmsProperties();
		smsProperties.setUrl(url);
		smsProperties.setUsername(username);
		smsProperties.setPassword(password);
		smsProperties.setSendflag(sendflag);
		return smsProperties;
	}

}
