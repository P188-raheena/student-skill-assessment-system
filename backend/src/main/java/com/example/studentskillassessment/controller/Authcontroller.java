package com.example.studentskillassessment.controller;

import com.example.studentskillassessment.service.OtpService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private OtpService otpService;

    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOtp(
            @RequestBody Map<String, String> request) {

        String email = request.get("email");

        if (email == null || email.isBlank()) {
            return ResponseEntity.badRequest()
                    .body("Email is required");
        }

        otpService.generateOtp(email);

        return ResponseEntity.ok("OTP generated successfully");
    }

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