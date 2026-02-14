package com.tech.com.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tech.com.dto.EmployeeDto;

@Service
public class EmployeeService {

    public List<EmployeeDto> getAllEmployees() {
        List<EmployeeDto> employees = new ArrayList<>();
        employees.add(new EmployeeDto("1", "Farook"));
        employees.add(new EmployeeDto("2", "Divya"));
        return employees;
    }
}
