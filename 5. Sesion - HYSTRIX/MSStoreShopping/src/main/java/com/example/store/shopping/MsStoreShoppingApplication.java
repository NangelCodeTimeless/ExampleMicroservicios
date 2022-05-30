package com.example.store.shopping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
//@EnableFeignClients(basePackages = "com.example.store.shopping.service.client")
@EnableHystrix
@EnableHystrixDashboard
public class MsStoreShoppingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsStoreShoppingApplication.class, args);
	}

}
