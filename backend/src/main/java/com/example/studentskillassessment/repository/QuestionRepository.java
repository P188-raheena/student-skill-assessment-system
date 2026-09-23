package com.example.studentskillassessment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.studentskillassessment.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}