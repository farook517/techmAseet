package com.tech.com.service;

import com.tech.com.dto.EmployeeDto;
import com.tech.com.model.Employee;
import com.tech.com.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired(required = false)
    private EmployeeRepository employeeRepository;

    // Convert Employee entity to EmployeeDto
    private EmployeeDto convertToDto(Employee employee) {
        return new EmployeeDto(
                employee.getId(),
                employee.getName(),
                employee.getEmail(),
                employee.getDepartment(),
                employee.getPosition(),
                employee.getHireDate()
        );
    }

    // Convert EmployeeDto to Employee entity
    private Employee convertToEntity(EmployeeDto employeeDto) {
        return new Employee(
                employeeDto.getId(),
                employeeDto.getName(),
                employeeDto.getEmail(),
                employeeDto.getDepartment(),
                employeeDto.getPosition(),
                employeeDto.getHireDate()
        );
    }

    // Get all employees
    public List<EmployeeDto> getAllEmployees() {
        if (employeeRepository == null) {
            return List.of();
        }
        return employeeRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // Get employee by ID
    public Optional<EmployeeDto> getEmployeeById(Long id) {
        if (employeeRepository == null) {
            return Optional.empty();
        }
        return employeeRepository.findById(id)
                .map(this::convertToDto);
    }

    // Create new employee
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        if (employeeRepository == null) {
            throw new IllegalStateException("Database is not configured");
        }
        Employee employee = convertToEntity(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return convertToDto(savedEmployee);
    }

    // Update existing employee
    public Optional<EmployeeDto> updateEmployee(Long id, EmployeeDto employeeDto) {
        if (employeeRepository == null) {
            return Optional.empty();
        }
        return employeeRepository.findById(id)
                .map(existingEmployee -> {
                    existingEmployee.setName(employeeDto.getName());
                    existingEmployee.setEmail(employeeDto.getEmail());
                    existingEmployee.setDepartment(employeeDto.getDepartment());
                    existingEmployee.setPosition(employeeDto.getPosition());
                    existingEmployee.setHireDate(employeeDto.getHireDate());
                    Employee updatedEmployee = employeeRepository.save(existingEmployee);
                    return convertToDto(updatedEmployee);
                });
    }

    // Delete employee
    public boolean deleteEmployee(Long id) {
        if (employeeRepository == null) {
            return false;
        }
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
