package tn.sirine.spring.sms;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;

import tn.sirine.spring.entities.User;
import tn.sirine.spring.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("twilio")
public class TwilioSmsSender implements SmsSender {

	@Autowired
	UserRepository userRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(TwilioSmsSender.class);

    private final TwilioConfiguration twilioConfiguration;

    @Autowired
    public TwilioSmsSender(TwilioConfiguration twilioConfiguration) {
        this.twilioConfiguration = twilioConfiguration;
    }

   @Override
    public void sendSms(String smsRequest,String message) {
	   User u=userRepository.findByNumero(smsRequest);
        if (isPhoneNumberValid(smsRequest)) {
            PhoneNumber to = new PhoneNumber(smsRequest);
            PhoneNumber from = new PhoneNumber(twilioConfiguration.getTrialNumber());
            //String message = "  ***"+u.getName()+"  rak testa3mel fi bercha klam interdit 3ana ";
            MessageCreator creator = Message.creator(to, from, message);
            creator.create();
            LOGGER.info("Send sms {}", smsRequest);
        } else {
            throw new IllegalArgumentException(
                    "Phone number [" + smsRequest + "] is not a valid number"
            );
        }

    }

    private boolean isPhoneNumberValid(String phoneNumber) {
        // TODO: Implement phone number validator
        return true;
    }
}
