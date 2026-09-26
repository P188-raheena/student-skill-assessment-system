// Student name
const studentName =
    localStorage.getItem("studentName") || "Student";

document.getElementById("studentName").textContent =
    studentName;


// Career goal
const careerGoal =
    localStorage.getItem("careerGoal") || "Not selected";

document.getElementById("careerGoal").textContent =
    careerGoal;


// Assessment score
const score = Number(
    localStorage.getItem("assessmentScore") || 0
);

document.getElementById("assessmentScore").textContent =
    score + "%";


// Dashboard
document.getElementById("dashboardBtn")
    .addEventListener("click", function () {

        window.location.href = "dashboard.html";

    });


// Skill Gap Analysis
document.getElementById("skillGapBtn")
    .addEventListener("click", function () {

        window.location.href = "skill-gap.html";

    });


// Progress
document.getElementById("progressBtn")
    .addEventListener("click", function () {

        window.location.href = "progress.html";

    });