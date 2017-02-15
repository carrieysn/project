package com.cifpay.lc.core.autoconfigure.amqp;

import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.RabbitConnectionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ConditionalOnProperty(name = "cifpay.amqp.enable", havingValue = "true")
public class RabbitmqAutoConfiguration {

    @Value("${cifpay.rabbitmq.addresses:}")
    private String addresses;

    @Value("${cifpay.rabbitmq.host:localhost}")
    private String host;
    
    @Value("${cifpay.rabbitmq.vhost:/}")
    private String vhost;

    @Value("${cifpay.rabbitmq.port:5672}")
    private int port;

    @Value("${cifpay.rabbitmq.username}")
    private String username;

    @Value("${cifpay.rabbitmq.password}")
    private String password;

    @Bean
    @Primary
    public RabbitProperties rabbitProperties() {
        RabbitProperties rabbitProperties = new RabbitProperties();
        if(StringUtils.isNotEmpty(addresses)){
        	rabbitProperties.setAddresses(addresses);
        }else{
        	rabbitProperties.setHost(host);
            rabbitProperties.setPort(port);
        }
        rabbitProperties.setVirtualHost(vhost);
        rabbitProperties.setUsername(username);
        rabbitProperties.setPassword(password);
        return rabbitProperties;
    }

    @Bean
    @Primary
	public CachingConnectionFactory rabbitConnectionFactory(RabbitProperties config)
			throws Exception {
		RabbitConnectionFactoryBean factory = new RabbitConnectionFactoryBean();
		if (config.getHost() != null) {
			factory.setHost(config.getHost());
			factory.setPort(config.getPort());
		}
		if (config.getUsername() != null) {
			factory.setUsername(config.getUsername());
		}
		if (config.getPassword() != null) {
			factory.setPassword(config.getPassword());
		}
		if (config.getVirtualHost() != null) {
			factory.setVirtualHost(config.getVirtualHost());
		}
		if (config.getRequestedHeartbeat() != null) {
			factory.setRequestedHeartbeat(config.getRequestedHeartbeat());
		}
		RabbitProperties.Ssl ssl = config.getSsl();
		if (ssl.isEnabled()) {
			factory.setUseSSL(true);
			factory.setKeyStore(ssl.getKeyStore());
			factory.setKeyStorePassphrase(ssl.getKeyStorePassword());
			factory.setTrustStore(ssl.getTrustStore());
			factory.setTrustStorePassphrase(ssl.getTrustStorePassword());
		}
		factory.afterPropertiesSet();
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory(
				factory.getObject());
		connectionFactory.setAddresses(config.getAddresses());
		return connectionFactory;
	}
}
