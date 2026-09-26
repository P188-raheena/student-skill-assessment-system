// Load student name
const studentName = localStorage.getItem("studentName") || "Student";

document.getElementById("studentName").textContent = studentName;


// Back to Dashboard
document.getElementById("backDashboardBtn").addEventListener("click", function () {
    window.location.href = "dashboard.html";
});


// Skill selection counter
const skillCheckboxes = document.querySelectorAll(
    '.skill-option input[type="checkbox"]'
);

const selectedCount = document.getElementById("selectedCount");

function updateSelectedCount() {

    const selectedSkills = document.querySelectorAll(
        '.skill-option input[type="checkbox"]:checked'
    );

    selectedCount.textContent =
        selectedSkills.length + " selected";
}

skillCheckboxes.forEach(function (checkbox) {

    checkbox.addEventListener("change", function () {
        updateSelectedCount();
    });

});


// Continue to Assessment
document.getElementById("continueAssessmentBtn")
    .addEventListener("click", function () {

        const selectedCareer =
            document.querySelector(
                'input[name="career"]:checked'
            );

        const selectedSkills =
            document.querySelectorAll(
                '.skill-option input[type="checkbox"]:checked'
            );


        // Check career
        if (!selectedCareer) {

            alert("Please select a career goal.");

            return;
        }


        // Check skills
        if (selectedSkills.length === 0) {

            alert("Please select at least one skill.");

            return;
        }


        // Store selected career
        localStorage.setItem(
            "careerGoal",
            selectedCareer.value
        );


        // Store selected skills
        const skills = [];

        selectedSkills.forEach(function (skill) {
            skills.push(skill.value);
        });

        localStorage.setItem(
            "selectedSkills",
            JSON.stringify(skills)
        );


        // Go to assessment
        window.location.href = "assessment.html";

    });