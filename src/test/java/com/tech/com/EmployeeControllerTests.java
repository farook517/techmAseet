package com.tech.com;

import com.tech.com.dto.EmployeeDTO;
import com.tech.com.entity.Employee;
import com.tech.com.repository.EmployeeRepository;
import com.tech.com.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class EmployeeControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        employeeRepository.deleteAll();
    }

    @Test
    void testGetAllEmployees_EmptyList() throws Exception {
        mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void testGetAllEmployees_WithData() throws Exception {
        // Create test employees
        Employee emp1 = new Employee("John", "Doe", "john.doe@example.com", "IT", "Developer");
        Employee emp2 = new Employee("Jane", "Smith", "jane.smith@example.com", "HR", "Manager");
        employeeRepository.save(emp1);
        employeeRepository.save(emp2);

        mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].firstName", is("John")))
                .andExpect(jsonPath("$[0].lastName", is("Doe")))
                .andExpect(jsonPath("$[0].email", is("john.doe@example.com")))
                .andExpect(jsonPath("$[1].firstName", is("Jane")))
                .andExpect(jsonPath("$[1].lastName", is("Smith")));
    }

    @Test
    void testGetEmployeeById() throws Exception {
        Employee emp = new Employee("John", "Doe", "john.doe@example.com", "IT", "Developer");
        Employee savedEmp = employeeRepository.save(emp);

        mockMvc.perform(get("/api/employees/" + savedEmp.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(savedEmp.getId().intValue())))
                .andExpect(jsonPath("$.firstName", is("John")))
                .andExpect(jsonPath("$.lastName", is("Doe")))
                .andExpect(jsonPath("$.email", is("john.doe@example.com")));
    }

    @Test
    void testGetEmployeeById_NotFound() throws Exception {
        mockMvc.perform(get("/api/employees/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetEmployeeByEmail() throws Exception {
        Employee emp = new Employee("John", "Doe", "john.doe@example.com", "IT", "Developer");
        employeeRepository.save(emp);

        mockMvc.perform(get("/api/employees/email/john.doe@example.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("John")))
                .andExpect(jsonPath("$.email", is("john.doe@example.com")));
    }

    @Test
    void testGetEmployeesByDepartment() throws Exception {
        Employee emp1 = new Employee("John", "Doe", "john.doe@example.com", "IT", "Developer");
        Employee emp2 = new Employee("Jane", "Smith", "jane.smith@example.com", "IT", "Manager");
        Employee emp3 = new Employee("Bob", "Johnson", "bob.johnson@example.com", "HR", "Manager");
        employeeRepository.save(emp1);
        employeeRepository.save(emp2);
        employeeRepository.save(emp3);

        mockMvc.perform(get("/api/employees/department/IT"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[*].department", everyItem(is("IT"))));
    }

    @Test
    void testCreateEmployee() throws Exception {
        String employeeJson = "{\"firstName\":\"John\",\"lastName\":\"Doe\",\"email\":\"john.doe@example.com\",\"department\":\"IT\",\"jobTitle\":\"Developer\"}";

        mockMvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(employeeJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.firstName", is("John")))
                .andExpect(jsonPath("$.lastName", is("Doe")))
                .andExpect(jsonPath("$.email", is("john.doe@example.com")));
    }

    @Test
    void testUpdateEmployee() throws Exception {
        Employee emp = new Employee("John", "Doe", "john.doe@example.com", "IT", "Developer");
        Employee savedEmp = employeeRepository.save(emp);

        String updatedJson = "{\"firstName\":\"John\",\"lastName\":\"Doe\",\"email\":\"john.updated@example.com\",\"department\":\"IT\",\"jobTitle\":\"Senior Developer\"}";

        mockMvc.perform(put("/api/employees/" + savedEmp.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is("john.updated@example.com")))
                .andExpect(jsonPath("$.jobTitle", is("Senior Developer")));
    }

    @Test
    void testUpdateEmployee_NotFound() throws Exception {
        String updatedJson = "{\"firstName\":\"John\",\"lastName\":\"Doe\",\"email\":\"john.doe@example.com\",\"department\":\"IT\",\"jobTitle\":\"Developer\"}";

        mockMvc.perform(put("/api/employees/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedJson))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteEmployee() throws Exception {
        Employee emp = new Employee("John", "Doe", "john.doe@example.com", "IT", "Developer");
        Employee savedEmp = employeeRepository.save(emp);

        mockMvc.perform(delete("/api/employees/" + savedEmp.getId()))
                .andExpect(status().isNoContent());

        // Verify employee was deleted
        mockMvc.perform(get("/api/employees/" + savedEmp.getId()))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteEmployee_NotFound() throws Exception {
        mockMvc.perform(delete("/api/employees/999"))
                .andExpect(status().isNotFound());
    }
}
