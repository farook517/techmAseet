package com.tech.com;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@RestController
@SpringBootApplication(scanBasePackages = "com.tech")
public class ComApplication {
	
	@GetMapping("/")
	public String message()
	{
		return "Spring boot application is successfully deployed";
	}
	@GetMapping("/test")
	public String test() {
		return "testing works";
	}
	@GetMapping("/allEmployees")
    public String getGsonResponse() {
        // Create a sample response object
        Map<String, Object> response = new HashMap<>();
        response.put("1", "Farook");
        response.put("2", "Divya");

        // Convert the response object to JSON using Gson
        Gson gson = new Gson();
        return gson.toJson(response);
    }

	public static void main(String[] args) {
		SpringApplication.run(ComApplication.class, args);
	}

}
