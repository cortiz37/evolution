package com.facade;

import com.model.Email;
import com.service.EmailSenderService;
import com.service.EmailService;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.mail.MessagingException;
import java.util.List;

@Stateless
public class EmailFacade {

    @Inject
    private EmailService emailService;

    @Inject
    private EmailSenderService emailSenderService;

    public List<Email> getAll() {
        return emailService.getAll();
    }

    public boolean delete(String id) {
        return emailService.delete(id);
    }

    @Asynchronous
    public void create(Email email) {
        emailService.create(email);
        try {
            emailSenderService.sendMail(email.getTo(), email.getSubject(), email.getBody());
            email.setSent(true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
