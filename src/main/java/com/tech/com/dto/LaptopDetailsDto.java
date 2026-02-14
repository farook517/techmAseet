package com.tech.com.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Data Transfer Object for Laptop Details
 * Used for transferring laptop asset information between layers
 */
public class LaptopDetailsDto {
    
    private Long id;
    private String assetTag;
    private String brand;
    private String model;
    private String serialNumber;
    private String processor;
    private String ram;
    private String storage;
    private String osVersion;
    private String assignedTo;
    private LocalDate purchaseDate;
    private LocalDate warrantyExpiry;
    private String status;
    private BigDecimal purchaseCost;
    private String location;
    private LocalDateTime lastModified;

    // Default constructor
    public LaptopDetailsDto() {
    }

    // All-args constructor
    public LaptopDetailsDto(Long id, String assetTag, String brand, String model, 
                           String serialNumber, String processor, String ram, String storage,
                           String osVersion, String assignedTo, LocalDate purchaseDate,
                           LocalDate warrantyExpiry, String status, BigDecimal purchaseCost,
                           String location, LocalDateTime lastModified) {
        this.id = id;
        this.assetTag = assetTag;
        this.brand = brand;
        this.model = model;
        this.serialNumber = serialNumber;
        this.processor = processor;
        this.ram = ram;
        this.storage = storage;
        this.osVersion = osVersion;
        this.assignedTo = assignedTo;
        this.purchaseDate = purchaseDate;
        this.warrantyExpiry = warrantyExpiry;
        this.status = status;
        this.purchaseCost = purchaseCost;
        this.location = location;
        this.lastModified = lastModified;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssetTag() {
        return assetTag;
    }

    public void setAssetTag(String assetTag) {
        this.assetTag = assetTag;
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

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public LocalDate getWarrantyExpiry() {
        return warrantyExpiry;
    }

    public void setWarrantyExpiry(LocalDate warrantyExpiry) {
        this.warrantyExpiry = warrantyExpiry;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getPurchaseCost() {
        return purchaseCost;
    }

    public void setPurchaseCost(BigDecimal purchaseCost) {
        this.purchaseCost = purchaseCost;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("LaptopDetailsDto{");
        sb.append("id=").append(id);
        sb.append(", assetTag='").append(assetTag).append('\'');
        sb.append(", brand='").append(brand).append('\'');
        sb.append(", model='").append(model).append('\'');
        sb.append(", serialNumber='").append(serialNumber).append('\'');
        sb.append(", processor='").append(processor).append('\'');
        sb.append(", ram='").append(ram).append('\'');
        sb.append(", storage='").append(storage).append('\'');
        sb.append(", osVersion='").append(osVersion).append('\'');
        sb.append(", assignedTo='").append(assignedTo).append('\'');
        sb.append(", purchaseDate=").append(purchaseDate);
        sb.append(", warrantyExpiry=").append(warrantyExpiry);
        sb.append(", status='").append(status).append('\'');
        sb.append(", purchaseCost=").append(purchaseCost);
        sb.append(", location='").append(location).append('\'');
        sb.append(", lastModified=").append(lastModified);
        sb.append('}');
        return sb.toString();
    }
}
