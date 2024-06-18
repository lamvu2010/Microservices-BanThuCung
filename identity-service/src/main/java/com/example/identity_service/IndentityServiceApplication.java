package com.example.identity_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class IndentityServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(IndentityServiceApplication.class, args);
	}

}
