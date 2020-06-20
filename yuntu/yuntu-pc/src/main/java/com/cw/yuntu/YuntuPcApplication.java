package com.cw.yuntu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class YuntuPcApplication {
    public static void main(String[] args) {

        SpringApplication.run(YuntuPcApplication.class, args);

    }
}
