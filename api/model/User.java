package com.example.api.model;

import java.util.List;

public class User {
    private String userName;
    private String password; // store BCrypt hash here
    private String email;
    private List<String> roles; // e.g. "ROLE_BASIC", "ROLE_ADMIN"

    public User() {}

    public User(String userName, String password, String email, List<String> roles) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public List<String> getRoles() { return roles; }
    public void setRoles(List<String> roles) { this.roles = roles; }
}
