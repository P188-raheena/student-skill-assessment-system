package com.example.studentskillassessment.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class Otpservice {

    private final Map<String, String> otpStorage = new HashMap<>();

    public String generateOtp(String email) {

        String otp = String.format(
                "%06d",
                new Random().nextInt(1000000)
        );

        otpStorage.put(email, otp);

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