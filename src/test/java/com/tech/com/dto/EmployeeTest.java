package com.tech.com.dto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    @Test
    @DisplayName("Test default constructor")
    void testDefaultConstructor() {
        Employee employee = new Employee();
        assertNotNull(employee);
    }

    @Test
    @DisplayName("Test parameterized constructor")
    void testParameterizedConstructor() {
        Employee employee = new Employee(
            1L,
            "John Doe",
            "G123",
            "SR456",
            "1234567890",
            "123 Main St",
            "john.doe@techm.com",
            "john.doe@swissre.com",
            "Development Team",
            "Jane Smith"
        );
        
        assertNotNull(employee);
        assertEquals(1L, employee.getE_id());
        assertEquals("John Doe", employee.getE_name());
        assertEquals("G123", employee.getG_id());
        assertEquals("SR456", employee.getSwissre_id());
        assertEquals("1234567890", employee.getContact_num());
        assertEquals("123 Main St", employee.getAddress());
        assertEquals("john.doe@techm.com", employee.getTechM_mail_id());
        assertEquals("john.doe@swissre.com", employee.getSwissre_mail_id());
        assertEquals("Development Team", employee.getTeam());
        assertEquals("Jane Smith", employee.getManager_name());
    }

    @Test
    @DisplayName("Test setters and getters for e_id")
    void testEIdSetterGetter() {
        Employee employee = new Employee();
        employee.setE_id(100L);
        assertEquals(100L, employee.getE_id());
    }

    @Test
    @DisplayName("Test setters and getters for e_name")
    void testENameSetterGetter() {
        Employee employee = new Employee();
        employee.setE_name("Alice Johnson");
        assertEquals("Alice Johnson", employee.getE_name());
    }

    @Test
    @DisplayName("Test setters and getters for G_id")
    void testGIdSetterGetter() {
        Employee employee = new Employee();
        employee.setG_id("G999");
        assertEquals("G999", employee.getG_id());
    }

    @Test
    @DisplayName("Test setters and getters for swissre_id")
    void testSwissreIdSetterGetter() {
        Employee employee = new Employee();
        employee.setSwissre_id("SR789");
        assertEquals("SR789", employee.getSwissre_id());
    }

    @Test
    @DisplayName("Test setters and getters for contact_num")
    void testContactNumSetterGetter() {
        Employee employee = new Employee();
        employee.setContact_num("9876543210");
        assertEquals("9876543210", employee.getContact_num());
    }

    @Test
    @DisplayName("Test setters and getters for address")
    void testAddressSetterGetter() {
        Employee employee = new Employee();
        employee.setAddress("456 Oak Avenue");
        assertEquals("456 Oak Avenue", employee.getAddress());
    }

    @Test
    @DisplayName("Test setters and getters for techM_mail_id")
    void testTechMMailIdSetterGetter() {
        Employee employee = new Employee();
        employee.setTechM_mail_id("test@techm.com");
        assertEquals("test@techm.com", employee.getTechM_mail_id());
    }

    @Test
    @DisplayName("Test setters and getters for swissre_mail_id")
    void testSwissreMailIdSetterGetter() {
        Employee employee = new Employee();
        employee.setSwissre_mail_id("test@swissre.com");
        assertEquals("test@swissre.com", employee.getSwissre_mail_id());
    }

    @Test
    @DisplayName("Test setters and getters for Team")
    void testTeamSetterGetter() {
        Employee employee = new Employee();
        employee.setTeam("QA Team");
        assertEquals("QA Team", employee.getTeam());
    }

    @Test
    @DisplayName("Test setters and getters for Manager_name")
    void testManagerNameSetterGetter() {
        Employee employee = new Employee();
        employee.setManager_name("Bob Wilson");
        assertEquals("Bob Wilson", employee.getManager_name());
    }

    @Test
    @DisplayName("Test toString method")
    void testToString() {
        Employee employee = new Employee(
            1L,
            "John Doe",
            "G123",
            "SR456",
            "1234567890",
            "123 Main St",
            "john.doe@techm.com",
            "john.doe@swissre.com",
            "Development Team",
            "Jane Smith"
        );
        
        String toString = employee.toString();
        assertNotNull(toString);
        assertTrue(toString.contains("e_id=1"));
        assertTrue(toString.contains("e_name='John Doe'"));
        assertTrue(toString.contains("G_id='G123'"));
        assertTrue(toString.contains("swissre_id='SR456'"));
        assertTrue(toString.contains("contact_num='1234567890'"));
        assertTrue(toString.contains("address='123 Main St'"));
        assertTrue(toString.contains("techM_mail_id='john.doe@techm.com'"));
        assertTrue(toString.contains("swissre_mail_id='john.doe@swissre.com'"));
        assertTrue(toString.contains("Team='Development Team'"));
        assertTrue(toString.contains("Manager_name='Jane Smith'"));
    }

    @Test
    @DisplayName("Test null values handling")
    void testNullValues() {
        Employee employee = new Employee();
        
        assertNull(employee.getE_id());
        assertNull(employee.getE_name());
        assertNull(employee.getG_id());
        assertNull(employee.getSwissre_id());
        assertNull(employee.getContact_num());
        assertNull(employee.getAddress());
        assertNull(employee.getTechM_mail_id());
        assertNull(employee.getSwissre_mail_id());
        assertNull(employee.getTeam());
        assertNull(employee.getManager_name());
    }
}
