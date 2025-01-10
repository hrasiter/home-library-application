package com.github.hrer.home_library_app;


import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "`USER`") 
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username; // Or email
    private String password;

    private Collection<String> roles = new ArrayList<String>(); // E.g., ["ROLE_USER", "ROLE_ADMIN"]

    // Constructors, Getters, and Setters
    public User() {
    }

    public User(String username, String password, Collection<String> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public User(String email, String password, String role) {
        this.username = email;
        this.password = password;
        this.roles.add(role);
	}
    
	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<String> getRoles() {
        return roles;
    }

    public void setRoles(Collection<String> roles) {
        this.roles = roles;
    }
}
