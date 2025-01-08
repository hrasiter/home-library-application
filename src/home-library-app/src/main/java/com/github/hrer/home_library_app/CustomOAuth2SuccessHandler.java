package com.github.hrer.home_library_app;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomOAuth2SuccessHandler implements AuthenticationSuccessHandler {

	Logger logger = LoggerFactory.getLogger(CustomAuthenticationSuccessHandler.class);
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		 OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
	        
	        // Access user attributes
	        String email = oAuth2User.getAttribute("email");
	        String name = oAuth2User.getAttribute("name");
	        
	        logger.info("name: " + name);
	        logger.info("email: " + email);
	        
	       
	        
	        String redirectUrl = "/homelibrary/" + name;
	        
	        response.sendRedirect(redirectUrl);
	}

}
