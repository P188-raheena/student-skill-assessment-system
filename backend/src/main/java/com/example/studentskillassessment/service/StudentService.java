package com.example.studentskillassessment.service;

import com.example.studentskillassessment.entity.Student;
import com.example.studentskillassessment.exception.DuplicateResourceException;
import com.example.studentskillassessment.exception.ResourceNotFoundException;
import com.example.studentskillassessment.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {

        if (studentRepository.existsByEmail(student.getEmail())) {
            throw new DuplicateResourceException(
                    "Student with this email already exists"
            );
        }

        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {

        return studentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student not found with id: " + id
                        ));
    }

    public Student updateStudent(Long id, Student updatedStudent) {

        Student existingStudent = getStudentById(id);

        existingStudent.setName(updatedStudent.getName());
        existingStudent.setEmail(updatedStudent.getEmail());
        existingStudent.setBranch(updatedStudent.getBranch());
        existingStudent.setYear(updatedStudent.getYear());

        return studentRepository.save(existingStudent);
    }

    public void deleteStudent(Long id) {

        Student student = getStudentById(id);

        studentRepository.delete(student);
    }
}