package com.bcnd.laboratories5.mapper;


import com.bcnd.laboratories5.dto.StudentCreateRequest;
import com.bcnd.laboratories5.dto.StudentResponse;
import com.bcnd.laboratories5.dto.StudentUpdateRequest;
import com.bcnd.laboratories5.entity.Student;

public final class StudentMapper {
    private StudentMapper() {}

    public static Student toEntity(StudentCreateRequest req) {
        return Student.builder()
                .firstName(req.firstName().trim())
                .lastName(req.lastName().trim())
                .email(req.email().trim().toLowerCase())
                .age(req.age())
                .build();
    }

    public static void applyPartialUpdate(Student entity, StudentUpdateRequest req) {
        if (req.firstName() != null) entity.setFirstName(req.firstName().trim());
        if (req.lastName() != null) entity.setLastName(req.lastName().trim());
        if (req.email() != null) entity.setEmail(req.email().trim().toLowerCase());
        if (req.age() != null) entity.setAge(req.age());
    }

    public static StudentResponse toResponse(Student entity) {
        return new StudentResponse(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getAge()
        );
    }
}