package com.example.studentskillassessment.controller;

import com.example.studentskillassessment.dto.SkillDTO;
import com.example.studentskillassessment.entity.Skill;
import com.example.studentskillassessment.service.SkillService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
public class SkillController {

    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @PostMapping
    public ResponseEntity<SkillDTO> createSkill(
            @Valid @RequestBody SkillDTO dto) {

        Skill skill = new Skill(
                dto.getName(),
                dto.getDescription()
        );

        Skill savedSkill = skillService.createSkill(skill);

        return new ResponseEntity<>(
                convertToDTO(savedSkill),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<SkillDTO>> getAllSkills() {

        List<SkillDTO> skills = skillService.getAllSkills()
                .stream()
                .map(this::convertToDTO)
                .toList();

        return ResponseEntity.ok(skills);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SkillDTO> getSkillById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                convertToDTO(
                        skillService.getSkillById(id)
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<SkillDTO> updateSkill(
            @PathVariable Long id,
            @Valid @RequestBody SkillDTO dto) {

        Skill skill = new Skill(
                dto.getName(),
                dto.getDescription()
        );

        Skill updatedSkill =
                skillService.updateSkill(id, skill);

        return ResponseEntity.ok(
                convertToDTO(updatedSkill)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSkill(
            @PathVariable Long id) {

        skillService.deleteSkill(id);

        return ResponseEntity.noContent().build();
    }

    private SkillDTO convertToDTO(Skill skill) {

        return new SkillDTO(
                skill.getId(),
                skill.getName(),
                skill.getDescription()
        );
    }
}