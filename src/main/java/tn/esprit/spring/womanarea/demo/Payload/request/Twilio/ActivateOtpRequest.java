package tn.esprit.spring.womanarea.demo.Payload.request.Twilio;


import lombok.Data;

@Data
public class ActivateOtpRequest {
    private String username;
    private String userInputOtp;
    private String newPassword;

}
