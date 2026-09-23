package com.example.studentskillassessment.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.studentskillassessment.dto.SkillPerformanceDTO;
import com.example.studentskillassessment.dto.SkillStrengthDTO;
import com.example.studentskillassessment.entity.Assessment;
import com.example.studentskillassessment.entity.AssessmentAnswer;
import com.example.studentskillassessment.entity.Result;
import com.example.studentskillassessment.entity.Student;
import com.example.studentskillassessment.exception.ResourceNotFoundException;
import com.example.studentskillassessment.repository.AssessmentAnswerRepository;
import com.example.studentskillassessment.repository.AssessmentRepository;
import com.example.studentskillassessment.repository.ResultRepository;
import com.example.studentskillassessment.repository.StudentRepository;

@Service
public class ResultService {

    private final ResultRepository resultRepository;
    private final StudentRepository studentRepository;
    private final AssessmentRepository assessmentRepository;
    private final AssessmentAnswerRepository assessmentAnswerRepository;

    public ResultService(
            ResultRepository resultRepository,
            StudentRepository studentRepository,
            AssessmentRepository assessmentRepository,
            AssessmentAnswerRepository assessmentAnswerRepository) {

        this.resultRepository = resultRepository;
        this.studentRepository = studentRepository;
        this.assessmentRepository = assessmentRepository;
        this.assessmentAnswerRepository = assessmentAnswerRepository;
    }

    // Create Result
    public Result createResult(Result result) {

        if (result.getStudent() == null
                || result.getStudent().getId() == null) {

            throw new IllegalArgumentException(
                    "Result must be associated with a student");
        }

        if (result.getAssessment() == null
                || result.getAssessment().getId() == null) {

            throw new IllegalArgumentException(
                    "Result must be associated with an assessment");
        }

        Student student = studentRepository.findById(
                result.getStudent().getId()
        ).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Student not found with id: "
                                + result.getStudent().getId()
                ));

        Assessment assessment = assessmentRepository.findById(
                result.getAssessment().getId()
        ).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Assessment not found with id: "
                                + result.getAssessment().getId()
                ));

        result.setStudent(student);
        result.setAssessment(assessment);

        return resultRepository.save(result);
    }

    // Automatic Result Calculation
    public Result calculateResult(
            Long studentId,
            Long assessmentId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student not found with id: "
                                        + studentId
                        ));

        Assessment assessment = assessmentRepository.findById(assessmentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Assessment not found with id: "
                                        + assessmentId
                        ));

        List<AssessmentAnswer> answers =
                assessmentAnswerRepository
                        .findByStudent_IdAndAssessment_Id(
                                studentId,
                                assessmentId
                        );

        if (answers.isEmpty()) {
            throw new IllegalArgumentException(
                    "No answers found for this student and assessment"
            );
        }

        int totalQuestions = answers.size();

        int correctAnswers = (int) answers.stream()
                .filter(AssessmentAnswer::isCorrect)
                .count();

        int incorrectAnswers =
                totalQuestions - correctAnswers;

        int score = correctAnswers;

        double percentage =
                (correctAnswers * 100.0) / totalQuestions;

        Result result = new Result(
                student,
                assessment,
                score,
                totalQuestions,
                correctAnswers,
                incorrectAnswers,
                percentage
        );

        return resultRepository.save(result);
    }

    // Skill-wise Performance
    public List<SkillPerformanceDTO> calculateSkillPerformance(
            Long studentId,
            Long assessmentId) {

        studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student not found with id: "
                                        + studentId
                        ));

        assessmentRepository.findById(assessmentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Assessment not found with id: "
                                        + assessmentId
                        ));

        List<AssessmentAnswer> answers =
                assessmentAnswerRepository
                        .findByStudent_IdAndAssessment_Id(
                                studentId,
                                assessmentId
                        );

        if (answers.isEmpty()) {
            throw new IllegalArgumentException(
                    "No answers found for this student and assessment"
            );
        }

        Map<Long, List<AssessmentAnswer>> skillAnswers =
                new LinkedHashMap<>();

        for (AssessmentAnswer answer : answers) {

            Long skillId = answer.getQuestion()
                    .getSkill()
                    .getId();

            skillAnswers
                    .computeIfAbsent(
                            skillId,
                            key -> new ArrayList<>()
                    )
                    .add(answer);
        }

        List<SkillPerformanceDTO> performanceList =
                new ArrayList<>();

        for (Map.Entry<Long, List<AssessmentAnswer>> entry
                : skillAnswers.entrySet()) {

            List<AssessmentAnswer> skillAnswerList =
                    entry.getValue();

            int totalQuestions =
                    skillAnswerList.size();

            int correctAnswers =
                    (int) skillAnswerList.stream()
                            .filter(AssessmentAnswer::isCorrect)
                            .count();

            int incorrectAnswers =
                    totalQuestions - correctAnswers;

            double percentage =
                    (correctAnswers * 100.0)
                            / totalQuestions;

            String skillName =
                    skillAnswerList.get(0)
                            .getQuestion()
                            .getSkill()
                            .getName();

            performanceList.add(
                    new SkillPerformanceDTO(
                            entry.getKey(),
                            skillName,
                            totalQuestions,
                            correctAnswers,
                            incorrectAnswers,
                            percentage
                    )
            );
        }

        return performanceList;
    }

    // Strong / Average / Weak Skill Detection
    public List<SkillStrengthDTO> detectSkillStrength(
            Long studentId,
            Long assessmentId) {

        List<SkillPerformanceDTO> performanceList =
                calculateSkillPerformance(
                        studentId,
                        assessmentId
                );

        List<SkillStrengthDTO> strengthList =
                new ArrayList<>();

        for (SkillPerformanceDTO performance : performanceList) {

            double percentage =
                    performance.getPercentage();

            String strength;

            if (percentage >= 80) {
                strength = "Strong";
            } else if (percentage >= 50) {
                strength = "Average";
            } else {
                strength = "Weak";
            }

            strengthList.add(
                    new SkillStrengthDTO(
                            performance.getSkillId(),
                            performance.getSkillName(),
                            percentage,
                            strength
                    )
            );
        }

        return strengthList;
    }

    // Get All Results
    public List<Result> getAllResults() {
        return resultRepository.findAll();
    }

    // Get Result By ID
    public Result getResultById(Long id) {

        return resultRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Result not found with id: " + id
                        ));
    }

    // Get Results By Student
    public List<Result> getResultsByStudent(Long studentId) {

        return resultRepository.findByStudent_Id(studentId);
    }

    // Get Results By Assessment
    public List<Result> getResultsByAssessment(
            Long assessmentId) {

        return resultRepository.findByAssessment_Id(assessmentId);
    }

    // Update Result
    public Result updateResult(
            Long id,
            Result updatedResult) {

        Result existingResult =
                getResultById(id);

        existingResult.setScore(
                updatedResult.getScore()
        );

        existingResult.setTotalQuestions(
                updatedResult.getTotalQuestions()
        );

        existingResult.setCorrectAnswers(
                updatedResult.getCorrectAnswers()
        );

        existingResult.setIncorrectAnswers(
                updatedResult.getIncorrectAnswers()
        );

        existingResult.setPercentage(
                updatedResult.getPercentage()
        );

        return resultRepository.save(existingResult);
    }

    // Delete Result
    public void deleteResult(Long id) {

        Result result = getResultById(id);

        resultRepository.delete(result);
    }
}