package com.example.store.shooping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MsStoreShoppingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsStoreShoppingApplication.class, args);
	}

}
