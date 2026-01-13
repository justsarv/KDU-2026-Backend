package com.example.hospitalshift.dto;

public record UserRequestDTO(
        String username,
        String timezone,
        Long tenantId
) {}
