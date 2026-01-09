package com.example.api.controller.dto;

import java.util.List;

public record CreateUserRequest(String userName, String password, String email, List<String> roles) {}
