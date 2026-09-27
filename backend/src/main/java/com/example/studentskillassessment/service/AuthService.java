package com.example.studentskillassessment.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.studentskillassessment.dto.RegisterDTO;
import com.example.studentskillassessment.entity.Student;
import com.example.studentskillassessment.exception.DuplicateResourceException;
import com.example.studentskillassessment.repository.StudentRepository;

@Service
public class AuthService {

    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(
            StudentRepository studentRepository,
            PasswordEncoder passwordEncoder) {

        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Student register(RegisterDTO registerDTO) {

        if (studentRepository.existsByEmail(registerDTO.getEmail())) {
            throw new DuplicateResourceException(
                    "Student with this email already exists"
            );
        }

        if (!registerDTO.getPassword()
                .equals(registerDTO.getConfirmPassword())) {

            throw new IllegalArgumentException(
                    "Passwords do not match"
            );
        }

        Student student = new Student();

        student.setEmail(registerDTO.getEmail());
        student.setPassword(
                passwordEncoder.encode(
                        registerDTO.getPassword()
                )
        );

        student.setRole("STUDENT");

        return studentRepository.save(student);
    }
}