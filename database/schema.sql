CREATE DATABASE IF NOT EXISTS student_skill_assessment;

USE student_skill_assessment;

-- =========================================
-- 1. STUDENTS
-- =========================================

CREATE TABLE students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    branch VARCHAR(255),
    year INT
);

-- =========================================
-- 2. SKILLS
-- =========================================

CREATE TABLE skills (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    description VARCHAR(255)
);

-- =========================================
-- 3. ASSESSMENTS
-- =========================================

CREATE TABLE assessments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL UNIQUE,
    description VARCHAR(255),
    total_questions INT NOT NULL DEFAULT 0
);

-- =========================================
-- 4. QUESTIONS
-- =========================================

CREATE TABLE questions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    assessment_id INT NOT NULL,
    question_text TEXT NOT NULL,
    option_a VARCHAR(255) NOT NULL,
    option_b VARCHAR(255) NOT NULL,
    option_c VARCHAR(255) NOT NULL,
    option_d VARCHAR(255) NOT NULL,
    correct_answer VARCHAR(255) NOT NULL,
    skill_id INT NOT NULL,
    question_number INT NOT NULL,

    CONSTRAINT fk_question_assessment
        FOREIGN KEY (assessment_id)
        REFERENCES assessments(id),

    CONSTRAINT fk_question_skill
        FOREIGN KEY (skill_id)
        REFERENCES skills(id),

    CONSTRAINT uq_assessment_question_number
        UNIQUE (assessment_id, question_number),

    CONSTRAINT chk_question_correct_answer
        CHECK (correct_answer IN ('A', 'B', 'C', 'D'))
);

-- =========================================
-- 5. ASSESSMENT ANSWERS
-- =========================================

CREATE TABLE assessment_answers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    assessment_id INT NOT NULL,
    question_id INT NOT NULL,
    selected_answer VARCHAR(255) NOT NULL,
    correct BOOLEAN NOT NULL,

    CONSTRAINT fk_answer_student
        FOREIGN KEY (student_id)
        REFERENCES students(id),

    CONSTRAINT fk_answer_assessment
        FOREIGN KEY (assessment_id)
        REFERENCES assessments(id),

    CONSTRAINT fk_answer_question
        FOREIGN KEY (question_id)
        REFERENCES questions(id),

    CONSTRAINT uq_student_assessment_question
        UNIQUE (student_id, assessment_id, question_id),

    CONSTRAINT chk_answer_selected
        CHECK (selected_answer IN ('A', 'B', 'C', 'D'))
);

-- =========================================
-- 6. RESULTS
-- =========================================

CREATE TABLE results (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    assessment_id INT NOT NULL,
    score INT NOT NULL,
    total_questions INT NOT NULL,
    correct_answers INT NOT NULL,
    incorrect_answers INT NOT NULL,
    percentage DECIMAL(5,2) NOT NULL,

    CONSTRAINT fk_result_student
        FOREIGN KEY (student_id)
        REFERENCES students(id),

    CONSTRAINT fk_result_assessment
        FOREIGN KEY (assessment_id)
        REFERENCES assessments(id),

    CONSTRAINT uq_student_assessment
        UNIQUE (student_id, assessment_id),

    CONSTRAINT chk_result_values
        CHECK (
            total_questions >= 0
            AND correct_answers >= 0
            AND correct_answers <= total_questions
            AND incorrect_answers >= 0
            AND incorrect_answers <= total_questions
            AND score >= 0
            AND percentage >= 0
            AND percentage <= 100
        )
);

-- =========================================
-- VERIFICATION
-- =========================================

SHOW TABLES;