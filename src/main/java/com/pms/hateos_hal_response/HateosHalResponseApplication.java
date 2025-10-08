package com.pms.hateos_hal_response;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.bean","com.controller", "com.dao", "com.exception"})
public class HateosHalResponseApplication {

    public static void main(String[] args) {
        SpringApplication.run(HateosHalResponseApplication.class, args);
        System.out.println("Hello world!!!!");
    }

}
