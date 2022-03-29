package tn.sirine.spring.sms;

import javax.validation.Valid;

import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.sirine.spring.entities.User;



@RestController
@RequestMapping("api/v1/sms")
public class Controller {

    private final Service service;

    @Autowired
    public Controller(Service service) {
        this.service = service;
    }

    @PostMapping
    public void sendSms(@Valid @RequestBody User smsRequest) {
       // service.sendSms(smsRequest);
    }
}
