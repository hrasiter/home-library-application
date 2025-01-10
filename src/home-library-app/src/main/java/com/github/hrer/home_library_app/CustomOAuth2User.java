package com.github.hrer.home_library_app;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

public class CustomOAuth2User extends DefaultOAuth2User {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomOAuth2User(OAuth2User delegate, Collection<? extends GrantedAuthority> authorities) {
        super(authorities, delegate.getAttributes(), "email");
    }

    public String getEmail() {
        return getAttribute("email");
    }

    public String getName() {
        return getAttribute("name");
    }
}

