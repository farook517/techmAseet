package com.tech.com.controller;

import com.tech.com.dto.EmployeeDTO;
import com.tech.com.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class EmployeeControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeService employeeService;

    private EmployeeDTO testEmployee;

    @BeforeEach
    void setUp() {
        testEmployee = new EmployeeDTO();
        testEmployee.setName("Test Employee");
        testEmployee.setEmail("test@example.com");
        testEmployee.setDepartment("IT");
        testEmployee.setPosition("Developer");
    }

    @Test
    void testGetAllEmployees() throws Exception {
        // Create a test employee
        employeeService.createEmployee(testEmployee);

        mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(1))))
                .andExpect(jsonPath("$[0].name", notNullValue()))
                .andExpect(jsonPath("$[0].email", notNullValue()));
    }

    @Test
    void testCreateEmployee() throws Exception {
        String employeeJson = "{\"name\":\"John Doe\",\"email\":\"john@example.com\",\"department\":\"HR\",\"position\":\"Manager\"}";

        mockMvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(employeeJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("John Doe")))
                .andExpect(jsonPath("$.email", is("john@example.com")))
                .andExpect(jsonPath("$.department", is("HR")))
                .andExpect(jsonPath("$.position", is("Manager")))
                .andExpect(jsonPath("$.id", notNullValue()));
    }

    @Test
    void testGetEmployeeById() throws Exception {
        // Create a test employee
        EmployeeDTO created = employeeService.createEmployee(testEmployee);

        mockMvc.perform(get("/api/employees/" + created.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(created.getId().intValue())))
                .andExpect(jsonPath("$.name", is("Test Employee")))
                .andExpect(jsonPath("$.email", is("test@example.com")));
    }

    @Test
    void testGetEmployeeById_NotFound() throws Exception {
        mockMvc.perform(get("/api/employees/99999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetEmployeeByEmail() throws Exception {
        // Create a test employee
        employeeService.createEmployee(testEmployee);

        mockMvc.perform(get("/api/employees/email/test@example.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is("test@example.com")))
                .andExpect(jsonPath("$.name", is("Test Employee")));
    }

    @Test
    void testUpdateEmployee() throws Exception {
        // Create a test employee
        EmployeeDTO created = employeeService.createEmployee(testEmployee);

        String updatedJson = "{\"name\":\"Updated Name\",\"email\":\"updated@example.com\",\"department\":\"Sales\",\"position\":\"Senior Developer\"}";

        mockMvc.perform(put("/api/employees/" + created.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Updated Name")))
                .andExpect(jsonPath("$.email", is("updated@example.com")))
                .andExpect(jsonPath("$.department", is("Sales")))
                .andExpect(jsonPath("$.position", is("Senior Developer")));
    }

    @Test
    void testUpdateEmployee_NotFound() throws Exception {
        String updatedJson = "{\"name\":\"Updated Name\",\"email\":\"updated@example.com\",\"department\":\"Sales\",\"position\":\"Senior Developer\"}";

        mockMvc.perform(put("/api/employees/99999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedJson))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteEmployee() throws Exception {
        // Create a test employee
        EmployeeDTO created = employeeService.createEmployee(testEmployee);

        mockMvc.perform(delete("/api/employees/" + created.getId()))
                .andExpect(status().isNoContent());

        // Verify it's deleted
        mockMvc.perform(get("/api/employees/" + created.getId()))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteEmployee_NotFound() throws Exception {
        mockMvc.perform(delete("/api/employees/99999"))
                .andExpect(status().isNotFound());
    }
}
