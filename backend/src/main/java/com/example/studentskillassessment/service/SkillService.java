package com.example.studentskillassessment.service;

import com.example.studentskillassessment.entity.Skill;
import com.example.studentskillassessment.exception.DuplicateResourceException;
import com.example.studentskillassessment.exception.ResourceNotFoundException;
import com.example.studentskillassessment.repository.SkillRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillService {

    private final SkillRepository skillRepository;

    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public Skill createSkill(Skill skill) {

        if (skillRepository.existsByName(skill.getName())) {
            throw new DuplicateResourceException(
                    "Skill with this name already exists"
            );
        }

        return skillRepository.save(skill);
    }

    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    public Skill getSkillById(Long id) {

        return skillRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Skill not found with id: " + id
                        ));
    }

    public Skill updateSkill(Long id, Skill updatedSkill) {

        Skill existingSkill = getSkillById(id);

        existingSkill.setName(updatedSkill.getName());
        existingSkill.setDescription(
                updatedSkill.getDescription()
        );

        return skillRepository.save(existingSkill);
    }

    public void deleteSkill(Long id) {

        Skill skill = getSkillById(id);

        skillRepository.delete(skill);
    }
}