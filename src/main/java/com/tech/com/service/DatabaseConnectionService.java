package com.tech.com.service;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabaseConnectionService {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseConnectionService.class);

    @Autowired
    private DataSource dataSource;

    /**
     * Test the database connection
     * 
     * @return true if connection is successful, false otherwise
     */
    public boolean testConnection() {
        try (Connection connection = dataSource.getConnection()) {
            if (connection != null && !connection.isClosed()) {
                logger.info("Database connection test successful");
                return true;
            }
        } catch (SQLException e) {
            logger.error("Database connection test failed", e);
        }
        return false;
    }

    /**
     * Get database metadata information
     * 
     * @return String containing database information
     */
    public String getDatabaseInfo() {
        StringBuilder info = new StringBuilder();
        try (Connection connection = dataSource.getConnection()) {
            if (connection != null) {
                DatabaseMetaData metaData = connection.getMetaData();
                info.append("Database Product: ").append(metaData.getDatabaseProductName()).append("\n");
                info.append("Database Version: ").append(metaData.getDatabaseProductVersion()).append("\n");
                info.append("Driver Name: ").append(metaData.getDriverName()).append("\n");
                info.append("Driver Version: ").append(metaData.getDriverVersion()).append("\n");
                info.append("Connection URL: ").append(metaData.getURL()).append("\n");
                info.append("Username: ").append(metaData.getUserName()).append("\n");
                logger.info("Successfully retrieved database metadata");
            }
        } catch (SQLException e) {
            logger.error("Failed to retrieve database metadata", e);
            info.append("Error: ").append(e.getMessage());
        }
        return info.toString();
    }

    /**
     * Get a connection from the data source
     * 
     * @return Connection object
     * @throws SQLException if connection fails
     */
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
