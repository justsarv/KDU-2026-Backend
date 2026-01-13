package com.example.hospitalshift.dto;

public record ShiftTypeResponseDTO(
        Long id,
        String name,
        String description,
        boolean isActive,
        Long tenantId
) {}
