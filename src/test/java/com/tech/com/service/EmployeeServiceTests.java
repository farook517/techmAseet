package com.tech.com.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tech.com.dto.EmployeeDto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeServiceTests {

    @Autowired
    private EmployeeService employeeService;

    @Test
    void testGetAllEmployees() {
        List<EmployeeDto> employees = employeeService.getAllEmployees();
        
        assertNotNull(employees, "Employee list should not be null");
        assertEquals(2, employees.size(), "Should return 2 employees");
        
        assertEquals("1", employees.get(0).getId(), "First employee ID should be 1");
        assertEquals("Farook", employees.get(0).getName(), "First employee name should be Farook");
        
        assertEquals("2", employees.get(1).getId(), "Second employee ID should be 2");
        assertEquals("Divya", employees.get(1).getName(), "Second employee name should be Divya");
    }
}
