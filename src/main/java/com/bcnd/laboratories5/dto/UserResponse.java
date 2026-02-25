package com.bcnd.laboratories5.dto;

public record UserResponse(
        Long id,
        String username,
        String email,
        Integer age
) {}