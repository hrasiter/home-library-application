package com.github.hrer.home_library_app;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

        // Get the username of the authenticated user
        String username = authentication.getName();
        
        // Build the URL based on the username
        String redirectUrl = "/homelibrary/" + username;

        // Redirect to the username-specific page
        response.sendRedirect(redirectUrl);
	}

}
