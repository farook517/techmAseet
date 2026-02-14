package com.tech.com.service;

import com.tech.com.dto.EmployeeDTO;
import com.tech.com.entity.Employee;
import com.tech.com.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    
    private final EmployeeRepository employeeRepository;
    
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }
    
    public Optional<EmployeeDTO> getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .map(this::convertToDTO);
    }
    
    public Optional<EmployeeDTO> getEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email)
                .map(this::convertToDTO);
    }
    
    public List<EmployeeDTO> getEmployeesByDepartment(String department) {
        return employeeRepository.findByDepartment(department)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }
    
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = convertToEntity(employeeDTO);
        Employee savedEmployee = employeeRepository.save(employee);
        return convertToDTO(savedEmployee);
    }
    
    public Optional<EmployeeDTO> updateEmployee(Long id, EmployeeDTO employeeDTO) {
        return employeeRepository.findById(id)
                .map(employee -> {
                    employee.setFirstName(employeeDTO.getFirstName());
                    employee.setLastName(employeeDTO.getLastName());
                    employee.setEmail(employeeDTO.getEmail());
                    employee.setDepartment(employeeDTO.getDepartment());
                    employee.setJobTitle(employeeDTO.getJobTitle());
                    Employee updatedEmployee = employeeRepository.save(employee);
                    return convertToDTO(updatedEmployee);
                });
    }
    
    public boolean deleteEmployee(Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    private EmployeeDTO convertToDTO(Employee employee) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(employee.getId());
        dto.setFirstName(employee.getFirstName());
        dto.setLastName(employee.getLastName());
        dto.setEmail(employee.getEmail());
        dto.setDepartment(employee.getDepartment());
        dto.setJobTitle(employee.getJobTitle());
        return dto;
    }
    
    private Employee convertToEntity(EmployeeDTO dto) {
        Employee employee = new Employee();
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setEmail(dto.getEmail());
        employee.setDepartment(dto.getDepartment());
        employee.setJobTitle(dto.getJobTitle());
        return employee;
    }
}
