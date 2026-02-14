package com.tech.com.model;

import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * JPA Entity representing an Employee in the database.
 * Maps to the 'employees' table.
 */
@Entity
@Table(name = "employees")
public class Employee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "email", unique = true)
    private String email;
    
    @Column(name = "department")
    private String department;
    
    @Column(name = "position")
    private String position;
    
    @Column(name = "hire_date")
    private LocalDate hireDate;

    // Default constructor
    public Employee() {
    }

    // Constructor with all fields
    public Employee(Long id, String name, String email, String department, String position, LocalDate hireDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.department = department;
        this.position = position;
        this.hireDate = hireDate;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", department='" + department + '\'' +
                ", position='" + position + '\'' +
                ", hireDate=" + hireDate +
                '}';
    }
}
