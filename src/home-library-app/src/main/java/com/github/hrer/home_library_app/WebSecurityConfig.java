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

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		.authorizeRequests(a -> a
				.requestMatchers("/", "/home", "/login").permitAll()  // Allow public access
				.anyRequest().authenticated()  // Require authentication for other URLs
				)
//		.exceptionHandling(e -> e
//				.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))  // Custom entry point for unauthorized access
//				)
		.formLogin((form) -> form
				.loginPage("/login")
				.successHandler(new CustomAuthenticationSuccessHandler())
				.permitAll()
				)
		  .logout(logout -> logout
		            .logoutSuccessUrl("/login?logout")  // Redirect after logout
		            .permitAll())
		.csrf().disable()
		.oauth2Login(oauth -> oauth
                .loginPage("/login")  // Reuse the same login page for OAuth2 login
                //.defaultSuccessUrl("/homelibrary/", true)
                .successHandler(new CustomOAuth2SuccessHandler())
                );  // Enable OAuth2 login

		return http.build();  // Return the security filter chain
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
