/* =========================================================
   SKILL GAP ANALYSIS
========================================================= */


/* =========================
   STUDENT NAME
========================= */

const studentName =
    localStorage.getItem("studentName") || "Student";

document.getElementById("studentName").textContent =
    studentName;


/* =========================
   GET ASSESSMENT DATA
========================= */

const score = Number(
    localStorage.getItem("assessmentScore") || 0
);

const correct = Number(
    localStorage.getItem("assessmentCorrect") || 0
);

const total = Number(
    localStorage.getItem("assessmentTotal") || 0
);


/* =========================
   DISPLAY BASIC DATA
========================= */

document.getElementById("overallScore").textContent =
    score + "%";

document.getElementById("correctAnswers").textContent =
    correct;

document.getElementById("totalQuestions").textContent =
    total;


/* =========================
   FIND FOCUS AREAS
========================= */

let focusAreas = 0;


/*
    Skill data can be stored by the assessment page.

    Example:

    localStorage.setItem(
        "skillScores",
        JSON.stringify({
            Java: 50,
            "Data Structures": 33
        })
    );

    If skillScores are not available,
    the page uses the current assessment score.
*/

let skillScores = {};

try {

    skillScores = JSON.parse(
        localStorage.getItem("skillScores")
    ) || {};

} catch (error) {

    skillScores = {};

}


/* =========================
   DEFAULT SKILLS
========================= */

if (Object.keys(skillScores).length === 0) {

    skillScores = {
        "Java": score,
        "Data Structures": score
    };

}


/* =========================
   HELPER
========================= */

function getStatus(skillScore) {

    if (skillScore >= 80) {

        return "Strong";

    } else if (skillScore >= 60) {

        return "Good";

    } else if (skillScore >= 40) {

        return "Needs Practice";

    } else {

        return "Needs Improvement";

    }

}


/* =========================
   OVERALL MESSAGE
========================= */

const performanceTitle =
    document.getElementById("performanceTitle");

const performanceDescription =
    document.getElementById("performanceDescription");

const focusTitle =
    document.getElementById("focusTitle");

const focusDescription =
    document.getElementById("focusDescription");


if (score >= 80) {

    performanceTitle.textContent =
        "Excellent Performance";

    performanceDescription.textContent =
        "You have demonstrated strong performance across your assessment. Continue practicing to maintain and strengthen your skills.";

    focusTitle.textContent =
        "Maintain and expand your skills";

    focusDescription.textContent =
        "Continue solving practical problems and gradually move toward more advanced concepts.";

} else if (score >= 60) {

    performanceTitle.textContent =
        "Good Progress";

    performanceDescription.textContent =
        "You have a good foundation. Focus on the topics where you made mistakes and continue practicing.";

    focusTitle.textContent =
        "Strengthen your weaker topics";

    focusDescription.textContent =
        "Review concepts that caused difficulty and solve additional practice questions.";

} else if (score >= 40) {

    performanceTitle.textContent =
        "Keep Building Your Skills";

    performanceDescription.textContent =
        "You have a starting foundation, but some areas require additional practice and revision.";

    focusTitle.textContent =
        "Build stronger fundamentals";

    focusDescription.textContent =
        "Review the basic concepts of your weaker skills and practice questions regularly.";

} else {

    performanceTitle.textContent =
        "Keep Improving";

    performanceDescription.textContent =
        "You have started building your skills. Focus on the areas where your performance is lower and practice regularly.";

    focusTitle.textContent =
        "Strengthen your fundamentals";

    focusDescription.textContent =
        "Review the basic concepts of your weaker skills, practice questions, and reassess your skills after learning.";

}


/* =========================
   BUILD SKILL LISTS
========================= */

const strengthList =
    document.getElementById("strengthList");

const improvementList =
    document.getElementById("improvementList");

const performanceList =
    document.getElementById("performanceList");


strengthList.innerHTML = "";

improvementList.innerHTML = "";

performanceList.innerHTML = "";


/* =========================
   PROCESS EACH SKILL
========================= */

Object.entries(skillScores).forEach(
    ([skill, skillScore]) => {

        skillScore = Number(skillScore);

        if (isNaN(skillScore)) {
            skillScore = 0;
        }

        skillScore =
            Math.max(0, Math.min(100, skillScore));


        const firstLetter =
            skill.charAt(0).toUpperCase();

        const status =
            getStatus(skillScore);


        /* =========================
           PERFORMANCE ROW
        ========================= */

        const performanceRow =
            document.createElement("div");

        performanceRow.className =
            "performance-row";


        performanceRow.innerHTML = `

            <div class="performance-top">

                <div class="skill-title">

                    <div class="skill-symbol">
                        ${firstLetter}
                    </div>

                    <span>
                        ${skill}
                    </span>

                </div>

                <strong>
                    ${skillScore}%
                </strong>

            </div>


            <div class="progress-track">

                <div
                    class="progress-fill"
                    style="width: ${skillScore}%;">
                </div>

            </div>


            <div class="performance-status">
                ${status}
            </div>

        `;


        performanceList.appendChild(
            performanceRow
        );


        /* =========================
           SKILL ROW
        ========================= */

        const skillRow =
            document.createElement("div");

        skillRow.className =
            "skill-row";


        skillRow.innerHTML = `

            <div class="skill-row-left">

                <div class="skill-symbol">
                    ${firstLetter}
                </div>

                <div>

                    <h3>
                        ${skill}
                    </h3>

                    <span>
                        ${
                            skillScore >= 60
                            ? "Good performance"
                            : "Needs more practice"
                        }
                    </span>

                </div>

            </div>


            <strong>
                ${skillScore}%
            </strong>

        `;


        /* =========================
           STRENGTH / IMPROVEMENT
        ========================= */

        if (skillScore >= 60) {

            strengthList.appendChild(
                skillRow
            );

        } else {

            improvementList.appendChild(
                skillRow
            );

            focusAreas++;

        }

    }
);


/* =========================
   FOCUS AREA COUNT
========================= */

document.getElementById(
    "improvementCount"
).textContent = focusAreas;


/* =========================
   EMPTY STRENGTH MESSAGE
========================= */

if (strengthList.children.length === 0) {

    strengthList.innerHTML = `

        <div class="skill-row">

            <div class="skill-row-left">

                <div class="skill-symbol">
                    +
                </div>

                <div>

                    <h3>
                        No strong area yet
                    </h3>

                    <span>
                        Continue practicing to build your strengths.
                    </span>

                </div>

            </div>

        </div>

    `;

}


/* =========================
   EMPTY IMPROVEMENT MESSAGE
========================= */

if (improvementList.children.length === 0) {

    improvementList.innerHTML = `

        <div class="skill-row">

            <div class="skill-row-left">

                <div class="skill-symbol">
                    ✓
                </div>

                <div>

                    <h3>
                        No major gaps identified
                    </h3>

                    <span>
                        Continue practicing to maintain your performance.
                    </span>

                </div>

            </div>

        </div>

    `;

}


/* =========================
   DASHBOARD
========================= */

document.getElementById(
    "dashboardBtn"
).addEventListener(
    "click",
    function () {

        window.location.href =
            "dashboard.html";

    }
);


/* =========================
   BACK TO RESULT
========================= */

document.getElementById(
    "backBtn"
).addEventListener(
    "click",
    function () {

        window.location.href =
            "result.html";

    }
);


/* =========================
   RETAKE ASSESSMENT
========================= */

document.getElementById(
    "retakeBtn"
).addEventListener(
    "click",
    function () {

        window.location.href =
            "assessment.html";

    }
);