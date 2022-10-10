package com.fams.restgateway;

import com.fams.manager.entities.AccountEntity;
import com.fams.manager.repositories.AccountManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@ComponentScan(basePackages = {"com.fams"})
public class RestGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestGatewayApplication.class, args);
    }

}
