// Student name
const studentName =
    localStorage.getItem("studentName") || "Student";

document.getElementById("studentName").textContent =
    studentName;


// Assessment score
const score = Number(
    localStorage.getItem("assessmentScore") || 0
);

document.getElementById("latestScore").textContent =
    score + "%";

document.getElementById("currentScore").textContent =
    score + "%";


// Number of assessments
const assessmentCount =
    localStorage.getItem("assessmentScore")
        ? 1
        : 0;

document.getElementById("assessmentCount").textContent =
    assessmentCount;


// Selected skills
const selectedSkills =
    JSON.parse(
        localStorage.getItem("selectedSkills") || "[]"
    );

document.getElementById("skillsCount").textContent =
    selectedSkills.length;


// Progress message
const progressMessage =
    document.getElementById("progressMessage");

if (score >= 80) {

    progressMessage.textContent =
        "Excellent progress! Keep strengthening your skills.";

} else if (score >= 60) {

    progressMessage.textContent =
        "Good progress! Continue practicing regularly.";

} else if (score >= 40) {

    progressMessage.textContent =
        "You are making progress. Focus on your skill gaps.";

} else {

    progressMessage.textContent =
        "Start practicing your selected skills and reassess.";

}


// Dashboard
document.getElementById("dashboardBtn")
    .addEventListener("click", function () {

        window.location.href = "dashboard.html";

    });


// Recommendations
document.getElementById("recommendationBtn")
    .addEventListener("click", function () {

        window.location.href = "recommendations.html";

    });


// Assessment
document.getElementById("assessmentBtn")
    .addEventListener("click", function () {

        window.location.href = "assessment.html";

    });