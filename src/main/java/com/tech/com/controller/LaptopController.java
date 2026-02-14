package com.tech.com.controller;

import com.tech.com.dto.LaptopDetailsDto;
import com.tech.com.service.LaptopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/laptops")
public class LaptopController {
    
    @Autowired
    private LaptopService laptopService;
    
    @PostMapping
    public ResponseEntity<LaptopDetailsDto> createLaptop(@RequestBody LaptopDetailsDto laptopDto) {
        LaptopDetailsDto createdLaptop = laptopService.createLaptop(laptopDto);
        return new ResponseEntity<>(createdLaptop, HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<List<LaptopDetailsDto>> getAllLaptops() {
        List<LaptopDetailsDto> laptops = laptopService.getAllLaptops();
        return ResponseEntity.ok(laptops);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<LaptopDetailsDto> getLaptopById(@PathVariable Long id) {
        return laptopService.getLaptopById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/serial/{serialNumber}")
    public ResponseEntity<LaptopDetailsDto> getLaptopBySerialNumber(@PathVariable String serialNumber) {
        return laptopService.getLaptopBySerialNumber(serialNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/status/{status}")
    public ResponseEntity<List<LaptopDetailsDto>> getLaptopsByStatus(@PathVariable String status) {
        List<LaptopDetailsDto> laptops = laptopService.getLaptopsByStatus(status);
        return ResponseEntity.ok(laptops);
    }
    
    @GetMapping("/brand/{brand}")
    public ResponseEntity<List<LaptopDetailsDto>> getLaptopsByBrand(@PathVariable String brand) {
        List<LaptopDetailsDto> laptops = laptopService.getLaptopsByBrand(brand);
        return ResponseEntity.ok(laptops);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<LaptopDetailsDto> updateLaptop(@PathVariable Long id, 
                                                         @RequestBody LaptopDetailsDto laptopDto) {
        return laptopService.updateLaptop(id, laptopDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLaptop(@PathVariable Long id) {
        boolean deleted = laptopService.deleteLaptop(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
