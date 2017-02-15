package com.cifpay.lc.core.autoconfigure.jmx;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jmx.export.annotation.AnnotationMBeanExporter;
import org.springframework.jmx.export.naming.ObjectNamingStrategy;
import org.springframework.jmx.support.RegistrationPolicy;

@Configuration
@ConditionalOnProperty(name="cifpay.jmx.enable", havingValue="true")
public class JmxConfiguration extends JmxAutoConfiguration {

	@Bean
	@Primary
	@Override
	public AnnotationMBeanExporter mbeanExporter(ObjectNamingStrategy namingStrategy) {
		AnnotationMBeanExporter exporter = super.mbeanExporter(namingStrategy);
		exporter.setRegistrationPolicy(RegistrationPolicy.IGNORE_EXISTING);
		return exporter;
	}
}
