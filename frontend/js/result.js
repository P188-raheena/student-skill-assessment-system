// =====================================================
// GET STUDENT NAME
// =====================================================

const studentName =
    localStorage.getItem("studentName") || "Student";

document.getElementById("studentName").textContent =
    studentName;


// =====================================================
// GET OVERALL ASSESSMENT RESULT
// =====================================================

const score = Number(
    localStorage.getItem("assessmentScore") || 0
);

const correct = Number(
    localStorage.getItem("assessmentCorrect") || 0
);

const total = Number(
    localStorage.getItem("assessmentTotal") || 0
);


// =====================================================
// DISPLAY OVERALL RESULT
// =====================================================

document.getElementById("scorePercentage").textContent =
    score + "%";


document.getElementById("overallScore").textContent =
    score + "%";


document.getElementById("correctAnswers").textContent =
    correct;


document.getElementById("correctSummary").textContent =
    correct;


document.getElementById("totalQuestions").textContent =
    total;


document.getElementById("attemptedSummary").textContent =
    total;


// =====================================================
// SCORE MESSAGE
// =====================================================

const scoreMessage =
    document.getElementById("scoreMessage");


if (score >= 80) {

    scoreMessage.textContent =
        "Excellent performance! Keep building your skills.";

}
else if (score >= 60) {

    scoreMessage.textContent =
        "Good performance! Continue improving your skills.";

}
else if (score >= 40) {

    scoreMessage.textContent =
        "You have a good starting point. Focus on your skill gaps.";

}
else {

    scoreMessage.textContent =
        "Keep practicing and reassess your skills to improve.";

}


// =====================================================
// GET SKILL-WISE RESULTS
// =====================================================

const skillResults =
    JSON.parse(
        localStorage.getItem("skillResults") || "{}"
    );


// =====================================================
// SKILL GAP CONTAINER
// =====================================================

const skillGapMessage =
    document.querySelector(".skill-gap-message");


// =====================================================
// DISPLAY SKILL-WISE PERFORMANCE
// =====================================================

if (Object.keys(skillResults).length > 0) {

    // Clear old placeholder
    skillGapMessage.innerHTML = "";


    // Create heading
    const heading =
        document.createElement("h3");

    heading.textContent =
        "Skill-wise Performance";


    skillGapMessage.appendChild(heading);


    // Create skill list
    const skillList =
        document.createElement("div");

    skillList.className =
        "skill-performance-list";


    // Loop through each skill
    Object.keys(skillResults).forEach(function(skill) {

        const result =
            skillResults[skill];


        const skillTotal =
            result.total;


        const skillCorrect =
            result.correct;


        const skillPercentage =
            Math.round(
                (skillCorrect / skillTotal) * 100
            );


        // Determine performance
        let status = "";


        if (skillPercentage >= 80) {

            status = "Strong";

        }
        else if (skillPercentage >= 60) {

            status = "Moderate";

        }
        else {

            status = "Needs Improvement";

        }


        // Create skill card
        const skillItem =
            document.createElement("div");

        skillItem.className =
            "skill-performance-item";


        skillItem.innerHTML = `
            
            <div class="skill-performance-top">

                <strong>${skill}</strong>

                <span>${skillPercentage}%</span>

            </div>


            <div class="skill-progress-bar">

                <div
                    class="skill-progress-fill"
                    style="width: ${skillPercentage}%;">
                </div>

            </div>


            <div class="skill-performance-bottom">

                <span>
                    ${skillCorrect} / ${skillTotal} correct
                </span>

                <span>
                    ${status}
                </span>

            </div>

        `;


        skillList.appendChild(skillItem);

    });


    skillGapMessage.appendChild(skillList);

}


// =====================================================
// RECOMMENDATIONS
// =====================================================

const recommendationPlaceholder =
    document.querySelector(
        ".recommendation-placeholder"
    );


if (recommendationPlaceholder) {

    recommendationPlaceholder.innerHTML = "";


    const recommendationList =
        document.createElement("div");


    recommendationList.className =
        "recommendation-list";


    let hasWeakSkill = false;


    Object.keys(skillResults).forEach(function(skill) {

        const result =
            skillResults[skill];


        const percentage =
            Math.round(
                (result.correct / result.total) * 100
            );


        if (percentage < 60) {

            hasWeakSkill = true;


            const item =
                document.createElement("div");


            item.className =
                "recommendation-item";


            item.innerHTML = `
                <strong>📚 ${skill}</strong>

                <p>
                    Your current score is ${percentage}%.
                    Review the basic concepts and practice
                    more questions in ${skill}.
                </p>
            `;


            recommendationList.appendChild(item);

        }

    });


    // No weak skills
    if (!hasWeakSkill) {

        recommendationList.innerHTML = `
            
            <div class="recommendation-item">

                <strong>🎉 Good Progress</strong>

                <p>
                    You have performed well across your
                    selected skills. Continue practicing
                    to maintain your progress.
                </p>

            </div>

        `;

    }


    recommendationPlaceholder.appendChild(
        recommendationList
    );

}


// =====================================================
// DASHBOARD BUTTON
// =====================================================

document.getElementById("dashboardBtn")
    .addEventListener("click", function() {

        window.location.href =
            "dashboard.html";

    });


// =====================================================
// DASHBOARD ACTION BUTTON
// =====================================================

document.getElementById("dashboardActionBtn")
    .addEventListener("click", function() {

        window.location.href =
            "dashboard.html";

    });


// =====================================================
// RETAKE ASSESSMENT
// =====================================================

document.getElementById("retakeBtn")
    .addEventListener("click", function() {

        window.location.href =
            "assessment.html";

    });