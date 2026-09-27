package com.example.studentskillassessment.dto;

public class DashboardDTO {

    private Long studentId;
    private String studentName;
    private String email;
    private String branch;
    private Integer year;

    private int assessmentsTaken;
    private double averagePercentage;

    private int totalCorrectAnswers;
    private int totalIncorrectAnswers;

    private ResultDTO latestResult;

    public DashboardDTO(
            Long studentId,
            String studentName,
            String email,
            String branch,
            Integer year,
            int assessmentsTaken,
            double averagePercentage,
            int totalCorrectAnswers,
            int totalIncorrectAnswers,
            ResultDTO latestResult) {

        this.studentId = studentId;
        this.studentName = studentName;
        this.email = email;
        this.branch = branch;
        this.year = year;
        this.assessmentsTaken = assessmentsTaken;
        this.averagePercentage = averagePercentage;
        this.totalCorrectAnswers = totalCorrectAnswers;
        this.totalIncorrectAnswers = totalIncorrectAnswers;
        this.latestResult = latestResult;
    }

    public Long getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getEmail() {
        return email;
    }

    public String getBranch() {
        return branch;
    }

    public Integer getYear() {
        return year;
    }

    public int getAssessmentsTaken() {
        return assessmentsTaken;
    }

    public double getAveragePercentage() {
        return averagePercentage;
    }

    public int getTotalCorrectAnswers() {
        return totalCorrectAnswers;
    }

    public int getTotalIncorrectAnswers() {
        return totalIncorrectAnswers;
    }

    public ResultDTO getLatestResult() {
        return latestResult;
    }
}