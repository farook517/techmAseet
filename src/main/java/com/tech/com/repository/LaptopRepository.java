package com.tech.com.repository;

import com.tech.com.entity.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LaptopRepository extends JpaRepository<Laptop, Long> {
    
    Optional<Laptop> findBySerialNumber(String serialNumber);
    
    List<Laptop> findByStatus(String status);
    
    List<Laptop> findByBrand(String brand);
    
    List<Laptop> findByAssignedTo(String assignedTo);
}
