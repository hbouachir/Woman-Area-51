package tn.sirine.spring.sms;


public interface SmsSender {

    void sendSms(String smsRequest,String message);

    // or maybe void sendSms(String phoneNumber, String message);
}
