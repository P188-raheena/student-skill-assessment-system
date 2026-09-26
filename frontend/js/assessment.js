// =====================================================
// QUESTION BANK
// =====================================================

const questions = [

    // =========================
    // JAVA
    // =========================

    {
        skill: "Java",
        difficulty: "Easy",
        question: "Which keyword is used to create a class in Java?",
        options: ["class", "create", "new", "object"],
        answer: 0
    },

    {
        skill: "Java",
        difficulty: "Easy",
        question: "Which method is the entry point of a Java program?",
        options: ["start()", "main()", "run()", "execute()"],
        answer: 1
    },

    {
        skill: "Java",
        difficulty: "Easy",
        question: "Which keyword is used to create an object?",
        options: ["class", "new", "this", "object"],
        answer: 1
    },

    {
        skill: "Java",
        difficulty: "Easy",
        question: "Which data type is used to store whole numbers?",
        options: ["float", "char", "int", "boolean"],
        answer: 2
    },

    {
        skill: "Java",
        difficulty: "Easy",
        question: "Which symbol is used to end a statement in Java?",
        options: [".", ":", ";", ","],
        answer: 2
    },

    {
        skill: "Java",
        difficulty: "Medium",
        question: "Which concept allows the same method name with different parameters?",
        options: [
            "Inheritance",
            "Encapsulation",
            "Method Overloading",
            "Abstraction"
        ],
        answer: 2
    },


    // =========================
    // DATA STRUCTURES
    // =========================

    {
        skill: "Data Structures",
        difficulty: "Easy",
        question: "Which data structure follows FIFO?",
        options: ["Stack", "Queue", "Tree", "Graph"],
        answer: 1
    },

    {
        skill: "Data Structures",
        difficulty: "Easy",
        question: "Which data structure follows LIFO?",
        options: ["Queue", "Array", "Stack", "Linked List"],
        answer: 2
    },

    {
        skill: "Data Structures",
        difficulty: "Easy",
        question: "Which data structure stores elements in contiguous memory locations?",
        options: ["Array", "Stack", "Queue", "Graph"],
        answer: 0
    },

    {
        skill: "Data Structures",
        difficulty: "Easy",
        question: "Which data structure uses nodes connected by links?",
        options: ["Array", "Linked List", "Stack", "Heap"],
        answer: 1
    },

    {
        skill: "Data Structures",
        difficulty: "Medium",
        question: "Which data structure is commonly used for BFS?",
        options: ["Stack", "Queue", "Array", "Heap"],
        answer: 1
    },

    {
        skill: "Data Structures",
        difficulty: "Medium",
        question: "Which data structure is commonly used for DFS?",
        options: ["Queue", "Stack", "Linked List", "Hash Table"],
        answer: 1
    },


    // =========================
    // HTML
    // =========================

    {
        skill: "HTML",
        difficulty: "Easy",
        question: "Which tag is used to create a hyperlink?",
        options: ["<p>", "<a>", "<h1>", "<link>"],
        answer: 1
    },

    {
        skill: "HTML",
        difficulty: "Easy",
        question: "Which tag is used for the largest heading?",
        options: ["<h6>", "<head>", "<h1>", "<heading>"],
        answer: 2
    },

    {
        skill: "HTML",
        difficulty: "Easy",
        question: "Which tag is used to display an image?",
        options: ["<image>", "<img>", "<src>", "<picture>"],
        answer: 1
    },

    {
        skill: "HTML",
        difficulty: "Easy",
        question: "Which tag is used to create an unordered list?",
        options: ["<ol>", "<li>", "<ul>", "<list>"],
        answer: 2
    },

    {
        skill: "HTML",
        difficulty: "Medium",
        question: "Which attribute specifies the destination of a hyperlink?",
        options: ["src", "href", "link", "target-url"],
        answer: 1
    },


    // =========================
    // CSS
    // =========================

    {
        skill: "CSS",
        difficulty: "Easy",
        question: "Which property is used to change text color?",
        options: [
            "font-size",
            "background",
            "color",
            "text-style"
        ],
        answer: 2
    },

    {
        skill: "CSS",
        difficulty: "Easy",
        question: "Which property changes the background color?",
        options: [
            "background-color",
            "color",
            "bg",
            "background-style"
        ],
        answer: 0
    },

    {
        skill: "CSS",
        difficulty: "Easy",
        question: "Which property is used to change the font size?",
        options: [
            "font",
            "text-size",
            "font-size",
            "size"
        ],
        answer: 2
    },

    {
        skill: "CSS",
        difficulty: "Easy",
        question: "Which CSS property adds space inside an element?",
        options: [
            "margin",
            "padding",
            "spacing",
            "border"
        ],
        answer: 1
    },

    {
        skill: "CSS",
        difficulty: "Medium",
        question: "Which CSS layout system is commonly used for one-dimensional layouts?",
        options: [
            "Float",
            "Flexbox",
            "Table",
            "Position"
        ],
        answer: 1
    },


    // =========================
    // JAVASCRIPT
    // =========================

    {
        skill: "JavaScript",
        difficulty: "Easy",
        question: "Which keyword declares a variable in JavaScript?",
        options: ["var", "variable", "declare", "int"],
        answer: 0
    },

    {
        skill: "JavaScript",
        difficulty: "Easy",
        question: "Which symbol is commonly used for a single-line comment?",
        options: ["//", "##", "<!--", "**"],
        answer: 0
    },

    {
        skill: "JavaScript",
        difficulty: "Easy",
        question: "Which method prints information to the browser console?",
        options: [
            "print()",
            "console.log()",
            "display()",
            "writeConsole()"
        ],
        answer: 1
    },

    {
        skill: "JavaScript",
        difficulty: "Easy",
        question: "Which keyword declares a constant?",
        options: ["constant", "const", "fixed", "static"],
        answer: 1
    },

    {
        skill: "JavaScript",
        difficulty: "Medium",
        question: "Which method adds an element to the end of an array?",
        options: [
            "push()",
            "add()",
            "insert()",
            "append()"
        ],
        answer: 0
    },


    // =========================
    // SQL
    // =========================

    {
        skill: "SQL",
        difficulty: "Easy",
        question: "Which SQL command is used to retrieve data?",
        options: ["GET", "SELECT", "FETCH", "READ"],
        answer: 1
    },

    {
        skill: "SQL",
        difficulty: "Easy",
        question: "Which command is used to add a new record?",
        options: ["ADD", "INSERT", "CREATE", "APPEND"],
        answer: 1
    },

    {
        skill: "SQL",
        difficulty: "Easy",
        question: "Which command is used to remove records?",
        options: ["DELETE", "REMOVE", "DROP", "CLEAR"],
        answer: 0
    },

    {
        skill: "SQL",
        difficulty: "Easy",
        question: "Which clause is used to filter rows?",
        options: ["ORDER BY", "GROUP BY", "WHERE", "FILTER"],
        answer: 2
    },

    {
        skill: "SQL",
        difficulty: "Medium",
        question: "Which clause is used to sort query results?",
        options: ["SORT BY", "ORDER BY", "GROUP BY", "ARRANGE"],
        answer: 1
    },


    // =========================
    // DBMS
    // =========================

    {
        skill: "DBMS",
        difficulty: "Easy",
        question: "What does DBMS stand for?",
        options: [
            "Data Backup Management System",
            "Database Management System",
            "Database Memory System",
            "Data Management Software"
        ],
        answer: 1
    },

    {
        skill: "DBMS",
        difficulty: "Easy",
        question: "Which key uniquely identifies a record?",
        options: [
            "Foreign Key",
            "Primary Key",
            "Candidate Key",
            "Composite Key"
        ],
        answer: 1
    },

    {
        skill: "DBMS",
        difficulty: "Easy",
        question: "Which key creates a relationship between tables?",
        options: [
            "Primary Key",
            "Foreign Key",
            "Super Key",
            "Unique Key"
        ],
        answer: 1
    },

    {
        skill: "DBMS",
        difficulty: "Medium",
        question: "What is normalization mainly used for?",
        options: [
            "Increasing duplicate data",
            "Reducing data redundancy",
            "Deleting databases",
            "Creating passwords"
        ],
        answer: 1
    },


    // =========================
    // OPERATING SYSTEMS
    // =========================

    {
        skill: "Operating Systems",
        difficulty: "Easy",
        question: "Which of these is an operating system?",
        options: ["Linux", "MySQL", "HTML", "Java"],
        answer: 0
    },

    {
        skill: "Operating Systems",
        difficulty: "Easy",
        question: "Which component manages processes?",
        options: [
            "Operating System",
            "Compiler",
            "Browser",
            "Database"
        ],
        answer: 0
    },

    {
        skill: "Operating Systems",
        difficulty: "Easy",
        question: "Which of these is a process state?",
        options: [
            "Ready",
            "Stored",
            "Compiled",
            "Printed"
        ],
        answer: 0
    },

    {
        skill: "Operating Systems",
        difficulty: "Medium",
        question: "Which scheduling algorithm uses a time quantum?",
        options: [
            "FCFS",
            "SJF",
            "Round Robin",
            "Priority"
        ],
        answer: 2
    }

];


