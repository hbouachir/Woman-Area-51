package tn.esprit.spring.womanarea.demo.Payload.request.Twilio;

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
