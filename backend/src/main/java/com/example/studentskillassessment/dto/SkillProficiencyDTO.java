package com.example.studentskillassessment.dto;

public class SkillProficiencyDTO {

    private Long skillId;
    private String skillName;
    private double percentage;
    private String proficiency;

    public SkillProficiencyDTO() {
    }

    public SkillProficiencyDTO(
            Long skillId,
            String skillName,
            double percentage,
            String proficiency) {

        this.skillId = skillId;
        this.skillName = skillName;
        this.percentage = percentage;
        this.proficiency = proficiency;
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

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public String getProficiency() {
        return proficiency;
    }

    public void setProficiency(String proficiency) {
        this.proficiency = proficiency;
    }
}