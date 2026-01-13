package com.example.hospitalshift.dto;

public record ShiftTypeRequestDTO(

        String name,
        String description,
        Long tenantId
) {}

