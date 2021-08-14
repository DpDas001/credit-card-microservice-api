package com.creditcard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.request.RequestContextListener;

@Configuration
@SpringBootApplication(scanBasePackageClasses = {Application.class})
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.creditcard"})
public class Application extends SpringBootServletInitializer {

    /**
     * Run for conventional Spring Boot app
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    /**
     * @return requestContextListener used for Application Server
     */
    @Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }
    

}
