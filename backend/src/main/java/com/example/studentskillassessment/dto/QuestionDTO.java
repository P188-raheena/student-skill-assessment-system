package com.example.studentskillassessment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class QuestionDTO {

    private Long id;

    @NotBlank(message = "Question text is required")
    @Size(max = 1000, message = "Question text must not exceed 1000 characters")
    private String questionText;

    @NotBlank(message = "Option A is required")
    @Size(max = 500, message = "Option A must not exceed 500 characters")
    private String optionA;

    @NotBlank(message = "Option B is required")
    @Size(max = 500, message = "Option B must not exceed 500 characters")
    private String optionB;

    @NotBlank(message = "Option C is required")
    @Size(max = 500, message = "Option C must not exceed 500 characters")
    private String optionC;

    @NotBlank(message = "Option D is required")
    @Size(max = 500, message = "Option D must not exceed 500 characters")
    private String optionD;

    @NotBlank(message = "Correct answer is required")
    private String correctAnswer;

    @NotNull(message = "Skill ID is required")
    private Long skillId;

    public QuestionDTO() {
    }

    public QuestionDTO(Long id, String questionText,
                       String optionA, String optionB,
                       String optionC, String optionD,
                       String correctAnswer, Long skillId) {
        this.id = id;
        this.questionText = questionText;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctAnswer = correctAnswer;
        this.skillId = skillId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }
}