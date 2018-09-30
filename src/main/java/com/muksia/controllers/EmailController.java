package com.muksia.controllers;

import com.muksia.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    EmailService emailService;

    @RequestMapping("/send")
    @ResponseBody
    public void sendEmail(@RequestParam(value = "title", defaultValue = "Test") String title,
                          @RequestParam(value = "body") String body){
        emailService.sendEmail(title, body);
    }
}
