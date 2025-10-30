package com.tech.com.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.tech.com.service.DatabaseConnectionService;

@RestController
@RequestMapping("/database")
public class DatabaseController {

    @Autowired
    private DatabaseConnectionService databaseConnectionService;

    private final Gson gson = new Gson();

    /**
     * Test database connection
     * 
     * @return JSON response with connection status
     */
    @GetMapping("/test-connection")
    public ResponseEntity<String> testConnection() {
        Map<String, Object> response = new HashMap<>();
        
        boolean isConnected = databaseConnectionService.testConnection();
        
        response.put("status", isConnected ? "success" : "failed");
        response.put("message", isConnected ? 
            "Database connection successful" : 
            "Database connection failed");
        response.put("connected", isConnected);
        
        return ResponseEntity
            .status(isConnected ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR)
            .body(gson.toJson(response));
    }

    /**
     * Get database information
     * 
     * @return JSON response with database metadata
     */
    @GetMapping("/info")
    public ResponseEntity<String> getDatabaseInfo() {
        Map<String, Object> response = new HashMap<>();
        
        String dbInfo = databaseConnectionService.getDatabaseInfo();
        
        response.put("status", "success");
        response.put("databaseInfo", dbInfo);
        
        return ResponseEntity.ok(gson.toJson(response));
    }
}
