package com.example.studentskillassessment.controller;

import com.example.studentskillassessment.dto.AssessmentAnswerDTO;
import com.example.studentskillassessment.entity.AssessmentAnswer;
import com.example.studentskillassessment.service.AssessmentAnswerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/answers")
public class AssessmentAnswerController {

    private final AssessmentAnswerService assessmentAnswerService;

    public AssessmentAnswerController(
            AssessmentAnswerService assessmentAnswerService) {
        this.assessmentAnswerService = assessmentAnswerService;
    }

    @PostMapping
    public ResponseEntity<AssessmentAnswer> submitAnswer(
            @Valid @RequestBody AssessmentAnswerDTO dto) {

        AssessmentAnswer answer =
                assessmentAnswerService.submitAnswer(
                        dto.getStudentId(),
                        dto.getAssessmentId(),
                        dto.getQuestionId(),
                        dto.getSelectedAnswer()
                );

        return new ResponseEntity<>(
                answer,
                HttpStatus.CREATED
        );
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<AssessmentAnswer>> getAnswersByStudent(
            @PathVariable Long studentId) {

        return ResponseEntity.ok(
                assessmentAnswerService
                        .getAnswersByStudent(studentId)
        );
    }

    @GetMapping("/assessment/{assessmentId}")
    public ResponseEntity<List<AssessmentAnswer>> getAnswersByAssessment(
            @PathVariable Long assessmentId) {

        return ResponseEntity.ok(
                assessmentAnswerService
                        .getAnswersByAssessment(assessmentId)
        );
    }

    @GetMapping("/student/{studentId}/assessment/{assessmentId}")
    public ResponseEntity<List<AssessmentAnswer>>
    getStudentAssessmentAnswers(
            @PathVariable Long studentId,
            @PathVariable Long assessmentId) {

        return ResponseEntity.ok(
                assessmentAnswerService
                        .getStudentAssessmentAnswers(
                                studentId,
                                assessmentId
                        )
        );
    }
}