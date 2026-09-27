package com.example.studentskillassessment.service;

import org.springframework.stereotype.Service;

import com.example.studentskillassessment.dto.AdminDashboardDTO;
import com.example.studentskillassessment.repository.AssessmentRepository;
import com.example.studentskillassessment.repository.QuestionRepository;
import com.example.studentskillassessment.repository.ResultRepository;
import com.example.studentskillassessment.repository.SkillRepository;
import com.example.studentskillassessment.repository.StudentRepository;

@Service
public class AdminService {

    private final StudentRepository studentRepository;
    private final SkillRepository skillRepository;
    private final QuestionRepository questionRepository;
    private final AssessmentRepository assessmentRepository;
    private final ResultRepository resultRepository;

    public AdminService(
            StudentRepository studentRepository,
            SkillRepository skillRepository,
            QuestionRepository questionRepository,
            AssessmentRepository assessmentRepository,
            ResultRepository resultRepository) {

        this.studentRepository = studentRepository;
        this.skillRepository = skillRepository;
        this.questionRepository = questionRepository;
        this.assessmentRepository = assessmentRepository;
        this.resultRepository = resultRepository;
    }

    public AdminDashboardDTO getAdminDashboard() {

        long totalStudents = studentRepository.count();
        long totalSkills = skillRepository.count();
        long totalQuestions = questionRepository.count();
        long totalAssessments = assessmentRepository.count();
        long totalResults = resultRepository.count();

        return new AdminDashboardDTO(
                totalStudents,
                totalSkills,
                totalQuestions,
                totalAssessments,
                totalResults
        );
    }
}