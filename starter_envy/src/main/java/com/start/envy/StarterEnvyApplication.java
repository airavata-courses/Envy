package com.start.envy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class StarterEnvyApplication {

	public static void main(String[] args) {
		SpringApplication.run(StarterEnvyApplication.class, args);
	}
	@Autowired
	private RestTemplate restTemplate;
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		   // Do any additional configuration here
		   return new RestTemplate();
		   //return builder.build();
		}

	
	
	
}



