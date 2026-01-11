package com.example.booklibraryfinal.model;

public class User {
    String username;
    String password;

    public User(String username, String password, String ROLE) {
        this.username = username;
        this.password = password;
        this.ROLE = ROLE;
    }

    String ROLE;

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

    public String getROLE() {
        return ROLE;
    }

    public void setROLE(String ROLE) {
        this.ROLE = ROLE;
    }


}
