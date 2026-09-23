package com.example.studentskillassessment.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.studentskillassessment.dto.SkillPerformanceDTO;
import com.example.studentskillassessment.dto.SkillStrengthDTO;
import com.example.studentskillassessment.entity.Result;
import com.example.studentskillassessment.service.ResultService;

@RestController
@RequestMapping("/api/results")
public class ResultController {

    private final ResultService resultService;

    public ResultController(ResultService resultService) {
        this.resultService = resultService;
    }

    @PostMapping("/calculate/{studentId}/{assessmentId}")
    public ResponseEntity<Result> calculateResult(
            @PathVariable Long studentId,
            @PathVariable Long assessmentId) {

        Result result = resultService.calculateResult(
                studentId,
                assessmentId
        );

        return ResponseEntity.ok(result);
    }

    @GetMapping("/skill-performance/{studentId}/{assessmentId}")
    public ResponseEntity<List<SkillPerformanceDTO>>
    getSkillPerformance(
            @PathVariable Long studentId,
            @PathVariable Long assessmentId) {

        return ResponseEntity.ok(
                resultService.calculateSkillPerformance(
                        studentId,
                        assessmentId
                )
        );
    }

    @GetMapping("/skill-strength/{studentId}/{assessmentId}")
    public ResponseEntity<List<SkillStrengthDTO>>
    getSkillStrength(
            @PathVariable Long studentId,
            @PathVariable Long assessmentId) {

        return ResponseEntity.ok(
                resultService.detectSkillStrength(
                        studentId,
                        assessmentId
                )
        );
    }

    @GetMapping
    public ResponseEntity<List<Result>> getAllResults() {
        return ResponseEntity.ok(
                resultService.getAllResults()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Result> getResultById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                resultService.getResultById(id)
        );
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Result>> getResultsByStudent(
            @PathVariable Long studentId) {

        return ResponseEntity.ok(
                resultService.getResultsByStudent(studentId)
        );
    }

    @GetMapping("/assessment/{assessmentId}")
    public ResponseEntity<List<Result>> getResultsByAssessment(
            @PathVariable Long assessmentId) {

        return ResponseEntity.ok(
                resultService.getResultsByAssessment(assessmentId)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> updateResult(
            @PathVariable Long id,
            @RequestBody Result updatedResult) {

        return ResponseEntity.ok(
                resultService.updateResult(
                        id,
                        updatedResult
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResult(
            @PathVariable Long id) {

        resultService.deleteResult(id);

        return ResponseEntity.noContent().build();
    }
}