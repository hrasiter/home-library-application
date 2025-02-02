package com.github.hrer.home_library_app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
//    private final UserDetailsService userDetailsService;
//    private final OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService;

//    @Autowired
//    public WebSecurityConfig(UserDetailsService userDetailsService, OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService) {
//        this.userDetailsService = userDetailsService;
//        this.oAuth2UserService = oAuth2UserService;
//    }

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		.authorizeRequests(a -> a
				.requestMatchers("/", "/home", "/login", "/h2-console").permitAll()  // Allow public access
		         .requestMatchers("/h2-console/**").permitAll()
				.anyRequest().authenticated()  // Require authentication for other URLs
				)
		.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)) //solution code
//		.exceptionHandling(e -> e
//				.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))  // Custom entry point for unauthorized access
//				)
		.formLogin((form) -> form
				.loginPage("/login")
				.successHandler(new CustomAuthenticationSuccessHandler())
				.permitAll()
				)
		  .logout(logout -> logout
		            .logoutSuccessUrl("/login?error")  // Redirect after logout
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
    public PasswordEncoder passwordEncoder() {
        return  new BCryptPasswordEncoder();
    }

//	@Bean
//	public UserDetailsService userDetailsService() {
//		UserDetails user =
//				User.withDefaultPasswordEncoder()
//				.username("username")
//				.password("password")
//				.roles("USER")
//				.build();
//
//		return new InMemoryUserDetailsManager(user);
//	}

}
