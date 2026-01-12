package com.example.kdubackendassessment1sarvesh.model;

public class User {
    String username;
    String password;
    String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    public User(String username,String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }


}
