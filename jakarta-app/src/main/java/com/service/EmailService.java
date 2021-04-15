package com.service;

import com.model.Email;
import com.repository.EmailRepository;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.Date;
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
        email.setDate(new Date());
        return emailRepository.save(email);
    }

    public boolean delete(String id) {
        return emailRepository.delete(id);
    }
}