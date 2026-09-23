package com.example.studentskillassessment.controller;

import com.example.studentskillassessment.dto.AssessmentDTO;
import com.example.studentskillassessment.entity.Assessment;
import com.example.studentskillassessment.entity.Question;
import com.example.studentskillassessment.service.AssessmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assessments")
public class AssessmentController {

    private final AssessmentService assessmentService;

    public AssessmentController(AssessmentService assessmentService) {
        this.assessmentService = assessmentService;
    }

    @PostMapping
    public ResponseEntity<AssessmentDTO> createAssessment(
            @Valid @RequestBody AssessmentDTO dto) {

        Assessment assessment = convertToEntity(dto);

        Assessment savedAssessment =
                assessmentService.createAssessment(assessment);

        return new ResponseEntity<>(
                convertToDTO(savedAssessment),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<AssessmentDTO>> getAllAssessments() {

        List<AssessmentDTO> assessments =
                assessmentService.getAllAssessments()
                        .stream()
                        .map(this::convertToDTO)
                        .toList();

        return ResponseEntity.ok(assessments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssessmentDTO> getAssessmentById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                convertToDTO(
                        assessmentService.getAssessmentById(id)
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssessmentDTO> updateAssessment(
            @PathVariable Long id,
            @Valid @RequestBody AssessmentDTO dto) {

        Assessment assessment = convertToEntity(dto);

        Assessment updatedAssessment =
                assessmentService.updateAssessment(id, assessment);

        return ResponseEntity.ok(
                convertToDTO(updatedAssessment)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssessment(
            @PathVariable Long id) {

        assessmentService.deleteAssessment(id);

        return ResponseEntity.noContent().build();
    }

    private Assessment convertToEntity(AssessmentDTO dto) {

        Assessment assessment = new Assessment();

        assessment.setTitle(dto.getTitle());
        assessment.setDescription(dto.getDescription());

        if (dto.getQuestionIds() != null) {

            List<Question> questions = dto.getQuestionIds()
                    .stream()
                    .map(id -> {
                        Question question = new Question();
                        question.setId(id);
                        return question;
                    })
                    .toList();

            assessment.setQuestions(questions);
        }

        return assessment;
    }

    private AssessmentDTO convertToDTO(Assessment assessment) {

        List<Long> questionIds = null;

        if (assessment.getQuestions() != null) {
            questionIds = assessment.getQuestions()
                    .stream()
                    .map(Question::getId)
                    .toList();
        }

        return new AssessmentDTO(
                assessment.getId(),
                assessment.getTitle(),
                assessment.getDescription(),
                assessment.getTotalQuestions(),
                questionIds
        );
    }
}