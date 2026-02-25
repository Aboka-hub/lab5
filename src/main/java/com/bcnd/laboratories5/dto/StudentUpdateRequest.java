package com.bcnd.laboratories5.dto;

import jakarta.validation.constraints.*;

public record StudentUpdateRequest(
        @Size(max = 50, message = "firstName max 50")
        String firstName,

        @Size(max = 50, message = "lastName max 50")
        String lastName,

        @Email(message = "email must be valid")
        @Size(max = 120, message = "email max 120")
        String email,

        @Min(value = 16, message = "age must be >= 16")
        @Max(value = 120, message = "age must be <= 120")
        Integer age
) {}