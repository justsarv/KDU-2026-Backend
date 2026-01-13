package com.example.hospitalshift.dto;

public record UserResponse(
        Long id,
        String username,
        boolean isLoggedIn,
        String timezone,
        Long tenantId
) {}