// =====================================================
// GET STUDENT NAME
// =====================================================

const studentName =
    localStorage.getItem("studentName") || "Student";

document.getElementById("studentName").textContent =
    studentName;


// =====================================================
// GET SELECTED SKILLS
// =====================================================

const selectedSkills =
    JSON.parse(localStorage.getItem("selectedSkills")) || ["Java"];


// =====================================================
// FILTER QUESTIONS
// =====================================================

let assessmentQuestions =
    questions.filter(function(question) {

        return selectedSkills.includes(question.skill);

    });


// If no matching questions exist
if (assessmentQuestions.length === 0) {

    assessmentQuestions = questions.slice(0, 2);

}


// =====================================================
// VARIABLES
// =====================================================

let currentQuestion = 0;

let userAnswers = [];


// =====================================================
// DISPLAY QUESTION
// =====================================================

function displayQuestion() {

    const question =
        assessmentQuestions[currentQuestion];


    document.getElementById("questionNumber").textContent =
        "Question " +
        (currentQuestion + 1) +
        " of " +
        assessmentQuestions.length;


    document.getElementById("questionProgress").style.width =
        (
            (currentQuestion + 1) /
            assessmentQuestions.length *
            100
        ) + "%";


    document.getElementById("questionSkill").textContent =
        question.skill;


    document.getElementById("difficulty").textContent =
        question.difficulty;


    document.getElementById("questionText").textContent =
        question.question;


    const optionsContainer =
        document.getElementById("options");


    optionsContainer.innerHTML = "";


    question.options.forEach(function(option, index) {

        const label =
            document.createElement("label");


        label.className = "option";


        label.innerHTML = `
            <input
                type="radio"
                name="answer"
                value="${index}">

            <span class="option-letter">
                ${String.fromCharCode(65 + index)}
            </span>

            <span class="option-text">
                ${option}
            </span>
        `;


        optionsContainer.appendChild(label);

    });


    // Restore previous answer
    if (userAnswers[currentQuestion] !== undefined) {

        const selected =
            document.querySelector(
                `input[name="answer"][value="${userAnswers[currentQuestion]}"]`
            );


        if (selected) {

            selected.checked = true;

        }

    }


    // Previous button
    document.getElementById("previousBtn").disabled =
        currentQuestion === 0;


    // Last question
    if (
        currentQuestion ===
        assessmentQuestions.length - 1
    ) {

        document.getElementById("nextBtn").textContent =
            "Submit Assessment ✓";

    } else {

        document.getElementById("nextBtn").textContent =
            "Next →";

    }

}


