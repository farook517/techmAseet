package com.tech.com.dto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeDtoTests {

    @Test
    @DisplayName("Test EmployeeDto creation with all fields")
    void testEmployeeDtoCreationWithAllFields() {
        LocalDate hireDate = LocalDate.of(2023, 1, 15);
        EmployeeDto employee = new EmployeeDto(
                1L, 
                "John Doe", 
                "john.doe@tech.com", 
                "IT", 
                "Software Engineer", 
                hireDate
        );

        assertEquals(1L, employee.getId());
        assertEquals("John Doe", employee.getName());
        assertEquals("john.doe@tech.com", employee.getEmail());
        assertEquals("IT", employee.getDepartment());
        assertEquals("Software Engineer", employee.getPosition());
        assertEquals(hireDate, employee.getHireDate());
    }

    @Test
    @DisplayName("Test EmployeeDto default constructor")
    void testEmployeeDtoDefaultConstructor() {
        EmployeeDto employee = new EmployeeDto();
        
        assertNotNull(employee);
        assertNull(employee.getId());
        assertNull(employee.getName());
        assertNull(employee.getEmail());
        assertNull(employee.getDepartment());
        assertNull(employee.getPosition());
        assertNull(employee.getHireDate());
    }

    @Test
    @DisplayName("Test EmployeeDto setters")
    void testEmployeeDtoSetters() {
        EmployeeDto employee = new EmployeeDto();
        LocalDate hireDate = LocalDate.of(2024, 3, 1);

        employee.setId(5L);
        employee.setName("Jane Smith");
        employee.setEmail("jane.smith@tech.com");
        employee.setDepartment("HR");
        employee.setPosition("HR Manager");
        employee.setHireDate(hireDate);

        assertEquals(5L, employee.getId());
        assertEquals("Jane Smith", employee.getName());
        assertEquals("jane.smith@tech.com", employee.getEmail());
        assertEquals("HR", employee.getDepartment());
        assertEquals("HR Manager", employee.getPosition());
        assertEquals(hireDate, employee.getHireDate());
    }

    @Test
    @DisplayName("Test EmployeeDto toString method")
    void testEmployeeDtoToString() {
        LocalDate hireDate = LocalDate.of(2023, 1, 15);
        EmployeeDto employee = new EmployeeDto(
                1L, 
                "John Doe", 
                "john.doe@tech.com", 
                "IT", 
                "Software Engineer", 
                hireDate
        );

        String result = employee.toString();
        
        assertNotNull(result);
        assertTrue(result.contains("John Doe"));
        assertTrue(result.contains("john.doe@tech.com"));
        assertTrue(result.contains("IT"));
        assertTrue(result.contains("Software Engineer"));
    }

    @Test
    @DisplayName("Test EmployeeDto with null values")
    void testEmployeeDtoWithNullValues() {
        EmployeeDto employee = new EmployeeDto(null, null, null, null, null, null);

        assertNull(employee.getId());
        assertNull(employee.getName());
        assertNull(employee.getEmail());
        assertNull(employee.getDepartment());
        assertNull(employee.getPosition());
        assertNull(employee.getHireDate());
    }
}
