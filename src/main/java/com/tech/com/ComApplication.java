package com.tech.com;

import java.time.LocalDate;
import java.util.Arrays;
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
	
	@Autowired(required = false)
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
    public List<EmployeeDto> getGsonResponse() {
        // If service is available, get from database, otherwise return sample data
        if (employeeService != null) {
            List<EmployeeDto> employees = employeeService.getAllEmployees();
            if (!employees.isEmpty()) {
                return employees;
            }
        }
        
        // Return sample data if no employees in database
        return Arrays.asList(
            new EmployeeDto(1L, "Farook", "farook@tech.com", "IT", "Developer", LocalDate.now()),
            new EmployeeDto(2L, "Divya", "divya@tech.com", "HR", "Manager", LocalDate.now())
        );
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
