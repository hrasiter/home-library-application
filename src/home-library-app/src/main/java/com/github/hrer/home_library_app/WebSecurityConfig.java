package com.github.hrer.home_library_app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		.authorizeHttpRequests((requests)-> requests
				.requestMatchers("/home").permitAll()
				.anyRequest().authenticated()
				)	
		.formLogin((form) -> form
				.loginPage("/login")
				.permitAll()
				)
		.logout((logout) -> logout
				.permitAll()
				)
		.csrf().disable(); //Web browser'dan kitap ekleme yapılamaya çalışıldığında 403 forbidden hatası için eklendi.
		
		return http.build();
		
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user =
				User.withDefaultPasswordEncoder()
				.username("user")
				.password("password")
				.roles("USER")
				.build();
		
		return new InMemoryUserDetailsManager(user);
	}

}
