package com.tech.com;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
class ComApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	@Test
	void testMessageEndpoint() throws Exception {
		mockMvc.perform(get("/"))
				.andExpect(status().isOk())
				.andExpect(content().string("Spring boot application is successfully deployed"));
	}

	@Test
	void testTestEndpoint() throws Exception {
		mockMvc.perform(get("/test"))
				.andExpect(status().isOk())
				.andExpect(content().string("testing works"));
	}

	@Test
	void testHealthCheckEndpoint() throws Exception {
		mockMvc.perform(get("/healthCheck"))
				.andExpect(status().isOk())
				.andExpect(content().string("health check working"));
	}

	@Test
	void testGetGsonResponse() throws Exception {
		mockMvc.perform(get("/allEmployees"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.1", is("Farook")))
				.andExpect(jsonPath("$.2", is("Divya")));
	}

	@Test
	void testGetAllUsers() throws Exception {
		mockMvc.perform(get("/allUsers"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.1", is("Farook")))
				.andExpect(jsonPath("$.2", is("Divya")))
				.andExpect(jsonPath("$.3", is("Rajeswari")));
	}

}
