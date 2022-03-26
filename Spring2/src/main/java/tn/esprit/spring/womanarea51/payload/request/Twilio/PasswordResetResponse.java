package tn.esprit.spring.womanarea51.payload.request.Twilio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordResetResponse {


    private OtpStatus status;
    private String message;
}
