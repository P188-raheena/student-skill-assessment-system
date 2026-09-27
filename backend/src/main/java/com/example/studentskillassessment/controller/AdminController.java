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

import com.example.studentskillassessment.dto.AdminDashboardDTO;
import com.example.studentskillassessment.entity.Assessment;
import com.example.studentskillassessment.entity.Question;
import com.example.studentskillassessment.entity.Skill;
import com.example.studentskillassessment.entity.Student;
import com.example.studentskillassessment.service.AdminService;
import com.example.studentskillassessment.service.AssessmentService;
import com.example.studentskillassessment.service.QuestionService;
import com.example.studentskillassessment.service.SkillService;
import com.example.studentskillassessment.service.StudentService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;
    private final AssessmentService assessmentService;
    private final SkillService skillService;
    private final StudentService studentService;
    private final QuestionService questionService;

    public AdminController(
            AdminService adminService,
            StudentService studentService,
            SkillService skillService,
            QuestionService questionService,
            AssessmentService assessmentService) {

        this.adminService = adminService;
        this.studentService = studentService;
        this.skillService = skillService;
        this.questionService = questionService;
        this.assessmentService = assessmentService;
    }

    // =========================================================
    // ADMIN DASHBOARD
    // =========================================================

    @GetMapping("/dashboard")
    public ResponseEntity<AdminDashboardDTO> getAdminDashboard() {

        return ResponseEntity.ok(
                adminService.getAdminDashboard()
        );
    }

    // =========================================================
    // STUDENT CRUD
    // =========================================================

    // Step 16.2: View all students
    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents() {

        return ResponseEntity.ok(
                studentService.getAllStudents()
        );
    }

    // Step 16.3: View one student
    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudentById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                studentService.getStudentById(id)
        );
    }

    // Step 16.4: Create student
    @PostMapping("/students")
    public ResponseEntity<Student> createStudent(
            @RequestBody Student student) {

        return ResponseEntity.ok(
                studentService.createStudent(student)
        );
    }

    // Step 16.5: Update student
    @PutMapping("/students/{id}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable Long id,
            @RequestBody Student updatedStudent) {

        return ResponseEntity.ok(
                studentService.updateStudent(id, updatedStudent)
        );
    }

    // Step 16.6: Delete student
    @DeleteMapping("/students/{id}")
    public ResponseEntity<Void> deleteStudent(
            @PathVariable Long id) {

        studentService.deleteStudent(id);

        return ResponseEntity.noContent().build();
    }

    // =========================================================
    // SKILL CRUD
    // =========================================================

    // Step 16.7: View all skills
    @GetMapping("/skills")
    public ResponseEntity<List<Skill>> getAllSkills() {

        return ResponseEntity.ok(
                skillService.getAllSkills()
        );
    }

    // Step 16.7: View skill by ID
    @GetMapping("/skills/{id}")
    public ResponseEntity<Skill> getSkillById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                skillService.getSkillById(id)
        );
    }

    // Step 16.7: Create skill
    @PostMapping("/skills")
    public ResponseEntity<Skill> createSkill(
            @RequestBody Skill skill) {

        return ResponseEntity.ok(
                skillService.createSkill(skill)
        );
    }

    // Step 16.7: Update skill
    @PutMapping("/skills/{id}")
    public ResponseEntity<Skill> updateSkill(
            @PathVariable Long id,
            @RequestBody Skill updatedSkill) {

        return ResponseEntity.ok(
                skillService.updateSkill(id, updatedSkill)
        );
    }

    // Step 16.7: Delete skill
    @DeleteMapping("/skills/{id}")
    public ResponseEntity<Void> deleteSkill(
            @PathVariable Long id) {

        skillService.deleteSkill(id);

        return ResponseEntity.noContent().build();
    }

    // =========================================================
    // QUESTION CRUD
    // =========================================================

    // Step 16.8: View all questions
    @GetMapping("/questions")
    public ResponseEntity<List<Question>> getAllQuestions() {

        return ResponseEntity.ok(
                questionService.getAllQuestions()
        );
    }

    // Step 16.8: View question by ID
    @GetMapping("/questions/{id}")
    public ResponseEntity<Question> getQuestionById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                questionService.getQuestionById(id)
        );
    }

    // Step 16.8: Create question
    @PostMapping("/questions")
    public ResponseEntity<Question> createQuestion(
            @RequestBody Question question) {

        return ResponseEntity.ok(
                questionService.createQuestion(question)
        );
    }

    // Step 16.8: Update question
    @PutMapping("/questions/{id}")
    public ResponseEntity<Question> updateQuestion(
            @PathVariable Long id,
            @RequestBody Question updatedQuestion) {

        return ResponseEntity.ok(
                questionService.updateQuestion(id, updatedQuestion)
        );
    }

    // Step 16.8: Delete question
    @DeleteMapping("/questions/{id}")
    public ResponseEntity<Void> deleteQuestion(
            @PathVariable Long id) {

        questionService.deleteQuestion(id);

        return ResponseEntity.noContent().build();
    }

    // =========================================================
    // ASSESSMENT CRUD
    // =========================================================

    // Step 16.9: View all assessments
    @GetMapping("/assessments")
    public ResponseEntity<List<Assessment>> getAllAssessments() {

        return ResponseEntity.ok(
                assessmentService.getAllAssessments()
        );
    }

    // Step 16.9: View assessment by ID
    @GetMapping("/assessments/{id}")
    public ResponseEntity<Assessment> getAssessmentById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                assessmentService.getAssessmentById(id)
        );
    }

    // Step 16.9: Create assessment
    @PostMapping("/assessments")
    public ResponseEntity<Assessment> createAssessment(
            @RequestBody Assessment assessment) {

        return ResponseEntity.ok(
                assessmentService.createAssessment(assessment)
        );
    }

    // Step 16.9: Update assessment
    @PutMapping("/assessments/{id}")
    public ResponseEntity<Assessment> updateAssessment(
            @PathVariable Long id,
            @RequestBody Assessment updatedAssessment) {

        return ResponseEntity.ok(
                assessmentService.updateAssessment(
                        id,
                        updatedAssessment
                )
        );
    }

    // Step 16.9: Delete assessment
    @DeleteMapping("/assessments/{id}")
    public ResponseEntity<Void> deleteAssessment(
            @PathVariable Long id) {

        assessmentService.deleteAssessment(id);

        return ResponseEntity.noContent().build();
    }
}