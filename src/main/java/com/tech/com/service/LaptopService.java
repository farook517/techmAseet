package com.tech.com.service;

import com.tech.com.dto.LaptopDetailsDto;
import com.tech.com.entity.Laptop;
import com.tech.com.repository.LaptopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LaptopService {
    
    @Autowired
    private LaptopRepository laptopRepository;
    
    public LaptopDetailsDto createLaptop(LaptopDetailsDto dto) {
        Laptop laptop = convertToEntity(dto);
        Laptop savedLaptop = laptopRepository.save(laptop);
        return convertToDto(savedLaptop);
    }
    
    public List<LaptopDetailsDto> getAllLaptops() {
        return laptopRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    
    public Optional<LaptopDetailsDto> getLaptopById(Long id) {
        return laptopRepository.findById(id)
                .map(this::convertToDto);
    }
    
    public Optional<LaptopDetailsDto> getLaptopBySerialNumber(String serialNumber) {
        return laptopRepository.findBySerialNumber(serialNumber)
                .map(this::convertToDto);
    }
    
    public List<LaptopDetailsDto> getLaptopsByStatus(String status) {
        return laptopRepository.findByStatus(status)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    
    public List<LaptopDetailsDto> getLaptopsByBrand(String brand) {
        return laptopRepository.findByBrand(brand)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    
    public Optional<LaptopDetailsDto> updateLaptop(Long id, LaptopDetailsDto dto) {
        return laptopRepository.findById(id)
                .map(laptop -> {
                    updateEntityFromDto(laptop, dto);
                    Laptop updatedLaptop = laptopRepository.save(laptop);
                    return convertToDto(updatedLaptop);
                });
    }
    
    public boolean deleteLaptop(Long id) {
        if (laptopRepository.existsById(id)) {
            laptopRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    private LaptopDetailsDto convertToDto(Laptop laptop) {
        return new LaptopDetailsDto(
                laptop.getId(),
                laptop.getBrand(),
                laptop.getModel(),
                laptop.getSerialNumber(),
                laptop.getProcessor(),
                laptop.getRamSizeGB(),
                laptop.getStorageSizeGB(),
                laptop.getOperatingSystem(),
                laptop.getPurchaseDate(),
                laptop.getPurchasePrice(),
                laptop.getStatus(),
                laptop.getAssignedTo()
        );
    }
    
    private Laptop convertToEntity(LaptopDetailsDto dto) {
        Laptop laptop = new Laptop();
        laptop.setBrand(dto.getBrand());
        laptop.setModel(dto.getModel());
        laptop.setSerialNumber(dto.getSerialNumber());
        laptop.setProcessor(dto.getProcessor());
        laptop.setRamSizeGB(dto.getRamSizeGB());
        laptop.setStorageSizeGB(dto.getStorageSizeGB());
        laptop.setOperatingSystem(dto.getOperatingSystem());
        laptop.setPurchaseDate(dto.getPurchaseDate());
        laptop.setPurchasePrice(dto.getPurchasePrice());
        laptop.setStatus(dto.getStatus());
        laptop.setAssignedTo(dto.getAssignedTo());
        return laptop;
    }
    
    private void updateEntityFromDto(Laptop laptop, LaptopDetailsDto dto) {
        laptop.setBrand(dto.getBrand());
        laptop.setModel(dto.getModel());
        laptop.setSerialNumber(dto.getSerialNumber());
        laptop.setProcessor(dto.getProcessor());
        laptop.setRamSizeGB(dto.getRamSizeGB());
        laptop.setStorageSizeGB(dto.getStorageSizeGB());
        laptop.setOperatingSystem(dto.getOperatingSystem());
        laptop.setPurchaseDate(dto.getPurchaseDate());
        laptop.setPurchasePrice(dto.getPurchasePrice());
        laptop.setStatus(dto.getStatus());
        laptop.setAssignedTo(dto.getAssignedTo());
    }
}
