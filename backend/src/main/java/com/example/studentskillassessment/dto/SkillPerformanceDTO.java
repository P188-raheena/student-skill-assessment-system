package com.example.studentskillassessment.dto;

public class SkillPerformanceDTO {

    private Long skillId;
    private String skillName;
    private int totalQuestions;
    private int correctAnswers;
    private int incorrectAnswers;
    private double percentage;

    public SkillPerformanceDTO() {
    }

    public SkillPerformanceDTO(
            Long skillId,
            String skillName,
            int totalQuestions,
            int correctAnswers,
            int incorrectAnswers,
            double percentage) {

        this.skillId = skillId;
        this.skillName = skillName;
        this.totalQuestions = totalQuestions;
        this.correctAnswers = correctAnswers;
        this.incorrectAnswers = incorrectAnswers;
        this.percentage = percentage;
    }

    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public int getIncorrectAnswers() {
        return incorrectAnswers;
    }

    public void setIncorrectAnswers(int incorrectAnswers) {
        this.incorrectAnswers = incorrectAnswers;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
}