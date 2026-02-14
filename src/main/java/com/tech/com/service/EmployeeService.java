package com.tech.com.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tech.com.dto.EmployeeDto;
import com.tech.com.entity.Employee;
import com.tech.com.repository.EmployeeRepository;

@Service
public class EmployeeService {
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    public List<EmployeeDto> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    
    public Optional<EmployeeDto> getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .map(this::convertToDto);
    }
    
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = convertToEntity(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return convertToDto(savedEmployee);
    }
    
    public Optional<EmployeeDto> updateEmployee(Long id, EmployeeDto employeeDto) {
        return employeeRepository.findById(id)
                .map(employee -> {
                    employee.setName(employeeDto.getName());
                    employee.setEmail(employeeDto.getEmail());
                    employee.setDepartment(employeeDto.getDepartment());
                    employee.setPosition(employeeDto.getPosition());
                    return convertToDto(employeeRepository.save(employee));
                });
    }
    
    public boolean deleteEmployee(Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    private EmployeeDto convertToDto(Employee employee) {
        return new EmployeeDto(
                employee.getId(),
                employee.getName(),
                employee.getEmail(),
                employee.getDepartment(),
                employee.getPosition()
        );
    }
    
    private Employee convertToEntity(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setId(employeeDto.getId());
        employee.setName(employeeDto.getName());
        employee.setEmail(employeeDto.getEmail());
        employee.setDepartment(employeeDto.getDepartment());
        employee.setPosition(employeeDto.getPosition());
        return employee;
    }
}
