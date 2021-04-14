package com.service;

import com.model.Email;
import com.repository.EmailRepository;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Dependent
public class EmailService {

    @Inject
    private EmailRepository emailRepository;

    public List<Email> getAll() {
        return emailRepository.getDatabase();
    }

    public Email create(Email email) {
        email.setId(UUID.randomUUID().toString());
        email.setDate(java.sql.Date.valueOf(LocalDate.now()));
        return emailRepository.save(email);
    }

    public boolean delete(String id) {
        return emailRepository.delete(id);
    }
}