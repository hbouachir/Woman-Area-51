package tn.esprit.spring.womanarea.demo.Payload.request.Twilio;

import lombok.Data;

@Data
public class PasswordResetRequest {

//    private String phoneNumber;//destination
    private String username;
    private String oneTimePassword;
}
