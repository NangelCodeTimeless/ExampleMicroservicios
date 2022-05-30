package com.example.store.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class MsStoreConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsStoreConfigServerApplication.class, args);
	}

}
