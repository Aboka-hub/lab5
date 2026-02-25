package com.bcnd.laboratories5.dto;


public record StudentResponse(
        Long id,
        String firstName,
        String lastName,
        String email,
        Integer age
) {}