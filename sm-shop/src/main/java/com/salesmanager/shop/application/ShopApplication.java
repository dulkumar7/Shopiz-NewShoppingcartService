package com.salesmanager.shop.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ShopApplication extends SpringBootServletInitializer {
	
    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
    }
    
    

}
