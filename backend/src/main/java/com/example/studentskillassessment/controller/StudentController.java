package com.example.studentskillassessment.controller;

import com.example.studentskillassessment.dto.StudentDTO;
import com.example.studentskillassessment.entity.Student;
import com.example.studentskillassessment.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(
            @Valid @RequestBody StudentDTO dto) {

        Student student = new Student(
                dto.getName(),
                dto.getEmail(),
                dto.getBranch(),
                dto.getYear()
        );

        Student savedStudent =
                studentService.createStudent(student);

        return new ResponseEntity<>(
                convertToDTO(savedStudent),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents() {

        List<StudentDTO> students =
                studentService.getAllStudents()
                        .stream()
                        .map(this::convertToDTO)
                        .toList();

        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                convertToDTO(
                        studentService.getStudentById(id)
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody StudentDTO dto) {

        Student student = new Student(
                dto.getName(),
                dto.getEmail(),
                dto.getBranch(),
                dto.getYear()
        );

        Student updatedStudent =
                studentService.updateStudent(id, student);

        return ResponseEntity.ok(
                convertToDTO(updatedStudent)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(
            @PathVariable Long id) {

        studentService.deleteStudent(id);

        return ResponseEntity.noContent().build();
    }

    private StudentDTO convertToDTO(Student student) {

        return new StudentDTO(
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getBranch(),
                student.getYear()
        );
    }
}