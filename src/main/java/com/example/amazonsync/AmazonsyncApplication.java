package com.example.amazonsync;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages={"com.example.amazonsync"})
public class AmazonsyncApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(AmazonsyncApplication.class, args);
    }

}
