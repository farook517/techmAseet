package com.tech.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class ComApplication {
	
	@GetMapping("/")
	public String message()
	{
		return "Spring boot application is successfully deployed";
	}

	public static void main(String[] args) {
		SpringApplication.run(ComApplication.class, args);
	}

}
