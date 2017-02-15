package mock.merchant;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import mock.merchant.servlet.IndexServlet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Mock Merchant WEB应用初始化入口。
 */
@Configuration
@EnableAutoConfiguration(exclude = {WebMvcAutoConfiguration.EnableWebMvcConfiguration.class, DataSourceAutoConfiguration.class})
@ComponentScan(basePackageClasses = {LCMockMerchantApplication.class})
@ImportResource({"classpath:/mock/merchant/config/spring-root-context.xml"})
public class LCMockMerchantApplication extends SpringBootServletInitializer /*extends SpringBootServletInitializer implements WebApplicationInitializer*/ {


    @Bean
    public InternalResourceViewResolver internalResourceViewResolver(@Value("${spring.mvc.view.prefix}") String prefix,
                                                                     @Value("${spring.mvc.view.suffix}") String suffix) {
        InternalResourceViewResolver vr = new InternalResourceViewResolver();
        vr.setContentType("text/html;charset=UTF-8");
        vr.setPrefix(prefix);
        vr.setSuffix(suffix);
        return vr;
    }

    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
       /*fastJsonConfig.setSerializerFeatures(
               SerializerFeature.PrettyFormat,
               SerializerFeature.WriteClassName,
               SerializerFeature.WriteMapNullValue
       );*/
        fastConverter.setFastJsonConfig(fastJsonConfig);
        HttpMessageConverter<?> converter = fastConverter;
        return new HttpMessageConverters(converter);
    }


    /*@Bean
    public FilterRegistrationBean encodingFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new CharacterEncodingFilter("UTF-8", true));
        registration.addUrlPatterns("/*");
        return registration;
    }

    @Bean
    public ServletRegistrationBean indexServlet() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new IndexServlet(), "/index");
        return registration;
    }


    @Bean
    public ServletRegistrationBean lcServlet() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new LcServlet(), "*.lc");
        return registration;
    }

    @Bean
    public ServletRegistrationBean getLcListServlet() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new GetLcListServlet(), "/getLcListServlet");
        return registration;
    }

    @Bean
    public ServletRegistrationBean getFundServlet() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new GetFundServlet(), "/getFundServlet");
        return registration;
    }

    @Bean
    public ServletRegistrationBean xyServlet() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new XyServlet(), "/xy/*");
        return registration;
    }

    @Bean
    public InternalResourceViewResolver internalResourceViewResolver(@Value("${spring.mvc.view.prefix}") String prefix, @Value("${spring.mvc.view.suffix}") String suffix) {
        InternalResourceViewResolver vr = new InternalResourceViewResolver();
        vr.setContentType("text/html;charset=UTF-8");
        vr.setPrefix(prefix);
        vr.setSuffix(suffix);
        return vr;
    }*/

}
