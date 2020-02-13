package com.allianz.auth.tokenconverter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
public class TokenConverterApplication {

	public static void main(String[] args) {
		SpringApplication.run(TokenConverterApplication.class, args);
	}



}
