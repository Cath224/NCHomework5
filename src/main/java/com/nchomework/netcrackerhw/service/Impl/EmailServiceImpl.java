package com.nchomework.netcrackerhw.service.Impl;

import com.nchomework.netcrackerhw.model.Message;
import com.nchomework.netcrackerhw.model.Person;
import com.nchomework.netcrackerhw.service.api.EmailService;
import com.nchomework.netcrackerhw.service.api.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EmailServiceImpl implements EmailService {


    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private PersonService personService;

    @Override
    public void sendEmailByPersonId(UUID personId, Message message) {
        Person person = personService.getById(personId);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(person.getEmail());
        mailMessage.setSubject(message.getSubject());
        mailMessage.setText(message.getContent());

        mailSender.send(mailMessage);
    }

    @Override
    public void sendEmail(String email, Message message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject(message.getSubject());
        mailMessage.setText(message.getContent());

        mailSender.send(mailMessage);
    }
}
