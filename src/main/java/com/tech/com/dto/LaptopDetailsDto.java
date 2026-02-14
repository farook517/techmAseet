package com.tech.com.dto;

import java.time.LocalDate;

public class LaptopDetailsDto {
    
    private Long id;
    private String brand;
    private String model;
    private String serialNumber;
    private String processor;
    private Integer ramSizeGB;
    private Integer storageSizeGB;
    private String operatingSystem;
    private LocalDate purchaseDate;
    private Double purchasePrice;
    private String status;
    private String assignedTo;
    
    // Constructors
    public LaptopDetailsDto() {
    }
    
    public LaptopDetailsDto(Long id, String brand, String model, String serialNumber, 
                           String processor, Integer ramSizeGB, Integer storageSizeGB,
                           String operatingSystem, LocalDate purchaseDate, Double purchasePrice,
                           String status, String assignedTo) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.serialNumber = serialNumber;
        this.processor = processor;
        this.ramSizeGB = ramSizeGB;
        this.storageSizeGB = storageSizeGB;
        this.operatingSystem = operatingSystem;
        this.purchaseDate = purchaseDate;
        this.purchasePrice = purchasePrice;
        this.status = status;
        this.assignedTo = assignedTo;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getBrand() {
        return brand;
    }
    
    public void setBrand(String brand) {
        this.brand = brand;
    }
    
    public String getModel() {
        return model;
    }
    
    public void setModel(String model) {
        this.model = model;
    }
    
    public String getSerialNumber() {
        return serialNumber;
    }
    
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
    
    public String getProcessor() {
        return processor;
    }
    
    public void setProcessor(String processor) {
        this.processor = processor;
    }
    
    public Integer getRamSizeGB() {
        return ramSizeGB;
    }
    
    public void setRamSizeGB(Integer ramSizeGB) {
        this.ramSizeGB = ramSizeGB;
    }
    
    public Integer getStorageSizeGB() {
        return storageSizeGB;
    }
    
    public void setStorageSizeGB(Integer storageSizeGB) {
        this.storageSizeGB = storageSizeGB;
    }
    
    public String getOperatingSystem() {
        return operatingSystem;
    }
    
    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }
    
    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }
    
    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
    
    public Double getPurchasePrice() {
        return purchasePrice;
    }
    
    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getAssignedTo() {
        return assignedTo;
    }
    
    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }
}
