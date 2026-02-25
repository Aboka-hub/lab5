package com.bcnd.laboratories5.service;


import com.bcnd.laboratories5.dto.StudentCreateRequest;
import com.bcnd.laboratories5.dto.StudentResponse;
import com.bcnd.laboratories5.dto.StudentUpdateRequest;
import com.bcnd.laboratories5.entity.Student;
import com.bcnd.laboratories5.exception.DuplicateEmailException;
import com.bcnd.laboratories5.exception.StudentNotFoundException;
import com.bcnd.laboratories5.mapper.StudentMapper;
import com.bcnd.laboratories5.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository repo;

    @Transactional
    public StudentResponse create(StudentCreateRequest req) {
        String email = req.email().trim().toLowerCase();
        if (repo.existsByEmailIgnoreCase(email)) {
            throw new DuplicateEmailException(email);
        }
        Student saved = repo.save(StudentMapper.toEntity(req));
        return StudentMapper.toResponse(saved);
    }

    @Transactional
    public StudentResponse update(Long id, StudentUpdateRequest req) {
        Student student = repo.findById(id).orElseThrow(() -> new StudentNotFoundException(id));

        if (req.email() != null) {
            String newEmail = req.email().trim().toLowerCase();
            if (!newEmail.equalsIgnoreCase(student.getEmail()) && repo.existsByEmailIgnoreCase(newEmail)) {
                throw new DuplicateEmailException(newEmail);
            }
        }

        StudentMapper.applyPartialUpdate(student, req);
        Student saved = repo.save(student);
        return StudentMapper.toResponse(saved);
    }

    @Transactional(readOnly = true)
    public StudentResponse getById(Long id) {
        return repo.findById(id)
                .map(StudentMapper::toResponse)
                .orElseThrow(() -> new StudentNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public List<StudentResponse> getAll() {
        return repo.findAll().stream().map(StudentMapper::toResponse).toList();
    }

    @Transactional
    public void delete(Long id) {
        if (!repo.existsById(id)) throw new StudentNotFoundException(id);
        repo.deleteById(id);
    }
}
