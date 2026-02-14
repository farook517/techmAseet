package com.tech.com.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tech.com.dto.EmployeeDto;
import com.tech.com.entity.Employee;
import com.tech.com.repository.EmployeeRepository;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    private Employee employee1;
    private Employee employee2;
    private EmployeeDto employeeDto1;

    @BeforeEach
    void setUp() {
        employee1 = new Employee("Farook", "farook@example.com", "IT", "Developer");
        employee1.setId(1L);
        
        employee2 = new Employee("Divya", "divya@example.com", "HR", "Manager");
        employee2.setId(2L);
        
        employeeDto1 = new EmployeeDto(null, "Rajeswari", "rajeswari@example.com", "Finance", "Analyst");
    }

    @Test
    void testGetAllEmployees() {
        when(employeeRepository.findAll()).thenReturn(Arrays.asList(employee1, employee2));

        List<EmployeeDto> employees = employeeService.getAllEmployees();

        assertNotNull(employees);
        assertEquals(2, employees.size());
        assertEquals("Farook", employees.get(0).getName());
        assertEquals("Divya", employees.get(1).getName());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    void testGetEmployeeById() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee1));

        Optional<EmployeeDto> result = employeeService.getEmployeeById(1L);

        assertTrue(result.isPresent());
        assertEquals("Farook", result.get().getName());
        assertEquals("farook@example.com", result.get().getEmail());
        verify(employeeRepository, times(1)).findById(1L);
    }

    @Test
    void testGetEmployeeByIdNotFound() {
        when(employeeRepository.findById(999L)).thenReturn(Optional.empty());

        Optional<EmployeeDto> result = employeeService.getEmployeeById(999L);

        assertFalse(result.isPresent());
        verify(employeeRepository, times(1)).findById(999L);
    }

    @Test
    void testCreateEmployee() {
        Employee savedEmployee = new Employee("Rajeswari", "rajeswari@example.com", "Finance", "Analyst");
        savedEmployee.setId(3L);
        
        when(employeeRepository.save(any(Employee.class))).thenReturn(savedEmployee);

        EmployeeDto result = employeeService.createEmployee(employeeDto1);

        assertNotNull(result);
        assertEquals(3L, result.getId());
        assertEquals("Rajeswari", result.getName());
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    void testUpdateEmployee() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee1));
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee1);

        EmployeeDto updateDto = new EmployeeDto(1L, "Farook Updated", "farook.updated@example.com", "IT", "Senior Developer");
        Optional<EmployeeDto> result = employeeService.updateEmployee(1L, updateDto);

        assertTrue(result.isPresent());
        assertEquals("Farook Updated", result.get().getName());
        assertEquals("farook.updated@example.com", result.get().getEmail());
        assertEquals("Senior Developer", result.get().getPosition());
        verify(employeeRepository, times(1)).findById(1L);
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    void testUpdateEmployeeNotFound() {
        when(employeeRepository.findById(999L)).thenReturn(Optional.empty());

        EmployeeDto updateDto = new EmployeeDto(999L, "Test", "test@example.com", "Test", "Test");
        Optional<EmployeeDto> result = employeeService.updateEmployee(999L, updateDto);

        assertFalse(result.isPresent());
        verify(employeeRepository, times(1)).findById(999L);
        verify(employeeRepository, never()).save(any(Employee.class));
    }

    @Test
    void testDeleteEmployee() {
        when(employeeRepository.existsById(1L)).thenReturn(true);
        doNothing().when(employeeRepository).deleteById(1L);

        boolean result = employeeService.deleteEmployee(1L);

        assertTrue(result);
        verify(employeeRepository, times(1)).existsById(1L);
        verify(employeeRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteEmployeeNotFound() {
        when(employeeRepository.existsById(999L)).thenReturn(false);

        boolean result = employeeService.deleteEmployee(999L);

        assertFalse(result);
        verify(employeeRepository, times(1)).existsById(999L);
        verify(employeeRepository, never()).deleteById(any());
    }
}
