package com.bcnd.laboratories5.service;


import com.bcnd.laboratories5.dto.UserRegistrationRequest;
import com.bcnd.laboratories5.dto.UserResponse;
import com.bcnd.laboratories5.entity.User;
import com.bcnd.laboratories5.exception.DuplicateEmailException;
import com.bcnd.laboratories5.mapper.UserMapper;
import com.bcnd.laboratories5.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repo;

    @Transactional
    public UserResponse register(UserRegistrationRequest req) {
        String email = req.email().trim().toLowerCase();
        if (repo.existsByEmailIgnoreCase(email)) {
            throw new DuplicateEmailException(email);
        }
        User saved = repo.save(UserMapper.toEntity(req));
        return UserMapper.toResponse(saved);
    }

    @Transactional(readOnly = true)
    public UserResponse getById(Long id) {
        User user = repo.findById(id).orElseThrow(() -> new RuntimeException("User not found: id=" + id));
        return UserMapper.toResponse(user);
    }
}