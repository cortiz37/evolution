package com.server.service;

import com.server.model.Email;
import com.server.repository.EmailRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class EmailService {

    private final EmailRepository emailRepository;
    private final EmailSenderService emailSenderService;

    public EmailService(EmailRepository emailRepository, EmailSenderService emailSenderService) {
        this.emailRepository = emailRepository;
        this.emailSenderService = emailSenderService;
    }

    public List<Email> getAll() {
        return emailRepository.getAll();
    }

    public Email create(Email email) {
        email.setId(UUID.randomUUID().toString());
        email.setDate(new Date());
        try {
            emailSenderService.sendMessage(email);
            email.setSent(true);
        } catch (MessagingException e) {
            log.error("Error sending the email", e);
        }
        return emailRepository.save(email);
    }

    public boolean delete(String id) {
        return emailRepository.delete(id);
    }
}