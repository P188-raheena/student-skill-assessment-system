package com.example.studentskillassessment.controller;

<<<<<<< ours
import java.util.Map;
=======
import com.example.studentskillassessment.service.OtpService;
>>>>>>> theirs

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.studentskillassessment.service.OtpService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private OtpService otpService;

<<<<<<< ours
    // Send OTP
=======
>>>>>>> theirs
    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOtp(
            @RequestBody Map<String, String> request) {

        String email = request.get("email");

        if (email == null || email.isBlank()) {
            return ResponseEntity.badRequest()
                    .body("Email is required");
        }

        otpService.generateOtp(email);

<<<<<<< ours
        // Development mode:
        // OTP is printed in the backend console instead of being emailed.
        System.out.println("OTP for " + email + " : " + otp);

        return ResponseEntity.ok("OTP generated successfully");
    }

    // Verify OTP
=======
        return ResponseEntity.ok("OTP generated successfully");
    }

>>>>>>> theirs
    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(
            @RequestBody Map<String, String> request) {

        String email = request.get("email");
        String otp = request.get("otp");

        if (email == null || otp == null) {
            return ResponseEntity.badRequest()
                    .body("Email and OTP are required");
        }

        boolean verified = otpService.verifyOtp(email, otp);

        if (verified) {
            return ResponseEntity.ok("Email verified successfully");
        }

        return ResponseEntity.badRequest()
                .body("Invalid OTP");
    }
}