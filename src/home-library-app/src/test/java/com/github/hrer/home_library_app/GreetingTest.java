package com.github.hrer.home_library_app;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class GreetingTest {
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	@WithMockUser
	void shouldReturnDefaultMessage() throws Exception {
		this.mockMvc.perform(get("/greeting")).andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(containsString("Hello, World!")));
	}

}
