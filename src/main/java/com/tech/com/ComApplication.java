package com.tech.com;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.tech.com.dto.EmployeeDto;
import com.tech.com.service.EmployeeService;

@RestController
@SpringBootApplication(scanBasePackages = "com.tech")
public class ComApplication {
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/")
	public String message()
	{
		return "Spring boot application is successfully deployed";
	}
	@GetMapping("/test")
	public String test() {
		return "testing works";
	}
	@GetMapping("/healthCheck")
	public String health() {
		return "health check working";
	}
	@GetMapping("/allEmployees")
    public String getGsonResponse() {
        // Get employees from service
        List<EmployeeDto> employees = employeeService.getAllEmployees();
        
        // Create response map for backward compatibility
        Map<String, Object> response = new HashMap<>();
        for (EmployeeDto employee : employees) {
            response.put(employee.getId(), employee.getName());
        }

        // Convert the response object to JSON using Gson
        Gson gson = new Gson();
        return gson.toJson(response);
    }
	@GetMapping("/allUsers")
    public String getAllUsers() {
        // Create a sample response object
        Map<String, Object> response = new HashMap<>();
        response.put("1", "Farook");
        response.put("2", "Divya");
        response.put("3", "Rajeswari");

        // Convert the response object to JSON using Gson
        Gson gson = new Gson();
        return gson.toJson(response);
    }

	public static void main(String[] args) {
		SpringApplication.run(ComApplication.class, args);
	}

}
