const loginTab = document.getElementById("loginTab");
const registerTab = document.getElementById("registerTab");

const loginForm = document.getElementById("loginForm");
const registerForm = document.getElementById("registerForm");

let emailVerified = false;


// =========================
// LOGIN TAB
// =========================

loginTab.addEventListener("click", function () {

    loginTab.classList.add("active");
    registerTab.classList.remove("active");

    loginForm.classList.remove("hidden");
    registerForm.classList.add("hidden");

});


// =========================
// CREATE ACCOUNT TAB
// =========================

registerTab.addEventListener("click", function () {

    registerTab.classList.add("active");
    loginTab.classList.remove("active");

    loginForm.classList.add("hidden");
    registerForm.classList.remove("hidden");

});


// =========================
// SEND VERIFICATION CODE
// =========================

const sendCodeButton =
    document.getElementById("sendCodeButton");

const verificationMessage =
    document.getElementById("verificationMessage");

sendCodeButton.addEventListener("click", function () {

    const email =
        document.getElementById("registerEmail").value.trim();

    if (email === "") {

        verificationMessage.textContent =
            "Please enter your email address.";

        verificationMessage.style.color = "#d9534f";

        return;
    }

    emailVerified = false;

    verificationMessage.textContent =
        "Verification code sent to your email.";

    verificationMessage.style.color = "#16a36a";

});


// =========================
// VERIFY EMAIL
// =========================

const verifyButton =
    document.getElementById("verifyButton");

verifyButton.addEventListener("click", function () {

    const code =
        document.getElementById("verificationCode").value.trim();

    if (code === "") {

        verificationMessage.textContent =
            "Please enter the verification code.";

        verificationMessage.style.color = "#d9534f";

        return;
    }

    /*
       Temporary frontend verification.

       Later your backend friend will replace
       this with real OTP verification.
    */

    emailVerified = true;

    verificationMessage.textContent =
        "Email verified successfully ✓";

    verificationMessage.style.color = "#16a36a";

});


// =========================
// CREATE ACCOUNT
// =========================

const registerFormElement =
    document.getElementById("registerFormElement");

registerFormElement.addEventListener("submit", function (event) {

    event.preventDefault();


    // Check email verification

    if (!emailVerified) {

        alert("Please verify your email before creating an account.");

        return;
    }


    // Get passwords

    const password =
        document.getElementById("registerPassword").value;

    const confirmPassword =
        document.getElementById("confirmPassword").value;


    // Check password match

    if (password !== confirmPassword) {

        alert("Passwords do not match.");

        return;
    }


    // Account created

    alert("Account created successfully!");

});