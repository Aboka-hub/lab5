package com.bcnd.laboratories5.dto;


import jakarta.validation.constraints.*;

public record UserRegistrationRequest(
        @NotBlank(message = "username is required")
        @Size(min = 3, max = 60, message = "username 3..60")
        String username,

        @NotBlank(message = "email is required")
        @Email(message = "email must be valid")
        @Size(max = 120, message = "email max 120")
        String email,

        @NotBlank(message = "password is required")
        @Size(min = 8, message = "password min 8 chars")
        String password,

        @NotNull(message = "age is required")
        @Min(value = 16, message = "age must be >= 16")
        @Max(value = 120, message = "age must be <= 120")
        Integer age
) {}