package com.bcnd.laboratories5.mapper;


import com.bcnd.laboratories5.dto.UserRegistrationRequest;
import com.bcnd.laboratories5.dto.UserResponse;
import com.bcnd.laboratories5.entity.User;

public final class UserMapper {
    private UserMapper() {}

    public static User toEntity(UserRegistrationRequest req) {
        return User.builder()
                .username(req.username().trim())
                .email(req.email().trim().toLowerCase())
                .password(req.password())
                .age(req.age())
                .build();
    }

    public static UserResponse toResponse(User user) {
        return new UserResponse(user.getId(), user.getUsername(), user.getEmail(), user.getAge());
    }
}
