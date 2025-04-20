package com.tech.com;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestConnection {

	public static void main(String[] args) {
        String url = "jdbc:sqlserver://sql750059.database.windows.net:1433;database=db750059";
        String username = "sqladmin";
        String password = "";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            if (connection != null) {
                System.out.println("Connected to Azure SQL Database successfully!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
