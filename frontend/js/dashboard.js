// =========================================
// SSAS - DASHBOARD JAVASCRIPT
// =========================================


// -----------------------------------------
// STUDENT NAME
// -----------------------------------------

const studentName =
    localStorage.getItem("studentName") || "Student";

const studentNameElement =
    document.getElementById("studentName");

if (studentNameElement) {
    studentNameElement.textContent = studentName;
}


// -----------------------------------------
// ASSESSMENT SCORE
// -----------------------------------------

const assessmentScore = Number(
    localStorage.getItem("assessmentScore") || 0
);

const scoreElements =
    document.querySelectorAll(
        "#assessmentScore, #scorePercentage, #overallScore"
    );

scoreElements.forEach(function (element) {
    element.textContent = assessmentScore + "%";
});


// -----------------------------------------
// SELECTED SKILLS
// -----------------------------------------

const selectedSkills = JSON.parse(
    localStorage.getItem("selectedSkills") || "[]"
);

const skillCountElement =
    document.getElementById("skillCount");

if (skillCountElement) {
    skillCountElement.textContent =
        selectedSkills.length + " Skills Selected";
}


// -----------------------------------------
// CAREER GOAL
// -----------------------------------------

const careerGoal =
    localStorage.getItem("careerGoal") || "Not selected";

const careerGoalElement =
    document.getElementById("careerGoal");

if (careerGoalElement) {
    careerGoalElement.textContent = careerGoal;
}


// =========================================
// DASHBOARD BUTTONS
// =========================================

const buttons =
    document.querySelectorAll("button");


buttons.forEach(function (button) {

    const buttonText =
        button.textContent.trim();


    // -------------------------------------
    // SELECT CAREER
    // -------------------------------------

    if (buttonText.includes("Select Career")) {

        button.addEventListener("click", function () {

            window.location.href =
                "skill-selection.html";

        });

    }


    // -------------------------------------
    // SELECT SKILLS
    // -------------------------------------

    if (buttonText.includes("Select Skills")) {

        button.addEventListener("click", function () {

            window.location.href =
                "skill-selection.html";

        });

    }


    // -------------------------------------
    // VIEW RESULTS
    // -------------------------------------

    if (buttonText.includes("View Results")) {

        button.addEventListener("click", function () {

            window.location.href =
                "result.html";

        });

    }


    // -------------------------------------
    // START ASSESSMENT
    // -------------------------------------

    if (buttonText.includes("Start Assessment")) {

        button.addEventListener("click", function () {

            window.location.href =
                "skill-selection.html";

        });

    }


    // -------------------------------------
    // PROGRESS & ANALYTICS
    // -------------------------------------

    if (buttonText.includes("Progress & Analytics")) {

        button.addEventListener("click", function () {

            window.location.href =
                "progress.html";

        });

    }


    // -------------------------------------
    // RECOMMENDATIONS
    // -------------------------------------

    if (buttonText.includes("Recommendations")) {

        button.addEventListener("click", function () {

            window.location.href =
                "recommendations.html";

        });

    }


    // -------------------------------------
    // VIEW ALL
    // -------------------------------------

    if (buttonText.includes("View All")) {

        button.addEventListener("click", function () {

            window.location.href =
                "progress.html";

        });

    }


    // -------------------------------------
    // CAREER MATCHING
    // -------------------------------------

    if (buttonText.includes("Career Matching")) {

        button.addEventListener("click", function () {

            alert(
                "Career Matching will be available soon."
            );

        });

    }


    // -------------------------------------
    // SKILL REPORT
    // -------------------------------------

    if (buttonText.includes("Skill Report")) {

        button.addEventListener("click", function () {

            window.location.href =
                "skill-gap.html";

        });

    }


    // -------------------------------------
    // LOGOUT
    // -------------------------------------

    if (buttonText.includes("Logout")) {

        button.addEventListener("click", function () {

            localStorage.clear();

            window.location.href =
                "../index.html";

        });

    }

});


// =========================================
// PAGE LOAD MESSAGE
// =========================================

console.log(
    "SSAS Dashboard loaded successfully."
);