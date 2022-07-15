package com.wjalong.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = "com.wjalong.util")
public class BlogGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogGatewayApplication.class, args);
	}

}
