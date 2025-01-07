package com.github.hrer.home_library_app;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class HomeLibraryRestControllerMocTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void shouldReturnDefaultMessage() throws Exception {
		this.mockMvc.perform(get("/home")).andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(containsString("<h1>Welcome to Home Library Application!</h1>")));
	}

	@Test
	@WithMockUser //Burası çokomel
	void postBook() throws Exception{
		mockMvc.perform(post("/homelibrary/harun")
				.with(csrf()) //burası çokomel
				.param("title", "BOOK TITLE")
				.param("author", "BOOK AUTHOR")
				.param("ISBN", "1234567890")
				.param("description", "DESCRIPTION"));
		
		
		Book expectedBook = new Book();
		expectedBook.setId(1L);
		expectedBook.setTitle("BOOK TITLE");
		expectedBook.setAuthor("BOOK AUTHOR");
		expectedBook.setISBN("1234567890");
		expectedBook.setDescription("DESCRIPTION");


		mockMvc.perform(get("/homelibrary/harun"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(view().name("bookList"))
		.andExpect(model().attributeExists("books"));
//						.andExpect(model().attribute("books", hasSize(1)));
		//				.andExpect(model().attribute("books",
		//				contains(samePropertyValuesAs(expectedBook))));
	}
}
