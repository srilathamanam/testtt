package com.jobapplicant.example.Frontendversion.Service;

import com.jobapplicant.example.Frontendversion.Entity.Login;
import com.jobapplicant.example.Frontendversion.Entity.Register;
import com.jobapplicant.example.Frontendversion.Entity.RegisterwithOTP;
import com.jobapplicant.example.Frontendversion.Repository.RegisterRepository;


import com.jobapplicant.example.Frontendversion.Util.EmailUtil;
import com.jobapplicant.example.Frontendversion.Util.OtpUtil;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class RegisterService {

    @Autowired
    private OtpUtil otpUtil;
    @Autowired
    private EmailUtil emailUtil;
    @Autowired
    private RegisterRepository registerRepository;
    public RegisterwithOTP register(Register register) {

        Optional<RegisterwithOTP> existingApplicant = registerRepository.findByEmail(register.getEmail());

        if (existingApplicant.isPresent()) {
            throw new IllegalArgumentException("Email already registered");
        }
        else {

            String otp = otpUtil.generateOtp();
            try {
                emailUtil.sendOtpEmail(register.getEmail(), otp);
            } catch (MessagingException e) {
                throw new RuntimeException("Unable to send otp please try again");
            }
            RegisterwithOTP user = new RegisterwithOTP();
            user.setName(register.getName());
            user.setEmail(register.getEmail());
            user.setPassword(register.getPassword());
            user.setOtp(otp);
            user.setOtpGeneratedTime(LocalDateTime.now());
           // userRepository.save(user);

            return registerRepository.save(user);  }

    }
    public String verifyAccount(String email, String otp) {
        RegisterwithOTP user = registerRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with this email: " + email));
        if (user.getOtp().equals(otp) && Duration.between(user.getOtpGeneratedTime(),
                LocalDateTime.now()).getSeconds() < (1 * 120)) {
            user.setOtp(null);
           registerRepository.save(user);
            return "OTP verified You can continue";
        }
        return "Please regenerate otp and try again";
    }
    public String regenerateOtp(String email) {
        RegisterwithOTP user = registerRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with this email: " + email));
        String otp = otpUtil.generateOtp();
        try {
            emailUtil.sendOtpEmail(email, otp);
        } catch (MessagingException e) {
            throw new RuntimeException("Unable to send otp please try again");
        }
        user.setOtp(otp);
        user.setOtpGeneratedTime(LocalDateTime.now());
        registerRepository.save(user);
        return "Email sent... please verify account within 2 minute";
    }

    public String login(Login login) {
     RegisterwithOTP user = registerRepository.findByEmail(login.getEmail())
                .orElseThrow(
                        () -> new RuntimeException("User not found with this email: " + login.getEmail()));
        if (user != null && user.getPassword().equals(login.getPassword())) {

            return "Login Successful";
        }
        return "email password not matching or incorrect";
    }
public String forgotPassword(String email) {

    RegisterwithOTP user = registerRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found with email" + email));


    String otp = otpUtil.generateOtp();
    try {
        emailUtil.sendOtpEmail(user.getEmail(), otp);
    } catch (MessagingException e) {
        throw new RuntimeException("Unable to send otp please try again");
    }

    user.setOtp(otp);
    user.setOtpGeneratedTime(LocalDateTime.now());
    registerRepository.save(user);
    return user.getEmail();

}
public String setPassword(String email, String newpassword)
{
    RegisterwithOTP user = registerRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found with email" + email));
    user.setPassword(newpassword);
    registerRepository.save(user);
    return "new password set sucessfully";
}

}
