package com.example.project2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender){
        this.mailSender = mailSender;
    }

    public void sendEmail(String toEmail, String subject, String text){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("BudgetMunchTeam@gmail.com");
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(text);

        mailSender.send(message);

        System.out.println("Mail Sent successfully...");
    }
}
