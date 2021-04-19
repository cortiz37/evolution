package com.server.stream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.model.Email;
import com.server.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class NotificationListener {

    private final ObjectMapper objectMapper;

    private final EmailService emailService;

    public NotificationListener(ObjectMapper objectMapper, EmailService emailService) {
        this.objectMapper = objectMapper;
        this.emailService = emailService;
    }

    @KafkaListener(id = "email-client", topics = "notifications")
    public boolean receiveEmails(String payload) {
        try {
            Email email = objectMapper.readValue(payload, Email.class);
            emailService.create(email);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return true;
    }
}
