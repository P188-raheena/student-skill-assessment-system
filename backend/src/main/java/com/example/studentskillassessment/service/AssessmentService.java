package com.example.studentskillassessment.service;

import com.example.studentskillassessment.entity.Assessment;
import com.example.studentskillassessment.entity.Question;
import com.example.studentskillassessment.exception.ResourceNotFoundException;
import com.example.studentskillassessment.repository.AssessmentRepository;
import com.example.studentskillassessment.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssessmentService {

    private final AssessmentRepository assessmentRepository;
    private final QuestionRepository questionRepository;

    public AssessmentService(AssessmentRepository assessmentRepository,
                             QuestionRepository questionRepository) {
        this.assessmentRepository = assessmentRepository;
        this.questionRepository = questionRepository;
    }

    public Assessment createAssessment(Assessment assessment) {

        if (assessment.getQuestions() != null) {

            List<Question> questions = assessment.getQuestions();

            List<Question> validQuestions = questions.stream()
                    .map(question -> questionRepository
                            .findById(question.getId())
                            .orElseThrow(() ->
                                    new ResourceNotFoundException(
                                            "Question not found with id: "
                                                    + question.getId()
                                    )))
                    .toList();

            assessment.setQuestions(validQuestions);
            assessment.setTotalQuestions(validQuestions.size());
        }

        return assessmentRepository.save(assessment);
    }

    public List<Assessment> getAllAssessments() {
        return assessmentRepository.findAll();
    }

    public Assessment getAssessmentById(Long id) {

        return assessmentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Assessment not found with id: " + id
                        ));
    }

    public Assessment updateAssessment(
            Long id,
            Assessment updatedAssessment) {

        Assessment existingAssessment =
                getAssessmentById(id);

        existingAssessment.setTitle(
                updatedAssessment.getTitle()
        );

        existingAssessment.setDescription(
                updatedAssessment.getDescription()
        );

        if (updatedAssessment.getQuestions() != null) {

            List<Question> questions =
                    updatedAssessment.getQuestions();

            List<Question> validQuestions =
                    questions.stream()
                            .map(question ->
                                    questionRepository
                                            .findById(question.getId())
                                            .orElseThrow(() ->
                                                    new ResourceNotFoundException(
                                                            "Question not found with id: "
                                                                    + question.getId()
                                                    )))
                            .toList();

            existingAssessment.setQuestions(
                    validQuestions
            );

            existingAssessment.setTotalQuestions(
                    validQuestions.size()
            );
        }

        return assessmentRepository.save(
                existingAssessment
        );
    }

    public void deleteAssessment(Long id) {

        Assessment assessment =
                getAssessmentById(id);

        assessmentRepository.delete(assessment);
    }
}