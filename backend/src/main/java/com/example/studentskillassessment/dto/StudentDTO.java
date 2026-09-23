package com.example.studentskillassessment.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class StudentDTO {

    private Long id;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Enter a valid email address")
    private String email;

    @NotBlank(message = "Branch is required")
    @Size(max = 100, message = "Branch must not exceed 100 characters")
    private String branch;

    @NotNull(message = "Year is required")
    @Min(value = 1, message = "Year must be at least 1")
    @Max(value = 5, message = "Year must not be greater than 5")
    private Integer year;

    public StudentDTO() {
    }

    public StudentDTO(Long id, String name, String email,
                      String branch, Integer year) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.branch = branch;
        this.year = year;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}