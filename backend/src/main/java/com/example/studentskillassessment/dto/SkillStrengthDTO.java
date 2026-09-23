package com.example.studentskillassessment.dto;

public class SkillStrengthDTO {

    private Long skillId;
    private String skillName;
    private double percentage;
    private String strength;

    public SkillStrengthDTO() {
    }

    public SkillStrengthDTO(
            Long skillId,
            String skillName,
            double percentage,
            String strength) {

        this.skillId = skillId;
        this.skillName = skillName;
        this.percentage = percentage;
        this.strength = strength;
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

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }
}