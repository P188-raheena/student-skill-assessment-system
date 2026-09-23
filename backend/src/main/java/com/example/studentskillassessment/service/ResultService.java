package com.example.studentskillassessment.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.studentskillassessment.dto.ResultDTO;
import com.example.studentskillassessment.dto.SkillPerformanceDTO;
import com.example.studentskillassessment.dto.SkillProficiencyDTO;
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

    // =========================
    // CALCULATE RESULT
    // =========================

    public Result calculateResult(
            Long studentId,
            Long assessmentId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student not found with id: " + studentId));

        Assessment assessment = assessmentRepository.findById(assessmentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Assessment not found with id: " + assessmentId));

        List<AssessmentAnswer> answers =
                assessmentAnswerRepository
                        .findByStudent_IdAndAssessment_Id(
                                studentId,
                                assessmentId);

        int totalQuestions = answers.size();

        int correctAnswers = (int) answers.stream()
                .filter(AssessmentAnswer::isCorrect)
                .count();

        int incorrectAnswers =
                totalQuestions - correctAnswers;

        double percentage = totalQuestions == 0
                ? 0
                : ((double) correctAnswers / totalQuestions) * 100;

        int score = correctAnswers;

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

    // =========================
    // GET RESULT BY ID
    // =========================

    public Result getResultById(Long id) {

        return resultRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Result not found with id: " + id));
    }

    // =========================
    // GET ALL RESULTS
    // =========================

    public List<Result> getAllResults() {

        return resultRepository.findAll();
    }

    // =========================
    // GET RESULTS BY STUDENT
    // =========================

    public List<Result> getResultsByStudent(Long studentId) {

        return resultRepository.findByStudent_Id(studentId);
    }

    // =========================
    // GET RESULTS BY ASSESSMENT
    // =========================

    public List<Result> getResultsByAssessment(Long assessmentId) {

        return resultRepository.findByAssessment_Id(assessmentId);
    }

    // =========================
    // UPDATE RESULT
    // =========================

    public Result updateResult(
            Long id,
            Result updatedResult) {

        Result existingResult = resultRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Result not found with id: " + id));

        existingResult.setScore(updatedResult.getScore());
        existingResult.setTotalQuestions(
                updatedResult.getTotalQuestions());
        existingResult.setCorrectAnswers(
                updatedResult.getCorrectAnswers());
        existingResult.setIncorrectAnswers(
                updatedResult.getIncorrectAnswers());
        existingResult.setPercentage(
                updatedResult.getPercentage());

        return resultRepository.save(existingResult);
    }

    // =========================
    // DELETE RESULT
    // =========================

    public void deleteResult(Long id) {

        Result result = resultRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Result not found with id: " + id));

        resultRepository.delete(result);
    }

    // =========================
    // CONVERT TO DTO
    // =========================

    public ResultDTO convertToDTO(Result result) {

        return new ResultDTO(
                result.getId(),
                result.getStudent().getId(),
                result.getAssessment().getId(),
                result.getScore(),
                result.getTotalQuestions(),
                result.getCorrectAnswers(),
                result.getIncorrectAnswers(),
                result.getPercentage()
        );
    }

    // =========================
    // SKILL-WISE PERFORMANCE
    // =========================

    public List<SkillPerformanceDTO> calculateSkillPerformance(
            Long studentId,
            Long assessmentId) {

        List<AssessmentAnswer> answers =
                assessmentAnswerRepository
                        .findByStudent_IdAndAssessment_Id(
                                studentId,
                                assessmentId);

        Map<Long, List<AssessmentAnswer>> groupedBySkill =
                answers.stream()
                        .filter(answer ->
                                answer.getQuestion() != null &&
                                answer.getQuestion().getSkill() != null)
                        .collect(Collectors.groupingBy(
                                answer ->
                                        answer.getQuestion()
                                                .getSkill()
                                                .getId(),
                                LinkedHashMap::new,
                                Collectors.toList()
                        ));

        List<SkillPerformanceDTO> performanceList =
                new ArrayList<>();

        for (Map.Entry<Long, List<AssessmentAnswer>> entry
                : groupedBySkill.entrySet()) {

            Long skillId = entry.getKey();

            List<AssessmentAnswer> skillAnswers =
                    entry.getValue();

            String skillName =
                    skillAnswers.get(0)
                            .getQuestion()
                            .getSkill()
                            .getName();

            int totalQuestions =
                    skillAnswers.size();

            int correctAnswers = (int) skillAnswers.stream()
                    .filter(AssessmentAnswer::isCorrect)
                    .count();

            int incorrectAnswers =
                    totalQuestions - correctAnswers;

            double percentage = totalQuestions == 0
                    ? 0
                    : ((double) correctAnswers
                            / totalQuestions) * 100;

            performanceList.add(
                    new SkillPerformanceDTO(
                            skillId,
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

    // =========================
    // STRONG / AVERAGE / WEAK
    // =========================

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

    // =========================
    // PROFICIENCY LEVEL
    // =========================

    public List<SkillProficiencyDTO> detectSkillProficiency(
            Long studentId,
            Long assessmentId) {

        List<SkillPerformanceDTO> performanceList =
                calculateSkillPerformance(
                        studentId,
                        assessmentId
                );

        List<SkillProficiencyDTO> proficiencyList =
                new ArrayList<>();

        for (SkillPerformanceDTO performance : performanceList) {

            double percentage =
                    performance.getPercentage();

            String proficiency;

            if (percentage >= 90) {
                proficiency = "Expert";
            } else if (percentage >= 75) {
                proficiency = "Advanced";
            } else if (percentage >= 50) {
                proficiency = "Intermediate";
            } else {
                proficiency = "Beginner";
            }

            proficiencyList.add(
                    new SkillProficiencyDTO(
                            performance.getSkillId(),
                            performance.getSkillName(),
                            percentage,
                            proficiency
                    )
            );
        }

        return proficiencyList;
    }
}