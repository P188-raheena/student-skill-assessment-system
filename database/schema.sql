CREATE DATABASE IF NOT EXISTS student_skill_assessment;

USE student_skill_assessment;

CREATE TABLE students (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE
);

CREATE TABLE skills (
    skill_id INT AUTO_INCREMENT PRIMARY KEY,
    skill_name VARCHAR(100) NOT NULL UNIQUE,
    description VARCHAR(255)
);

CREATE TABLE assessments (
    assessment_id INT AUTO_INCREMENT PRIMARY KEY,
    assessment_name VARCHAR(150) NOT NULL,
    skill_id INT NOT NULL,

    CONSTRAINT fk_assessment_skill
        FOREIGN KEY (skill_id)
        REFERENCES skills(skill_id)
);

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

CREATE TABLE assessment_attempts (
    attempt_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    assessment_id INT NOT NULL,
    started_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    submitted_at DATETIME NULL,

    CONSTRAINT fk_attempt_student
        FOREIGN KEY (student_id)
        REFERENCES students(student_id),

    CONSTRAINT fk_attempt_assessment
        FOREIGN KEY (assessment_id)
        REFERENCES assessments(assessment_id)
);

CREATE TABLE answers (
    answer_id INT AUTO_INCREMENT PRIMARY KEY,
    attempt_id INT NOT NULL,
    question_id INT NOT NULL,
    selected_option CHAR(1) NOT NULL,
    is_correct BOOLEAN NOT NULL,

    CONSTRAINT fk_answer_attempt
        FOREIGN KEY (attempt_id)
        REFERENCES assessment_attempts(attempt_id),

    CONSTRAINT fk_answer_question
        FOREIGN KEY (question_id)
        REFERENCES questions(question_id),

    CONSTRAINT uq_attempt_question
        UNIQUE (attempt_id, question_id)
);

CREATE TABLE results (
    result_id INT AUTO_INCREMENT PRIMARY KEY,
    attempt_id INT NOT NULL UNIQUE,
    total_questions INT NOT NULL,
    correct_answers INT NOT NULL,
    score DECIMAL(5,2) NOT NULL,
    percentage DECIMAL(5,2) NOT NULL,

    CONSTRAINT fk_result_attempt
        FOREIGN KEY (attempt_id)
        REFERENCES assessment_attempts(attempt_id)
);