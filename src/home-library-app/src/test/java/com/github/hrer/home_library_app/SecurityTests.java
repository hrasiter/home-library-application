package com.github.hrer.home_library_app;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.*;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.h2.command.dml.MergeUsing.When;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

//@WebMvcTest
@SpringBootTest
@AutoConfigureMockMvc
@Import(TestSecurityConfig.class)
public class SecurityTests {
	@Autowired
	private MockMvc mockMvc;
	
	 @MockBean
	 private CustomUserDetailsService customUserDetailsService;


	@Test
	public void testFormLoginSuccess() throws Exception {
		
		UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
	            .username("testuser")
	            .password("{noop}password") // No password encoding for simplicity
	            .roles("USER")
	            .build();

	    when(customUserDetailsService.loadUserByUsername("testuser")).thenReturn(userDetails);
		
		mockMvc.perform(formLogin()
				.user("testuser")
				.password("password"))
		.andDo(print())
		.andExpect(authenticated().withUsername("testuser"))      // doğrulanamazsa authentication shoul not be null hatası  
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/homelibrary/testuser"));
	}

	@Test
	public void testFormLoginFailure() throws Exception {
		mockMvc.perform(formLogin().user("user").password("wrong-password"))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/login?error"));
	}

	@Test
	public void testOAuth2LoginRedirect() throws Exception {
		mockMvc.perform(get("/oauth2/authorization/google"))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrlPattern("https://accounts.google.com/**"));
	}

	@Test
	public void testUnauthorizedAccess() throws Exception {
		mockMvc.perform(get("/homelibrary/user"))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrlPattern("**/login"));
	}
}

