package com.salesmanager.shop.application;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@RefreshScope
@EnableDiscoveryClient
@SpringBootApplication
public class ShopApplication extends SpringBootServletInitializer {
	
    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
    }
    
    

}
