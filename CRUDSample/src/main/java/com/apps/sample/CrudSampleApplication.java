package com.apps.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.apps.sample.controllers", "com.apps.sample.services", "com.apps.sample.dao"})
public class CrudSampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudSampleApplication.class, args);
    }
}
