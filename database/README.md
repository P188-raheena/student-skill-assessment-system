# Student Skill Assessment System - Database

This folder contains the database design and SQL schema for the **Student Skill Assessment System (SSAS)**.

The database is implemented using **MySQL**.

## Database Name

```text
student_skill_assessment
```

## Tables

The database contains 7 main tables:

1. `students`
2. `skills`
3. `assessments`
4. `questions`
5. `assessment_attempts`
6. `answers`
7. `results`

---

## 1. students

Stores student information.

* `student_id` - Primary Key
* `name` - Student name
* `email` - Student email

### Constraints

* `student_id` is the Primary Key.
* `email` must be unique.
* `name` and `email` cannot be NULL.

---

## 2. skills

Stores the skills available for assessment.

* `skill_id` - Primary Key
* `skill_name` - Skill name
* `description` - Skill description

### Constraints

* `skill_id` is the Primary Key.
* `skill_name` must be unique.
* `skill_name` cannot be NULL.

---

## 3. assessments

Stores assessments. Each assessment belongs to one skill.

* `assessment_id` - Primary Key
* `assessment_name` - Assessment name
* `skill_id` - Foreign Key → `skills.skill_id`

### Constraints

* `assessment_id` is the Primary Key.
* `skill_id` is a Foreign Key.
* The combination of `skill_id` and `assessment_name` must be unique.

This prevents duplicate assessment names under the same skill.

---

## 4. questions

Stores questions for each assessment.

* `question_id` - Primary Key
* `assessment_id` - Foreign Key → `assessments.assessment_id`
* `question_text` - Question
* `option_a` - Option A
* `option_b` - Option B
* `option_c` - Option C
* `option_d` - Option D
* `correct_option` - Correct option

### Constraints

* `question_id` is the Primary Key.
* `assessment_id` is a Foreign Key.
* `correct_option` must be `A`, `B`, `C`, or `D`.

---

## 5. assessment_attempts

Stores every time a student starts an assessment.

* `attempt_id` - Primary Key
* `student_id` - Foreign Key → `students.student_id`
* `assessment_id` - Foreign Key → `assessments.assessment_id`
* `started_at` - Start time
* `submitted_at` - Submission time

A student can have multiple attempts for the same assessment.

Example:

```text
Student
  |
  └── Java Assessment
        |
        ├── Attempt 1
        ├── Attempt 2
        └── Attempt 3
```

---

## 6. answers

Stores the answers given by a student during a particular attempt.

* `answer_id` - Primary Key
* `attempt_id` - Foreign Key → `assessment_attempts.attempt_id`
* `question_id` - Foreign Key → `questions.question_id`
* `selected_option` - Student's selected option
* `is_correct` - Whether the answer is correct

### Constraints

* `answer_id` is the Primary Key.
* `attempt_id` is a Foreign Key.
* `question_id` is a Foreign Key.
* `selected_option` must be `A`, `B`, `C`, or `D`.
* A question can have only one answer within a particular attempt.

This is enforced using:

```text
UNIQUE (attempt_id, question_id)
```

---

## 7. results

Stores the result of an assessment attempt.

* `result_id` - Primary Key
* `attempt_id` - Foreign Key → `assessment_attempts.attempt_id`
* `total_questions` - Total number of questions
* `correct_answers` - Number of correct answers
* `score` - Score
* `percentage` - Percentage

### Constraints

* `result_id` is the Primary Key.
* `attempt_id` is a Foreign Key.
* Each attempt can have only one result.
* `total_questions` cannot be negative.
* `correct_answers` cannot be negative.
* `correct_answers` cannot be greater than `total_questions`.
* `score` cannot be negative.
* `percentage` must be between `0` and `100`.

---

# Relationships

```text
students
    |
    | 1-to-many
    ↓
assessment_attempts
    |
    | many-to-1
    ↓
assessments
    |
    | many-to-1
    ↓
skills
```

Questions belong to assessments:

```text
assessments
    |
    | 1-to-many
    ↓
questions
```

Answers belong to attempts and questions:

```text
assessment_attempts
    |
    | 1-to-many
    ↓
answers
    |
    | many-to-1
    ↓
questions
```

Results belong to assessment attempts:

```text
assessment_attempts
    |
    | 1-to-1
    ↓
results
```

---

# Assessment Flow

```text
Student
   ↓
Select Skill
   ↓
Select Assessment
   ↓
Start Attempt
   ↓
Answer Questions
   ↓
Submit Assessment
   ↓
Evaluate Answers
   ↓
Calculate Score and Percentage
   ↓
Store Result
   ↓
View Performance
```

---

# Multiple Attempts

The database supports multiple attempts for the same assessment.

Example:

```text
Student
  |
  └── Java Assessment
        |
        ├── Attempt 1 → Result
        |
        ├── Attempt 2 → Result
        |
        └── Attempt 3 → Result
```

Each attempt has its own:

* Assessment attempt record
* Answers
* Result

This allows the system to maintain assessment history.

---

# Backend Integration

The backend uses **Java, Spring Boot, and JPA/Hibernate**.

Java entity field names do not have to be identical to database column names.

For example:

```text
Database Column       Java Field
----------------------------------------
student_id        →   id
skill_id          →   id
skill_name        →   name
assessment_id     →   id
assessment_name   →   assessmentName
question_id       →   id
question_text     →   questionText
correct_option    →   correctOption
```

JPA annotations such as `@Column`, `@ManyToOne`, and `@OneToMany` can be used to map Java entities to the database.

The backend should have entities for:

```text
Student
Skill
Assessment
Question
AssessmentAttempt
Answer
Result
```

`AssessmentAttempt` is required because the database supports multiple attempts.

`Result` is connected to `AssessmentAttempt`, rather than directly storing `student_id` and `assessment_id`.

For detailed backend mappings, see:

```text
database/BACKEND_DATABASE_MAPPING.md
```

---

# SQL Schema

The complete database creation script is available at:

```text
database/schema.sql
```

**Important:** `schema.sql` is intended for creating a fresh database structure. It should not be executed over an existing populated database without appropriate migration or table-management steps.

---

# Current Database Status

The database has been tested with sample data:

* 1 student
* 3 skills
* 3 assessments
* 9 questions
* 2 assessment attempts
* 6 answers
* 2 results

The database also contains validation constraints for:

* Assessment names
* Correct answer options
* Selected answer options
* Total questions
* Correct answer counts
* Scores
* Percentages
