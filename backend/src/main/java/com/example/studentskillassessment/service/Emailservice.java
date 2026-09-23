package com.example.studentskillassessment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class Emailservice {

    @Autowired
    private JavaMailSender mailSender;

    public void sendOtpEmail(String email, String otp) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(email);
        message.setSubject("SSAS Email Verification OTP");

        message.setText(
                "Hello,\n\n" +
                "Your SSAS email verification OTP is: " + otp + "\n\n" +
                "Please use this OTP to verify your email address.\n\n" +
                "Regards,\n" +
                "Student Skill Assessment System"
        );

        mailSender.send(message);
    }
}