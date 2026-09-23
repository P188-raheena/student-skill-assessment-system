package com.example.studentskillassessment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.studentskillassessment.entity.Assessment;
import com.example.studentskillassessment.entity.AssessmentAnswer;
import com.example.studentskillassessment.entity.Question;
import com.example.studentskillassessment.entity.Student;
import com.example.studentskillassessment.exception.ResourceNotFoundException;
import com.example.studentskillassessment.repository.AssessmentAnswerRepository;
import com.example.studentskillassessment.repository.AssessmentRepository;
import com.example.studentskillassessment.repository.QuestionRepository;
import com.example.studentskillassessment.repository.StudentRepository;

@Service
public class AssessmentAnswerService {

    private final AssessmentAnswerRepository assessmentAnswerRepository;
    private final StudentRepository studentRepository;
    private final AssessmentRepository assessmentRepository;
    private final QuestionRepository questionRepository;

    public AssessmentAnswerService(
            AssessmentAnswerRepository assessmentAnswerRepository,
            StudentRepository studentRepository,
            AssessmentRepository assessmentRepository,
            QuestionRepository questionRepository) {

        this.assessmentAnswerRepository = assessmentAnswerRepository;
        this.studentRepository = studentRepository;
        this.assessmentRepository = assessmentRepository;
        this.questionRepository = questionRepository;
    }

    public AssessmentAnswer submitAnswer(
            Long studentId,
            Long assessmentId,
            Long questionId,
            String selectedAnswer) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student not found with id: " + studentId));

        Assessment assessment = assessmentRepository.findById(assessmentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Assessment not found with id: " + assessmentId));

        Question question = questionRepository.findById(questionId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Question not found with id: " + questionId));

    boolean alreadyAnswered =
        assessmentAnswerRepository
                .findByStudent_IdAndAssessment_Id(
                        studentId,
                        assessmentId
                )
                .stream()
                .anyMatch(answer ->
                        answer.getQuestion().getId().equals(questionId)
                );

if (alreadyAnswered) {
    throw new IllegalArgumentException(
            "This question has already been answered by the student"
    );
}

boolean correct = question.getCorrectAnswer()
        .equalsIgnoreCase(selectedAnswer);

AssessmentAnswer answer = new AssessmentAnswer(
        student,
        assessment,
        question,
        selectedAnswer,
        correct
);

        return assessmentAnswerRepository.save(answer);
    }

    public List<AssessmentAnswer> getAnswersByStudent(Long studentId) {
        return assessmentAnswerRepository.findByStudent_Id(studentId);
    }

    public List<AssessmentAnswer> getAnswersByAssessment(Long assessmentId) {
        return assessmentAnswerRepository.findByAssessment_Id(assessmentId);
    }

    public List<AssessmentAnswer> getStudentAssessmentAnswers(
            Long studentId,
            Long assessmentId) {

        return assessmentAnswerRepository
                .findByStudent_IdAndAssessment_Id(
                        studentId,
                        assessmentId
                );
    }
}