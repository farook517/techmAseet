package com.tech.com.repository;

import com.tech.com.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
    // Custom query methods can be added here
    Optional<Employee> findByEmail(String email);
}
