package com.example.store.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MsStoreCustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsStoreCustomerApplication.class, args);
	}

}
