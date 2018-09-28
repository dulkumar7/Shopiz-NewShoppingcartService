package com.shopizer.shop.services.taxservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.domain.EntityScan;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@EnableEurekaClient
//@EnableDiscoveryClient
@SpringBootApplication
@EnableJpaRepositories( basePackages = "com.shopizer.shop.services.taxservice.repository")
public class TaxServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaxServiceApplication.class, args);
	}


}
