package com.example.studentskillassessment.controller;

import com.example.studentskillassessment.dto.QuestionDTO;
import com.example.studentskillassessment.entity.Question;
import com.example.studentskillassessment.entity.Skill;
import com.example.studentskillassessment.service.QuestionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping
    public ResponseEntity<QuestionDTO> createQuestion(
            @Valid @RequestBody QuestionDTO dto) {

        Question question = convertToEntity(dto);

        Question savedQuestion =
                questionService.createQuestion(question);

        return new ResponseEntity<>(
                convertToDTO(savedQuestion),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<QuestionDTO>> getAllQuestions() {

        List<QuestionDTO> questions =
                questionService.getAllQuestions()
                        .stream()
                        .map(this::convertToDTO)
                        .toList();

        return ResponseEntity.ok(questions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionDTO> getQuestionById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                convertToDTO(
                        questionService.getQuestionById(id)
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuestionDTO> updateQuestion(
            @PathVariable Long id,
            @Valid @RequestBody QuestionDTO dto) {

        Question question = convertToEntity(dto);

        Question updatedQuestion =
                questionService.updateQuestion(id, question);

        return ResponseEntity.ok(
                convertToDTO(updatedQuestion)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(
            @PathVariable Long id) {

        questionService.deleteQuestion(id);

        return ResponseEntity.noContent().build();
    }

    private Question convertToEntity(QuestionDTO dto) {

        Question question = new Question();

        question.setQuestionText(dto.getQuestionText());
        question.setOptionA(dto.getOptionA());
        question.setOptionB(dto.getOptionB());
        question.setOptionC(dto.getOptionC());
        question.setOptionD(dto.getOptionD());
        question.setCorrectAnswer(dto.getCorrectAnswer());

        Skill skill = new Skill();
        skill.setId(dto.getSkillId());
        question.setSkill(skill);

        return question;
    }

    private QuestionDTO convertToDTO(Question question) {

        Long skillId = null;

        if (question.getSkill() != null) {
            skillId = question.getSkill().getId();
        }

        return new QuestionDTO(
                question.getId(),
                question.getQuestionText(),
                question.getOptionA(),
                question.getOptionB(),
                question.getOptionC(),
                question.getOptionD(),
                question.getCorrectAnswer(),
                skillId
        );
    }
}