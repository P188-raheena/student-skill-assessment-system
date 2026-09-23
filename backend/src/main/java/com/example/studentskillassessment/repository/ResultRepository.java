package com.example.studentskillassessment.repository;

import com.example.studentskillassessment.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultRepository extends JpaRepository<Result, Long> {

    List<Result> findByStudent_Id(Long studentId);

    List<Result> findByAssessment_Id(Long assessmentId);
}