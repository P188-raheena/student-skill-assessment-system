# Backend ↔ Database Mapping

This document explains how the Spring Boot backend maps to the MySQL database.

## Database

**Database name:** `student_skill_assessment`

**Database technology:** MySQL

---

## 1. Student

### Database table

`students`

### Columns

| Database Column | Java Field | Notes |
|---|---|---|
| id | id | Primary Key |
| name | name | Student name |
| email | email | Unique email |
| branch | branch | Student branch |
| year | year | Student year |
| password | password | Encoded student password |
| role | role | Student role, e.g. `STUDENT` |

The Java `id` field maps to `students.id`.

The email is unique and is used for student authentication.

---

## 2. Skill

### Database table

`skills`

### Columns

| Database Column | Java Field | Notes |
|---|---|---|
| id | id | Primary Key |
| name | name | Unique skill name |
| description | description | Skill description |

---

## 3. Assessment

### Database table

`assessments`

### Columns

| Database Column | Java Field | Notes |
|---|---|---|
| id | id | Primary Key |
| title | title | Unique assessment title |
| description | description | Assessment description |
| total_questions | totalQuestions | Number of questions |

An assessment contains multiple questions.

---

## 4. Question

### Database table

`questions`

### Columns

| Database Column | Java Field | Notes |
|---|---|---|
| id | id | Primary Key |
| assessment_id | assessment | Foreign Key → assessments.id |
| question_text | questionText | Question text |
| option_a | optionA | Option A |
| option_b | optionB | Option B |
| option_c | optionC | Option C |
| option_d | optionD | Option D |
| correct_answer | correctAnswer | Correct option |
| skill_id | skill | Foreign Key → skills.id |
| question_number | questionNumber | Question number within assessment |

Each question belongs to one assessment and one skill.

---

## 5. Assessment Answer

### Database table

`assessment_answers`

### Columns

| Database Column | Java Field | Notes |
|---|---|---|
| id | id | Primary Key |
| student_id | student | Foreign Key → students.id |
| assessment_id | assessment | Foreign Key → assessments.id |
| question_id | question | Foreign Key → questions.id |
| selected_answer | selectedAnswer | Student's selected option |
| correct | correct | Whether the selected answer is correct |

A student can have only one answer for a particular question in a particular assessment.

Unique key:

`(student_id, assessment_id, question_id)`

---

## 6. Result

### Database table

`results`

### Columns

| Database Column | Java Field | Notes |
|---|---|---|
| id | id | Primary Key |
| student_id | student | Foreign Key → students.id |
| assessment_id | assessment | Foreign Key → assessments.id |
| score | score | Assessment score |
| total_questions | totalQuestions | Total questions answered |
| correct_answers | correctAnswers | Number of correct answers |
| incorrect_answers | incorrectAnswers | Number of incorrect answers |
| percentage | percentage | Percentage score |

A student can have only one result for each assessment.

Unique key:

`(student_id, assessment_id)`

---

# Overall Relationships

```text
Student
   |
   | 1 : Many
   ↓
AssessmentAnswer
   |
   ├──────────────→ Assessment
   |
   └──────────────→ Question


Student
   |
   | 1 : Many
   ↓
Result
   |
   └──────────────→ Assessment


Assessment
   |
   | 1 : Many
   ↓
Question
   |
   └──────────────→ Skill