package com.example.studentskillassessment.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class OtpService {

    private final Map<String, String> otpStorage = new HashMap<>();

    private final JavaMailSender mailSender;

    public OtpService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public String generateOtp(String email) {

        String otp = String.format(
                "%06d",
                new Random().nextInt(1000000)
        );

        otpStorage.put(email, otp);

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("studentskillassessment@gmail.com");
        message.setTo(email);
        message.setSubject(
                "Student Skill Assessment - Email Verification OTP"
        );

        message.setText(
                "Hello,\n\n" +
                "Your Student Skill Assessment verification OTP is:\n\n" +
                otp +
                "\n\n" +
                "Please enter this OTP in the application to verify your email.\n\n" +
                "Regards,\n" +
                "Student Skill Assessment Team"
        );

        mailSender.send(message);

        return otp;
    }

    public boolean verifyOtp(String email, String otp) {

        String storedOtp = otpStorage.get(email);

        if (storedOtp == null) {
            return false;
        }

        if (storedOtp.equals(otp)) {
            otpStorage.remove(email);
            return true;
        }

        return false;
    }
}