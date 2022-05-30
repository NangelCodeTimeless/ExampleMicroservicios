package com.example.store.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@EnableAdminServer
@SpringBootApplication
public class MStoreAdmin2Application {

	public static void main(String[] args) {
		SpringApplication.run(MStoreAdmin2Application.class, args);
	}

}
