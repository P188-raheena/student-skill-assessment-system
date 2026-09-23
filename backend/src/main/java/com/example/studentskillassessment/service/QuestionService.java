package com.example.studentskillassessment.service;

import com.example.studentskillassessment.entity.Question;
import com.example.studentskillassessment.entity.Skill;
import com.example.studentskillassessment.exception.ResourceNotFoundException;
import com.example.studentskillassessment.repository.QuestionRepository;
import com.example.studentskillassessment.repository.SkillRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final SkillRepository skillRepository;

    public QuestionService(QuestionRepository questionRepository,
                           SkillRepository skillRepository) {
        this.questionRepository = questionRepository;
        this.skillRepository = skillRepository;
    }

    public Question createQuestion(Question question) {

        if (question.getSkill() == null
                || question.getSkill().getId() == null) {

            throw new IllegalArgumentException(
                    "Question must be associated with a skill"
            );
        }

        Skill skill = skillRepository.findById(
                question.getSkill().getId()
        ).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Skill not found with id: "
                                + question.getSkill().getId()
                ));

        question.setSkill(skill);

        return questionRepository.save(question);
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Question getQuestionById(Long id) {

        return questionRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Question not found with id: " + id
                        ));
    }

    public Question updateQuestion(Long id,
                                   Question updatedQuestion) {

        Question existingQuestion = getQuestionById(id);

        existingQuestion.setQuestionText(
                updatedQuestion.getQuestionText()
        );

        existingQuestion.setOptionA(
                updatedQuestion.getOptionA()
        );

        existingQuestion.setOptionB(
                updatedQuestion.getOptionB()
        );

        existingQuestion.setOptionC(
                updatedQuestion.getOptionC()
        );

        existingQuestion.setOptionD(
                updatedQuestion.getOptionD()
        );

        existingQuestion.setCorrectAnswer(
                updatedQuestion.getCorrectAnswer()
        );

        if (updatedQuestion.getSkill() != null
                && updatedQuestion.getSkill().getId() != null) {

            Skill skill = skillRepository.findById(
                    updatedQuestion.getSkill().getId()
            ).orElseThrow(() ->
                    new ResourceNotFoundException(
                            "Skill not found with id: "
                                    + updatedQuestion.getSkill().getId()
                    ));

            existingQuestion.setSkill(skill);
        }

        return questionRepository.save(existingQuestion);
    }

    public void deleteQuestion(Long id) {

        Question question = getQuestionById(id);

        questionRepository.delete(question);
    }
}