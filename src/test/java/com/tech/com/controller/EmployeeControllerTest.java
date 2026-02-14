package com.tech.com.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.com.dto.EmployeeDTO;
import com.tech.com.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EmployeeService employeeService;

    @Test
    void testGetAllEmployees() throws Exception {
        EmployeeDTO emp1 = new EmployeeDTO(1L, "Farook", "farook@test.com", "IT", "Developer");
        EmployeeDTO emp2 = new EmployeeDTO(2L, "Divya", "divya@test.com", "HR", "Manager");
        List<EmployeeDTO> employees = Arrays.asList(emp1, emp2);

        when(employeeService.getAllEmployees()).thenReturn(employees);

        mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Farook")))
                .andExpect(jsonPath("$[0].email", is("farook@test.com")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("Divya")));
    }

    @Test
    void testGetEmployeeById() throws Exception {
        EmployeeDTO employee = new EmployeeDTO(1L, "Farook", "farook@test.com", "IT", "Developer");

        when(employeeService.getEmployeeById(1L)).thenReturn(Optional.of(employee));

        mockMvc.perform(get("/api/employees/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Farook")))
                .andExpect(jsonPath("$.email", is("farook@test.com")));
    }

    @Test
    void testGetEmployeeByIdNotFound() throws Exception {
        when(employeeService.getEmployeeById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/employees/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateEmployee() throws Exception {
        EmployeeDTO inputEmployee = new EmployeeDTO(null, "Rajeswari", "rajeswari@test.com", "Finance", "Analyst");
        EmployeeDTO savedEmployee = new EmployeeDTO(3L, "Rajeswari", "rajeswari@test.com", "Finance", "Analyst");

        when(employeeService.createEmployee(any(EmployeeDTO.class))).thenReturn(savedEmployee);

        mockMvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputEmployee)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.name", is("Rajeswari")))
                .andExpect(jsonPath("$.email", is("rajeswari@test.com")));
    }

    @Test
    void testUpdateEmployee() throws Exception {
        EmployeeDTO updatedEmployee = new EmployeeDTO(1L, "Farook Updated", "farook.new@test.com", "IT", "Senior Developer");

        when(employeeService.updateEmployee(eq(1L), any(EmployeeDTO.class))).thenReturn(Optional.of(updatedEmployee));

        mockMvc.perform(put("/api/employees/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedEmployee)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Farook Updated")))
                .andExpect(jsonPath("$.email", is("farook.new@test.com")));
    }

    @Test
    void testUpdateEmployeeNotFound() throws Exception {
        EmployeeDTO employee = new EmployeeDTO(999L, "Test", "test@test.com", "IT", "Developer");

        when(employeeService.updateEmployee(eq(999L), any(EmployeeDTO.class))).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/employees/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
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
