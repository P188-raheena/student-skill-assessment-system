# Student Skill Assessment System - Database

## Database

MySQL

Database name:

student_skill_assessment

## Tables

### 1. students

Stores student information.

- `student_id` - Primary Key
- `name` - Student name
- `email` - Student email

### 2. skills

Stores the skills available for assessment.

- `skill_id` - Primary Key
- `skill_name` - Skill name
- `description` - Skill description

### 3. assessments

Stores assessments. Each assessment belongs to one skill.

- `assessment_id` - Primary Key
- `assessment_name` - Assessment name
- `skill_id` - Foreign Key → skills.skill_id

### 4. questions

Stores questions for each assessment.

- `question_id` - Primary Key
- `assessment_id` - Foreign Key → assessments.assessment_id
- `question_text` - Question
- `option_a` - Option A
- `option_b` - Option B
- `option_c` - Option C
- `option_d` - Option D
- `correct_option` - Correct option

### 5. assessment_attempts

Stores every time a student starts an assessment.

- `attempt_id` - Primary Key
- `student_id` - Foreign Key → students.student_id
- `assessment_id` - Foreign Key → assessments.assessment_id
- `started_at` - Start time
- `submitted_at` - Submission time

A student can have multiple attempts for the same assessment.

### 6. answers

Stores the answers given by a student during a particular attempt.

- `answer_id` - Primary Key
- `attempt_id` - Foreign Key → assessment_attempts.attempt_id
- `question_id` - Foreign Key → questions.question_id
- `selected_option` - Student's selected option
- `is_correct` - Whether the answer is correct

Each question can have only one answer within a particular attempt.

### 7. results

Stores the result of an assessment attempt.

- `result_id` - Primary Key
- `attempt_id` - Foreign Key → assessment_attempts.attempt_id
- `total_questions` - Total number of questions
- `correct_answers` - Number of correct answers
- `score` - Score
- `percentage` - Percentage

Each attempt has one result.

## Relationships

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

assessments
    |
    | 1-to-many
    ↓
questions

assessment_attempts
    |
    | 1-to-many
    ↓
answers

assessment_attempts
    |
    | 1-to-1
    ↓
results

##Assessment Flow

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
Calculate Result
   ↓
Store Result
   ↓
View Assessment History

Multiple Attempts

Student
  |
  └── Java Assessment
        |
        ├── Attempt 1 → Result
        |
        ├── Attempt 2 → Result
        |
        └── Attempt 3 → Result