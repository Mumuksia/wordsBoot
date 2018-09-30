package com.muksia.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private MailSender mailSender;

    public void sendEmail(String title, String body){

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo("yurko.zavada@gmail.com");
        simpleMailMessage.setSubject(title);
        simpleMailMessage.setFrom("mumuksiatemp@gmail.com");
        simpleMailMessage.setText(body);


        mailSender.send(simpleMailMessage);
    }
}
