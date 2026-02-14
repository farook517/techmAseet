package com.tech.com.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.com.dto.EmployeeDto;
import com.tech.com.service.EmployeeService;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    private EmployeeDto employeeDto1;
    private EmployeeDto employeeDto2;

    @BeforeEach
    void setUp() {
        employeeDto1 = new EmployeeDto(1L, "Farook", "farook@example.com", "IT", "Developer");
        employeeDto2 = new EmployeeDto(2L, "Divya", "divya@example.com", "HR", "Manager");
    }

    @Test
    void testGetAllEmployees() throws Exception {
        when(employeeService.getAllEmployees()).thenReturn(Arrays.asList(employeeDto1, employeeDto2));

        mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("Farook")))
                .andExpect(jsonPath("$[1].name", is("Divya")));
    }

    @Test
    void testGetEmployeeById() throws Exception {
        when(employeeService.getEmployeeById(1L)).thenReturn(Optional.of(employeeDto1));

        mockMvc.perform(get("/api/employees/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Farook")))
                .andExpect(jsonPath("$.email", is("farook@example.com")));
    }

    @Test
    void testGetEmployeeByIdNotFound() throws Exception {
        when(employeeService.getEmployeeById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/employees/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateEmployee() throws Exception {
        EmployeeDto newEmployee = new EmployeeDto(null, "Rajeswari", "rajeswari@example.com", "Finance", "Analyst");
        EmployeeDto savedEmployee = new EmployeeDto(3L, "Rajeswari", "rajeswari@example.com", "Finance", "Analyst");
        
        when(employeeService.createEmployee(any(EmployeeDto.class))).thenReturn(savedEmployee);

        mockMvc.perform(post("/api/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newEmployee)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.name", is("Rajeswari")));
    }

    @Test
    void testUpdateEmployee() throws Exception {
        EmployeeDto updatedEmployee = new EmployeeDto(1L, "Farook Updated", "farook.updated@example.com", "IT", "Senior Developer");
        
        when(employeeService.updateEmployee(eq(1L), any(EmployeeDto.class))).thenReturn(Optional.of(updatedEmployee));

        mockMvc.perform(put("/api/employees/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedEmployee)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Farook Updated")))
                .andExpect(jsonPath("$.position", is("Senior Developer")));
    }

    @Test
    void testUpdateEmployeeNotFound() throws Exception {
        EmployeeDto updateDto = new EmployeeDto(999L, "Test", "test@example.com", "Test", "Test");
        
        when(employeeService.updateEmployee(eq(999L), any(EmployeeDto.class))).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/employees/999")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteEmployee() throws Exception {
        when(employeeService.deleteEmployee(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/employees/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteEmployeeNotFound() throws Exception {
        when(employeeService.deleteEmployee(999L)).thenReturn(false);

        mockMvc.perform(delete("/api/employees/999"))
                .andExpect(status().isNotFound());
    }
}
