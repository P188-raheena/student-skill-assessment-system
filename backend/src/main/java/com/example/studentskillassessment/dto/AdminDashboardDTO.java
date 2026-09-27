package com.example.studentskillassessment.dto;

public class AdminDashboardDTO {

    private long totalStudents;
    private long totalSkills;
    private long totalQuestions;
    private long totalAssessments;
    private long totalResults;

    public AdminDashboardDTO(
            long totalStudents,
            long totalSkills,
            long totalQuestions,
            long totalAssessments,
            long totalResults) {

        this.totalStudents = totalStudents;
        this.totalSkills = totalSkills;
        this.totalQuestions = totalQuestions;
        this.totalAssessments = totalAssessments;
        this.totalResults = totalResults;
    }

    public long getTotalStudents() {
        return totalStudents;
    }

    public long getTotalSkills() {
        return totalSkills;
    }

    public long getTotalQuestions() {
        return totalQuestions;
    }

    public long getTotalAssessments() {
        return totalAssessments;
    }

    public long getTotalResults() {
        return totalResults;
    }
}