// =====================================================
// SAVE ANSWER
// =====================================================

function saveAnswer() {

    const selected =
        document.querySelector(
            'input[name="answer"]:checked'
        );


    if (!selected) {

        alert("Please select an answer.");

        return false;

    }


    userAnswers[currentQuestion] =
        Number(selected.value);


    return true;

}


// =====================================================
// NEXT BUTTON
// =====================================================

document.getElementById("nextBtn")
    .addEventListener("click", function() {

        if (!saveAnswer()) {

            return;

        }


        // Last question
        if (
            currentQuestion ===
            assessmentQuestions.length - 1
        ) {

            calculateResult();

            return;

        }


        currentQuestion++;

        displayQuestion();

    });


// =====================================================
// PREVIOUS BUTTON
// =====================================================

document.getElementById("previousBtn")
    .addEventListener("click", function() {

        if (currentQuestion > 0) {

            currentQuestion--;

            displayQuestion();

        }

    });


// =====================================================
// DASHBOARD BUTTON
// =====================================================

document.getElementById("dashboardBtn")
    .addEventListener("click", function() {

        window.location.href =
            "dashboard.html";

    });


// =====================================================
// CALCULATE RESULT
// =====================================================

function calculateResult() {

    let score = 0;


    // Object to store skill-wise results
    let skillResults = {};


    // Check every question
    assessmentQuestions.forEach(function(question, index) {


        // Create skill entry
        if (!skillResults[question.skill]) {

            skillResults[question.skill] = {
                total: 0,
                correct: 0
            };

        }


        // Count total questions for this skill
        skillResults[question.skill].total++;


        // Check whether answer is correct
        if (
            userAnswers[index] ===
            question.answer
        ) {

            score++;


            skillResults[question.skill].correct++;

        }

    });


    // =================================================
    // OVERALL RESULT
    // =================================================

    const total =
        assessmentQuestions.length;


    const percentage =
        Math.round(
            (score / total) * 100
        );


    // =================================================
    // SAVE OVERALL RESULT
    // =================================================

    localStorage.setItem(
        "assessmentScore",
        percentage
    );


    localStorage.setItem(
        "assessmentTotal",
        total
    );


    localStorage.setItem(
        "assessmentCorrect",
        score
    );


    // =================================================
    // SAVE SKILL-WISE RESULT
    // =================================================

    localStorage.setItem(
        "skillResults",
        JSON.stringify(skillResults)
    );


    // =================================================
    // GO TO RESULT PAGE
    // =================================================

    window.location.href =
        "result.html";

}


// =====================================================
// START ASSESSMENT
// =====================================================

displayQuestion();