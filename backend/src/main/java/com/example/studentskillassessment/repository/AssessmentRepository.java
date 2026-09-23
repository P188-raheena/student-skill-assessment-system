package com.example.studentskillassessment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.studentskillassessment.entity.Assessment;

public interface AssessmentRepository extends JpaRepository<Assessment, Long> {
}