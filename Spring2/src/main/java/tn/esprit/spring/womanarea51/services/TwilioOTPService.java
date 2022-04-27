package tn.esprit.spring.womanarea51.services;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import tn.esprit.spring.womanarea51.configurations.TwilioConfig;
import tn.esprit.spring.womanarea51.entities.User;
import tn.esprit.spring.womanarea51.payload.request.Twilio.OtpStatus;
import tn.esprit.spring.womanarea51.payload.request.Twilio.PasswordResetRequest;
import tn.esprit.spring.womanarea51.payload.request.Twilio.PasswordResetResponse;
import tn.esprit.spring.womanarea51.repositories.UserRepository;

import javax.mail.internet.MimeMessage;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class TwilioOTPService {

    @Autowired
    PasswordEncoder encoder;
    @Autowired
    UserRepository userRepository;


    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private TwilioConfig twilioConfig;


    Map<String, String> otpMap = new HashMap<>();

    public Mono<PasswordResetResponse> sendOTPForPasswordReset(PasswordResetRequest passwordResetRequestDto) {
            PasswordResetResponse passwordResetResponseDto = null;




            try {

                PhoneNumber to = new PhoneNumber(userRepository.findByUsername(passwordResetRequestDto.getUsername()).get().getTel());
                PhoneNumber from = new PhoneNumber(twilioConfig.getTrialNumber());
                String otp = generateOTP();
                String otpMessage = "Dear Customer , Your OTP is ##" + otp + "##. Use this Passcode to rest your password. Thank You.";
                Message message = Message.creator(to, from,otpMessage).create();

                otpMap.put(passwordResetRequestDto.getUsername(), otp);
                passwordResetResponseDto = new PasswordResetResponse(OtpStatus.DELIVERED, otpMessage);


            } catch (Exception ex) {
                passwordResetResponseDto = new PasswordResetResponse(OtpStatus.FAILED, ex.getMessage());
            }
            return Mono.just(passwordResetResponseDto);


    }

    public Mono<String> validateOTP(String userInputOtp, String username, String newPassword) {
        if (userInputOtp.equals(otpMap.get(username))) {
            User u=userRepository.findByUsername(username).orElse(null);

            u.setPassword(encoder.encode(newPassword));
            userRepository.save(u);


            otpMap.remove(username,userInputOtp);
            return Mono.just("Valid OTP please proceed with your transaction !");
        } else {
            return Mono.error(new IllegalArgumentException("Invalid otp please retry !"));
        }
    }

    //6 digit otp
    private String generateOTP() {
        return new DecimalFormat("000000")
                .format(new Random().nextInt(999999));
    }

}
