package com.example.studentskillassessment.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class ResultDTO {

    private Long id;

    @NotNull(message = "Student ID is required")
    private Long studentId;

    @NotNull(message = "Assessment ID is required")
    private Long assessmentId;

    @Min(value = 0, message = "Score cannot be negative")
    private Integer score;

    @Min(value = 0, message = "Total questions cannot be negative")
    private Integer totalQuestions;

    @Min(value = 0, message = "Correct answers cannot be negative")
    private Integer correctAnswers;

    @Min(value = 0, message = "Incorrect answers cannot be negative")
    private Integer incorrectAnswers;

    @DecimalMin(value = "0.0", message = "Percentage cannot be negative")
    @DecimalMax(value = "100.0", message = "Percentage cannot exceed 100")
    private Double percentage;

    public ResultDTO() {
    }

    public ResultDTO(Long id, Long studentId, Long assessmentId,
                     Integer score, Integer totalQuestions,
                     Integer correctAnswers, Integer incorrectAnswers,
                     Double percentage) {
        this.id = id;
        this.studentId = studentId;
        this.assessmentId = assessmentId;
        this.score = score;
        this.totalQuestions = totalQuestions;
        this.correctAnswers = correctAnswers;
        this.incorrectAnswers = incorrectAnswers;
        this.percentage = percentage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(Integer totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public Integer getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(Integer correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public Integer getIncorrectAnswers() {
        return incorrectAnswers;
    }

    public void setIncorrectAnswers(Integer incorrectAnswers) {
        this.incorrectAnswers = incorrectAnswers;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }
}