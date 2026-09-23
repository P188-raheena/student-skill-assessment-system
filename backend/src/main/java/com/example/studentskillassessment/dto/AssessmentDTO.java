package com.example.studentskillassessment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public class AssessmentDTO {

    private Long id;

    @NotBlank(message = "Assessment title is required")
    @Size(min = 2, max = 150,
            message = "Assessment title must be between 2 and 150 characters")
    private String title;

    @Size(max = 1000,
            message = "Description must not exceed 1000 characters")
    private String description;

    private Integer totalQuestions;

    @NotEmpty(message = "At least one question is required")
    private List<Long> questionIds;

    public AssessmentDTO() {
    }

    public AssessmentDTO(Long id, String title, String description,
                         Integer totalQuestions, List<Long> questionIds) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.totalQuestions = totalQuestions;
        this.questionIds = questionIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(Integer totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public List<Long> getQuestionIds() {
        return questionIds;
    }

    public void setQuestionIds(List<Long> questionIds) {
        this.questionIds = questionIds;
    }
}