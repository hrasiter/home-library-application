package com.github.hrer.home_library_app;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    
    Logger logger = LoggerFactory.getLogger(CustomAuthenticationSuccessHandler.class);

    @Autowired
    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        // Fetch user details from the OAuth2 provider
        OAuth2User oAuth2User = super.loadUser(userRequest);
        String email = oAuth2User.getAttribute("email");
        
        logger.warn("email: " + email);
        logger.warn("oauthuser: " +  oAuth2User);
        logger.warn("request: " + userRequest);
        
        

        // Find or create user in your database
        User user = userRepository.findByUsername(email);
                if (user == null)
                {
                    user = new User();
                    user.setUsername(email);
                    user.setRoles(List.of("ROLE_USER")); // Default role for new users
                    userRepository.save(user);
                }
                

        // Convert roles to GrantedAuthority
        var authorities = user.getRoles().stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());

        // Return a DefaultOAuth2User with custom authorities
        return new DefaultOAuth2User(authorities, oAuth2User.getAttributes(), "email");
    }
}
