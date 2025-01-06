package com.github.hrer.home_library_app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	//	
	//	@Bean
	//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	//		http
	//		.authorizeHttpRequests((requests)-> requests
	//				.requestMatchers("/home").permitAll()
	//				.anyRequest().authenticated()
	//				)	
	//		.formLogin((form) -> form
	//				.loginPage("/login")
	//				.permitAll()
	//				)
	//		.logout((logout) -> logout
	//				.permitAll()
	//				)
	//		.csrf().disable(); //Web browser'dan kitap ekleme yapılamaya çalışıldığında 403 forbidden hatası için eklendi.
	//		
	//		return http.build();
	//		
	//	}
	//	
	//	@Bean
	//	public UserDetailsService userDetailsService() {
	//		UserDetails user =
	//				User.withDefaultPasswordEncoder()
	//				.username("user")
	//				.password("password")
	//				.roles("USER")
	//				.build();
	//		
	//		return new InMemoryUserDetailsManager(user);
	//	}
	//

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		.authorizeRequests(a -> a
				.requestMatchers("/", "/home", "/login").permitAll()  // Allow public access
				.anyRequest().authenticated()  // Require authentication for other URLs
				)
		.exceptionHandling(e -> e
				.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))  // Custom entry point for unauthorized access
				)
		.oauth2Login();  // Enable OAuth2 login

		return http.build();  // Return the security filter chain
	}

}
