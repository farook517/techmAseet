package com.tech.com;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class TestConnectionTests {

	private static final String TEST_DB_URL = "jdbc:sqlserver://sql750059.database.windows.net:1433;database=db750059";
	private static final String TEST_USERNAME = "sqladmin";

	@Test
	@DisplayName("Test database connection string format")
	void testConnectionStringFormat() {
		assertTrue(TEST_DB_URL.startsWith("jdbc:sqlserver://"), "URL should start with jdbc:sqlserver://");
		assertTrue(TEST_DB_URL.contains("database="), "URL should contain database parameter");
	}

	@Test
	@DisplayName("Test connection URL parsing")
	void testConnectionUrlParsing() {
		assertNotNull(TEST_DB_URL, "Connection URL should not be null");
		assertTrue(TEST_DB_URL.length() > 0, "Connection URL should not be empty");
		assertTrue(TEST_DB_URL.contains(":1433"), "Connection URL should contain port number");
	}

	@Test
	@DisplayName("Test username validation")
	void testUsernameValidation() {
		assertNotNull(TEST_USERNAME, "Username should not be null");
		assertFalse(TEST_USERNAME.isEmpty(), "Username should not be empty");
	}

	@Test
	@DisplayName("Test connection with null URL should throw SQLException")
	void testConnectionWithNullUrl() {
		String url = null;
		String username = "test-user";
		String password = "test-password";

		assertThrows(SQLException.class, () -> {
			DriverManager.getConnection(url, username, password);
		}, "Should throw SQLException for null URL");
	}

	@Test
	@DisplayName("Test connection string components")
	void testConnectionStringComponents() {
		String[] parts = TEST_DB_URL.split(":");
		
		assertEquals("jdbc", parts[0], "Protocol should be jdbc");
		assertTrue(parts[1].startsWith("sqlserver"), "Database type should be sqlserver");
		assertTrue(TEST_DB_URL.contains("sql750059.database.windows.net"), "Should contain server hostname");
	}
}
