package com.example.store.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MsStoreGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsStoreGatewayApplication.class, args);
	}

}
