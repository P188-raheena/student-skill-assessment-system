package com.example.studentskillassessment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AssessmentAnswerDTO {

    @NotNull(message = "Student ID is required")
    private Long studentId;

    @NotNull(message = "Assessment ID is required")
    private Long assessmentId;

    @NotNull(message = "Question ID is required")
    private Long questionId;

    @NotBlank(message = "Selected answer is required")
    private String selectedAnswer;

    public AssessmentAnswerDTO() {
    }

    public AssessmentAnswerDTO(
            Long studentId,
            Long assessmentId,
            Long questionId,
            String selectedAnswer) {

        this.studentId = studentId;
        this.assessmentId = assessmentId;
        this.questionId = questionId;
        this.selectedAnswer = selectedAnswer;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(Long assessmentId) {
        this.assessmentId = assessmentId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getSelectedAnswer() {
        return selectedAnswer;
    }

    public void setSelectedAnswer(String selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }
}