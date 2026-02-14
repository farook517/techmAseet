package com.tech.com.dto;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for LaptopDetailsDto
 */
class LaptopDetailsDtoTest {

    @Test
    void testDefaultConstructor() {
        LaptopDetailsDto dto = new LaptopDetailsDto();
        assertNotNull(dto);
        assertNull(dto.getId());
    }

    @Test
    void testAllArgsConstructor() {
        Long id = 1L;
        String assetTag = "LAP-001";
        String brand = "Dell";
        String model = "Latitude 5520";
        String serialNumber = "SN123456";
        String processor = "Intel Core i7";
        String ram = "16GB";
        String storage = "512GB SSD";
        String osVersion = "Windows 11";
        String assignedTo = "John Doe";
        LocalDate purchaseDate = LocalDate.of(2023, 1, 15);
        LocalDate warrantyExpiry = LocalDate.of(2026, 1, 15);
        String status = "Active";
        BigDecimal purchaseCost = new BigDecimal("1200.00");
        String location = "Office A";
        LocalDateTime lastModified = LocalDateTime.now();

        LaptopDetailsDto dto = new LaptopDetailsDto(
            id, assetTag, brand, model, serialNumber, processor, ram, storage,
            osVersion, assignedTo, purchaseDate, warrantyExpiry, status,
            purchaseCost, location, lastModified
        );

        assertEquals(id, dto.getId());
        assertEquals(assetTag, dto.getAssetTag());
        assertEquals(brand, dto.getBrand());
        assertEquals(model, dto.getModel());
        assertEquals(serialNumber, dto.getSerialNumber());
        assertEquals(processor, dto.getProcessor());
        assertEquals(ram, dto.getRam());
        assertEquals(storage, dto.getStorage());
        assertEquals(osVersion, dto.getOsVersion());
        assertEquals(assignedTo, dto.getAssignedTo());
        assertEquals(purchaseDate, dto.getPurchaseDate());
        assertEquals(warrantyExpiry, dto.getWarrantyExpiry());
        assertEquals(status, dto.getStatus());
        assertEquals(purchaseCost, dto.getPurchaseCost());
        assertEquals(location, dto.getLocation());
        assertEquals(lastModified, dto.getLastModified());
    }

    @Test
    void testSettersAndGetters() {
        LaptopDetailsDto dto = new LaptopDetailsDto();
        
        dto.setId(2L);
        dto.setAssetTag("LAP-002");
        dto.setBrand("HP");
        dto.setModel("EliteBook 840");
        dto.setSerialNumber("SN789012");
        dto.setProcessor("Intel Core i5");
        dto.setRam("8GB");
        dto.setStorage("256GB SSD");
        dto.setOsVersion("Windows 10");
        dto.setAssignedTo("Jane Smith");
        dto.setPurchaseDate(LocalDate.of(2022, 6, 1));
        dto.setWarrantyExpiry(LocalDate.of(2025, 6, 1));
        dto.setStatus("Active");
        dto.setPurchaseCost(new BigDecimal("950.00"));
        dto.setLocation("Office B");
        LocalDateTime now = LocalDateTime.now();
        dto.setLastModified(now);

        assertEquals(2L, dto.getId());
        assertEquals("LAP-002", dto.getAssetTag());
        assertEquals("HP", dto.getBrand());
        assertEquals("EliteBook 840", dto.getModel());
        assertEquals("SN789012", dto.getSerialNumber());
        assertEquals("Intel Core i5", dto.getProcessor());
        assertEquals("8GB", dto.getRam());
        assertEquals("256GB SSD", dto.getStorage());
        assertEquals("Windows 10", dto.getOsVersion());
        assertEquals("Jane Smith", dto.getAssignedTo());
        assertEquals(LocalDate.of(2022, 6, 1), dto.getPurchaseDate());
        assertEquals(LocalDate.of(2025, 6, 1), dto.getWarrantyExpiry());
        assertEquals("Active", dto.getStatus());
        assertEquals(new BigDecimal("950.00"), dto.getPurchaseCost());
        assertEquals("Office B", dto.getLocation());
        assertEquals(now, dto.getLastModified());
    }

    @Test
    void testToString() {
        LaptopDetailsDto dto = new LaptopDetailsDto();
        dto.setId(1L);
        dto.setAssetTag("LAP-001");
        dto.setBrand("Dell");
        
        String result = dto.toString();
        
        assertNotNull(result);
        assertTrue(result.contains("LaptopDetailsDto"));
        assertTrue(result.contains("id=1"));
        assertTrue(result.contains("assetTag='LAP-001'"));
        assertTrue(result.contains("brand='Dell'"));
    }
}
