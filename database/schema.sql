CREATE DATABASE IF NOT EXISTS student_skill_assessment;

USE student_skill_assessment;


-- =========================================
-- 1. STUDENTS
-- =========================================

CREATE TABLE students (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE
);


-- =========================================
-- 2. SKILLS
-- =========================================

CREATE TABLE skills (
    skill_id INT AUTO_INCREMENT PRIMARY KEY,
    skill_name VARCHAR(100) NOT NULL UNIQUE,
    description VARCHAR(255)
);


-- =========================================
-- 3. ASSESSMENTS
-- Each assessment belongs to one skill
-- =========================================

CREATE TABLE assessments (
    assessment_id INT AUTO_INCREMENT PRIMARY KEY,
    assessment_name VARCHAR(150) NOT NULL,
    skill_id INT NOT NULL,

    CONSTRAINT fk_assessment_skill
        FOREIGN KEY (skill_id)
        REFERENCES skills(skill_id)
);


-- =========================================
-- 4. QUESTIONS
-- Each question belongs to one assessment
-- =========================================

CREATE TABLE questions (
    question_id INT AUTO_INCREMENT PRIMARY KEY,
    assessment_id INT NOT NULL,
    question_text TEXT NOT NULL,

    option_a VARCHAR(255) NOT NULL,
    option_b VARCHAR(255) NOT NULL,
    option_c VARCHAR(255) NOT NULL,
    option_d VARCHAR(255) NOT NULL,

    correct_option CHAR(1) NOT NULL,

    CONSTRAINT fk_question_assessment
        FOREIGN KEY (assessment_id)
        REFERENCES assessments(assessment_id)
);


-- =========================================
-- 5. ANSWERS
-- Stores the student's submitted answers
-- =========================================

CREATE TABLE answers (
    answer_id INT AUTO_INCREMENT PRIMARY KEY,

    student_id INT NOT NULL,
    assessment_id INT NOT NULL,
    question_id INT NOT NULL,

    selected_option CHAR(1) NOT NULL,
    is_correct BOOLEAN NOT NULL,

    CONSTRAINT fk_answer_student
        FOREIGN KEY (student_id)
        REFERENCES students(student_id),

    CONSTRAINT fk_answer_assessment
        FOREIGN KEY (assessment_id)
        REFERENCES assessments(assessment_id),

    CONSTRAINT fk_answer_question
        FOREIGN KEY (question_id)
        REFERENCES questions(question_id)
);


-- =========================================
-- 6. RESULTS
-- Stores the final assessment result
-- =========================================

CREATE TABLE results (
    result_id INT AUTO_INCREMENT PRIMARY KEY,

    student_id INT NOT NULL,
    assessment_id INT NOT NULL,

    total_questions INT NOT NULL,
    correct_answers INT NOT NULL,
    score DECIMAL(5,2) NOT NULL,
    percentage DECIMAL(5,2) NOT NULL,

    CONSTRAINT fk_result_student
        FOREIGN KEY (student_id)
        REFERENCES students(student_id),

    CONSTRAINT fk_result_assessment
        FOREIGN KEY (assessment_id)
        REFERENCES assessments(assessment_id)
);