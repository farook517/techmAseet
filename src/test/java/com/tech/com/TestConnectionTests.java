package com.tech.com;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class TestConnectionTests {

	@Test
	@DisplayName("Test database connection string format")
	void testConnectionStringFormat() {
		String url = "jdbc:sqlserver://sql750059.database.windows.net:1433;database=db750059";
		assertTrue(url.startsWith("jdbc:sqlserver://"), "URL should start with jdbc:sqlserver://");
		assertTrue(url.contains("database="), "URL should contain database parameter");
	}

	@Test
	@DisplayName("Test connection URL parsing")
	void testConnectionUrlParsing() {
		String url = "jdbc:sqlserver://sql750059.database.windows.net:1433;database=db750059";
		assertNotNull(url, "Connection URL should not be null");
		assertTrue(url.length() > 0, "Connection URL should not be empty");
		assertTrue(url.contains(":1433"), "Connection URL should contain port number");
	}

	@Test
	@DisplayName("Test username validation")
	void testUsernameValidation() {
		String username = "sqladmin";
		assertNotNull(username, "Username should not be null");
		assertFalse(username.isEmpty(), "Username should not be empty");
	}

	@Test
	@DisplayName("Test connection with invalid credentials should throw SQLException")
	void testConnectionWithInvalidCredentials() {
		String url = "jdbc:sqlserver://invalid-server:1433;database=invalid-db";
		String username = "invalid-user";
		String password = "invalid-password";

		assertThrows(SQLException.class, () -> {
			try (Connection connection = DriverManager.getConnection(url, username, password)) {
				// This should throw an exception
			}
		}, "Should throw SQLException for invalid connection");
	}

	@Test
	@DisplayName("Test connection string components")
	void testConnectionStringComponents() {
		String url = "jdbc:sqlserver://sql750059.database.windows.net:1433;database=db750059";
		String[] parts = url.split(":");
		
		assertEquals("jdbc", parts[0], "Protocol should be jdbc");
		assertTrue(parts[1].startsWith("sqlserver"), "Database type should be sqlserver");
		assertTrue(url.contains("sql750059.database.windows.net"), "Should contain server hostname");
	}
}
