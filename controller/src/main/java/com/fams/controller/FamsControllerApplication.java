package com.fams.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.fams")
public class FamsControllerApplication {
    public static void main(String[] args) {
        SpringApplication.run(FamsControllerApplication.class, args);
    }
}
