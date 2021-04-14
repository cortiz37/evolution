package com.repository;

import com.model.Email;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * In memory 'database'
 */
@ApplicationScoped
public class EmailRepository {

    private final static List<Email> database = new ArrayList<>();

    public Email save(Email email) {
        database.add(email);
        return email;
    }

    public boolean delete(String id) {
        return database.stream()
            .filter(e -> e.getId().equals(id))
            .findFirst()
            .map(database::remove)
            .orElse(false);
    }

    public List<Email> getDatabase() {
        return database.stream()
            .sorted(Comparator.comparing(Email::getDate).reversed())
            .collect(Collectors.toList());
    }
}