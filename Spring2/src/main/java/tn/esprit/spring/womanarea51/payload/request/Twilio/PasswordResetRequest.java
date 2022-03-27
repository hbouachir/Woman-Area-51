package tn.esprit.spring.womanarea51.payload.request.Twilio;

import lombok.Data;

@Data
public class PasswordResetRequest {

//    private String phoneNumber;//destination
    private String username;
    private String oneTimePassword;
}
