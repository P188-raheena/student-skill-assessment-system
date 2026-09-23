# Backend ↔ Database Mapping

This document explains how the Spring Boot backend should map to the MySQL database.

## Database

Database name:

student_skill_assessment

Database technology:

MySQL

---

## 1. Student

### Database table

students

### Columns

| Database Column | Java Field | Notes |
|---|---|---|
| student_id | id | Primary Key |
| name | name | Student name |
| email | email | Unique email |

The Java `id` field should map to `student_id`.

---

## 2. Skill

### Database table

skills

### Columns

| Database Column | Java Field | Notes |
|---|---|---|
| skill_id | id | Primary Key |
| skill_name | name | Skill name |
| description | description | Skill description |

The Java `id` field should map to `skill_id`.

The Java `name` field should map to `skill_name`.

---

## 3. Assessment

### Database table

assessments

### Columns

| Database Column | Java Field | Notes |
|---|---|---|
| assessment_id | id | Primary Key |
| assessment_name | assessmentName/title | Assessment name |
| skill_id | skill | Foreign Key → skills.skill_id |

Each assessment belongs to one skill.

Relationship:

Assessment → Skill

Many assessments can belong to a skill.

---

## 4. Question

### Database table

questions

### Columns

| Database Column | Java Field | Notes |
|---|---|---|
| question_id | id | Primary Key |
| assessment_id | assessment | Foreign Key → assessments.assessment_id |
| question_text | questionText | Question text |
| option_a | optionA | Option A |
| option_b | optionB | Option B |
| option_c | optionC | Option C |
| option_d | optionD | Option D |
| correct_option | correctOption/correctAnswer | Correct option |

Each question belongs to one assessment.

Relationship:

Question → Assessment

---

## 5. Assessment Attempt

### Database table

assessment_attempts

### Required backend entity

AssessmentAttempt

### Columns

| Database Column | Java Field | Notes |
|---|---|---|
| attempt_id | id | Primary Key |
| student_id | student | Foreign Key → students.student_id |
| assessment_id | assessment | Foreign Key → assessments.assessment_id |
| started_at | startedAt | Attempt start time |
| submitted_at | submittedAt | Attempt submission time |

This table supports multiple attempts.

Example:

Student → Java Assessment → Attempt 1

Student → Java Assessment → Attempt 2

Student → Java Assessment → Attempt 3

---

## 6. Answer

### Database table

answers

### Columns

| Database Column | Java Field | Notes |
|---|---|---|
| answer_id | id | Primary Key |
| attempt_id | attempt | Foreign Key → assessment_attempts.attempt_id |
| question_id | question | Foreign Key → questions.question_id |
| selected_option | selectedOption | Student's selected option |
| is_correct | isCorrect | Whether the answer is correct |

Constraint:

One question can have only one answer within a particular attempt.

Unique key:

(attempt_id, question_id)

---

## 7. Result

### Database table

results

### Columns

| Database Column | Java Field | Notes |
|---|---|---|
| result_id | id | Primary Key |
| attempt_id | attempt | Foreign Key → assessment_attempts.attempt_id |
| total_questions | totalQuestions | Total questions |
| correct_answers | correctAnswers | Correct answers |
| score | score | Score |
| percentage | percentage | Percentage |

Each assessment attempt has one result.

Relationship:

AssessmentAttempt → Result

---

# Overall Relationships

```text
Student
   |
   | 1 : Many
   ↓
AssessmentAttempt
   |
   ├──────────────→ Answer
   |
   └──────────────→ Result


Skill
   |
   | 1 : Many
   ↓
Assessment
   |
   | 1 : Many
   ↓
Question