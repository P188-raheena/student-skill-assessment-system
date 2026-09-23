package com.example.studentskillassessment.repository;

import com.example.studentskillassessment.entity.AssessmentAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssessmentAnswerRepository
        extends JpaRepository<AssessmentAnswer, Long> {

    List<AssessmentAnswer> findByStudent_Id(Long studentId);

    List<AssessmentAnswer> findByAssessment_Id(Long assessmentId);

    List<AssessmentAnswer> findByStudent_IdAndAssessment_Id(
            Long studentId,
            Long assessmentId
    );
}