package com.tech.com.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class DatabaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DatabaseController databaseController;

    @Test
    void testControllerNotNull() {
        assertNotNull(databaseController);
    }

    @Test
    void testTestConnectionEndpoint() throws Exception {
        mockMvc.perform(get("/database/test-connection"))
               .andExpect(status().isOk());
    }

    @Test
    void testDatabaseInfoEndpoint() throws Exception {
        mockMvc.perform(get("/database/info"))
               .andExpect(status().isOk());
    }
}
